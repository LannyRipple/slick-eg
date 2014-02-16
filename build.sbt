name := "slickeg"

organization := "me.ljr"

scalaVersion := "2.10.3"

scalacOptions := Seq("-feature", "-unchecked", "-deprecation")

libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % "2.0.1-RC1",
  "com.h2database" % "h2" % "1.3.175",
  "org.slf4j" % "slf4j-simple" % "1.7.6"
)
