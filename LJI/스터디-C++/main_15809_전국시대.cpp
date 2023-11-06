#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int N, M;

// union-find로 국가 연합을 관리하고
// vector로 병력 수를 관리
vector<int> parent;
vector<int> army;
vector<bool> visit;
int Find(int a)
{
    if (parent[a] == a)
        return a;
    else
        return parent[a] = Find(parent[a]);
}

void Union(int a, int b)
{
    int aP = Find(a);
    int bP = Find(b);

    if (aP < bP)
    {
        parent[aP] = bP;
    }
    else
    {
        parent[bP] = aP;
    }
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> M;

    parent.push_back(0);
    army.push_back(0);
    visit.push_back(false);
    for (int i = 1; i <= N; i++)
    {
        int tmp;
        cin >> tmp;
        parent.push_back(i);
        army.push_back(tmp);
        visit.push_back(false);
    }

    // 동맹 및 전쟁 입력
    int O, P, Q, PP, QP, newArmy = 0; // 뒤에 두개는 P와 Q의 조상
    for (int i = 0; i < M; i++)
    {
        cin >> O >> P >> Q;

        P = Find(P);
        Q = Find(Q);

        // 동맹
        if (O == 1)
        {
            newArmy = army[P] + army[Q];
        }
        else // 전쟁
        {
            newArmy = abs(army[P] - army[Q]);
        }
        Union(P, Q);
        army[Find(P)] = newArmy;
    }

    vector<int> answer;
    int cnt = 0;
    int p;
    for (int i = 1; i <= N; i++)
    {
        p = Find(i);
        if (!visit[p])
        {
            visit[p] = true;
            if (army[p] > 0)
            {
                answer.push_back(army[p]);
                cnt++;
            }
        }
    }

    if (cnt > 0)
    {
        cout << cnt << endl;
        sort(answer.begin(), answer.end());
        for (int n : answer)
        {
            cout << n << ' ';
        }
    }
    else
    {
        cout << '0' << endl;
    }
}