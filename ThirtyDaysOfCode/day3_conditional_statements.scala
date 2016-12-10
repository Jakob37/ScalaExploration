object Solution {
    def main(args: Array[String]) {
    
        val input_int = scala.io.StdIn.readInt

        if (input_int % 2 == 1) {
            println("Weird")
        }
        else if (input_int >= 2 && input_int <= 5) {
            println("Not Weird")
        }
        else if (input_int > 5 && input_int <= 20) {
            println("Weird")
        }
        else if (input_int > 20) {
            println("Not Weird")
        }
    }
}
