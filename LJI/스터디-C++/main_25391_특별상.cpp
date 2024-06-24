#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int N, M, K;

// 심판의 점수 순으로 정렬하여 K개의 높은 값들은 무조건 포함
// 나머지 것 중 주최자 순으로 정렬하여 M 개 뽑기

// 이유?
// 주최자 점수가 높고 심사의원 점수가 낮은 경우: 주최자 선택으로 뽑아야한다
// 주최자 점수가 낮고 심사의원 점수가 높은 경우: 주최자가 뽑지 않아도 어차피 뽑히게 됨
// 따라서 심판 점수가 높은 것은 어차피 뽑히니 미리 K개를 빼고 나머지 중 주최자 점수 순으로 정렬

bool HostCmp(pair<int, int> A, pair<int, int> B)
{
    return A.first > B.first;
}

bool RefreeCmp(pair<int, int> A, pair<int, int> B)
{
    return A.second > B.second;
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> M >> K;

    vector<pair<int, int>> Stu;
    int a, b;
    for (int i = 0; i < N; i++)
    {
        cin >> a >> b;
        Stu.push_back(make_pair(a, b));
    }

    long long answer = 0;
    sort(Stu.begin(), Stu.end(), RefreeCmp);

    for (int i = 0; i < K; i++)
    {
        answer += Stu[i].first;
    }

    // 나머지 심판순 정렬
    sort(Stu.begin() + K, Stu.end(), HostCmp);
    for (int i = K; i < M + K; i++)
    {
        answer += Stu[i].first;
    }

    cout << answer;
}