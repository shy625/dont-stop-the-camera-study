#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

//둘 중 한변을 기준으로 큰순으로 정렬 해서 dp를 해야할 것 같다
//색종이 90도 회전은 되니 큰 변을 기준으로
//한변이 큰순,같다면 다른 변이 큰 순으로 정렬
//자신보다 전에 색종이들 중 모든 변이 큰 색종이들의 겹친 수+1 하면 될 것 같다

// struct cmp{
//     bool operator()(pair<int,int> a,pair<int,int> b){
//         if(a.first==b.first){
//             return a.second < b.second;
//         }

//         return a.first < b.first;
//     }
// }

bool cmp(pair<int,int> a,pair<int,int> b){
    if(a.first==b.first){
        return a.second > b.second;
    }

    return a.first > b.first;
}

int main(){
    int N;
    cin >> N;

    vector<pair<int, int>> arr;
    for (int i = 0; i < N; i++)
    {
        int a, b;
        cin >> a >> b;
        if(a>=b)
            arr.push_back(make_pair(a, b));
        else
            arr.push_back(make_pair(b, a));
    }
    
    //정렬
    sort(arr.begin(), arr.end(), cmp);

    // cout << endl;
    // for (int i = 0; i < N; i++)
    // {
    //     cout << arr[i].first << " " << arr[i].second << endl;
    // }
    // cout << endl;

    //dp 배열 생성
    int dp[N]={0,};

    int answer = 0;
    for (int i = 0; i < N; i++)
    {
        int cnt = 0;//이전 dp들 중에서 가능한 가장 많은 색종이가 겹친 값 가져오기

        //이전 색종이들의 dp값 중 가장 큰 것 가져오기
        for (int j = 0; j < i; j++)
        {
            //이미 첫번째 변은 작거나 같으니 두번째 변만 비교해보면 된다
            if(arr[j].second>=arr[i].second)
                cnt = max(cnt, dp[j]);
        }

        dp[i] = cnt + 1;//현재 색종이를 이전 가장 많이 겹친 색종이 위에 겹친다

        answer = max(dp[i], answer);//굳이 한번 더 순회하는 것을 줄이기 위해 정답 갱신
    }


    cout << answer;
}