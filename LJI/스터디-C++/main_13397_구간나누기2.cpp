#include<iostream>
#include <algorithm>
#include <vector>
using namespace std;

//구간을 최댓값을 안주는게 중요
//허용하는 최댓값을 이분탐색으로 찾을 것
int N, M;

bool check(int mid,vector<int> &arr){
    //mid 값으로 가능한 구간들을 최대한 적은 개수로 생성해서 카운트
    //이때 카운트 값이 M보다 미만이어야한다

    int max = arr[0];
    int min = arr[0];
    int cnt = 1;

    for (int i = 1; i <N; i++)
    {   
        //min max값 갱신
        if(arr[i]<min)
            min = arr[i];
        if(arr[i]>max)
            max = arr[i];

        if(max-min > mid){//만약 허용된 최대값인 mid보다 현재 구간의 차이가 커진다면 이번 원소는 다음 구간으로 넘겨야한다
            cnt++;//현재 원소는 새로운 구간
            min = arr[i];
            max = arr[i];
        }
    }
    return cnt <= M;
}
main(){
    cin >> N >> M;

    //배열 입력 받기
    vector<int> arr(N);
    for (int i = 0; i < N; i++)
    {
        cin >> arr.at(i);
    }

    int right = *max_element(arr.begin(), arr.end());
    int left = 0;
    int answer = right;

    while (left<=right)
    {
        int mid = (left + right) / 2;

        if(check(mid,arr)){//현재 mid값이 구간들의 최소값으로 가능하다면? 값 갱신 가능하면 answer 갱신 후 mid값 줄여보기
            if(answer>mid)
                answer = mid;

            right = mid - 1;
        }else{//아니면 mid값을 더 높여서 반복
            left = mid + 1;
        }
    }
    
    cout << answer;

}