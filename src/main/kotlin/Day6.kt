object Day6 {
    fun longsOf(s: String) = Regex("""\d+""").findAll(s).map { it.value.toLong() }.toList()
    fun parse(s: String) = s.lines().let { (times0, dists0) -> Pair(longsOf(times0), longsOf(dists0)) }
    fun ways(time: Long, dist: Long) = (0..time).fold(0) { ways, i -> if (i * (time - i) > dist) ways + 1 else ways }
    fun part1(input: String) = parse(input).let { (times, dists) ->
        times.indices.map { i -> ways(times[i], dists[i]) }.reduce { x, y -> x * y }
    }

    fun part2(input: String) = parse(input).let { (times, dists) ->
        val time = times.joinToString("").toLong()
        val dist = dists.joinToString("").toLong()
        ways(time, dist)
    }
}
