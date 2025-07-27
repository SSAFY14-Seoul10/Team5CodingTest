import java.util.*;
import java.io.*;

class Main {
    static int n;
    static int max = Integer.MIN_VALUE;
    static char[] arr;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new char[n];
        
        String str = br.readLine();
        for(int i=0;i<n;i++)
            arr[i] = str.charAt(i);

        recv(arr[0]-'0',0);

        System.out.println(max);
    }

    public static void recv(int val,int idx){
        if(idx >= n-1){
            max = Math.max(max,val);
            return;
        }

        //괄호를 추가하지 않는 경우
        int nextNum = arr[idx+2] - '0';
        int nextVal = calc(val,nextNum,arr[idx+1]);
        recv(nextVal,idx+2);

        //괄호를 추가하는 경우
        if(idx+4<n) {
            int tempVal = calc(nextNum,arr[idx+4]-'0',arr[idx+3]);
            nextVal = calc(val,tempVal,arr[idx+1]);
            recv(nextVal,idx+4);
        }
    }

    public static int calc(int n1,int n2,char op){
        int res=0;
        switch(op){
            case '+':
                res = n1+n2;
                break;
            case '-':
                res = n1-n2;
                break;
            case '*':
                res = n1*n2;
                break;
        }
        return res;
    }
}
