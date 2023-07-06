import java.util.*
import kotlin.math.max

fun main() = with(System.`in`.bufferedReader()) {
    val T = readLine().toInt()
    val sb = StringBuilder()
    repeat(T) {
        val k = readLine().toInt()
        val map = hashMapOf<Int, Int>()
        val minPQ = PriorityQueue<Int> ()
        val maxPQ = PriorityQueue<Int> { a, b -> b.compareTo(a)}

        repeat(k) {
            val (operator, operand) = readLine().split(" ")
            val num = operand.toInt()

            if (operator == "I") {
                map[num] = (map[num] ?: 0) + 1
                maxPQ.add(num)
                minPQ.add(num)
            }
            else if (operator == "D") {
                if (num == 1) {
                    while (maxPQ.isNotEmpty()) {
                        val n = maxPQ.poll()
                        if ((map[n] ?: 0) > 0) {
                            map[n] = (map[n] ?: 0) - 1
                            break
                        }
                    }
                }
                else if (num == -1) {
                    while (minPQ.isNotEmpty()) {
                        val n = minPQ.poll()
                        if ((map[n] ?: 0) > 0) {
                            map[n] = (map[n] ?: 0) - 1
                            break
                        }
                    }
                }
            }
        }

        var max = Int.MIN_VALUE
        var isNotEmpty = false
        while (maxPQ.isNotEmpty()) {
            val n = maxPQ.poll()
            if ((map[n] ?: 0) > 0) {
                max = n
                isNotEmpty = true
                break
            }
        }
        if (isNotEmpty) {
            sb.append(max).append(" ")
            var min = Int.MAX_VALUE
            while (minPQ.isNotEmpty()) {
                val n = minPQ.poll()
                if ((map[n] ?: 0) > 0) {
                    min = n
                    break
                }
            }
            sb.append(min).append("\n")
        }
        else {
            sb.append("EMPTY\n")
        }
    }

    println(sb)
}