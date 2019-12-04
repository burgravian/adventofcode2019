import java.io.File
import java.lang.IllegalArgumentException

fun main() {
    val reader = File("files/day4.txt").bufferedReader()
    val a = reader.readLine().split("-").map { x -> x.toInt() }

    println(Day04().part1(a[0], a[1]))
    println(Day04().part2(a[0], a[1]))
}

class Day04 {
    fun part1(min: Int, max: Int): Int {
        var res = 0
        for (i in min..max) {
            if (checkNumber1(i)) {
                res++
            }
        }
        return res
    }

    private fun checkNumber1(n: Int): Boolean {
        val s: String = n.toString()
        var double = false
        for (i in 0 until s.length - 1) {
            if (s[i] > s[i + 1]) {
                return false
            }
            if (s[i] == s[i + 1]) {
                double = true
            }
        }
        return double
    }

    fun part2(min: Int, max: Int): Int {
        var res = 0
        for (i in min..max) {
            if (checkNumber2(i)) {
                res++
            }
        }
        return res
    }

    private fun checkNumber2(n: Int): Boolean {
        val s: String = n.toString()
        var double = false
        for (i in 0 until s.length - 1) {
            if (s[i] > s[i + 1]) {
                return false
            }
            if (s[i] == s[i + 1] && s.count { x -> x == s[i] } == 2) {
                double = true
            }
        }
        return double
    }
}
