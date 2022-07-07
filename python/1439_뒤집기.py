# Greedy

inp = input()
idxNum = inp[0]
cnt = 0

for i in range(1, len(inp)):
    if inp[i] != inp[i-1]:
        cnt += 1

res = int((cnt+1)/2)

print(res)