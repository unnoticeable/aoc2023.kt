object Day05 {
    data class Range(val start: Long, val end: Long, val shift: Long)

    fun parse(input: String): Pair<List<Long>, List<List<Range>>> {
        val parts0 = input.split("\n\n")
        val seeds = longsOf(parts0.first()).toList()
        fun mapOf(s: String) = s.lines().drop(1).map {
            val (dest, src, n) = longsOf(it).toList()
            Range(src, src + n, dest - src)
        }

        val maps = parts0.drop(1).map { mapOf(it) }
        return Pair(seeds, maps)
    }

    fun part1(input: String): Long {
        val (seeds, maps) = parse(input)
        fun convert(seed0: Long) = maps.fold(seed0) { seed, map ->
            val range = map.firstOrNull { it.start <= seed && seed < it.end }
            if (range != null) seed + range.shift else seed
        }
        return seeds.minOf { convert(it) }
    }

    fun part2(input: String): Long {
        val (seeds0, maps0) = parse(input)
        val seeds = seeds0.chunked(2).map { Range(it[0], it[0] + it[1], 0) }
        fun augment(l: List<Range>): List<Range> {
            val sorted = l.sortedBy { it.start }
            val sentinels = mutableListOf<Range>()
            if (sorted.first().start != 0L) {
                sentinels.add(Range(0L, sorted[0].start, 0L))
            }
            if (sorted.last().end != Long.MAX_VALUE) {
                sentinels.add(Range(sorted.last().end, Long.MAX_VALUE, 0L))
            }
            for (i in sorted.indices.drop(1)) {
                if (sorted[i - 1].end != sorted[i].start) {
                    sentinels.add(Range(sorted[i - 1].end, sorted[i].start, 0))
                }
            }
            return (sorted + sentinels).sortedBy { it.start }
        }

        val maps = maps0.map { augment(it) }
        fun convert(seeds0: MutableList<Range>) = maps.fold(seeds0) { seeds, map ->
            val result = mutableListOf<Range>()
            while (seeds.isNotEmpty()) {
                val seed = seeds.removeLast()
                val range = map.first { it.start <= seed.start && seed.start < it.end }
                if (seed.end <= range.end) {
                    result.add(Range(seed.start + range.shift, seed.end + range.shift, 0))
                } else {
                    result.add(Range(seed.start + range.shift, range.end + range.shift, 0))
                    seeds.add(Range(range.end, seed.end, 0))
                }
            }
            result
        }
        return convert(seeds.toMutableList()).minOf { it.start }
    }
}

fun main() {
    with(Day05) {
        val input0 = readInput(javaClass.simpleName)
        val input1 = """
            seeds: 79 14 55 13

            seed-to-soil map:
            50 98 2
            52 50 48

            soil-to-fertilizer map:
            0 15 37
            37 52 2
            39 0 15

            fertilizer-to-water map:
            49 53 8
            0 11 42
            42 0 7
            57 7 4

            water-to-light map:
            88 18 7
            18 25 70

            light-to-temperature map:
            45 77 23
            81 45 19
            68 64 13

            temperature-to-humidity map:
            0 69 1
            1 0 69

            humidity-to-location map:
            60 56 37
            56 93 4
        """.trimIndent()
        val input1expected1 = """
            35
        """.trimIndent()
        val input0expected1 = """
            282277027
        """.trimIndent()
        val input1expected2 = """
            46
        """.trimIndent()
        val input0expected2 = """
            11554135
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
