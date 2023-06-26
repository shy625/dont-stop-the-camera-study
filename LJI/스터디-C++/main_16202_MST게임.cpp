#include<iostream>
#include<algorithm>
#include<vector>
using namespace std;

//크루스칼 써야할 듯
//가중치 순으로 이미 정렬되어있으니 배열로 만든 뒤 턴에 맞게 그 이후부터 찾으면 될 듯?
int N, M,K;

void set_parents(int* parents){
    for (int i = 1; i <= N; i++)
    {
        parents[i] = i;
    }
    
}

int find(int a,int* parents){
    if(parents[a]==a)
        return a;
    else{
        return parents[a] = find(parents[a],parents);
    }
}

bool unions(int a,int b,int* parents){
    int aParent = find(a, parents);
    int bParent = find(b, parents);

    if(aParent==bParent){//이미 같은 그룹
        return false;
    }else{
        parents[aParent] = bParent;
        return true;
    }
}

main(){
    cin >> N >> M >> K;

    vector<pair<int, int>> arr;
    arr.push_back(make_pair(0, 0));//가중치 1부터 시작하니 그냥 0번은 비워 놓자
    for (int i = 0; i < M; i++)
    {
        int a, b;
        cin >> a >> b;
        arr.push_back(make_pair(a, b));//현재 인덱스가 가중치라고 생각하면 된다
    }

    int parents[N + 1] = {
        0,
    };

    bool zero = false;
    for (int k = 1; k <= K; k++)//k는 각 턴 ,k부터 간선 탐색하면 된다
    {
        if(zero){//이미 값이 0이면 다음도 어차피 0
            cout << 0 << ' ';
            continue;
        }

        int answer = 0;

        //크루스칼
        set_parents(parents);

        int cnt = N;//union 성공했을 시--

        for (int i = k; i <=M ; i++)//간선의 개수만큼
        {
            if(unions(arr[i].first,arr[i].second,parents)){
                answer += i;
                cnt--;
            }

            if(cnt==1){//이미 모든 노드가 하나가 됨
                break;
            }
        }

        if(cnt!=1){//모든 노드가 하나가 되지 못함
            answer = 0;
        }
        if(answer==0){
            zero = true;
        }

        cout << answer << ' ';
    }
    
    
}