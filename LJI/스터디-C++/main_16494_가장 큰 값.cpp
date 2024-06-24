#include <iostream>
#include <vector>

#define INF 1000000000
using namespace std;
int N, M;

void Per(int Cnt, int Idx, int &Answer, vector<bool> &Check, const vector<vector<int>> &DP)
{
    // 구역 M개 나누어서 합들 구하기
    if (Cnt == M - 1)
    {
        int Start = 1;
        int End;
        int Sum = 0;
        for (int i = 1; i < N; i++)
        {
            if (Check[i])
            {
                End = i;
                Sum += DP[Start][End];
                Start = End + 1;
            }
        }

        Sum += DP[Start][N];

        Answer = max(Answer, Sum);
        return;
    }

    // 구역 나눌 위치 선정 M-1개
    for (int i = Idx; i < N; i++)
    {
        Check[i] = true;
        Per(Cnt + 1, i + 1, Answer, Check, DP);
        Check[i] = false;
    }
}

// 특정 start-> end 까지 중 가장 최적의 거리는 start 와 end 사이의 누적합들로 구할 수 있을 것
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> M;

    vector<int> Vec(N + 1);

    for (int i = 1; i <= N; i++)
    {
        cin >> Vec[i];
    }

    // i-> j 까지의 누적합 계산
    vector<vector<int>> Sum(N + 1, vector<int>(N + 1, 0));

    for (int i = 1; i <= N; i++)
    {
        for (int j = i; j <= N; j++)
        {
            Sum[i][j] = Sum[i][j - 1] + Vec[j];
        }
    }

    // i->j 까지 각 최소값 계산
    // i~j 사이의 Sum 값 중 큰 값이 와야한다
    vector<vector<int>> DP(N + 1, vector<int>(N + 1, -INF));

    for (int i = 1; i <= N; i++)
    {
        for (int j = i; j <= N; j++)
        {
            for (int s = i; s <= j; s++)
            {
                for (int e = s; e <= j; e++)
                {
                    DP[i][j] = max(DP[i][j], Sum[s][e]);
                }
            }
        }
    }

    // 구역을 M개로 나누고 그 구역 DP중 최고값 오면 된다.
    vector<bool> Check(N + 1, false);
    int Answer = -INF;
    Per(0, 1, Answer, Check, DP);
    cout << Answer;
}
