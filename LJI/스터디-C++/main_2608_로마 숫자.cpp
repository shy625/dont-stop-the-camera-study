#include <iostream>
#include <map>
#include <vector>
using namespace std;

map<char, int> romeNum = {
    {'I', 1},
    {'V', 5},
    {'X', 10},
    {'L', 50},
    {'C', 100},
    {'D', 500},
    {'M', 1000},
};

pair<string, int> romeArr[] = {
    {"M", 1000},
    {"CM", 900},
    {"D", 500},
    {"CD", 400},
    {"C", 100},
    {"XC", 90},
    {"L", 50},
    {"XL", 40},
    {"X", 10},
    {"IX", 9},
    {"V", 5},
    {"IV", 4},
    {"I", 1},
};

// IV 같은 숫자는 V-I의 값을 취함
// 즉 뒤에 값이 더 크면 앞의 값은 빼주면 된다
int RomeToNum(string str)
{
    int sum = 0;
    int size = str.size();
    for (int i = 0; i < size - 1; i++)
    {
        // 뒤의 값이 크면 -
        if (romeNum[str[i]] < romeNum[str[i + 1]])
        {
            sum -= romeNum[str[i]];
        }
        else
        {
            sum += romeNum[str[i]];
        }
    }

    sum += romeNum[str[size - 1]];
    return sum;
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    string A, B;
    cin >> A >> B;

    int ANum = RomeToNum(A);
    int BNum = RomeToNum(B);
    int answerNum = ANum + BNum;
    cout << answerNum << endl;

    // 숫자를 문자열로 변환
    vector<string> answerStr;
    int tmp = answerNum;
    while (tmp != 0)
    {
        for (int i = 0; i < 13; i++)
        {
            if (tmp >= romeArr[i].second)
            {
                tmp -= romeArr[i].second;
                answerStr.push_back(romeArr[i].first);

                break;
            }
        }
    }

    for (string str : answerStr)
    {
        cout << str;
    }
}