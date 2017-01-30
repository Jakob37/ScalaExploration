// Recap: Case classes

abstract class JSON
case class JSeq (elems: List[JSON])             extends JSON
case class JObj (bindings: Map[String, JSON])   extends JSON
case class JNum (num: Double)                   extends JSON
case class JStr (str: String)                   extends JSON
case class JBool(b: Boolean)                    extends JSON
case object JNull                               extends JSON

def show(json: JSON): String = json match {
    case JSeq(elems) =>
        "[" + (elems map show mkString ", ") + "]"
    case JStr(str) => '\"' + str + '\"'
    case JNull => "null"
}

trait Function[-A, +R] {
    def apply(x: A): R
}

new Function1[JBinding, String] {
    def apply(x: JBinding) = x match {
        case (key, values) => key + ": " + show(value)
    }
}

// Functions being traits - We can subclass them

trait Map[Key, Value] extends (Key => Value) ...

trait Seq[Elem] extends(Int => Elem)

// This is why we can write: elems(i) ! (Compared to Java's elems[i])

// Partial function? Not yet

val f: String => String = { case "ping" => "pong" }

// Now: Partial function - Decide for what arguments it is defined
val f: PartialFunction[String, String] = { case "ping" => "pong" }



