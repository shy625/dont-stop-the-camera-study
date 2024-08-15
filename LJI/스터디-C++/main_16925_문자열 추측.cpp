#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int N;
string S;
struct Info
{
    string Str;
    int Idx;
    bool isPre;
};

bool LenCmp(Info A, Info B)
{
    return A.Str.length() > B.Str.length();
}

bool IdxCmp(Info A, Info B)
{

    return A.Idx < B.Idx;
}

bool CheckStr(string Str, vector<Info> &Origin)
{
    // 중복 문자 방지
    vector<bool> UsePre(N, false);

    for (Info &Now : Origin)
    {
        int Len = Now.Str.length();
        string PreStr = Str.substr(0, Len);
        string PostStr = Str.substr(Str.length() - Len, Len);

        bool NotMatch = true;

        // cout << Now.Str << " PreStr : " << PreStr << " PostStr : " << PostStr << endl;

        if (PreStr == Now.Str && !UsePre[Len])
        {
            UsePre[Len] = true;
            Now.isPre = true;
            NotMatch = false;
        }
        else if (PostStr == Now.Str)
        {
            Now.isPre = false;
            NotMatch = false;
        }

        if (NotMatch)
        {
            return false;
        }
    }

    return true;
}

main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    cin >> N;

    // 문자열 입력
    vector<Info> Origin;
    for (int i = 0; i < (N - 1) * 2; i++)
    {
        string tmp;
        cin >> tmp;
        Origin.push_back(Info({tmp, i, false}));
    }

    // 문자열 길이순으로 정렬
    sort(Origin.begin(), Origin.end(), LenCmp);

    // 0,1번째 문자열을 붙여 온전한 문자열 생성하기
    // 마지막 문자열은 한글자만 있으면 된다.
    for (int i = 0; i <= 1; i++)
    {
        S = Origin[i].Str + Origin[(i + 1) % 2].Str[N - 2];

        // S 문자열이 성립하는지 체크
        if (CheckStr(S, Origin))
            break;
    }

    sort(Origin.begin(), Origin.end(), IdxCmp);

    cout << S << endl;
    for (Info Now : Origin)
    {
        if (Now.isPre)
        {
            cout << 'P';
        }
        else
        {
            cout << 'S';
        }
    }
}