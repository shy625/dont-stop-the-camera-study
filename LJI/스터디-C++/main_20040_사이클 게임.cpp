#include <iostream>
#include <vector>
#include <queue>
using namespace std;
int n, m;

// union-find를 해서 연결된 것끼리 묶으려 할 때
// union 실패시 사이클 생성
vector<vector<int>> graph;
vector<int> parents;
int Find(int a)
{
    if (parents[a] == a)
        return a;
    else
        return parents[a] = Find(parents[a]);
}

bool Union(int a, int b)
{
    a = Find(a);
    b = Find(b);
    if (a == b)
    {
        return false;
    }
    else if (a > b)
    {
        parents[a] = b;
    }
    else if (a < b)
    {
        parents[b] = a;
    }

    return true;
}

main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    cin >> n >> m;

    // parents초기화
    for (int i = 0; i < n; i++)
    {
        parents.push_back(i);
    }

    int answer = 0;
    for (int i = 0; i < m; i++)
    {
        int a, b;
        cin >> a >> b;
        if (!Union(a, b))
        {
            answer = i + 1;
            break;
        }
    }
    cout << answer;
}