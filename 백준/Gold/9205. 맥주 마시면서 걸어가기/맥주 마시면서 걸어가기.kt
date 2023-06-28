import kotlin.math.abs

var n = 0 // 편의점 개수
var homeX = 0
var homeY = 0
var festX = 0
var festY = 0
lateinit var convPos: Array<Pair<Int, Int>>

lateinit var visited: BooleanArray
var canReach = false

fun getDist(x1: Int, y1: Int, x2: Int, y2: Int): Int {
    return abs(x1 - x2) + abs(y1 - y2)
}

fun solve(pos: Int) {
    visited[pos] = true
    if (getDist(convPos[pos].first, convPos[pos].second, festX, festY) <= 1000) canReach = true

    for (i in 0 until n) {
        if (canReach) return
        if (!visited[i] && getDist(convPos[pos].first, convPos[pos].second, convPos[i].first, convPos[i].second) <= 1000) solve(i)
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val t = readLine().toInt()
    val sb = StringBuilder()
    repeat(t) {
        n = readLine().toInt()
        convPos = Array(n) { Pair(0, 0) }
        readLine().split(" ").apply {
            homeX = this[0].toInt()
            homeY = this[1].toInt()
        }
        repeat(n) { i ->
            val (x, y) = readLine().split(" ").map { it.toInt() }
            convPos[i] = Pair(x, y)
        }
        readLine().split(" ").apply {
            festX = this[0].toInt()
            festY = this[1].toInt()
        }

        visited = BooleanArray(n)
        canReach = false

        if (getDist(homeX, homeY, festX, festY) <= 1000) canReach = true

        for (i in 0 until n) {
            if (canReach) break
            if (getDist(homeX, homeY, convPos[i].first, convPos[i].second) <= 1000) solve(i)
        }

        if (canReach) sb.append("happy")
        else sb.append("sad")
        sb.append("\n")
    }

    print(sb)
}