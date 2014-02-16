name := "slickeg"

organization := "me.ljr"

scalaVersion := "2.10.3"

scalacOptions := Seq("-feature", "-unchecked", "-deprecation")

libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % "2.0.1-RC1"
  )
