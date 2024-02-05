#include <iostream>

using namespace std;
int T;
int N;
string str;

// 시작 인덱스부터 패턴인지를 체크
// 패턴이 제대로 완성된 경우 다음 위치에 인덱스에서 PatternCheck 이어서
// 만약 문자열의 길이이 N으로 시작한다면 이전에 패턴들이 온전히 완성됐다는 뜻이므로 isPattern 변경
void PatternCheck(int startIdx, bool &isPattern)
{
    if (isPattern)
        return;

    if (startIdx == N)
    {
        isPattern = true;
        return;
    }

    // 100+1+ 패턴
    if (str[startIdx] == '1')
    {
        int idx = startIdx + 1;

        // 00 체크
        for (int i = 0; i < 2; i++)
        {

            // idx가 문자열 길이를 넘어서거나 0이 아니면 패턴 불가
            if ((idx >= N) || (str[idx] != '0'))
            {
                return;
            }
            idx++;
        }

        // 뒤에 계속해서 나오는 0 체크-> 1나올 때까지 반복
        while (idx < N && (str[idx] == '0'))
        {
            idx++;
        }

        if (idx >= N)
            return;
        // 1뒤에 1이 나오면 계속해서 패턴 체크 새로 발생
        do
        {
            PatternCheck(idx + 1, isPattern);
            idx++;
        } while (idx < N && (str[idx] == '1'));
    }
    // 01패턴
    else
    {
        if ((startIdx + 1 < N) && (str[startIdx + 1] == '1'))
        {
            PatternCheck(startIdx + 2, isPattern);
        }
    }
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    cin >> T;

    for (int t = 0; t < T; t++)
    {
        cin >> str;
        N = str.length();
        bool isPattern = false;
        // cout << N << endl;

        PatternCheck(0, isPattern);

        if (isPattern)
            cout << "YES\n";
        else
            cout << "NO\n";
    }
}