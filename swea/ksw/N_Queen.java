package swea.ksw;

import java.util.Scanner;

public class N_Queen {
	static int n;
	static int cnt;
	static int[][] arr;

    //대각선 탐색을 위한 배열
	static int[] dx = {1,1,-1,-1};
	static int[] dy = {-1,1,1,-1};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		
		for(int s = 1;s<=t;s++) {
			n = sc.nextInt();
			cnt = 0;
			arr = new int[n][n];
			backtracking(0);
			
			sb.append("#"+s+" "+cnt+"\n");
		}
		sc.close();
		System.out.println(sb.toString());
	}
	static boolean possible(int x, int y) {
		boolean p = true;
		//상하좌우 겹치는지 확인
		for(int i = 0;i<n;i++) {
			if(arr[i][y]==1||arr[x][i]==1) {
				p = false;
				break;
			}
		}
		//대각선 확인
		for(int i = 0;i<4;i++) {
			int cx = x+dx[i];
			int cy = y+dy[i];
			while(cx>=0&&cy>=0&&cx<n&&cy<n&&p) {
				if(arr[cx][cy]==1) p = false;
				cx+=dx[i];
				cy+=dy[i];
			}
		}
		return p;
	}
	static void backtracking(int dep) {
        //마지막 행까지 가능한 경우라면 cnt++
		if(dep==n) {
			cnt++;
			return;
		}
		for(int i = 0;i<n;i++) {
            //dep행, i열에 퀸을 배치 할 수 있다면
            //해당 자리에 퀸을 배치하고 다음 행으로 이동
			if(possible(dep,i)) {
				arr[dep][i] = 1;
				backtracking(dep+1);
				arr[dep][i] = 0;
			}
		}
	}
}
