// Decomposition

trait Expr {
    def isNumber: Boolean  // Classification
    def isSum: Boolean     // Classification
    def numValue: Int      // Accessor
    def leftOp: Expr       // Accessor
    def rightOp: Expr      // Accessor
}

class Number(n: Int) extends Expr {
    def isNumber: Boolean = true
    def isSum: Boolean = false
    def numValue: Int = n
    def leftOp: Expr = throw new Error("Number.leftOp")
    def rightOp: Expr = throw new Error("Number.rightOp")
}

class Sum(e1: Expr, e2: Expr) extends Expr {
    def isNumber: Boolean = false
    def isSum: Boolean = true
    def numValue: Int = throw new Error("Sum.numValue")
    def leftOp: Expr = e1
    def rightOp: Expr = e2
}

// Tedious!
def eval(e: Expr): Int = {
    if (e.isNumber) e.numValue
    else if (e.isSum) eval(e.leftOp) + eval(e.rightOp)
    else throw new Error("Unknown expression " + e)
}

// Can we simplify this?

// A hacky kind-of-solution
// Check instance type
// Scala has better alternatives

x.isInstanceOf[T]  // x instanceOf T in Java
x.asInstanceOf[T]  // (T) x in Java

// Better solution

trait Expr {
    def eval: Int
}

class Number(n: Int) extends Expr {
    def eval: Int = n
}

class Sum(e1: Expr, e2: Expr) extends Expr {
    def eval: Int = e1.eval + e2.eval
}


