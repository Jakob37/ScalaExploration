// Procedure

// Set initial estimate
// Repeatedly improve estimate by taking mean of y and x/y

// Also, lexical scoping allowing function separation without namespace pollution
// Furthermore, it allows reduction of explicit x-usage (not sure I agree fully here?)

object session {

    def sqrt(x: Double) = {

        def sqrtIter(guess: Double): Double =
            if (isGoodEnough(guess)) guess
            else sqrtIter(improve(guess))


        def isGoodEnough(guess: Double): Boolean =
            Math.abs(guess * guess - x) / x < 0.001

        def improve(guess: Double): Double =
            (guess + x / guess) / 2


        sqrtIter(1.0)
    }
}

println(session.sqrt(2))
println(session.sqrt(4))

