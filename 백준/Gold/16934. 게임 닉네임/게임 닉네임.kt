fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    val sb = StringBuilder()

    val tree = arrayListOf<Node>()
    val sameNameMap = hashMapOf<String, Int>()

    for (k in 0 until N) {
        val name = readLine()
        if (sameNameMap.containsKey(name)) {
            sameNameMap[name] = (sameNameMap[name] ?: 0) + 1
            val nameWithNum = name + sameNameMap[name]
            sb.append(nameWithNum).append("\n")

            continue
        }

        var treeList = tree
        var i = 0
        while (i < name.length) {
            var j = 0
            while (j < treeList.size) {
                if (treeList[j].str == name[i]) {
                    break
                }
                j++
            }

            if (j < treeList.size) {
                treeList = treeList[j].list
                i++

                if (i == name.length) sb.append(name).append("\n")
                continue
            }
            else {
                val nickname = name.substring(0, i + 1)
                sb.append(nickname).append("\n")

                while (i < name.length) {
                    val newNode = Node(name[i++], arrayListOf())
                    treeList.add(newNode)
                    treeList = newNode.list
                }
                break
            }
        }
        sameNameMap[name] = 1
    }

    print(sb)
}

data class Node (
    var str: Char,
    var list: ArrayList<Node>
)