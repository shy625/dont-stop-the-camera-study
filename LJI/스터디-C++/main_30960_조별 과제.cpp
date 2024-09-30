#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int N;

// 학생을 학번 순으로 정렬하고 앞에부터 두개씩 묶은 어색함의 누적합 뒤에 두개씩 묶은 누적합을 각각 기록
// 그 뒤 누적합의 i번째 인덱스를 기준으로 앞과 뒤에 합을 계산해서 그 중 최솟값 기록

// ex) 1 3 4 5 9
// front :  2 3
// back :  1 5
// 이 때 front[i]+ (back[last]-front[i-1])을 하면
// 특정 인덱스 기준으로 3인조를 만들고 기준으로 나눈 나머지의 어색함의 합을 구할 수 있다.
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N;

    vector<long long> Stu(N);
    for (int i = 0; i < N; i++)
    {
        cin >> Stu[i];
    }

    sort(Stu.begin(), Stu.end());

    // 앞에 순으로 두개씩 묶은 어색함의 누적합
    vector<long long> Front;
    // 뒤에 두개씩 묶은 어색함의 누적합
    vector<long long> Back;

    for (int i = 1; i < N; i++)
    {
        if (i % 2 == 1)
        { // front
            if (Front.empty())
            {
                Front.push_back(Stu[i] - Stu[i - 1]);
            }
            else
            {
                Front.push_back(Front.back() + Stu[i] - Stu[i - 1]);
            }
        }
        else
        { // back
            if (Back.empty())
            {
                Back.push_back(Stu[i] - Stu[i - 1]);
            }
            else
            {
                Back.push_back(Back.back() + Stu[i] - Stu[i - 1]);
            }
        }
    }

    // 시작값 초기화
    int Last = Front.size() - 1;
    long long Answer = Front[0] + Back[Last];

    //
    for (int i = 1; i <= Last; i++)
    {
        Answer = min(Answer, Front[i] + (Back[Last] - Back[i - 1]));
    }

    cout << Answer;
}