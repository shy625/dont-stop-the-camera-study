#include <iostream>
#include <vector>
using namespace std;
int N, M, k;

// 친구비는 무리에서 가장 비용이 싼 애한테만 주면 된다.

int find(int n, vector<int> &parent)
{
    if (parent[n] == n)
        return n;
    else
        return parent[n] = find(parent[n], parent);
}

void unions(int a, int b, vector<int> &parent, vector<int> &cost)
{
    a = find(a, parent);
    b = find(b, parent);

    if (a != b)
    {
        // 최소 비용이 최고 조상이 되게끔하자
        if (cost[a] >= cost[b])
            parent[a] = b;
        else
            parent[b] = a;
    }
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> M >> k;

    // union-find 초기화
    vector<int> parent;
    vector<bool> v;
    vector<int> cost;
    parent.push_back(0);
    v.push_back(false);
    cost.push_back(0);
    for (int i = 1; i <= N; i++)
    {
        int c;
        cin >> c;
        parent.push_back(i);
        v.push_back(false);
        cost.push_back(c);
    }

    // union
    for (int i = 0; i < M; i++)
    {
        int a, b;
        cin >> a >> b;
        unions(a, b, parent, cost);
    }

    // 친구 비용 구하기
    int sum = 0;
    for (int i = 1; i <= N; i++)
    {
        int Parent = find(i, parent);

        // cout << Parent << endl;
        //   한번도 방문하지 않은 그룹
        if (!v[Parent])
        {
            v[Parent] = true;
            // 무리 중 최소 비용인 친구 값 더하기
            sum += cost[Parent];
        }
        if (sum > k)
            break;
    }

    if (sum > k)
    {
        cout << "Oh no";
    }
    else
    {
        cout << sum;
    }
}