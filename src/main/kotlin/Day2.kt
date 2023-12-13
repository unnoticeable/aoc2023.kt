import kotlin.math.max

object Day2 {
    fun intOf(s: String) = Regex("""\d+""").find(s)?.value?.toInt() ?: 0
    fun wordOf(s: String) = Regex("""\p{Alpha}+""").find(s)?.value ?: ""
    fun subsetOf(s: String) = s.split(",").associate { wordOf(it) to intOf(it) }
    fun gameOf(s: String) = s.split(":").let { (id0, subsets0) ->
        Pair(intOf(id0), subsets0.split(";").map { subsetOf(it) })
    }

    fun isPossible(m: Map<String, Int>) = (m["red"] ?: 0) <= 12 && (m["green"] ?: 0) <= 13 && (m["blue"] ?: 0) <= 14
    fun part1(input: String) = input.lines().sumOf { line ->
        val (id, subsets) = gameOf(line)
        if (subsets.all { isPossible(it) }) id else 0
    }

    fun union(x: Map<String, Int>, y: Map<String, Int>) =
        (x.keys + y.keys).associateWith { max(x[it] ?: 0, y[it] ?: 0) }

    fun part2(input: String) = input.lines().sumOf { line ->
        val (_, subsets) = gameOf(line)
        subsets.reduce { x, y -> union(x, y) }.values.reduce { x, y -> x * y }
    }
}
