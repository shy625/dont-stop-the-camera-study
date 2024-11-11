#include <iostream>
#include <map>
#include <vector>
#include <sstream>
#include <string>
using namespace std;

// 맨앞과 맨뒤가 같은 글자일 때 사이에 있는 글자 수가 같다면?
// 섞여서 구현할 수 있는 조합일 것

string MakeAlignString(string Str)
{
    vector<int> Alpha(52, 0);

    for (int i = 0; i < Str.length(); i++)
    {
        if (isupper(Str[i]))
        {
            Alpha[26 + Str[i] - 'A']++;
        }
        else
        {
            Alpha[Str[i] - 'a']++;
        }
    }

    string AlignStr = "";

    for (int alpha = 0; alpha < 52; alpha++)
    {
        for (int i = 0; i < Alpha[alpha]; i++)
        {
            if (alpha < 26)
            {
                AlignStr += 'a' + alpha;
            }
            else
            {
                AlignStr += 'A' + alpha - 26;
            }
        }
    }
    return AlignStr;
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    // key의 앞 pair의 문자는 시작과 끝 문자, string은 a부터 정렬된 문자 , value는 같은 단어의 개수
    map<pair<pair<char, char>, string>, int> Map;

    int N;
    cin >> N;

    for (int i = 0; i < N; i++)
    {
        string Word;
        cin >> Word;

        pair<char, char> Key1(Word[0], Word[Word.length() - 1]);
        string Key2 = MakeAlignString(Word);
        Map[make_pair(Key1, Key2)]++;
    }

    int M;
    cin >> M;
    string sentence;
    getline(cin, sentence);
    for (int i = 0; i < M; i++)
    {

        // 개행문자 제거
        getline(cin, sentence);
        int answer = 1;
        bool isFound = false;
        istringstream iss(sentence);
        string word;

        // 공백을 기준으로 단어 나누기
        while (iss >> word)
        {
            string AlignStr = MakeAlignString(word);
            if (Map.find(make_pair(make_pair(word[0], word[word.length() - 1]), AlignStr)) != Map.end())
            {
                isFound = true;
                answer *= Map[make_pair(make_pair(word[0], word[word.length() - 1]), AlignStr)];
            }
        }
        // 하나도 못찾은 경우
        if (!isFound)
        {
            cout << "0\n";
        }
        else
        {
            cout << answer << '\n';
        }
    }
}