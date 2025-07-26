package SSAFY.codingTest.week1;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BattleField {

  static int t;
  static int h;
  static int w;
  static int n;
  static char[][] map;
  static char[] cmd;
  static int curX;
  static int curY;


  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    t = Integer.parseInt(br.readLine());

    for (int i = 0; i < t; i++) {
      String[] input = br.readLine().split(" ");
      h = Integer.parseInt(input[0]);
      w = Integer.parseInt(input[1]);

      map = new char[h][w];

      for (int j = 0; j < h; j++) {
        map[j] = br.readLine().toCharArray();
        for (int k = 0; k < w; k++) {
          if(map[j][k] != '.' && map[j][k] != '*' && map[j][k] != '#' && map[j][k] != '-' ) {
            curX = j;
            curY = k;
          }
        }
      }

      n = Integer.parseInt(br.readLine());
      cmd = br.readLine().toCharArray();

      findLastLoc();
      System.out.print("#" + (i+1) + " ");
      for (int j = 0; j < h; j++) {
        for (int k = 0; k < w; k++) {
          System.out.print(map[j][k]);
        }
        System.out.println();
      }
    }

  }

  static void findLastLoc() {
    for (int i = 0; i < n; i++) {   //순서대로 command 실행
      move(cmd[i]);
    }
  }

  static void move(char c) {    //각 command 별 실행
    if(c == 'U') {
      map[curX][curY] = '^';
      if(curX -1 >= 0 && map[curX-1][curY] == '.') {
        map[curX][curY] = '.';
        map[curX-1][curY] = '^';
        curX -= 1;
      }
    } else if(c == 'D') {
      map[curX][curY] = 'v';
      if(curX + 1 < h && map[curX+1][curY] == '.') {
        map[curX][curY] = '.';
        map[curX+1][curY] = 'v';
        curX += 1;
      }
    } else if(c == 'L') {
      map[curX][curY] = '<';
      if(curY -1 >= 0 && map[curX][curY - 1] == '.') {
        map[curX][curY] = '.';
        map[curX][curY-1] = '<';
        curY -= 1;
      }
    } else if(c == 'R') {
      map[curX][curY] = '>';
      if(curY + 1 < w  && map[curX][curY+1] == '.') {
        map[curX][curY] = '.';
        map[curX][curY+1] = '>';
        curY += 1;
      }
    } else if(c == 'S') {
      if(map[curX][curY] == '^') {
        int destination = curX;
        while(destination >= 0 && map[destination][curY] != '*' && map[destination][curY] != '#') {
          destination--;
        }
        if(destination < 0)return;
        else if(map[destination][curY] == '*') {
          map[destination][curY] = '.';
          return;
        }
      } else if(map[curX][curY] == 'v') {
        int destination = curX;
        while(destination < h && map[destination][curY] != '*' && map[destination][curY] != '#') {
          destination++;
        }
        if(destination == h)return;
        else if(map[destination][curY] == '*') {
          map[destination][curY] = '.';
          return;
        }
      } else if(map[curX][curY] == '<') {
        int destination = curY;
        while(destination >= 0 && map[curX][destination] != '*' && map[curX][destination] != '#') {
          destination--;
        }
        if(destination < 0)return;
        else if(map[curX][destination] == '*') {
          map[curX][destination] = '.';
          return;
        }
      } else if(map[curX][curY] == '>') {
        int destination = curY;
        while(destination < w && map[curX][destination] != '*' && map[curX][destination] != '#') {
          destination++;
        }
        if(destination == w)return;
        else if(map[curX][destination] == '*') {
          map[curX][destination] = '.';
          return;
        }
      }
    }
  }

}

