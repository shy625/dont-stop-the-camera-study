#include <iostream>
#include <vector>
#include <set>
using namespace std;

int N, K;
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> K;

    vector<int> Request(N, 0);

    for (int i = 0; i < N; i++)
    {
        cin >> Request[i];
    }

    // K개의 구간 내에서 최소값 중 가장 큰 값이 정답
    // multiset을 사용하면 중복 요소를 허용하고 자동으로 오름차순으로 정렬해서 편리하게 사용가능

    multiset<int> MultiSet;

    // K개 기록

    for (int i = 0; i < K; i++)
    {
        MultiSet.insert(Request[i]);
    }

    int Answer = *MultiSet.begin();

    // i-K의 원소는 빼고 i위치의 원소는 삽입한다
    for (int i = K; i < N; i++)
    {
        MultiSet.erase(MultiSet.find(Request[i - K]));
        MultiSet.insert(Request[i]);

        Answer = max(Answer, *MultiSet.begin());
    }

    cout << Answer;
}