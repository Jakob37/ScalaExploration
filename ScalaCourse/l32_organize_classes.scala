
// Place file in package
package progfun.examples

// Refer to Hello object in examples from REPL
> scala progfun.examples.Hello

// Alternative
import progfun.examples.Hello
> Hello()

// Import more from package
import week3._
import week3.{Rational, Hello}

// We can import from both packages and objects

/////

// What is a Trait? - Similar to Interface
trait Planar {
    def height: Int
    def width: Int
    def surface = height * width // Concrete method
}

// Superclass and two traits
class Square extends Shape with Planar with Movable

// Compared to Interfaces - Can contain concrete method and fields

/////

// Scala stems from scala.Any
// -> scala.AnyVal or -> scala.AnyRef

// Also, scala.Nothing and scala.Now subclass many classes

// AnyRef - java objects
// AnyVal - primitive values

// When is Nothing useful?
// Signal abnormal termination or empty collections

// Also Null, value of every reference type

///// Execution

// throw new Error(msg)











