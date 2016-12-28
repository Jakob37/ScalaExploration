// Procedure

// Set initial estimate
// Repeatedly improve estimate by taking mean of y and x/y

object session {

    def sqrtIter(guess: Double, x: Double): Double =
        if (isGoodEnough(guess, x)) guess
        else sqrtIter(improve(guess, x), x)


    def isGoodEnough(guess: Double, x: Double): Boolean =
        Math.abs(guess * guess -x) / x < 0.001

    def improve(guess: Double, x: Double): Double =
        (guess + x / guess) / 2

    def sqrt(x: Double): Double = sqrtIter(1.0, x)
}

println(session.sqrt(2))
println(session.sqrt(4))

