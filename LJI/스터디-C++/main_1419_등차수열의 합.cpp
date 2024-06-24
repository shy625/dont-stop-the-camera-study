#include <iostream>

using namespace std;
int l, r, k;

// x는 k개 존재 x*k
// 공차 d의 총 개수는  k(k-1)/2

// d랑 x값만 바꿔가면서 만족하는지 체크
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> l >> r >> k;

    // num이 가능한지 체크
    int Answer = 0;

    if (k == 2)
    {
        // 공차 d가 하나만 존재
        // 따라서 2*x+d가 성립하는 경우들 전부 포함
        // x,d는 자연수니까 3이상의 모든 수들

        if (r >= 3)
        {
            int MAX = r;
            int MIN = max(3, l);

            Answer = MAX - MIN + 1;
        }
    }
    else if (k == 3)
    {
        // 3*2/2 -> 3 이기에 3의 배수들은 전부 성립
        // 3(x+d)이므로 6이상의 3 배수

        // for (int i = l; i <= r; i++)
        // {
        //     if (i < 6)
        //         continue;

        //     if (i % 3 == 0)
        //         Answer++;
        // }

        if (r >= 6)
        {
            int MAX = r / 3;
            int MIN = (max(6, l) - 1) / 3;

            Answer = MAX - MIN;
        }
    }
    else if (k == 4)
    {
        // 가능한 경우들
        // 10 16 22 28...
        // 14 20 26 32...
        // 18 24 30 36...
        // 22 28 34..-> 여기부턴 첫째줄과 겹친다
        // 즉 6으로 나눴을 때 나머지 기준으로 생각하면 된다
        // 단 각 자리의 최소 시작값은 고려, 10,14,18

        for (int i = l; i <= r; i++)
        {
            bool possible = false;

            int tmp = i % 6;
            if (tmp == 4)
            {
                if (i >= 10)
                    Answer++;
            }
            else if (tmp == 2)
            {
                if (i >= 14)
                    Answer++;
            }
            else if (tmp == 0)
            {
                if (i >= 18)
                    Answer++;
            }
        }
    }
    else if (k == 5)
    {
        // 위와 같은 방식으로 찾으면
        // 5(x+2d)이므로 15 이상의 5배수
        // for (int i = l; i <= r; i++)
        // {
        //     if (i < 15)
        //         continue;

        //     if (i % 5 == 0)
        //         Answer++;
        // }

        if (r >= 15)
        {
            int MAX = r / 5;
            int MIN = (max(15, l) - 1) / 5;

            Answer = MAX - MIN;
        }
    }

    cout << Answer;
}
