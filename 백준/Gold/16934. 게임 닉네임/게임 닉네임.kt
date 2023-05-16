fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    val sb = StringBuilder()

    val tree = arrayListOf<Node>()
    val sameNameMap = hashMapOf<String, Int>()

    for (k in 0 until N) {
        val name = readLine()

        if (sameNameMap.containsKey(name)) {
            // 이미 입력된 같은 이름이 있을 경우 숫자 붙이기
            sameNameMap[name] = (sameNameMap[name] ?: 0) + 1
            sb.append(name + sameNameMap[name]).append("\n")

            continue
        }

        var treeList = tree // 현재 검색할 리스트
        var i = 0
        while (i < name.length) {
            // 입력된 이름에서 char 하나씩 확인
            var j = 0
            while (j < treeList.size) {
                // 현재 검색하는 리스트에서 동일한 문자가 있으면 break
                if (treeList[j].str == name[i]) {
                    break
                }
                j++
            }

            if (j < treeList.size) {
                // 위에서 break되었으면 검색 리스트 새로 설정하고 continue
                treeList = treeList[j].list
                i++

                // 입력된 문자의 끝일 경우 검색 멈춤, 출력 추가
                if (i == name.length) sb.append(name).append("\n")
                continue
            }
            else {
                // 현재까지 입력되지 않은 문자일 경우 출력 추가
                val nickname = name.substring(0, i + 1)
                sb.append(nickname).append("\n")

                while (i < name.length) {
                    // 남은 문자 리스트 추가
                    val newNode = Node(name[i++], arrayListOf())
                    treeList.add(newNode)
                    treeList = newNode.list
                }
                break
            }
        }
        
        // 동일한 이름 hashmap 추가
        sameNameMap[name] = 1
    }

    print(sb)
}

data class Node (
    var str: Char,
    var list: ArrayList<Node>
)