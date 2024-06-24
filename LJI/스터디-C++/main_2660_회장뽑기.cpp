#include <iostream>
#include <vector>
using namespace std;

// 다른 친구들과 상대 거리들 중 최대값이 가장 짧은 사람이 회장 가능
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    int N;
    cin >> N;

    vector<vector<int>> Floyd(N + 1, vector<int>(N + 1, 1000));
    for (int i = 0; i <= N; i++)
    {
        Floyd[i][i] = 0;
    }

    while (true)
    {
        int a, b;
        cin >> a >> b;
        if (a == -1 && b == -1)
            break;

        Floyd[a][b] = 1;
        Floyd[b][a] = 1;
    }

    for (int k = 1; k <= N; k++)
    {
        for (int i = 1; i <= N; i++)
        {
            for (int j = 1; j <= N; j++)
            {
                if (Floyd[i][j] > Floyd[i][k] + Floyd[k][j])
                {
                    Floyd[i][j] = Floyd[i][k] + Floyd[k][j];
                }
            }
        }
    }

    /// 정답 찾기
    int answer = 1000;
    vector<int> answerList;

    for (int i = 1; i <= N; i++)
    {
        int score = 0;
        for (int j = 1; j <= N; j++)
        {
            if (i == j)
                continue;
            score = max(score, Floyd[i][j]);
        }

        if (answer == score)
        {
            answerList.push_back(i);
        }
        else if (answer > score)
        {
            answer = score;
            answerList.clear();
            answerList.push_back(i);
        }
    }

    cout << answer << ' ' << answerList.size() << '\n';
    for (int num : answerList)
    {
        cout << num << ' ';
    }
}