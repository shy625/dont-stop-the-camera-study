/**
 * BJ 1516 게임 개발
 * @ prob : https://www.acmicpc.net/problem/1516
 * @ level : G3
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Week14_BJ_1516_게임개발 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());    // 건물의 종류 수
        int[] times = new int[N + 1];   // 각 건물을 짓는데 걸리는 시간
        List<Integer>[] requirements = new ArrayList[N + 1];    // 각 건물을 짓기 전에 먼저 지어져야하는 건물 리스트
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            times[i] = Integer.parseInt(st.nextToken());
            requirements[i] = new ArrayList<>();
            while (st.hasMoreTokens()) {
                int next = Integer.parseInt(st.nextToken());
                if (next == -1) {
                    break;
                }
                requirements[i].add(next);
            }
        }

        int[] minBuildTime = new int[N + 1];    // DP 배열 : i번째 건물을 짓는데 걸리는 최소 시간 저장
        Queue<Integer> q = new LinkedList<>();  
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());     // 최대 힙
        // Queue 초기화
        for (int i = 1; i <= N; i++) {
            q.offer(i);
        }
        // DP 배열 완성하기
        while (!q.isEmpty()) {
            int cur = q.poll();     // 이번 차례에 최소 시간을 구할 건물
            boolean isReady = true;
            pq.clear();
            for (int i = 0; i < requirements[cur].size(); i++) {
                int time = minBuildTime[requirements[cur].get(i)];
                if (time == 0) {    // 필요 건물 중 아직 최소 시간이 정해지지 않은 건물이 있는 경우
                    isReady = false;
                    break;
                }
                pq.offer(time);
            }

            if (!isReady) {     // 지금 최소 시간을 결정할 수 없는 경우 다시 Queue에 넣고 나중에 다시 계산
                q.offer(cur);
                continue;
            }

            minBuildTime[cur] = times[cur] + (pq.size() == 0 ? 0 : pq.poll());  // DP 배열 계산
        }

        for (int i = 1; i <= N; i++) {
            sb.append(minBuildTime[i]).append(System.lineSeparator());
        }

        System.out.println(sb.toString());

        br.close();
    }
}

// 풀이 1
// DP 사용
// minBuildTime[i] : i번째 건물을 짓는데 걸리는 최소 시간 저장
// minBuildTime[i] = time[i] + max(i번째 건물을 짓는데 필요한 건물들을 짓는데 걸리는 시간)
// Queue에 각 건물을 순서대로 넣음
// Queue에서 순서대로 건물을 꺼내면서 해당 건물을 짓는데 필요한 건물들의 최소 시간을 Priority Queue에 저장
// 필요한 건물들 중 아직 최소 시간이 정해지지 않은 건물이 있다면 해당 건물은 Queue에 다시 넣기
// 필요한 모든 건물들의 최소 시간이 정해져 있다면 PQ를 이용해서 그 중 최댓값을 구함
// 해당 건물을 짓는데까지 걸리는 최소 시간 = 구한 최댓값 + 해당 건물을 짓는 시간
// Queue에 더이상 남아있는 건물이 없다면 결과 출력

// 다른 풀이 1
// 그래프로 만들어 Topology Sort(위상 정렬) 이용 가능
// 일단 Queue에 들어온 순서대로 다 계산해보는 풀이 1에 비해
// 필요 건물이 완성된 것만 Queue에 넣고 돌기 때문에 더 효율적일 것으로 생각됨
// https://www.acmicpc.net/source/18255345
// 위상 정렬 개념 : https://m.blog.naver.com/ndb796/221236874984