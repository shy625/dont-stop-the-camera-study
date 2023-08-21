#include <iostream>
#include <vector>
#include <queue>
using namespace std;

string S, T;
// 문자의 순서도 중요하네
// alphabet별로 몇번째 idx인지 체크하면서 묶어놓자

main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> S >> T;

    vector<queue<int>> alpha;
    for (int i = 0; i < 26; i++)
    {
        alpha.push_back(queue<int>());
    }

    // S의 문자들의 인덱스를 문자들의 큐에 넣어놓기
    int len = S.length();
    for (int i = 0; i < len; i++)
    {
        // 현재 문자의 큐에 현재 인덱스를 넣어준다
        alpha[S[i] - 'a'].push(i);
    }

    int answer = 0;
    // 문자열이 한번 완성될 때마다 answer+1;
    len = T.length();
    while (true)
    {
        // 문자열이 생성되는지 확인
        // idx값은 항상 현재 문자의 큐에서 꺼낸 값보다 작아야한다.
        // 현재 문자가 값이 더 작으면? 계속 que에서 꺼내보자
        bool bBreak = false;
        int idx = -1; // 시작은 -1;
        for (int i = 0; i < len; i++)
        {
            int alphaIdx = T[i] - 'a';

            // T의 현재 문자의 가장 적은 인덱스가 이전 문자 인덱스(idx)를 넘길때까지 큐에서 꺼내기
            while (!alpha[alphaIdx].empty() && idx > alpha[alphaIdx].front())
            {
                alpha[alphaIdx].pop();
            }

            // 큐가 비었다면 이전 문자를 이을 뒤에 문자가 없다
            if (alpha[alphaIdx].empty())
            {
                bBreak = true;
                break;
            }
            // 큐가 남아있다면 이전 문자의 다음으로 올 문자가 있다는 뜻
            else
            {
                idx = alpha[alphaIdx].front();
                alpha[alphaIdx].pop();
            }
        }
        if (bBreak)
            break;
        // 문자열 완성이 된 경우이므로 +1
        answer++;
    }
    cout << answer;
}