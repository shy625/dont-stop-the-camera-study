#include <iostream>
#include <algorithm>
using namespace std;

int T, K;
string W;
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> T;
    int AlphaCnt[26];
    for (int t = 0; t < T; t++)
    {
        cin >> W >> K;

        if (K == 1)
        {
            cout << "1 1\n";
            continue;
        }
        int answer1 = 100000, answer2 = -1;

        int length = W.length();
        fill(&AlphaCnt[0], &AlphaCnt[26], 0);

        // 시작 초기화
        for (int i = 0; i < length; i++)
        {
            AlphaCnt[W[i] - 'a']++;
        }

        // i부터 알파벳 개수 K개까지 세기
        for (int i = 0; i < length; i++)
        {
            if (AlphaCnt[W[i] - 'a'] < K)
                continue;

            int cnt = 1;
            for (int j = i + 1; j < length; j++)
            {
                if (W[i] == W[j])
                    cnt++;

                if (cnt == K)
                {
                    int len = j - i + 1;
                    answer1 = min(answer1, len);
                    answer2 = max(answer2, len);
                    break;
                }
            }
        }

        if (answer1 == 100000 || answer2 == -1)
        {
            cout << "-1" << '\n';
        }
        else
        {
            cout << answer1 << ' ' << answer2 << '\n';
        }
    }
}