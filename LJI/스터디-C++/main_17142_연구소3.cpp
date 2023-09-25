#include <iostream>
#include <vector>
#include <queue>
using namespace std;
int N, M, Size, All, answer;
int map[50][50];
int dr[] = {-1, 0, 1, 0};
int dc[] = {0, 1, 0, -1};
vector<pair<int, int>> virus;
vector<bool> virusV;
bool check(int r, int c)
{
    return r >= 0 && r < N && c >= 0 && c < N;
}

void VirusPick(int idx, int cnt)
{
    if (cnt == M)
    {
        // 전부 골랐으니 bfs하기
        int virusCnt = 0;
        bool v[N][N];
        fill(&v[0][0], &v[N - 1][N], false);
        // 시작 초기화
        queue<pair<int, int>> que;
        for (int i = 0; i < Size; i++)
        {
            if (virusV[i])
            {
                que.push(virus[i]);
                v[virus[i].first][virus[i].second] = true;
                // virusCnt++;
            }
        }
        int t = 0;
        while (!que.empty() && virusCnt != All)
        {
            int size = que.size();
            for (int i = 0; i < size; i++)
            {
                pair<int, int> cur = que.front();
                que.pop();
                int r = cur.first;
                int c = cur.second;
                for (int d = 0; d < 4; d++)
                {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if (!check(nr, nc))
                        continue;

                    if (map[nr][nc] != 1 && !v[nr][nc])
                    {
                        v[nr][nc] = true;
                        que.push(make_pair(nr, nc));
                        if (map[nr][nc] == 0)
                            virusCnt++;
                    }
                }
            }
            t++;
        }

        if (virusCnt == All)
        {
            answer = min(answer, t);
        }
        return;
    }

    for (int i = idx; i < Size; i++)
    {
        if (virusV[i] == true)
            continue;
        // 현재 요소 고르기
        virusV[i] = true;
        VirusPick(i, cnt + 1);
        virusV[i] = false;
    }
}

main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> M;
    All = 0;
    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < N; j++)
        {
            cin >> map[i][j];
            if (map[i][j] == 2)
            {
                virus.push_back(make_pair(i, j));
                virusV.push_back(false);
            }

            if (map[i][j] == 0)
            {
                All++;
            }
        }
    }

    // 바이러스 M개 만큼 뽑기
    Size = virus.size();
    answer = 1000000;
    VirusPick(0, 0);

    // cout << All << endl;
    if (answer == 1000000)
    {
        cout << "-1";
    }
    else
    {
        cout << answer;
    }
}