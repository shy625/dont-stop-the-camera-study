

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2141_우체국 {
	// 각 마을가 몇 번쨰 마을인지, 마을에 몇 명의 사람이 있는지를 나타내는 클래스
	static class Coordinate implements Comparable<Coordinate> {
		int idx; long people;
		public Coordinate(int idx, long people) {
			this.idx = idx;
			this.people = people;
		}
		public int compareTo(Coordinate c) {
			return Integer.compare(this.idx, c.idx);
		}		
	}
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Coordinate[] coordinates = new Coordinate[N];
		// 우체국의 위치를 X라고 할 때, 
		// 우체국과 사람들 간의 총 거리의 합은 A_1*|X-X_1|+A_2*|X-X_2|+...+A_N*|X-X_N|이다.
		// 해당 함수의 그래프 특징은, 값이 최소가 될 때가 기울기가 음수에서 양수로 넘어갈 떄,
		// 즉 A_1+...+A_(i-1)-A_i-...-A_N이 처음으로 양수가 될 때 X_i에서 최솟값이 나온다는 것이다.
		// 따라서, -A_1-...-A_N부터 2*A_i를 i가 1일 때부터 순차적으로 더해나가면서
		// 처음으로 양수가 되는 순간을 구하면 된다.
		long sumGrad = 0l;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			long people = Long.parseLong(st.nextToken());
			coordinates[i] = new Coordinate(idx, people);
			sumGrad -= people;
		}
		Arrays.sort(coordinates);
		int idx = coordinates[0].idx;
		for (int i = 0; i < N; i++) {
			sumGrad += coordinates[i].people * 2;
			if (sumGrad >= 0) {
				idx = coordinates[i].idx;
				break;
			}
		}
		System.out.println(idx);
	}
}
