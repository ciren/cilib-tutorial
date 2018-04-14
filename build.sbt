enablePlugins(PreprocessPlugin)

val cilibVersion = "2.0.1"

scalaVersion := "2.12.4"

name := "cilib-tutorial-book"
organization := "cirg-up"

enablePlugins(GitVersioning, TutPlugin)

git.useGitDescribe := true

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "utf-8",                // Specify character encoding used by source files.
  "-explaintypes",                     // Explain type errors in more detail.
  "-feature",                          // Emit warning and location for usages of features that should be imported explicitly.
  "-language:existentials",            // Existential types (besides wildcard types) can be written and inferred
  "-language:experimental.macros",     // Allow macro definition (besides implementation and application)
  "-language:higherKinds",             // Allow higher-kinded types
  "-language:implicitConversions",     // Allow definition of implicit functions called views
  "-unchecked",                        // Enable additional warnings where generated code depends on assumptions.
  "-Xcheckinit",                       // Wrap field accessors to throw an exception on uninitialized access.
  "-Xfatal-warnings",                  // Fail the compilation if there are any warnings.
  "-Xfuture",                          // Turn on future language features.
  "-Xlint:adapted-args",               // Warn if an argument list is modified to match the receiver.
  "-Xlint:by-name-right-associative",  // By-name parameter of right associative operator.
  "-Xlint:constant",                   // Evaluation of a constant arithmetic expression results in an error.
  "-Xlint:delayedinit-select",         // Selecting member of DelayedInit.
  "-Xlint:doc-detached",               // A Scaladoc comment appears to be detached from its element.
  "-Xlint:inaccessible",               // Warn about inaccessible types in method signatures.
  "-Xlint:infer-any",                  // Warn when a type argument is inferred to be `Any`.
  "-Xlint:missing-interpolator",       // A string literal appears to be missing an interpolator id.
  "-Xlint:nullary-override",           // Warn when non-nullary `def f()' overrides nullary `def f'.
  "-Xlint:nullary-unit",               // Warn when nullary methods return Unit.
  "-Xlint:option-implicit",            // Option.apply used implicit view.
  "-Xlint:package-object-classes",     // Class or object defined in package object.
  "-Xlint:poly-implicit-overload",     // Parameterized overloaded implicit methods are not visible as view bounds.
  "-Xlint:private-shadow",             // A private field (or class parameter) shadows a superclass field.
  "-Xlint:stars-align",                // Pattern sequence wildcard must align with sequence component.
  "-Xlint:type-parameter-shadow",      // A local type parameter shadows a type already in scope.
  "-Xlint:unsound-match",              // Pattern match may not be typesafe.
  "-Yno-adapted-args",                 // Do not adapt an argument list (either by inserting () or creating a tuple) to match the receiver.
  "-Ypartial-unification",             // Enable partial unification in type constructor inference
  "-Ywarn-dead-code",                  // Warn when dead code is identified.
  "-Ywarn-extra-implicit",             // Warn when more than one implicit parameter section is defined.
  "-Ywarn-inaccessible",               // Warn about inaccessible types in method signatures.
  "-Ywarn-infer-any",                  // Warn when a type argument is inferred to be `Any`.
  "-Ywarn-nullary-override",           // Warn when non-nullary `def f()' overrides nullary `def f'.
  "-Ywarn-nullary-unit",               // Warn when nullary methods return Unit.
  "-Ywarn-numeric-widen",              // Warn when numerics are widened.
  "-Ywarn-unused:implicits",           // Warn if an implicit parameter is unused.
  "-Ywarn-unused:imports",             // Warn if an import selector is not referenced.
  "-Ywarn-unused:locals",              // Warn if a local definition is unused.
  "-Ywarn-unused:params",              // Warn if a value parameter is unused.
  "-Ywarn-unused:patvars",             // Warn if a variable bound in a pattern is unused.
  "-Ywarn-unused:privates",            // Warn if a private member is unused.
  "-Ywarn-value-discard"               // Warn when non-Unit expression results are unused.
)


scalacOptions in Tut := (scalacOptions in Tut).value.filterNot(Set("-Ywarn-unused-import", "-Ywarn-unused:imports"))

libraryDependencies ++= Seq(
  "net.cilib" %% "cilib-core" % cilibVersion,
  "net.cilib" %% "cilib-ga" % cilibVersion,
  "net.cilib" %% "cilib-pso" % cilibVersion,
  "net.cilib" %% "benchmarks" % "0.1.1"
)

addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.3")

tutSourceDirectory := target.value / "preprocessed"
tutTargetDirectory := target.value / "pages"

sourceDirectory in Preprocess := sourceDirectory.value / "pages"
target in Preprocess := target.value / "preprocessed"

preprocessVars in Preprocess := Map(
  "CILIB_VERSION" -> cilibVersion
//  "DATE" -> new Date().toString
)


lazy val build = taskKey[Unit]("Compile the sources of the book")

build := {
  (preprocess in Preprocess).value
  tutQuick.value
}

lazy val pdf  = taskKey[Unit]("Build the PDF version of the book")
lazy val html = taskKey[Unit]("Build the HTML version of the book")
lazy val epub = taskKey[Unit]("Build the ePub version of the book")
lazy val json = taskKey[Unit]("Build the Pandoc JSON AST of the book")
lazy val all  = taskKey[Unit]("Build all versions of the book")

import scala.sys.process._

pdf  := { build.value ; Seq("grunt", "pdf").! }
html := { build.value ; Seq("grunt", "html").! }
epub := { build.value ; Seq("grunt", "epub").! }
json := { build.value ; Seq("grunt", "json").! }
all  := { pdf.value ; html.value ; epub.value ; json.value }
