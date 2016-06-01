# FIX protocol for Scala

A small library for parsing and working with the FIX protocol

This is the grammar definition and a handful of case classes at this point. It will successfully parse a FIX message string (or series of them) as demonstrated by the examples in Test.scala

It can also convert the Protocol case classes in to valid FIX messages using the toString methods
