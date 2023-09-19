class Solution {

    var rowSize = 0
    var colSize = 0
    var res = Int.MAX_VALUE
    
    lateinit var start: Array<IntArray>
    lateinit var end: Array<IntArray>
    
    fun isSame(arr1: Array<IntArray>, arr2: Array<IntArray>): Boolean {
        var same = true
        repeat(rowSize) { i ->
            arr1[i].forEachIndexed { j, it ->
                if (it != arr2[i][j]) {
                    same = false
                    return@repeat
                }
            }   
        }
        
        return same
    }
    
    fun solve(line: Int, cnt: Int) {
        if (cnt >= res || line >= rowSize + colSize) return
        
        if (isSame(start, end)) {
            res = res.coerceAtMost(cnt)
            // return
        }
        solve(line + 1, cnt)
        
        if (line < rowSize) {
            repeat(colSize) {
                start[line][it] = if (start[line][it] == 0) 1 else 0
            }

            if (isSame(start, end)) {
                res = res.coerceAtMost(cnt + 1)
                // return
            }
            solve(line + 1, cnt + 1)
            
            repeat(colSize) {
                start[line][it] = if (start[line][it] == 0) 1 else 0
            }
        } else {
            repeat(rowSize) {
                start[it][line - rowSize] = if (start[it][line - rowSize] == 0) 1 else 0
            }

            if (isSame(start, end)) {
                res = res.coerceAtMost(cnt + 1)
                // return
            }
            solve(line + 1, cnt + 1)
            
            repeat(rowSize) {
                start[it][line - rowSize] = if (start[it][line - rowSize] == 0) 1 else 0
            }
        }
    }
    
    fun solution(beginning: Array<IntArray>, target: Array<IntArray>): Int {
        start = beginning
        end = target
        
        rowSize = beginning.size
        colSize = beginning[0].size
        
        if (isSame(start, end)) res = 0 else solve(0, 0)
        
        return if (res == Int.MAX_VALUE) -1 else res
    }
}