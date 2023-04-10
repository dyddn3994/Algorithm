import heapq

N = int(input())
M = int(input())
INF = 0x7fffffff

arr = [[] for i in range(N+1)]
for i in range(M):
    u,v,w = map(int, input().split())
    arr[u].append((v,w))

ans = [INF for i in range(N+1)]
start, finish = map(int, input().split())

def dijkstra():
    qData = []
    ans[start] = 0

    heapq.heappush(qData, (0, start))

    while qData:
        cost, pos = heapq.heappop(qData)

        if ans[pos] < cost:
            continue

        for c in arr[pos]:
            w = c[0]
            wCost = c[1]
            if ans[w] > ans[pos] + wCost:
                ans[w] = ans[pos] + wCost
                heapq.heappush(qData, (ans[w], w))
dijkstra()
print(ans[finish])
