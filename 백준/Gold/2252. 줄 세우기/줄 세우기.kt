import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (N, M) = readLine().split(" ").map { it.toInt() }

    val frontCnt = IntArray(N + 1)
    val backList = Array(N + 1) { arrayListOf<Int>() }

    repeat(M) {
        val (A, B) = readLine().split(" ").map { it.toInt() }
        frontCnt[B]++
        backList[A].add(B)
    }

    val resList = arrayListOf<Int>()

    val queue = ArrayDeque<Int>()
    for (i in 1 .. N) {
        if (frontCnt[i] == 0) queue.add(i)
    }

    while (queue.isNotEmpty()) {
        val node = queue.poll()
        resList.add(node)

        backList[node].forEach {
            if (--frontCnt[it] == 0) queue.add(it)
        }
    }

    val sb = StringBuilder()
    resList.forEach {
        sb.append(it).append(" ")
    }
    println(sb)
}