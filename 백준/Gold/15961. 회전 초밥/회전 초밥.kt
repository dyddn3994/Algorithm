
fun main() = with(System.`in`.bufferedReader()) {
    val (N, d, k, c) = readLine().split(" ").map { it.toInt() }

    val sushies = IntArray(N + k - 1)
    repeat(N) {
        sushies[it] = readLine().toInt()
    }
    repeat(k - 1) {
        sushies[N + it] = sushies[it]
    }

    val cnts = IntArray(d + 1)
    var diffCnt = 0
    repeat(k) {
        if (cnts[sushies[it]]++ == 0) diffCnt++
    }
    var maxCnt = diffCnt + (if (cnts[c] == 0) 1 else 0)

    for (i in 0 until N - 1) {
        if (cnts[sushies[i]]-- == 1) diffCnt--
        if (cnts[sushies[k + i]]++ == 0) diffCnt++

        maxCnt = maxCnt.coerceAtLeast(diffCnt + (if (cnts[c] == 0) 1 else 0))
        if (maxCnt == k + 1) break
    }

    print(maxCnt)
}