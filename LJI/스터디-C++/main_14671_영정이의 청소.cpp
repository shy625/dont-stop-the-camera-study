#include <iostream>
#include <vector>
#include <queue>
using namespace std;
int N, M, K;
// 현재 위치가 사라지는 독특한 구조
// 2턴 전과 상태가 똑같다면 반복상태라고 봐야할 것
// 이건 개수로 따져도 될 듯

int Dr[] = {-2, -2, -2, 0, 2, 2, 2, 0};
int Dc[] = {-2, 0, 2, 2, 2, 0, -2, -2};

bool Check(int R, int C)
{
    return R >= 0 && R < N && C >= 0 && C < M;
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> M >> K;
    queue<pair<int, int>> Que;
    int MoldCnt = 0;
    vector<vector<bool>> Visited(N, vector<bool>(M, false));
    for (int i = 0; i < K; i++)
    {
        int R, C;
        cin >> R >> C;
        Que.push(make_pair(R - 1, C - 1));
        Visited[R - 1][C - 1] = true;
        MoldCnt++;
    }

    // 특정 칸에 연동되서 퍼지는 곳은? 대각선 2번째 위치들
    // 이곳들 BFS해서 맵 다 차면 ㅇㅈ

    while (!Que.empty())
    {
        int R = Que.front().first;
        int C = Que.front().second;
        Que.pop();

        for (int d = 0; d < 8; d++)
        {
            int NR = R + Dr[d];
            int NC = C + Dc[d];

            if (Check(NR, NC) && !Visited[NR][NC])
            {
                MoldCnt++;
                Visited[NR][NC] = true;
                Que.push(make_pair(NR, NC));
            }
        }
    }

    if (MoldCnt == N * M)
    {
        cout << "YES";
    }
    else
    {
        cout << "NO";
    }
}