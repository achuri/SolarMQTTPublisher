import AssemblyKeys._

seq(assemblySettings: _*)

mergeStrategy in assembly := {
  case m if m.toLowerCase.endsWith("manifest.mf")          => MergeStrategy.discard
  case _                                                   => MergeStrategy.first
}

name := "sender"

version := "1.0"

scalaVersion := "2.10.4"

libraryDependencies ++= Seq(
  "org.eclipse.paho"       % "mqtt-client"          % "0.4.0",
  "com.googlecode.json-simple" % "json-simple"      % "1.1" 
)

resolvers ++= Seq(
  "Eclipse Paho Repo" at "https://repo.eclipse.org/content/repositories/paho-releases/"
)
