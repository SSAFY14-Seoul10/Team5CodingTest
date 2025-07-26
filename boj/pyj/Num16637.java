package SSAFY.codingTest.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Num16637 {


  static int n;
  static char[] execution;
  static long result = Integer.MIN_VALUE;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    n = Integer.parseInt(br.readLine());
    execution = br.readLine().toCharArray();

    DFS(0, 0);

    System.out.println(result);


  }

  /**
   *
   * @param x 현재 위치
   * @param sum 지금까지의 합
   * case1 - 괄호를 추가하기 않고 앞에서부터 순서대로 계산
   * case2 - 뒤 식을 먼저 계산(괄호 추가) 후 계산
   *
   */

  static void DFS(int x, long sum) {
    if(x == 0) {
      sum = execution[0]-'0';
    }
    if(x >= n-1) {
      if(result < sum) {
        result = sum;
      }
      return;
    }
    DFS(x+2, calculate(sum, execution[x+2] - '0', execution[x+1]));   //case1
    if(x < n-3) {
      long cal = calculate(execution[x+2] - '0', execution[x+4] - '0', execution[x+3]);   //case2
      DFS(x+4, calculate(sum,cal, execution[x+1]));
    }
  }

  static long calculate(long x, long y, char c) {
    if(c == '+') {
      return x+y;
    }else if(c == '-') {
      return x-y;
    } else {
      return x*y;
    }
  }

}

