/*
 * Copyright 2008-2011 WorldWide Conferencing, LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.liftmodules
package textile

import xml._

import org.specs.Specification

import net.liftweb._
import common._
import util._

class TextileDivSpec extends Specification {

  val shouldRelax = (java.lang.Boolean.getBoolean("textile.relax"))

  import TextileParser._

  "A Textile Parser" can {

    "deal with 'bq.'" in {
      toHtml("bq[de](pull-left){color:red;}. foo\nbar\n\npara") must ==/(<blockquote style="color:red;" class="pull-left" lang="de"><p>foo<br/>bar</p></blockquote> ++ <p>para</p>)
    }

    "treat 'div.' almost as 'bq.'" in {
      toHtml("div[de](pull-left){color:red;}. foo\nbar\n\npara") must ==/(<div style="color:red;" class="pull-left" lang="de">foo<br/>bar</div> ++ <p>para</p>)
    }

    "embed lists in div elements" in {
      toHtml("""<div class="span6">
* foo
* bar
</div>""") must ==/(<div class="span6">
                      <ul><li>foo</li><li>bar</li></ul>
                    </div>)
    }

    "embed text and lists in div elements" in {
      toHtml("""<div>
hello

* foo
* bar
</div>""") must ==/(<div>
                      <p>hello</p>
                      <ul><li>foo</li><li>bar</li></ul>
                    </div>)
    }

    "embed multi-line text and lists in div elements" in {
      toHtml("""<div>
hello
how are you?

* foo
* bar
</div>""") must ==/(<div>
                      <p>hello<br/>how are you?</p>
                      <ul><li>foo</li><li>bar</li></ul>
                    </div>)
    }

    "embed text (with double-\\n at the end) of div elements" in {
      toHtml("""<div>
bq[de]. hello
how are you?

* foo
* irks

fine

</div>""") must ==/(<div>
                      <blockquote lang="de"><p>hello<br/>how are you?</p></blockquote>
                      <ul><li>foo</li><li>irks</li></ul>
                      <p>fine</p>
                    </div>)
    }

    "embed text (with single-\\n at the end) of div elements" in {
      toHtml("""<div>
bq[de]. hello
how are you?

* foo
* irks

fine
</div>""") must ==/(<div>
                      <blockquote lang="de"><p>hello<br/>how are you?</p></blockquote>
                      <ul><li>foo</li><li>irks</li></ul>
                      <p>fine</p>
                    </div>)
    }

    "embed images (plus double-\\n at the end) in div elements" in {
      toHtml("""<div>
!test.jpg!

</div>""") must ==/(<div>
                      <p><img src="test.jpg" alt=""/></p>
                    </div>)
    }

    "embed images (with single-\\n at the end) in div elements" in {
      toHtml("""<div>
!test.jpg!
</div>""") must ==/(<div>
                      <p><img src="test.jpg" alt=""/></p>
                    </div>)
    }

  }
}
