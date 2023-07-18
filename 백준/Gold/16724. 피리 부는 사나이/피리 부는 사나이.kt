val directionWord = arrayOf('R', 'D', 'L', 'U')
val dir = arrayOf(0 to 1, 1 to 0, 0 to -1, -1 to 0)

var N = 0
var M = 0

lateinit var visited: Array<IntArray>
lateinit var graph: Array<CharArray>

var res = 0

fun findFront(x: Int, y: Int): Int {
    val idx = directionWord.indexOf(graph[x][y])
    val nx = x + dir[idx].first
    val ny = y + dir[idx].second

    visited[x][y] =
        if (visited[nx][ny] == 0) {
            visited[nx][ny] = visited[x][y]
            findFront(nx, ny)
        }
        else visited[nx][ny]

    return visited[x][y]
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

    visited = Array(N) { IntArray(M) }

    repeat(N) { i ->
        repeat(M) { j ->
            if (visited[i][j] == 0) {
                visited[i][j] = res + 1
                findFront(i, j)

                if (visited[i][j] == res + 1) res++
            }
        }
    }

    println(res)
}