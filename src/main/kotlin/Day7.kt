object Day7 {
    fun parse(s: String) = s.lines().map { it.split(" ").let { (cards, bid0) -> Pair(cards, bid0.toInt()) } }
    fun sizes(cards: String) = cards.groupBy { it }.map { it.value.size }.sortedDescending()
    fun type(sizes: List<Int>) = when (sizes) {
        listOf(5) -> 7L
        listOf(4, 1) -> 6
        listOf(3, 2) -> 5
        listOf(3, 1, 1) -> 4
        listOf(2, 2, 1) -> 3
        listOf(2, 1, 1, 1) -> 2
        listOf(1, 1, 1, 1, 1) -> 1
        else -> error("Hand must be 5 cards.")
    }

    fun part1(input: String) = parse(input).let { hands ->
        fun value(cards: String) = cards.fold(type(sizes(cards))) { acc, c -> acc * 100 + "23456789TJQKA".indexOf(c) }
        val sorted = hands.sortedWith { (x, _), (y, _) -> value(x).compareTo(value(y)) }
        sorted.mapIndexed { i, (_, bid) -> bid * (i + 1) }.sum()
    }

    fun part2(input: String) = parse(input).let { hands ->
        fun value(cards: String) = run {
            val noJokers = cards.filter { it != 'J' }
            val sizes = sizes(noJokers).toMutableList()
            if (sizes.isEmpty()) sizes.add(5) else sizes[0] += 5 - sizes.sum()
            cards.fold(type(sizes)) { acc, c -> acc * 100 + "J23456789TQKA".indexOf(c) }
        }

        val sorted = hands.sortedWith { (x, _), (y, _) -> value(x).compareTo(value(y)) }
        sorted.mapIndexed { i, (_, bid) -> bid * (i + 1) }.sum()
    }
}
