
lateinit var infoList: ArrayList<Pair<Int, Int>>
lateinit var dp: Array<Array<IntArray>>

var res = 0

fun solve(idx: Int, wCnt: Int, bCnt: Int): Int {
    if (idx == infoList.size) return 0

    if (dp[wCnt][bCnt][idx] > 0) return dp[wCnt][bCnt][idx]

    if (wCnt == 15 && bCnt == 15) {
        return 0
    }

    if (wCnt < 15) {
        dp[wCnt][bCnt][idx] = dp[wCnt][bCnt][idx].coerceAtLeast(infoList[idx].first + solve(idx + 1, wCnt + 1, bCnt))
    }
    if (bCnt < 15) {
        dp[wCnt][bCnt][idx] = dp[wCnt][bCnt][idx].coerceAtLeast(infoList[idx].second + solve(idx + 1, wCnt, bCnt + 1))
    }
    dp[wCnt][bCnt][idx] = dp[wCnt][bCnt][idx].coerceAtLeast(solve(idx + 1, wCnt, bCnt))

    return dp[wCnt][bCnt][idx]
}

fun main() = with(System.`in`.bufferedReader()) {
    infoList = arrayListOf()

    var idx = 0
    while (true) {
        val input = readLine()
        if (input.isNullOrBlank()) {
            break
        }

        input.split(" ").apply {
            infoList.add(Pair(this[0].toInt(), this[1].toInt()))
        }
        idx++
    }
    dp = Array(16) { Array(16) { IntArray(idx) } }

    solve(0, 0, 0)

    println(dp[0][0][0])
}