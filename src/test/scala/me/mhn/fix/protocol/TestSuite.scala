package me.mhn.fix.protocol

import org.scalatest.FunSpec

class TestSuite extends FunSpec {
  val parser = new Parser

  val validMessagesObject = Messages(
    Message(Field(Tag(8), Value("FIX.5.0")), Field(Tag(35), Value("8")), Field(Tag(58), Value("Blah blah = #!@$%"))),
    Message(Field(Tag(8), Value("FIX.5.1")), Field(Tag(35), Value("8")), Field(Tag(58), Value("Blah blah = #!@$%"))),
    Message(Field(Tag(8), Value("FIX.5.2")), Field(Tag(35), Value("8")), Field(Tag(58), Value("Blah blah = #!@$%"))),
    Message(Field(Tag(8), Value("FIX.5.3")), Field(Tag(35), Value("8")), Field(Tag(58), Value("Blah blah = #!@$%")))
  )

  val validMessageString = (
      "8=FIX.5.0|35=8|58=Blah blah = #!@$%|\n" +
      "8=FIX.5.1|35=8|58=Blah blah = #!@$%|\n" +
      "8=FIX.5.2|35=8|58=Blah blah = #!@$%|\n" +
      "8=FIX.5.3|35=8|58=Blah blah = #!@$%|"
    ).replace('|', 1.toChar)

  val invalidMessageString = (
      "8=FIX.5.0|35=8|58=Blah blah = #!@$%|\n" +
      "8=FIX.5.1|35=8|58=Blah blah = #!@$%|\n" +
      "8=FIX.5.2|35=8|58=Missing SOH at the end\n" +
      "8=FIX.5.3|35=8|58=Blah blah = #!@$%|\n"
    ).replace('|', 1.toChar)

  describe("Messages from objects") {
    describe("when created correctly") {
      it("should generate a valid list of FIX messages as strings") {
        assert(validMessagesObject.toString == validMessageString)
      }
    }
    describe("when created incorrectly") {
      it("should give a parse error") {
        assert(parser.parse(parser.messages, invalidMessageString) match {
          case ParseError(_) => true
          case _ => false
        })
      }
    }
  }

  describe("Messages from strings") {
    describe("when the strings are valid messages") {
      it("should create a valid Messages class with four Message objects") {
        assert(parser.parse(parser.messages, validMessageString) match {
          case Messages(_: Message, _: Message, _: Message, _: Message) => true
          case _ => false
        })
      }
    }
    describe("when converted to an object and back to a string again") {
      it("should still be the same string") {
        assert(parser.parse(parser.messages, validMessageString).toString == validMessageString)
      }
    }
  }
}
