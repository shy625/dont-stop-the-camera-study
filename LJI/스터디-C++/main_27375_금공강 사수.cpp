#include <iostream>
#include <vector>
using namespace std;
int n, k;
// 모든 가짓수를 찾아야해서 브루트포스

struct Lecture
{
    int Date;
    int Start;
    int End;
};

bool CheckSchedule(Lecture &Lec, vector<vector<bool>> &Schedule)
{
    for (int i = Lec.Start; i <= Lec.End; i++)
    {
        if (Schedule[Lec.Date][i])
        {
            return false;
        }
    }
    return true;
}

void PickLecture(int Idx, int Credit, vector<Lecture> &LecVec, vector<vector<bool>> &Schedule, int &Answer)
{
    // 학점 정확히 k일 때
    if (Credit == k)
    {
        Answer++;
        return;
    }

    // 학점 도달 실패
    if (Idx == n)
    {
        return;
    }

    Lecture Cur = LecVec[Idx];
    // Idx 과목을 고르기
    // Idx 과목을 선택 가능한지
    // 금요일은 제외
    if (Cur.Date != 5 && CheckSchedule(Cur, Schedule))
    {
        // 스케줄 체크
        for (int i = Cur.Start; i <= Cur.End; i++)
        {
            Schedule[Cur.Date][i] = true;
        }
        PickLecture(Idx + 1, Credit + (Cur.End - Cur.Start + 1), LecVec, Schedule, Answer);
        // 선택 안한 상태로 돌리기
        for (int i = Cur.Start; i <= Cur.End; i++)
        {
            Schedule[Cur.Date][i] = false;
        }
    }

    // Idx 과목 선택 안한상태
    PickLecture(Idx + 1, Credit, LecVec, Schedule, Answer);
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> n >> k;

    vector<Lecture> LecVec;
    for (int i = 0; i < n; i++)
    {
        int d, s, e;
        cin >> d >> s >> e;

        LecVec.push_back(Lecture() = {d, s, e});
    }

    vector<vector<bool>> Schedule(6, vector<bool>(11, false));

    int Answer = 0;
    PickLecture(0, 0, LecVec, Schedule, Answer);

    cout << Answer;
}