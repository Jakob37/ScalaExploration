// Types is the way the compiler sees the classes

// Cons-list, cells referring to values, until we hit nil
// Pairwise cells refering to value and to next or nil

trait List[T] {
    def isEmpty: Boolean
    def head: T
    def tail: List[T]
}

// head and tail already implemented using val definition
class Cons[T](val head: T, val tail: List[T]) extends List[T] {
    def isEmpty = false
}

class Nil[T] extends List[T] {
    def isEmpty: Boolean = true
    def head: Nothing = throw new NoSuchElementException("Nil.head")
    def tail: Nothing = throw new NoSuchElementException("Nil.tail")
}

def singleton[T](elem: T) = new Cons[T](elem, new Nil[T])
singleton[Int](1)
singleton[Boolean](true)

// Usually the Scala compiler can deduce types directly
singleton(1)
singleton(true)

// Two forms of polymorphism: subtyping and generics

// Exercise: Find n-th value in our list

object nth {
    def nth(n: Int, xs: List[T]): T =
        if (xs.isEmpty) throw new IndexOutOfBoundsException
        else if (n == 0) xs.head
        else nth(n - 1, xs.tail)
    
    val list = new Cons(1, new Cons(2, new Cons(3, new Nil)))

    nth(2, list)  // 3
    nth(-1, list) // Exception!
}







