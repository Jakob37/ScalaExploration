object Solution {
    
    def main(args: Array[String]) {
        val init_int = scala.io.StdIn.readInt

        for (i <- 1 to 10) {
            val prod = i * init_int
            println(s"$init_int x $i = $prod")
        }
    }
}
