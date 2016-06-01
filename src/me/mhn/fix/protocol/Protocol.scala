package me.mhn.fix.protocol

sealed trait Protocol

case class Messages(messages: List[Message]) extends Protocol {
  override def toString = messages.mkString("\n")
}

case class Message(fields: Set[Field]) extends Protocol {
  override def toString = fields.mkString
}

case class Field(tag: Tag, value: Value) extends Protocol {
  override def toString = tag + "=" + value + 1.toChar
}

case class Tag(tag: Int) extends Protocol {
  override def toString = tag.toString
}

case class Value(value: String) extends Protocol {
  override def toString = value
}
