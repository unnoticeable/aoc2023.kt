import kotlin.io.path.Path
import kotlin.io.path.readText

fun readInput(name: String) = Path("src/${name}.txt").readText().trim()
fun intOf(s: String) = Regex("""\d+""").find(s)!!.value.toInt()
fun intsOf(s: String) = Regex("""\d+""").findAll(s).map { it.value.toInt() }
fun wordOf(s: String) = Regex("""\p{Alpha}+""").find(s)!!.value
