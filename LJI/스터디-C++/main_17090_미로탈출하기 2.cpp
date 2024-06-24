#include<iostream>
#include<algorithm>
using namespace std;

//그 이전에 이미 경로를 찾은 적이 있다면? +1만 해주면 되는 dp일 것 같다
//탈출-> 미로 경계 밖으로 나가기

int N, M;

//상,우,하,좌 순
int dr[] = {-1, 0, 1, 0};
int dc[] = {0, 1, 0, -1};

bool check(int r,int c){
    return r >= 0 && r < N && c >= 0 && c < M;
}

int map[500][500];
int dp[500][500];

//-1: 미탐색,1:탈출,0:탈출불가
int dfs(int r,int c){
    if(r < 0 || r >= N || c < 0 || c >= M)
        return 1;
    if(dp[r][c] != -1)
        return dp[r][c];

    dp[r][c] = 0;
    dp[r][c] = dfs(r + dr[map[r][c]], c + dc[map[r][c]]);

    return dp[r][c];
}
main(){
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> M;
    for (int i = 0; i < N; i++)
    {
        string str;
        cin >> str;
        for (int j = 0; j < M; j++)
        {
            char c = str[j];
            int oper = 0;
            if(c=='U'){
                oper = 0;
            }else if(c=='R'){
                oper = 1;
            }else if(c=='D'){
                oper = 2;
            }else{
                oper = 3;
            }
            map[i][j] = oper;
            dp[i][j] = -1;
        }
    }
    
    int answer = 0;
    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < M; j++)
        {
            answer += dfs(i, j);
        }
    }

    
    cout << answer;
}