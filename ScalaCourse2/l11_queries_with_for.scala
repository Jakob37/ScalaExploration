// Mini-database

val books: List[Book] = List(
    Book(title="a title", author="the author")
)

for (b <- books; a <- b.authors if a startswith "Bird,")
    yield b.title


// Find authors who have written at least two books

{for {
    b1 <- books
    b2 <- books
    if b1.title < b2.title  # Only get one pair combination
        a1 <- b1.authors
        a2 <- b2.authors
        if a1 == a2
} yield a1}.distinct  # Like 'unique'? Alternative: Set of books
