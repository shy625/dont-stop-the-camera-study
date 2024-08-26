#include <iostream>
#include <vector>
using namespace std;

int Find(int Now, vector<vector<pair<int, int>>> &Graph, vector<bool> &Visited)
{

    int Max = 0;
    for (pair<int, int> Cur : Graph[Now])
    {
        if (!Visited[Cur.first])
        {
            int Dis = Cur.second;
            Visited[Cur.first] = true;
            Dis += Find(Cur.first, Graph, Visited);
            Max = max(Max, Dis);
        }
    }
    return Max;
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    vector<vector<pair<int, int>>> Graph(10001);
    vector<bool> City(10001, false);

    int a, b, cost;

    while (cin >> a >> b >> cost)
    {
        Graph[a].push_back(make_pair(b, cost));
        Graph[b].push_back(make_pair(a, cost));
        City[a] = true;
        City[b] = true;
    }

    int Answer = 0;
    for (int i = 1; i <= 10000; i++)
    {
        if (City[i])
        {
            vector<bool> Visited(10001, false);
            Visited[i] = true;
            Answer = max(Answer, Find(i, Graph, Visited));
        }
    }

    cout << Answer;
}