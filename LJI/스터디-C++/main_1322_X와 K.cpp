#include <iostream>

using namespace std;
long long int X, K;

// X의 비트에서 0인 부분들로만 구성된 자연수 중 K번째를 구해야한다
// 101(5) + 10(2)=111(7)

// K에 비트 중 1일 때 X의 비트에서 0인 부분부터 채워야할 것

// X중 now의 인덱스부터 0인 비트의 인덱스 찾아내기
int FindXidx(int now)
{

    while (true)
    {
        if (X & ((long long)1 << now))
            now++;
        else
            return now;
    }
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> X >> K;
    long long int answer = 0;
    int kIdx = 0;
    int xIdx = 0;
    while (((long long)1 << kIdx) <= K)
    {

        xIdx = FindXidx(xIdx);
        // K에서 1<<kIdx 위치가 0이면 X에서 0인 위치 중에서 해당 인덱스 위치도 0이여야한다.
        if (K & (long long)1 << kIdx)
        {
            answer += (long long)1 << xIdx;
            xIdx++;
        }
        else
        {
            xIdx++;
        }
        kIdx++;
    }

    cout << answer;
}