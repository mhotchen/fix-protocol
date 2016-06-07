# FIX protocol for Scala

A small library for parsing and working with the [FIX (Financial Information eXchange) protocol](http://www.fixtradingcommunity.org/)

The library is a grammar definition with some case classes, so is purely for parsing and creating FIX message strings. It does not handle field types, validation or generating a checksum.

## Installation

Using SBT place the following line in your project's `build.sbt` file:

```scala
libraryDependencies += "me.mhn" %% "fix-protocol" % "1.0.0"
```

## Usage

```scala
// convert a string to case classes:
import me.mhn.fix.protocol._

val parser = new Parser
val fixMessages = (
    "8=FIX.5.0|35=8|58=Blah blah = #!@$%|\n" +
    "8=FIX.5.1|35=8|58=Blah blah = #!@$%|\n" +
    "8=FIX.5.2|35=8|58=Blah blah = #!@$%|\n" +
    "8=FIX.5.3|35=8|58=Blah blah = #!@$%|"
  ).replace('|', 1.toChar)

val caseClasses = parser.parse(parser.messages, fixMessages)
println(caseClasses) // will convert them back to a string so you can verify

// convert case classes to FIX messages
val caseClasses = Messages(
  Message(Field(Tag(8), Value("FIX.5.0")), Field(Tag(35), Value("8")), Field(Tag(58), Value("Blah blah = #!@$%"))),
  Message(Field(Tag(8), Value("FIX.5.1")), Field(Tag(35), Value("8")), Field(Tag(58), Value("Blah blah = #!@$%"))),
  Message(Field(Tag(8), Value("FIX.5.2")), Field(Tag(35), Value("8")), Field(Tag(58), Value("Blah blah = #!@$%"))),
  Message(Field(Tag(8), Value("FIX.5.3")), Field(Tag(35), Value("8")), Field(Tag(58), Value("Blah blah = #!@$%")))
)

println(caseClasses.toString)

// if something goes wrong you'll get back a ParseError case class so you might want to pattern match like so:

val invalidMessageString = (
    "8=FIX.5.0|35=8|58=Blah blah = #!@$%|\n" +
    "8=FIX.5.1|35=8|58=Blah blah = #!@$%|\n" +
    "8=FIX.5.2|35=8|58=Missing SOH at the end\n" +
    "8=FIX.5.3|35=8|58=Blah blah = #!@$%|\n"
  ).replace('|', 1.toChar)

parser.parse(parser.messages, invalidMessageString) match {
  case Messages(_)   => doNormalThing()
  case ParseError(e) => doErrorThing(e)
}
