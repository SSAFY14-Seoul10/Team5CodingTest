import java.util.*;
import java.io.*;
class Node{
    int row;
    int col;
    Node(int row,int col){
        this.row = row;
        this.col = col;
    }
}
class Main {
    static int h,w;
    static char[][] gameMap;
    static String operation;
    static int n;
    static int[] dRow = {-1,1,0,0};
    static int[] dCol = {0,0,-1,1};
    static int dir;
    static Map<Integer,Character> map = new HashMap<>();
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        map.put(0,'^');
        map.put(1,'v');
        map.put(2,'<');
        map.put(3,'>');

        for(int tc=1;tc<=t;tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            dir = -1;
            Node node = null;

            gameMap = new char[h][w];
            boolean flag = false;
            for(int i=0;i<h;i++){
                String str = br.readLine();
                for(int j=0;j<w;j++){
                    gameMap[i][j] = str.charAt(j);
                    if(gameMap[i][j] == '<' )
                        dir = 2;
                    else if(gameMap[i][j] == '>')
                        dir = 3;
                    else if(gameMap[i][j] == '^')
                        dir = 0;
                    else if(gameMap[i][j] == 'v')
                        dir = 1;

                    if(dir != -1 && !flag){
                        node = new Node(i,j);
                        flag = true;
                    }
                }
            }
            n = Integer.parseInt(br.readLine());
            operation = br.readLine();

            func(node);

            System.out.print("#"+tc+" ");
            for(char[] arr:gameMap){
                for(char c:arr){
                    System.out.print(c);
                }
                System.out.println();
            }
        }
    }

    public static void func(Node node){
        for(int i=0;i<n;i++){
            char op = operation.charAt(i);
            switch(op){
                case 'U':
                    dir = 0;
                    move(node);
                    break;
                case 'D':
                    dir = 1;
                    move(node);
                    break;
                case 'L':
                    dir = 2;
                    move(node);
                    break;
                case 'R':
                    dir = 3;
                    move(node);
                    break;
                case 'S':
                    shot(node);
                    break;
            }
        }
    }

    public static void move(Node node){
        gameMap[node.row][node.col] = '.';
        int nextRow = node.row + dRow[dir];
        int nextCol = node.col + dCol[dir];

        if(nextRow<0 || nextRow>=h || nextCol<0 || nextCol>=w) {
            gameMap[node.row][node.col] = map.get(dir);
            return;
        }

        if(gameMap[nextRow][nextCol] == '.'){
            node.row = nextRow;
            node.col = nextCol;
        }

        gameMap[node.row][node.col] = map.get(dir);
    }

    public static void shot(Node node){
        int row = node.row;
        int col = node.col;

        while(true){
            row += dRow[dir];
            col += dCol[dir];
            if(row<0 || row>=h || col<0 || col>=w)
                break;
            if(gameMap[row][col]=='*'){
                gameMap[row][col] = '.';
                break;
            }
            if(gameMap[row][col]=='#'){
                break;
            }
        }
    }
}
