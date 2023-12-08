object Day08 {
    fun parse(input: String): Pair<String, Map<String, Pair<String, String>>> {
        val (path0, graph0) = input.split("\n\n")
        val path = path0.trim()
        val graph = mutableMapOf<String, Pair<String, String>>()
        for (line in graph0.lines()) {
            val (node, left, right) = alnumsOf(line).toList()
            graph[node] = Pair(left, right)
        }
        return Pair(path, graph)
    }

    fun cycleOf(path: String) = iterator {
        var i = 0
        while (true) {
            yield(path[i])
            i = (i + 1) % path.length
        }
    }

    fun stepsOf(node: String, path: String, graph: Map<String, Pair<String, String>>): Int {
        val cycle = cycleOf(path)
        var numSteps = 0
        var location = node
        while (!location.endsWith('Z')) {
            location = when (cycle.next()) {
                'L' -> graph[location]!!.first
                'R' -> graph[location]!!.second
                else -> error(Unit)
            }
            numSteps++
        }
        return numSteps
    }

    fun part1(input: String): Int {
        val (path, graph) = parse(input)
        return stepsOf("AAA", path, graph)
    }

    fun part2(input: String): Long {
        val (path, graph) = parse(input)
        val starts = graph.keys.filter { it.endsWith('A') }
        val steps = starts.map { stepsOf(it, path, graph).toLong() }
        return steps.reduce { x, y -> lcm(x, y) }
    }
}

fun main() {
    with(Day08) {
        val input0 = readInput(javaClass.simpleName)
        val input1 = """
            RL

            AAA = (BBB, CCC)
            BBB = (DDD, EEE)
            CCC = (ZZZ, GGG)
            DDD = (DDD, DDD)
            EEE = (EEE, EEE)
            GGG = (GGG, GGG)
            ZZZ = (ZZZ, ZZZ)
        """.trimIndent()
        val input2 = """
            LLR

            AAA = (BBB, BBB)
            BBB = (AAA, ZZZ)
            ZZZ = (ZZZ, ZZZ)
        """.trimIndent()
        val input3 = """
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
        val input1expected1 = """
            2
        """.trimIndent()
        val input2expected1 = """
            6
        """.trimIndent()
        val input0expected1 = """
            15989
        """.trimIndent()
        val input3expected2 = """
            6
        """.trimIndent()
        val input0expected2 = """
            13830919117339
        """.trimIndent()
        part1(input1).toString().also {
            println(it)
            check(input1expected1 == it)
        }
        part1(input2).toString().also {
            println(it)
            check(input2expected1 == it)
        }
        part1(input0).toString().also {
            println(it)
            check(input0expected1 == it)
        }
        part2(input3).toString().also {
            println(it)
            check(input3expected2 == it)
        }
        part2(input0).toString().also {
            println(it)
            check(input0expected2 == it)
        }
    }
}
