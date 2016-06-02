package me.mhn.fix.protocol

sealed trait Protocol

case class Field(tag: Tag, value: Value) extends Protocol {
  override def toString = tag + "=" + value + 1.toChar
}

case class Tag(tag: Int) extends Protocol {
  override def toString = tag.toString
}

case class Value(value: String) extends Protocol {
  override def toString = value
}

case class Message(fields: Field*) extends Protocol {
  override def toString = ("" /: fields) (_ + _.toString)
}

object Message {
  def apply(fields: List[Field]): Message = Message(fields:_*)
}

case class Messages(messages: Message*) extends Protocol {
  override def toString = messages.mkString("\n")
}

object Messages {
  def apply(messages: List[Message]): Messages = Messages(messages:_*)
}

case class ParseError(message: String) extends Protocol {
  override def toString = message
}

