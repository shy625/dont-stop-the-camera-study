/**
 * BJ 9019 DSLR
 * @ prob : https://www.acmicpc.net/problem/9019
 * @ level : G5
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Week13_BJ_9019_DSLR {
    
    static class Node {
        int n;
        String cmdSeq;

        Node(int n, String cmdSeq) {
            this.n = n;
            this.cmdSeq = cmdSeq;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(bfs(a, b)).append(System.lineSeparator());
        }
        System.out.println(sb.toString());

        br.close();
    }

    private static int cmdD(int n) {
        return 2 * n % 10000;
    }

    private static int cmdS(int n) {
        return n == 0 ? 9999 : n - 1;
    }

    private static int cmdL(int n) {
        char[] base = {'0', '0', '0', '0'};
        for(int i = 3; i >= 0; i--) {
            int idx = i == 0 ? 3 : i - 1;
            base[idx] = (char)(n % 10 + '0');
            n /= 10;
        }
        return Integer.parseInt(String.valueOf(base));
    }

    private static int cmdR(int n) {
        char[] base = {'0', '0', '0', '0'};
        for(int i = 3; i >= 0; i--) {
            int idx = i == 3 ? 0 : i + 1;
            base[idx] = (char)(n % 10 + '0');
            n /= 10;
        }
        return Integer.parseInt(String.valueOf(base));
    }

    private static String bfs(int a, int b) {
        String result = "";
        Queue<Node> q = new LinkedList<>();
        boolean[] isVisited = new boolean[10000];

        q.offer(new Node(a, ""));
        isVisited[a] = true;
        while(!q.isEmpty()) {
            Node cur = q.poll();
            if(cur.n == b) {
                result = cur.cmdSeq;
                break;
            }
            for(int i = 0; i < 4; i++) {
                int nextN = 0;
                StringBuilder nextCmdSeq = new StringBuilder(cur.cmdSeq);
                if(i == 0) {
                    nextN = cmdD(cur.n);
                    nextCmdSeq.append("D");
                } else if(i == 1) {
                    nextN = cmdS(cur.n);
                    nextCmdSeq.append("S");
                } else if(i == 2) {
                    nextN = cmdL(cur.n);
                    nextCmdSeq.append("L");
                } else if(i == 3) {
                    nextN = cmdR(cur.n);
                    nextCmdSeq.append("R");
                }
                if(!isVisited[nextN]) {
                    isVisited[nextN] = true;
                    q.offer(new Node(nextN, nextCmdSeq.toString()));
                }
            }
        }
        return result;
    }
}

// 풀이 1
// 주어진 숫자에 대해 D, S, L, R을 수행하며 BFS로 이동
// 목표 숫자가 되면 BFS 종료
// 메모리, 시간 개선 필요 - 308156kb / 10096ms