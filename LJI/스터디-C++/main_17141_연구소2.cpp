#include <iostream>
#include<vector>
#include<queue>
#include<algorithm>
using namespace std;

//맵을 입력 받을 때 넣을 수 있는 위치들을 배열로 기록한뒤 M개를 고르는 방식으로 체크
//그 뒤 고른 위치들로 bfs
//0이 존재하는지 체크후 시간 체크
int N, M,MAX;//MAX는 모든 칸을 채웠을 시
vector<pair<int, int>> virusArr;
int answer=0;

int dr[] = {-1, 0, 1, 0};
int dc[] = {0, 1, 0, -1};

bool check(int r,int c){
    return r >= 0 && r < N && c >= 0 && c < N;
}

void pickUp(int start,int cnt,int size,bool* c,int* map,int& answer){
    if(cnt==M){//bfs 시작
        //맵 복사하고 c검사해서 true위치에 바이러스 넣기
        int copyMap[N][N];
        copy(map, map + N * N, &copyMap[0][0]);

        queue<pair<int, int>> que;
        
        for (int i = 0; i < size; i++)
        {
            if(c[i]){
                que.push(make_pair(virusArr[i].first, virusArr[i].second));
                copyMap[virusArr[i].first][virusArr[i].second] = 2;
            }
        }

        //bfs 실행
        int virusCnt = M;
        if(virusCnt==MAX){
            answer = 0;
            return;
        }
        int time = 0;
        while(!que.empty()){
            int turn = que.size();
            for (int i = 0; i < turn; i++)
            {
                pair<int, int> cur = que.front();
                que.pop();

                for (int d = 0; d < 4; d++)
                {
                    int nr = cur.first + dr[d];
                    int nc = cur.second + dc[d];

                    if(!check(nr,nc))continue;
                    if(copyMap[nr][nc]!=0)continue;

                    copyMap[nr][nc] = 2;
                    virusCnt++;
                    que.push(make_pair(nr, nc));
                }
                

            }

            time++;

            //개수 검사
            if(virusCnt==MAX){
                //cout << "test"<<endl;
                answer = min(answer, time);
            }
        }
        return;
    }

    for (int i = start; i < size; i++)
    {
        c[i] = true;
        pickUp(i + 1, cnt + 1, size, c, map,answer);
        c[i] = false;
    }
    
}
main(){
    cin >> N >> M;

    //맵을 입력 받을 때 배열 생성
    int map[N][N]={0,};
    MAX = N * N;
    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < N; j++)
        {
            cin >> map[i][j];
            if(map[i][j]==1){
                MAX--;
            }
            else if(map[i][j]==2){
                virusArr.push_back(make_pair(i, j));
                map[i][j] = 0;
            }
        }
    }
    

    //M개를 골라내기
    int size = virusArr.size();
    bool c[size]={false,};
    int answer = INT32_MAX;
    pickUp(0, 0, size, c,(int *) map,answer);

    if(answer==INT32_MAX){
        cout << "-1";
    }else{
        cout << answer;
    }
}