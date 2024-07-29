#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
using namespace std;

main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    string Order;

    vector<int> Query;
    vector<pair<int, double>> Chocolate;
    vector<pair<int, double>> Coffee;
    while (true)
    {
        int Time;
        double Amount;

        cin >> Order;
        cin >> Time;
        if (Order == "Query")
        {
            Query.push_back(Time);
        }
        else if (Order == "Chocolate")
        {
            cin >> Amount;
            Chocolate.push_back(make_pair(Time, Amount));
        }
        else if (Order == "Coffee")
        {
            cin >> Amount;
            Coffee.push_back(make_pair(Time, Amount));
        }

        Order = "";

        if (cin.eof() == true)
            break;
    }

    sort(Query.begin(), Query.end());
    sort(Chocolate.begin(), Chocolate.end());
    sort(Coffee.begin(), Coffee.end());

    for (int i = 0; i < Query.size(); i++)
    {
        int Time = Query[i];

        double SafetyDis = 0.0;
        // Chocolate
        for (int j = 0; j < Chocolate.size(); j++)
        {
            int EatTime = Chocolate[j].first;
            double EatAmount = Chocolate[j].second;
            if (EatTime > Time)
            {
                break;
            }

            double NowSafetyDis = 8.0 * EatAmount - (Time - EatTime) / 12.0;

            if (NowSafetyDis < 0.0)
                continue;
            SafetyDis += NowSafetyDis;
        }

        // Coffee;
        for (int j = 0; j < Coffee.size(); j++)
        {
            int EatTime = Coffee[j].first;
            double EatAmount = Coffee[j].second;
            if (EatTime > Time)
            {
                break;
            }

            double NowSafetyDis = 2.0 * EatAmount - (Time - EatTime) * (Time - EatTime) / 79.0;

            if (NowSafetyDis < 0.0)
                continue;
            SafetyDis += NowSafetyDis;
        }

        cout << Time << ' ';
        if (SafetyDis < 1.0)
        {
            cout << "1.0\n";
        }
        else
        {
            cout << fixed;
            cout.precision(1);
            cout << round(SafetyDis * 10) / 10 << '\n';
        }
    }
}