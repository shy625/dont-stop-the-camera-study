#include <iostream>

using namespace std;

main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    int N, M;
    // 자료 입력
    cin >> N;

    if (N == 1) // N이 1이면 0만 판다는 뜻이며 이는 만들 수 있는 수는 0밖에 없다
    {
        cout << '0';
        return 0;
    }
    int price[N];
    for (int i = 0; i < N; i++)
    {
        cin >> price[i];
    }

    cin >> M;

    // 단위를 늘리는게 일단 가장 커지는 방법
    // 그럼 가장 작은 금액을 여러개 산다?
    // 그 뒤 남은 돈으로 앞자리부터 금액을 올리면?
    // 문제는 0이 가장 싼 경우라면?

    int minIdx = 0;  // 0포함
    int minIdx2 = 1; // 0 포함 X
    for (int i = 0; i < N; i++)
    {
        if (price[i] < price[minIdx])
        {
            minIdx = i;
        }

        if (i != 0)
        {
            if (price[i] < price[minIdx2])
            {
                minIdx2 = i;
            }
        }
    }

    // 두번째 비용보다 적은 돈이면 리턴
    if (M < price[minIdx2])
    {
        cout << '0';
        return 0;
    }
    // cout << minIdx << endl
    //      << minIdx2 << endl;
    int ans[51] = {
        0,
    }; // 방번호 기록

    // 처음은 0이 나오면 안되기에 0을 제외한 최소값 배치
    int idx = 1; // 최대 번호 길이 기록
    ans[0] = minIdx2;
    M -= price[minIdx2];
    // 그 뒤부터 가능한 최소값 구하기
    // 가능한 개수
    int cnt = M / price[minIdx];
    // 배치시키기
    for (int i = 1; i <= cnt; i++)
    {
        ans[i] = minIdx;
    }
    idx += cnt;
    M -= price[minIdx] * cnt;

    // 남은 돈을 가지고 맨 앞부터 큰 수랑 교환

    for (int i = 0; i <= idx; i++)
    {

        // 일단 현재 위치의 수는 팔면 더 비싼 수를 살 수 있는가를 체크 있다면 몇인지 체크
        int newIdx = -1;
        for (int j = N - 1; j >= 0; j--)
        {
            // 기존 수와 같으면 낮은 숫자들은 더 할 필요 없다
            if (j == ans[i])
                break;

            if (M + price[ans[i]] >= price[j])
            {
                newIdx = j;
                break;
            }
        }

        // newIdx가 -1이 아니면 교체해야함
        if (newIdx != -1)
        {
            M += price[ans[i]] - price[newIdx];
            ans[i] = newIdx;
        }
    }

    // cout << idx << endl;
    for (int i = 0; i < idx; i++)
    {
        cout << ans[i];
    }
}