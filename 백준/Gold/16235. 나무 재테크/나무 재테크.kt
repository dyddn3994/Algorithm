var N = 0
var M = 0
var K = 0

lateinit var foods: Array<IntArray>
lateinit var trees: Array<Array<MutableList<Int>>>
lateinit var lands: Array<IntArray>

val dx = arrayOf(-1, -1, -1, 0, 1, 1, 1, 0)
val dy = arrayOf(-1, 0, 1, 1, 1, 0, -1, -1)
fun isWall(x: Int, y: Int): Boolean = x < 0 || x >= N || y < 0 || y >= N

fun main() = with(System.`in`.bufferedReader()) {
    readLine().split(" ").map { it.toInt() }.apply {
        N = this[0]
        M = this[1]
        K = this[2]
    }

    foods = Array(N) { IntArray(N) }
    repeat(N) { i ->
        foods[i] = readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    trees = Array(N) { Array(N) { mutableListOf() } }
    repeat(M) {
        val (x, y, z) = readLine().split(" ").map { it.toInt() }
        trees[x - 1][y - 1].add(z)
    }

    lands = Array(N) { IntArray(N) { 5 } }
    for (i in 0 until N) {
        for (j in 0 until N) {
            trees[i][j].sort()
        }
    }

    repeat(K) { y ->
        // 봄
        repeat(N) { i ->
            repeat(N) { j ->
                for (idx in trees[i][j].indices) {
                    val it = trees[i][j][idx]

//                    if (trees[i][j].size < idx) break
                    if (lands[i][j] >= it) {
                        lands[i][j] -= it
                        trees[i][j][idx]++
                    } else {
                        // 양분 될 놈들 여름 처리
                        for (k in idx until trees[i][j].size) {
                            lands[i][j] += trees[i][j][idx] / 2
                            trees[i][j].removeAt(idx)
                        }
                        break
//                        trees[i][j] = trees[i][j].take(idx).toMutableList()
                    }
                }
            }
        }

        // 가을
        repeat(N) { i ->
            repeat(N) { j ->
                val cnt = trees[i][j].count { it >= 5 && it % 5 == 0 }
                if (cnt > 0) {
                    for (k in dx.indices) {
                        val nx = i + dx[k]
                        val ny = j + dy[k]
                        if (isWall(nx, ny)) continue

                        repeat(cnt) {
                            trees[nx][ny].add(0, 1)
                        }
                    }
                }
            }
        }

        // 겨울
        repeat(N) { i ->
            repeat(N) { j ->
                lands[i][j] += foods[i][j]
            }
        }
    }

    var treeCnt = 0
    repeat(N) { i ->
        repeat(N) { j ->
            treeCnt += trees[i][j].size
        }
    }
    print(treeCnt)
}