#include<iostream>
#include<cstring>
#include<stack>
#include<cmath>
using namespace std;

int N;
string S;
//몇겹으로 쌓여있냐가 중요한 것 같다 //같은 겹에 쌓여있으면 하루면 처리가 가능하기 때문
//)와 (의 개수를 각각 카운팅 하며 다를땐 겹수 +1 같으면-1로 하면 될 것 같은디?
//물론 개수가 다르면 불가할 것
main(){
    cin >> N ;

    stack<char> Stack;
    cin.get();
    for (int i = 0; i < N; i++)
    {
        Stack.push(cin.get());
    }

    int cntOpen = 0;
    int cntClose = 0;
    int cnt = 0;
    int Max = 0;//정답 출력할 것

    while (!Stack.empty())
    {
        //여기서 큰쪽이 증가하면 겹 증가 작은쪽이 증가하면 겹 감소
        if(Stack.top()=='('){
            cntOpen++;
            if(cntOpen>cntClose)
                cnt++;
            else
                cnt--;
        }else{
            cntClose++;
            if(cntClose>cntOpen)
                cnt++;
            else
                cnt--;
        }

        Max = max(cnt, Max);

        //cout << Stack.top()<<cntOpen<<" "<<cntClose<<endl;
        Stack.pop();
    }
    
    if(cntOpen!=cntClose)
        Max = -1;

    cout << Max;
    return 0;
}