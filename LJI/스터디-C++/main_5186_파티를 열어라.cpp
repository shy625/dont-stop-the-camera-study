#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int T, n, c, l;
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> T;

    for (int t = 1; t <= T; t++)
    {
        cin >> n >> c >> l;

        int Answer = n;

        // 안취한 사람 :0 , 취한 사람 :1
        vector<vector<int>> Friends(l + 1, vector<int>(2, 0));

        for (int i = 0; i < n; i++)
        {
            int num;
            char ch;
            cin >> num >> ch;

            if (ch == 'I')
            {
                Friends[num][1]++;
            }
            else
            {
                Friends[num][0]++;
            }
        }

        // 최대한 탑승 인원 많은 순서로 정렬
        vector<vector<int>> Cars(l + 1, vector<int>());
        for (int i = 0; i < c; i++)
        {
            int local, seat;
            cin >> local >> seat;

            Cars[local].push_back(seat);
        }

        for (int i = 1; i <= l; i++)
        {
            // 차 정렬하고 시작
            sort(Cars[i].begin(), Cars[i].end(), greater<int>());

            for (int j = 0; j < Cars[i].size(); j++)
            {
                // 남은 좌석 수
                int seat = Cars[i][j];
                // 안취한 사람이 없어서 운행 불가
                if (Friends[i][0] == 0)
                    break;

                // 먼저 운행 사람 한명 빼기
                seat--;
                Friends[i][0]--;
                Answer--;
                // 술 취한 사람들 중 사람 추가
                int drunk = min(seat, Friends[i][1]);
                seat -= drunk;
                Friends[i][1] -= drunk;
                Answer -= drunk;

                // 술 안취한 사람들 중 남은 사람 추가
                int other = min(seat, Friends[i][0]);
                seat -= other;
                Friends[i][0] -= other;
                Answer -= other;
            }
        }

        cout << "Data Set " << t << ":\n"
             << Answer << '\n';
    }
}