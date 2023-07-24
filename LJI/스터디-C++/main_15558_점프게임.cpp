#include<iostream>
#include<queue>
using namespace std;

struct Info
{
    int idx;
    int line;
    int turn;
    // Info(int idx,int line,int turn){
    //     this->idx = idx;
    //     this->line = line;
    //     this->turn = turn;
    // }
};

int N,k;

int main(){
    ios_base::sync_with_stdio(false);
    cout.tie(0);
    cin.tie(0);

    cin >> N >> k;
    int arr[N + 1][2];
    bool v[N + 1][2] = {
        false,
    };
    //배열 입력받기
    for (int t = 0; t <2; t++)
    {
        string str;
        cin >> str;
        for (int i = 1; i <= N; i++)
        {
            arr[i][t] = str[i - 1] - '0';
        }
    }

    queue<Info> que;
    que.push(Info{1, 0, 1});
    v[1][0] = true;
    while(!que.empty()){
        Info now=que.front();
        que.pop();


        int newIdx;
        int newLine;
        int newTurn = now.turn + 1;//이것보다 크거나 같아야한다.
        // 한칸  앞으로//앞으로 가기에 사라지는 칸은 굳이 체크 안해도 됨
        newIdx = now.idx + 1;
        newLine = now.line;
        
        if(newIdx>N){//종료 체크
            cout << '1';
            return 0;
        }

        if(arr[newIdx][newLine]==1&& !v[newIdx][newLine]){
            v[newIdx][newLine] = true;
            que.push(Info{newIdx, newLine, newTurn});
        }

        // 힌칸 뒤로//칸 사라진 거 체크
        newIdx = now.idx - 1;
        newLine = now.line;

         if(arr[newIdx][newLine]==1&& !v[newIdx][newLine]&&newIdx>=newTurn){
            v[newIdx][newLine] = true;
            que.push(Info{newIdx, newLine, newTurn});
        }

        // 옆라인 idx+k;
        newIdx = now.idx + k;
        newLine = !now.line; //0->1,1->0

        if(newIdx>N){//종료 체크
            cout << '1';
            return 0;
        }

        if(arr[newIdx][newLine]==1&& !v[newIdx][newLine]){
            v[newIdx][newLine] = true;
            que.push(Info{newIdx, newLine, newTurn});
        }
    }

    cout << '0';
    return 0;
}