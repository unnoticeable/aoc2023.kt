object Day5 {
    data class Range(val start: Long, val end: Long, val shift: Long)

    fun longsOf(s: String) = Regex("""\d+""").findAll(s).map { it.value.toLong() }.toList()
    fun mapOf(s: String) = s.lines().drop(1).map { line ->
        val (dest, src, len) = longsOf(line)
        Range(src, src + len, dest - src)
    }

    fun augment(l: List<Range>) = run {
        val sorted = l.sortedBy { it.start }
        val sentinels = mutableListOf<Range>()
        if (sorted.first().start != 0L) sentinels.add(Range(0, sorted.first().start, 0))
        if (sorted.last().end != Long.MAX_VALUE) sentinels.add(Range(sorted.last().end, Long.MAX_VALUE, 0))
        sorted.indices.drop(1).forEach { i ->
            if (sorted[i - 1].end != sorted[i].start) sentinels.add(Range(sorted[i - 1].end, sorted[i].start, 0))
        }
        (sorted + sentinels).sortedBy { it.start }
    }

    fun parse(s: String) = s.split("\n\n").let { parts0 ->
        val seeds = longsOf(parts0.first())
        val maps = parts0.drop(1).map { augment(mapOf(it)) }
        Pair(seeds, maps)
    }

    fun part1(input: String) = parse(input).let { (seeds, maps) ->
        seeds.minOf { seed0 ->
            maps.fold(seed0) { seed, map -> seed + map.first { it.start <= seed && seed < it.end }.shift }
        }
    }

    fun part2(input: String) = parse(input).let { (seeds, maps) ->
        val ranges0 = seeds.chunked(2).map { (start, len) -> Range(start, start + len, 0) }
        maps.fold(ranges0) { ranges, map ->
            val inRanges = ranges.toMutableList()
            val outRanges = mutableListOf<Range>()
            while (inRanges.isNotEmpty()) {
                val inRange = inRanges.removeLast()
                val mapRange = map.first { it.start <= inRange.start && inRange.start < it.end }
                if (inRange.end <= mapRange.end) {
                    outRanges.add(Range(inRange.start + mapRange.shift, inRange.end + mapRange.shift, 0))
                } else {
                    outRanges.add(Range(inRange.start + mapRange.shift, mapRange.end + mapRange.shift, 0))
                    inRanges.add(Range(mapRange.end, inRange.end, 0))
                }
            }
            outRanges
        }.minOf { it.start }
    }
}
