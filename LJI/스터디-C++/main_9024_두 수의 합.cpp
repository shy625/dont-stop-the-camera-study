#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int T, n, k;
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    cin >> T;

    for (int t = 0; t < T; t++)
    {
        cin >> n >> k;

        vector<long> vec;
        for (int i = 0; i < n; i++)
        {
            long tmp;
            cin >> tmp;
            vec.push_back(tmp);
        }
        sort(vec.begin(), vec.end());

        int minIdx = 0;
        int maxIdx = n - 1;
        int cnt = 0;
        long near = 200000001;
        while (minIdx < maxIdx)
        {
            long diff = vec[maxIdx] + vec[minIdx];

            if (near > abs(k - diff))
            {
                near = abs(k - diff);
                cnt = 1;
            }
            else if (near == abs(k - diff))
            {
                cnt++;
            }

            if (diff > k)
            {
                maxIdx--;
            }
            else
            {
                minIdx++;
            }
        }
        cout << cnt << endl;
    }
}