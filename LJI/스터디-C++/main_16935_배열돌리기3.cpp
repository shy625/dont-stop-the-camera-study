#include<iostream>
#include<algorithm>
#include<queue>
using namespace std;

int N, M, R;
int map[100][100];

void oper(int n){
    if(n==1){
        for (int i = 0; i < N/2; i++) {
			for (int j = 0; j < M; j++) {
				int temp=map[i][j];
				map[i][j]=map[N-1-i][j];
				map[N-1-i][j]=temp;
			}
		}
    }else if(n==2){
        for (int i = 0; i < N; i++) {
			for (int j = 0; j < M/2; j++) {
				int temp=map[i][j];
				map[i][j]=map[i][M-1-j];
				map[i][M-1-j]=temp;
			}
		}
    }else if(n==3||n==4){
            int tempMap[100][100];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(n==3)tempMap[j][N-1-i]=map[i][j];
					else tempMap[M-1-j][i]=map[i][j];
				}
			}
			int temp=M;
			M=N;
			N=temp;
            copy(&tempMap[0][0], &tempMap[0][0] + 10000, &map[0][0]);
    }else if(n==5||n==6){
        int tempMap[100][100];
			int rc=N/2;//row Center
			int cc=M/2;//col Center
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					int nr=i;
					int nc=j;
					if(n==5) {
						if(i<rc&&j<cc) {//1번 부분배열
							nc=j+cc;
						}else if(i<rc&&j>=cc) {//2번 부분배열
							nr=i+rc;
						}else if(i>=rc&&j>=cc) {//3번 부분배열
							nc=j-cc;
						}else {//4번부분배열
							nr=i-rc;
						}
					}else {
						if(i<rc&&j<cc) {//1번 부분배열
							nr=i+rc;
						}else if(i<rc&&j>=cc) {//2번 부분배열
							nc=j-cc;
						}else if(i>=rc&&j>=cc) {//3번 부분배열
							nr=i-rc;
						}else {//4번부분배열
							nc=j+cc;
						}
					}
					tempMap[nr][nc]=map[i][j];
				}
			}
			copy(&tempMap[0][0], &tempMap[0][0] + 10000, &map[0][0]);
    }
}

main(){
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> M >> R;

    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < M; j++)
        {
            int temp;
            cin >> temp;
            map[i][j] = temp;
        }
    }
    
    queue<int> que;
    for (int i = 0; i <R; i++)
    {
        int temp;
        cin >> temp;
        que.push(temp);
    }
    
    while(!que.empty()){
        int now = que.front();
        que.pop();
        oper(now);
    }

    //출력
    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < M; j++)
        {
            cout << map[i][j] << ' ';
        }
        cout << endl;
    }
    
}