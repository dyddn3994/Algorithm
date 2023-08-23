import kotlin.math.abs

fun Int.spinPlusCnt(e: Int): Int {
    return if (this <= e) e - this else 10 - this + e
}

fun Int.spinMinusCnt(e: Int): Int {
    return if (this >= e) this - e else 10 + this - e
}

fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    val start = readLine().reversed()
    val end = readLine().reversed()

    val dp = Array(N) { IntArray(10) }
    val firstN = end[0] - '0'
    repeat(10) {
        dp[0][it] = (10 + it - firstN).coerceAtMost(abs(firstN - it))
    }

    for (i in 1 until N) {
        val sb = start[i - 1] - '0'
        val sn = start[i] - '0'
        val e = end[i] - '0'

        for (j in 0..9) {
            val n = (sn + j) % 10
            val plusCnt = n.spinPlusCnt(e)
            val minusCnt = n.spinMinusCnt(e)

            dp[i][n] = (plusCnt + dp[i - 1][(plusCnt + sb + j) % 10]).coerceAtMost(minusCnt + dp[i - 1][(sb + j) % 10])
        }
    }

    println(dp[N - 1][start[N - 1] - '0'])
}