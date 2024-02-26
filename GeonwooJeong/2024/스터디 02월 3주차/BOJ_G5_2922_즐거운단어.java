import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_G5_2922_즐거운단어 {
	static int N;
	static char [] arr;
	static long ans;
	
	// 자음일 경우, 모음일 경우, L일 경우로 나눠서 백트래킹 진행
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		arr = br.readLine().toCharArray();
		N = arr.length;
		
		make(0, 0, 0, false);
		
		System.out.println(ans);
		
	}

	private static void make(int idx, int jaum, int moum, boolean flag) {
		// 자음이나 모음이 연속 3번이상 오는 것은 불가능하다.
		if(jaum >= 3 || moum >= 3) return;
		
		// 단어를 완성했을 때, 규칙을 만족하면 경우의 수가 몇가지 되는지 계산하여 더해준다.
		if(idx == N) {
			if(!flag) return;
			cal();
			
			return;
		}
		
		char c = arr[idx];
		if(c == '_') {
			
			// 자음을 넣는다.
			arr[idx] = '0';
			make(idx+1, jaum+1, 0, flag);
			
			// 모음을 넣는다.
			arr[idx] = '1';
			make(idx+1, 0, moum+1, flag);
			
			// 'L'을 넣은 뒤 백트래킹 (arr[idx], idx, jaum, moum, flag에 대해)
			arr[idx] = '2';
			idx++;
			jaum++;
			
			int beforeMoum = moum;
			moum = 0;
			
			boolean tmp = flag;
			flag = true;
			
			make(idx, jaum, moum, flag);
			
			flag = tmp;
			moum = beforeMoum;
			
			jaum--;
			idx--;
			
			arr[idx] = '_';
			// 빈 칸이 아닌 경우는 자음인지 모음인지 L인지 여부만 파악한다.
		} else {
			if(isMoum(c)) {
				make(idx+1, 0, moum+1, flag);
			} else if(c == 'L'){
				make(idx+1, jaum+1, 0, true);
			} else {
				make(idx+1, jaum+1, 0, flag);
			}
		}	
		
	}

	// 완성한 단어의 총 경우의수가 몇인지 계산하는 함수.
	// 자음은 B, C, ... X, Y, Z(20개), 모음은 A, E, I, O, U(5개), L은 1개로 계산한다. 
	private static void cal() {
		long base = 1;
		for (int i = 0; i < N; i++) {
			char c = arr[i];
			// 자음일 경우(L은 제외)
			if(c == '0') {
				base *= 20;
			// 모음일 경우
			} else if(c == '1') {
				base *= 5;
			} 
		}
		
		ans += base;
	}

	private static boolean isMoum(char c) {
		return c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
	}

}
