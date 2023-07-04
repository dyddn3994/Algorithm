import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (N, M, L) = readLine().split(" ").map { it.toInt() }
    val pq = PriorityQueue<Pos>()

    lateinit var restStopList: List<Int>
    if (N != 0) {
        restStopList = readLine().split(" ").map { it.toInt() }.sorted()
        pq.add(Pos(restStopList[0], false, 0))
        for (i in 0 until N - 1) {
            pq.add(Pos(restStopList[i + 1] - restStopList[i], false, 0))
        }
        pq.add(Pos(L - restStopList.last(), false, 0))
    }
    else {
        pq.add(Pos(L, false, 0))
    }

    repeat(M) {
        val pos = pq.poll()
        if (pos.isBuilt) {
            pos.cnt++
            pq.add(pos)
        }
        else {
            pos.isBuilt = true
            pos.cnt++
            pq.add(pos)
        }

    }

    val res = pq.poll()
    print(res.dist / (res.cnt + 1) + if (res.dist % (res.cnt + 1) > 0) 1 else 0)
}

data class Pos (
    val dist: Int,
    var isBuilt: Boolean,
    var cnt: Int
): Comparable<Pos> {
    override fun compareTo(p2: Pos): Int {
        val d1 = this.dist / (this.cnt + 1) + if (this.dist % (this.cnt + 1) > 0) 1 else 0
        val d2 = p2.dist / (p2.cnt + 1) + if (p2.dist % (p2.cnt + 1) > 0) 1 else 0

        return d2 - d1
    }
}