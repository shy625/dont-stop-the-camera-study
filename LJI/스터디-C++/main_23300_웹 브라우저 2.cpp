#include <iostream>
#include <deque>
using namespace std;
int N, Q;

// 압축 기능 구현을 위하여 덱으로 구현

void Compress(deque<int> &Deq)
{
    int Prev = -1;

    // 덱 끝까지 순회하면서 Prev와 현재 값이 같으면 현재요소 삭제
    // for (deque<int>::iterator it = Deq.begin(); it != Deq.end(); ++it)
    // {
    //     if (Prev == *it)
    //     {
    //         // 제거한 위치에 iter 반환
    //         it = Deq.erase(it);
    //         if (it == Deq.end())
    //             break;
    //     }
    //     else
    //     {
    //         Prev = *it;
    //     }
    // }

    deque<int>::iterator it = Deq.begin();
    while (it != Deq.end())
    {
        if (Prev == *it)
        {
            // 제거한 위치에 iter 반환
            it = Deq.erase(it);
            if (it == Deq.end())
                break;
        }
        else
        {
            Prev = *it;
            it++;
        }
    }
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> Q;

    deque<int> BackDeq;
    deque<int> FrontDeq;
    int NowPage = -1;
    for (int q = 0; q < Q; q++)
    {
        char Order;
        cin >> Order;

        if (Order == 'B')
        {
            // 1
            if (!BackDeq.empty())
            {
                // 2
                FrontDeq.push_front(NowPage);
                // 3
                NowPage = BackDeq.back();
                BackDeq.pop_back();
            }
        }
        else if (Order == 'F')
        {
            // 1
            if (!FrontDeq.empty())
            {
                // 2
                BackDeq.push_back(NowPage);
                // 3
                NowPage = FrontDeq.front();
                FrontDeq.pop_front();
            }
        }
        else if (Order == 'A')
        {
            int NewPage = 0;
            cin >> NewPage;
            if (NewPage > N)
                continue;
            if (NowPage == -1)
            {
                NowPage = NewPage;
            }
            else
            {
                FrontDeq.clear();
                BackDeq.push_back(NowPage);
                NowPage = NewPage;
            }
        }
        else if (Order == 'C')
        {
            Compress(BackDeq);
        }
    }

    cout << NowPage << endl;
    if (BackDeq.empty())
    {
        cout << "-1\n";
    }
    else
    {
        while (!BackDeq.empty())
        {
            cout << BackDeq.back() << ' ';
            BackDeq.pop_back();
        }
        cout << '\n';
    }

    if (FrontDeq.empty())
    {
        cout << "-1\n";
    }
    else
    {
        while (!FrontDeq.empty())
        {
            cout << FrontDeq.front() << ' ';
            FrontDeq.pop_front();
        }
        cout << '\n';
    }
}