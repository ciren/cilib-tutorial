val cilibVersion = "2.0.1"

scalaVersion := "2.12.4"

name := "cilib-tutorial-book"
organization := "cirg-up"

enablePlugins(GitVersioning, TutPlugin)

git.useGitDescribe := true

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-unchecked",
  "-feature",
  "-Xlint",
//  "-Xfatal-warnings",
  "-Ywarn-dead-code",
  "-Ypartial-unification"
//  "-Ydelambdafy:inline" // workaround for future deadlock on the 2.12.1 REPL
)


scalacOptions in Tut := (scalacOptions in Tut).value.filterNot(Set("-Ywarn-unused-import"))

libraryDependencies ++= Seq(
  "net.cilib" %% "cilib-core" % cilibVersion,
  "net.cilib" %% "cilib-ga" % cilibVersion,
  "net.cilib" %% "cilib-pso" % cilibVersion,
  "net.cilib" %% "benchmarks" % "0.1.1"
)

addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.3")

tutSourceDirectory := sourceDirectory.value / "pages"
tutTargetDirectory := target.value          / "pages"

lazy val pdf  = taskKey[Unit]("Build the PDF version of the book")
lazy val html = taskKey[Unit]("Build the HTML version of the book")
lazy val epub = taskKey[Unit]("Build the ePub version of the book")
lazy val json = taskKey[Unit]("Build the Pandoc JSON AST of the book")
lazy val all  = taskKey[Unit]("Build all versions of the book")

import scala.sys.process._

pdf  := { tutQuick.value ; Seq("grunt", "pdf").! }
html := { tutQuick.value ; Seq("grunt", "html").! }
epub := { tutQuick.value ; Seq("grunt", "epub").! }
json := { tutQuick.value ; Seq("grunt", "json").! }
all  := { pdf.value ; html.value ; epub.value ; json.value }
