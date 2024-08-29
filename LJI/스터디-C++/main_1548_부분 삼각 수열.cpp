#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int N;

// 오름차순으로 정렬 된 수열에서 (i<j(i다음 작은수)<k)라고 할 때
// i+j>k면 모든 경우가 성립
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N;

    if (N <= 2)
    {
        cout << N;
        return 0;
    }
    vector<int> Seq(N);
    for (int i = 0; i < N; i++)
    {
        cin >> Seq[i];
    }

    sort(Seq.begin(), Seq.end());
    int Answer = 0;

    for (int start = 0; start < N; start++)
    {
        int second = start + 1;

        int Cnt = 2;
        for (int end = start + 2; end < N; end++)
        {
            if (Seq[start] + Seq[second] > Seq[end])
            {
                Cnt++;
            }
            else
            {
                break;
            }
        }

        Answer = max(Answer, Cnt);
    }

    cout << Answer;
}