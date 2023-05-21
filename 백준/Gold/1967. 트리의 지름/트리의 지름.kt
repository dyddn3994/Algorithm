
var n = 0
lateinit var list: Array<ArrayList<Pair<Int, Int>>>
lateinit var visited: BooleanArray

var maxNode = 0
var maxDist = 0

fun dfs(node: Int, dist: Int) {
    visited[node] = true
    if (dist > maxDist) {
        maxNode = node
        maxDist = dist
    }

    list[node].forEach {
        if (!visited[it.first]) {
            dfs(it.first, dist + it.second)
        }
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    list = Array(n + 1) { arrayListOf() }
    visited = BooleanArray(n + 1)
    repeat(n - 1) {
        val (u, v, d) = readLine().split(" ").map { it.toInt() }
        list[u].add(Pair(v, d))
        list[v].add(Pair(u, d))
    }

    dfs(1, 0)

    visited.fill(false)
    maxDist = 0
    dfs(maxNode, 0)

    print(maxDist)
}