#include <iostream>
#include <vector>
// 유니온 파인드로 연결된 건물들은 하나로 계산
// 연결된 건물은 이동 횟수 세지 않아도 된다.
using namespace std;
int N, M;

vector<int> Parents;

int Find(int A)
{
    if (Parents[A] == A)
        return A;
    else
        return Parents[A] = Find(Parents[A]);
}
void Union(int A, int B)
{
    int AParent = Find(A);
    int BParent = Find(B);

    if (AParent != BParent)
    {
        Parents[AParent] = BParent;
    }
}

main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> M;

    // 부모배열 초기화
    for (int i = 0; i <= N; i++)
    {
        Parents.push_back(i);
    }

    for (int i = 0; i < M; i++)
    {
        int A, B;

        cin >> A >> B;
        Union(A, B);
    }

    int Answer = 0;

    int Code;
    // 첫 강의 입력
    cin >> Code;
    int Pre = Find(Code);
    for (int i = 1; i < N; i++)
    {
        cin >> Code;
        int Now = Find(Code);

        // 같으면 밖으로 나갈 필요 없다
        if (Pre != Now)
        {
            Answer++;
            Pre = Now;
        }
    }

    cout << Answer;
}