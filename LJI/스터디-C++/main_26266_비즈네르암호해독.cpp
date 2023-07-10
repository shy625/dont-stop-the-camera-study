#include<iostream>

using namespace std;

char Alpha[] = {',', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 
'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
main(){
    ios::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    string text, crypt;//평문 ,암호문
    cin>>text>>crypt;

    int length=text.length();

    //text+vig=crypt의 관계 -> vig=crypt-text//단 여기서 값이 0보다 작으면 +26해야할 것
    int vig[length];
    for (int i = 0; i < length; i++)
    {
        int t = text[i] - 'A';
        int c = crypt[i] - 'A';

        int temp = c - t;

        if(temp<=0)
            temp += 26;
        vig[i] = temp;
    }

    int answer = 0;
    for (int i = 1; i <=length; i++)//암호문이 가능한 길이
    {
        //키의 길이가 약수의 길이여야한다!
        if(length%i!=0)continue;
        //처음 시작 단어는 같아야한다 최소
        if(vig[0]!=vig[i]&&i!=length)continue;
        

        int cycle[i];
        for (int j = 0; j < i; j++)
        {
            cycle[j] = vig[j];
        }

        bool canVig = true;
        for (int j = i; j <length; j++)
        {
            if(cycle[j%i]!=vig[j]){
                canVig = false;
                break;
            }
        }
        
        if(canVig){
            answer = i;
            break;
        }
    }
    
    for (int i = 0; i <answer; i++)
    {
        cout << Alpha[vig[i]];
    }
    
}