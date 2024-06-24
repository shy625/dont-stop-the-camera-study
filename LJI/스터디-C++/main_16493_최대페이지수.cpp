#include<iostream>
#include<algorithm>
#include<vector>
using namespace std;

main(){
    int N, M;
    cin >> N>> M;

    vector<pair<int, int>> chapters;
    chapters.push_back(make_pair(0, 0));

    for (int i = 0; i < M; i++)
    {
        int days, pages;
        cin >> days >> pages;
        chapters.push_back(make_pair(days, pages));
    }
    
    int dp[M+1][N+1];
    fill(&dp[0][0], &dp[M][N+1], 0);

    
    for (int i = 1; i <= M; i++)
    {
        int days = chapters[i].first;
        int pages = chapters[i].second;

        for (int j = 0; j <=N; j++)
        {
            //만약 읽기가 가능하다면?-> 책을 읽는데 걸리는 요일보다 남은 요일이 높을때
            if(days<=j){
                dp[i][j] = max(pages + dp[i - 1][j - days], dp[i - 1][j]);//현재 챕터 안읽는 것과 이전에서 읽은 것 중 최대값 갱신
            }else{
                dp[i][j] = dp[i - 1][j];
            }
            
        }
        
    }
    

    cout << dp[M][N];
}