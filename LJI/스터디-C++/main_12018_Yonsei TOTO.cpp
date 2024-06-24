#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
// 최대 효율로 신청하려면 마지막 자리에 최대한 많이 들어가면 된다.
// 만약 최대 효율로 나눴는데 남은 과목이 있다면?의 경우도 처리
int n, m;
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    cin >> n >> m;

    // 과목별 최소 요구 마일리지 구해서 vector에 삽입
    vector<int> subVec;
    for (int i = 0; i < n; i++)
    {
        int P, L;           // 신청인원,수강인원
        vector<int> tmpVec; // 신청 인원들의 마일리지
        cin >> P >> L;
        for (int j = 0; j < P; j++)
        {
            int tmp;
            cin >> tmp;
            tmpVec.push_back(tmp);
        }

        // 만약 수강인원의 여유가 있다면 마일리지 1로 통과 가능
        if (L > P)
        {
            subVec.push_back(1);
            continue;
        }

        // 수강 제한 인원의 마지막 자리로 들어가야함->즉 마지막 인원과 같은 마일리지를 넣으면 된다
        sort(tmpVec.begin(), tmpVec.end());
        subVec.push_back(tmpVec[P - L]);
    }

    // subVec을 정렬하여 최소값부터 가진 마일리지 빼면서 과목 수 추가
    int answer = 0;
    sort(subVec.begin(), subVec.end());

    for (int i = 0; i < n; i++)
    {

        if (m - subVec[i] >= 0)
        {
            m -= subVec[i];
            answer++;
        }
    }

    cout << answer;
}