// First merge-sort implementation

def msort(xs: List[Int]): List[Int] = {
    val n = xs.length / 2
    if (n == 0) xs
    else {
        def merge(xs: List[Int], ys: List[Int]) = ???
        val (fst, snd) = xs splitAt n
        merge(msort(fst), msort(snd))
    }
}

// Merge definition

def merge(xs: List[Int], ys: List[Int]) = 
    xs match {
        case Nil =>
            ys
        case x :: xs1 =>
            ys match {
                case Nil =>
                    cs
                case y :: ys1 =>
                    if (x < y) x :: merge(xs1, ys)
                    else y :: merge(xs, ys1)
            }
    }

// Detour: Pairs and tuples
// (x, y), can have different types

case class Tuple2[T1, T2](_1: +T1, _2: +T2) {
    override def toString = "(" + _1 + "," + _2 + ")"
}


// Shorter merge
def merge(xs: List[Int], ys: List[Int]): List[Int] = 
    (xs, ys) match {
        case (Nil, ys) => ys
        case (xs, Nil) => xs
        case (x :: xs1, y :: ys1) =>
            if (x < y) x :: merge(xs1, ys)
            else y :: merge(xs, ys1)
    }
