
fun main() = with(System.`in`.bufferedReader()) {
    val (N, K) = readLine().split(" ").map { it.toInt() }

    val map = hashMapOf<Long, Long>()
    repeat(N) {
        val (x, y) = readLine().split(" ").map { it.toLong() }
        map[y - x * K] = (map[y - x * K] ?: 0L) + 1L
    }

    var res = 0L
    map.forEach { (_, v) ->
        res += v * (v - 1) / 2
    }

    println(res * 2L)
}