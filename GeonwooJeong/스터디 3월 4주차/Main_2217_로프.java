import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_2217_로프 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		// 로프 정보를 저장할 배열
		int [] arr = new int [N];
		// 들어올릴 수 있는 중량 중 최댓값(출력값)
		int max = -1;
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(arr);

		// 중량을 구하는 공식 : i번째 무게 x (i번째 무게보다 큰 로프의 수 + i번째 자신)
		for (int i = 0; i < N; i++) {
			max = Math.max(max, arr[i] * (N - i));
		}
		
		System.out.println(max);
		
	}

}
