#include <iostream>
#include <cmath>
// 1의 자리: 9개 -> 9*1
// 10의 자리: 90개 -> 90*2
// 100의 자리:900개 -> 900*3
using namespace std;
int N;
long long K;
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> K;

    int idx = 1;
    long long digit = 9;
    long long sum = 0;

    // 몇번째 자리인지 알아내기
    while (true)
    {
        sum += idx * digit;

        if (sum >= K)
        {
            sum -= idx * digit;
            break;
        }

        idx++;
        digit *= 10;
    }

    // 단위별 시작 숫자//1,10,100,1000....
    float tmp = pow(10, idx - 1);
    int start = (int)tmp;

    // K에서 그전 자리수들의 합을 뺸 나머지 값
    int least = K - sum - 1;

    // k가 나타내고 있는 실제 숫자
    int num = start + (least / idx);

    // num이 N보다 크면 -1출력
    if (num > N)
    {
        cout << "-1";
        return 0;
    }

    // num에서 몇번째 자리의 숫자를 출력해야하는지
    int digitIdx = least % idx;

    string answerNum = to_string(num);

    cout << answerNum[digitIdx];
}