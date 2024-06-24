

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1474_밑_줄 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		String[] arr = new String[N];
		int l = 0; // 단어들의 길이의 합
		for (int i = 0; i < N; i++) {
			arr[i] = br.readLine();
			l += arr[i].length();
		}
		int blank = M-l; // 밑 줄의 총 개수
		StringBuilder sb = new StringBuilder();
		// greedy algorithm을 이용
		// 기본적으로 단어 사이에는 blank/(N-1)개의 밑 줄이 들어가야 한다.
		// 이때 blank%(N-1)개의 밑 줄이 남을 수 있다.
		// 앞 문자가 소문자인 단어 앞에 해당 밑 줄을 넣을수록 사전순으로 앞서고,
		// 앞 문자가 대문자인 단어 앞에 해당 밑 줄을 넣지 않는게 사전순으로 앞선다.
		// 따라서, 앞에 있는 단어부터 소문자로 시작하는 단어면 밑 줄을 하나씩 더 넣고,
		// 그래도 밑 줄이 남으면 최대한 뒤에 있는 단어(대문자로 시작하는 단어)에 밑 줄을 하나씩 더 넣는다.
		int c = blank/(N-1); // 단어 사이에 기본적으로 넣어야 하는 밑 줄의 개수
		int k = blank%(N-1); // 추가적으로 넣어야 하는 밑 줄의 개수
		String B = ""; // 단어 사이에 들어가는 밑 줄들
		for (int i = 0; i < c; i++) {
			B += '_';
		}
		for (int i = 0; i < N-1; i++) {
			// 추가적으로 넣어야 하는 밑 줄이 있는 경우
			if (k > 0) {
				// 뒤에 있는 단어가 소문자로 시작하면 밑 줄 추가 
				if ('a' <= arr[i+1].charAt(0) && arr[i+1].charAt(0) <= 'z') {
					sb.append(arr[i] + '_' + B);
					k--;
				// 뒤에 있는 단어가 대문자로 시작하나, 반드시 밑 줄을 넣어야 길이가 M인 문자열을 만들 수 있는 경우
				} else if (i >= N-1-k) {
					sb.append(arr[i] + '_' + B);
					k--;
				// 밑 줄을 당장 꼭 넣을 필요없는 경우
				} else {
					sb.append(arr[i] + B);
				}
			// 추가적으로 넣어야 하는 밑 줄이 없는 경우
			} else {				
				sb.append(arr[i] + B);
			}
		}
		// 마지막 단어 추가
		sb.append(arr[N-1]);
		System.out.println(sb);
	}
}
