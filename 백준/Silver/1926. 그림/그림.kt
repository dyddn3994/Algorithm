import java.lang.StringBuilder

var n = 0
var m = 0

val dx = arrayOf(0, 1, 0, -1)
val dy = arrayOf(1, 0, -1, 0)

var cnt = 0
var maxSize = 0

lateinit var graph: Array<IntArray>
lateinit var visited: Array<BooleanArray>

fun isWall(x: Int, y: Int): Boolean {
    return x < 0 || x >= n || y < 0 || y >= m
}

fun solve(x: Int, y: Int): Int {
    visited[x][y] = true
    var size = 1

    repeat(4) {
        val nx = x + dx[it]
        val ny = y + dy[it]
        if (!isWall(nx, ny) && !visited[nx][ny] && graph[nx][ny] == 1) {
            size += solve(nx, ny)
        }
    }

    return size
}

fun main() = with(System.`in`.bufferedReader()) {
    readLine().split(" ").apply {
        n = this[0].toInt()
        m = this[1].toInt()
    }

    graph = Array(n) { IntArray(m) }
    visited = Array(n) { BooleanArray(m) }

    repeat(n) {
        graph[it] = readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    repeat(n) { i ->
        repeat(m) { j ->
            if (!visited[i][j] && graph[i][j] == 1) {
                maxSize = maxSize.coerceAtLeast(solve(i, j))
                cnt++
            }
        }
    }

    val sb = StringBuilder()
    sb.append(cnt).append("\n").append(maxSize)
    print(sb)
}