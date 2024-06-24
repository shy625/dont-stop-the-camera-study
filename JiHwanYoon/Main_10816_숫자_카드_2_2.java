

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_10816_숫자_카드_2_2 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		// counting을 활용하는 방법
		// -10000000~10000000에 대해 각 원소가 숫자 카드 내에서 몇 번 나오는지를 저장한다.
		// 단, 배열은 음수 인덱스를 가지지 않으므로 이를 보정하기 위해 모든 원소에 10000000을 더한 결과를 인덱스로 한다.
		// 따라서 있어야 하는 배열의 인덱스는 0~20000000이므로 20000001 크기의 배열 생성
		int[] arr = new int[20000001];
		// 위에서 설명한 대로 각 숫자에 10000000을 더한 결과를 인덱스로 하는 원소에 1을 더해준다.
		for (int i = 0; i < N; i++) {
			arr[Integer.parseInt(st.nextToken())+10000000]++;
		}
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		// M개의 숫자에 대해 이분 탐색을 통해 해당 숫자가 몇 개 있는지 파악
		// M이 크기 때문에 출력은 StringBuilder를 이용
		StringBuilder sb = new StringBuilder();
		// 아까 보정한 결과를 인덱스로 했기 때문에 결과값을 출력할 때도 보정이 필요하다.
		for (int i = 0; i < M; i++) {
			sb.append(arr[Integer.parseInt(st.nextToken())+10000000]+" ");
		}
		System.out.println(sb.toString());
	}
}
