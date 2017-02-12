# Functions and State

Let's introduce state! This means we cannot necessarily express everything purely.

* State: Behaviour influenced by history
* `var` for getting mutable variable

## Bank account example

```
val account = new BankAccount
acct deposit 50
acct withdraw 20
acct withdraw 20
```

## Identity and change 

Referential transparency - Not true anymore!

In this case, x and y is not the same.

```
val x = new BankAccount
val y = new BankAccount
```

Model of subtitution cannot be used - Replacing name with function using it.

## Loops

Loops non-essential for imperative programs. Variables are enough.

This one can be tail recursive - Making it as efficient as an iterative instance.

```
def WHILE(condition: => Boolean)(command: => Unit): Unit =
    if (condition) {
        command
        WHILE(condition)(command)
    }
    else ()
```

What is used in Scala?

```
do {
    # command
} while (condition)
```

Classical for-loop with mutated state cannot be properly modeled.

# Discrete event simulation - Extended example

## Introduction

### Digital circuits

Basic components:

* Inverter - Inversing input
* AND gate - Conjunction of inputs
* OR gate - Disjunction of inputs

Features:

* Delay

### Diagrams

```
# Inverter
IN => OUT

# AND
IN => OUT_COMB
IN => 

# OR
IN => OUT_COMB
IN => 
```

### Components

* Components
* Wire

### Functions placing gates

```
def inverter(input: Wire, output: Wire): Unit
def andGate(a1: Wire, a2: Wire, output: Wire): Unit
def orGate(o1: Wire, o2: Wire, output: Wire): Unit
```

Higher-order component example.

```
def fullAdder(a: Wire, b: Wire, cin: Wire, sum: Wire, cout: Wire): Unit = {
    val s = new Wire
    val c1 = new Wire
    val c2 = new Wire
    halfAdder(b, cin, s, s1)
    halfAdder(a, s, sum, c2)
    orGate(c1, c2, cout)
}
```

### Exercise

```
def f(a: Wire, b: Wire, c: Wire): Unit = {
    val d, e, f, g = new Wire
    inverter(a, d)    # d = !a
    inverter(b, e)    # e = !b
    andGate(a, e, f)  # f = a && !b
    andGate(b, d, g)  # g = b && !a
    orGate(f, g, c)   # c = (a && !b) or (!a && b)   - XOR!
}
```

### Implement event simulation

```
trait Simulation {
    def currentTime: Int = ???
    def afterDelay(delay: Int)(block: => Unit)

}
```

Class hierarchy:

* Top: Simulation
* Gates (wire, AND, OR, INV)
* Circuits (HA, adder)
* My simulation

### Wire operations

* getSignal: Boolean 
* setSignal(sig: Boolean): Unit
* addAction(a: Action): Unit  # When wire changes, certain things can happen

### Implementation of class Wire

```{scala}
class Wire {
    private var sigVal = false
    private var actions: List[Action] = List()
    def getSignal: Boolean = sigVal
    def setSignal(s: Boolean): Unit =
        if (s != sigVal) {
            sigVal = s
            actions foreach (_())
        }
    def addAction(a: Action): Unit = {
        actions = a :: actions
        a()
    }
}
```

### What should the inverter do?

* Installing action on its input wire
* Action produces inverse of input signal on output wire
* Change must be effective after delay of InverterDelay units of simulated time

```{scala}
def inverter(input: Wire, output: Wire): Unit = {

    // Action to be applied to the input wire
    def invertAction(): Unit = {
        val inputSig = input.getSignal
        afterDelay(inverterDelay) { output setSignal !inputSig }
    }

    // When input wire changes, apply action
    input addAction invertAction
}
```

### AND gate

```{scala}
def andGate(in1: Wire, in2: Wire, output: Wire): Unit = {
    def andAction(): Unit = {
        val in1Sig = in1.getSignal
        val in2Sig = in2.getSignal
        afterDelay(AndGateDelay) { output setSignal (in1Sig & in2Sig) }
    }
    in1 addAction andAction
    in2 addAction andAction
}
```

### Exercise - Calculating in1Sig and in2Sig inline instead of as values?

Does not model OR gates faithfully.

# Lecture: Discrete event simulation - Implementation and test

## Simulation trait 

```{scala}
trait Simulation {
    type Action = () => Unit
    case class Event(time: Int, action: Action)
    private type Agenda = List[Event]
    private var agenda: Agenda = List()
}
```

## Handling time

Using private variable `curtime` containing current simulation time 
`private var curtime = 0`.

Can be accessed with getter function:

```{scala}
def currentTime: Int = curtime
```

Can be used:

```{scala}
Event(curtime + delay, () => block)
```

### Implementation of AfterDelay

```{r}
def afterDelay(delay: Int)(block: => Unit): Unit = {
    val item = Event(currentTime + delay, () => block)
    agenda = insert(agenda, item)
}

private def insert(ag: List[Event], item: Event): List[Event] = ag match {
    case first :: rest if first.time <= item.time =>
        first :: insert(rest, item)
    case _ =>
        item :: ag
}
```

### Event handling loop

```{scala}
private def loop(): Unit = agenda match {
    case first :: rest =>
        agenda = rest
        curtime = first.time
        first.action()
        loop()
    case Nil =>
}
```

### Run

```{r}
def run(): Unit = {
    afterDelay(0) {
        println("Simulation started!")
    }
    loop()
}
```

### Probing wires

```{scala}
def probe(name: String, wire: Wire): Unit = {
    def probeAction(): Unit = {
        println(s"$name $currentTime value = ${wire.getSignal}")
    }
    wire addAction probeAction
}
```

### Technology dependent parameters

```{scala}
trait Parameters {
    def InverterDelay = 2
    def AndGateDelay = 3
    def OrGateDelay = 5
}
```

# Run it!

```{scala}
object test {
    println("Worksheet")
    object sim extends Circuits with Parameters
    import sim._
    val in1, in2, sum, carry = new Wire
    halfAdder(in1, in2, sum, carry)
    probe("sum", sum)
    probe("carry", carry)

    in1 setSignal true
    run()

    in1 setSignal false
    run()
}
```

# Wrap

## Variant gate

Invertering inputs into AND gate, and finally inverting output.

What happens: a + b => !(!a && !b)
OR gate: a + b => !(!a || !b)

```{engine='scala'}
def orGateAlt(in1: Wire, in2: Wire, output: Wire): Unit = {
    val notIn1, notIn2, notOut = new Wire
    inverter(in1, notIn1)
    inverter(in2, notIn2)
    andGate(notIn1, notIn2, notOut)
    inverter(notOut, output)
}
```










