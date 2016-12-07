object Solution {
    def main(args: Array[String]) {
        
        val meal_cost = scala.io.StdIn.readLine
        val tip_percent = scala.io.StdIn.readLine
        val tax_percent = scala.io.StdIn.readLine

        val tip = meal_cost.toFloat * tip_percent.toFloat / 100
        val tax = meal_cost.toFloat * tax_percent.toFloat / 100
        val total_cost = meal_cost.toFloat + tip + tax

        println(s"The total meal cost is ${total_cost.toInt} dollars.")
    }
}
