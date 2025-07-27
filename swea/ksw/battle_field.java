package swea.ksw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class battle_field {
	static int n,m;
	static char[][] arr;
	static int x,y;
	
	//상하좌우 순서
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(br.readLine());
		
		for(int s =1;s<=t;s++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			arr = new char[n][m];
			x = 0;
			y = 0;
			for(int i = 0;i<n;i++) {
				String line =br.readLine();
				for(int j=  0;j<m;j++) {
					arr[i][j] = line.charAt(j);
                    //탱크 위치 x,y에 저장
					if(arr[i][j]=='<'||arr[i][j]=='>'||arr[i][j]=='^'||arr[i][j]=='v') {
						x = i;
						y = j;
					}
				}
			}
			
			int num = Integer.parseInt(br.readLine());
			String con = br.readLine();
			
			for(int i = 0;i<num;i++) {
				move(con.charAt(i));
			}
			
			sb.append("#"+s+" ");
			for(int i = 0;i<n;i++) {
				for(int j =0;j<m;j++) {
					sb.append(arr[i][j]);
				}
				sb.append("\n");
			}
		}
		System.out.println(sb.toString());
	}
	static void move(char c) {
        //만약 위라면
		if(c=='U') {
            //일단 위쪽 방향을 바라보도록(경계에 있을 경우를 방지)
			int cx = x+dx[0];
			arr[x][y] = '^';
			if(cx>=0) {
                //이동좌표가 평지라면 현재 위치를 평지, 이동한 위치에 전차 배치
				if(arr[cx][y]=='.') {
					arr[x][y] = '.';
					arr[cx][y] = '^';
					x = cx;
				}
			}
		}
        //이하 동일
		else if(c=='D') {
			int cx = x+dx[1];
			arr[x][y] = 'v';
			if(cx<n) {
				if(arr[cx][y]=='.') {
					arr[x][y] = '.';
					arr[cx][y] = 'v';
					x = cx;
				}
			}
		}
		else if(c=='L') {
			int cy = y+dy[2];
			arr[x][y] = '<';
			if(cy>=0) {
				if(arr[x][cy]=='.') {
					arr[x][y] = '.';
					arr[x][cy] = '<';
					y = cy;
				}
			}
		}
		else if(c=='R') {
			int cy = y+dy[3];
			arr[x][y] ='>';
			if(cy<m) {
				if(arr[x][cy]=='.') {
					arr[x][y] = '.';
					arr[x][cy] = '>';
					y = cy;
				}
			}
		}
		else {
            //포탄 발사일 경우
            //방향 설정
			int dir = 0;
			if(arr[x][y]=='^') dir= 0; 
			if(arr[x][y]=='v') dir= 1; 
			if(arr[x][y]=='<') dir= 2;
			if(arr[x][y]=='>') dir= 3;
			int cx = x+dx[dir];
			int cy = y+dy[dir];
            //좌표 끝까지 or 벽돌이나 강철을 만날 때까지 좌표이동
			while(cx>=0&&cy>=0&&cx<n&&cy<m) {
				if(arr[cx][cy]=='*'||arr[cx][cy]=='#') {
                    //만날 경우 벽돌이면 평지로 바꿈
					if(arr[cx][cy]=='*') arr[cx][cy]='.';
					break;
				}
                //탐색좌표 갱신
				cx+=dx[dir]; 
				cy+=dy[dir]; 
			}
		}
		
	}
}
