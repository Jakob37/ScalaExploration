object Solution {
	
	def main(args: Array[String]) {
		val input_val = scala.io.StdIn.readInt
		val bin_str = input_val.toBinaryString
		
		var current_stretch = 0
		var longest_stretch = 0
		for (i <- 0 to bin_str.length - 1) {
			if (bin_str(i) == '1') {
				current_stretch += 1
			}
			else {
				if (current_stretch > longest_stretch) {
					longest_stretch = current_stretch
				}
				current_stretch = 0
			}
		}

		if (current_stretch > longest_stretch) {
			longest_stretch = current_stretch
		}

		println(longest_stretch)
	}

}
