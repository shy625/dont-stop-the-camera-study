#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int N, M, K;

// 각 캐릭터마다 15분동안 얻을 수 있는 최대 골드 효율 계산
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> M >> K;

    vector<long long> Damage(N);
    for (int i = 0; i < N; i++)
    {
        cin >> Damage[i];
    }
    // 무조건 높은 데미지로 잡는게 유리
    sort(Damage.begin(), Damage.end(), greater<long long>());

    vector<pair<long long, int>> Boss;
    Boss.push_back(make_pair(0, 0));
    for (int i = 0; i < K; i++)
    {
        long long P;
        int Q;
        cin >> P >> Q;
        Boss.push_back(make_pair(P, Q));
    }

    // M 개의 캐릭터 레이드 시작
    // 15분-> 900초
    long long answer = 0;
    for (int i = 0; i < M; i++)
    {
        // 이번 캐릭터의 초당 데미지
        long long DPS = Damage[i];

        vector<vector<int>> Gold(K + 1, vector<int>(901, 0));

        for (int i = 1; i <= K; i++)
        {
            // 이번 보스를 잡는데 걸리는 시간
            long long TTK = Boss[i].first / DPS;
            // 나누어 떨어지지 않으면 한대 더 쳐야한다.
            if (Boss[i].first % DPS > 0)
            {
                TTK++;
            }

            for (int j = 1; j <= 900; j++)
            {
                // 먼저 잡지 않는 경우로 업데이트
                Gold[i][j] = Gold[i - 1][j];

                // 만약 이번 보스를 잡을 수 있다면 잡는 경우와 비교
                if (j - TTK >= 0)
                {
                    Gold[i][j] = max(Gold[i][j], Gold[i - 1][j - TTK] + Boss[i].second);
                }
            }
        }

        answer += Gold[K][900];
    }

    cout << answer;
}