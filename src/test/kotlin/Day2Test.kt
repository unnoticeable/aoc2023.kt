import kotlin.test.Test
import kotlin.test.assertEquals

class Day2Test {
    val day = Day2
    val input = javaClass.getResourceAsStream("${day.javaClass.simpleName}.txt")?.reader()?.readText()?.trim() ?: ""
    val example1 = """
        Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
        Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
        Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
        Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
        Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
    """.trimIndent()

    @Test
    fun part1() {
        val example1Expected1 = """
            8
        """.trimIndent()
        val inputExpected1 = """
            2331
        """.trimIndent()
        assertEquals(example1Expected1, day.part1(example1).toString())
        assertEquals(inputExpected1, day.part1(input).toString())
    }

    @Test
    fun part2() {
        val example1Expected2 = """
            2286
        """.trimIndent()
        val inputExpected2 = """
            71585
        """.trimIndent()
        assertEquals(example1Expected2, day.part2(example1).toString())
        assertEquals(inputExpected2, day.part2(input).toString())
    }
}
