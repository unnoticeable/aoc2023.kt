object Day03 {
    data class Number(val value: Int, val row: Int, val col: IntRange)
    data class Symbol(val value: Char, val row: Int, val col: Int)

    fun parse(input: String): Pair<List<Number>, List<Symbol>> {
        val lines = input.replace(".", " ").lines()
        val numbers = lines.flatMapIndexed { row, s ->
            Regex("""\d+""").findAll(s).map { Number(it.value.toInt(), row, it.range) }
        }
        val symbols = lines.flatMapIndexed { row, s ->
            Regex("""\p{Punct}""").findAll(s).map { Symbol(it.value.first(), row, it.range.first) }
        }
        return Pair(numbers, symbols)
    }

    fun isAdjacent(n: Number, s: Symbol): Boolean {
        val rows = n.row - 1..n.row + 1
        val cols = n.col.first - 1..n.col.last + 1
        val box = sequence { rows.forEach { row -> cols.forEach { col -> yield(Pair(row, col)) } } }
        return Pair(s.row, s.col) in box
    }

    fun part1(input: String): Int {
        val (numbers, symbols) = parse(input)
        fun isPart(n: Number) = symbols.any { isAdjacent(n, it) }
        return numbers.filter { isPart(it) }.sumOf { it.value }
    }

    fun part2(input: String): Int {
        val (numbers, symbols) = parse(input)
        val stars = symbols.filter { it.value == '*' }
        val ratios = stars.map { star ->
            val neighbors = numbers.filter { isAdjacent(it, star) }
            if (neighbors.size == 2) neighbors[0].value * neighbors[1].value else 0
        }
        return ratios.sum()
    }
}

fun main() {
    with(Day03) {
        val input0 = readInput(javaClass.simpleName)
        val input1 = """
            467..114..
            ...*......
            ..35..633.
            ......#...
            617*......
            .....+.58.
            ..592.....
            ......755.
            ...$.*....
            .664.598..
        """.trimIndent()
        val input1expected1 = """
            4361
        """.trimIndent()
        val input0expected1 = """
            546312
        """.trimIndent()
        val input1expected2 = """
            467835
        """.trimIndent()
        val input0expected2 = """
            87449461
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
