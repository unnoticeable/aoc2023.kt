object Day8 {
    fun wordsOf(s: String) = Regex("""\w+""").findAll(s).map { it.value }.toList()
    fun parse(s: String) = s.split("\n\n").let { (dirs0, map0) ->
        val dirs = sequence {
            while (true) {
                yieldAll(dirs0.toList())
            }
        }
        val map = map0.lines().associate { line ->
            val (p, l, r) = wordsOf(line)
            p to Pair(l, r)
        }
        Pair(dirs, map)
    }

    fun part1(input: String) = parse(input).let { (dirs, map) ->
        var pos = "AAA"
        var steps = 0
        for (dir in dirs) {
            if (pos == "ZZZ") break
            val (l, r) = map.getValue(pos)
            pos = if (dir == 'L') l else r
            steps++
        }
        steps
    }

    fun gcd(x: Long, y: Long): Long = if (x % y == 0L) y else gcd(y, x % y)
    fun lcm(x: Long, y: Long) = x / gcd(x, y) * y
    fun part2(input: String) = parse(input).let { (dirs, map) ->
        map.keys.filter { it.endsWith('A') }.map { start ->
            var pos = start
            var steps = 0L
            for (dir in dirs) {
                if (pos.endsWith('Z')) break
                val (l, r) = map.getValue(pos)
                pos = if (dir == 'L') l else r
                steps++
            }
            steps
        }.reduce { x, y -> lcm(x, y) }
    }
}
