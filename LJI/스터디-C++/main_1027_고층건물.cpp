#include<iostream>
#include<algorithm>
using namespace std;

main(){
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    int N;
    cin >> N;
    int arr[N];
    for (int i = 0; i < N; i++)
    {
        int temp;
        cin >> temp;
        arr[i] = temp;
    }
    
    //보다 높은 건물은 보이겠지만 보다 낮은 건물은 보이지 않을 것
    //즉 현재 건물에서 기울기(y값의 증가량/x값의 증가량)를 구해서 max보다 다음게 더 작아지면 볼 수 없다

    int ans[N] = {
        0,
    }; // 볼 수 있는 개수 카운트

    for (int i = 0; i <N; i++)//현재 건물
    {
        double diff = -1000000001.0f;                           // 임의의 큰 값
        for (int j = i+1; j <N; j++)//볼 건물
        {
            //y값 증가량
            int ydiff = arr[j] - arr[i];
            //x값 증가량
            int xdiff = j - i;

            double nowDiff = (double)ydiff / xdiff;
            if(nowDiff>diff){
                diff = nowDiff;
                ans[i]++;
                ans[j]++;
            }
        }
    }

    cout << * max_element(ans, ans+N);
}