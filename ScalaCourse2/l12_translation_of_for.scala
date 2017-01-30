// For-expressions and higher-order functions

def mapFun[T, U](xs: List[T], f: T => U): List[U] =
    for (x <- xs) yield f(x)

def flatMap[T, U](xs: List[T], f: T => Iterable[U]): List[U] =
    for (x <- xs; y <- f(x)) yield y

def filter[T](xs: List[T], p: T => Boolean): List[T] =
    for (x <- xs if p(x)) yield x

// Multi-level for expressions will layer by layer be translated to a simple value map
// This seems to have nice connections to flatMap


