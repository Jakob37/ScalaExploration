# Latency as effect

Computation can take time. `Future[T]`.

## Adventure game

```{scala}
trait Adventure {
    def collectCoins(): List[Coin]
    def buyTreasure(coins: List[Coin]): Treasure
}

val adventure = Adventure()
val coins = adventure.collectCoins()
val treasure = adventure.buyTreasure(coins)

trait Socket {
    def readFromMemory(): Array[Byte]
    def sendToEurope(packet: Array[Byte]): Array[Byte]
}

val socket = Socket()
val packet = socket.readFromMemory()
val confirmation = socket.sendToEurope(packet)

```

## Latency as effect: Using monad

The monad is called `Future[T]`. Handles both exception
and latency.

```{scala}
trait Future[T] {
    def onComplete(callback: Try[T] => Unit)
        (implicit executor: ExecutionContext): Unit
}
```

Callbacks needs to use pattern matching.

```{scala}
ts match {
    case Success(t) => onNext(t)
    case Failure(e) => onError(e)
}
```

Can take two callbacks - Success and fail continuation.

## Creating futures

```{scala}
object Future {
    def apply(body: => T): Future[T]
}
```

# Combinators on futures

## Starting point

```{scala}
val socket = Socket()
val packet: Future[Array[Byte]] =
    socket.readFromMemory()
packet onComplete {
    case Success(p) => {
        val confirmation: Future[Array[Byte]] =
            socket.sendToEurope(p)
    }
    case Failure(t) => ...
}
```

## Updated

```{scala}
val socket = Socket()
val packet: Future[Array[Byte]] =
    socket.readFromMemory()

val confirmation: Future[Array[Byte]] =
    packet.flatMap(p => socket.sendToEurope(p))

packet onComplete {
    case Success(p) => {
        val confirmation: Future[Array[Byte]] =
            socket.sendToEurope(p)
    }
    case Failure(t) => ...
}
```

## Send packets using futures robustly

```{scala}
def recover(f: PartialFunction[Throwable, T]): Future[T]
def recoverWith(f: PartialFunction[Throwable, Future[T]]): Future[T]
```

Send to Europe first, and if it fails, then send to US.

We want to try doing better recovery combinator that tries to run 'this' future,
and if it fails then returning fallback function, and if that fails, then returning
error.

```{scala}
def fallbackTo(that: =>Future[T]): Future[T] = {
    this recoverWith {
        case _ => that recoverWith { case _ => this }
    }
}
```

Asynchronous where possible, blocking where necessary using trait `Waitable` (but
don't use this in production code, only in small demos and little scripts).

Aha, when you are asynchonous, you should never ever block. The reason to introduce futures
no *not* end up with this stuff.

## Composing futures

Another recombinatory: Retry. Will retry certain number of times before blocking.

Implementaiton:

```{scala}
def retry(noTimes: Int)(block: => Future[T]): Future[T] = {
    if (noTimes == 0) {
        Future.failed(new Exception("Sorry"))
    }
    else {
        block fallbackTo {
            retry(noTimes - 1){ block }
        }
    }
}
```

## Implementation of FlatMap on Future

```{scala}
trait Future[T] {
    def onComplete(callback: Try[T] => Unit) = ...
    def flatMap[S](f: T => Future[S]): Future[S] = ???
}

trait Future[T] {
    self => def flatMap[S](f: T => Future[S]): Future[S] =
        new Future[S] {
            def onCOmplete(callback: Try[S] => Unit): Unit =
                self onComplete {
                    case Success(x) => f(x) ...
                    case Failure(e) => ...
                }
        }
}
```

## Recursive macho programming

Using `foldRight` and `foldLeft`. 

```{r}
List(a,b,c).foldRight(e)(f) = f(a, f(b, f(c, e)))

List(a,b,c).foldLeft(e)(f) = f(f(f(e, a), b), c)
```

Create list from 'number of times'.

























