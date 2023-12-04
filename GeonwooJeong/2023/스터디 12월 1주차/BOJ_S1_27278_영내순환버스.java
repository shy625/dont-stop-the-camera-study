import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S1_27278_영내순환버스 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// 누적합을 저장할 배열
		int [] sum = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			int n = Integer.parseInt(st.nextToken());
			sum[i] = sum[i-1] + n;
		}
		
		int max = 0;
		
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			
			// 출발 지점
			int a = Integer.parseInt(st.nextToken());
			// 도착 지점
			int b = Integer.parseInt(st.nextToken());
			// 처음 기다린 시간
			int min = Integer.parseInt(st.nextToken());
			
			// 버스가 몇바퀴 돌아야 할지 계산한다.
			int cycle = min / sum[N];
			// 버스가 remain만큼 돌고, 남은 시간을 계산한다.
			int remain = min % sum[N];
			// 만약 1번~a지점까지의 거리가 remain보다 작으면 한 바퀴 더 돌아야 한다.
			if(sum[a-1] < remain) cycle++;
			// 탑승 시각은 (돈 바퀴 수) * (한 바퀴 도는 데 걸리는 시간) + (1번~a지점까지의 거리)이다.
			int ride = cycle * sum[N] + sum[a-1];
			
			// 하차 시각을 저장할 변수
			int quit = 0;
			// a<b라면 단순히 누적합을 이용해 계산한다.
			if(a < b) {
				quit = sum[b-1] - sum[a-1] + ride;
			// a>b라면 b~N지점 까지의 시간을 구하고, 1~a지점까지 거리를 더해준다.
			} else if(a > b) {
				quit = sum[N] - sum[a-1] + sum[b-1] + ride;
			}
			
			// 하차 시각을 기준으로 max를 갱신한다.
			max = Math.max(max, quit);
			
		}
		
		System.out.println(max);
		
	}

}
