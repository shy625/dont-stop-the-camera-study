#include <iostream>
#include <vector>
using namespace std;
// 1234변중 2개의 길이 나온 곳에서 구해야할 것
// 어느 꼭지점에서 시작하든 같은 방향으로 돌기에 같은 형태 관측이 가능
// 반복되서 주어지는 라인에 가운데 두 변의 길이가 필요

int K;
main()
{
    ios_base::sync_with_stdio(false);
    cout.tie(0);
    cin.tie(0);

    cin >> K;

    vector<int> vec[5];

    vector<pair<int, int>> order; // 등장한 변과 길이 순서 기록

    for (int i = 0; i < 6; i++) // 6변//반복되는 두변이 있는 위치를 알아야한다
    {
        int line, length;
        cin >> line >> length;

        vec[line].push_back(length);
        order.push_back(make_pair(line, length));
    }

    int bigX, bigY, smallX, smallY;
    int bigSquare;
    int smallSquare;

    // 변 길이 구하기
    for (int i = 0; i < 6; i++)
    {
        // 현재 인덱스==현재 인덱스 +2 &&다음 인덱스==다음 인덱스+2 인 line을 가진 곳을 찾아야한다

        int index = i;
        int nextIndex = (i + 1) % 6;
        int index2 = (index + 2) % 6;
        int nextIndex2 = (nextIndex + 2) % 6;

                if (order[index].first == order[index2].first && order[nextIndex].first == order[nextIndex2].first)
        {
            smallX = order[nextIndex].second;
            smallY = order[index2].second;
            smallSquare = smallX * smallY;

            bigX = order[(i + 4) % 6].second;
            bigY = order[(i + 5) % 6].second;
            bigSquare = bigX * bigY;
        }
    }

    cout << K * (bigSquare - smallSquare);
}