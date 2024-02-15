#include <iostream>

using namespace std;
int T;
string str;
// 양 끝점에서 시작하여 같으면 계속 진행
// 만약 다른 문자가 나오면 양쪽 중 하나의 포인터를 한칸 전진하여 유사회문 여부 체크
//  그래도 안되면 일반문자열

void CheckPalindrome(int left, int right, int passCnt, int &answer)
{
    // 두점이 교차되면 끝
    if (left >= right)
    {
        answer = passCnt;
        return;
    }

    // 두 포인터의 문자가 같은경우
    if (str[left] == str[right])
    {
        CheckPalindrome(left + 1, right - 1, passCnt, answer);
    }
    else
    {
        // 이미 한번 문자포인터를 넘겼으면 끝
        if (passCnt == 1)
        {
            return;
        }

        // 그렇지 않다면 두 포인터를 각각 옮겨서 시도
        CheckPalindrome(left + 1, right, passCnt + 1, answer);
        CheckPalindrome(left, right - 1, passCnt + 1, answer);
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

        int answer = 2;
        CheckPalindrome(0, str.length() - 1, 0, answer);
        cout << answer << '\n';
    }
}