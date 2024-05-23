#include <iostream>
#include <vector>
using namespace std;

main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    int N, M;

    cin >> N >> M;

    vector<int> Vec(N);

    for (int i = 0; i < N; i++)
    {
        cin >> Vec[i];
    }

    long long Sum = 0;

    int Min = 0;

    int Answer = 0;
    for (int i = 0; i < N; i++)
    {
        Sum += Vec[i];

        while (Sum > M)
        {
            Sum -= Vec[Min++];
        }

        Answer = max(Answer, (int)Sum);
    }

    cout << Answer;
}