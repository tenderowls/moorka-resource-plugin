import sbt._
import sbt.Keys._
import bintray.Keys._

val currentScalaVersion = "2.10.5"
val moorkaVersion = "0.4.0-SNAPSHOT"

scalaVersion := currentScalaVersion

val dontPublish = Seq(
  publish := { }
)

val publishSettings = moorkaVersion.endsWith("SNAPSHOT") match {
  case true => Seq(
    publishTo := Some("Flexis Thirdparty Snapshots" at "https://nexus.flexis.ru/content/repositories/thirdparty-snapshots"),
    credentials += {
      val ivyHome = sys.props.get("sbt.ivy.home") match {
        case Some(path) ⇒ file(path)
        case None ⇒ Path.userHome / ".ivy2"
      }
      Credentials(ivyHome / ".credentials")
    }
  )
  case false => bintraySettings ++ bintrayPublishSettings ++ Seq(
    repository in bintray := "moorka",
    bintrayOrganization in bintray := Some("tenderowls"),
    publishMavenStyle := false
  )
}

lazy val root = project.
  in(file(".")).
  settings(publishSettings:_*).
  settings(
    version := moorkaVersion,
    organization := "com.tenderowls.opensource",
    licenses := Seq("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0.html")),
    homepage := Some(url("http://github.com/tenderowls/moorka-resouce-plugin")),
    scalacOptions ++= Seq("-deprecation", "-feature"),
    scalaVersion := "2.10.4",
    sbtPlugin := true
  )
