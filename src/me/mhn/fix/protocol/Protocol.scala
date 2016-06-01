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

case class Message(fields: Map[Tag, Field]) extends Protocol {
  override def toString = ("" /: fields) (_ + _._2)
}

object Message {
  def apply(fields: Field*): Message = Message(fields.toList)
  def apply(fields: List[Field]): Message = Message(fields.flatMap(f => Map(f.tag -> f)).toMap)
}

case class Messages(messages: List[Message]) extends Protocol {
  override def toString = messages.mkString("\n")
}

object Messages {
  def apply(messages: Message*): Messages = Messages(messages.toList)
}
