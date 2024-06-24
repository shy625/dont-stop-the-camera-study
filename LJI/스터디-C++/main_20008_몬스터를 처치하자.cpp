#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;
int N, HP;

main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> HP;

    vector<pair<int, int>> skills;
    int timeSum = 0, damageSum = 0;
    for (int i = 0; i < N; i++)
    {
        int a, b;
        cin >> a >> b;
        skills.push_back(make_pair(a, b));
        timeSum += a;
        damageSum += b;
    }
    sort(skills.begin(), skills.end());
    int answer = INT32_MAX;
    // 최대로 걸리는 시간
    int maxTime = timeSum * ((HP / damageSum) + 1);

    // 스킬 순서 구하기
    do
    {
        // 스킬 쿨마다 넣기
        vector<int> time(maxTime, 0);
        for (int i = 0; i < N; i++)
        {
            int idx = 0;
            while (idx < maxTime)
            {
                if (time[idx] == 0)
                {
                    time[idx] = skills[i].second;
                    idx += skills[i].first;
                }
                else
                {
                    idx++;
                }
            }
        }
        //  체력이 낮아지는 순간 계산
        int nowHP = HP;
        int turn = 0;
        while (true)
        {
            nowHP -= time[turn];

            if (nowHP <= 0)
            {
                answer = min(answer, turn + 1);
                break;
            }

            turn++;
        }

    } while (next_permutation(skills.begin(), skills.end()));

    cout << answer;
}