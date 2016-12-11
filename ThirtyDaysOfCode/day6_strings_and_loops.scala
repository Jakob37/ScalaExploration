object Solution {

    def main(args: Array[String]) {
        
        val lines = scala.io.StdIn.readInt
        
        for (i <- 1 to lines) {
            val line = scala.io.StdIn.readLine
            
            var evens = ""
            var odds = ""
                
            for (j <- 0 to line.length - 1) {
                if (j % 2 == 0) {
                    evens += line(j)
                }
                else {
                    odds += line(j)
                }
            }
            
            println(s"$evens $odds")
        }
        
    }
}
