# Runner and Our First Algorithm

In this chapter we are going to create our very own Genetic Algorithm.
As well as use it in static and dynamic enviroments.

CILib offers a data type that we may use to run our algorithms. I say "may"
because, if you so choose, you can create your own way of running the algorithms. This
data type is called `Runner`. `Runner` isn't a part of the cilib-core, but is
included in this part of the book because it is needed to demonstrate
everything we have learnt. In order to include `Runner` to your project you
need to add `cilib-exec` as a dependency in your `build.sbt`.

And since we are creating a GA we should also include `cilib-ga`. The reason
we are creating a GA rather than, let's say, a PSO is that the ga package uses
types we have seen before where as a PSO uses types exclusive to the PSO
package (We will explore these later).

Your library dependencies should look very similar to the following...

```
libraryDependencies ++= Seq(
    "net.cilib" %% "cilib-core" % "2.0.1",
    "net.cilib" %% "cilib-ga" % "2.0.1",
    "net.cilib" %% "cilib-exec" % "2.0.1"
)
```

Once you have refreshed your project we can start looking at what `Runner` is.