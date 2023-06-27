
var N = 0
var M = 0
lateinit var arr: Array<IntArray>

val ran = arrayOf(-1, 1)
lateinit var visited: Array<BooleanArray>

var res = 0
var sum = 0

fun isWall(x: Int, y: Int): Boolean {
    if (x < 0 || x >= N || y < 0 || y >= M) return true
    return false
}

fun solve(x: Int, y: Int) {
    var ranY = y

    for (i in x until N) {
        for (j in ranY until M) {
            if (visited[i][j]) continue

            for (a in ran) {
                for (b in ran) {
                    val x2 = i + a
                    val y2 = j + b
                    if (isWall(i, y2) || isWall(x2, j) || visited[i][y2] || visited[x2][j]) continue

                    sum += arr[i][j] * 2 + arr[i][y2] + arr[x2][j]
                    res = res.coerceAtLeast(sum)

                    visited[i][j] = true
                    visited[i][y2] = true
                    visited[x2][j] = true
                    solve(i, j)
                    visited[i][j] = false
                    visited[i][y2] = false
                    visited[x2][j] = false

                    sum -= arr[i][j] * 2 + arr[i][y2] + arr[x2][j]
                }
            }
        }
        ranY = 0
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    readLine().split(" ").apply {
        N = this[0].toInt()
        M = this[1].toInt()
    }
    arr = Array(N) { IntArray(M) }
    repeat(N) { i ->
        arr[i] = readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    visited = Array(N) { BooleanArray(M) }

    repeat(N) { i ->
        repeat(M) { j ->
            for (a in ran) {
                for (b in ran) {
                    val x = i + a
                    val y = j + b
                    if (isWall(i, y) || isWall(x, j)) continue

                    sum = arr[i][j] * 2 + arr[i][y] + arr[x][j]
                    visited[i][j] = true
                    visited[i][y] = true
                    visited[x][j] = true

                    res = res.coerceAtLeast(sum)
                    solve(i, j)

                    visited[i][j] = false
                    visited[i][y] = false
                    visited[x][j] = false
                }
            }
        }
    }

    print(res)
}