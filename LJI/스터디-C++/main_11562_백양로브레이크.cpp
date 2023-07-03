#include<iostream>
using namespace std;
#define INF INT32_MAX/100;

//일방통행일때 막힌 길을 비용을 1로 보고 플로이드-워셜을 사용하면 될 것 같다.
//맵에서 0이면 막힌 길, 1이면 뚫린 길, 2면 길이 X
int floyd[251][251];
int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    int n, m,k;

    cin>> n>> m;

    for (int i = 1; i <=n; i++)
    {
        for (int j = 1; j <=n; j++)
        {
            if(i==j)floyd[i][j] = 0;
            else floyd[i][j] = INF;
        }
        
    }
    
    for (int i = 0; i < m; i++)
    {
        int u,v,b;
        cin>> u>> v>> b;
        if(!b){//u->v 일방통행
            floyd[u][v] = 0;
            floyd[v][u] = 1;//길뚫는 카운트 1
        }else{//양방통행
            floyd[u][v] = 0;
            floyd[v][u] = 0;
        }
    }
    
    //플로이드 워셜
    for (int k = 1; k <=n; k++)
    {
        for (int i = 1; i <=n; i++)
        {
            if(k==i) continue;
            for (int j = 1; j <=n; j++)
            {
                if(i==j || j==k) continue;
                if(floyd[i][j]>floyd[i][k]+floyd[k][j]){
                    floyd[i][j] = floyd[i][k] + floyd[k][j];
                }
            }
        }
    }

    cin >> k;
    
    for (int i = 0; i < k; i++)
    {
        int s, e;
        cin >> s >> e;
        cout << floyd[s][e]<<'\n';//endl 쓰니 시간초과!
    }
    return 0;
}