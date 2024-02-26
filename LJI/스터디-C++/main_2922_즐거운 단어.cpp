#include <iostream>

using namespace std;
string str;
int len;
char vowel[] = {'A', 'E', 'I', 'O', 'U'};

// 모음 자음 연속 3번 나오지 않게 체크, L이 나왔는지 체크, 하면서 진행
void CheckStr(int idx, int vowelCnt, int consonantCnt, bool LCheck, long long cnt, long long &answer)
{
    if (vowelCnt == 3 || consonantCnt == 3)
    {
        return;
    }
    // 끝 조건
    if (idx == len)
    {
        if (LCheck)
        {
            answer += cnt;
        }
        return;
    }

    // 먼저 _가 나올 경우
    if (str[idx] == '_')
    {
        // 모음 경우
        CheckStr(idx + 1, vowelCnt + 1, 0, LCheck, cnt * 5, answer);
        // l 고정 경우
        CheckStr(idx + 1, 0, consonantCnt + 1, true, cnt, answer);
        // 나머지 자음 경우
        CheckStr(idx + 1, 0, consonantCnt + 1, LCheck, cnt * 20, answer);
    }
    // 기본 알파벳이 나올 경우
    else
    {
        bool isVowel = false;
        for (int i = 0; i < 5; i++)
        {
            if (str[idx] == vowel[i])
            {
                isVowel = true;
                break;
            }
        }

        // 모음인 경우
        if (isVowel)
        {
            CheckStr(idx + 1, vowelCnt + 1, 0, LCheck, cnt, answer);
        }
        else
        {
            if (str[idx] == 'L')
            {
                // cout << "is Here" << endl;
                CheckStr(idx + 1, 0, consonantCnt + 1, true, cnt, answer);
            }
            else
            {
                CheckStr(idx + 1, 0, consonantCnt + 1, LCheck, cnt, answer);
            }
        }
    }
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> str;
    len = str.length();
    long long answer = 0;

    CheckStr(0, 0, 0, false, 1, answer);
    cout << answer;
}