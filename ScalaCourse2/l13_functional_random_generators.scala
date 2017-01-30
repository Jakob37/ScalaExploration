// Other uses for For-expressions

// Only tied to collections? Nope.
// Example: Random value generators

trait Generator[+T] {
    def generate: T
}

val integers = new Generator[Int] {
    val rand = java.util.Random
    def generate = rand.NextInt()
}

val booleans = new Generator[Boolean] {
    def generate = rand.Next() > 0.5
}


trait Generator[+T] {
    self =>

    def generate: T

    def map[S](f: T => S): Generator[s] = new Generator[S] {
        def generate = f(self.generate)
    }

    def flatMap[S](f: T => Generator[S]): Generator[S] = new Generator[S] {
        def generate = f(self.generate).generate
    }
}

// pairs generator expanded

// Starting with
def pairs[T, U](t: Generator[T], u: Generator[U]) = t flatMap {
    x => u map { y => (x, y) }
}

// Expand the map with the corresponding generator
def pairs[T, U](t: Generator[T], u: Generator[U]) = t flatMap {
    x => new Generator[(T, U)] {
        def generate = (x, u.generate)
    }
}

// Also expand the flatmap part
def pairs[T, U](t: Generator[T], u: Generator[U]) = new Generator[(T, U)] {
    def generate = (new Generator[(T, U)] {
        def generate = (t.generate, u.generate)
    }).generate
}

// Simplified
def pairs[T, U](t: Generator[T], u: Generator[U]) = new Generator[(T, U)] {
    def generate = (t.generate, u.generate)
}

//// Generator examples

def single[T](x: T): Generator[T] = new Generator[T] {
    def generate = x
}

def choose(lo: Int, hi: Int): Generate[Int] =
    for (x <- integers) yield lo + x % (hi - lo)

def oneOf[T](xs: T*): Generator[T] =
    for (idx <- choose(0, xs.length)) yield xs(idx)


// Exercise: Tree generator

def leafs: Generator[Leaf] = for {
    x <- integers
} yield Leaf(x)

def inners: Generator[Inner] = for {
    l <- trees
    r <- trees
} yield Inner(l, r)

def trees: Generator[Tree] = for {
    isLeaf <- booleans
    tree <- if (isLeaf) leafs else inners
} yield tree

// Can also do testing by feeding random input (in some cases)

// Random test function

def test[T](g: Generator[T], numTimes: Int = 100)
    (test: T => Boolean): Unit = {
        for (i <- 0 until numTimes) {
            val value = g.generate
            assert(test(value), "test failed for " + value)
        }
        println("Passed " + numTimes + " tests")
    }



















