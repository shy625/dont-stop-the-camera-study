#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int N, L, R, X;
vector<int> list;
int answer;
void dfs(int cnt, int pro)
{
    if (cnt == N)
    {
        int diffSum = 0;       // 난이도 합
        int proCnt = 0;        // 문제수
        int maxDiff = 0;       // 최대 난이도
        int minDiff = 1000001; // 최소 난이도

        for (int i = 0; i < N; i++)
        {
            // 해당 문제를 골랐다면
            if (pro & 1 << i)
            {
                proCnt++;
                maxDiff = max(maxDiff, list[i]);
                minDiff = min(minDiff, list[i]);
                diffSum += list[i];
            }
        }

        if (proCnt >= 2 && diffSum >= L && diffSum <= R && maxDiff - minDiff >= X)
        {
            answer++;
        }
        return;
    }

    // cnt 문제를 넣은 경우
    dfs(cnt + 1, pro | 1 << cnt);
    // cnt 문제를 넣지 않는 경우
    dfs(cnt + 1, pro);
}

main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> L >> R >> X;

    for (int i = 0; i < N; i++)
    {
        int tmp;
        cin >> tmp;

        list.push_back(tmp);
    }

    answer = 0;
    dfs(0, 0);

    cout << answer;
}