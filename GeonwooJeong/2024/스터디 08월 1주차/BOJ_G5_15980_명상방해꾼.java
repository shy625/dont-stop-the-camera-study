import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_15980_명상방해꾼 {
	static class Bird {
		int side;
		String sound;
		
		public Bird(int side, String sound) {
			this.side = side;
			this.sound = sound;
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// 새들의 지저귐 정보를 저장할 1차원 배열
		Bird [] arr = new Bird[N];
		// 새들의 지저귐의 합을 저장할 1차원 배열
		int [] level = new int[M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			// L이면 -1, R이면 1을 저장한다.
			int side = st.nextToken().charAt(0) == 'L' ? -1 : 1;
			// 각 새가 몇초에 지저귀었는지 저장한다.
			String sound = st.nextToken();
			
			arr[i] = new Bird(side, sound);
			for (int j = 0; j < M; j++) {
				// level 배열에는 지저귐 정보와 방향을 합산하여 더해준다.
				level[j] += (sound.charAt(j) - '0') * side;
			}
		}
		
		// 방해받는 소리의 크기가 가장 작은 값을 저장하기 위한 변수
		int min = Integer.MAX_VALUE;
		// 그 때 새의 idx를 저장할 변수
		int minIdx = -1;
		
		// 0 ~ N-1번째 새를 잡았을 때의 값을 확인해본다.
		for (int i = 0; i < N; i++) {
			Bird bird = arr[i];
			
			// 매초 지저귐의 합을 저장할 변수
			int sum = 0;
			// 0 ~ M초까지 확인해보면서 소리가 가장 컸던 값을 저장한다.
			int max = Integer.MIN_VALUE;
			
			for (int j = 0; j < M; j++) {
				// i번째 새를 잡으면, i번째 새의 반대 방향으로 +1 해줘야 한다.
				sum += level[j] + (bird.sound.charAt(j) - '0') * bird.side * -1;
				// j초에서의 소리의 크기를 max와 비교하며 갱신한다.
				max = Math.max(max, Math.abs(sum));
			}
			
			// i번째 새를 잡았을 때가 소리가 가장 작으면, min과 minIdx를 갱신한다.
			if(min > max) {
				min = max;
				minIdx = i;
			}	
		}
		
		System.out.println(minIdx+1);
		System.out.println(min);
		
	}

}
