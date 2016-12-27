object Solution {

    def main(args: Array[String]) {
        val sc = new java.util.Scanner(System.in)
        var n = sc.nextInt()
        var a = new Array[Int](n)
        for (a_i <- 0 to n-1) {
            a(a_i) = sc.nextInt()
        }

        var totalSwaps = 0
        for (i <- 0 to n - 2) {
            
            var numberOfSwaps = 0
            for (j <- 0 to n - 2) {
                if (a(j) > a(j + 1)) {
                    swap(a, j, j+1)
                    numberOfSwaps += 1
                }
            }
            totalSwaps += numberOfSwaps
        }

        println(s"Array is sorted in $totalSwaps swaps.")
        println(s"First Element: ${a(0)}")
        println(s"Last Element: ${a.last}")
    }

    def swap(a:Array[Int], index_1:Int, index_2:Int):Unit = {
        
        val val1 = a(index_1)
        val val2 = a(index_2)

        a(index_1) = val2
        a(index_2) = val1
    }
}
