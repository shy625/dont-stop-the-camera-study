#include <iostream>
#include <queue>

using namespace std;
int N, P, Q;
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N;

    // 유저가 어느 컴퓨터를 이용하고 있는지 체크//-1이면 이용하지 않고 있는 경우, 다른 수면 해당 컴퓨터 이용 중
    vector<int> User(N, -1);

    // 컴퓨터마다 이용자 수 기록
    vector<int> CPU;

    // 우선 순위 큐로 오름차순으로 각 시간과 유저 정렬
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> Time;

    int start, end;
    for (int i = 0; i < N; i++)
    {
        cin >> start >> end;
        Time.push(make_pair(start, i));
        Time.push(make_pair(end, i));
    }

    // 빈 컴퓨터들을 낮은 번호순으로 정렬
    priority_queue<int, vector<int>, greater<int>> FreeCPU;

    // 이용 처리 시작
    while (!Time.empty())
    {
        int NowTime = Time.top().first;
        int NowUser = Time.top().second;
        Time.pop();

        // 해당 유저의 시작인지 종료인지 체크

        // 종료시간
        // 해당 컴퓨터를 프리하게 만든다
        if (User[NowUser] != -1)
        {
            FreeCPU.push(User[NowUser]);
        }
        // 시작 시간
        else
        {
            // 먼저 빈 컴퓨터가 있는지 체크
            // 빈 컴퓨터가 없다면새로 컴퓨터 늘리기
            if (FreeCPU.empty())
            {
                // 새로 컴퓨터를 들이고
                CPU.push_back(0);
                // 해당 컴퓨터의 번호를 자유로운 컴퓨터 리스트에 추가
                FreeCPU.push(CPU.size() - 1);
            }

            // 위의 과정으로 자유로운 컴퓨터 생겼으므로 할당
            // 해당 컴퓨터는 사용 횟수 증가
            int CPUNum = FreeCPU.top();
            FreeCPU.pop();
            User[NowUser] = CPUNum;
            CPU[CPUNum]++;
        }
    }

    // 출력
    int size = CPU.size();
    cout << size << '\n';

    for (int cnt : CPU)
    {
        cout << cnt << ' ';
    }
}