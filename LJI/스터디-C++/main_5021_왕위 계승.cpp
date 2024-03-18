#include <iostream>
#include <map>
#include <vector>
#include <queue>
using namespace std;
int N, M;
string King;

struct Info
{
    bool Find = false;
    long long Blood = 0;
    vector<string> Parents;
};

map<string, Info> InfoMap;

long long Cal(string Name)
{

    // 아예 처음 나오는 이름
    if (InfoMap.count(Name) == 0)
    {
        return 0;
    }

    // 부모가 없는 조상 노드들
    if (InfoMap[Name].Parents.size() == 0)
    {
        return InfoMap[Name].Blood;
    }

    // 이미 갱신된 노드들
    if (InfoMap[Name].Blood != 0)
    {
        return InfoMap[Name].Blood;
    }

    // 처음 갱신하는 노드
    return (Cal(InfoMap[Name].Parents[0]) + Cal(InfoMap[Name].Parents[1])) / 2;
}

main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> M >> King;

    // map<string, Info> InfoMap;

    for (int i = 0; i < N; i++)
    {
        string child, mother, father;
        cin >> child >> mother >> father;

        //  각 요소가 없다면 일단 만들기
        if (InfoMap.count(child) == 0)
        {
            InfoMap[child] = Info({false, 0});
        }
        if (InfoMap.count(mother) == 0)
        {
            InfoMap[mother] = Info({false, 0});
        }
        if (InfoMap.count(father) == 0)
        {
            InfoMap[father] = Info({false, 0});
        }

        // 자식의 부모 추가
        InfoMap[child].Parents.push_back(mother);
        InfoMap[child].Parents.push_back(father);
    }

    InfoMap[King].Blood = 1LL << 51;

    string answer;
    long long answerVal = -1;
    string Name;
    for (int i = 0; i < M; i++)
    {
        cin >> Name;
        long long Val = Cal(Name);
        if (Val > answerVal)
        {
            answer = Name;
            answerVal = Val;
        }
    }

    cout << answer;
}