import kotlin.test.Test
import kotlin.test.assertEquals

class Day5Test {
    val day = Day5
    val input = javaClass.getResourceAsStream("${day.javaClass.simpleName}.txt")?.reader()?.readText()?.trim() ?: ""
    val example1 = """
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

    @Test
    fun part1() {
        val example1Expected1 = """
            35
        """.trimIndent()
        val inputExpected1 = """
            282277027
        """.trimIndent()
        assertEquals(example1Expected1, day.part1(example1).toString())
        assertEquals(inputExpected1, day.part1(input).toString())
    }

    @Test
    fun part2() {
        val example1Expected2 = """
            46
        """.trimIndent()
        val inputExpected2 = """
            11554135
        """.trimIndent()
        assertEquals(example1Expected2, day.part2(example1).toString())
        assertEquals(inputExpected2, day.part2(input).toString())
    }
}
