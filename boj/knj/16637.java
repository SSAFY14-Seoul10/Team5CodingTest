import java.util.Scanner;

public class Main {
	static int max = Integer.MIN_VALUE;
	static int N;
	static char[] expression;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		N = Integer.parseInt(scanner.nextLine());
		expression = scanner.nextLine().toCharArray();

		dfs(2, expression[0] - '0');
		System.out.println(max);
	}

	static void dfs(int index, int value) {
		if (index >= N) {
			max = Math.max(max, value);
			return;
		}

		// 괄호 안치고 바로 계산
		int num = expression[index] - '0';
		char ops = expression[index - 1];
		int noBracket = calculate(value, ops, num);
		dfs(index + 2, noBracket);

		// 괄호 사용(가능하면~)
		if (index + 2 < N) {
			int num1 = expression[index] - '0';
			char ops2 = expression[index + 1];
			int num2 = expression[index + 2] - '0';

			int bracketResult = calculate(num1, ops2, num2);
			int withBracket = calculate(value, ops, bracketResult);
			dfs(index + 4, withBracket);
		}
	}

	public static int calculate(int a, char ops, int b) {
		switch (ops) {
			case '+': {
				return a + b;
			}
			case '-': {
				return a - b;
			}
			case '*': {
				return a * b;
			}
			default:
				throw new IllegalStateException("Unexpected value: " + ops);
		}
	}
}
