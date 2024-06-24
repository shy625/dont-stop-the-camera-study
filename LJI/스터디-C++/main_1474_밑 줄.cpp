#include <iostream>
#include <vector>
using namespace std;
int N, M;
// N개 사이엔 N-1개 단위의 _가 들어간다
// 남은 개수를 뒷 단어가 소문자면 앞에 먼저 넣고
// 그 뒤 남은 개수 전부 처리
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> M;

    vector<string> words;
    int wordsLen = 0;
    for (int i = 0; i < N; i++)
    {
        string tmp;
        cin >> tmp;
        words.push_back(tmp);
        wordsLen += tmp.length();
    }

    // 남은 단어 개수
    int needWords = M - wordsLen;
    // 기본적으로 들어갈 _개수
    int default_ = needWords / (N - 1);
    // 추가로 더 들어가야할 _개수
    int cnt_ = needWords - (default_ * (N - 1));

    // 기본 맨앞 단어 배치
    string answer = words[0];
    for (int i = 1; i < words.size(); i++)
    {
        // 먼저 default_개 배치
        for (int i = 0; i < default_; i++)
        {
            answer += '_';
        }

        // 더 이상 붙일 게 없으니 바로 단어 붙이기로 패스
        if (cnt_ == 0)
        {
        }
        // 만약 현재 포함 남은 공간이 cnt_와 같다면 무조건 _넣어야함
        else if (words.size() - i == cnt_)
        {
            answer += '_';
            cnt_--;
        }
        // 뒤에 단어가 소문자면 _붙여야 우선순위가 땡겨지므로 붙이기
        else if (islower(words[i][0]))
        {
            answer += '_';
            cnt_--;
        }

        // 뒤에 문자 붙이기
        answer += words[i];
    }

    cout << answer;
}