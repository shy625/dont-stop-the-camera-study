#include <iostream>

using namespace std;
int N;
double J, C;

// 기댓값은 결국 총 횟수가 원금 기준으로 세팅 된다.
// J금액의 당첨될 확률 -> 강호 원금/총 원금
// 이게 C주간 반복되는 값이 기대값
// 따라서: 강호 원금+(J*(강호 원금/총 원금))*C가 기댓값
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N;

    double GangHo;
    cin >> GangHo;

    double Sum = GangHo;

    for (int i = 1; i < N; i++)
    {
        double tmp;
        cin >> tmp;
        Sum += tmp;
    }
    cin >> J >> C;
    double Answer = GangHo + (J * (GangHo / Sum)) * C;

    // 소수점 밑 14 제한
    cout.precision(14);

    cout << GangHo + (J * (GangHo / Sum)) * C;
}