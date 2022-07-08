# Greedy
inp = int(input())
changes = [500, 100, 50, 10, 5, 1]
money = 1000 - inp
cnt = 0

for i in changes:
    cnt += money // i
    money %= i

print(cnt)