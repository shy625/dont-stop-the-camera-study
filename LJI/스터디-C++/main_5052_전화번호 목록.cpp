#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int t, n;

// 사전순으로 정렬했을 때 이전 번호가 다음 번호에 포함되면 안된다.
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    cin >> t;
    for (int k = 0; k < t; k++)
    {
        cin >> n;
        vector<string> dict;
        for (int i = 0; i < n; i++)
        {
            string temp;
            cin >> temp;
            dict.push_back(temp);
        }
        // 사전 순 정렬
        sort(dict.begin(), dict.end());

        int size = dict.size();
        if (size == 1)
        {
            cout << "YES" << endl;
            continue;
        }

        bool bConsist = true;
        // 이전 문자열이 현재 문자열에 포함되면 bConsist=false;
        for (int i = 1; i < n; i++)
        {
            int preLen = dict[i - 1].length();

            try
            {
                string sub = dict[i].substr(0, preLen);
                if (dict[i - 1].compare(sub) == 0)
                {
                    bConsist = false;
                }
            }
            catch (const std::exception &e)
            {
            }

            if (!bConsist)
                break;
        }

        if (bConsist)
        {
            cout << "YES" << endl;
        }
        else
        {
            cout << "NO" << endl;
        }
    }
}