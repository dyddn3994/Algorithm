import java.util.*

var N = 0
var M = 0
var t = 0
lateinit var list: Array<ArrayList<Node>>

lateinit var visited: BooleanArray
var res = 0

fun prim(start: Int) {
    val pq = PriorityQueue<Node>()
    var cnt = 1
    visited[start] = true

    list[start].forEach {
        pq.add(it)
    }

    while (cnt < N) {
        val (num, cost) = pq.poll()
        if (visited[num]) continue
        visited[num] = true

        res += cost + (cnt - 1) * t

        list[num].forEach {
            if (!visited[it.num]) pq.add(it)
        }
        cnt++
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    readLine().split(" ").apply {
        N = this[0].toInt()
        M = this[1].toInt()
        t = this[2].toInt()
    }
    list = Array(N + 1) { arrayListOf() }
    repeat(M) {
        val (A, B, C) = readLine().split(" ").map { it.toInt() }
        list[A].add(Node(B, C))
        list[B].add(Node(A, C))
    }

    visited = BooleanArray(N + 1)

    prim(1)

    print(res)
}

data class Node (
    val num: Int,
    val cost: Int
) : Comparable<Node> {
    override fun compareTo(other: Node): Int {
        return this.cost - other.cost
    }
}