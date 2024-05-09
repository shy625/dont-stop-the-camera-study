#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int N;

struct Country
{
    string name;
    int Score;
    int LoseScore;
    int Point;
    int OriScore;
    int OriLoseScore;
    int OriPoint;
};

string CountryNameList[] = {"korea", "uruguay", "ghana", "portugal"};

bool cmp(Country A, Country B)
{
    if (A.Point == B.Point)
    {
        // 득실차로 결정
        int ADiff = A.Score - A.LoseScore;
        int BDiff = B.Score - B.LoseScore;

        if (ADiff == BDiff)
        {
            // 득점순으로 결정
            return A.Score > B.Score;
        }
        else
        {
            return ADiff > BDiff;
        }
    }
    else
    {
        // 승점차로 결정
        return A.Point > B.Point;
    }
}

void SumScore(int TeamIdx, int OtherIdx, Country &Team, vector<int> &NowScore)
{
    Team.LoseScore = Team.OriLoseScore + NowScore[OtherIdx];
    Team.Score = Team.OriScore + NowScore[TeamIdx];

    if (NowScore[TeamIdx] == NowScore[OtherIdx])
    {
        Team.Point = Team.OriPoint + 1;
    }
    else if (NowScore[TeamIdx] > NowScore[OtherIdx])
    {
        Team.Point = Team.OriPoint + 3;
    }
    else
    {
        Team.Point = Team.OriPoint;
    }
}

// 현재 결과 가지고 예상해서 cry 나 unhappy 출력
// 16강 진출->cry
// 16강 진출 실패->unhappy
void Prediction(vector<Country> &vec, vector<int> &NowScore)
{
    // NowScore에따라 승패 결과 변경
    for (Country &Cur : vec)
    {
        if (Cur.name == "korea")
        {
            SumScore(0, 3, Cur, NowScore);
        }
        else if (Cur.name == "uruguay")
        {
            SumScore(1, 2, Cur, NowScore);
        }
        else if (Cur.name == "ghana")
        {
            SumScore(2, 1, Cur, NowScore);
        }
        else
        {
            SumScore(3, 0, Cur, NowScore);
        }
    }

    // cmp 기준으로 정렬
    sort(vec.begin(), vec.end(), cmp);

    // 출력
    if (vec[0].name == "korea")
    {
        if (vec[0].LoseScore == vec[2].LoseScore && vec[0].Point == vec[2].Point && vec[0].Score == vec[2].Score)
        {
            cout << "unhappy\n";
        }
        else
        {
            cout << "cry\n";
        }
    }
    else if (vec[1].name == "korea")
    {
        // 재경기시 unhappy
        if (vec[1].LoseScore == vec[2].LoseScore && vec[1].Point == vec[2].Point && vec[1].Score == vec[2].Score)
        {
            cout << "unhappy\n";
        }
        else
        {
            cout << "cry\n";
        }
    }
    else
    {
        cout << "unhappy\n";
    }
}

main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    vector<Country> vec;

    for (int i = 0; i < 4; i++)
    {
        int Score, LoseScore, Point;
        cin >> Score >> LoseScore >> Point;

        vec.push_back(Country() = {CountryNameList[i], Score, LoseScore, Point, Score, LoseScore, Point});
    }

    vector<int> NowScore(4, 0);
    Prediction(vec, NowScore);
    cin >> N;

    for (int i = 0; i < N; i++)
    {
        string Name;
        cin >> Name;

        for (int j = 0; j < 4; j++)
        {
            if (Name == CountryNameList[j])
            {
                ++NowScore[j];
            }
        }

        Prediction(vec, NowScore);
    }
}