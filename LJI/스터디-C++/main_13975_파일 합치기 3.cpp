#include <iostream>
#include <queue>
using namespace std;
int T, K;
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> T;

    for (int t = 0; t < T; t++)
    {
        cin >> K;

        priority_queue<long long, vector<long long>, greater<long long>> PQ;

        for (int i = 0; i < K; i++)
        {
            int tmp;
            cin >> tmp;
            PQ.push(tmp);
        }

        long long answer = 0;

        while (PQ.size() != 1)
        {
            long long a = PQ.top();
            PQ.pop();

            long long b = PQ.top();
            PQ.pop();

            long long sum = a + b;

            answer += sum;
            PQ.push(sum);
        }

        cout << answer << endl;
    }
}