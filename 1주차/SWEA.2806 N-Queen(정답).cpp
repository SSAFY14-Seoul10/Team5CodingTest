#include <iostream>
#include <cmath>
#include <cstring>

using namespace std;

int T,N;
int ret_Cnt;
int visited[14]; // visited[0]=2 ¸é 0Çà 2¿­¿¡ Äý

bool is_check(int x, int y) {

	for (int i = 0; i < x; i++) {
		if ((x - i) == abs(y - visited[i]) or y == visited[i]) {
			return false;
		}
	}
	return true;
}
void go_(int curr) {

	if (curr == N) {
		ret_Cnt += 1;
		return;
	}

	for (int i = 0; i < N; i++) {
		
		if (is_check(curr,i)) {
			visited[curr] = i;
			go_(curr + 1);
		}
	}
}

void init_() {
	ret_Cnt = 0;
	memset(visited, 0, sizeof(visited));
}
int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	cin >> T;

	for (int test = 1; test <= T; test++) {

		cin >> N;
		
		init_();
		go_(0);

		cout << "#" << test << " " << ret_Cnt << "\n";
	}
	return 0;
}