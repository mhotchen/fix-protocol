package me.mhn.fix.protocol

object Test extends App {
  val parser = new Parser
  val test1 = Messages(List(
    Message(Set(Field(Tag(8), Value("FIX.5.0")), Field(Tag(35), Value("8")), Field(Tag(58), Value("Blah blah = #!@$%")))),
    Message(Set(Field(Tag(8), Value("FIX.5.1")), Field(Tag(35), Value("8")), Field(Tag(58), Value("Blah blah = #!@$%")))),
    Message(Set(Field(Tag(8), Value("FIX.5.2")), Field(Tag(35), Value("8")), Field(Tag(58), Value("Blah blah = #!@$%")))),
    Message(Set(Field(Tag(8), Value("FIX.5.3")), Field(Tag(35), Value("8")), Field(Tag(58), Value("Blah blah = #!@$%"))))
  ))

  val test2 = "8=FIX.4.4|35=8|58=blah blah $%^*(=|".replace('|', 1.toChar)
  val testInvalid = "8=FIX.4.5|35=8|58=no pipe at the end".replace('|', 1.toChar)

  val parsedFromTest1 = parser.parseAll(parser.messages, test1.toString)
  val parsedFromTest2 = parser.parseAll(parser.messages, test2)
  val parsedFromInvalid = parser.parseAll(parser.message, testInvalid)

  println("-- messages as built")
  println(test1.toString.replace(1.toChar, '|'))
  println("-- messages after conversion")
  println("---- test1")
  println(parsedFromTest1.get.toString.replace(1.toChar, '|'))
  println("---- test2")
  println(parsedFromTest2.get.toString.replace(1.toChar, '|'))
  println("---- invalid")
  println(parsedFromInvalid)
}
