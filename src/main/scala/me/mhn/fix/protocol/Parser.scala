package me.mhn.fix.protocol

import scala.util.parsing.combinator._

class Parser extends RegexParsers {
  override protected val whiteSpace = "".r

  def messages: Parser[Messages] = repsep(message, "\n")    ^^ (Messages(_))
  def message:  Parser[Message]  = rep(field)               ^^ (Message(_))
  def field:    Parser[Field]    = tag~"="~value<~"\\x01".r ^^ {case t~"="~v => Field(t, v)} // even the last field ends in SOH character
  def tag:      Parser[Tag]      = "\\d+".r                 ^^ (x => Tag(x.toInt))
  def value:    Parser[Value]    = """([\x20-\x7f])+""".r   ^^ (Value(_))

  def parse(definition: Parser[Protocol], string: String): Protocol = parseAll(definition, string) match {
    case Success(e, _) => e
    case f: NoSuccess => ParseError(f.msg)
  }
}
