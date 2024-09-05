#include <iostream>
#include <set>
using namespace std;

int T, Len;
string n;

// 주어진 숫자 사이에 +를 각각 넣어서 얻은 결과물이 각 자리수의 제곱을 넘지 않는지 체크
// 1과 0으로 이루어진 수일 경우 Hello, BOJ 2023!을 출력

void MakeSum(int cnt, int prev, int total, set<int> &Set, int &Answer)
{
    if (cnt == Len - 1)
    {
        // 무조건 더하기만
        int now = prev + n[cnt] - '0';
        total += now;

        long long sum = 0;
        int idx = 1;
        while (sum <= (long long)total)
        {
            if (Set.find(idx) != Set.end())
            {
                idx++;
                continue;
            }
            sum = 0;
            for (int i = 0; i < Len; i++)
            {
                int num = n[i] - '0';
                long long now = num;
                for (int j = 1; j < idx; j++)
                {
                    now *= (long long)num;
                }
                sum += now;
            }

            if (sum == (long long)total)
            {
                Set.insert(idx);
                Answer++;
                break;
            }
            idx++;
        }

        return;
    }

    // 현재 자리 뒤에 더하기 넣기
    int now = prev + (n[cnt] - '0');
    MakeSum(cnt + 1, 0, total + now, Set, Answer);
    // 현재 자리 뒤에 더하기 넣지 않기
    MakeSum(cnt + 1, now * 10, total, Set, Answer);
}

main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> T;
    for (int t = 0; t < T; t++)
    {

        cin >> n;
        Len = n.length();
        bool CheckHello = true;
        for (char c : n)
        {
            if (!(c - '0' == 0 || c - '0' == 1))
            {
                CheckHello = false;
                break;
            }
        }

        if (CheckHello)
        {
            cout << "Hello, BOJ 2023!\n";
            continue;
        }

        // 자리 사이에 + 넣거나 패스
        int Answer = 0;
        // 이미 방법을 찾은 m의 값들 기록
        set<int> Set;
        MakeSum(0, 0, 0, Set, Answer);
        cout << Answer << '\n';
    }
}