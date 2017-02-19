# The Observer pattern in Scala

* Model: Capturing state
* Views: Subscribes to model and shows state

Model publishes information to views.

Sometimes third part - controller - managing interaction between
view and model.

## Publisher trait

Allows publishing event to list of subscribed entities.

```{scala}
trait Publisher {
    private var subscribers: Set[Subscriber] = Set()

    def subscribe(subscriber: Subscriver): Unit =
        subscribers += subscriber

    def unsubscribe(subscriber: Subscriber): Unit =
        subscribers -= subscriber

    def publish(): Unit =
        subscribers.foreach(_.handler(this))
}

trait Subscriber {
    def handler(handler: Publisher)
}
```

## Bank account example

We add a Subscriber 'Consolidator', subscribing to list of bank accounts.

## Usage example

```{scala}
object observers {
    val a = new BankAccount
    val b = new BankAccount
    val c = new Consolidator(List(a, b))

    c.totalBalance
    a deposit 20
    c.totalBalance
}
```

## Evaluate

Good parts:

* Decouple views from state
* Allows having varying number of views of given state
* Simple to set up

Problematic parts:

* Forces imperative style - Handlers are Unit typed
* Many moving parts that needs to be coordinated
* Concurrency makes things more complicated
* Views tightly bound to state - View updates happens immediately

To quantify (from Adobe presentation):

* 1/3 of code in Adobe's desktop applications devoted to event handling
* 1/2 of bugs are found in this code

Rest of course: How can we improve on this pattern?
























