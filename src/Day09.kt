object Day09 {
    fun parse(input: String): List<List<Long>> {
        return input.lines().map { longsOf(it).toList() }
    }

    fun diff1(l: List<Long>) = l.windowed(2).map { it[1] - it[0] }
    fun diffN(l: List<Long>): List<List<Long>> {
        val result = mutableListOf(l)
        while (!result.last().all { it == 0L }) {
            result.add(diff1(result.last()))
        }
        return result
    }

    fun fillForward(triangle: List<List<Long>>): List<List<Long>> {
        val result = mutableListOf(triangle.last() + listOf(0))
        for (i in triangle.size - 2 downTo 0) {
            result.addFirst(triangle[i] + listOf(triangle[i].last() + result.first().last()))
        }
        return result
    }

    fun fillBackward(triangle: List<List<Long>>): List<List<Long>> {
        val result = mutableListOf(listOf(0L) + triangle.last())
        for (i in triangle.size - 2 downTo 0) {
            result.addFirst(listOf(triangle[i].first() - result.first().first()) + triangle[i])
        }
        return result
    }

    fun part1(input: String): Long {
        val seqs = parse(input)
        return seqs.sumOf { fillForward(diffN(it)).first().last() }
    }

    fun part2(input: String): Long {
        val seqs = parse(input)
        return seqs.sumOf { fillBackward(diffN(it)).first().first() }
    }
}

fun main() {
    with(Day09) {
        val input0 = readInput(javaClass.simpleName)
        val input1 = """
            0 3 6 9 12 15
            1 3 6 10 15 21
            10 13 16 21 30 45
        """.trimIndent()
        val input1expected1 = """
            114
        """.trimIndent()
        val input0expected1 = """
            1819125966
        """.trimIndent()
        val input1expected2 = """
            2
        """.trimIndent()
        val input0expected2 = """
            1140
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
