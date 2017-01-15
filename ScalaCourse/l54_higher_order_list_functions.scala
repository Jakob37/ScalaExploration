// Recurring patterns for computations on lists

abstract class List[T] {

    def map[U](f: T => U): List[U] = this match {
        case Nil => this
        case x :: xs => f(x) :: xs.map(f)
    }

    def filter(p: T => Boolean): List[T] = this match {
        case Nil => this
        case x :: xs => if (p(x)) x :: xs.filter(p) else xs.filter(p)
    }
}

// Exercise

def squareList(xs: List[Int]): List[Int] = xs match {
    case Nil => Nil
    case y :: ys => y * y :: squareList(ys)
}

def squareList(xs: List[Int]): List[Int] =
    xs map (x => x * x)


def posElems(xs: List[Int]): List[Int] = xs match {
    case Nil => xs
    case y :: ys => if (y > 0) y :: ...
}

// Other useful higher-orders are filterNot, partition, takeWhile

// Exercise: Write pack function

def pack[T](xs: List[T]): List[List[T]] = xs match {
    case Nil => Nil
    case x :: xs1 => 
        val (first, rest) = xs span (y => y == x )
        first :: pack(rest)
}

def encode[T](xs: List[T]): List[(T, Int)] = 
    pack(xs) map (ys => (ys.head, ys.length))

// Will likely need to revisit these
