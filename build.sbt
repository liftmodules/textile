name := "textile"

organization := "net.liftmodules"

version := "1.4-SNAPSHOT"

liftVersion <<= liftVersion ?? "3.1.1"

liftEdition <<= liftVersion apply { _.substring(0,3) }

moduleName <<= (name, liftEdition) { (n, e) =>  n + "_" + e }

scalaVersion := "2.12.2"

scalacOptions ++= Seq("-unchecked", "-deprecation")

crossScalaVersions := Seq("2.12.2", "2.11.7")

resolvers += "CB Central Mirror" at "http://repo.cloudbees.com/content/groups/public"

resolvers += "Java.net Maven2 Repository" at "http://download.java.net/maven/2/"

libraryDependencies <++= liftVersion { v =>
  "net.liftweb" %% "lift-util" % v % "provided" ::
  Nil
}

libraryDependencies <++= scalaVersion { sv =>
  sv match {
		case ("2.11.7" | "2.12.2" ) => {
      "org.specs2" %% "specs2-core" % "3.8.6" % "test" :: Nil
      "org.specs2" %% "specs2-matcher-extra" % "3.8.6" % "test" :: Nil
    }
  }  
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

