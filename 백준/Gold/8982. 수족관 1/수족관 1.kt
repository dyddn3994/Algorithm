fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    val floorList = IntArray(40_000)
    var by = 0
    for (i in 0 until N) {
        val (y, x) = readLine().split(" ").map { it.toInt() }
        if (y == by) continue

        for (j in by until y) {
            floorList[j] = x
        }

        by = y
    }
    val K = readLine().toInt()
    val holeList = BooleanArray(40_000)
    repeat(K) {
        val (y1, x1, y2, x2) = readLine().split(" ").map { it.toInt() }
        for (i in y1 until y2) {
            holeList[i] = true
        }
    }

    val maxWidth = by
    val waterList = IntArray(maxWidth)
    // 왼쪽부터 구멍 높이 기준으로 채워지는 물 확인
    var holeHeight = 0
    for (i in 0 until maxWidth) {
        if (holeList[i]) {
            waterList[i] = 0
            holeHeight = floorList[i]
        }
        else {
            if (holeHeight < floorList[i]) {
                waterList[i] = floorList[i] - holeHeight
            }
            else {
                waterList[i] = 0
                holeHeight = floorList[i]
            }
        }
    }

    // 오른쪽부터 ,,
    holeHeight = 0
    for (i in maxWidth - 1 downTo 0) {
        if (holeList[i]) {
            waterList[i] = 0
            holeHeight = floorList[i]
        }
        else {
            if (holeHeight < floorList[i]) {
                waterList[i] = waterList[i].coerceAtMost(floorList[i] - holeHeight)
            }
            else {
                waterList[i] = 0
                holeHeight = floorList[i]
            }
        }
    }

    // 넓이 계산
    var area = 0
    waterList.forEach {
        area += it
    }

    print(area)
}