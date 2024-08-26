#include <iostream>
#include <cmath>
#include <queue>
#include <vector>
#include <algorithm>
using namespace std;

// 1의 자리부터 소수인 수를 하나씩 만들어가보면?

int N;

// 소수인지 검증하는 함수

// A ≥ B 일 때
// A×A ≥ A×B
// ⇒ A×A ≥ n
// ⇒ A ≥ sqrt(n) ≥ B
bool isPrime(int n)
{
    if (n <= 1)
        return false;
    if (n <= 3)
        return true;

    if (n % 2 == 0)
        return false;

    for (int i = 2; i <= sqrt(n); i++)
    {
        if (n % i == 0)
            return false;
    }

    return true;
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N;

    queue<pair<int, int>> Que;

    Que.push(make_pair(0, 0));
    vector<int> Answer;

    while (!Que.empty())
    {
        int Num = Que.front().first;
        int Cnt = Que.front().second;
        Que.pop();
        if (Cnt == N)
        {
            Answer.push_back(Num);
            continue;
        }

        for (int i = 1; i <= 9; i++)
        {
            int New = Num * 10 + i;

            if (isPrime(New))
            {
                Que.push(make_pair(New, Cnt + 1));
            }
        }
    }

    sort(Answer.begin(), Answer.end());

    for (int now : Answer)
    {
        cout << now << '\n';
    }
}