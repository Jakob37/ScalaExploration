class Person {

    var age: Int = 0

    def this(initialAge:Int) = {
        this()
        
        if (initialAge >= 0) {
            this.age = initialAge
        }
        else {
            println("Age is not valid, setting age to 0..")
            this.age = 0
        }
    }

    def amIOld(): Unit = {
        if (this.age < 13) {
            println("You are young..")
        }
        else if (this.age < 18) {
            println("You are a teenager..")
        }
        else {
            println("You are old..")
        }
    }


    def yearPasses(): Unit = {
        this.age += 1 
    }
}
