// Both iterable and functions

// Kind of associating keys and values

// The Option type

trait Option[+A]
case class Some[+A](value: A) extends Option[A]
object None extends Option[Nothing]

// Map example: Polynomials

class Poly(val terms: Map[Int, Double]) {
    
    def adjust(term: (Int, Double)): (Int, Double) = {
        val (exp, coeff) = term
        terms get exp match {
            case Some(coeff1) => exp -> coeff + coeff1
            case None => exp -> coeff
        }
    }

    def + (other: Poly) = new Poly(terms ++ (other.terms map adjust))
    override def toString = 
        (for ((exp, coeff) <- terms.toList.sorted.reverse) yield coeff + "x^" + exp) mkString
}

val p1 = new Poly(Map(1 -> 2.0, 3 -> 4.0, 5 -> 6.2))
val p2 = new Poly(Map(0 -> 3.0, 3 -> 7.0))
p1 + p2

// So far - maps have been partial functions - Failing for some values
// We can do it a complete function by including default values

class Poly(terms0: Map[Int, Double]) {

    def this(bindings: (Int, Double)*) = this(bindings.toMap)

    val terms = terms0 withDefaultValue 0.0
    def + (other: Poly) = new Poly(terms ++ (other.terms map adjust))
    def adjust(term: (Int, Double)): (Int, Double) = {
        val (exp, coeff) = term
        exp -> (coeff + terms(exp))
    }

    override def toString = 
    (???)

    def + (other: Poly) = new Poly((other.terms foldLeft terms)(addTerm))
    def addTerm(terms: Map[Int, Double], term: (Int, Double)): Map[Int, Double] = {
        val (exp, coeff) = term
        terms + (exp -> (coeff + terms(exp)))
    }
}


