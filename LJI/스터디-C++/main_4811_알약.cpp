#include<iostream>

using namespace std;

int N = 1;
long long dp[31][31];//W,H//
//길이가 N개인 문자열= N-1문자열+ W or H
//이때 dp[w][h]= (dp[w][h-1]+'H')+(dp[w-1][h]+'W')
//w,h개의 문자열은 w,h-1 개에서 H문자 하나 붙인 것 + w-1,h 개에서 W문자 하나 붙인 것
main(){
    
    for (int w = 0; w <=30; w++)
    {
        for (int h = 0; h <=30; h++)
        {
            if(h>w)continue;//반개짜리가 더 커지는 경우는 없다
            if(h==0)//반개짜리가 없으면? W,WW,WWW..
                dp[w][h] = 1;
            else{//
                dp[w][h] = dp[w - 1][h] + dp[w][h - 1];
            }
        }
        
    }
    

    while(N!=0){
        cin >> N;
        //끝
        if(N==0)
            return 0;
        //
        cout << dp[N][N] << '\n';
    }
}