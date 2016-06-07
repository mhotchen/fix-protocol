name := "FIX Protocol"

organization := "me.mhn"

version := "1.0.0"

scalaVersion := "2.11.8"

libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.4"
libraryDependencies += "org.scalactic" %% "scalactic" % "2.2.6"
libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.6" % "test"

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")

pomIncludeRepository := { _ => false }
pomExtra :=
  <url> https://github.com/mhotchen/fix-protocol</url>
  <licenses>
    <license>
      <name>Apache 2</name>
      <url>https://www.apache.org/licenses/LICENSE-2.0.txt</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>git@github.com:mhotchen/fix-protocol.git</url>
    <connection>scm:git:git@github.com:mhotchen/fix-protocol.git</connection>
  </scm>
  <developers>
    <developer>
      <id>mhotchen</id>
      <name>Matthew Hotchen</name>
      <url>http://mhn.me</url>
    </developer>
  </developers>
