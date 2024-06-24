#include <iostream>
#include <map>
#include <vector>
using namespace std;
int N, K;
struct Node
{
    char c;
    vector<Node> *next;
};
// K를 기준으로 앞은 정방향 트라이,뒤는 역방향 트라이로 구성하면 될 것 같다.

Node *MakeNode(char c, Node *ptr, int &cnt)
{
    // 현재 포인터에서 연결된 Node의 char 중 있는지 체크
    // 없다면 새 노드 만들고 연결
    bool bFind = false;

    for (int j = 0; j < ptr->next->size(); j++)
    {
        if (ptr->next->at(j).c == c)
        {

            ptr = &ptr->next->at(j);
            bFind = true;
            break;
        }
    }

    if (!bFind)
    {
        Node *newNode = new Node;
        newNode->c = c;
        newNode->next = new vector<Node>;
        // cout << frontPtr->c << " " << newNode.c << endl;
        ptr->next->push_back(*newNode);
        ptr = newNode;
        cnt++; // 새 노드가 만들어졌기에 +1
    }

    return ptr;
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    cin >> N >> K;

    vector<string> words;

    for (int i = 0; i < N; i++)
    {
        string word;
        cin >> word;
        words.push_back(word);
    }

    Node *front = new Node;
    Node *back = new Node;
    int cnt = 0;
    front->next = new vector<Node>;
    back->next = new vector<Node>;
    Node *frontPtr = front;
    Node *backPtr = back;

    for (int n = 0; n < N; n++)
    {
        frontPtr = front;
        backPtr = back;
        string word = words[n];
        // 절반으로 나눠 앞과 뒤에서 각각의 방향으로 트라이
        for (int i = 0; i < K; i++)
        {
            // 앞 부분
            int idx = i;
            char c = word[idx];
            frontPtr = MakeNode(c, frontPtr, cnt);

            // 뒤 부분
            idx = 2 * K - 1 - i;
            c = word[idx];

            backPtr = MakeNode(c, backPtr, cnt);
        }
    }

    cout << cnt;
}