import kotlin.math.abs

fun main() = with(System.`in`.bufferedReader()) {
    val (N, M) = readLine().split(" ").map { it.toInt() }
    val books = readLine().split(" ").map { it.toInt() }

    val plusPos = arrayListOf<Int>()
    val minusPos = arrayListOf<Int>()
    books.forEach {
        if (it > 0) plusPos.add(it)
        else minusPos.add(it)
    }

    plusPos.sort()
    minusPos.sort()

    val maxDist =
        if (plusPos.size == 0)
            abs(minusPos[0])
        else if (minusPos.size == 0)
            plusPos[plusPos.size - 1]
        else
            plusPos[plusPos.size - 1].coerceAtLeast(abs(minusPos[0]))

    var step = 0
    var i = plusPos.size - 1
    while (i >= 0) {
        step += plusPos[i] * 2
        i -= M
    }

    i = 0
    while (i < minusPos.size) {
        step += minusPos[i] * 2 * -1
        i += M
    }

    step -= maxDist

    println(step)
}