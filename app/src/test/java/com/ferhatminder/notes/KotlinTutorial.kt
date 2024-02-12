package com.ferhatminder.notes

import org.junit.Test

class KotlinTutorial {
    @Test
    fun hello_world() {
        println("Hello, world!")
    }

    @Test
    fun variables() {
        //Variables
        val popcorn = 5    // There are 5 boxes of popcorn
        val hotdog = 7     // There are 7 hotdogs
        var customers = 10 // There are 10 customers in the queue

        // Some customers leave the queue
        customers = 8

        //String templates
        println("There are $customers customers")
    }

    @Test
    fun basicTypes() {
        // Some customers leave the queue
        var customers = 8

        customers = customers + 3 // Example of addition: 11
        customers += 7            // Example of addition: 18
        customers -= 3            // Example of subtraction: 15
        customers *= 2            // Example of multiplication: 30
        customers /= 3            // Example of division: 10

        println(customers) // 10

        // Variable declared without initialization
        val d: Int
        // Variable initialized
        d = 3

        // Variable explicitly typed and initialized
        val e: String = "hello"

        // Variables can be read because they have been initialized
        println(d) // 3
        println(e) // hello
    }

    @Test
    fun collections() {
        // Read only list
        val readOnlyShapes = listOf("triangle", "square", "circle")
        println(readOnlyShapes)
        // [triangle, square, circle]

        // Mutable list with explicit type declaration
        val shapes: MutableList<String> = mutableListOf("triangle", "square", "circle")
        println(shapes)
        // [triangle, square, circle]

        // Casting
        val shapesLocked: List<String> = shapes

        println("The first item in the list is: ${readOnlyShapes[0]}")

        println("The first item in the list is: ${readOnlyShapes.first()}")

        println("This list has ${readOnlyShapes.count()} items")

        println("circle" in readOnlyShapes)


        shapes.add("pentagon")
        println(shapes)
        shapes.remove("pentagon")
        println(shapes)

        // SETS
        val fruit: MutableSet<String> = mutableSetOf("apple", "banana", "cherry", "cherry")
        val fruitLocked: Set<String> = fruit

        val readOnlyFruit = setOf("apple", "banana", "cherry", "cherry")
        println("This set has ${readOnlyFruit.count()} items")
        // This set has 3 items
        //As sets are unordered, you can't access an item at a particular index.

        fruit.add("dragonfruit")    // Add "dragonfruit" to the set
        println(fruit)              // [apple, banana, cherry, dragonfruit]

        fruit.remove("dragonfruit") // Remove "dragonfruit" from the set
        println(fruit)              // [apple, banana, cherry]

        //MAP
        // Read-only map
        val readOnlyJuiceMenu = mapOf("apple" to 100, "kiwi" to 190, "orange" to 100)
        println(readOnlyJuiceMenu)
        // {apple=100, kiwi=190, orange=100}

        // Mutable map with explicit type declaration
        val juiceMenu: MutableMap<String, Int> =
            mutableMapOf("apple" to 100, "kiwi" to 190, "orange" to 100)
        println(juiceMenu)
        // {apple=100, kiwi=190, orange=100}

        // Casting
        val juiceMenuLocked: Map<String, Int> = juiceMenu

        println("The value of apple juice is: ${readOnlyJuiceMenu["apple"]}")
        // The value of apple juice is: 100

        println("This map has ${readOnlyJuiceMenu.count()} key-value pairs")
        // This map has 3 key-value pairs
    }

    @Test
    fun controlFlow() {
        val d: Int
        val check = true
        if (check) {
            d = 1
        } else {
            d = 2
        }
        println(d)
        // 1


        val a = 1
        val b = 2

        println(if (a > b) a else b) // Returns a value: 2

        val obj = "Hello"

        // When Statement
        when (obj) {
            // Checks whether obj equals to "1"
            "1" -> println("One")
            // Checks whether obj equals to "Hello"
            "Hello" -> println("Greeting")
            // Default statement
            else -> println("Unknown")
        }
        // Greeting

        val obj2 = "Hello"
        val result = when (obj2) {
            // If obj equals "1", sets result to "one"
            "1" -> "One"
            // If obj equals "Hello", sets result to "Greeting"
            "Hello" -> "Greeting"
            // Sets result to "Unknown" if no previous condition is satisfied
            else -> "Unknown"
        }
        println(result)
        // Greeting

        // When Expression
        val temp = 18
        val description = when {
            // If temp < 0 is true, sets description to "very cold"
            temp < 0 -> "very cold"
            // If temp < 10 is true, sets description to "a bit cold"
            temp < 10 -> "a bit cold"
            // If temp < 20 is true, sets description to "warm"
            temp < 20 -> "warm"
            // Sets description to "hot" if no previous condition is satisfied
            else -> "hot"
        }
        println(description)
        // warm
    }

    @OptIn(ExperimentalStdlibApi::class)
    @Test
    fun ranges() {
        println(1..4)
        println(1..<4)
        println(4 downTo 1)
        println(1..5 step 2)
        println('a'..'d')
        println('z' downTo 's' step 2)
    }

    @Test
    fun loops() {
        for (number in 1..5) {
            // number is the iterator and 1..5 is the range
            print(number)
        }

        val cakes = listOf("carrot", "cheese", "chocolate")

        for (cake in cakes) {
            println("Yummy, it's a $cake cake!")
        }


        var cakesEaten = 0
        while (cakesEaten < 3) {
            println("Eat a cake")
            cakesEaten++
        }

        cakesEaten = 0
        var cakesBaked = 0
        while (cakesEaten < 3) {
            println("Eat a cake")
            cakesEaten++
        }
        do {
            println("Bake a cake")
            cakesBaked++
        } while (cakesBaked < cakesEaten)
    }

    @Test
    fun functions() {
        fun sum(x: Int, y: Int) = x + y

        println(sum(2, 2))

        // lambda expression
        val uppercase = { string: String -> string.uppercase() }
        println(uppercase("hello"))

        // The initial value is zero.
        // The operation sums the initial value with every item in the list cumulatively.
        println(listOf(1, 2, 3).fold(0, { sum, item -> sum + item })) // 6
    }

    @Test
    fun classes() {
        class Contact(val id: Int, var email: String = "example@gmail.com") {
            val category: String = ""
            fun printId() {
                println(id)
            }
        }

        val contact = Contact(1)
        println("Contact email address is: ${contact.email}")

        contact.printId()


        // Data Class
        // toString, equals, copy
        data class User(val name: String, val id: Int)

        val user = User("John", 2)
        println(user)

        val secondUser = User("Alex", 2)
        println(user == secondUser)

        println(user.copy(name = "Max"))
    }

    @Test
    fun nullSafety() {
        // neverNull has String type
        var neverNull: String = "This can't be null"

        // Throws a compiler error
        //neverNull = null

        // nullable has nullable String type
        var nullable: String? = "You can keep a null here"
        nullable = null

        // By default, null values aren't accepted
        var inferredNonNull = "The compiler assumes non-nullable"

        // Throws a compiler error
        //inferredNonNull = null

        // notNull doesn't accept null values
        fun strLength(notNull: String): Int {
            return notNull.length
        }

        println(strLength(neverNull)) // 18
        //println(strLength(nullable))  // Throws a compiler error

        fun strLengthNullable(str: String?): Int {
            return str?.length ?: 0
        }

        println(strLengthNullable(nullable))

        fun String?.extensionStrLen(): Int? = this?.length

        println(nullable.extensionStrLen())
    }


}