#include <iostream>
#include <vector>
#include <algorithm>
#include <map>
using namespace std;
int N, M;

bool CheckFriend(int a, int b, vector<vector<int>> &Graph)
{
    for (int i = 0; i < Graph[a].size(); i++)
    {
        if (Graph[a][i] == b)
        {
            return true;
        }
    }

    return false;
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    cin >> N >> M;

    // 그래프 만들기
    vector<vector<int>> Graph(N + 1);

    int a, b;
    for (int i = 0; i < M; i++)
    {
        cin >> a >> b;

        Graph[a].push_back(b);
        Graph[b].push_back(a);
    }

    // 이미 체크한 세 친구는 중복체크하지 않기 위한 Map체크
    map<vector<int>, bool> CheckMap;

    // A,B,C 구하기
    vector<int> ThreeMan(3);

    int answer = INT32_MAX;
    for (int i = 1; i <= N; i++)
    {

        int len = Graph[i].size();

        // A와 연결된 두 명을 각각 고르기
        for (int j = 0; j < len; j++)
        {
            for (int k = j + 1; k < len; k++)
            {

                ThreeMan[0] = i;
                ThreeMan[1] = Graph[i][j];
                ThreeMan[2] = Graph[i][k];

                // B와 C도 친구인지 체크
                if (!CheckFriend(ThreeMan[1], ThreeMan[2], Graph))
                    continue;

                answer = min(answer, (int)(Graph[ThreeMan[0]].size() +
                                           Graph[ThreeMan[1]].size() +
                                           Graph[ThreeMan[2]].size() - 6));
            }
        }
    }

    if (answer == INT32_MAX)
        cout << "-1";
    else
        cout << answer;
}