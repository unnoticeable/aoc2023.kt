object Day07 {
    fun parse(input: String): List<Pair<String, Int>> {
        return input.lines().map {
            val (cards0, bid0) = it.split(" ")
            Pair(cards0.trim(), bid0.toInt())
        }
    }

    fun sizesOf(cards: String) = cards.groupBy { it }.map { (_, v) -> v.size }.sortedDescending()
    fun typeOf(sizes: List<Int>): Int {
        return if (sizes[0] == 5) 6
        else if (sizes[0] == 4 && sizes[1] == 1) 5
        else if (sizes[0] == 3 && sizes[1] == 2) 4
        else if (sizes[0] == 3 && sizes[1] == 1 && sizes[2] == 1) 3
        else if (sizes[0] == 2 && sizes[1] == 2 && sizes[2] == 1) 2
        else if (sizes[0] == 2 && sizes[1] == 1 && sizes[2] == 1 && sizes[3] == 1) 1
        else if (sizes[0] == 1 && sizes[1] == 1 && sizes[2] == 1 && sizes[3] == 1 && sizes[4] == 1) 0
        else error(Unit)
    }

    fun part1(input: String): Int {
        val hands = parse(input)
        val order = "23456789TJQKA"
        fun ordOf(cards: String) = listOf(typeOf(sizesOf(cards))) + cards.map { order.indexOf(it) }
        val sorted = hands.sortedWith { (xCards, _), (yCards, _) ->
            (ordOf(xCards) zip ordOf(yCards)).map { (x, y) -> x - y }.first { it != 0 }
        }
        return sorted.mapIndexed { i, h -> (i + 1) * h.second }.sum()
    }

    fun part2(input: String): Int {
        val hands = parse(input)
        val order = "J23456789TQKA"
        fun ordOf(cards: String): List<Int> {
            val numJacks = cards.count { it == 'J' }
            val sizes = sizesOf(cards.filter { it != 'J' }).toMutableList()
            if (numJacks == 5) sizes.add(5) else sizes[0] += numJacks
            return listOf(typeOf(sizes)) + cards.map { order.indexOf(it) }
        }

        val sorted = hands.sortedWith { (xCards, _), (yCards, _) ->
            (ordOf(xCards) zip ordOf(yCards)).map { (x, y) -> x - y }.first { it != 0 }
        }
        return sorted.mapIndexed { i, h -> (i + 1) * h.second }.sum()
    }
}

fun main() {
    with(Day07) {
        val input0 = readInput(javaClass.simpleName)
        val input1 = """
            32T3K 765
            T55J5 684
            KK677 28
            KTJJT 220
            QQQJA 483
        """.trimIndent()
        val input1expected1 = """
            6440
        """.trimIndent()
        val input0expected1 = """
            253866470
        """.trimIndent()
        val input1expected2 = """
            5905
        """.trimIndent()
        val input0expected2 = """
            254494947
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
