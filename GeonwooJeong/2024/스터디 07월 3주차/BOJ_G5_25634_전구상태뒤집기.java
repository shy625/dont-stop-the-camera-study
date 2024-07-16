import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_25634_전구상태뒤집기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		// 전구의 밝기를 저장할 1차원 배열
		int [] bright = new int[N];
		// 전구의 상태를 저장할 1차원 배열
		boolean [] light = new boolean[N];
		// 꺼진 전구가 1개도 없을 경우를 대비하기 위한 min 변수
		int min = Integer.MAX_VALUE;
		// 꺼진 전구가 1개도 없는지 체크하기 위한 boolean 변수
		boolean noZero = true;
		// 밝기의 합을 저장할 변수
		int ans = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			bright[i] = Integer.parseInt(st.nextToken());
			min = Math.min(min, bright[i]);
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			light[i] = Integer.parseInt(st.nextToken()) == 1 ? true : false;
			// 켜진 전구에 한해서 ans에 더해준다.
			if(light[i]) {
				ans += bright[i];
			} else {
				noZero = false;
			}
		}
		
		// 꺼진 전구가 1개도 없는 경우에는 밝기가 가장 작은 전구 1개를 끄고 종료한다.
		if(noZero) {
			System.out.println(ans - min);
			System.exit(0);
		}
		
		// 뒤집었을 때 최대 밝기를 저장할 변수
		int max = 0;
		// 연속적으로 뒤집을 때 밝기 변화의 합을 저장할 변수
		int sum = 0;
		
		for (int i = 0; i < N; i++) {
			// i번째 전구를 뒤집는다고 가정한다.
			// 이 때, 꺼진 전구를 켰으므로 전구의 밝기만큼 sum에 더해준다.
			if(!light[i]) {
				sum += bright[i];
			// 켜져있던 전구도 뒤집으므로 전구의 밝기만큼 sum에서 빼준다.
			} else {
				sum -= bright[i];
			}
			
			// 켜져 있던 전구를 끄면서 합이 0보다 작아지면, sum을 0으로 초기화하고 다음 전구부터 다시 계산한다.
			if(sum < 0) {
				sum = 0;
			}
			
			max = Math.max(max, sum);
			
		}
		
		System.out.println(ans + max);
		
		
	}

}
