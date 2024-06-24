#include <iostream>
#include <map>
#include <queue>
using namespace std;

int N, K, Half;

// idx부터 idx+k까지 자리 스왑
string swapStr(string origin, int idx)
{

    for (int i = 0; i < K; i++)
    {
        if (K - 1 - i <= i)
            break;

        swap(origin[idx + i], origin[idx + K - 1 - i]);
    }

    return origin;
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> K;
    // 정답이 될 순열 만들기
    string answerStr = "";
    for (int i = 1; i <= N; i++)
    {
        answerStr += to_string(i);
    }

    string start = "";
    for (int i = 0; i < N; i++)
    {
        int n;
        cin >> n;
        start += to_string(n);
    }

    map<string, bool> visit;
    queue<pair<string, int>> que;
    que.push(make_pair(start, 0));
    visit.insert({start, true});
    int answer = -1;
    while (!que.empty())
    {
        string curStr = que.front().first;
        int turn = que.front().second;
        que.pop();

        if (curStr == answerStr)
        {
            answer = turn;
            break;
        }

        for (int i = 0; i <= N - K; i++)
        {
            string newStr = swapStr(curStr, i);

            // 만약 바뀐 문자열이 이미 나왔으면 패스
            if (visit.find(newStr) != visit.end())
                continue;

            visit.insert({newStr, true});
            que.push(make_pair(newStr, turn + 1));
        }
    }

    cout << answer;
}