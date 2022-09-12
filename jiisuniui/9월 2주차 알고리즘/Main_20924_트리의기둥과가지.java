import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Main_20924_트리의기둥과가지 {

    // public class Node {
    // int element;
    // Node parent;
    // int value; // 간선길이
    // ArrayList<Node> childArray = new ArrayList<>(); // 다중자식노드

    // Node(int element) {
    // this.element = element;
    // this.parent = null;
    // }

    // Node(int element, int value) {
    // this.element = element;
    // this.parent = null;
    // this.value = value;
    // }

    // public void setParentNode(Node parent) {
    // this.parent = parent;
    // }

    // public Node addChildNode(int element) {
    // Node child = new Node(element);
    // child.setParentNode(this);
    // childArray.add(child);

    // return child;
    // }
    // }

    static int N, R;// 노드 개수, 루트노드 번호
    static int G = 0; // 기가노드 번호
    static boolean[] visited;

    static class Node {
        int element;
        ArrayList<Integer> linkNode = new ArrayList<Integer>();
        ArrayList<Integer> linkValue = new ArrayList<Integer>();
        int linkSize; // linknode size
        int sumValue; // 기가노드부터 sum한 value;

        public Node(int element) {
            this.element = element;
            this.linkSize = 0;
            this.sumValue = 0;
        }

        public void setLinkNode(int node, int value) {
            this.linkNode.add(node);
            this.linkValue.add(value);
            this.linkSize++;
        }

        public void setSumValue(int plusValue) {
            this.sumValue += plusValue;
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner scann = new Scanner(System.in);
        N = scann.nextInt();
        R = scann.nextInt();
        visited = new boolean[N + 1];

        Node[] NodeList = new Node[N + 1];
        for (int i = 1; i <= N; i++) {
            NodeList[i] = new Node(i);
        }

        // NodeList setting
        for (int i = 0; i < N - 1; i++) {
            int a, b, d;
            a = scann.nextInt(); // node num
            b = scann.nextInt(); // node num
            d = scann.nextInt(); // edge length

            NodeList[a].setLinkNode(b, d);
            NodeList[b].setLinkNode(a, d);
        }

        int pillarLen = 0;
        int branchLen = 0;
        int cn = R; // 현재노드 위치

        // dfs 들어가면서 기가노드(G) 찾기 & 기둥길이 계산
        for (;;) {
            visited[cn] = true;
            if (NodeList[cn].linkSize == 0) {
                break;
            } // 연결된게 없다면 break
            if (cn == R && NodeList[cn].linkSize == 2) {
                G = cn;
                break;
            } // 현재 node가 RootNode 라면 연결된 node가 2개일때, 기가노드
            if (NodeList[cn].linkSize > 2) {
                G = cn;
                break;
            } // 연결된 node가 3개이상이라면, 기가노드

            // 기둥길이 더하고, 다음노드로 이동
            int x = NodeList[cn].linkNode.get(0);
            // 연결된 노드 중 visited하지 않은 노드 찾음
            if (visited[NodeList[cn].linkNode.get(0)] == false) {
                pillarLen += NodeList[cn].linkValue.get(0);
                cn = NodeList[cn].linkNode.get(0);

            }
            // linksize가 1보다 클때, visited하지 않았는지 확인
            else if (NodeList[cn].linkSize > 1 && visited[NodeList[cn].linkNode.get(1)] == false) {
                pillarLen += NodeList[cn].linkValue.get(1);
                cn = NodeList[cn].linkNode.get(1);
            } else {
                break;
            } // 리프노드
        }

        Stack<Integer> stack = new Stack<Integer>();
        visited[G] = false;
        stack.push(G);
        // 기가노드가 0이 아닐 때, 리프노드까지의 최대거리 계산
        if (G != 0) {
            while (!stack.empty()) {
                cn = stack.pop();
                if (visited[cn]) {
                    continue;
                }
                visited[cn] = true;
                if (NodeList[cn].linkSize == 1) {
                    if (branchLen < NodeList[cn].sumValue) {
                        branchLen = NodeList[cn].sumValue;
                    }
                    continue;
                }
                for (int i = 0; i < NodeList[cn].linkSize; i++) {
                    int nextNode = NodeList[cn].linkNode.get(i);
                    int nextValue = NodeList[cn].linkValue.get(i);

                    if (visited[nextNode] == false) {
                        stack.push(nextNode);
                        NodeList[nextNode].setSumValue(NodeList[cn].sumValue); // 현재노드의 sumValue를 전달
                        NodeList[nextNode].setSumValue(nextValue); // linkValue를 더해줌
                    }
                }
            }
        }

        System.out.println(pillarLen + " " + branchLen);
    }
}
