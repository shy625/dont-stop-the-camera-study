#include <iostream>
#include <vector>
using namespace std;
int N, M;
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> M;

    vector<vector<int>> Floyd(N + 1, vector<int>(N + 1, 1000000));

    int A, B, T;

    for (int i = 0; i < M; i++)
    {
        cin >> A >> B >> T;

        // 일방향 길
        Floyd[A][B] = T;
    }

    // 플로이드 워셜
    for (int k = 1; k <= N; k++)
    {
        for (int i = 1; i <= N; i++)
        {
            if (k == i)
                continue;
            for (int j = 1; j <= N; j++)
            {
                if (j == i)
                {
                    Floyd[i][j] = 0;
                    Floyd[j][i] = 0;
                    continue;
                }
                if (k == j)
                    continue;

                if (Floyd[i][j] > Floyd[i][k] + Floyd[k][j])
                {
                    Floyd[i][j] = Floyd[i][k] + Floyd[k][j];
                }
            }
        }
    }

    // 친구들 위치 정보
    int K;
    cin >> K;
    vector<int> Friend(K);
    for (int i = 0; i < K; i++)
    {
        cin >> Friend[i];
    }

    // 도시 탐색
    int answerTime = INT32_MAX;
    vector<int> answerList;
    for (int i = 1; i <= N; i++)
    {
        int Time = 0;
        bool IsPossible = true;

        for (int k = 0; k < K; k++)
        {
            int FriendCity = Friend[k];

            if (Floyd[FriendCity][i] == 1000000 || Floyd[i][FriendCity] == 1000000)
            {
                IsPossible = false;
                break;
            }
            Time = max(Floyd[FriendCity][i] + Floyd[i][FriendCity], Time);
        }

        // 이번 도시는 불가
        if (!IsPossible)
            continue;

        // 가능한 도시인 경우
        if (answerTime == Time)
        {
            answerList.push_back(i);
        }
        else if (answerTime > Time)
        {
            answerTime = Time;
            answerList.clear();
            answerList.push_back(i);
        }
    }

    for (int city : answerList)
    {
        cout << city << ' ';
    }
}