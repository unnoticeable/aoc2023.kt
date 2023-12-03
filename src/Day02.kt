import kotlin.math.max

object Day02 {
    data class Game(val id: Int, val subsets: List<Map<String, Int>>)

    fun parse(input: String): List<Game> {
        fun subsetOf(s: String) = s.split(",").associate { wordOf(it) to intOf(it) }
        fun subsetsOf(s: String) = s.split(";").map { subsetOf(it) }
        fun gameOf(s: String): Game {
            val (id0, subsets0) = s.split(":")
            return Game(intOf(id0), subsetsOf(subsets0))
        }
        return input.lines().map { gameOf(it) }
    }

    fun part1(input: String): Int {
        val games = parse(input)
        fun isPossible(g: Game) = g.subsets.all {
            (it["red"] ?: 0) <= 12 && (it["green"] ?: 0) <= 13 && (it["blue"] ?: 0) <= 14
        }
        return games.filter { isPossible(it) }.sumOf { it.id }
    }

    fun part2(input: String): Int {
        val games = parse(input)
        fun fewestOf(g: Game) = g.subsets.reduce { x, y ->
            mapOf(
                "red" to max(x["red"] ?: 0, y["red"] ?: 0),
                "green" to max(x["green"] ?: 0, y["green"] ?: 0),
                "blue" to max(x["blue"] ?: 0, y["blue"] ?: 0)
            )
        }.filterValues { it != 0 }

        fun powerOf(m: Map<String, Int>) = m.values.reduce { x, y -> x * y }
        return games.sumOf { powerOf(fewestOf(it)) }
    }
}

fun main() {
    with(Day02) {
        val input0 = readInput(javaClass.simpleName)
        val input1 = """
            Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
            Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
            Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
            Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
            Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
        """.trimIndent()
        val input1expected1 = """
            8
        """.trimIndent()
        val input0expected1 = """
            2331
        """.trimIndent()
        val input1expected2 = """
            2286
        """.trimIndent()
        val input0expected2 = """
            71585
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
