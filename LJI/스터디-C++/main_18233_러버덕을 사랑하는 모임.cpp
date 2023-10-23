#include <iostream>
#include <vector>
using namespace std;
int N, P, E;
// P명의 사람을 골랐을 때 최소범위값의 합과 최대범위합 사이에 E값이 있다면 이는 받을 수 있는 조합
// 사람이 다 골라졌다면 최소값으로 일단 나눠주고 모자란 만큼 최대값으로 올리면서 채우기

vector<pair<int, int>> Members;
vector<bool> Visit;

bool PickMember(int Cnt, int Idx, int MinSum, int MaxSum)
{
    if (Cnt == P)
    {
        if (E >= MinSum && E <= MaxSum)
        {
            vector<int> answers(N);
            // 남은 값을 뽑힌 리스트들로부터 더해주기
            int least = E - MinSum;
            for (int i = 0; i < N; i++)
            {
                // 뽑힌 값은 minVal로 초기화
                if (Visit[i])
                {
                    answers[i] = Members[i].first;

                    if (least != 0)
                    {
                        // 추가로 가능한 인형의 개수와 남은 개수 중  더 작은값으로 갱신
                        int addDoll = min(Members[i].second - Members[i].first, least);
                        least -= addDoll;
                        answers[i] += addDoll;
                    }
                }
                else
                    answers[i] = 0;
            }

            // 출력
            for (int i = 0; i < N; i++)
            {
                cout << answers[i] << ' ';
            }

            return true;
        }
        return false;
    }

    for (int i = Idx; i < N; i++)
    {
        // 현재 멤버 포함
        if (MinSum + Members[i].first <= E)
        {
            Visit[i] = true;
            if (PickMember(Cnt + 1, i + 1, MinSum + Members[i].first, MaxSum + Members[i].second))
                return true;
            Visit[i] = false;
        }
    }
    return false;
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    cin >> N >> P >> E;

    for (int i = 0; i < N; i++)
    {
        int a, b;
        cin >> a >> b;
        Members.push_back(make_pair(a, b));
        Visit.push_back(false);
    }

    // 조합해서 P 명 뽑고 최소값합 최대값합 구하기
    // 그러던 중에 최소값합이 E를 넘었다면 할 필요 없을 것
    if (!PickMember(0, 0, 0, 0))
        cout << "-1" << endl;
}