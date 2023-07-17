import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G4_1027_고층건물 {
	static int N;
	static int [] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N];
		int max = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < N; i++) {
			max = Math.max(max, solve(i));
		}
		
		System.out.println(max);
	}

	private static int solve(int idx) {
		int cnt = 0;
		double tmp = 0;
		
		// 현재 건물을 기준으로 왼쪽으로 몇개가 보이는지 계산한다.
		for (int i = idx-1; i >= 0; i--) {
			// 현재 건물과 i번째 건물의 기울기를 계산한다.
			double slope = (double) (map[idx] - map[i]) / (idx - i);
			
			// 현재 건물 바로 왼쪽에 있는 건물이거나, 지금까지의 기울기보다 더 작은 경우에는 i번째 건물을 볼 수 있다.
			if(i == idx-1 || tmp > slope) {
				cnt++;
				tmp = slope;
			}
		}
		
		tmp = 0;
		
		// 현재 건물을 기준으로 오른쪽으로 몇개가 보이는지 계산한다.
		for (int i = idx+1; i < N; i++) {
			// 현재 건물과 i번째 건물의 기울기를 계산한다.
			double slope = (double) (map[idx] - map[i]) / (idx - i);
			
			// 현재 건물 바로 오른쪽에 있는 건물이거나, 지금까지의 기울기보다 더 큰 경우에는 i번째 건물을 볼 수 있다.
			if(i == idx+1 || tmp < slope) {
				cnt++;
				tmp = slope;
			}
		}
		
		return cnt;
	}

}
