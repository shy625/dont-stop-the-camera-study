#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

string SCB[3][3] = {{"CIRCLE", "TRIANGLE", "SQUARE"},
                    {"YELLOW", "RED", "BLUE"},
                    {"GRAY", "WHITE", "BLACK"}};
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    // 그림 정보 입력
    vector<vector<int>> Drawing(10, vector<int>(3, 0));
    for (int i = 1; i <= 9; i++)
    {
        // 그림 속성별 입력
        for (int j = 0; j < 3; j++)
        {
            string Type;
            cin >> Type;

            for (int t = 0; t < 3; t++)
            {
                if (Type == SCB[j][t])
                {
                    Drawing[i][j] = t;
                }
            }
        }
    }

    // 해당 그림 번호 3개 조합으로 합 여부 기록
    // 각 요소 별로 위치 카운트를 해서 2개가 겹친 곳이 없다면 합
    vector<vector<vector<bool>>> GH(10, vector<vector<bool>>(10, vector<bool>(10, false)));

    // 합 개수 카운트
    int HCnt = 0;

    for (int i = 1; i <= 9; i++)
    {
        for (int j = i + 1; j <= 9; j++)
        {
            for (int k = j + 1; k <= 9; k++)
            {
                // i,j,k의 합 여부 체크
                vector<vector<int>> TypeCnt(3, vector<int>(3, 0));

                // 속성 기록
                for (int t = 0; t < 3; t++)
                {
                    TypeCnt[t][Drawing[i][t]]++;
                    TypeCnt[t][Drawing[j][t]]++;
                    TypeCnt[t][Drawing[k][t]]++;
                }

                bool isH = true;
                for (int a = 0; a < 3; a++)
                {
                    for (int b = 0; b < 3; b++)
                    {
                        if (TypeCnt[a][b] == 2)
                        {
                            isH = false;
                            break;
                        }
                    }
                }

                if (isH)
                {
                    GH[i][j][k] = true;
                    HCnt++;
                }
            }
        }
    }

    // 플레이어 기록 입력 후 점수 계산
    // 한번 쓴 조합은 GH상에서false로 변경하여 중복 방지
    int n;
    cin >> n;

    int Score = 0;
    bool UseG = false;
    for (int i = 0; i < n; i++)
    {
        char Order;
        cin >> Order;

        // 합 선언
        if (Order == 'H')
        {
            vector<int> Num(3, 0);
            for (int j = 0; j < 3; j++)
            {
                cin >> Num[j];
            }

            sort(Num.begin(), Num.end());

            if (GH[Num[0]][Num[1]][Num[2]])
            {
                GH[Num[0]][Num[1]][Num[2]] = false;
                Score++;
                HCnt--;
            }
            else
            {
                Score--;
            }
        }
        // 결 선언
        else
        {
            if (!UseG && HCnt == 0)
            {
                Score += 3;
                UseG = true;
            }
            else
            {
                Score--;
            }
        }
    }

    // 점수 출력

    cout << Score;
}