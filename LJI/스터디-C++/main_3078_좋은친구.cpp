#include <iostream>
#include <vector>
using namespace std;

// 이름의 길이만 따지니 걍 길이만 저장해도 될 것 같다
int N, K;
main()
{
    ios_base::sync_with_stdio(false);
    cout.tie(0);
    cin.tie(0);

    cin >> N >> K;
    vector<int> names;

    for (int i = 0; i < N; i++)
    {
        string str;
        cin >> str;

        names.push_back(str.length());
    }

    // 슬라이딩 윈도우 크기 K
    long answer = 0;
    // 이름 길이 담을 배열
    int namesLen[21] = {
        0,
    };

    // for (int i = 0; i < 21; i++)
    // {
    //     cout << namesLen[i] << " ";
    // }

    // 처음 슬라이딩 윈도우 채우기
    for (int i = 0; i <= K; i++)
    {
        namesLen[names[i]]++;
        if (namesLen[names[i]] >= 2)
        {
            answer += namesLen[names[i]] - 1;
        }
    }

    for (int i = K + 1; i < N; i++)
    {
        namesLen[names[i - K - 1]]--;
        namesLen[names[i]]++;
        if (namesLen[names[i]] >= 2)
        {
            answer += namesLen[names[i]] - 1;
        }
    }

    cout << answer;
}