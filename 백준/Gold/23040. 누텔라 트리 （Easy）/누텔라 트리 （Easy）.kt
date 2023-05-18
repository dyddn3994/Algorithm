
var N = 0
lateinit var list: Array<ArrayList<Int>> // 인접 리스트
lateinit var visited: BooleanArray // 방문 여부
lateinit var isRed: BooleanArray // 빨강이면 true, 검정이면 false
lateinit var parents: IntArray

var res = 0L

fun getParent(node: Int): Int {
    if (node != parents[node]) parents[node] = getParent(parents[node])
    return parents[node]
}

fun union(n1: Int, n2: Int) {
    parents[getParent(n2)] = getParent(n1)
}

fun dfs(node: Int): Long {
    var cnt = 1L
    visited[node] = true

    list[node].forEach {
        if (isRed[it] && !visited[it]) {
            union(node, it)
            cnt += dfs(it)
        }
    }

    return cnt
}

fun main() = with(System.`in`.bufferedReader()) {
    N = readLine().toInt()
    list = Array(N + 1) { arrayListOf() }
    visited = BooleanArray(N + 1)
    isRed = BooleanArray(N + 1)

    repeat(N - 1) {
        val (u, v) = readLine().split(" ").map { it.toInt() }
        list[u].add(v)
        list[v].add(u)
    }

    val colorInput = readLine()
    for (i in 0 until N) {
        isRed[i + 1] = colorInput[i] == 'R'
    }
    // 입력 끝

    parents = IntArray(N + 1)
    parents.forEachIndexed { index, _ ->
        parents[index] = index
    }

    // 닿아있는 빨강으로부터 연결된 빨강 개수 찾기
    val redCntList = LongArray(N + 1)
    for (i in 1..N) {
        if (!isRed[i] || visited[i]) continue

        redCntList[i] = 1L
        visited[i] = true
        list[i].forEach {
            if (isRed[it] && !visited[it]) {
                union(i, it)
                redCntList[i] += dfs(it)
            }
        }
    }

    // 검정 노드 기준으로 닿아있는 빨강들의 빨강 수 더하기
    for (i in 1..N) {
        if (isRed[i]) continue

        list[i].forEach {
            res += redCntList[getParent(it)]
        }
    }

    print(res)
}