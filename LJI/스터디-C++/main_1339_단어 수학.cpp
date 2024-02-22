#include <iostream>
#include <vector>
#include <cmath>
#include <algorithm>
using namespace std;
int N;

// 알파벳별로 자리수값으로 더해놓기
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    cin >> N;

    vector<int> alphaVec(26, 0);

    for (int i = 0; i < N; i++)
    {
        string str;
        cin >> str;

        float tmp = pow(10, (str.length() - 1));
        int val = (int)tmp;
        for (int j = 0; j < str.length(); j++)
        {
            alphaVec[str[j] - 'A'] += val;
            val /= 10;
        }
    }

    // alphaVec을 내림차순 정렬해서 9부터 할당
    sort(alphaVec.begin(), alphaVec.end(), greater<int>());
    int answer = 0;
    for (int i = 0; i < 10; i++)
    {
        answer += alphaVec[i] * (9 - i);
    }

    cout << answer;
}