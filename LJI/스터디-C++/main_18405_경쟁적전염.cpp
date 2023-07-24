#include<iostream>
#include<queue>
using namespace std;

int N,K,S,X,Y;

int dr[] = {-1, 0, 1, 0};
int dc[] = {0, 1, 0, -1};

int map[201][201];

bool check(int r,int c){
    return r > 0 && r <= N && c > 0 && c <= N;
}
main(){
    ios_base::sync_with_stdio(false);
    cout.tie(0);
    cin.tie(0);

    cin >> N>>K;

    //바이러스 위치 담을 배열
    queue<pair<int,int>> queArr[K+1];

    for (int i = 1; i <=N; i++)
    {
        for (int j = 1; j <= N; j++)
        {
            cin >> map[i][j];

            if(map[i][j]!=0)
                queArr[map[i][j]].push(make_pair(i, j));//현재 바이러스 위치를 배열에 업데이트
        }
        
    }

    cin >> S >> X >> Y;

    //S초 시뮬
    for (int t = 0; t < S; t++)
    {
        //1~K까지 바이러스 번지기
        //이미 한번 퍼진 바이러스는 다시 퍼질 위치가 없을테니 굳이 다시 queue에 넣을 필요 없다
        for (int k = 1; k <=K; k++)
        {
            int size = queArr[k].size();
            for (int i = 0; i < size; i++)
            {
                //que에서 꺼내서 4방 탐색 후 0일때 퍼뜨리면 끝
                pair<int, int> point = queArr[k].front();
                queArr[k].pop();

                int r = point.first;
                int c = point.second;

                for (int d = 0; d < 4; d++)
                {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if(!check(nr,nc))continue;
                    if(map[nr][nc]!=0)continue;
                    map[nr][nc] = k;
                    queArr[k].push(make_pair(nr, nc));//다음 바이러스 넣어놓기
                }
                
            }
            
        }
        
    }
    

    //출력
    cout << map[X][Y];
}