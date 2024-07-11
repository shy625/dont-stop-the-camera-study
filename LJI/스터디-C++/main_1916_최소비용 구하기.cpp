#include <iostream>
#include <vector>
#include <queue>
using namespace std;
int N, M;

struct Info
{
    int CityNum;
    int Cost;
};

struct Cmp
{
    // 오름차순 정렬
    bool operator()(Info &A, Info &B)
    {
        return A.Cost > B.Cost;
    }
};

main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> M;

    vector<vector<int>> City(N + 1, vector<int>(N + 1, -1));

    for (int i = 0; i < M; i++)
    {
        int Start, End, Cost;
        cin >> Start >> End >> Cost;

        // 중복된 출발 도착 정보 존재
        if (City[Start][End] == -1)
        {
            City[Start][End] = Cost;
        }
        else
        {
            City[Start][End] = min(City[Start][End], Cost);
        }
    }

    // 최소 경로 찾기
    int Start, End;
    cin >> Start >> End;
    vector<bool> Visited(N + 1, false);

    priority_queue<Info, vector<Info>, Cmp> PQ;
    PQ.push(Info({Start, 0}));

    while (!PQ.empty())
    {
        int CityNum = PQ.top().CityNum;
        int Cost = PQ.top().Cost;
        PQ.pop();

        if (Visited[CityNum])
            continue;

        Visited[CityNum] = true;

        if (CityNum == End)
        {
            cout << Cost;
            return 0;
        }

        for (int i = 1; i <= N; i++)
        {
            if (City[CityNum][i] != -1 && !Visited[i])
            {

                PQ.push(Info({i, Cost + City[CityNum][i]}));
            }
        }
    }
}