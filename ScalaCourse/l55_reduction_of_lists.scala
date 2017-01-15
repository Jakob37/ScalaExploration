// Reducing - Combining elements of lists to single value

// reduceLeft(list)
// Can be used to reduce sum(list) and product(list)

def sum(xs: List[Int]) = (0 :: xs) reduceLeft (_ + _)

def product(xs: List[Int]) = (1 :: xs) reduceLeft (_ * _)

// FoldLeft - Can be applied to non-empty, with default element for empty lists

abstract class List[T] {

    def reduceLeft(op: (T, T) => T): T = this match {
        case Nil => throw new Error("Nil.reduceLeft")
        case x :: xs => (xs foldLeft x)(op)
    }

    def foldLeft[U](z: U)(op: (U, T) => U): U = this match {
        case Nil => z
        case x :: xs => (xs foldLeft op(z, x))(op)
    }
}

// Mostly, either fold left or fold right can be used, but not always

def concat[T](xs: List[T], ys: List[T]): List[T] =
    (xs foldRight ys) (_ :: _)

// Here, types would not work out
