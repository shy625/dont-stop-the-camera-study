#include<iostream>
#include<queue>
#include<algorithm>
using namespace std;

int dr[] = {-1, 0, 1, 0};
int dc[] = {0, 1, 0, -1};

bool check(int r,int c,int N,int M){
    return r > 0 && r <= N && c > 0 && c <= M;
}

main(){
    ios::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    int N, M, K;

    cin >> N >> M >> K;

    //음식물 여부
    bool map[N + 1][M + 1]={false,};
    fill(&map[0][0], &map[N - 1][M], false);
    for (int i = 0; i < K; i++)
    {
        int r, c;
        cin >> r >> c;
        map[r][c] = true;
    }

    //방문 여부
    bool ch[N + 1][M + 1] = {false,};
    fill(&ch[0][0], &ch[N - 1][M], false);
    
    int answer = 0;
    for (int i = 1; i <=N; i++)
    {
        for (int j = 1; j <=M; j++)
        {
            if(map[i][j]&& !ch[i][j]){//음식물은 있으며 방문한 적이 없다면
                queue<pair<int, int>> que;
                que.push(make_pair(i, j));
                int cnt = 0;
                ch[i][j] = true;

                while (!que.empty())
                {
                    int r = que.front().first;
                    int c = que.front().second;
                    que.pop();

                    cnt++;//음식물 카운트

                    for (int d = 0; d < 4; d++)
                    {
                        int nr = r + dr[d];
                        int nc = c + dc[d];

                        if(!check(nr,nc,N,M))continue;
                        
                        if(map[nr][nc] && !ch[nr][nc]){
                            ch[nr][nc] = true;
                            que.push(make_pair(nr, nc));
                        }
                    }
                }
                answer = max(answer, cnt);
            }
        }
    }

    cout << answer;
}