#include <iostream>
#include <string>

using namespace std;

int T, H, W;
char arr[24][24];
int N;
string command;

int dir[4][2] = { {0,1},{1,0},{0,-1},{-1,0} };
int currX, currY, currD;

void move_() {

	int dx, dy;
	dx = currX + dir[currD][0];
	dy = currY + dir[currD][1];

	if (dx >= 0 or dx < H or dy >= 0 or dy < W) {
		if (arr[dx][dy] == '.') {
			currX = dx;
			currY = dy;
		}
	}
}

void up_() {

	currD = 3;
	move_();
}

void down_() {
	currD = 1;
	move_();
}

void left_() {
	currD = 2;
	move_();
}

void right_() {
	currD = 0;
	move_();
}

void go_() {

	for (int i = 0; i < N; i++) {

		if (command[i] == 'U') {
			up_();
		}
		else if (command[i] == 'D') {
			down_();
		}
		else if (command[i] == 'L') {
			left_();
		}
		else if (command[i] == 'R') {
			right_();
		}
		else {

		}
	}
}
int main() {

	cin >> H >> W;
	for (int i = 0; i < H; i++) {
		for (int j = 0; j < W; j++) {
			cin >> arr[i][j];

			if (arr[i][j] == '^') {
				currX = i;
				currY = j;
				currD = 3;
				arr[i][j] = '.';
			}

			if (arr[i][j] == 'v') {
				currX = i;
				currY = j;
				currD = 1;
				arr[i][j] = '.';
			}

			if (arr[i][j] == '<') {
				currX = i;
				currY = j;
				currD = 2;
				arr[i][j] = '.';
			}

			if (arr[i][j] == '>') {
				currX = i;
				currY = j;
				currD = 0;
				arr[i][j] = '.';
			}
		}
	}
	cin >> N;
	cin >> command;
}