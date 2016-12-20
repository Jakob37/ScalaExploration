object Solution {

    def main(args: Array[String]) {

        val alice_arr = scala.io.StdIn.readLine.split(" ").map(_.toInt)
        val bob_arr = scala.io.StdIn.readLine.split(" ").map(_.toInt)

        var alice_score = 0
        var bob_score = 0

        for (var i <- 0 to alice_arr.length - 1) {
            val alice_val = alice_arr(i)
            val bob_val = bob_arr(i)
        
            if (alice_val > bob_val) {
                alice_score += 1
            }
            else if (bob_val > alice_val) {
                bob_score += 1
            }
        }

        println(s"$alice_score $bob_score")
    }

}
