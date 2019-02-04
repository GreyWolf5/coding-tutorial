import io.kotlintest.matchers.collections.shouldContain
import io.kotlintest.matchers.numerics.shouldBeGreaterThanOrEqual
import io.kotlintest.matchers.numerics.shouldBeInRange
import io.kotlintest.matchers.numerics.shouldBeLessThan
import io.kotlintest.matchers.numerics.shouldNotBeInRange
import io.kotlintest.shouldBe
import io.kotlintest.shouldHave
import io.kotlintest.shouldNot
import io.kotlintest.shouldNotBe
import io.kotlintest.specs.FeatureSpec

class BasicsFeatureSpec : FeatureSpec({
    feature("functions") {
        scenario("sum1 and sum2 works the same") {
            val a = 1
            val b = 2
            sum1(a, b) shouldBe 3
            sum1(a, b) shouldBeLessThan 4
            sum1(0, 3) shouldBe 3
            sum1(-1, 1) shouldBe 0
            sum1(3,8) shouldBeGreaterThanOrEqual 10
            sum1(7,8) shouldBeInRange 10..20
            sum1(2,6) shouldNotBeInRange 2..6
            // Add greater less checks
        }
    }

    feature("variables") {

        val readOnly = 11
        var reassignable = 3
//            scenario("val can not be reassigned") {
//                readOnly = 12
//            }

        scenario("can be reassigned") {
            reassignable = 6
            reassignable shouldBe 6
        }
    }

    feature("strings") {
        val toge = "toge"
        val ther = "ther"
        val together = "together"

        scenario("concatenation works") {
            toge + ther shouldBe together
        }

        scenario("string interpolation works") {
            "$toge$ther" shouldBe together
        }

        scenario("is not empty") {
            together.isNotBlank() shouldBe true
        }
    }

    feature("conditional expressions") {
        val max = 100
        val min = 0

        scenario("returns max") {
            maxOf(min, max) shouldBe max
        }

        scenario("") {
            minOf(1, 2)
            minOf(7,6,20,15,16,6,4) shouldBe 4
        }
    }

    // Write minOff function

    feature("when expression") {
        describe(1) shouldBe "One"
        describe("hello") shouldBe "Unknown"
        describe(10L) shouldBe "Long"
        describe(10) shouldNotBe  "Long"
        describe("abc") shouldNotBe  null

        // Add other checks
    }

    feature("collections") {
        val fruits = arrayListOf("Apple", "Orange", "Grapes", "Cherry", "Orange", "oRanGe", "APPLE")
        val cars = arrayListOf<String>()
        val cats = arrayListOf("Sharky" , "Snow", "Bella", "Sunny", "Snоw", "Барсик") //Add cyrillic symbol

        scenario("") {
            fruits.count() shouldBe 7
            fruits shouldContain "Apple"
            count(fruits) shouldBe 7

            print(fruits)
            print(listToMap(fruits))
            println()
            print(cars)
            print(listToMap(cars))
            println()
            print(cats)
            print(listToMap(cats))
            println()
        }
    }


})

fun sum1(a: Int, b: Int): Int {
    return a + b
}

fun sum2(a: Int, b: Int) = a + b

fun maxOf(a: Int, b: Int) = if (a > b) a else b

fun maxOf(a: Int, b: Int, c: Int) : Int {
    return maxOf((maxOf(a, b)), c)
}

/*
fun minOf(list: List<Int> ) : Int{
    var min: Int = list[0]
    for (i in list){
        if (i<min) min=i
    }
    return min
}
*/

fun minOf(vararg number: Int): Int {
    var min = number[0]
    number.forEach { if (it<min) min=it  }
    return min
}

fun minOf(a: Double, b: Double): Any {
    if (a < b) return a
    return Unit
}

fun describe(obj: Any): String =
        when (obj) {
            1 -> "One"
            "Hello" -> "Greeting"
            is Long -> "Long"
            !is String -> "Not a string"
            else -> "Unknown"
        }

fun count(list: ArrayList<String>): Int {
    var counter = 0
    for (i in list) {
        counter += 1
    }
    return counter
}

fun listToMap(list:List<String>): Map<String,Int>{
    var Result: MutableMap<String,Int> = mutableMapOf()
    var items: MutableList<String> = mutableListOf<String>()
    list.forEach { items.add(it.toLowerCase()) }
    if (items.isNotEmpty()){
        items.forEach {
            if(Result.containsKey(it)) {
                var a:Int = Result.getValue(it)
                Result.set(it,++a)
            } else{
                Result.put(it,1)
            }
        }
    }
    return  Result
}
fun print (list:List<String>){
    list.forEach { println(it) }
}

fun print (map: Map<String,Int>){
    map.forEach{
        k, v ->
        println("$k = $v")
    }
}