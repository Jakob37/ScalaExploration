# A Simple FDR Implementation

## Signal API

```{scala}
class Signal[T](expr: => T) {
    def apply(): T = ???
}

object Signal {
    def apply[T](expr: => T) = new Signal(expr)
}

class Var[T](expr: => T) extends Signal[T](expr) {
    def update(expr: => T): Unit = ???
}

object Var {
    def apply[T](expr: => T) = new Var(expr)
}
```

Each signal maintains:

* Current value
* Current expression defining signal value
* Set of observers - Other signals depending on its value

If signal changes - All observers need to be re-evaluated.

## Dependency maintenance

What signall `caller` gets defined or updated by the expression?

## Stackable variables

Initial signal, and then update value based on other signals.

## Object `Signal`

```{scala}
object NoSignal extends Signal[Nothing](???) { ... }

object Signal {
    private val caller = new StackableVariable[Signal[_]](NoSignal)
    def apply[T](expr: => T) = new Signal(expr)
}
```

## Signal class

```{scala}
class Signal[T](expr: => T) {
    import Signal._
    private var myExpr: () => T = _
    private var myValue: T = _
    private var observers: Set[Signal[_]] = Set()
    update(expr)

    protected def update(expr: => T): Unit = {
        myExpr = () => expr
        computeValue()
    }

    protected def computeValue(): Unit = {
        myValue = caller.withValue(this)(myExpr())
    }

    // Simple way?
    // protected def computeValue(): Unit =
    //    myValue = caller.withValue(this)(myExpr())

    // Reevaluating callers?
    protected def computeValue(): Unit = {
        val newValue = caller.withValue(this)(myExpr())
        if (myValue != newValue) {
            myValue = newValue
            val obs = observers
            observers = Set()
            obs.foreach(_.computeValue())
        }
    }

    def apply() = {
        observers += caller.value
        // Observer of signal may not contain signal itself
        assert(!caller.value.observers.contains(this), "cyclic signal definition")
        myValue
    }
}
```

## Reevaluating callers

Signal's current value can change when:

* Update operation called on `Var`
* Value of dependent signal changes

## Handing `NoSignal`

```{scala}
object NoSignal extends Signal[Nothing](???) {
    override def computeValue() = ()
}
```

## Handling `Vars`

`Var` is `Signal` that can be updated by client program.

```{scala}
class Var[T](expr: => T) extends Signal[T](expr) {
    override def update(expr: => T): Unit = super.update(expr)
}

object Var {
    def apply[T](expr: => T) = new Var(expr)
}
```

# Thread-local state

Can use synchronization. More appropriately - Thread-local states.
Wrapped in java as `DynamicVariable`.

Here, we can switch out `StackableVariable`.

Disadvantages:

* Imperative nature produce hidden dependencies that are hard to manage
* Implementation on JDK involves global hash table lookup which can be a performace problem
* Does not play well in situations where threads are multiplexed between several tasks

# Another solution: Implicit parameters

Passing current value into signal expression as implicit parameter.

# Conclude

* Quick tour of funcitonal reactive programming
* Can also work with continous signals - Here, sampling is used instead of event propagation























