# General theory - Design pattern

Data structures such as map and flatMap are called 'monads'

* Parametric type M[T] with two operations: flatMap and unit

```
trait M[T] {
    def flatMap[U](f: T => M[U]): M[U]
}

def unit[T](x: T): M[T]
```

Examples of monads:

```
unit(x) = List(x)
unit(x) = Set(x)
unit(x) = Some(x) // Option monad
unit(x) = single(x) // Generator monad
```

flatMap is operation on each of these types, while unit is different for each monad.

## Monads and map

`map` can be defined for every monad as a combination between flatMap and unit.

```
m map f == m flatMap (x => unit(f(x)))
m map f == m flatMap (f andThen unit)
```

## Monad laws

Associativity

```
m flatMap f flatMap g == m flatMap (x => f(x) flatMap g)
```

Left unit

```
unit(x) flatMap f == f(x)
```

Right unit

```
m flatMap unit == m
```

## Monoid

Simpler form not binding anything, for example integers.

## Significance of laws - For expression

Inlining for-expression as we will get same result.

## Type: Try

```
abstract class Try[+T]
case class Sucess[T](x: T) extends Try[T]
case class Failure(ex: Exception) extends Try[Nothing]
```

### Implementation of Try

```
object Try {
    def apply[T](expr: => T): Try[T] =
        try Success(expr)
        catch {
            case NonFatal(ex) => Failure(ex)
        }
}
```

### Is try monad?

No - Left unit law fails: Try(expr) flatMap f != f(expr)
Right-hand might throw fatal errors, while left-hand would not.






























