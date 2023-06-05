#include<iostream>
#include<vector>
using namespace std;

//1은 차단 벽이고 0은 연결고리 아닌가?
int n=0;

bool stableCheck(vector<int> list,int **map);
main(){
    cin >> n;

    //맵 입력받기
    int **map = new int* [n];
    for (int i = 0; i < n; i++)
    {
        map[i] = new int[n];
    }
    

    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            cin >> map[i][j];
        }
    }
    

    //연결리스트로 만들면 되려나?
    bool stable = true;
    bool c[n]={false,};//한번 쓴 녀석들 체크

    // cout << endl;
    // for (int i = 0; i < sizeof(c); i++)
    // {
    //     if(!c[i])
    //         cout <<"false" << " ";
    // }
    // cout << endl;

    vector<vector<int>> list;
    int cnt = 0;
    for (int i = 0; i < n; i++)
    {
        if(c[i])continue;//이미 다른 집단에 포함됨
        
        //새로운 집단
        
        c[i] = true;
        list.push_back(vector<int>());
        list[cnt].push_back(i);

        //cout << i << " start" << endl;

        for (int j = i+1; j < n; j++)
        {
            if(map[i][j]==0&&map[j][i]==0){//서로 좋아하면 연결 리스트에 넣기
                list[cnt].push_back(j);
                c[j] = true;//체크
            }
        }

        if(list[cnt].size()==1){
            stable = false;
            break;
        }

        stable = stableCheck(list[cnt],map);
        if(!stable){
            break;
        }
        //리스트 카운트
        cnt++;

        
    }
    
    if(!stable)
        cout << "0";
    else{
        //정답출력
        cout << list.size() << endl;
        for (auto listIn : list)
        {
            for (auto num: listIn)
            {
                cout << num+1 << " ";
            }
            cout << endl;
        }
        
    }
}

bool stableCheck(vector<int> list,int** map){
    int cnt = 0;
    int size = list.size();
    //cout <<size <<" size"<<endl;
    for (int i = 1; i < size; i++)
    {
        for (int j = i+1; j < size; j++)
        {
            int a = list.at(i);
            int b = list.at(j);
            if(map[a][b]!=0 || map[b][a]!=0)//둘 중 하나라도 좋아하지 않으면
                return false;
        }
        
    }
    
    return true;
};