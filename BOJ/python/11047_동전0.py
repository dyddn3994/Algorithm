# Greedy
N, K = map(int, input().split())
A = []
for _ in range(N):
    inp = int(input())
    A.append(inp)

A.sort(reverse=True)

cnt = 0
for i in A:
    cnt += K // i
    K %= i

print(cnt)