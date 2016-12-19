object Solution {

    def main(args: Array[String]) {
        val number_ints = scala.io.StdIn.readInt
        val arr = scala.io.StdIn.readLine.split(" ").map(_.toInt)

        println(arr.sum)
    }
}
