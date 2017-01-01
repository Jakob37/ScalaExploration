// Using anonymous functions
// x:Int => a, b <- The basic syntax,
// Typing can be omitted when clear from context
def sumInts(a: Int, b: Int) = sum(x => x, a, b)
def sumCubes(a: Int, b: Int) = sum(x => x * x * x, a, b)


// Tail-recursive version of sum-arbitrary-function
// Accumulator is here summed value this far
def sum(f:Int => Int)(a:Int, b:Int) = {
    def loop(a:Int, acc:Int):Int = {
        if (a > b) acc

        // Increase lower bound
        // Add f(a) to accumulator for next call
        else loop(a+1, f(a) + acc)
    }
    loop(a, 0)
}

//// Let's do currying

// Create and return a composed sum function for target
// given function
def sum(f: Int => Int): (Int, Int) => Int = {
    def sumF(a:Int, b:Int): Int = 
        if (a > b) 0
        else f(a) + sumF(a + 1, b)
    sumF
}

// Apply sum to cube, and return sum of cubes
// sum(cube) is equivalent to sumCubes
// This is then applied to arguments (1, 10)
sum(cube)(1, 10)

// Simplified currying syntax
def sum(f: Int => Int)(a:Int, b:Int):Int = {
    if (a > b) 0
    else f(a) + sum(f)(a + 1, b)
}

// The type of this would be
// (Int => Int) => (Int, Int) => Int
// Argument 1: function taking and giving one int
// Argument 2: Two input ints
// Argument 3: Output int


//// Exercise

// (1) Product function

def product(f: Int => Int)(a:Int, b:Int): Int = {
    if (a > b) 1
    else f(a) * product(f)(a + 1, b)
}
> product(x => x * x)(3, 4)
// Result: 144 (3 ** 2 * 4 **2)

// (2) Factorial in terms of product

def fact(n:Int) = product(x => x)(1, n)
> fact(5)
// Result: 120

// (3) Generalize sum and product functions

def mapReduce(f:Int => Int, combine:(Int, Int) => Int, zero:Int)(a:Int, b:Int): Int = {
    if (a > b) zero
    else combine(f(a), mapReduce(f, combine, zero)(a+1, b)
}


// Define product in terms of mapReduce
def product(f:Int => Int)(a:Int, b:Int): Int = 
    mapReduce(f, (x, y) => x * y, 1)(a, b)
// The target function to evaluate is f
// Borders of range of inputs are a and b
// The 1 indicates zero value, which for multiplication is 1 (2 * 1 = 1)
// The mapReduce takes our inital function, as well as lambda function,
//  which in this case gives product of x and y

















