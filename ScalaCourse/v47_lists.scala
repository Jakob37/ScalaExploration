// Lists

// How are they defined?

val fruit = List("apples", "oranges", "pears")
val nums = List(1, 2, 3)
val empty = List()

// Lists are immutable - Can't change elements
// Lists are recursive, while arrays are flat - Cell-wise structure (LinkedList)

// All lists constructed from Nil

fruit = "apples" :: ("oranges" :: ("pears" :: Nil))

// :: is the concatenate operator

// val nums 1 :: 2 :: 3 :: 4 :: Nil
// equivalent to
// Nil.::4.::3.::2.::1

// Other operations

// head - First element of list
// tail - List composed of all elements except first
// isEmpty - 'true' if empty, otherwise 'false'

// Often preferred to decompose lists with pattern matching

Nil - Nil constant
p :: ps - Matches list with head, and tail
List(p1, ..., pn) - Same as p1 :: ... :: pn :: Nil

// What does pattern match?
x :: y :: List(xs, ys) :: zs

// Would match 3+ element list, where third element is a List

// Insertion sort
isort(xs: List[Int]): List[Int] = xs match {
    case List() => List()
    case y :: ys => insert(y, isort(ys))
}

// The insert will recursively try to insert it in subsets of the list
// until inserted value is the correct head of remaining list

def insert(x: Int, xs: List[Int]): List[Int] = xs match {
    case List() => List(x)
    case y :: ys => if (x <= y) x :: xs else y :: insert(x, ys)
}
