// Streams - Similar to lists, but tail is evaluated on demand
// Elegant solutions to search problems

// Finding prime

((1000 to 10000) filter isPrime)(1)

// vs. recursive - much shorter!
// But we construct all prime numbers!


// We want to avoid to compute tail until it is actually needed

val xs = Stream.cons(1, Stream.cons(2, Steam.empty))
Stream(1, 2, 3)
(1 to 1000).toStream // Can be applied to any collection



def streamRange(lo: Int, hi:Int): Stream[Int] =
    if (lo >= hi) Stream.empty
    else Stream.cons(lo, streamRange(lo + 1, hi))


((1000 to 10000).toStream filter isPrime)(1)


// Cons operator :: - Exception to stream applicability
x #:: xs == Stream.cons(x, xs)



trait Stream[+A] extends Seq[A] {
    def isEmpty
    def head
    def tail
}

object Stream {
    
    // The leading arrow: By name-parameter (instead by value)
    // This seems to be a key part
    def cons[T](hd: T, tl: => Stream[T]) = new Stream[T] {
        def isEmpty = false
        def head = hd
        def tail = tl
    }
    val empty = new Stream[Nothing] {
        def isEmpty = true
        def head = throw new NoSuchElementException("empty.head")
        def tail = throw new NoSuchElementException("empty.tail")
    }
}








