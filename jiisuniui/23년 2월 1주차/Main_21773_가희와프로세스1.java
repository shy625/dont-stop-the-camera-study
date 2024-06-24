package BOJ;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 어짜피 실행된 것 외의 우선순위를 증가시켜야되니까, 실행된 것의 우선순위를 뺀다
public class Main_21773_가희와프로세스1 {

    public static class Process implements Comparable<Process> {
        int id;
        int time;
        int priority;

        Process(int id, int time, int priority) {
            this.id = id;
            this.time = time;
            this.priority = priority;
        }

        @Override
        public int compareTo(Process o) {
            // 우선 순위 값이 제일 큰 프로세스
            // 우선 순위 값이 제일 큰 프로세스가 여러 개라면, id가 가장 작은 프로세스
            // 결과값이 작은 쪽이 우선순위 높게(TreeMap에 넣을거라)
            if (this.priority == o.priority) {
                return (this.id - o.id);
            }
            return -(this.priority - o.priority);
        }
    }

    static int T, n;
    static PriorityQueue<Process> queue = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            int prio = Integer.parseInt(st.nextToken());
            queue.add(new Process(id, time, prio));
        }

        for (int i = 0; i < T; i++) {
            if (queue.isEmpty()) {
                break;
            }
            Process proc = queue.poll();
            sb.append(proc.id).append('\n');

            if (proc.time - 1 != 0) {
                queue.add(new Process(proc.id, proc.time - 1, proc.priority - 1));
            }
        }

        System.out.println(sb.toString());

    }
}
