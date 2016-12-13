object Solution {
    
    def main(args: Array[String]) {
        val lines = scala.io.StdIn.readInt

        val phone_book = scala.collection.mutable.Map[String,Int]().withDefaultValue(-1)

        for (i <- 1 to lines) {
            val phone_entry = scala.io.StdIn.readLine
            val name_number_arr = phone_entry.split(" ")
            phone_book(name_number_arr(0)) = name_number_arr(1).toInt
        }

        for (i <- 1 to lines) {

            val query = scala.io.StdIn.readLine

            if (!query.isEmpty()) {
                val result = phone_book(query)

                if (result == -1) {
                    println("Not found")
                }
                else {
                    println(s"$query=$result")
                }
            }
        }
    }
}
