class Solution {
    fun solution(e: Int, starts: IntArray): IntArray {
        val cntArr = IntArray(e + 1) { 1 }
        for (i in 2..e) {
            var n = i
            while (n <= e) {
                cntArr[n]++
                n += i
            }
        }
        
        val nums = IntArray(e + 1)
        var minNum = e
        for (i in e downTo 1) {
            if (cntArr[i] >= cntArr[minNum]) minNum = i
            nums[i] = minNum
        }
        
        val res = IntArray(starts.size)
        starts.forEachIndexed { idx, it ->
            res[idx] = nums[it]
        }
        
        return res
    }
}