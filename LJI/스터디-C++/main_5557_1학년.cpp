#include<iostream>
#include<vector>
using namespace std;

//나올 수 있는 수가 0~20 사이이다?
//목표 숫자는 N의 값
main(){
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    int N;
    cin >> N;

    vector<int> vec;
    vec.push_back(0);//0자리 넣기
    for (int i = 0; i < N; i++)
    {
        int temp;
        cin >> temp;
        vec.push_back(temp);
    }

    long long dp[N + 1][21]={0,};

    fill(&dp[0][0], &dp[N][21], 0);
    
    dp[1][vec[1]] = 1;//처음은 +밖에 안되서 바로 초기화
    for (int i = 2; i < N; i++)
    {
        for (int n = 0; n <=20; n++)
        {
            if(dp[i-1][n]>0){//이전 값이 존재하면?
                int temp;
                //+
                temp = n + vec[i];
                if(temp<=20){
                    dp[i][temp]+=dp[i-1][n];
                }
                //-
                temp = n - vec[i];
                if(temp>=0){
                    dp[i][temp]+=dp[i-1][n];
                }
            }
        }
    }

    cout << dp[N-1][vec[N]];
}