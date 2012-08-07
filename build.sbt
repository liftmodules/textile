name := "textile"

organization := "net.liftmodules"

liftVersion <<= liftVersion ?? "2.5-SNAPSHOT"

version <<= liftVersion apply { _ + "-1.1-SNAPSHOT" }
 
scalaVersion := "2.9.2"
 
scalacOptions ++= Seq("-unchecked", "-deprecation")
 
crossScalaVersions := Seq("2.9.2", "2.9.1-1", "2.9.1", "2.9.0-1", "2.9.0")

resolvers += "CB Central Mirror" at "http://repo.cloudbees.com/content/groups/public"

resolvers += "Java.net Maven2 Repository" at "http://download.java.net/maven/2/"

libraryDependencies <++= liftVersion { v =>
  "net.liftweb" %% "lift-util" % v % "compile->default" ::
  Nil
}    

libraryDependencies <++= scalaVersion { sv => 
  (sv match { 
      case "2.9.2" | "2.9.1" | "2.9.1-1" => "org.scala-tools.testing" % "specs_2.9.1" % "1.6.9" % "test"
      case _ =>  "org.scala-tools.testing" %% "specs" % "1.6.8" % "test"
      })  :: 
   (sv match { 
      case "2.9.2"  => "org.scalacheck" % "scalacheck_2.9.1" % "1.9" % "test"
      case _ => "org.scalacheck" %% "scalacheck" % "1.9" % "test"
      })  ::
  Nil
}

publishTo <<= version { _.endsWith("SNAPSHOT") match {
 	case true  => Some("snapshots" at "https://oss.sonatype.org/content/repositories/snapshots")
 	case false => Some("releases" at "https://oss.sonatype.org/service/local/staging/deploy/maven2")
  }
 } 


// For local deployment:

credentials += Credentials( file("sonatype.credentials") )

// For the build server:

credentials += Credentials( file("/private/liftmodules/sonatype.credentials") )

publishMavenStyle := true

publishArtifact in Test := false

pomIncludeRepository := { _ => false }


pomExtra := (
	<url>https://github.com/liftmodules/textile</url>
	<licenses>
		<license>
	      <name>Apache 2.0 License</name>
	      <url>http://www.apache.org/licenses/LICENSE-2.0.html</url>
	      <distribution>repo</distribution>
	    </license>
	 </licenses>
	 <scm>
	    <url>git@github.com:liftmodules/textile.git</url>
	    <connection>scm:git:git@github.com:liftmodules/textile.git</connection>
	 </scm>
	 <developers>
	    <developer>
	      <id>liftmodules</id>
	      <name>Lift Team</name>
	      <url>http://www.liftmodules.net</url>
	 	</developer>
	 </developers> 
 )
  
