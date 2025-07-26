package SSAFY.codingTest.week1;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class NQueen {

  static int n;
  static int t;
  static int[][] map;   //처음 boolean 동시에 지나가는 경우 존재 int로 바꿈
  static int result;
  static int[] dy = {-1, 0, 1};


  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    t = Integer.parseInt(br.readLine());
    for (int i = 0; i < t; i++) {
      result = 0;
      n = Integer.parseInt(br.readLine());
      map = new int[n][n];
      solution(0);
      System.out.println("#"+(i+1)+" "+result);
    }

  }

  /**
   *
   * @param x 현재 줄
   * 퀸은 각 줄에 하나씩 존재
   * 각 줄에 가능한 곳에 퀸 위치후 지나갈 수 있는 곳 표시
   */
  static void solution(int x) {
    if(x == n) {
      result++;
      return;
    }
    for (int i = 0; i < n; i++) {
      if(isValid(x, i)) {   //가능 여부 확인
        execute(x, i);      //지나가는 곳 표시
        solution(x+1);  //다음줄
        restore(x, i);    //원복
      }
    }

  }

  static boolean isValid(int x, int y) {
    if(map[x][y] == 0) return true;
    return false;
  }
  static void execute(int x, int y) {
    for (int i = 0; i < 3; i++) {
      int nx = x;
      int ny = y;
      while(nx < n && ny >= 0 && ny <n) {
        map[nx][ny]++;
        nx++;
        ny += dy[i];
      }
    }
  }
  static void restore(int x, int y) {
    for (int i = 0; i < 3; i++) {
      int nx = x;
      int ny = y;
      while(nx < n && ny >= 0 && ny <n) {
        map[nx][ny]--;
        nx++;
        ny += dy[i];
      }
    }
  }

}
