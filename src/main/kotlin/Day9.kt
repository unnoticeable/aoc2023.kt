object Day9 {
    fun longsOf(s: String) = Regex("""-?\d+""").findAll(s).map { it.value.toLong() }.toList()
    fun parse(s: String) = s.lines().map { longsOf(it) }
    fun diff(l: List<Long>) = sequence {
        var longs = l
        do {
            yield(longs)
            longs = longs.zipWithNext { a, b -> b - a }
        } while (!longs.all { it == 0L })
    }.toList()

    fun part1(input: String) = parse(input).sumOf { list ->
        diff(list).reversed().fold(0L) { acc, l -> l.last() + acc }
    }

    fun part2(input: String) = parse(input).sumOf { list ->
        diff(list).reversed().fold(0L) { acc, l -> l.first() - acc }
    }
}
