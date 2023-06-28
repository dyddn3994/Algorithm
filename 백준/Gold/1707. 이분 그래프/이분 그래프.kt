
var K = 0
var V = 0
var E = 0

lateinit var adjList: Array<ArrayList<Int>>
lateinit var colArr: IntArray
var res = true

fun solve(node: Int) {
    adjList[node].forEach {
        if (colArr[it] > 0 && colArr[it] == colArr[node]) {
            res = false
            return
        }

        if (colArr[it] == 0) {
            if (colArr[node] == 1) colArr[it] = 2
            else colArr[it] = 1
            solve(it)
        }

        if (!res) return
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val sb = StringBuilder()
    K = readLine().toInt()
    repeat(K) {
        readLine().split(" ").apply {
            V = this[0].toInt()
            E = this[1].toInt()
        }
        adjList = Array(V + 1) { arrayListOf() }
        colArr = IntArray(V + 1)
        repeat(E) {
            val (u, v) = readLine().split(" ").map { it.toInt() }
            adjList[u].add(v)
            adjList[v].add(u)
        }

        res = true

        for (i in 1 .. V) {
            if (colArr[i] > 0) continue
            
            colArr[i] = 1
            solve(i)
        }

        if (res) sb.append("YES")
        else sb.append("NO")
        sb.append("\n")
    }

    print(sb)
}