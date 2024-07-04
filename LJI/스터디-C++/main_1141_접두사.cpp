#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int N;
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N;

    vector<string> Dict;
    for (int i = 0; i < N; i++)
    {
        string tmp;
        cin >> tmp;
        Dict.push_back(tmp);
    }

    sort(Dict.begin(), Dict.end());

    string Keyword = Dict[0];
    int Answer = 1;

    for (int i = 1; i < N; i++)
    {
        // 만약 이전에 나온 문자열이 접두어가 되면 정답 카운트 X 하고 키워드만 현재로 갱신
        // 이는 최대한 많은 단어 포함하게 하기 위함
        // 이전에 나오지 않았다면 현재 단어를 새로운 키워드로 설정하고 정답개수 ++
        if (Dict[i].find(Keyword) == 0)
        {
            Keyword = Dict[i];
        }
        else
        {
            Keyword = Dict[i];
            Answer++;
        }
    }

    cout << Answer;
}