# Greedy
def solution(food_times, k):
    sum = 0
    for i in range(len(food_times)):
        sum += food_times[i]
    if sum <= k:
        return -1
    
    eatIdx = -1
    for _ in range(k):
        eatIdx = (eatIdx+1) % len(food_times)
        while food_times[eatIdx] == 0:
            eatIdx = (eatIdx+1) % len(food_times)
        food_times[eatIdx] -= 1
        
    eatIdx = (eatIdx+1) % len(food_times)
    while food_times[eatIdx] == 0:
        eatIdx = (eatIdx+1) % len(food_times)
        
    return eatIdx + 1
