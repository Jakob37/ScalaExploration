# Lazy evaluation

 We want to store computed results, but not compute it all before wanted

```
lazy val x = expr
```

Haskell is default lazy!
Issue: Hard to predict time and extent of computation

We can lazily evaluate any val.

```
lazy val x = expr
```

# Computing infinite sequences

```
// lazy
def from(n: Int): Stream[Int] = n #:: from(n+1)

val nats = from(0)
val m4s = nats map (_ * 4)

def sieve(s: Stream[Int]): Stream[Int] =
    s.head #:: sieve(s.tail filter (_ % s.head != 0))

val prime = sieve(from(2))

primes.take(100).toList
```

Square roots on stream.

```
// Square root of stream
def sqrtStream(x: Double): Stream[Double] = {

    // Iteratively improve guess
    def improve(guess: Double) = (guess + x / guess) / 2

    // Aha, we lazily improves a stream of guesses
    lazyval guesses: Stream[Double] = 1 #:: (guesses map improve)
    
    // Finally, return the stream of guesses
    guesses
}
```
