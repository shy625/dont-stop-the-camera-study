

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main_1334_다음_팰린드롬_수 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String strN = br.readLine();
		int l = strN.length();
		String strHalf = strN.substring(0, (l+1)/2); // N의 앞 절반
		StringBuffer sb = new StringBuffer(strHalf);
		String reversedstrHalf = sb.reverse().toString(); // N의 앞 절반을 뒤집은 값
		String nineRepeat = ""; // 9, 99, 999, ...와 같은 수 중 길이가 N과 같은 값
		for (int i = 0; i < l; i++) {
			nineRepeat += "9";
		}
		// 1. N이 9, 99, 999, ... 와 같은 경우
		if (strN.equals(nineRepeat)) {
			// 11, 101, 1001, ... 을 출력
			BigInteger b = new BigInteger(strN).add(new BigInteger("2"));
			System.out.println(b.toString());
		// 2. N이 한 자리 수
		} else if (l == 1) {
			// N+1을 출력
			BigInteger b = new BigInteger(strN).add(new BigInteger("1"));
			System.out.println(b.toString());
		// 3. N이 홀수 자리 수
		} else if (l%2 == 1) {
			// 앞 절반과 이를 뒤집은 절반을 합했을 때, 
			// 해당 값이 N보다 크면 그 값을 출력하고,
			// 그렇지 않으면 앞 절반에 1을 더한 뒤 이를 뒤집은 절반과 합쳐서 출력
			String temp = strHalf + reversedstrHalf.substring(1);
			if ((new BigInteger(temp)).compareTo(new BigInteger(strN)) > 0) {
				System.out.println(temp);
			} else {
				temp = ((new BigInteger(strHalf)).add(new BigInteger("1"))).toString();
				sb = new StringBuffer(temp);
				reversedstrHalf = sb.reverse().toString();
				temp = temp + reversedstrHalf.substring(1);
				System.out.println(temp);
			}
		// 4. N이 짝수 자리 수
		} else {
			// 앞 절반과 이를 뒤집은 절반을 합했을 때, 
			// 해당 값이 N보다 크면 그 값을 출력하고,
			// 그렇지 않으면 앞 절반에 1을 더한 뒤 이를 뒤집은 절반과 합쳐서 출력
			String temp = strHalf + reversedstrHalf;
			if ((new BigInteger(temp)).compareTo(new BigInteger(strN)) > 0) {
				System.out.println(temp);
			} else {
				temp = ((new BigInteger(strHalf)).add(new BigInteger("1"))).toString();
				sb = new StringBuffer(temp);
				reversedstrHalf = sb.reverse().toString();
				temp = temp + reversedstrHalf;
				System.out.println(temp);
			}
		}
	}

}
