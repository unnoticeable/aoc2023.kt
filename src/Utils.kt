import kotlin.io.path.Path
import kotlin.io.path.readText

fun readInput(name: String) = Path("src/${name}.txt").readText().trim()
fun intOf(s: String) = Regex("""-?\d+""").find(s)!!.value.toInt()
fun intsOf(s: String) = Regex("""-?\d+""").findAll(s).map { it.value.toInt() }
fun wordOf(s: String) = Regex("""\p{Alpha}+""").find(s)!!.value
fun alnumsOf(s: String) = Regex("""\p{Alnum}+""").findAll(s).map { it.value }
fun longsOf(s: String) = Regex("""-?\d+""").findAll(s).map { it.value.toLong() }

fun lcm(x: Long, y: Long): Long {
    var a = x
    var b = y
    while (true) {
        if (a % y == 0L) return a
        else if (b % x == 0L) return b
        if (a > b) {
            b = (a / y + 1) * y
        } else {
            a = (b / x + 1) * x
        }
    }
}
