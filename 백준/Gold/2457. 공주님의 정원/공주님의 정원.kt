
fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    val flowers = Array<Flower?>(N) { null }
    val bloom = Array(13) { Array(32) { arrayListOf<Int>() } }
    repeat(N) { idx ->
        val (sm, sd, em, ed) = readLine().split(" ").map { it.toInt() }
        flowers[idx] = Flower(sm, sd, em, ed)
        for (i in sm .. em) {
            val startDay =
                if (i == sm)
                    sd
                else
                    1
            val lastDay =
                if (i == em)
                    ed - 1
                else {
                    when (i) {
                        2 -> 28
                        4, 6, 9, 11 -> 30
                        else -> 31
                    }
                }
            for (j in startDay .. lastDay) {
                bloom[i][j].add(idx)
            }
        }
    }

    val set = hashSetOf<Int>()

    var month = 3
    var day = 1
    var isValid = true
    while (month < 11 || (month == 11 && day <= 30)) {
        if (bloom[month][day].size == 0) {
            isValid = false
            break
        }

        var maxIdx = bloom[month][day][0]
        for (i in 1 until bloom[month][day].size) {
            val nIdx = bloom[month][day][i]
            if (flowers[maxIdx]!!.endMonth < flowers[nIdx]!!.endMonth
                || (flowers[maxIdx]!!.endMonth == flowers[nIdx]!!.endMonth && flowers[maxIdx]!!.endDay < flowers[nIdx]!!.endDay)) {
                maxIdx = nIdx
            }
        }

        set.add(maxIdx)
        month = flowers[maxIdx]!!.endMonth
        day = flowers[maxIdx]!!.endDay
    }

    if (!isValid) println(0)
    else println(set.size)
}

data class Flower (
    val startMonth: Int,
    val startDay: Int,
    val endMonth: Int,
    val endDay: Int
)