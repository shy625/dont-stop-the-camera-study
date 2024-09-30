#include <iostream>
#include <vector>
#include <set>
#include <queue>
using namespace std;
int T;
int N, A, B;

// 에라스토테네스 체
void CheckPrime(vector<bool> &Prime, int Size)
{
    for (int i = 2; i <= Size; i++)
    {
        // 현재가 소수면 현재의 배수는 모두 false
        if (Prime[i])
        {
            int tmp = i + i;
            while (tmp <= Size)
            {
                Prime[tmp] = false;
                tmp += i;
            }
        }
    }
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    // 먼저 100000까지의 소수 여부를 기록
    vector<bool> Prime(100001, true);
    CheckPrime(Prime, 100000);

    // 테케 진행
    cin >> T;

    for (int t = 0; t < T; t++)
    {
        cin >> N >> A >> B;

        // 먼저 A,B사이에 소수 존재하는지 체크
        bool isPrime = false;
        for (int i = A; i <= B; i++)
        {
            if (Prime[i])
            {
                isPrime = true;
                break;
            }
        }

        if (!isPrime)
        {
            cout << "-1\n";
            continue;
        }

        // N부터 진행
        queue<int> Que;
        Que.push(N);
        set<int> Visited;
        Visited.insert(N);

        int Answer = 0;
        bool isFindPrime = false;
        while (!Que.empty())
        {
            int Size = Que.size();
            for (int i = 0; i < Size; i++)
            {
                int Cur = Que.front();
                Que.pop();

                if (Cur >= A && Cur <= B && Prime[Cur])
                {
                    isFindPrime = true;
                    cout << Answer << '\n';
                    break;
                }

                // 4가지 동작
                for (int i = 0; i < 4; i++)
                {
                    int Next = 0;
                    if (i == 0)
                    {
                        Next = Cur / 2;
                    }
                    else if (i == 1)
                    {
                        Next = Cur / 3;
                    }
                    else if (i == 2)
                    {
                        Next = Cur + 1;
                    }
                    else if (i == 3)
                    {
                        Next = Cur - 1;
                        if (Next < 0)
                        {
                            Next = 0;
                        }
                    }

                    if (Visited.find(Next) == Visited.end())
                    {
                        Visited.insert(Next);
                        Que.push(Next);
                    }
                }
            }

            if (isFindPrime)
                break;
            Answer++;
        }
    }
}