import kotlin.test.Test
import kotlin.test.assertEquals

class Day10Test {
    val day = Day10
    val input = javaClass.getResourceAsStream("${day.javaClass.simpleName}.txt")?.reader()?.readText()?.trim() ?: ""
    val example1 = """
        ..F7.
        .FJ|.
        SJ.L7
        |F--J
        LJ...
    """.trimIndent()
    val example2 = """
        ...........
        .S-------7.
        .|F-----7|.
        .||.....||.
        .||.....||.
        .|L-7.F-J|.
        .|..|.|..|.
        .L--J.L--J.
        ...........
    """.trimIndent()
    val example3 = """
        .F----7F7F7F7F-7....
        .|F--7||||||||FJ....
        .||.FJ||||||||L7....
        FJL7L7LJLJ||LJ.L-7..
        L--J.L7...LJS7F-7L7.
        ....F-J..F7FJ|L7L7L7
        ....L7.F7||L7|.L7L7|
        .....|FJLJ|FJ|F7|.LJ
        ....FJL-7.||.||||...
        ....L---J.LJ.LJLJ...
    """.trimIndent()
    val example4 = """
        FF7FSF7F7F7F7F7F---7
        L|LJ||||||||||||F--J
        FL-7LJLJ||||||LJL-77
        F--JF--7||LJLJ7F7FJ-
        L---JF-JLJ.||-FJLJJ7
        |F|F-JF---7F7-L7L|7|
        |FFJF7L7F-JF7|JL---7
        7-L-JL7||F7|L7F-7F7|
        L.L7LFJ|||||FJL7||LJ
        L7JLJL-JLJLJL--JLJ.L
    """.trimIndent()

    @Test
    fun part1() {
        val example1Expected1 = """
            8
        """.trimIndent()
        val inputExpected1 = """
            6931
        """.trimIndent()
        assertEquals(example1Expected1, day.part1(example1).toString())
        assertEquals(inputExpected1, day.part1(input).toString())
    }

    @Test
    fun part2() {
        val example2Expected2 = """
            4
        """.trimIndent()
        val example3Expected2 = """
            8
        """.trimIndent()
        val example4Expected2 = """
            10
        """.trimIndent()
        val inputExpected2 = """
            357
        """.trimIndent()
        assertEquals(example2Expected2, day.part2(example2).toString())
        assertEquals(example3Expected2, day.part2(example3).toString())
        assertEquals(example4Expected2, day.part2(example4).toString())
        assertEquals(inputExpected2, day.part2(input).toString())
    }
}
