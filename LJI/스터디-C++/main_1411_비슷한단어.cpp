#include <iostream>
#include <map>
using namespace std;

// 단어를 패턴화 시켜야하지 않을까
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    int N;

    cin >> N;

    map<string, int> patterns;
    for (int n = 0; n < N; n++)
    {
        map<char, char> charMap; // 단어를 a,b,c순으로 치환하기 위함
        int alpha = 0;

        string str;
        cin >> str;
        int len = str.length();

        string newStr = "";
        for (int i = 0; i < len; i++)
        {
            // 만약 해당 알파벳이 charMap에 없다면? 추가
            if (charMap.find(str[i]) == charMap.end())
            {
                charMap.insert({str[i], (char)('a' + alpha++)});
                newStr += charMap[str[i]];
            }
            else
            { // 맵에 존재
                newStr += charMap[str[i]];
            }
        }

        // cout << newStr << endl;
        if (patterns.find(newStr) == patterns.end())
        {
            patterns.insert({newStr, 1});
        }
        else
        {
            patterns[newStr] += 1;
        }
    }

    // 단어 쌍 구하기
    int answer = 0;
    for (const auto &item : patterns)
    {
        if (item.second <= 1)
            continue;

        int sum = item.second - 1;

        for (int i = sum - 1; i >= 1; i--)
        {
            sum += i;
        }

        answer += sum;
    }
    cout << answer;
}