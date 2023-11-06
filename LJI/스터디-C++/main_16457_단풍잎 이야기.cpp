#include <iostream>
#include <vector>
using namespace std;
int n, m, k;

vector<vector<int>> quests; // 퀘스트가 각각 필요한 스킬들
vector<int> skills;         // 스킬의 사용 회수

void DoQuest(int idx, int questCnt, int skillCnt, int &answer)
{
    if (idx == m)
    {
        if (questCnt > answer)
        {
            answer = questCnt;
        }
        return ;
    }

    // idx 퀘스트를 실행 시키는 경우
    int newSkillCnt = skillCnt;
    for (int i = 0; i < k; i++)
    {
        // 그전까지 한번도 쓰인적 없는 스킬이었다면 스킬 개수+
        if (skills[quests[idx][i]] == 0)
        {
            newSkillCnt++;
        }
        skills[quests[idx][i]]++;
    }

    // newSkillCnt가 n개 이하일 때만 진행
    if (newSkillCnt <= n)
    {
        DoQuest(idx + 1, questCnt + 1, newSkillCnt, answer);
    }

    // 초기화
    for (int i = 0; i < k; i++)
    {
        skills[quests[idx][i]]--;
    }

    // idx 퀘스트를 실행하지 않는 경우
    DoQuest(idx + 1, questCnt, skillCnt, answer);
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> n >> m >> k;

    // 스킬 초기화
    for (int i = 0; i <= 2 * n; i++)
    {
        skills.push_back(0);
    }

    // 퀘스트 입력
    for (int i = 0; i < m; i++)
    {
        vector<int> quest;
        for (int j = 0; j < k; j++)
        {
            int tmp;
            cin >> tmp;
            quest.push_back(tmp);
        }
        quests.push_back(quest);
    }

    // 퀘스트 실행
    int answer = 0;
    DoQuest(0, 0, 0, answer);
    cout << answer;
}