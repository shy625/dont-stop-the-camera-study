#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int N;
// a,b,k..... y를 idx로 기록
// ng->c로 변경

char MinSik[] = {'a', 'b', 'k', 'd', 'e',
                 'g', 'h', 'i', 'l', 'm',
                 'n', 'c', 'o', 'p', 'r',
                 's', 't', 'u', 'w', 'y'};
vector<int> ChangeString(string str)
{
    vector<int> vec;
    int len = str.length();

    for (int i = 0; i < len; i++)
    {
        char c;
        c = str[i];

        if (c == 'n' && i + 1 < len && str[i + 1] == 'g')
        {
            i++;
            c = 'c';
        }

        for (int j = 0; j < 20; j++)
        {
            if (MinSik[j] == c)
            {
                vec.push_back(j);
                break;
            }
        }
    }

    return vec;
}

bool CmpVec(vector<int> &A, vector<int> &B, int idx)
{
    // 만약 현재가 같다면?
    // 다음 것을 통해 비교
    if (A[idx] == B[idx])
    {
        // A가 더 이상 없다면 A가 먼저 오게된다
        if (idx + 1 == A.size())
        {
            return true;
        }
        else if (idx + 1 == B.size())
        {
            return false;
        }

        return CmpVec(A, B, idx + 1);
    }

    // 기본적으로는 오름차순 정렬
    return A[idx] < B[idx];
}

bool CmpString(string A, string B)
{
    vector<int> VecA = ChangeString(A);
    vector<int> VecB = ChangeString(B);

    return CmpVec(VecA, VecB, 0);
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N;
    vector<string> Dict;

    for (int i = 0; i < N; i++)
    {
        string str;
        cin >> str;
        Dict.push_back(str);
    }

    sort(Dict.begin(), Dict.end(), CmpString);

    for (string str : Dict)
    {
        cout << str << '\n';
    }
}