import kotlin.math.pow

object Day4 {
    data class Card(val id: Int, val winners: List<Int>, val haves: List<Int>)

    fun intOf(s: String) = Regex("""\d+""").find(s)?.value?.toInt() ?: 0
    fun intsOf(s: String) = Regex("""\d+""").findAll(s).map { it.value.toInt() }.toList()
    fun parse(s: String) = s.lines().map { line ->
        val (id0, rest0) = line.split(":")
        val (winners0, haves0) = rest0.split("|")
        Card(intOf(id0), intsOf(winners0), intsOf(haves0))
    }

    fun part1(input: String) = parse(input).sumOf { (_, winners, haves) ->
        val matches = winners.toSet().intersect(haves.toSet()).size
        if (matches == 0) 0 else 2.0.pow(matches - 1).toInt()
    }

    fun part2(input: String) = parse(input).let { cards ->
        val matches = cards.map { it.winners.toSet().intersect(it.haves.toSet()).size }
        val copies = Array(matches.size) { 1 }
        copies.indices.forEach { i -> (i + 1..i + matches[i]).forEach { j -> copies[j] += copies[i] } }
        copies.sum()
    }
}
