object Day3 {
    data class Num(val i: Int, val row: Int, val col: IntRange)
    data class Sym(val c: Char, val row: Int, val col: Int)
    data class ParseResult(val nums: List<Num>, val syms: List<Sym>, val isAdjacent: (Num, Sym) -> Boolean)

    fun parse(s: String) = run {
        val nums = mutableListOf<Num>()
        val syms = mutableListOf<Sym>()
        s.lines().forEachIndexed { row, line ->
            nums.addAll(Regex("""\d+""").findAll(line).map { Num(it.value.toInt(), row, it.range) })
            syms.addAll(Regex("""[^.\d]""").findAll(line).map { Sym(it.value.first(), row, it.range.first()) })
        }
        fun isAdjacent(num: Num, sym: Sym) = run {
            val rows = num.row - 1..num.row + 1
            val cols = num.col.first() - 1..num.col.last() + 1
            rows.any { row -> cols.any { col -> sym.row == row && sym.col == col } }
        }
        ParseResult(nums, syms, ::isAdjacent)
    }

    fun part1(input: String) = parse(input).let { (nums, syms, isAdjacent) ->
        nums.filter { num -> syms.any { sym -> isAdjacent(num, sym) } }.sumOf { it.i }
    }

    fun part2(input: String) = parse(input).let { (nums, syms, isAdjacent) ->
        syms.filter { it.c == '*' }.sumOf { sym ->
            val parts = nums.filter { num -> isAdjacent(num, sym) }
            if (parts.size == 2) parts[0].i * parts[1].i else 0
        }
    }
}
