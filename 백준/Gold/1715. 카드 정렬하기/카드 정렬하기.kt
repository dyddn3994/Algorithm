import java.util.PriorityQueue

fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()

    val pq = PriorityQueue<Int>()
    repeat(N) {
        pq.add(readLine().toInt())
    }

    var cnt = 0
    while (pq.size >= 2) {
        val sum = pq.poll() + pq.poll()
        cnt += sum
        pq.add(sum)
    }

    println(cnt)
}