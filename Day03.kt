import java.io.File
import java.lang.IllegalArgumentException

fun main() {
    val reader = File("files/day3.txt").bufferedReader()
    val a = reader.readLine().split(",")
    val b = reader.readLine().split(",")

    println(Day03().part1(a, b))
    println(Day03().part2(a, b))
}

class Day03 {
    fun part1(aas: List<String>, bbs: List<String>): Int {
        return makeSet(aas)
            .intersect(makeSet(bbs))
            .asSequence()
            .minus(Pair(0, 0))
            .toList()
            .map { x -> Math.abs(x.first) + Math.abs(x.second) }
            .sortedBy { x -> x }
            .first()
    }

    fun part2(aas: List<String>, bbs: List<String>): Int {
        val intersectionsSet = makeSet(aas)
            .intersect(makeSet(bbs))
            .asSequence()
            .minus(Pair(0, 0))

        val stepMapA = makeSetWithSteps(aas).filter { x -> x.first in intersectionsSet }.toMap()
        val stepMapB = makeSetWithSteps(bbs).filter { x -> x.first in intersectionsSet }.toMap()

        val res = intersectionsSet.map { x -> stepMapA.getOrDefault(x, 0) + stepMapB.getOrDefault(x, 0) }
        return res.sorted().first()
    }

    private fun makeSet(xs: List<String>): Set<Pair<Int, Int>> {
        val res: MutableSet<Pair<Int, Int>> = mutableSetOf()
        var current = Pair(0, 0)
        for (x in xs) {
            val dir = x[0]
            val value = x.substring(1).toInt()
            when (dir) {
                'R' -> {
                    for (i in 0..value) {
                        res.add(Pair(current.first + i, current.second))
                    }
                    current = Pair(current.first + value, current.second)
                }
                'L' -> {
                    for (i in 0..value) {
                        res.add(Pair(current.first - i, current.second))
                    }
                    current = Pair(current.first - value, current.second)
                }
                'U' -> {
                    for (i in 0..value) {
                        res.add(Pair(current.first, current.second + i))
                    }
                    current = Pair(current.first, current.second + value)
                }
                'D' -> {
                    for (i in 0..value) {
                        res.add(Pair(current.first, current.second - i))
                    }
                    current = Pair(current.first, current.second - value)
                }
                else -> throw IllegalArgumentException("no.")
            }
        }
        return res
    }

    private fun makeSetWithSteps(xs: List<String>): Set<Pair<Pair<Int, Int>, Int>> {
        val res: MutableSet<Pair<Pair<Int, Int>, Int>> = mutableSetOf()
        var current = Pair(0, 0)
        var c = 0
        for (x in xs) {
            c--
            val dir = x[0]
            val value = x.substring(1).toInt()
            when (dir) {
                'R' -> {
                    for (i in 0..value) {
                        c++
                        res.add(Pair(Pair(current.first + i, current.second), c))
                    }
                    current = Pair(current.first + value, current.second)
                }
                'L' -> {
                    for (i in 0..value) {
                        c++
                        res.add(Pair(Pair(current.first - i, current.second), c))
                    }
                    current = Pair(current.first - value, current.second)
                }
                'U' -> {
                    for (i in 0..value) {
                        c++
                        res.add(Pair(Pair(current.first, current.second + i), c))
                    }
                    current = Pair(current.first, current.second + value)
                }
                'D' -> {
                    for (i in 0..value) {
                        c++
                        res.add(Pair(Pair(current.first, current.second - i), c))
                    }
                    current = Pair(current.first, current.second - value)
                }
                else -> throw IllegalArgumentException("no.")
            }
        }
        return res
    }
}
