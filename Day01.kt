import java.io.BufferedReader
import java.io.File

fun main() {
    println(Day01().part1())
    println(Day01().part2())
}

class Day01 {
    fun part1(): Int {
        val reader: BufferedReader = File("files/day1.txt").bufferedReader()
        return reader.lines()
            .map { x: String -> x.toInt() / 3 - 2 }
            .reduce { acc: Int, x: Int -> x + acc }
            .get()
    }

    fun part2(): Int {
        val reader: BufferedReader = File("files/day1.txt").bufferedReader()
        return reader.lines()
            .map { x: String -> x.toInt() }
            .map(::computeFuel)
            .reduce { acc: Int, x: Int -> x + acc }
            .get()
    }

    private fun computeFuel(x: Int): Int {
        if(x / 3 - 2 <= 0){
            return 0
        }
        val value = x / 3 - 2
        return value + computeFuel(value)
    }
}