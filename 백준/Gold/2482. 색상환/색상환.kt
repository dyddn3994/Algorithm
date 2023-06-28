
const val MOD = 1_000_000_003

fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    val K = readLine().toInt()

    val dp = Array(N + 1) { IntArray(K + 1) }

    for (i in 1 .. N) {
        dp[i][1] = i
        for (j in 2 .. K) {
            dp[i][j] = dp[i - 1][j]
            if (i > 3) dp[i][j] = (dp[i][j] + dp[i - 2][j - 1]) % MOD
        }
    }

    print(dp[N][K])
}