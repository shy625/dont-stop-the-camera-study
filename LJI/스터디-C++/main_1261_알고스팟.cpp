#include<iostream>
#include<queue>
using namespace std;

//벽이 있는 1은 그냥 가중치 1로 보면 될 것 같은데?
int N, M;
int map[100][100]={0,};

int dr[] = {-1, 0, 1, 0};
int dc[] = {0, 1, 0, -1};

bool check(int r,int c){
    return r >= 0 && r < N && c >= 0 && c < M;
}
main(){
    cin >> M >> N;

    for (int i = 0; i < N; i++)
    {
        string str;
        cin >> str;
        for (int j = 0; j < M; j++)
        {
            map[i][j] = str[j]-'0';
        }
    }

    //다잌스트라 만들기
    //거리 최대값으로 초기화
    int dijk[N][M]={0,};
    fill(&dijk[0][0], &dijk[N-1][M], 10000);
    dijk[0][0] = map[0][0];

    //갔던 곳 중복체크는? 굳이 해야할까?
    //가려는 곳의 값+ 현재 거리가 기존 다잌스트라보다 작아졌을 때만 갱신하면 되지 않을까?
    queue<pair<int, int>> que;
    que.push(make_pair(0, 0));

    while (!que.empty())
    {
        pair<int,int> cur = que.front();
        int r = cur.first;
        int c = cur.second;
        que.pop();

        if(r==N-1&&c==M-1){//끝자리 도착하면 더 할 필요는 없을 것
            continue;
        }


        //현재 위치에서 4방 탐색 하여 거리 줄을 시에 갱신 후 que에 넣기
        for (int d = 0; d < 4; d++)
        {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if(!check(nr,nc))continue;
            
            //만약 기존 최소 거리> 현재 최소 거리+ 다음 위치 값 -> 갱신
            if(dijk[nr][nc]>dijk[r][c]+map[nr][nc]){
                dijk[nr][nc] = dijk[r][c] + map[nr][nc];
                que.push(make_pair(nr, nc));
            }
        }
        
    }
    

    //N,M까지 가는 최소 거리가 부순 벽의 개수일 것
    cout << dijk[N - 1][M - 1];
}