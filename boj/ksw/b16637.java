package boj.ksw;

import java.util.*;

public class b16637{
	static int n;
	static int max = Integer.MIN_VALUE;
	static List<Integer> nums;
	static List<Character> oper;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		sc.nextLine();
		String line = sc.nextLine();
		sc.close();
		nums = new ArrayList<>();
		oper = new ArrayList<>();
		
		for(int i = 0;i<n;i++) {
			char c = line.charAt(i);
			if(c-'0'>=0) nums.add(c-'0');
			else oper.add(c);
		}
		
		recur(0,nums.get(0));
		System.out.print(max);
		
		
	}
	static void recur(int cnt, int value) {
		//연산자를 다 사용하면 종료
		if(cnt==n/2) {
			max = Math.max(max, value);
			return;
		}
		
		// 괄호를 사용하지 않는 경우 
		int disuse = cal(oper.get(cnt),value,nums.get(cnt+1));
		recur(cnt+1,disuse);
		
		// (cnt+1)과 (cnt+2)에 괄호를 사용하는 경우
		if(cnt<n/2-1) {
			int use = cal(oper.get(cnt+1),nums.get(cnt+1),nums.get(cnt+2));
			//기존 값과 합해줌
			int total = cal(oper.get(cnt),value,use);
			recur(cnt+2,total);
		}
	}
	
	static int cal(char op,int a, int b) {
		if(op=='+') return a+b;
		else if(op=='-') return a-b;
		else return a*b;
	}
}
