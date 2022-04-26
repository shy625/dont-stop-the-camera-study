

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2212_센서 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		int[] points = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			points[i] = Integer.parseInt(st.nextToken());
		}
		// 센서 좌표를 오름차순으로 정렬
		Arrays.sort(points);
		// 수신 가능거리의 합이 최소가 되려면 인접한 센서와의 거리들 중 그 값이 큰 것부터 제거하면 된다.
		// 이를 위해 인접한 센서와의 거리를 계산하고 정렬한 뒤 뒤에서부터, 즉 큰 값부터 K개만큼은 수신 가능거리 계산에 제외한다.
		int[] diff = new int[N-1];
		for (int i = 0; i < N-1; i++) {
			diff[i] = points[i+1] - points[i];
		}
		Arrays.sort(diff);
		int sum = 0;
		for (int i = 0; i < N - K; i++) {
			sum += diff[i];
		}
		System.out.println(sum);
	}

}
