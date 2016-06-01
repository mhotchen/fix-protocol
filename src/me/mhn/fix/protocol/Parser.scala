package me.mhn.fix.protocol

import scala.util.parsing.combinator._

class Parser extends RegexParsers {
  override protected val whiteSpace = "".r

  def messages: Parser[Messages] = repsep(message, "\n")    ^^ (Messages(_))
  def message:  Parser[Message]  = rep(field)               ^^ (x => Message(Set() ++ x))
  def field:    Parser[Field]    = tag~"="~value<~"\\x01".r ^^ {case t~"="~v => Field(t, v)} // even the last field ends in SOH character
  def tag:      Parser[Tag]      = "\\d+".r                 ^^ (x => Tag(x.toInt))
  def value:    Parser[Value]    = """([\x02-\x7f])+""".r   ^^ (Value(_))
}
