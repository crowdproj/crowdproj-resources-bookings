

fun main() {
    val count = readln().toInt()
    var list = mutableListOf<Int>()
    repeat(count) {
        list.add(readln().toInt())
    }
    if (count in 0..1) {
        println(list.toString())
    } else {
        var temp: Int
        val countSteps = readln().toInt() % count
        repeat(countSteps) {
            temp = list.last()
            list.removeLast()
            list.add(0, temp)
        }
        println(list.joinToString(" "))
    }
}
