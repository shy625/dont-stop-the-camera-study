#include <iostream>
#include <vector>
using namespace std;
int N;

void DFS(int cnt, int total, vector<int> &Potion, vector<vector<int>> &Sale, vector<bool> &Check, vector<int> &SaleCost, int &Answer)
{
    if (cnt == N)
    {

        Answer = min(Answer, total);
        return;
    }

    for (int i = 1; i <= N; i++)
    {
        if (Check[i])
            continue;

        // 해당 구매 처리 후 세일 정보 추가
        Check[i] = true;

        int Cost = max(1, Potion[i] - SaleCost[i]);
        for (int j = 1; j <= N; j++)
        {
            SaleCost[j] += Sale[i][j];
        }
        // DFS
        DFS(cnt + 1, total + Cost, Potion, Sale, Check, SaleCost, Answer);
        // 초기화
        for (int j = 1; j <= N; j++)
        {
            SaleCost[j] -= Sale[i][j];
        }
        Check[i] = false;
    }
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N;

    vector<int> Potion(N + 1, 0);

    for (int i = 1; i <= N; i++)
    {
        cin >> Potion[i];
    }

    // 세일 정보
    // i를 샀을 때 j가 Sale[i][j] 만큼 세일
    vector<vector<int>> Sale(N + 1, vector<int>(N + 1, 0));
    for (int i = 1; i <= N; i++)
    {
        int j, cost;
        int P;
        cin >> P;

        for (int p = 0; p < P; p++)
        {
            cin >> j >> cost;
            Sale[i][j] = cost;
        }
    }

    int Answer = 100000;
    vector<bool> Check(N + 1, false);
    vector<int> SaleCost(N + 1, 0);

    DFS(0, 0, Potion, Sale, Check, SaleCost, Answer);
    cout << Answer;
}