#include <iostream>
#include <queue>
using namespace std;

int N, M;
int dr[] = {-1, 0, 1, 0};
int dc[] = {0, 1, 0, -1};

// 중복체크를 어떻게 할지가 중요
// 2^5승이 한계이니 3중 배열로 해도 될 것 같다
// 가로 세로 반대로 줌
main()
{
    ios_base::sync_with_stdio(false);
    cout.tie(0);
    cin.tie(0);

    cin >> M >> N;

    int map[N][M];
    int idx = 1;        // 물건 index
    int sR, sC, eR, eC; // 시작 위치와 종료위치
    // 맵에 정보들 입력 받기
    for (int i = 0; i < N; i++)
    {
        string str;
        cin >> str;
        for (int j = 0; j < M; j++)
        {
            if (str[j] == '#')
            { // 벽
                map[i][j] = -1;
            }
            else if (str[j] == 'X')
            {
                map[i][j] = idx++;
            }
            else
            {
                if (str[j] == 'S')
                {
                    sR = i;
                    sC = j;
                }
                else if (str[j] == 'E')
                {
                    eR = i;
                    eC = j;
                }
                map[i][j] = 0;
            }
        }
    }
    //  모든 물건을 챙긴 비트 마스킹 구하기
    int max = 0;
    for (int i = 0; i < (idx - 1); i++)
    {
        max += 1 << i;
    }
    // cout << max << endl;//8+4+2+1=15;
    //  중복배열 초기화
    bool v[N][M][max + 1];
    fill(&v[0][0][0], &v[N - 1][M - 1][max + 1], false);

    // 시작 초기화
    queue<pair<pair<int, int>, int>> que;
    que.push(make_pair(make_pair(sR, sC), 0)); // 시작 위치에 아무 물건도 가지고 있지 않게 시작
    v[sR][sC][0] = true;
    int turn = 0;
    while (!que.empty())
    {
        int size = que.size();
        for (int i = 0; i < size; i++)
        {
            pair<pair<int, int>, int> now = que.front();
            que.pop();

            // 현재 위치가 E인지 맞다면 물건을 전부 챙겼는지 여부 확인
            if (now.first.first == eR && now.first.second == eC)
            {
                if (now.second == max)
                {
                    cout << turn;
                    return 0;
                }
                // 종료가 아니라면 continue로 넘기기
                continue;
            }
            // 4방 탐색 가려는 위치에 물건이 있다면 물건도 챙기기
            for (int d = 0; d < 4; d++)
            {
                int nr = now.first.first + dr[d];
                int nc = now.first.second + dc[d];

                // 벽 만났을 시 패스
                if (map[nr][nc] == -1)
                    continue;
                int object = now.second;
                // 가려는 곳에 물건이 존재할 경우
                if (map[nr][nc] > 0)
                {
                    object = object | (1 << (map[nr][nc] - 1));
                }

                // 중복여부 확인
                if (v[nr][nc][object])
                    continue;

                v[nr][nc][object] = true;
                que.push(make_pair(make_pair(nr, nc), object));
            }
        }
        turn++;
    }
}