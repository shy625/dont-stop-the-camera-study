#include <iostream>
#include <queue>
#include <algorithm>
using namespace std;
//상대 말의 번호를 맵의 표시해놓고 이동하면 될 것 같은디?
static int dr []= {-2,-1,1,2,2,1,-1,-2};
static int dc[] = {1, 2, 2, 1, -1, -2, -2, -1};
int N, M;
bool check(int r, int c);

main(){
    cin >> N >> M;

    //나이트 입력
    int knightR, knightC;
    cin >> knightR >> knightC;
    knightR--;
    knightC--;

   

    //맵 선언//상대 나이트 숫자에 맞게 초기화
    int map[N][N]={0,};
    fill(map[0], map[N], 0);//이상하게 따로 초기화 해줘야한다

    for (int i = 1; i <= M; i++)
    {
        int r, c;
        cin >> r >> c;
        r--;
        c--;

        map[r][c] = i;
    }
    
    bool checkKnight[M + 1] = {
        false,
    };
    int answer[M + 1] = {0};
    bool c[N][N]={false,};
    int cnt = 0;
    //bfs로 갈 수 있는 곳 탐방
    queue<pair<int,int>> que;

    que.push(make_pair(knightR,knightC));
    int turn = 0;
    while (!que.empty())
    {
        int size = que.size();
        
        for (int s = 0; s < size; s++)
        {
           pair<int,int> temp = que.front();

            if(map[temp.first][temp.second]!=0){//상대 말의 위치
                answer[map[temp.first][temp.second]] = turn;
                cnt++;
                if(cnt==M){//끝내는 조건
                    for (int i = 1; i <= M; i++)
                    {
                        cout << answer[i] << " ";
                    }
                    return 0;
                }
            }

            for (int d = 0; d < 8; d++)
            {
                int nr = temp.first + dr[d];
                int nc = temp.second + dc[d];
                if(!check(nr,nc))continue;

                if(c[nr][nc])continue;

                c[nr][nc] = true;
                que.push(make_pair(nr, nc));
            }

            que.pop();
        }

        turn++;//턴 넘기기
    }
    
    return 0;
}

bool check(int r, int c){
    return r >= 0 && r < N && c >= 0 && c < N;
};