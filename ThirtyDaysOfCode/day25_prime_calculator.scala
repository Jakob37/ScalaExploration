object Solution {

    def main(args: Array[String]) {
    
        val nbr_primes = scala.io.StdIn.readInt

        for (i <- 0 to nbr_primes - 1) {
            val current_number = scala.io.StdIn.readInt
            val is_prime = checkIfPrime(current_number)
            if (is_prime) {
                println("Prime")
            }
            else {
                println("Not prime")
            }
        }

    }

    def checkIfPrime(number:Integer):Boolean = {
        
        for (i <- 2 to (math.sqrt(number.toDouble)).toInt) {
            if (number % i == 0) {
                return false
            }
        }
        return true
    }

}



