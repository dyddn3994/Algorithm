
lateinit var parents: IntArray
lateinit var notLeafs: BooleanArray

// true:
fun findParent(node: Int): Boolean {
    if (parents[node] == -2) return false
    if (notLeafs[node]) return true

    notLeafs[node] = true
    if (parents[node] == -1) return true

    val inTree = findParent(parents[node])
    return if (inTree) {
        true
    }
    else {
        parents[node] = -2
        false
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    notLeafs = BooleanArray(N)
    parents = readLine().split(" ").map { it.toInt() }.toIntArray()
    val d = readLine().toInt()
    parents[d] = -2
    notLeafs[d] = true

    for (node in 0 until N) {
        if (parents[node] == -2 || parents[node] == -1 || notLeafs[node]) continue

        val inTree = findParent(parents[node])
        if (!inTree) {
            parents[node] = -2
            notLeafs[node] = true
        }
    }

    var cnt = 0
    notLeafs.forEach { if(!it) cnt++ }
    print(cnt)
}