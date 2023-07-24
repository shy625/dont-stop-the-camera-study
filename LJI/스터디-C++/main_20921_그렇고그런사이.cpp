#include<iostream>
#include <vector>
#include<algorithm>
using namespace std;
//낮은 순으로 정수를 나눠준다고 가정하고 가장 뒤에 값을 K만큼 이동
//모자르다면 다음 수를 이동

int N, K;
main(){
    ios_base::sync_with_stdio(false);
    cout.tie(0);
    cin.tie(0);

    cin >> N >> K;

    vector<int> vec;
    for (int i = 0; i <=N; i++)
    {
        vec.push_back(i);
    }

    int nowMax = N - 1;
    while(K>0){
        int dis = min(nowMax, K);

        int idx = N - dis;//이 위치로 맨 마지막 값이 들어가야할 것
        K -= dis;

        //값 넣고 맨 마지막 값 빼기

        vec.insert(vec.begin() + idx,vec.back() );
        vec.erase(vec.end() - 1);

        nowMax--;
    }

    for (int i = 1; i <=N; i++)
    {
        cout << vec.at(i)<<' ';
    }
    
}