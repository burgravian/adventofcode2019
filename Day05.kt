import java.io.File

fun main() {
    println(Day05().part1(1))
    println(Day05().part2(5))
}

class Day05 {
    fun part1(input: Int): Int {
        val vals =
            File("files/day5.txt").bufferedReader().readLine().split(",").map { x: String -> x.toInt() }.toMutableList()
        var i = 0
        while (i < vals.size) {
            var fullcode = vals[i].toString()
            while (fullcode.length != 5) {
                fullcode = "0$fullcode"
            }
            val opcode = fullcode.substring(fullcode.length - 2).toInt()
            when (opcode) {
                1 -> {
                    val param1 = if (fullcode[2] == '1') vals[i + 1] else vals[vals[i + 1]]
                    val param2 = if (fullcode[1] == '1') vals[i + 2] else vals[vals[i + 2]]
                    vals[vals[i + 3]] = param1 + param2
                    i += 4
                }
                2 -> {
                    val param1 = if (fullcode[2] == '1') vals[i + 1] else vals[vals[i + 1]]
                    val param2 = if (fullcode[1] == '1') vals[i + 2] else vals[vals[i + 2]]
                    vals[vals[i + 3]] = param1 * param2
                    i += 4
                }
                3 -> {
                    vals[vals[i + 1]] = input
                    i += 2
                }
                4 -> {
                    val param1 = if (fullcode[2] == '1') vals[i + 1] else vals[vals[i + 1]]
                    if (param1 != 0) {
                        return param1
                    }
                    i += 2
                }
                99 -> {
                    return vals[0]
                }
                else -> return -1
            }
        }

        return -1
    }

    fun part2(input: Int): Int {
        val vals =
            File("files/day5.txt").bufferedReader().readLine().split(",").map { x: String -> x.toInt() }.toMutableList()
        var i = 0
        while (i < vals.size) {
            var fullcode = vals[i].toString()
            while (fullcode.length != 5) {
                fullcode = "0$fullcode"
            }
            val opcode = fullcode.substring(fullcode.length - 2).toInt()
            when (opcode) {
                1 -> {
                    val param1 = if (fullcode[2] == '1') vals[i + 1] else vals[vals[i + 1]]
                    val param2 = if (fullcode[1] == '1') vals[i + 2] else vals[vals[i + 2]]
                    vals[vals[i + 3]] = param1 + param2
                    i += 4
                }
                2 -> {
                    val param1 = if (fullcode[2] == '1') vals[i + 1] else vals[vals[i + 1]]
                    val param2 = if (fullcode[1] == '1') vals[i + 2] else vals[vals[i + 2]]
                    vals[vals[i + 3]] = param1 * param2
                    i += 4
                }
                3 -> {
                    vals[vals[i + 1]] = input
                    i += 2
                }
                4 -> {
                    return if (fullcode[2] == '1') vals[i + 1] else vals[vals[i + 1]]
                }
                5 -> {
                    val param1 = if (fullcode[2] == '1') vals[i + 1] else vals[vals[i + 1]]
                    val param2 = if (fullcode[1] == '1') vals[i + 2] else vals[vals[i + 2]]
                    if (param1 != 0) {
                        i = param2
                    } else {
                        i += 3
                    }
                }
                6 -> {
                    val param1 = if (fullcode[2] == '1') vals[i + 1] else vals[vals[i + 1]]
                    val param2 = if (fullcode[1] == '1') vals[i + 2] else vals[vals[i + 2]]
                    if (param1 == 0) {
                        i = param2
                    } else {
                        i += 3
                    }
                }
                7 -> {
                    val param1 = if (fullcode[2] == '1') vals[i + 1] else vals[vals[i + 1]]
                    val param2 = if (fullcode[1] == '1') vals[i + 2] else vals[vals[i + 2]]
                    if (param1 < param2) {
                        vals[vals[i + 3]] = 1
                    } else {
                        vals[vals[i + 3]] = 0
                    }
                    i += 4
                }
                8 -> {
                    val param1 = if (fullcode[2] == '1') vals[i + 1] else vals[vals[i + 1]]
                    val param2 = if (fullcode[1] == '1') vals[i + 2] else vals[vals[i + 2]]
                    if (param1 == param2) {
                        vals[vals[i + 3]] = 1
                    } else {
                        vals[vals[i + 3]] = 0
                    }
                    i += 4
                }
                99 -> {
                    return vals[0]
                }
                else -> return -1
            }
        }

        return -1
    }
}