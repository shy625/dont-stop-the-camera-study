#include <iostream>
#include <algorithm>
using namespace std;

bool isBiggerA(string A, string B)
{
    for (int i = 0; i < A.size(); i++)
    {
        int a = A[i] - '0';
        int b = B[i] - '0';

        if (a > b)
        {
            return true;
        }
        else if (a < b)
        {
            return false;
        }
    }

    return false;
}

void Increase(string &A, int idx, bool &isChange)
{
    int now = A[idx] - '0';

    now++;

    if (now == 10)
    {
        A[idx] = '0';

        if (idx == 0)
        {
            isChange = true;
            A.insert(0, "1");
        }
        else
        {
            Increase(A, idx - 1, isChange);
        }
    }
    else
    {
        A[idx] = now + '0';
    }
}

main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    string N;
    cin >> N;

    // 한자리 수
    if (N.size() == 1)
    {
        int IntN = N[0] - '0';
        IntN++;
        if (IntN == 10)
        {
            cout << "11";
        }
        else
        {
            cout << IntN;
        }

        return 0;
    }

    int Half = -1;
    int Size = N.size();
    int BackStart = N.size() / 2;
    // 가운데 수 존재 시 갱신
    if (Size % 2 == 1)
    {
        Half = N[Size / 2] - '0';
        // 뒷 숫자 시작 인덱스는 한칸 밀린다
        BackStart++;
    }

    string Front = N.substr(0, Size / 2);
    string FrontRev = Front;
    reverse(FrontRev.begin(), FrontRev.end());
    string Back = N.substr(BackStart, Size / 2);

    // 백넘버를 프론트 넘버로 교체만 해주면 된다
    if (isBiggerA(FrontRev, Back))
    {

        cout << Front;

        if (Half != -1)
        {
            cout << Half;
        }

        reverse(Front.begin(), Front.end());
        cout << Front;
    }
    // Half가 존재하면 Half를 올려 출력
    // 없으면 FrontNum올려 출력
    else
    {
        bool isChange = false;
        if (Half != -1)
        {
            Half++;
            if (Half == 10)
            {
                Increase(Front, Front.size() - 1, isChange);
                Half = 0;
            }
        }
        else
        {
            Increase(Front, Front.size() - 1, isChange);
        }

        // 9999가 100001로 출력되지 않고 10001로 출력되게끔 함
        if (isChange)
        {
            cout << Front.substr(0, Front.size() - 1);
        }
        else
        {
            cout << Front;
        }

        if (Half != -1)
        {
            cout << Half;
        }

        reverse(Front.begin(), Front.end());
        cout << Front;
    }
}