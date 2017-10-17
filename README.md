Textile Lift Module
==================

Adds support for the Textile markup format to your Lift application.

To include this module in your Lift project, update your `libraryDependencies` in `build.sbt` to include:

Lift 3.1.x for Scala 2.12 and 2.11:

    "net.liftmodules" %% "textile_3.1" % "1.4-SNAPSHOT"

*Lift 3.0.x* for Scala 2.10 and 2.11:

    "net.liftmodules" %% "textile_3.0" % "1.3-SNAPSHOT"

*Lift 2.6.x* for Scala 2.11:

    "net.liftmodules" %% "textile_2.6" % "1.3-SNAPSHOT"

*Lift 2.6.x* for Scala 2.9 and 2.10:

    "net.liftmodules" %% "textile_2.6" % "1.3"

*Lift 2.5.x* for Scala 2.9 and 2.10:

    "net.liftmodules" %% "textile_2.5" % "1.3"



Documentation
=============

* [Rendering Textile Markup](http://cookbook.liftweb.net/#Textile) in the _Lift Cookbook_.

* [Using the Textile module](http://www.assembla.com/spaces/liftweb/wiki/Textile) on the Lift wiki.

* There are examples of using Textile in [Lift in Action](http://www.manning.com/perrett/).

**Note:** The module package changed from `net.liftweb.textile` to `net.liftmodules.textile` in May 2012.  Please consider this when referencing documentation written before that date.

---

Notes for module developers
===========================

* The [Jenkins build](https://liftmodules.ci.cloudbees.com/job/textile/) is triggered on a push to master.



