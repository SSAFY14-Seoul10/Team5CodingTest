#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

long long N;
long long ret=-987654321;
vector<long long> num;
vector<char> op;

long long cal_(long long a, long long b, char t) {

	if (t == '+') {
		return a + b;
	}
	else if (t == '-') {
		return a - b;
	}
	else {
		return a * b;
	}
}

void go_(long long idx, long long sum) {

	if (idx == N / 2) {
		ret = max(ret, sum);
		return;
	}

	go_(idx + 1, cal_(sum, num[idx + 1], op[idx]));

	if (idx + 2 <= N / 2) {
		long long temp = cal_(num[idx + 1], num[idx + 2], op[idx + 1]);
		go_(idx + 2, cal_(sum, temp, op[idx]));
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	cin >> N;

	string temp;
	cin >> temp;
	for (long long i = 0; i < N; i++) {
		if (temp[i] == '+' or temp[i] == '-' or temp[i] == '*') {
			op.push_back(temp[i]);
		}
		else {
			num.push_back(temp[i] - '0');
		}
	}

	go_(0, num[0]);

	cout << ret << "\n";

	return 0;
}