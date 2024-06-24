#include <iostream>
#include <cstring>
using namespace std;

main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    string S, P;
    cin >> S >> P;

    int cnt = 0; // 문자열 붙인 횟수

    int PMAX = P.length();
    int SMAX = S.length();
    // i가 P의 인덱스 역할
    for (int i = 0; i < PMAX;)
    {
        // 연산 한번 시작을 표현
        cnt++;

        // 최대 길이의 같은 문자열을 기록할 len
        int len = 0;

        // S문자열을 첫째자리부터 시작하면서 최대 길이의 같은 문자열 찾기
        for (int j = 0; j < SMAX; j++)
        {
            int wordCnt = 0;
            // S의 시작부터 P의 현재 위치부터 같은 구간의 길이 구하기
            while (S[j + wordCnt] == P[i + wordCnt])
            {
                wordCnt++;
            }
            // 최대값 갱신
            len = max(len, wordCnt);
        }
        i += len;
    }

    cout << cnt;
}