#include <iostream>
#include <vector>
#include <algorithm>
#include <map>
using namespace std;

// 특정 구간이 불가능해지는 경우는?
// 그 구간에 시작 시간보다 앞서 시작한 경우
// 그 구간에 끝나는 시간보다 늦게 끝난 경우
// 전체 N에서 두 경우를 빼주면 내부에 있는 값을 구할 수 있다.

// 단, 해당 구간을 포함하는 더 큰 구간은 중복되서 카운팅 되는데
// 어차피 정답은 가장 많은 것을 포함하는 구간이기에
// 더 큰 구간에서 정상적인 답을 얻게 되기에 오류 정정 필요 X
int N;

bool EndCmp(pair<int, int> A, pair<int, int> B)
{

    return A.second < B.second;
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N;

    vector<int> StartVec;
    vector<int> EndVec;
    vector<pair<int, int>> Vec;
    for (int i = 0; i < N; i++)
    {
        int Start, End;
        cin >> Start >> End;
        StartVec.push_back(Start);
        EndVec.push_back(End);
        Vec.push_back(make_pair(Start, End));
    }

    // 시작, 종료 시간을 오름차순으로 정렬
    sort(StartVec.begin(), StartVec.end(), less<int>());
    sort(EndVec.begin(), EndVec.end(), less<int>());

    map<int, int> StartMap;
    map<int, int> EndMap;

    // 굳이 또 순회하지 않기 위해 각 구간 이전에 나온 구간의 개수를 저장
    for (int i = 0; i < N; i++)
    {
        StartMap[StartVec[i]] = i;
        EndMap[EndVec[i]] = i;
    }

    int MAX = 0;
    for (int i = 0; i < N; i++)
    {
        int Start = Vec[i].first;
        int End = Vec[i].second;

        // 현재 구간의 시작보다 먼저 시작한 구간의 수
        int OutOfStart = StartMap[Start];
        // 현재 구간의 종료보다 나중에 종료된 구간의 수
        int OutOfEnd = N - (EndMap[End] + 1);

        // 위에 구한 자료로 최대 포함 구간 계산
        MAX = max(MAX, N - 1 - (OutOfEnd + OutOfStart));
    }

    cout << MAX;
}