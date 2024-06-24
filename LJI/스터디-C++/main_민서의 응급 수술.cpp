#include <iostream>
#include <vector>
using namespace std;
// 유니온 파인드를 통해 그룹을 나누는 작업
// 만약 같은 그룹이면 연결 끊는 작업
// 최종 그룹 수를 서로 연결하는 작업을 하면
// 트리형태로 정렬 가능
int N, M;
vector<int> Parents;
int Find(int a)
{
    if (Parents[a] == a)
        return a;
    else
        return Parents[a] = Find(Parents[a]);
}

bool Union(int a, int b)
{
    int aParent = Find(a);
    int bParent = Find(b);

    if (aParent == bParent)
    {
        return false;
    }

    if (aParent < bParent)
    {
        Parents[aParent] = bParent;
    }
    else
    {
        Parents[bParent] = aParent;
    }
    return true;
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> M;

    // 부모 배열 초기화
    for (int i = 0; i <= N; i++)
    {
        Parents.push_back(i);
    }

    // 제대로 연결된 횟수
    // 끊은 횟수= M-연결된 횟수
    // 연결해야하는 회수= N-연결된 횟수-1
    int Cnt = 0;
    // 연결관계 입력
    int u, v;
    for (int i = 0; i < M; i++)
    {
        cin >> u >> v;
        if (Union(u, v))
            Cnt++;
    }

    int CutCnt = M - Cnt;
    int LinkCnt = N - Cnt - 1;
    cout << CutCnt + LinkCnt;
}