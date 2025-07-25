#include <iostream>
#include <string>

using namespace std;

int T, H, W;
char arr[24][24];
int N;
string command;

int dir[4][2] = { {0,1},{1,0},{0,-1},{-1,0} };
int currX, currY, currD;

char display_Dir_() {

	if (currD == 0) {
		return '>';
	}
	else if (currD == 1) {
		return 'v';
	}
	else if (currD == 2) {
		return '<';
	}
	else {
		return '^';
	}
}

void display_() {

	for (int i = 0; i < H; i++) {
		for (int j = 0; j < W; j++) {

			if (i == currX and j == currY) {
				cout << display_Dir_();
			}
			else {
				cout << arr[i][j];
			}
		}
		cout << "\n";
	}
}

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

void shoot_() {

	int dx, dy;

	dx = currX + dir[currD][0];
	dy = currY + dir[currD][1];

	while (dx >= 0 and dx < H and dy >= 0 and dy < W) {

		if (arr[dx][dy] == '*') {
			arr[dx][dy] = '.';
			break;
		}

		if (arr[dx][dy] == '#') {
			break;
		}
		dx = dx + dir[currD][0];
		dy = dy + dir[currD][1];
	}
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
			shoot_();
		}
	}
}
int main() {


	cin >> T;

	for (int test = 1; test <= T; test++) {

		cin >> H >> W;
		for (int i = 0; i < H; i++) {

			string data;
			cin >> data;
			for (int j = 0; j < W; j++) {
				arr[i][j]=data[j];

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

		go_();
		
		cout << "#" << test << " ";
		display_();
	}

	return 0;
}