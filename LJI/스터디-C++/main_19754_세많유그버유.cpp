#include <iostream>
#include <map>
#include <vector>
#include <algorithm>
using namespace std;

// 같은 날 방송 두번하는지 체크할 필요가 없다
struct VTuber
{
    vector<int> *Days;
    vector<int> *Time;
};

int N, M;

main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> M;

    int TotalWeek = M / 7;
    map<string, VTuber *> VTuberMap;

    for (int i = 0; i < N; i++)
    {
        string Name;
        int Day;
        int Week;
        string StartTimeStr, EndTimeStr;

        cin >> Name >> Day >> StartTimeStr >> EndTimeStr;

        Week = (Day - 1) / 7;

        if (VTuberMap.find(Name) == VTuberMap.end())
        {
            VTuberMap[Name] = new VTuber{new vector<int>(TotalWeek, 0), new vector<int>(TotalWeek, 0.0f)};
        }

        (*VTuberMap[Name]->Days)[Week]++;

        int StartTime, EndTime;

        StartTime = stoi(StartTimeStr.substr(0, 2)) * 60 + (stoi(StartTimeStr.substr(3)));
        EndTime = stoi(EndTimeStr.substr(0, 2)) * 60 + (stoi(EndTimeStr.substr(3)));

        (*VTuberMap[Name]->Time)[Week] += (EndTime - StartTime);
    }

    vector<string> RealVTuber;
    for (auto VTuber : VTuberMap)
    {

        bool bVTuber = true;
        for (int i = 0; i < TotalWeek; i++)
        {
            if (((*VTuber.second->Days)[i]) < 5 || ((*VTuber.second->Time)[i]) < 3600)
            {
                bVTuber = false;
                break;
            }
        }

        if (bVTuber)
        {
            RealVTuber.push_back(VTuber.first);
        }
    }

    if (RealVTuber.empty())
    {
        cout << "-1\n";
    }
    else
    {
        sort(RealVTuber.begin(), RealVTuber.end());

        for (auto Name : RealVTuber)
        {
            cout << Name << '\n';
        }
    }
}