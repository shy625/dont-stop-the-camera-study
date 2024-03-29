import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_G4_13975_파일합치기3 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			int K = Integer.parseInt(br.readLine());
			// 1000000*10000까지 올 수 있으므로 범위는 Long이 되어야 한다.
			PriorityQueue<Long> pq = new PriorityQueue<Long>();

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < K; i++) {
				pq.add(Long.parseLong(st.nextToken()));
			}

			long ans = 0;

			// 최종 파일 1개가 남을 때 까지 시행한다.
			while (pq.size() > 1) {
				// 제일 크기가 작은 파일 2개를 꺼낸다.
				long n1 = pq.poll();
				long n2 = pq.poll();

				// 그 둘을 합치고, ans에 값을 더해준다.
				long sum = n1 + n2;
				ans += sum;
				// 합친 파일을 pq에 다시 넣어준다.
				pq.add(sum);
			}

			sb.append(ans).append("\n");
		}

		sb.setLength(sb.length() - 1);
		System.out.println(sb.toString());

	}

}
