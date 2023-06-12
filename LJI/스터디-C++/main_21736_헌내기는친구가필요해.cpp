#include<iostream>
#include <queue>
#include<algorithm>
using namespace std;

bool check(int r, int c);

//전형적인 bfs
int N, M;
char **map;
bool **c;
int dr[] = {-1, 0, 1, 0};
int dc[] = {0, 1, 0, -1};

main(){
    cin >> N>> M;
    
    map = new char*[N + 1];
    c = new bool *[N + 1];
    for (int i = 0; i <=N; i++)
    {
        map[i] = new char[M+1];
        c[i] = new bool[M + 1];
    }

    //동적 할당 된 배열은 연속된 배열이 아니기에 각각 초기화 해야겠다
    for (int i = 0; i <=N; i++)
    {
        for (int j = 0; j <=M; j++)
        {
            map[i][j] = 'X';
            c[i][j] = false;
        }
    }
    //cout << "test";
    int sR, sC;
    for (int i = 1; i <=N; i++)
    {
        string str;
        cin >> str;

        for (int j = 1; j <= M; j++)
        {
            map[i][j] = str[j - 1];
            if(map[i][j]=='I'){
                sR = i;
                sC = j;
            }
        }
    }
    
    //bfs
    queue<pair<int, int>> que;
    que.push(make_pair(sR, sC));
    c[sR][sC] = true;

    int cnt = 0;

    while (!que.empty())
    {
        pair<int,int> cur = que.front();
        que.pop();

        if(map[cur.first][cur.second]=='P'){
            cnt++;
        }

        for (int d = 0; d < 4; d++)
        {
            int nr = cur.first + dr[d];
            int nc = cur.second + dc[d];

            if(!check(nr,nc))continue;
            if(map[nr][nc]=='X')continue;
            if(c[nr][nc])continue;

            c[nr][nc] = true;
            que.push(make_pair(nr, nc));
        }
        
    }
    if(cnt==0){
        cout << "TT";
    }else{
        cout << cnt;
    }
}

bool check(int r,int c){
    return r >= 1 && r <= N && c >= 1 && c <= M;
}