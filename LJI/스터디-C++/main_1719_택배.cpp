#include<iostream>

using namespace std;
const int MAX = 10000;
int n, m;
int map[201][201]; // n은 200이하;
int floyd[201][201];
main()
{
    cin >> n >> m;

    
    fill(&floyd[0][0], &floyd[201][201], MAX);
    //간선 삽입
    for (int i = 0; i < m; i++)
    {
        int a, b, t;
        cin >> a >> b >> t;

        //거리 초기화
        floyd[a][b] = t;
        floyd[b][a] = t;
        //현재는 각 상대에게 갈때 처음 들리는 곳이 상대쪽이다 
        map[a][b] = b;
        map[b][a] = a;
    }

    //플로이드 워셜
    for (int k = 1; k <=n; k++)
    {
        for (int i = 1; i <=n; i++)
        {
            if(i==k)continue;
            for (int j = 1; j <=n; j++)
            {
                if(k==j||j==i)continue;
                
                if(floyd[i][j]>floyd[i][k]+floyd[k][j]){
                    floyd[i][j] = floyd[i][k] + floyd[k][j];
                    //k를 거쳐갈 떄 거리가 최소화 된다면 그건 i->k->j 니 i에서 k갈떄 맨처음 들리는 곳과 같을 것이다
                    map[i][j] = map[i][k];
                    
                }
            }
            
        }
        
    }

    for (int i = 1; i <= n; i++)
    {
        for (int j = 1; j <=n; j++)
        {
            if(i==j)
                cout << "- ";
            else
                cout << map[i][j] << " ";
        }
        cout << endl;
    }
    
    
}