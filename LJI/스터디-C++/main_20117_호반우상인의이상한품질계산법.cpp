#include<iostream>
#include<algorithm>
#include<vector>
using namespace std;
//그냥 큰 값과 작은값 하나씩 묶어볼까
//그래서 큰 값들터 *2
main(){
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    int N;
    cin >> N;

    vector<int> vec;

    for (int i = 0; i < N; i++)
    {
        int temp;
        cin >> temp;
        vec.push_back(temp);
    }

    sort(vec.begin(), vec.end(), less<int>());

    int answer = 0;
    int mid = N / 2;
    if(N%2){//홀수//중간값은 혼자 판매
        int sum = 0;
        for (int i = mid+1; i <N; i++)
        {
            sum += vec[i];
        }
        answer=vec[mid]+sum*2;
        
    }else{//짝수
        int sum=0;
        for (int i = mid; i < N; i++)
        {
            sum += vec[i];
        }
        answer = sum * 2;
    }

    cout << answer;
}