import java.util.*;
import java.io.*;

class Main {
    static int n;
    static int result;
    static int[] col;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int tc=1;tc<=t;tc++){
            n = Integer.parseInt(br.readLine());
            result = 0;

            // 배열의 가장 첫 인덱스에 queen이 들어갈 행을 1부터 n까지 넣는다.
            for(int i=1;i<=n;i++){
                col = new int[n+1];
                col[1] = i;
                queens(1);
            }

            sb.append("#"+tc+" "+result).append("\n");
        }
        System.out.println(sb);
    }

    public static void queens(int idx){
        //배열에 들어간 queen의 행이 적절하다면
        if(isPossible(idx)){

            //마지막까지 채워진 경우 값을 증가 시키고 종료
            if(idx == n) {
                result++;
                return;
            }

            //다음 인덱스에 queen이 들어갈 행을 1부터 n까지 넣는다.
            for(int i=1;i<=n;i++){
                col[idx+1] = i;
                queens(idx+1);
            }
        }
    }

    public static boolean isPossible(int idx){
        for(int i=1;i<idx;i++){
            // 퀸이 들어간 행이 중복되거나 퀸이 서로 대각선인 경우
            if(col[i] == col[idx] || (idx-i == Math.abs(col[idx] - col[i])))
                return false;
        }
        return true;
    }

}
