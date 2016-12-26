import scala.collection.mutable.Stack
import scala.collection.mutable.Queue
import scala.util.control._

class Solution {
    val queue = new scala.collection.mutable.Queue[Char]
    val stack = new scala.collection.mutable.Stack[Char]

    def pushCharacter(ch:Char):Unit = {
        stack.push(ch)
    }

    def enqueueCharacter(ch:Char):Unit = {
        queue.enqueue(ch)
    }

    def popCharacter():Char = {
        return stack.pop
    }

    def dequeueCharacter():Char = {
        return queue.dequeue
    }
}


