import scala.util.control.Exception._


class Calculator {      
    def power(n:Integer, p:Integer): Int = {         
        if (n > 0 && p > 0) {             
            return scala.math.pow(n.toDouble, p.toDouble).toInt
        }
        throw new Exception("n and p should be non-negative")     
    }  
}

object Solution {      def main(args: Array[String]) {         var
myCalculator=new Calculator();         var T=scala.io.StdIn.readLine().toInt
while(T>0){             val Array(n,p) = scala.io.StdIn.readLine().split("
").map(_.toInt);             try{                   var
ans=myCalculator.power(n,p);                   println(ans);             }
catch{                 case e: Exception => {
println(e.getMessage());                 }             }             T-=1;
}     } }


