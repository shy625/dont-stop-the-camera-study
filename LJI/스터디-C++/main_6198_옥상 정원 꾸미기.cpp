#include <iostream>
#include <stack>
using namespace std;
int N;
// 스택에 내림차순이 되게 넣음으로써 i번째 건물을 볼 수 있는 건물들의 개수를 구하기

main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N;

    stack<int> Building;

    long long answer = 0;

    for (int i = 0; i < N; i++)
    {
        // 현재 빌딩의 높이 입력 받기
        int h;
        cin >> h;

        // stack에서 현재 높이보다 낮은 높이들 Pop
        while (!Building.empty() && Building.top() <= h)
        {
            Building.pop();
        }

        // 현재 빌딩을 볼 수 있는 개수 정답에 더해주기
        answer += Building.size();

        // 현재 빌딩 스택에 추가
        Building.push(h);
    }

    cout << answer;
}