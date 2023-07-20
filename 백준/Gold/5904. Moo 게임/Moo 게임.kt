const val MAX_SIZE = 28

fun main() = with(System.`in`.bufferedReader()) {
    var N = readLine().toInt()

    val arr = IntArray(MAX_SIZE)
    arr[0] = 3

    var idx = MAX_SIZE - 1
    for (i in 0 until MAX_SIZE - 1) {
        if (N <= arr[i]) {
            idx = i - 1
            break
        }
        arr[i + 1] = arr[i] * 2 + 4 + i
    }

    var res = 'o'
    if (N == 1) {
        res = 'm'
        N = -1
    }

    while (N > 0) {
        if (N <= 3) break

        N -= arr[idx]
        if (N == 1) {
            res = 'm'
            break
        }

        N -= 4 + idx
        if (N == 1) {
            res = 'm'
            break
        }

        while (N > 3 && N <= arr[idx]) idx--
    }

    println(res)
}