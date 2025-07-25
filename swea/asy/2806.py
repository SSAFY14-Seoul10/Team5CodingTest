n = int(input())

board = [[False] * n for _ in range (n)]
attack = [[0] * n for _ in range (n)]

def set_queen(x, y):
  board[x][y] = True
  for i in range (n):
    attack[x][i] += 1 # 세로
    attack[i][y] += 1 # 가로
    # 대각선
    if x + i < n and y + i < n:
      attack[x+i][y+i] += 1
    if x - i >= 0 and y - i >= 0:
      attack[x-i][y-i] += 1
    if x + i < n and y - i >= 0:
      attack[x+i][y-i] += 1
    if x - i >= 0 and y + i < n:
      attack[x-i][y+i] += 1

def remove_queen(x, y):
  board[x][y] = False
  for i in range (n):
    attack[x][i] -= 1 # 세로
    attack[i][y] -= 1 # 가로
    # 대각선
    if x + i < n and y + i < n:
      attack[x+i][y+i] -= 1
    if x - i >= 0 and y - i >= 0:
      attack[x-i][y-i] -= 1
    if x + i < n and y - i >= 0:
      attack[x+i][y-i] -= 1
    if x - i >= 0 and y + i < n:
      attack[x-i][y+i] -= 1

def sol(x):
  global cnt
  if x == n: # 어차피 한 줄에 퀸이 하나씩 들어가므로
    cnt += 1
    return
  for y in range (n):
    if attack[x][y] == 0: # 현재 위치가 공격받지 않는 곳인지 검사
      set_queen(x, y)
      sol(x + 1) # 다음줄 검사
      remove_queen(x, y)

cnt = 0
sol(0)
print(cnt)