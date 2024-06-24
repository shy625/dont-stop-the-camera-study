#include <iostream>
#include <vector>
#include <cmath>
using namespace std;

vector<vector<int>> wheelVec(4, vector<int>(8, 0));
// 각 톱니바퀴의 현재 왼쪽 idx
vector<int> Left(4, 6);
int K, num, dir;

void Spin(int now, int dir, int pre)
{

    // 회전
    // 시계 방향
    int originL = wheelVec[now][Left[now]];
    int originR = wheelVec[now][(Left[now] + 4) % 8];
    if (dir == 1)
    {
        Left[now] = (Left[now] + 7) % 8;
    }
    // 반시계 방향
    else
    {
        Left[now] = (Left[now] + 1) % 8;
    }

    // 맞닿은 극이 다르면 다음 거 회전
    // 이전 톱니바퀴 번호의 따라 다음 것 정함

    // 왼쪽//왼쪽 톱니바퀴의 오른쪽과 비교
    if ((now - 1 >= 0) && (now - 1 != pre))
    {
        if (originL != wheelVec[now - 1][(Left[now - 1] + 4) % 8])
        {
            Spin(now - 1, dir != 1, now);
        }
    }

    // 오른쪽//오른 톱니바퀴의 왼쪽과 비교
    if ((now + 1 < 4) && (now + 1 != pre))
    {
        if (originR != wheelVec[now + 1][Left[now + 1] % 8])
        {
            Spin(now + 1, dir != 1, now);
        }
    }
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    for (int i = 0; i < 4; i++)
    {
        string tmp;
        cin >> tmp;
        for (int j = 0; j < 8; j++)
        {
            wheelVec[i][j] = tmp[j] - '0';
        }
    }

    cin >> K;

    for (int i = 0; i < K; i++)
    {
        cin >> num >> dir;
        num--;

        Spin(num, dir, -1);
    }

    // 점수 계산
    int answer = 0;
    for (int i = 0; i < 4; i++)
    {
        if (wheelVec[i][(Left[i] + 2) % 8])
        {
            answer += pow(2, i);
        }
    }

    cout << answer;
}