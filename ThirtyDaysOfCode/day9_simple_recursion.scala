object Solution {
    
    def main(args: Array[String]) {
        val input_val = scala.io.StdIn.readInt
        val factorial_result = get_recursive_value(input_val)
        println(factorial_result)
    }

    def get_recursive_value(value:Int):Int = {
        if (value > 1) {
            return get_recursive_value(value - 1) * value
        }
        else {
            return value
        }
    }
}
