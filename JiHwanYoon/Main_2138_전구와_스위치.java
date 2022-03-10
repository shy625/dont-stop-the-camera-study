

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_2138_전구와_스위치 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// 0번째 스위치를 건드리지 않는 경우
		char[] first = br.readLine().toCharArray();
		// 목표
		char[] last = br.readLine().toCharArray();
		// 0번째 스위치를 건드리는 경우
		char[] temp = Arrays.copyOf(first, N);
		// 스위치 누른 횟수의 최솟값
		int min = Integer.MAX_VALUE/100;
		min = Math.min(min, burbSwitch(first, last, N));
		// 0번째 스위치 누름
		temp[0] = temp[0] == '0' ? '1' : '0';
		temp[1] = temp[1] == '0' ? '1' : '0';
		min = Math.min(min, burbSwitch(temp, last, N)+1);
		// min이 Integer.MAX_VALUE/100보다 크거나 같으면 스위치를 눌러서 원하는 상태를 만들 수 없음을 의미
		System.out.println(min >= Integer.MAX_VALUE/100 ? -1 : min);
		
	}
	// 원하는 상태를 만들기 위해 전구 스위치를 누르는 횟수의 최솟값을 구하는 함수
	private static int burbSwitch(char[] first, char[] last, int N) {
		// 스위치 누른 횟수
		int cnt = 0;
		// 최종 결과
		int res = Integer.MAX_VALUE/100;
		// 1번째 스위치부터 N-2번째 스위치까지 스위치 위치 앞의 전구의 상태와 원하는 상태가 다른 경우에만 스위치를 누른다.
		// 해당 스위치 이후로는 그 전구의 상태를 바꿀 방법이 없기 때문이다.
		for (int i = 1; i < N-1; i++) {
			if (first[i-1] == last[i-1]) continue;
			first[i-1] = first[i-1] == '0'? '1' : '0';
			first[i] = first[i] == '0' ? '1' : '0';
			first[i+1] = first[i+1] == '0' ? '1' : '0';
			cnt++;
		}
		// N-1번째 스위치를 누르지 않아도 원하는 상태에 도달하는 경우
		if (first[N-2] == last[N-2] && first[N-1] == last[N-1]) {
			res = cnt;
		} else if (first[N-2] != last[N-2] && first[N-1] != last[N-1]) { // N-1번째 스위치를 누르면 원하는 상태에 도달하는 경우
			res = cnt + 1;
		}
		// 위 두 경우가 아니면 N-1번째 스위치를 눌러도 원하는 상태에 도달할 수 없다.
		return res;
	}

}
