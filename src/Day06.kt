object Day06 {
    fun parse(input: String): Pair<List<Int>, List<Int>> {
        val (times0, dists0) = input.lines()
        val times = intsOf(times0).toList()
        val dists = intsOf(dists0).toList()
        return Pair(times, dists)
    }

    fun part1(input: String): Int {
        val (times, dists) = parse(input)
        fun numWaysOf(i: Int) = (0..times[i]).fold(0) { numWays, holdTime ->
            if (holdTime * (times[i] - holdTime) > dists[i]) numWays + 1 else numWays
        }
        return times.indices.map { numWaysOf(it) }.reduce { x, y -> x * y }
    }

    fun part2(input: String): Int {
        val (times, dists) = parse(input)
        val time = times.joinToString("").toLong()
        val dist = dists.joinToString("").toLong()
        return (0..time).fold(0) { numWays, holdTime ->
            if (holdTime * (time - holdTime) > dist) numWays + 1 else numWays
        }
    }
}

fun main() {
    with(Day06) {
        val input0 = readInput(javaClass.simpleName)
        val input1 = """
            Time:      7  15   30
            Distance:  9  40  200
        """.trimIndent()
        val input1expected1 = """
            288
        """.trimIndent()
        val input0expected1 = """
            227850
        """.trimIndent()
        val input1expected2 = """
            71503
        """.trimIndent()
        val input0expected2 = """
            42948149
        """.trimIndent()
        part1(input1).toString().also {
            println(it)
            check(input1expected1 == it)
        }
        part1(input0).toString().also {
            println(it)
            check(input0expected1 == it)
        }
        part2(input1).toString().also {
            println(it)
            check(input1expected2 == it)
        }
        part2(input0).toString().also {
            println(it)
            check(input0expected2 == it)
        }
    }
}
