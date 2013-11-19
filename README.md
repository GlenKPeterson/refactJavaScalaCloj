refactJavaScalaCloj
===================

A demonstration of refactoring a year/month representation in Java, Scala, and Clojure.
This is the source code for the examples in my blog post:
http://glenpeterson.blogspot.com/2013/09/expression-problem-java-vs-scala-vs.html

The Java files are meant to be compiled and run:
<pre><code>cd src
$JDK_HOME/bin/javac RefactJava1.java 
$JDK_HOME/bin/javac RefactJava2.java 

$JDK_HOME/bin/java RefactJava1
$JDK_HOME/bin/java RefactJava2</code></pre>

The Scala files can be compiled, then evaluated in a REPL, or just evaluated (you have to use multi-line mode if you don't compile first)

Scala Compile (optional):
<pre><code>$SCALA_HOME/bin/scalac RefactScala1.scala 
$SCALA_HOME/bin/scalac RefactScala2.scala</code></pre>


The Clojure files are meant to be evaluated in a REPL.  You can open them in LightTable and make the buffer an InstaRepl.


Copyright 2013 Glen K. Peterson http://www.apache.org/licenses/LICENSE-2.0
