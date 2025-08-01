'''
.	평지(전차가 들어갈 수 있다.)
*	벽돌로 만들어진 벽
#	강철로 만들어진 벽
-	물(전차는 들어갈 수 없다.)
^	위쪽을 바라보는 전차(아래는 평지이다.)
v	아래쪽을 바라보는 전차(아래는 평지이다.)
<	왼쪽을 바라보는 전차(아래는 평지이다.)
>	오른쪽을 바라보는 전차(아래는 평지이다.)

U	Up : 전차가 바라보는 방향을 위쪽으로 바꾸고, 한 칸 위의 칸이 평지라면 위 그 칸으로 이동한다.
D	Down : 전차가 바라보는 방향을 아래쪽으로 바꾸고, 한 칸 아래의 칸이 평지라면 그 칸으로 이동한다.
L	Left : 전차가 바라보는 방향을 왼쪽으로 바꾸고, 한 칸 왼쪽의 칸이 평지라면 그 칸으로 이동한다.
R	Right : 전차가 바라보는 방향을 오른쪽으로 바꾸고, 한 칸 오른쪽의 칸이 평지라면 그 칸으로 이동한다.
S	Shoot : 전차가 현재 바라보고 있는 방향으로 포탄을 발사한다.
'''
for tc in range (1, int(input())+1):
    h, w = map(int, input().split()) # 높이, 너비
    arr = []
    for i in range (h):
        arr.append(list(input().strip()))
        for j in range (w):
            if arr[i][j] in ('^', 'v', '>', '<'):
                position = (i, j) # 현재 전차의 위치

    n = int(input())
    string = input().strip()

    for s in string:
        x, y = position
        if s == 'U':
            # 전차가 바라보는 방향을 위로 바꾸고 위가 평지라면 이동
            arr[x][y] = '^'
            nx, ny = x - 1, y
            if 0 <= nx < h and 0 <= ny < w and arr[nx][ny] == '.': # 다음 위치가 . 이면 이동
                position = (nx, ny) # position을 기반으로 전차의 위치를 판별하므로 position 변경
                arr[nx][ny] = '^' # 다음 위치로 전차 이동
                arr[x][y] = '.' # 이전 위치 평지로 변경

        elif s == 'D':
            # 전차가 바라보는 방향을 아래로 바꾸고 아래가 평지라면 이동
            arr[x][y] = 'v'
            nx, ny = x + 1, y
            if 0 <= nx < h and 0 <= ny < w and arr[nx][ny] == '.':
                position = (nx, ny)
                arr[nx][ny] = 'v'
                arr[x][y] = '.'
        
        elif s == 'L':
            # 전차가 바라보는 방향을 왼쪽으로 바꾸고 왼쪽이 평지라면 이동
            arr[x][y] = '<'
            nx, ny = x, y - 1
            if 0 <= nx < h and 0 <= ny < w and arr[nx][ny] == '.':
                position = (nx, ny)
                arr[nx][ny] = '<'
                arr[x][y] = '.'
        
        elif s == 'R':
            # 전차가 바라보는 방향을 오른쪽으로 바꾸고 오른쪽이 평지라면 이동
            arr[x][y] = '>'
            nx, ny = x, y + 1
            if 0 <= nx < h and 0 <= ny < w and arr[nx][ny] == '.':
                position = (nx, ny)
                arr[nx][ny] = '>'
                arr[x][y] = '.'
        
        else: # shoot
            # *나 # 만날 때까지 직진
            if arr[x][y] == '^':
                for cx in range (x, -1, -1): # 현재 위치부터 위로 한칸씩 이동
                    if arr[cx][y] == '*':
                        arr[cx][y] = '.'
                        break
                    elif arr[cx][y] == '#': # 강철은 뚫을 수 없음
                        break
            
            elif arr[x][y] == 'v':
                for cx in range (x, h): # 현재 위치부터 아래로 한칸씩 이동
                    if arr[cx][y] == '*':
                        arr[cx][y] = '.'
                        break
                    elif arr[cx][y] == '#':
                        break

            elif arr[x][y] == '<':
                for cy in range (y, -1, -1):
                    if arr[x][cy] == '*':
                        arr[x][cy] = '.'
                        break
                    elif arr[x][cy] == '#':
                        break
            
            else:
                for cy in range (y, w):
                    if arr[x][cy] == '*':
                        arr[x][cy] = '.'
                        break
                    elif arr[x][cy] == '#':
                        break

    print(f"#{tc}", end = ' ')
    for i in range (h):
        print(''.join(arr[i]))#