class Solution {
    int[] nums;
    int res;
    int targ;
    
    void dfs(int idx, int val) {
        if (idx == nums.length) {
            if (val == targ) res++;
            return;
        }
        
        dfs(idx + 1, val + nums[idx]);
        dfs(idx + 1, val - nums[idx]);
    }
    
    public int solution(int[] numbers, int target) {
        nums = numbers;
        res = 0;
        targ = target;
        
        dfs(0, 0);
        
        return res;
    }
}