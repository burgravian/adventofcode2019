import java.io.File
import java.lang.IllegalArgumentException
import java.util.*

fun main() {
    val vals =
        File("files/day8.txt").bufferedReader().readLine().toCharArray().toList().map { x: Char -> x.toInt() - 48 }
            .toList()
    println(Day08().part1(vals, 25, 6))
    Day08().part2(vals, 25, 6)
}

class Day08 {
    fun part1(list: List<Int>, width: Int, height: Int): Int {
        val layers: MutableList<MutableList<Int>> = mutableListOf()
        for ((i, c) in list.withIndex()) {
            if (i / (width * height) >= layers.size) {
                layers.add(mutableListOf())
            }
            layers[i / (width * height)].add(c)
        }
        var zeroes = Integer.MAX_VALUE
        var ones = 0
        var twos = 0
        for (l in layers) {
            if (l.count { x -> x == 0 } < zeroes) {
                zeroes = l.count { x -> x == 0 }
                ones = l.count { x -> x == 1 }
                twos = l.count { x -> x == 2 }
            }
        }
        return ones * twos
    }

    fun part2(list: List<Int>, width: Int, height: Int) {
        val layers: MutableList<MutableList<Int>> = mutableListOf()
        for ((i, c) in list.withIndex()) {
            if (i / (width * height) >= layers.size) {
                layers.add(mutableListOf())
            }
            layers[i / (width * height)].add(c)
        }
        val imageMutable: MutableList<Int> = mutableListOf()
        for (i in 0..width * height) {
            imageMutable.add(2)
        }
        var image: List<Int> = imageMutable.toList()
        for (l in layers.reversed()) {
            image = image.zip(l).map { x -> if (x.second != 2) x.second else x.first }
        }

        for ((i, p) in image.map { x -> if (x == 1) "##" else "  " }.withIndex()) {
            if (i % width == 0) {
                println()
            }
            print(p)
        }
    }
}