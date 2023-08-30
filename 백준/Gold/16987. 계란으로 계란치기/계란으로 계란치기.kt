
var N = 0
lateinit var durability: IntArray
lateinit var weight: IntArray

var maxBrokenCnt = 0

fun solve(idx: Int, brokenCnt: Int) {
    if (idx == N) {
        maxBrokenCnt = maxBrokenCnt.coerceAtLeast(brokenCnt)
        return
    }

    if (durability[idx] <= 0) {
        solve(idx + 1, brokenCnt)
        return
    }

    var isSolved = false

    for (i in 0 until N) {
        if (idx == i || durability[i] <= 0) continue

        isSolved = true

        durability[idx] -= weight[i]
        durability[i] -= weight[idx]

        var eggBrokenCnt = if (durability[idx] <= 0) 1 else 0
        eggBrokenCnt = if (durability[i] <= 0) eggBrokenCnt + 1 else eggBrokenCnt
        solve(idx + 1, brokenCnt + eggBrokenCnt)

        durability[idx] += weight[i]
        durability[i] += weight[idx]
    }

    if (!isSolved) solve(idx + 1, brokenCnt)
}

fun main() = with(System.`in`.bufferedReader()) {
    N = readLine().toInt()
    durability = IntArray(N)
    weight = IntArray(N)

    repeat(N) {
        readLine().split(" ").apply {
            durability[it] = this[0].toInt()
            weight[it] = this[1].toInt()
        }
    }

    solve(0, 0)

    print(maxBrokenCnt)
}