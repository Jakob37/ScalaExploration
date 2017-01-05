// Type A => B abbreviation for scala.Function1[A, B]

// Functions are objects with apply methods
trait Function1[A, B] {
    def apply(x: A): B
}

// How to implement (x: Int) => x * x

{
    class AnonFun extends Function1[Int, Int] {
        def apply(x: Int) = x * x
    }
    new AnonFun
}

// Anonymous class syntax
new Function1[Int, Int] {
    def apply(x: Int) = x * x
}



// Functions are objects

// Methods are not itself function value,
// But if it is placed where Function type is expected, it is converted to function value

def f(x: Int): Boolean = ...

(x: Int) => f(x)

// or expanded (eta expansion):

new Function1[Int, Boolean] {
    def apply(x: Int) = f(x)
}


// Define object List{...} with 3 functions so that users can create
// lists of length 0-2

trait List[T] {
    def isEmpty: Boolean
    def head: T
    def tail: List[T]
}

object List {
    def apply[T](x1: T, x2: T): List[T] = new Cons(x1, new Cons(x2, new Nil))
    def apply[T]() = new Nil
}
