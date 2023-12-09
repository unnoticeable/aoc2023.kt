object Day10 {
    fun parse(input: String) = input.lines().map { line -> line.map { it } }
    fun startOf(grid: List<List<Char>>): Pair<Int, Int> {
        for (row in grid.indices) {
            for (col in grid[row].indices) {
                if (grid[row][col] == 'S') return Pair(row, col)
            }
        }
        error(Unit)
    }

    fun neighborsOf(coord: Pair<Int, Int>, grid: List<List<Char>>): List<Pair<Int, Int>> {
        val (row, col) = coord
        return when (grid[row][col]) {
            '|' -> listOf(Pair(row - 1, col), Pair(row + 1, col))
            '-' -> listOf(Pair(row, col - 1), Pair(row, col + 1))
            'L' -> listOf(Pair(row - 1, col), Pair(row, col + 1))
            'J' -> listOf(Pair(row - 1, col), Pair(row, col - 1))
            '7' -> listOf(Pair(row + 1, col), Pair(row, col - 1))
            'F' -> listOf(Pair(row + 1, col), Pair(row, col + 1))
            'S' -> {
                val neighbors = mutableListOf<Pair<Int, Int>>()
                if (row - 1 >= 0 && grid[row - 1][col] in "|7F") neighbors.add(Pair(row - 1, col))
                if (col + 1 < grid[row].size && grid[row][col + 1] in "-J7") neighbors.add(Pair(row, col + 1))
                if (row + 1 < grid.size && grid[row + 1][col] in "|LJ") neighbors.add(Pair(row + 1, col))
                if (col - 1 >= 0 && grid[row][col - 1] in "-LF") neighbors.add(Pair(row, col - 1))
                neighbors
            }

            else -> error(Unit)
        }
    }

    fun part1(input: String): Int {
        val grid = parse(input)
        val start = startOf(grid)
        var unvisited = listOf(start)
        val visited = mutableSetOf<Pair<Int, Int>>()
        var dist = 0
        while (true) {
            visited.addAll(unvisited)
            unvisited = unvisited.flatMap { neighborsOf(it, grid) }.filter { !visited.contains(it) }
            if (unvisited.isEmpty()) {
                return dist
            }
            dist++
        }
    }

    enum class Dir { Up, Right, Down, Left }

    fun part2(input: String): Int {
        val grid = parse(input)
        val start = startOf(grid)
        var unvisited = listOf(start)
        val visited = mutableListOf<Pair<Int, Int>>()
        while (true) {
            visited.addAll(unvisited)
            unvisited = unvisited.flatMap { neighborsOf(it, grid) }.filter { !visited.contains(it) }
            if (unvisited.isEmpty()) {
                break
            }
        }
        val inside = mutableSetOf<Pair<Int, Int>>()
        var (row, col) = start
        val startingNeighbors = visited.slice(1..2)
        var dir = if (Pair(row - 1, col) in startingNeighbors && Pair(row, col + 1) in startingNeighbors) Dir.Up
        else if (Pair(row + 1, col) in startingNeighbors && Pair(row, col + 1) in startingNeighbors) Dir.Right
        else if (Pair(row + 1, col) in startingNeighbors && Pair(row, col - 1) in startingNeighbors) Dir.Down
        else if (Pair(row - 1, col) in startingNeighbors && Pair(row, col - 1) in startingNeighbors) Dir.Left
        else error(Unit)
        do {
            when (dir) {
                Dir.Up -> {
                    row--
                    when (grid[row][col]) {
                        '7' -> dir = Dir.Left
                        'F' -> dir = Dir.Right
                    }
                }

                Dir.Right -> {
                    col++
                    when (grid[row][col]) {
                        'J' -> dir = Dir.Up
                        '7' -> dir = Dir.Down
                    }
                }

                Dir.Down -> {
                    row++
                    when (grid[row][col]) {
                        'J' -> dir = Dir.Left
                        'L' -> dir = Dir.Right
                    }
                }

                Dir.Left -> {
                    col--
                    when (grid[row][col]) {
                        'L' -> dir = Dir.Up
                        'F' -> dir = Dir.Down
                    }
                }
            }
            // 1 for clockwise (look right), -1 for counterclockwise (look left)
            val sign = 1
            when (dir) {
                Dir.Up -> {
                    for (k in 1..Int.MAX_VALUE) {
                        val c = sign * k + col
                        if (0 <= c && c < grid[row].size && !visited.contains(Pair(row, c))) {
                            inside.add(Pair(row, c))
                        } else {
                            break
                        }
                    }
                }

                Dir.Right -> {
                    for (k in 1..Int.MAX_VALUE) {
                        val r = sign * k + row
                        if (0 <= r && r < grid.size && !visited.contains(Pair(r, col))) {
                            inside.add(Pair(r, col))
                        } else {
                            break
                        }
                    }
                }

                Dir.Down -> {
                    for (k in 1..Int.MAX_VALUE) {
                        val c = sign * (-k) + col
                        if (0 <= c && c < grid[row].size && !visited.contains(Pair(row, c))) {
                            inside.add(Pair(row, c))
                        } else {
                            break
                        }
                    }
                }

                Dir.Left -> {
                    for (k in 1..Int.MAX_VALUE) {
                        val r = sign * (-k) + row
                        if (0 <= r && r < grid.size && !visited.contains(Pair(r, col))) {
                            inside.add(Pair(r, col))
                        } else {
                            break
                        }
                    }
                }
            }
        } while (!(row == start.first && col == start.second))
        return inside.size
    }
}

fun main() {
    with(Day10) {
        val input0 = readInput(javaClass.simpleName)
        val input1 = """
            ..F7.
            .FJ|.
            SJ.L7
            |F--J
            LJ...
        """.trimIndent()
        val input2 = """
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
        val input3 = """
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
        val input4 = """
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
        val input1expected1 = """
            8
        """.trimIndent()
        val input0expected1 = """
            6931
        """.trimIndent()
        val input1expected2 = """
            1
        """.trimIndent()
        val input2expected2 = """
            4
        """.trimIndent()
        val input3expected2 = """
            8
        """.trimIndent()
        val input4expected2 = """
            10
        """.trimIndent()
        val input0expected2 = """
            0
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
        part2(input2).toString().also {
            println(it)
            check(input2expected2 == it)
        }
//        part2(input3).toString().also {
//            println(it)
//            check(input3expected2 == it)
//        }
//        part2(input4).toString().also {
//            println(it)
//            check(input4expected2 == it)
//        }
//        part2(input0).toString().also {
//            println(it)
//            check(input0expected2 == it)
//        }
    }
}
