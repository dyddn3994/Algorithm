import sys
from sys import stdin
sys.setrecursionlimit(100001)
N = int(stdin.readline())

memo = [0 for _ in range(100001)]

def f(n):
    if n == 1:
        return 3
    elif n == 2:
        return 7
    if memo[n] != 0:
        return memo[n]
    memo[n] = (f(n-1)*2 + f(n-2)) % 9901
    return memo[n]

print(f(N))