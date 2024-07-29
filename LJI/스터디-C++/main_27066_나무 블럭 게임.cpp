#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int N, K;
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N;

    vector<int> Blocks(N, 0);

    for (int i = 0; i < N; i++)
    {
        cin >> Blocks[i];
    }

    // 가장 왼쪽에 있는 두개를 계속해서 합쳐가면서 중앙값 갱신
    sort(Blocks.begin(), Blocks.end());

    int Size = Blocks.size();

    double Answer = 0.0;

    long long Sum = 0;
    for (int i = 0; i < N; i++)
    {
        Sum += Blocks[i];
    }

    if (N >= 2)
        Answer = max((double)Sum * 1.0 / N, (double)Blocks[N - 2]);
    else
        Answer = Blocks[0];

    cout << fixed;
    cout.precision(7);
    cout << Answer;
}