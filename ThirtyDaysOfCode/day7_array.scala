object Solution {

    def main(args: Array[String]) {

        val sc = new java.util.Scanner(System.in)
        var n = sc.nextInt()
        var arr = new Array[Int](n)

        for (arr_i <- 0 to n-1) {
            arr(arr_i) = sc.nextInt
        }

        var out_string = ""

        for (i <- 0 to arr.length - 1) {
            val inverse_i = arr.length - i -1
            out_string += arr(inverse_i)
            out_string += " "
        }

        println(out_string)
    }
}
