#include <iostream>
#include <vector>
using namespace std;
int n, x, y, MAX, answer;
vector<bool> Visited;
vector<int> Langford;

void MakeLangford(int idx)
{
    // 종료
    if (idx == MAX + 1)
    {
        answer++;
        return;
    }

    // langford[idx]의 값이 0이면 현재 위치와 맞대응되는 위치의 값 넣기
    if (Langford[idx] == 0)
    {
        for (int i = 1; i <= n; i++)
        {
            if (!Visited[i] && (idx + i + 1 <= MAX) && Langford[idx + i + 1] == 0)
            {
                Visited[i] = true;
                Langford[idx] = i;
                Langford[idx + i + 1] = i;

                MakeLangford(idx + 1);

                Visited[i] = false;
                Langford[idx] = 0;
                Langford[idx + i + 1] = 0;
            }
        }
    }
    else
    {
        MakeLangford(idx + 1);
    }
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> n >> x >> y;
    MAX = 2 * n;
    Langford = vector<int>(MAX + 1, 0);
    Visited = vector<bool>(n + 1, false);

    // x ,y는 고정 수
    int diff = y - x - 1;
    Langford[y] = diff;
    Langford[x] = diff;
    Visited[diff] = true;

    answer = 0;
    MakeLangford(1);

    cout << answer;
}