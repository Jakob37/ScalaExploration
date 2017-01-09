// Main areas: bounds and variance

assertAllPos(Empty) = Empty
assertAllPos(NonEmpty()) = {NonEmpty() or Exception()}

def assertAllPos[S <: IntSet](r: S): S = ...

// <: IntSet is 'upper bound' on type parameter
// S can be instantiated if it conforms to IntSet

// S <: T - S is subtype of T
// S >: T - S is supertype of T

// Could also mix: [S >: NonEmpty <: IntSet]
// Would limit S between NonEmpty and IntSet


// List[NonEmpty] <: List[IntSet] - Covariant type

// Scala does not allow subtype overloading similar to Java
