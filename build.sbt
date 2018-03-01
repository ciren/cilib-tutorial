name              in ThisBuild := "cilib-tutorial-book"
organization      in ThisBuild := "cirg-up"
version           in ThisBuild := "0.0.1"

scalaVersion      in ThisBuild := "2.12.4"

logLevel          in Global    := Level.Warn

tutSettings

tutSourceDirectory := sourceDirectory.value / "pages"
tutTargetDirectory := target.value          / "pages"

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-unchecked",
  "-feature",
  "-Xlint",
  "-Xfatal-warnings",
  "-Ywarn-dead-code",
  "-Ypartial-unification",
  "-Ydelambdafy:inline" // workaround for future deadlock on the 2.12.1 REPL
)

// resolvers ++= Seq(Resolver.sonatypeRepo("snapshots"))

libraryDependencies ++= Seq("net.cilib" %% "cilib-core" % "2.0.0-RC1")

addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.3")

lazy val pdf  = taskKey[Unit]("Build the PDF version of the book")
lazy val html = taskKey[Unit]("Build the HTML version of the book")
lazy val epub = taskKey[Unit]("Build the ePub version of the book")
lazy val json = taskKey[Unit]("Build the Pandoc JSON AST of the book")
lazy val all  = taskKey[Unit]("Build all versions of the book")

pdf  := { tutQuick.value ; "grunt pdf"  ! }
html := { tutQuick.value ; "grunt html" ! }
epub := { tutQuick.value ; "grunt epub" ! }
json := { tutQuick.value ; "grunt json" ! }
all  := { pdf.value ; html.value ; epub.value ; json.value }

