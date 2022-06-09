

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main_1793_타일링 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line;
		// 2*n 직사각형을 채우는 경우의 수를 n번째 값으로 하는 배열
		// 이때 n에 따라 그 값이 매우 커질 수 있어 long 타입에서도 overflow가 발생한다.
		// 따라서 큰 수를 나타내주는 BigInteger를 활용한다.
		BigInteger[] arr = new BigInteger[251];
		// 초기값
		arr[1] = new BigInteger("1");
		arr[0] = new BigInteger("1");
		StringBuilder sb = new StringBuilder();
		// 입력의 개수가 주어지지 않아 입력이 끝날 때까지 입력을 받는다.
		while ((line = br.readLine()) != null) {
			int n = 0;
			// 입력이 끝나면 break
			try {
				n = Integer.parseInt(line);
			} catch (Exception e) {
				break;
			}
			// 이전에 구한 적이 있으면 그 값을 출력한다.
			if (arr[n] != null && arr[n].compareTo(new BigInteger("0")) > 0) sb.append(arr[n]);
			else {
				// 구한 적이 없으면 점화식을 활용해 n번째 값을 구한다.
				for (int i = 2; i <= n; i++) {
					arr[i] = arr[i-2].multiply(new BigInteger("2")).add(arr[i-1]);
				}
				sb.append(arr[n]);
			}
			sb.append("\n");						
		}
		// 출력
		if (sb.length() > 0) sb.setLength(sb.length() - 1);
		System.out.println(sb.toString());
	}

}
