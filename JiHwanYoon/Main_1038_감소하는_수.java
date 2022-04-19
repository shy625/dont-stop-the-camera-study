

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main_1038_감소하는_수 {
	static ArrayList<Long> arr;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		arr = new ArrayList<>();
		// 부분 집합을 이용해 가능한 감소하는 수를 모두 구한다.
		subset(0, 0, 1);
		// 감소하는 수를 모두 구한 뒤 정렬한다.
		Collections.sort(arr);
		// N이 감소하는 수의 총 개수보다 작으면 N번째 감소하는 수를 출력하고, 그렇지 않으면 -1을 출력한다.
		// 가능한 감소하는 수는 2^10-1 = 1023개다.
		if (N < arr.size()) System.out.println(arr.get(N));
		else System.out.println(-1);
	}
	// cnt는 0~9까지 가능하고, 이 숫자를 sum에 포함할지 말지를 결정한다.
	// cnt를 sum에 포함시키면 t를 10배해서 다음 숫자를 입력할 수 있도록 한다.
	private static void subset(int cnt, long sum, long t) {
		if (cnt == 10) {
			if (t > 1) arr.add(sum);
			return;
		}
		// cnt를 sum에 포함시키지 않는 경우
		subset(cnt+1, sum, t);
		// cnt를 sum에 포함시키는 경우
		subset(cnt+1, sum+cnt*t, t*10);
	}

}
