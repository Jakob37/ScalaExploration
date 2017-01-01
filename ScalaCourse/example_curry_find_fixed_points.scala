// Number x is fixed point of f if f(x) = x


// Initial function - Calculate fixed point iteratively

val tolerance = 0.0001

def isCloseEnough(x: Double, y: Double) = 
    abs((x - y) / x) / x) < tolerance

def fixedPoint(f: Double => Double)(firstGuess: Double) = {
    def iterate(guess: Double): Double = {
        val next = f(guess)
        if (isCloseEnough(guess, next)) next
        else iterate(next)
    }
    iterate(firstGuess)
}
> fixedPoint(x => 1 + x/2)(1)

// Iteration method testing if our value is close enough

//// Return to square root

// sqrt(x) = number of y giving y * y = x
// or, y giving y = x / y
// -> Fixed point of function (y => x / y)

sqrt(x: Double) =
    fixedPoint(y => x / y)(1)

//// Example: Implement damping square root function
def averageDamp(f: Double => Double)(x: Double) = (x + f(x)) / 2

def sqrt(x: Double) =
    fixedPoint(averageDamp(y => x / y))(1)

