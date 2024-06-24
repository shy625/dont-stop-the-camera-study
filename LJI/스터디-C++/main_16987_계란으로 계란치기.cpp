#include <iostream>
#include <vector>
using namespace std;

// dfs로 갑시다
// 0 이하인 경우 깨진다

int N, answer;

//(계란 인덱스, 계란 리스트, 깨진 계란 수)
void dfs(int idx, vector<pair<int, int>> eggs, int cnt)
{
    if (idx == N)
    {
        answer = max(answer, cnt);
        return;
    }

    if (eggs[idx].first <= 0)
    { // 이번 계란이 이미 깨져있다면 그냥 넘기기
        dfs(idx + 1, eggs, cnt);
    }
    else
    {
        bool bCrash = false;
        // 깰 수 있는 상대 계란 찾아보기
        for (int i = 0; i < N; i++)
        {
            // 자기 자신 넘기고
            if (i == idx)
                continue;

            if (eggs[i].first > 0)
            { // 깨기 가능
                // 한번이라도 깼다면
                if (!bCrash)
                    bCrash = true;
                // 깨는 경우
                eggs[i].first -= eggs[idx].second;
                eggs[idx].first -= eggs[i].second;
                int newCnt = cnt;
                // 만약 손에 든 계란이 깨졌다면 cnt+1;
                if (eggs[idx].first <= 0)
                {
                    newCnt++;
                }
                // 만약 상대 계란이 깨졌으면 cnt+1;
                if(eggs[i].first<=0){
                    newCnt++;
                }
                dfs(idx + 1, eggs, newCnt);

                // 깨지 않은 경우로 복구
                eggs[i].first += eggs[idx].second;
                eggs[idx].first += eggs[i].second;
            }
        }
        // 깰 수 있는 계란이 없다면?
        if (!bCrash)
        {
            dfs(idx + 1, eggs, cnt);
        }
    }
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N;
    vector<pair<int, int>> eggs;

    for (int i = 0; i < N; i++)
    {
        int S, W;
        cin >> S >> W;

        eggs.push_back(make_pair(S, W));
    }
    answer = 0;
    dfs(0, eggs, 0);

    cout << answer;
}