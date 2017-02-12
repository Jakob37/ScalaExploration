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


















