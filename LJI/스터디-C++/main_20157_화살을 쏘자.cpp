#include <iostream>
#include <map>
using namespace std;
int N;
// 기울기가 같다면 같은 라인이으로 생각;
// 근데 기울기면 계산 에서 오류가 난다->공약수로 나누기

// 유클리드 호제법
int gcd(int a, int b)
{
    if (b == 0)
        return a;
    return gcd(b, a % b);
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N;
    map<pair<int, int>, int> Map;

    for (int i = 0; i < N; i++)
    {
        int x, y;
        cin >> x >> y;

        int num = gcd(x, y);
        // 공약수 그대로 나누면 4분면이 구분이 안되기에 값을 일정하게 해야함
        num = max(num, -1 * num);
        pair<int, int> key = {x / num, y / num};
        if (Map.find(key) != Map.end())
        {
            Map[key]++;
        }
        else
        {
            Map.insert({key, 1});
        }
    }

    // 순회하면서 최대점수 찾기
    int answer = 0;
    for (auto i = Map.begin(); i != Map.end(); i++)
    {
        answer = max(answer, i->second);
    }
    cout << answer;
}