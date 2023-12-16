object Day10 {
    data class ParseResult(
        val map: List<String>, val start: Pair<Int, Int>, val neighbors: (Pair<Int, Int>) -> List<Pair<Int, Int>>
    )

    fun parse(s: String) = run {
        var map = s.lines()
        val startCoord = map.indexOfFirst { row -> 'S' in row }.let { r -> Pair(r, map[r].indexOf('S')) }
        val startLetter = run {
            val (r, c) = startCoord
            if ((0 <= r - 1 && map[r - 1][c] in "7|F") && (c + 1 < map[r].length && map[r][c + 1] in "J-7")) 'L'
            else if ((c + 1 < map[r].length && map[r][c + 1] in "J-7") && (r + 1 < map.size && map[r + 1][c] in "J|L")) 'F'
            else if ((r + 1 < map.size && map[r + 1][c] in "J|L") && (0 < +c - 1 && map[r][c - 1] in "L-F")) '7'
            else if ((0 < +c - 1 && map[r][c - 1] in "L-F") && (0 <= r - 1 && map[r - 1][c] in "7|F")) 'J'
            else error("Start must be a corner.")
        }
        map = map.map { if ('S' in it) it.replace('S', startLetter) else it }
        fun neighbors(coord: Pair<Int, Int>) = coord.let { (row, col) ->
            when (map[row][col]) {
                '|' -> listOf(Pair(row - 1, col), Pair(row + 1, col))
                '-' -> listOf(Pair(row, col - 1), Pair(row, col + 1))
                'L' -> listOf(Pair(row - 1, col), Pair(row, col + 1))
                'J' -> listOf(Pair(row - 1, col), Pair(row, col - 1))
                '7' -> listOf(Pair(row, col - 1), Pair(row + 1, col))
                'F' -> listOf(Pair(row, col + 1), Pair(row + 1, col))
                else -> error("Character is not on the loop.")
            }
        }
        ParseResult(map, startCoord, ::neighbors)
    }

    fun part1(input: String) = parse(input).let { (_, start, neighbors) ->
        val loop = mutableSetOf(start)
        var fringe = neighbors(start)
        var steps = 0
        while (fringe.isNotEmpty()) {
            loop.addAll(fringe)
            steps++
            fringe = fringe.flatMap { neighbors(it) }.filter { it !in loop }
        }
        steps
    }

    fun part2(input: String) = parse(input).let { (map, start, neighbors) ->
        val loop = mutableSetOf(start)
        var fringe = neighbors(start)
        while (fringe.isNotEmpty()) {
            loop.addAll(fringe)
            fringe = fringe.flatMap { neighbors(it) }.filter { it !in loop }
        }

        /*
         * Suppose that for each original line of the map we insert a virtual line beneath it. For each tile of the
         * original line that connects to the tile beneath it, we draw a vertical pipe in our virtual line. When we
         * ray trace across our virtual line instead of the original line, we avoid being "stuck" inside horizontal
         * pipes or upward pointing corners that don't actually cross our ray. We don't need to actually materialize the
         * virtual line, we could've just changed the trace function to check only for "7|F" characters.
         */
        val verts = map.map { line -> line.map { if (it in "7|F") '|' else '.' } }

        // If we cross an odd number of verts then we are inside, and an even number is outside.
        fun trace(row: Int, col: Int) = (col + 1 until map[row].length).fold(0) { acc, c ->
            if (Pair(row, c) in loop && verts[row][c] == '|') acc + 1 else acc
        }

        val nonLoop = map.indices.flatMap { r -> map[r].indices.map { c -> Pair(r, c) } }.filter { it !in loop }
        nonLoop.fold(0) { acc, coord -> if (trace(coord.first, coord.second) % 2 == 1) acc + 1 else acc }
    }
}
