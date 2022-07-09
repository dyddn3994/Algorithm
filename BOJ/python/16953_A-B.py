# Greedy
A, B = map(int, input().split())
cnt = 1

while B > A:
    if B % 2 == 0:
        B //= 2
    elif str(B)[-1] == '1':
        B //= 10
    else:
        break
    cnt += 1
    
if B == A:
    print(cnt)
else:
    print("-1")