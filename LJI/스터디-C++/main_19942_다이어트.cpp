#include <iostream>
#include <vector>
using namespace std;
int N;

int foods[16][5]; // 음식 기록
bool v[16];       // visit 배열
int mins[4];

void Pick(int cnt, vector<int> sum, int &answer, string &pickStr)
{
    // 계산
    if (cnt == N)
    {
        // 먼저 모든sum이 최소 조건 넘겼는지 체크
        bool bComplete = true;
        for (int i = 0; i < 4; i++)
        {
            if (mins[i] > sum[i])
            {
                bComplete = false;
                break;
            }
        }
        if (!bComplete)
            return;

        // 음식 최솟값 구하기
        int money = 0;
        for (int i = 1; i <= N; i++)
        {
            if (v[i])
            {
                money += foods[i][4];
            }
        }

        // 최소비용 갱신
        if (answer > money)
        {
            answer = money;
            // 뽑힌 문자열 갱신
            string newStr = "";
            for (int i = 1; i <= N; i++)
            {
                if (v[i])
                {
                    newStr.append(to_string(i)).append(" ");
                }
            }
            pickStr = newStr;
        }
        return;
    }

    // 음식을 포함하거나 하지 않거나
    // 이때 음식에 포함된 모든 영양분이 이미 기준을 넘어섰다면 굳이 포함시키지 말자

    int foodNum = cnt + 1;
    // 음식을 포함
    bool bAdd = false;
    for (int i = 0; i < 4; i++)
    {
        if (mins[i] > sum[i])
        {
            bAdd = true;
            break;
        }
    }

    if (bAdd)
    {
        v[foodNum] = true;
        for (int i = 0; i < 4; i++)
        {
            sum[i] += foods[foodNum][i];
        }

        Pick(cnt + 1, sum, answer, pickStr);

        for (int i = 0; i < 4; i++)
        {
            sum[i] -= foods[foodNum][i];
        }
        v[foodNum] = false;
    }

    // 음식 포함 x
    Pick(cnt + 1, sum, answer, pickStr);
}

main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N;
    for (int i = 0; i < 4; i++)
    {
        cin >> mins[i];
    }

    for (int i = 1; i <= N; i++)
    {
        v[i] = false;
        for (int j = 0; j < 5; j++)
        {
            cin >> foods[i][j];
        }
    }

    // 재귀함수 구현
    int answer = 1000000;
    vector<int> sum(4);
    string pickStr;
    Pick(0, sum, answer, pickStr);

    if (answer == 1000000)
    {
        cout << "-1" << endl;
    }
    else
    {
        cout << answer << endl;
        cout << pickStr;
    }
}