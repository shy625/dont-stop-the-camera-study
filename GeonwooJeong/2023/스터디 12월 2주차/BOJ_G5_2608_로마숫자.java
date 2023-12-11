import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_G5_2608_로마숫자 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str1 = br.readLine();
		String str2 = br.readLine();
		
		str1 = toEasy(str1);
		str2 = toEasy(str2);
		
		int num1 = toNum(str1);
		int num2 = toNum(str2);
		
		int ans1 = num1 + num2;
		String ans2 = toRoma(ans1);
		
		System.out.println(ans1);
		System.out.println(ans2);
	}

	private static String toRoma(int num) {
		char [] arr = new char[] {'I', 'V', 'X', 'L', 'C', 'D', 'M'};
		StringBuilder sb = new StringBuilder();
		int length = String.valueOf(num).length();
		int tmp = (int) Math.pow(10, length-1);
		
		for (int i = length-1; i >= 0; i--) {
			int n = num / tmp;
			
			if(n == 0) {
				tmp /= 10;
				continue;
			}
			
			if(n == 4) {
				sb.append(arr[i*2]).append(arr[i*2+1]);
			} else if(n == 9) {
				sb.append(arr[i*2]).append(arr[(i+1)*2]);
			} else {
				if(n >= 5) {
					sb.append(arr[i*2+1]);
				}
				int cnt = n % 5;
				for (int j = 0; j < cnt; j++) {
					sb.append(arr[i*2]);
				}
			}
			
			num -= n*tmp;
			tmp /= 10;
		}
		
		return sb.toString();
	}

	private static String toEasy(String str) {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if(c == 'I') {
				sb.append('a');
			} else if(c == 'V') {
				sb.append('b');
			} else if(c == 'X') {
				sb.append('c');
			} else if(c == 'L') {
				sb.append('d');
			} else if(c == 'C') {
				sb.append('e');
			} else if(c == 'D') {
				sb.append('f');
			} else if(c == 'M') {
				sb.append('g');
			}
		}
		
		return sb.toString();
	}

	private static int toNum(String str1) {
		int sum = 0;
		char past = str1.charAt(0);
		
		boolean pass = false;
		for (int i = 1; i < str1.length(); i++) {
			char c = str1.charAt(i);
			
			if(pass) {
				pass = false;
				past = c;
				continue;
			}
			
			if(past >= c) {
				sum = cal(sum, past, 1);
			} else {
				pass = true;
				sum = cal(sum, c, 1);
				sum = cal(sum, past, -1);
			}
			
			past = c;
		}
		
		if(!pass) {
			sum = cal(sum, past, 1);
		}
		
		return sum;
	}

	private static int cal(int sum, char c, int comm) {
		int n = 0;
		
		if(c == 'a') {
			n = 1;
		} else if(c == 'b') {
			n = 5;
		} else if(c == 'c') {
			n = 10;
		} else if(c == 'd') {
			n = 50;
		} else if(c == 'e') {
			n = 100;
		} else if(c == 'f') {
			n = 500;
		} else if(c == 'g') {
			n = 1000;
		}
		
		if(comm > 0) {
			sum += n;
		} else if(comm < 0) {
			sum -= n;
		}
		
		return sum;
	}

}
