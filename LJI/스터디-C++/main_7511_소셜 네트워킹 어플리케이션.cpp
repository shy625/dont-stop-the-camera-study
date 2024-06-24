#include <iostream>
#include <vector>
using namespace std;
int T, n, k, m;

vector<int> parent;

int Find(int a)
{
    if (parent[a] == a)
        return a;
    else
        return parent[a] = Find(parent[a]);
}

void Union(int a, int b)
{
    a = Find(a);
    b = Find(b);

    if (a > b)
    {
        parent[a] = b;
    }
    else if (a < b)
    {
        parent[b] = a;
    }
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    cin >> T;

    for (int t = 1; t <= T; t++)
    {
        cout << "Scenario " << t << ':' << '\n';

        cin >> n >> k;

        parent = vector<int>(n, 0);
        for (int i = 0; i < n; i++)
        {
            parent[i] = i;
        }

        // union
        int a, b;
        for (int i = 0; i < k; i++)
        {
            cin >> a >> b;

            Union(a, b);
        }

        cin >> m;
        for (int i = 0; i < m; i++)
        {
            cin >> a >> b;

            if (Find(a) == Find(b))
            {
                cout << 1 << '\n';
            }
            else
            {
                cout << 0 << '\n';
            }
        }

        cout << '\n';
    }
}