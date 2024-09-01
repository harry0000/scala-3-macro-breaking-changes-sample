val scala213 = "2.13.14"
val scala3 = "3.5.0"

ThisBuild / scalaVersion := scala213
ThisBuild / crossScalaVersions := Seq(scala213, scala3)

ThisBuild / organization := "io.github.harry0000"

lazy val compilerOptions = Def.setting(
  CrossVersion.partialVersion(scalaVersion.value) match {
    case Some((2, 13)) => Seq(
      "-Xlint",
      "-Xsource:3",
    )
    case _ => Seq(
      "-Wunused:imports",
      "-Wunused:locals",
      "-Wunused:params",
      "-Wshadow:all"
    )
  }
)

lazy val commonSettings = Seq(
  scalacOptions ++= Seq(
    "-deprecation",
    "-feature",
    "-unchecked",
    "-Wconf:any:e",
    "-Werror"
  ) ++ compilerOptions.value
)

lazy val root = project
  .in(file("."))
  .settings(
    name := "macro-sample",
    description := "Scala 3 macro breaking changes sample",
    version := "0.1.0-SNAPSHOT",
    commonSettings,
    scalaVersion := scala3,
    libraryDependencies ++= Seq(
      "org.scalameta" %% "munit" % "1.0.0" % Test
    )
  )
  .aggregate(domain, infra, infraMacro)
  .dependsOn(domain, infra, infraMacro)

lazy val domain = (project in file("domain"))
  .settings(commonSettings)

lazy val infraMacro = (project in file("infra-macro"))
  .settings(
    commonSettings,
    libraryDependencies ++= {
      CrossVersion.partialVersion(scalaVersion.value) match {
        case Some((2, 13)) => Seq(
          "org.scala-lang" % "scala-reflect" % scalaVersion.value
        )
        case _ => Seq.empty
      }
    }
  )

lazy val infra = (project in file("infra"))
  .settings(
    commonSettings,
    libraryDependencies ++= Seq(
      "com.lihaoyi" %% "sourcecode" % "0.4.2",
      "org.scalameta" %% "munit" % "1.0.0" % Test
    )
  )
  .dependsOn(domain, infraMacro)
