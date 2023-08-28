#include <iostream>

using namespace std;
int N, M, C, A, B;
int map[11][11];
bool v[11];
int answer;

void dfs(int node, int maxC, int sum)
{ // 끝나는 경우
    if (B == node)
    {
        answer = min(answer, maxC);
        return;
    }

    for (int i = 1; i <= N; i++)
    {
        // 현재 노드이면 패스
        if (i == node)
            continue;
        // 갈 수 있는 길이 없다면 패스
        if (map[node][i] == 0)
            continue;
        // 방문했다면 패스
        if (v[i])
            continue;

        // newSum이 C를 넘으면 패스
        int newSum = sum + map[node][i];
        if (newSum > C)
            continue;

        v[i] = true;
        dfs(i, max(maxC, map[node][i]), newSum);
        v[i] = false;
    }
}

main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> M >> A >> B >> C;

    fill(&map[0][0], &map[10][11], 0);
    fill(&v[0], &v[11], false);

    answer = 1001;
    for (int i = 1; i <= M; i++)
    {
        int a, b, c;
        cin >> a >> b >> c;
        map[a][b] = c;
        map[b][a] = c;
    }

    v[A] = true;
    dfs(A, 0, 0);

    if (answer == 1001)
    {
        cout << -1;
    }
    else
    {
        cout << answer;
    }
}