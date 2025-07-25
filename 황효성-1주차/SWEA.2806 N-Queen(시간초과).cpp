#include <iostream>

using namespace std;

int T, N;
int ret_Cnt;
int dir[8][2] = { {0,1},{1,0},{0,-1},{-1,0},{-1,1},{1,1},{1,-1},{-1,-1} };
int visited[14][14];

void display_() {
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cout << visited[i][j] << " ";
		}
		cout << "\n";
	}
	cout << "\n";
}

void draw_(int x, int y, int num) {

	int dx, dy;

	visited[x][y] += num;
	for (int i = 0; i < 8; i++) {

		dx = x + dir[i][0];
		dy = y + dir[i][1];

		while (dx >= 0 and dx < N and dy >= 0 and dy < N) {
			visited[dx][dy] += num;
			dx = dx + dir[i][0];
			dy = dy + dir[i][1];
		}
	}
}

void go_(int curr) {

	if (curr == N) {
		ret_Cnt += 1;
		return;
	}

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {

			if (visited[i][j] == 0) {
				draw_(i, j, 1);
				go_(curr + 1);
				draw_(i, j, -1);
			}
		}
	}
}
int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	cin >> N;

	go_(0);
	cout << ret_Cnt << "\n";

	return 0;
}