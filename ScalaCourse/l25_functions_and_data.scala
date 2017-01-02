// Datastructure

// Scala's central datastructure is the class

// require() - Intent is to require particular argument from user
// assert() - Intent is to make sure that the program has a particular internal state
// The latter one should not be seen as the user's fault if it crashes

class Rational(x: Int, y: Int) {

    require(y != 0, "Denominator must be non-zero")

    // Secondary constructor
    def this(x: Int) = this(x, 1)

    // Greatest common divider
    private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
    private val g = gcd(x, y)

    def numer = x / g
    def denom = y / g

    def + (that: Rational) =
        new Rational(
            numer * that.denom + that.numer * denom,
            denom * that.denom)

    // Minus standing by itself. Note that colon needs separation to not become part of operator
    def unary_- : Rational = new Rational(-numer, denom)

    def - : (that: Rational) = this + -that.neg

    def < (that: Rational) = numer * that.denom < that.numer * denom

    def max(that: Rational) = if (this < that) that else this

    override def toString = numer + "/" + denom
}

val x = new Rational(1, 2)
val y = new Rational(2, 3)
val z = new Rational(4, 5)

println(x - y - z)

