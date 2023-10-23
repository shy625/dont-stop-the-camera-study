#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
// 이미 정해진 그룹들이 있다
// Union-Find를 통한 최소 그룹 만들기
int N, M;

struct Edge
{
    int A;
    int B;
    int Cost;
};

vector<Edge> Graph;
int Parent[10001];

int Find(int A)
{
    if (Parent[A] == A)
        return A;
    else
        return Parent[A] = Find(Parent[A]);
}

bool Union(int A, int B)
{
    int AParent = Find(A);
    int BParent = Find(B);

    if (AParent == BParent)
    {
        return false;
    }
    else
    {
        if (AParent < BParent)
        {
            Parent[AParent] = BParent;
        }
        else
        {
            Parent[BParent] = AParent;
        }
        return true;
    }
}

bool cmp(Edge A, Edge B)
{
    return A.Cost > B.Cost;
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> M;

    // parent 초기화
    for (int i = 1; i <= N; i++)
    {
        Parent[i] = i;
    }

    // 관계 정보 받으며 1(성사된 사랑 관계)는 바로 유니온 해버리기
    int A, B, Cost, D;
    for (int i = 0; i < M; i++)
    {
        cin >> A >> B >> Cost >> D;

        if (D == 1)
        {
            Union(A, B);
        }
        else
        {
            Graph.push_back(Edge{A, B, Cost});
        }
    }
    // 버리는 사랑관계를 최소값으로 하고 싶기에 Cost값이 높은 순 정렬
    sort(Graph.begin(), Graph.end(), cmp);

    // 꺼내면서 Union되는 것은 패스
    // Union되지 못하면 버려진 사랑 관계이니 정답값에 더하기
    int answer = 0;
    for (Edge Now : Graph)
    {
        A = Now.A;
        B = Now.B;
        // Union이 실패했으면 정답값에 더해주기
        if (!Union(A, B))
        {
            answer += Now.Cost;
        }
    }

    cout << answer;
}