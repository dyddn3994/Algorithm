import kotlin.collections.ArrayDeque

const val BOARD_SIZE = 8
const val MAX_COUNT = 8
const val START_X = 7
const val START_Y = 0

val dx = arrayOf(0, 0, 1, 1, 1, 0, -1, -1, -1)
val dy = arrayOf(0, 1, 1, 0, -1, -1, -1, 0, 1)

lateinit var board: Array<Array<BooleanArray>>
var res = 0

fun isWall(x: Int, y: Int): Boolean {
    if (x < 0 || x >= BOARD_SIZE || y < 0 || y >= BOARD_SIZE) return true
    return false
}

fun dfs(x: Int, y: Int, cnt: Int) {
    if (cnt == 8) {
        res = 1
        return
    }

    for (i in dx.indices) {
        if (res == 1) return

        val nx = x + dx[i]
        val ny = y + dy[i]
        if (isWall(nx, ny) || board[nx][ny][cnt]) continue
        if (cnt < 7 && board[nx][ny][cnt + 1]) continue

        dfs(nx, ny, cnt + 1)
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    board = Array(BOARD_SIZE) { Array(BOARD_SIZE) { BooleanArray(MAX_COUNT) } }
    repeat(BOARD_SIZE) { i ->
        val input = readLine()
        repeat(BOARD_SIZE) { j ->
            if (input[j] == '#') board[i][j][0] = true
        }
    }

    // board 초기화
    for (cnt in 1 until MAX_COUNT) {
        for (i in 0 until BOARD_SIZE) {
            for (j in BOARD_SIZE - 1 downTo cnt) {
                if (board[j - 1][i][cnt - 1]) board[j][i][cnt] = board[j - 1][i][cnt - 1]
            }
        }
    }

    if (!board[START_X][START_Y][0]) {
        dfs(START_X, START_Y, 0)
    }

    print(res)

}