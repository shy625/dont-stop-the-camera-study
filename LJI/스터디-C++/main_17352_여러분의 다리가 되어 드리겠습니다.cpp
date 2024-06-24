#include <iostream>
#include <vector>
using namespace std;

// union find

vector<int> Parent;

int Find(int a)
{
    if (Parent[a] == a)
        return a;
    else
        return Parent[a] = Find(Parent[a]);
}

void Union(int a, int b)
{
    int aP = Find(a);
    int bP = Find(b);

    if (aP != bP)
    {
        if (aP <= bP)
            Parent[aP] = bP;
        else
            Parent[bP] = aP;
    }
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    int N;
    cin >> N;
    for (int i = 0; i <= N; i++)
    {
        Parent.push_back(i);
    }

    int A, B;
    for (int i = 0; i < N - 2; i++)
    {
        cin >> A >> B;
        Union(A, B);
    }

    for (int i = 1; i <= N; i++)
    {
        for (int j = 1; j <= N; j++)
        {
            if (Find(Parent[i]) != Find(Parent[j]))
            {
                cout << i << ' ' << j;
                return 0;
            }
        }
    }
}