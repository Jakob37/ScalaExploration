// Optional video

// List - Immutable
// Array - Mutable as we can change elements

// C[T] - C[A] and C[B] can have three relationships
// C[A] <: C[B] - C is covariant
// C[A] >: C[B} - C is contravariant
// Neither is subtype of the other - Nonvariant

// class[A+]
// class[A-]

// Functions are contravariant in argument types, and covariant in result types
// This is controlled by a set of rules

package week4

trait List[+T] {
    def isEmpty: Boolean
    def head: T
    def tail: List[T]
}

class Cons[T](val head: T, val tail: List[T]) extends List[T] {
    def isEmpty = false
}

object Nil extends List[Nothing] {
    def isEmpty: Boolean = true
    def head: Nothing = throw new NoSuchElementException("Nil.head")
    def tail: Nothing = throw new NoSuchElementException("Nil.tail")
}

object test {
    val x: List[String] = Nil
}


