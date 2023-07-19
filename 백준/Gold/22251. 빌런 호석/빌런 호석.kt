import kotlin.math.pow

val numLights = arrayOf (
    booleanArrayOf(true, true, true, true, true, true, false),
    booleanArrayOf(false, true, true, false, false, false, false),
    booleanArrayOf(true, true, false, true, true, false, true),
    booleanArrayOf(true, true, true, true, false, false, true),
    booleanArrayOf(false, true, true, false, false, true, true),
    booleanArrayOf(true, false, true, true, false, true, true),
    booleanArrayOf(true, false, true, true, true, true, true),
    booleanArrayOf(true, true, true, false, false, false, false),
    booleanArrayOf(true, true, true, true, true, true, true),
    booleanArrayOf(true, true, true, true, false, true, true)
)

lateinit var lightDiffs: Array<IntArray>

fun getLightDiff(n1: Int, n2: Int): Int {
    if (lightDiffs[n1][n2] != -1) return lightDiffs[n1][n2]

    var cnt = 0
    for (i in 0 until numLights[0].size) {
        if (numLights[n1][i] != numLights[n2][i]) cnt++
    }

    lightDiffs[n1][n2] = cnt
    lightDiffs[n2][n1] = cnt
    return cnt
}

var N = 0
var K = 0
var P = 0
var X = 0
lateinit var xToArr: IntArray

var res = 0

fun solve(cnt: Int, k: Int, isMaxNum:Boolean) {
    if (cnt > P) {
        return
    }
    if (k < 0) {

        res++
        return
    }

    if (!isMaxNum) {
        for (i in 0 .. 9) {
            solve(cnt + getLightDiff(i, xToArr[k]), k - 1, false)
        }
    }
    else {
        val maxNum = (N / 10.0.pow(k.toDouble()).toInt()) % 10
        for (i in 0 until maxNum) {
            solve(cnt + getLightDiff(i, xToArr[k]), k - 1, false)
        }
        solve(cnt + getLightDiff(maxNum, xToArr[k]), k - 1, true)
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    readLine().split(" ").apply {
        N = this[0].toInt()
        K = this[1].toInt()
        P = this[2].toInt()
        X = this[3].toInt()
    }

    lightDiffs = Array(10) { IntArray(10) { -1 } }
    xToArr = IntArray(K).mapIndexed { idx, _ ->
        (X / 10.0.pow(idx.toDouble()).toInt()) % 10
    }.toIntArray()

    solve(0, K - 1, true)

    var zeroCnt = 0
    xToArr.forEach {
        zeroCnt += getLightDiff(it, 0)
    }
    if (zeroCnt <= P) res--

    println(res - 1) // 같은 숫자의 경우 빼기
}