

// Build sequence of pairs

val n = 7
(1 until n) map (n ...

// Generate pairs

((1 until n) map (i =>
    (1 until i) map (j => (i, j)))).flatten

xs flatMap f = (xs map f).flatten


// For-expression example

for (p <- persons if p.age > 20) yield p.name

persons filter (p => p.age > 20) map (p => p.name)

// For syntax
for (s) yield e

for {
    i <- 1 until n
    j <- 1 until i
    if isPrime(i + j)
} yield (i, j)

def scalarProduct(xs: List[Double], ys: List[Double]): Double = 
    for ( (x,y) <= xs zip ys) yield x * y).sum



