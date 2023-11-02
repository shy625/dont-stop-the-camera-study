#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int N, L;

vector<pair<int, int>> vec;

int SetBoard(int start)
{
    return start + L - 1;
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> L;

    int a, b;
    for (int i = 0; i < N; i++)
    {
        cin >> a >> b;
        vec.push_back(make_pair(a, b));
    }

    sort(vec.begin(), vec.end());

    int now = -1;
    int answer = 0;
    for (int i = 0; i < N; i++)
    {
        int start = vec[i].first;
        int end = vec[i].second;

        // 만약 now가 start보다 작다면 start부터 새로 커버
        if (now < start)
        {

            now = start - 1;
            while (now < end - 1)
            {
                now++;
                now = SetBoard(now);
                answer++;
            }
        }
        // now가  중간부터 시작
        else if (now < end - 1)
        {
            while (now < end - 1)
            {
                now++;
                now = SetBoard(now);
                answer++;
            }
        }
        // 이미 다음 웅덩이 범위를 커버
        else
        {
        }
    }

    cout << answer;
}