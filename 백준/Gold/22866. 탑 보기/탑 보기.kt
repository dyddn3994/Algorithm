import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    val buildings = readLine().split(" ").map { it.toInt() }

    val higherBuildingCnt = IntArray(N)
    val higherBuildingNum = IntArray(N)

    val rHigherBuildingCnt = IntArray(N)
    val stack = Stack<Int>()
    for (i in N - 1 downTo 0) {
        while (stack.isNotEmpty()) {
            if (buildings[stack.peek()] > buildings[i]) break
            stack.pop()
        }

        if (stack.isNotEmpty()) {
            rHigherBuildingCnt[i] = rHigherBuildingCnt[stack.peek()] + 1
            higherBuildingNum[i] = stack.peek()
        }

        higherBuildingCnt[i] = rHigherBuildingCnt[i]
        stack.add(i)
    }

    stack.clear()
    val lHigherBuildingCnt = IntArray(N)
    buildings.forEachIndexed { idx, it ->
        while (stack.isNotEmpty()) {
            if (buildings[stack.peek()] > it) break
            stack.pop()
        }

        if (stack.isNotEmpty()) {
            lHigherBuildingCnt[idx] = lHigherBuildingCnt[stack.peek()] + 1
            if (higherBuildingCnt[idx] == 0 || higherBuildingNum[idx] - idx >= idx - stack.peek()) higherBuildingNum[idx] = stack.peek()
        }

        higherBuildingCnt[idx] += lHigherBuildingCnt[idx]
        stack.add(idx)
    }

    val sb = StringBuilder()
    for (i in 0 until N) {
        sb.append(higherBuildingCnt[i]).append(" ")
        if (higherBuildingCnt[i] > 0) sb.append(higherBuildingNum[i] + 1)
        sb.append("\n")
    }
    println(sb)
}