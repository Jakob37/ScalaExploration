# Functional Reactive Programming

React to sequences of events happening over time.

Functional view: Aggregate event sequence into *signal*.

* Signal is value changing over time
* Represented as function from time to value domain
* Instead of propagating updates to mutable state, defining new signals
    in terms of existing ones.

## Example: Mouse positions

* Event based: Whenever mouse moves - event is fire.
* FRP: Signal which at any point in time represents current mouse position.

## Fundamental signal operations

* First, obtain value of signal at any time: `mousePosition()`
* Next, define signal in terms of other signals.

```{scala}
def inRectangle(LL: Position, UR: Position): Signal[Boolean] =
    Signal {
        val pos = mousePosition()
        LL <= pos && pos <= UR
    }

val sig = Signal(3)  # Signal that is always 3 (?)
```

## Time varying signals

`Var` is subclass of `Signal` for signals that can be changed.

```{scala}
val sig = Var(3)
sig.update(5)
```

We can use them kind of variables:

```{scala}
sig()  # Get value
sig() = newValue  # Assign value
```

Crucial difference: We can map them, providing link over time.

```{scala}
a() = 2
b() = 2 * a()  # Providing reference between variables forevermore
```

## Example: Bank account

```{scala}
class BankAccount {
    val balance = Var(0)
    
    def deposit(amount: Int): Unit =
        if (amount > 0) {
            # Take current value of balance(),
            # and then redefine balance to old balance plus amount
            val b = balance()
            balance() = b + amount
        }

    def withdraw(amount: Int): Unit =
        if (0 < amount && amount <= balance()) {
            val b = balance()
            balance() = b - amount
        }
        else throw new Error("insufficient funds")
}
```

```{scala}
object accounts {
    def consolidated(accts: List[BankAccount]): Signal[Int] =
        Signal(accts.map(_.balance()).sum)

    val a = new BankAccount()
    val b = new BankAccount()
    val c = consolidated(List(a, b))
    c()
    a deposit 20

    val xchange = Signal(246.00)
    val inDollar = Signal(c() * xchange())
    inDollar()
}
```
























