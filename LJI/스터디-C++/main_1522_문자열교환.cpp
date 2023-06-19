#include <iostream>
#include<algorithm>
#include<cstring>
using namespace std;
//슬라이딩 윈도우 
//a의 길이만큼의 크기를 구하고 그 안이 전부 a로 구성할 수 있게끔하는 최소한의 교환 횟수를 구해야함
//a의 길이를 고정 크기로 정하여 안에 있는 b의 개수를 교환할 것이기에 전부 세서 그 중 최솟값을 구하면 될 것
//원형이기에 사이즈를 넘아가는 건 0부터 시작
string str;

int main(){
    cin >> str;

    int aLen = 0;       //슬라이딩 윈도우 길이
    int len = str.length(); //문자열의 길이
    for (char c:str)
    {
        if(c=='a')
            aLen++;
    }

    //처음 윈도우 크기에서 b개수 세기 이 뒤는 현재 위치값 뺴고 추가되는 값 넣고 하는 식으로 계산
    int bCnt=0;
    for (int i = 0; i < aLen; i++)
    {
        if(str[i]=='b')
            bCnt++;
    }


    //0위치는 초기값으로 넣었기에 그 뒤부터 계산
    int answer = bCnt;

    //cout << "aLen : " << aLen<<" Len : "<<len<<" bCnt : "<<bCnt<<endl;

    for (int i = 1; i < len; i++)
    {
        //더 이상 할 필요 없다
        if(answer==0)
            break;

        //슬라이딩 윈도우로 빠진 값 빼기
        if(str[i-1]=='b')
            bCnt--;
        //슬라이딩 윈도우로 추가된 값 넣기
        if(str[(i-1+aLen)%len]=='b')
            bCnt++;
        
        answer = min(bCnt, answer);
    }

    cout << answer;
}