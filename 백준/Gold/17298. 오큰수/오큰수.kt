import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    val nums = readLine().split(" ").map { it.toInt() }

    val bigNumStack = Stack<Int>()
    val resStack = Stack<Int>()
    for (i in nums.size - 1 downTo 0) {
        while (bigNumStack.isNotEmpty()) {
            if (bigNumStack.peek() > nums[i]) break
            bigNumStack.pop()
        }

        if (bigNumStack.isEmpty()) resStack.add(-1)
        else resStack.add(bigNumStack.peek())

        bigNumStack.add(nums[i])
    }

    val sb = StringBuilder()
    while (resStack.isNotEmpty()) {
        sb.append(resStack.pop()).append(" ")
    }

    println(sb)
}