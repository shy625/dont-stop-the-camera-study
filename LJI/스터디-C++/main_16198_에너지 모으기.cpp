#include <iostream>
#include <vector>
using namespace std;
int N;
vector<int> orbs;
vector<bool> visit;

void GatherEnergy(int total, int &answer)
{
    if (total > answer)
    {
        answer = total;
    }

    // 처음과 끝 제거
    for (int i = 1; i < N - 1; i++)
    {
        // 만약 현재 오브를 제거 하지 않았다면
        if (!visit[i])
        {

            int left, right;
            // 왼쪽 찾기
            for (int j = i - 1; j >= 0; j--)
            {
                if (!visit[j])
                {
                    left = orbs[j];
                    break;
                }
            }
            // 오른쪽 찾기
            for (int j = i + 1; j < N; j++)
            {
                if (!visit[j])
                {
                    right = orbs[j];
                    break;
                }
            }

            visit[i] = true;
            GatherEnergy(total + left * right, answer);
            visit[i] = false;
        }
    }
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N;

    for (int i = 0; i < N; i++)
    {
        int tmp;
        cin >> tmp;

        orbs.push_back(tmp);
        visit.push_back(false);
    }

    int answer = 0;
    GatherEnergy(0, answer);

    cout << answer;
}