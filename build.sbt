import sbt._
import sbt.Keys._
import bintray.Keys._

val moorkaVersion = "0.4.0"

scalaVersion := "2.10.5"

version := moorkaVersion

organization := "com.tenderowls.opensource"

name := "Moorka Resources Plugin"

normalizedName := "moorka-resources-plugin"

licenses := Seq("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0.html"))

homepage := Some(url("http://github.com/tenderowls/moorka"))

scalacOptions ++= Seq("-deprecation", "-feature")

sbtPlugin := true

val publishSettings = moorkaVersion.endsWith("SNAPSHOT") match {
  case true => Seq(
    publishTo := Some("Flexis Thirdparty Snapshots" at "https://nexus.flexis.ru/content/repositories/thirdparty-snapshots")
  )
  case false => bintraySettings ++ bintrayPublishSettings ++ Seq(
    repository in bintray := "moorka",
    bintrayOrganization in bintray := Some("tenderowls"),
    publishMavenStyle := false
  )
}

seq(publishSettings:_*)
