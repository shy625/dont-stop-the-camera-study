#include <iostream>
#include <vector>
#include <map>
using namespace std;

// 극단적으로 얘기했을 때 모든 램프가 켜져있을 때 k=1이라면?답은 0인가?
// k는 m보다 적은 개수까지 2n를 빼야할 것 같은데...?
// 그 뒤 새로운 k보다 작은 0의 개수를 세야한다
int N, M, K, answer;

main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> M;

    vector<string> lamps;
    for (int i = 0; i < N; i++)
    {
        string str;
        cin >> str;
        lamps.push_back(str);
    }
    string str;

    cin >> K;

    // 현재 행과 완전히 같은 행만 같은 모양에 전부 켜진 형태 도달 가능
    // 따라서 같은 행은 맵에 갯수 기록해놓자

    map<string, int> lampsMap;
    for (int i = 0; i < N; i++)
    {
        if (lampsMap.find(lamps[i]) == lampsMap.end())
        {
            lampsMap.insert({lamps[i], 1});
        }
        else
        {
            lampsMap[lamps[i]] += 1;
        }
    }

    // 행이 가능하면 그 값 비교
    answer = 0;

    for (int i = 0; i < N; i++)
    {
        // 0값 세기
        int cnt = 0;
        for (int j = 0; j < M; j++)
        {
            if (lamps[i][j] - '0' == 0)
                cnt++;
        }

        // 0의 개수가 K보다 작고 2로 나눴을 때 같은 나머지여야한다.
        if (cnt <= K && (cnt % 2 == K % 2))
        {
            answer = max(answer, lampsMap[lamps[i]]);
        }
    }

    cout << answer;
}