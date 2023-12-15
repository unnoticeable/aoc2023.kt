import kotlin.test.Test
import kotlin.test.assertEquals

class Day8Test {
    val day = Day8
    val input = javaClass.getResourceAsStream("${day.javaClass.simpleName}.txt")?.reader()?.readText()?.trim() ?: ""
    val example1 = """
        LLR

        AAA = (BBB, BBB)
        BBB = (AAA, ZZZ)
        ZZZ = (ZZZ, ZZZ)
    """.trimIndent()
    val example2 = """
        LR

        11A = (11B, XXX)
        11B = (XXX, 11Z)
        11Z = (11B, XXX)
        22A = (22B, XXX)
        22B = (22C, 22C)
        22C = (22Z, 22Z)
        22Z = (22B, 22B)
        XXX = (XXX, XXX)
    """.trimIndent()

    @Test
    fun part1() {
        val example1Expected1 = """
            6
        """.trimIndent()
        val inputExpected1 = """
            15989
        """.trimIndent()
        assertEquals(example1Expected1, day.part1(example1).toString())
        assertEquals(inputExpected1, day.part1(input).toString())
    }

    @Test
    fun part2() {
        val example2Expected2 = """
            6
        """.trimIndent()
        val inputExpected2 = """
            13830919117339
        """.trimIndent()
        assertEquals(example2Expected2, day.part2(example2).toString())
        assertEquals(inputExpected2, day.part2(input).toString())
    }
}
