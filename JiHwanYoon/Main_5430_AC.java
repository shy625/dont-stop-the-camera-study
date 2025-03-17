

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_5430_AC {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		outer: for (int t = 1; t <= T; t++) {
			String p = br.readLine();
			int n = Integer.parseInt(br.readLine());
			int[] arr = new int[n];
			String arrStr = br.readLine();
			StringTokenizer st = new StringTokenizer(arrStr.substring(1, arrStr.length()-1), ",");
			for (int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			// 배열에서 왼쪽 끝과 오른쪽 끝 인덱스를 조정하면서 R, D 함수 수행
			boolean reverse = false; // 현재 배열이 뒤집혀져 있는지 나타내는 변수
			int l = 0; int r = n-1; // 현재 남은 배열의 왼쪽 끝과 오른쪽 끝 인덱스
			for (char c : p.toCharArray()) {
				// 뒤집기
				if (c == 'R') {
					reverse = reverse ^ true;
				// 버리기
				} else if (c == 'D') {
					// 빈 배열인 경우 error 출력
					if (l > r) {
						sb.append("error").append("\n");
						continue outer;
					}
					// 뒤집히지 않았으면 왼쪽 끝을 버리고, 뒤집혔으면 오른쪽 끝을 버린다.
					if (!reverse) l++;
					else r--;
				}
			}
			// error가 발생하지 않은 경우 배열 출력
			sb.append("[");
			if (!reverse) {
				for (int i = l; i <= r; i++) {
					sb.append(arr[i]).append(",");
				}
			} else {
				for (int i = r; i >= l; i--) {
					sb.append(arr[i]).append(",");
				}
			}
			// 빈 배열이 아닌 경우 마지막 숫자에 붙은 쉼표 제거
			if (l <= r) sb.setLength(sb.length()-1);
			sb.append("]");
			sb.append("\n");
		}
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
