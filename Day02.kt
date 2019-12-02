import java.io.BufferedReader
import java.io.File
import kotlin.streams.toList

fun main() {
    println(Day02().part1())
    println(Day02().part2())
}

class Day02 {
    fun part1(noun: Int = 12, verb: Int = 2): Int {
        val input = File("files/day2.txt").bufferedReader().readLine()
        val vals = input.split(",").map { x: String -> x.toInt() }.toMutableList()
        vals[1] = noun
        vals[2] = verb
        for (i in 0..vals.size step 4) {
            when {
                vals[i] == 1 -> vals[vals[i + 3]] = vals[vals[i + 1]] + vals[vals[i + 2]]
                vals[i] == 2 -> vals[vals[i + 3]] = vals[vals[i + 1]] * vals[vals[i + 2]]
                vals[i] == 99 -> return vals[0]
                else -> return -1
            }
        }


        return -1
    }

    fun part2(): Int {
        for (i in 0..99) {
            for (j in 0..99) {
                if (part1(i, j) == 19690720) {
                    return 100 * i + j
                }
            }
        }
        return -1
    }
}