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
    cin >> N >> K;

    vector<bool> visited(N + 1, false);
    vector<int> vec(N + 1);
    for (int i = 1; i <= N; i++)
    {
        cin >> vec[i];
    }

    sort(vec.begin(), vec.end());

    int answer = -1;

    for (int i = 1; i <= N; i++)
    {
        int now = vec[i];

        while (true)
        {
            // 불가능해짐
            if (now > N)
            {
                answer = 0;
                break;
            }

            // 가능한 수
            if (!visited[now])
            {
                visited[now] = true;
                break;
            }

            now += K;
        }

        if (answer != -1)
        {
            break;
        }
    }

    if (answer != 0)
    {
        answer = 1;
    }

    cout << answer;
}
