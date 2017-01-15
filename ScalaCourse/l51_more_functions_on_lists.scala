// List methods

// xs.length  - Number of elements
// xs.last - Last element
// xs.init - All elements except last one
// xs take n - List with first n elements of xs, or xs if shorter than n
// xs drop n - Rest of collection after taking n elements
// xs(n) - The nth element

// xs ++ ys - Concatenate lists
// xs.reverse - Reverse list
// xs updated(n,x) - List with same elements, except n which is updated with x
// xs indexOf x - Index of x, -1 if not present
// xs contains x - Check whether exists in list

// Implement last
def last[T](xs: List[T]): T = xs match {
    case List() => Error
    case List(x) => x
    case y :: ys => last(ys)
}


// Implement init
def init[T](xs: List[T]): List[T] = xs match {
    case List() => throw new Error("init of empty list")
    case List(x) = List()
    case y :: ys = y :: init(ys)
}

// Concatenation
def concat[T](xs: List[T], ys: List[T]) = xs match {
    case List() => ys
    case z :: zs => z :: concat(zs, ys)
}

// Reverse
def reverse[T](xs: List[T]): List[T] = xs match {
    case List() => xs
    case y :: ys => reverse(ys) ++ List(y)
}

// Exercise: removeAt[T](xs: List[T], n: Int)
// Helper function?

def removeAt(n: Int, xs: List[Int]) = (xs take n) ::: (xs drop n + 1)
