
var N = 0
var M = 0
lateinit var go: IntArray
lateinit var dp: IntArray

fun dfs(idx: Int) {
    for (i in idx + 1 .. 100) {
        var cnt = dp[i - 1]
        for (j in 1 .. 5) {
            if (i - 1 - j < 1) break
            cnt = cnt.coerceAtMost(dp[i - 1 - j])
        }
        if (cnt == Int.MAX_VALUE) continue

        if (go[i] > 0) {
            if (dp[go[i]] > cnt + 1) {
                dp[go[i]] = cnt + 1
                dfs(go[i])
            }
        }
        else dp[i] = dp[i].coerceAtMost(cnt + 1)
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    readLine().split(" ").apply {
        N = this[0].toInt()
        M = this[1].toInt()
    }
    go = IntArray(101)
    repeat(N + M) {
        val (a, b) = readLine().split(" ").map { it.toInt() }
        go[a] = b
    }

    dp = IntArray(101) { Int.MAX_VALUE }
    dp[1] = 0
    for (i in 2 .. 7) {
        if (go[i] > 0 && dp[go[i]] > 1) {
            dp[go[i]] = 1
            dfs(go[i])
        }
        else dp[i] = 1
    }
    dfs(7)

    print(dp[100])
}