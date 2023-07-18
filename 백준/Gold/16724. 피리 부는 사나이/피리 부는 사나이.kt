
val directionWord = arrayOf('R', 'D', 'L', 'U')
val dir = arrayOf(0 to 1, 1 to 0, 0 to -1, -1 to 0)

var N = 0
var M = 0

lateinit var visited: Array<BooleanArray>
lateinit var graph: Array<CharArray>

var res = 0

fun isWall(x: Int, y: Int): Boolean {
    if (x >= N || x < 0 || y >= M || y < 0) return true
    return false
}

fun findFront(x: Int, y: Int) {
    visited[x][y] = true

    val idx = directionWord.indexOf(graph[x][y])
    val nx = x + dir[idx].first
    val ny = y + dir[idx].second

    if (!visited[nx][ny]) {
        findFront(nx, ny)
        findBack(nx, ny)
    }
}

fun findBack(x: Int, y: Int) {
    visited[x][y] = true

    dir.forEachIndexed { idx, (px, py) ->
        val nx = x + px
        val ny = y + py

        if (!isWall(nx, ny) && !visited[nx][ny]) {
            if (graph[nx][ny] == directionWord[(idx + 2) % 4]) findBack(nx, ny)
        }
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    readLine().split(" ").apply {
        N = this[0].toInt()
        M = this[1].toInt()
    }

    graph = Array(N) { CharArray(M) }
    repeat(N) { i ->
        val input = readLine()
        repeat(M) { j ->
            graph[i][j] = input[j]
        }
    }

    visited = Array(N) { BooleanArray(M) }

    repeat(N) { i ->
        repeat(M) { j ->
            if (!visited[i][j]) {
                findBack(i, j)
                findFront(i, j)
                res++
            }
        }
    }

    println(res)
}