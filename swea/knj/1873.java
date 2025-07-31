import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws Exception {
        System.setIn(Solution.class.getClassLoader().getResourceAsStream("input.txt"));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 위:0 아래:1 왼쪽:2 오른쪽:3
        int directionX[] = {-1, 1, 0, 0};
        int directionY[] = {0, 0, -1, 1};

        int T = Integer.parseInt(bf.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            st = new StringTokenizer(bf.readLine());
            int H = Integer.parseInt(st.nextToken());  // 맵 높이
            int W = Integer.parseInt(st.nextToken());  // 맵 너비
            char map[][] = new char[H][W]; // 맵
            for (int i = 0; i < H; i++) {
                map[i] = bf.readLine().toCharArray();
            }
            int N = Integer.parseInt(bf.readLine()); // 인풋 길이
            char userInput[] = bf.readLine().toCharArray(); // 유저 인풋


            int currentX = 0;
            int currentY = 0;
            int currentDirection = 1;

            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    switch (map[i][j]) {
                        case '^': {
                            currentX = i;
                            currentY = j;
                            currentDirection = 0;
                            break;
                        }
                        case 'v': {
                            currentX = i;
                            currentY = j;
                            currentDirection = 1;
                            break;
                        }
                        case '<': {
                            currentX = i;
                            currentY = j;
                            currentDirection = 2;
                            break;
                        }
                        case '>': {
                            currentX = i;
                            currentY = j;
                            currentDirection = 3;
                            break;
                        }
                    }
                }
            }

            for (char input : userInput) {
                switch (input) {
                    case 'U': {
                        int dx = currentX + directionX[0];
                        int dy = currentY + directionY[0];
                        if (0 <= dx && dx < H && 0 <= dy && dy < W && map[dx][dy] == '.') {
                            map[currentX][currentY] = '.';
                            currentX = dx;
                            currentY = dy;
                            map[dx][dy] = '^';
                            currentDirection = 0;
                        } else {
                            map[currentX][currentY] = '^';
                            currentDirection = 0;
                        }
                        break;
                    }
                    case 'D': {
                        int dx = currentX + directionX[1];
                        int dy = currentY + directionY[1];
                        if (0 <= dx && dx < H && 0 <= dy && dy < W && map[dx][dy] == '.') {
                            map[currentX][currentY] = '.';
                            currentX = dx;
                            currentY = dy;
                            map[dx][dy] = 'v';
                            currentDirection = 1;
                        } else {
                            map[currentX][currentY] = 'v';
                            currentDirection = 1;
                        }
                        break;
                    }
                    case 'L': {
                        int dx = currentX + directionX[2];
                        int dy = currentY + directionY[2];
                        if (0 <= dx && dx < H && 0 <= dy && dy < W && map[dx][dy] == '.') {
                            map[currentX][currentY] = '.';
                            currentX = dx;
                            currentY = dy;
                            map[dx][dy] = '<';
                            currentDirection = 2;
                        } else {
                            map[currentX][currentY] = '<';
                            currentDirection = 2;
                        }
                        break;
                    }
                    case 'R': {
                        int dx = currentX + directionX[3];
                        int dy = currentY + directionY[3];
                        if (0 <= dx && dx < H && 0 <= dy && dy < W && map[dx][dy] == '.') {
                            map[currentX][currentY] = '.';
                            currentX = dx;
                            currentY = dy;
                            map[dx][dy] = '>';
                            currentDirection = 3;
                        } else {
                            map[currentX][currentY] = '>';
                            currentDirection = 3;
                        }
                        break;
                    }
                    case 'S': {
                        int dx = currentX + directionX[currentDirection];
                        int dy = currentY + directionY[currentDirection];
                        while (0 <= dx && dx < H && 0 <= dy && dy < W) {
                            if (map[dx][dy] == '*') {
                                map[dx][dy] = '.';
                                break;
                            }
                            if (map[dx][dy] == '#') {
                                break;
                            }
                            dx += directionX[currentDirection];
                            dy += directionY[currentDirection];

                        }
                        break;
                    }
                }
            }

            System.out.printf("#%d ", testCase);
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    System.out.print(map[i][j]);
                }
                System.out.println(" ");
            }


        }// TestCase 반복문 끝!
    }
}
