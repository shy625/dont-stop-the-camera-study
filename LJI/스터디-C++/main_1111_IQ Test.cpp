#include <iostream>
#include <vector>
using namespace std;
int N;
int a, b;
// a,b가 어떤 수든 절댓값이 100을 넘어가는 순간 그 뒤는 할 필요 없다
// a,b 각각 0부터 -,+ 방향으로 계속 늘려가는 총 4가지 경우를 규칙에 부합할 때 지속
// 최종 N개를 통과했으면 정답 카운트+1

// 정답 카운트가 0 이면 B
// 1이면 그때의 값
// 2이면 A
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    cin >> N;

    vector<int> seq;
    int tmp;
    bool isSeq;
    for (int i = 0; i < N; i++)
    {
        cin >> tmp;
        seq.push_back(tmp);
    }

    // N==1
    // 원하는 아무값 가능
    if (N == 1)
    {
        cout << "A";
    }
    // N==2

    else if (N == 2)
    {
        // 두 수가 같으면 a=1,b=0으로 계속 유지시키는 방법->계속 같은값
        if (seq[0] == seq[1])
        {
            cout << seq[0];
        }
        // 다르면 a,b를 조절하는 무한한 방법 가능
        else
        {
            cout << "A";
        }
    }
    // N>=3
    else
    {
        // 첫번째==두번째
        if (seq[0] == seq[1])
        {
            // 두번째==이후에 모든 값
            isSeq = true;
            int idx = 2;
            while (idx < N)
            {
                if (seq[1] != seq[idx])
                {
                    isSeq = false;
                    break;
                }
                idx++;
            }

            if (isSeq)
            {
                cout << seq[0];
            }
            else
            {
                cout << "B";
            }
        }
        // 첫번째!=두번째
        else
        {
            // 두번째==세번째
            if (seq[1] == seq[2])
            {
                // 이 뒤로 쭉 같다면 a=0,b=0이 아닌 값으로 계속 b값이 되는 경우
                // 만약 다른 값이 있다면 B
                isSeq = true;
                int idx = 3;
                while (idx < N)
                {
                    if (seq[1] != seq[idx])
                    {
                        isSeq = false;
                        break;
                    }
                    idx++;
                }

                if (isSeq)
                {
                    cout << seq[1];
                }
                else
                {
                    cout << "B";
                }
            }
            // 두번째!=세번째
            else
            {
                // 먼저 0과1, 1과2 항을 통해 a,b를 구한다
                // x1a-b=y1
                // x2a-b=y2
                // 의 형태이므로 각 항을 빼주면
                // (x2-x1)a=(y2-y1)
                // a=(y2-y1)/(x2-x1) 이 된다.

                float A = (seq[2] - seq[1]) / (float)(seq[1] - seq[0]);

                // 이때 a,b는 정수기에 A가 정수가 아니면 "B"출력
                if (A - (int)A != 0)
                {
                    cout << "B";
                }
                else
                {
                    a = (int)A;
                    b = seq[0] * a - seq[1];

                    // 이제 이 뒤에 모든 항이 a,b식을 만족하면 마지막 항을 통한 계산 출력
                    // 아니면 "B"출력
                    isSeq = true;
                    int idx = 3;
                    while (idx < N)
                    {
                        if (seq[idx - 1] * a - b != seq[idx])
                        {
                            isSeq = false;
                            break;
                        }
                        idx++;
                    }

                    if (isSeq)
                    {
                        cout << seq[N - 1] * a - b;
                    }
                    else
                    {
                        cout << "B";
                    }
                }
            }
        }
    }
}