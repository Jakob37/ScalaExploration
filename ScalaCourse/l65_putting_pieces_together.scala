
// Phone keys have mnemonics assigned to them

val in = Source.fromURL("url")

val words = in.getLines.toList filter (word => word forall (chr => chr.isLetter))
val mnem = Map('2' -> "ABC", ...)

// DO DRY - val charCode: Map[Char, Char] = Map('A' -> '2', 'B' -> '2'...)
val charCode: Map[Char, Char] =
    for ((digit, str) <- mnem; ltr <- str) yield ltr -> digit

def wordCode(word: String): String =
    word.toUpperCase map charCode

wordCode("JAVA")


val wordsForNum: Map[String, Seq[String]] =
    words groupBy wordCode withDefaultValue Seq()


def encode(number: String): Set[List[String]] =
    if (number.isEmpty) Set(List())
    else {
        for {
            split <- 1 to number.length
            word <- wordsForNum(number take split)
            rest <- encode(number drop split)
        } yield word :: rest
    }.toSet

encode("20938390")

def translate(number: String): Set[String] = 
    encode(number) map (_ mkString " ")




