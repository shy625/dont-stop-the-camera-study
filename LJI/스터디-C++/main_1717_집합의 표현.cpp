#include <iostream>
#include <vector>
using namespace std;
int n, m;

// union-find

vector<int> Parents;
int Find(int a)
{
    if (Parents[a] == a)
        return a;
    else
        return Parents[a] = Find(Parents[a]);
}

void Union(int a, int b)
{
    int ParentA = Find(a);
    int ParentB = Find(b);

    if (ParentA > ParentB)
    {
        Parents[ParentA] = ParentB;
    }
    else if (ParentA < ParentB)
    {
        Parents[ParentB] = ParentA;
    }
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> n >> m;

    for (int i = 0; i <= n; i++)
    {
        Parents.push_back(i);
    }

    for (int i = 0; i < m; i++)
    {
        int order, a, b;
        cin >> order >> a >> b;

        if (order == 0)
        {
            Union(a, b);
        }
        else
        {
            if (Find(a) == Find(b))
            {
                cout << "YES\n";
            }
            else
            {
                cout << "NO\n";
            }
        }
    }
}