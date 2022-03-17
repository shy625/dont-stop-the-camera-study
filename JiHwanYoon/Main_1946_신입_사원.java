

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1946_신입_사원 {

	public static void main(String[] args) throws IOException{
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			// grade[i]는 서류심사 성적이 (i+1)위인 사람의 면접 성적 순위를 의미
			int[] grades = new int[N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				int r1 = Integer.parseInt(st.nextToken());
				int r2 = Integer.parseInt(st.nextToken());
				grades[r1-1] = r2;
			}
			// 서류심사 성적이 (i+1)위인 사람까지의 면접 성적 중 가장 좋은 면접 성적 순위
			int best = grades[0];
			// 선발 가능한 신입 사원의 최대 인원 수
			int cnt = 1;
			for (int i = 1; i < N; i++) {
				// 서류심사 성적이 (i+1)위인 사람은 면접 성적이 자기보다 서류심사 성적이 더 높은 사람들(1위부터 i위)의 가장 높은 면접 성적(best)보다 좋아야 선발될 수 있다.
				if (best > grades[i]) {
					cnt++;
					best = grades[i];
				}
			}
			System.out.println(cnt);
		}

	}

}
