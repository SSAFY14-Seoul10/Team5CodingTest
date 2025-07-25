import sys
input = sys.stdin.readline

n = int(input())
formula = list(input().strip())

if n < 4:
    print(eval(''.join(formula)))
    exit()

# 왼쪽부터 순서대로 계산 (괄호는 괄호 내부)
"""
연속된 숫자 두개 뽑기 --> (n//4)개, (n//4 - 1)개, (n//4 - 2)개, ... 1개, 0개
백트래킹을 이용하여 해당하는 개수만큼 연속된 숫자 뽑아내기
"""
nl = [] # num_list
ol = [] # operator_list
max_hap = -float('inf') # 최대값 (음수가 최대값을 수 있다는 점!!)
# 숫자는 nl에, 연산자는 ol에 저장
for f in formula:
    if f.isdigit():
        nl.append(f)
    else:
        ol.append(f)

def sol(ans, v, idx):
    global max_hap # 결과의 최대값
    global max_cp # 사용해야하는 괄호의 개수
    if len(ans) == max_cp:
        j = 1
        hap = nl[0]
        while j < len(nl):
            if j in ans: # 괄호가 있는 경우
                tmp = str(eval(nl[j] + ol[j] + nl[j+1])) # 괄호 속 먼저 계산
                hap = str(eval(hap + ol[j-1] + tmp))
                j += 1 # j+1을 이미 계산 했으므로 다음으로
            else: # 괄호가 없는 경우
                hap = str(eval(hap + ol[j-1] + nl[j]))
            j += 1
        max_hap = max(int(hap), max_hap)
        return
    # 여기부터 다시 하기..
    for i in range (idx, len(nl) - 1): # idx + 1부터 탐색해서 중복 제거
        if not v[i] and not v[i+1]: # 연속된 두 개가 방문된 적 없을 때
            v[i], v[i+1] = True, True # 연속된 두 수를 사용
            ans.add(i) # i만 저장하면 계산할 때는 i+1까지 호출하면 됨
            sol(ans, v, i)
            ans.remove(i)
            v[i], v[i+1] = False, False

for max_cp in range (n//4 + 1):
    v = [False] * (len(nl))
    sol(set(), v, 0)

print(max_hap)