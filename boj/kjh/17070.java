import java.util.*;
import java.io.*;
class Point{
    int a;
    int b;
    int c;
}
class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st;
        int[][] map = new int[n][n];
        Point[][] record = new Point[n][n];
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                record[i][j] = new Point();
            }
        }

        record[0][1].a=1;
        for(int i=2;i<n;i++){
            if(map[0][i] != 1)
                record[0][i].a = record[0][i-1].a;
        }

        for(int i=1;i<n;i++){
            for(int j=1;j<n;j++){
                //대각선으로 파이프가 들어오는 경우
                if(map[i-1][j] != 1 && map[i][j-1] != 1 && map[i][j] != 1){
                    record[i][j].c += record[i-1][j-1].a;
                    record[i][j].c += record[i-1][j-1].b;
                    record[i][j].c += record[i-1][j-1].c;
                }

                //그 외
                if(map[i][j] != 1){
                    record[i][j].a += record[i][j-1].a + record[i][j-1].c;
                    record[i][j].b += record[i-1][j].b + record[i-1][j].c;
                }
            }
        }

        System.out.println(record[n-1][n-1].c+record[n-1][n-1].b+record[n-1][n-1].a);
    }
}
