import java.io.File
import java.util.*
import kotlin.streams.toList

fun main() {
    val reader = File("files/day6.txt").bufferedReader()
    val list = reader.lines().toList().map { x -> Pair(x.split(")")[0], x.split(")")[1]) }

    println(Day06().part1(list))
    println(Day06().part2(list))
}

class Day06 {
    fun part1(vals: List<Pair<String, String>>): Int {
        return countOrbits(makeGraph(vals), "COM")
    }

    fun part2(vals: List<Pair<String, String>>): Int {
        return countOrbitsBetween(makeGraph(vals, true), "YOU")
    }

    private fun countOrbits(g: Map<String, MutableList<String>>, currentNode: String, depth: Int = 0): Int {
        var res = depth
        if (g.containsKey(currentNode)) {
            for (v in g.getValue(currentNode)) {
                res += countOrbits(g, v, depth + 1)
            }
        }
        return res
    }

    private fun countOrbitsBetween(
        g: Map<String, MutableList<String>>,
        currentNode: String,
        vis: MutableSet<String> = mutableSetOf(),
        depth: Int = 0
    ): Int {
        if (currentNode == "SAN") {
            return depth - 2
        }
        vis.add(currentNode)
        if (g.containsKey(currentNode)) {
            for (v in g.getValue(currentNode)) {
                if (!vis.contains(v)) {
                    val a = countOrbitsBetween(g, v, vis, depth + 1)
                    if (a != -1) {
                        return a
                    }
                }
            }
        }
        return -1
    }

    private fun makeGraph(vals: List<Pair<String, String>>, biDir: Boolean = false): Map<String, MutableList<String>> {
        val map: MutableMap<String, MutableList<String>> = mutableMapOf()
        for (v in vals) {
            if (map.containsKey(v.first)) {
                map[v.first]!!.add(v.second)
            } else {
                map[v.first] = mutableListOf(v.second)
            }
            if (biDir) {
                if (map.containsKey(v.second)) {
                    map[v.second]!!.add(v.first)
                } else {
                    map[v.second] = mutableListOf(v.first)
                }
            }
        }
        return map
    }
}