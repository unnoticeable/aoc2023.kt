import kotlin.math.pow

object Day04 {
    data class Card(val id: Int, val winners: Set<Int>, val havers: Set<Int>)

    fun parse(input: String): List<Card> {
        fun cardOf(s: String): Card {
            val (id0, nums0) = s.split(":")
            val id = intOf(id0)
            val (winners0, havers0) = nums0.split("|")
            val winners = intsOf(winners0).toSet()
            val havers = intsOf(havers0).toSet()
            return Card(id, winners, havers)
        }
        return input.lines().map { cardOf(it) }
    }

    fun part1(input: String): Int {
        val cards = parse(input)
        fun pointsOf(c: Card): Int {
            val numMatches = (c.winners intersect c.havers).size
            return if (numMatches == 0) 0 else 2.0.pow(numMatches - 1).toInt()
        }
        return cards.sumOf { pointsOf(it) }
    }

    fun part2(input: String): Int {
        val cards = parse(input)
        val numMatches = cards.map { (it.winners intersect it.havers).size }
        val numCopies = Array(cards.size) { 1 }
        for (i in numCopies.indices) {
            for (j in 0 until numMatches[i]) {
                numCopies[i + 1 + j] += numCopies[i]
            }
        }
        return numCopies.sum()
    }
}

fun main() {
    with(Day04) {
        val input0 = readInput(javaClass.simpleName)
        val input1 = """
            Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
            Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
            Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
            Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
            Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
            Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
        """.trimIndent()
        val input1expected1 = """
            13
        """.trimIndent()
        val input0expected1 = """
            27059
        """.trimIndent()
        val input1expected2 = """
            30
        """.trimIndent()
        val input0expected2 = """
            5744979
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
