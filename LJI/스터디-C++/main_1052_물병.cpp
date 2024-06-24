#include <iostream>

using namespace std;
// 만들 수 없는 경우의 수는 뭐지? 없지 않나?
// N의 개수가 2의 n승개일 경우 물병의 개수를 한개까지 줄일 수 있다
// 일단 N개를 최대한 줄인뒤 작업 시작해야하지 않을까

// 1짜리 N개일떄 2로 나누면? 2,2,2... 나머지가 1이면 1짜리 물병 하나 남는 것
// 마찬가지로 진행해서 2로 나누면? 4,4,4... 나머지가 1일때 2짜리 물병 하나 남음
// 같은 식으로 반복하자
int N, K;
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> K;

    if (N <= K)
    {
        cout << '0';
        return 0;
    }

    // K개 이하가 아니면 N에 한개씩 추가해주기
    int answer = 0;

    while (true)
    {
        int remainCnt = 0;
        int newN = N;
        while (newN > 0)
        {
            if (newN % 2 == 1)
            {
                remainCnt++;
            }
            newN /= 2;
        }

        // 끝내는 조건
        if (remainCnt <= K)
        {
            cout << answer;
            return 0;
        }

        // 못끝냈다면 N++;
        N++;
        answer++;
    }
}