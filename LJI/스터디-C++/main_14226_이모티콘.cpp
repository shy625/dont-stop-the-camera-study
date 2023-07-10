#include<iostream>
#include<queue>
using namespace std;
//큐로 시도하면 될 것 같은데?
//최대 천개이니 아무리 커져도 2000개 넘을 일은 없을 것

bool c[2001][2001];

main(){
    ios::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    int S;
    cin >> S;

    queue<pair<pair<int,int>,int>> que;//이모티콘,클립보드 , 시간
    que.push(make_pair(make_pair(1,0),0));
    c[1][0]=true;
    int answer = 0;
    while(!que.empty()){
        pair<pair<int, int>, int> now = que.front();
        que.pop();
        int emo=now.first.first;
        int clip=now.first.second;
        int time = now.second;
        if(emo==S){
            answer = time;
            break;
        }

        //1연산
        if(emo>0){
            if(!c[emo][emo]){
                c[emo][emo] = true;
                que.push(make_pair(make_pair(emo, emo), time + 1));
            }
        }
        //2연산
        if(clip>0){
            if(emo+clip<2000&&!c[emo+clip][clip]){
                c[emo + clip][clip] = true;
                que.push(make_pair(make_pair(emo+clip, clip), time + 1));
            }
        }
        //3연산
        if(emo>0){
            if(!c[emo-1][clip]){
                c[emo - 1][clip] = true;
                que.push(make_pair(make_pair(emo-1, clip), time + 1));
            }
        }
    }
    

    cout << answer;
}