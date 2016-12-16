object Solution {

    def main(args: Array[String]) {
        val sc = new java.util.Scanner(System.in)
        val arr = Array.ofDim[Int](6,6)
        val size = 6

        for (arr_i <- 0 to size-1) {
            for (arr_j <- 0 to size-1) {
                arr(arr_i)(arr_j) = sc.nextInt()
            }
        }


        var best_sum = -Int.MaxValue
        for (corner_y <- 0 to size-3) {
            for (corner_x <- 0 to size-3) {
                val sum = get_hourglass_sum(corner_x, corner_y, arr)
                if (sum > best_sum) {
                    best_sum = sum
                }
            }
        }

        println(best_sum)
    }

    def get_hourglass_sum(corner_x:Int, corner_y:Int, arr:Array[Array[Int]]):Int = {

        val hourglass_pos = Array.ofDim[(Int,Int)](7)

        hourglass_pos(0) = (0,0)
        hourglass_pos(1) = (1,0)
        hourglass_pos(2) = (2,0)
        hourglass_pos(3) = (1,1)
        hourglass_pos(4) = (0,2)
        hourglass_pos(5) = (1,2)
        hourglass_pos(6) = (2,2)

        var sum = 0

        for (pos <- hourglass_pos) {
            val target_x = corner_x + pos._1
            val target_y = corner_y + pos._2

            // println(s"x: $target_x y: $target_y")
            // println("Adding: " + arr(target_y)(target_x))
            // println("Current sum: " + sum)
                
            sum += arr(target_y)(target_x)
        }

        return sum
    }

}

