// Trying to find general and convenient way to access objects in extensive class hierarchy

// Classification and access methods - Quadratric explosion of methods
// Type tests and casts - Unsafe, low-level
// Object-oriented decomposition - Works well for access, forces touch all classes for new method

// Factory methods

object Number {
    def apply(n: Int) = new Number(n)
}

object Sum {
    def apply(e1: Expr, e2: Expr) = new Sum(e1, e2)
}

// Kind of generalization of switch from C/Java

def eval(e: Expr): Int = e match {
    case Number(n) => n
    case Sum(e1, e2) => eval(e1) + eval(e2)
}

// Syntax:
// e match {
// case pattern => expr
// case pattern => expr
//}

// Patterns can be constructors, variables, wildcard patterns, constants...

// Variables should begin with lowercase letter
// Constants should begin with uppercase letter

def eval(e) = e match {
    case Number(n) => n
    case Sum(e1, e2) => eval(e1) + eval(e2)
}

// Breaks down to: eval(Number(1)) + eval(Number(2))
// Pattern matching is useful when we want to add methods rather than classes?

// Exercise

trait Expr
case class Number(n: Int) extends Expr
case class Sum(e1: Expr, e2: Expr) extends Expr

def show(e: Expr): String = e match {
    case Number(x) => x.toString
    case Sum(l, r) => show(l) + " + " show(r)
}
