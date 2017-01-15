// Making merge sort more general

def msort[T](xs: List[T])(lt: (T, T) => Boolean): List[T] = {
    ...
}

// Parametrization with Ordered (scala.math.Ordering[T])

def msort[T](xs: List[T])(implicit ord: Ordering[T]): List[T]


