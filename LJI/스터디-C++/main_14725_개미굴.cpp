#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
// 출력 방식은 dfs
int N;

struct Node
{
    string food;
    vector<Node> *next;
};

bool cmp(Node a, Node b)
{
    return a.food < b.food;
}

void Print(Node now, int level)
{
    if (now.food != "start")
    {
        string str = "";
        for (int i = 1; i < level; i++)
        {
            str.append("--");
        }
        str.append(now.food);
        cout << str << endl;
    }

    sort(now.next->begin(), now.next->end(), cmp);
    for (int i = 0; i < now.next->size(); i++)
    {

        Print(now.next->at(i), level + 1);
    }
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    cin >> N;

    // 초기화
    vector<Node> *nextNode = new vector<Node>;
    Node *start = new Node;
    start->food = "start";
    start->next = nextNode;
    for (int i = 0; i < N; i++)
    {
        int K = 0;
        cin >> K;
        Node *now = start;
        for (int j = 0; j < K; j++)
        {
            string nowFood;
            cin >> nowFood;

            //     now 노드에 연결된 노드들중 nowFood와 같은게 있는지 찾기
            bool bFind = false;
            if (now->next->size() != 0)
            {
                for (int i = 0; i < now->next->size(); i++)
                {
                    // 이미 해당 음식 루트가 존재
                    if (now->next->at(i).food.compare(nowFood) == 0)
                    {
                        //  다음 노드로 now값 변경
                        now = &now->next->at(i);

                        bFind = true;
                        break;
                    }
                }
            }

            //  못찾았을 경우는 노드 추가
            if (!bFind)
            {

                vector<Node> *newVec = new vector<Node>;
                Node *nextN = new Node;
                nextN->food = nowFood;
                nextN->next = newVec;

                now->next->push_back(*nextN);
                now = nextN;
            }
        }
    }

    //   출력하기
    int level = 0;

    Print(*start, 0);
}