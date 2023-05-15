package BOJ;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main_17503_맥주축제 {

    static class Beer {
        int v, c;

        public Beer(int v, int c) {
            this.v = v;
            this.c = c;
        }
    }

    public static void main(String[] args) {
        Scanner scann = new Scanner(System.in);
        int N = scann.nextInt();
        int M = scann.nextInt();
        int K = scann.nextInt();

        List<Beer> beers = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            int v = scann.nextInt();
            int c = scann.nextInt();
            beers.add(new Beer(v, c));
        }

        beers.sort(new Comparator<Beer>() {
            @Override
            public int compare(Beer o1, Beer o2) {
                if (o1.c == o2.c) { // 알콜이 같을때는
                    return o2.v - o1.v; // 선호도는 큰것부터 내림차순
                }
                return o1.c - o2.c; // 알콜은 낮은것부터 오름차순
            }
        });

        Queue<Integer> queue = new PriorityQueue<>(); // 오름차순
        int ans = -1;
        int sum = 0;
        for (Beer beer : beers) {
            queue.add(beer.v);
            sum += beer.v;

            if (queue.size() > N) {
                int p = queue.poll(); // 작은값 제거
                sum -= p;
            }

            if (queue.size() == N && sum >= M) {
                ans = beer.c;
                break;
            }
        }
        System.out.println(ans);

        scann.close();
    }
}
