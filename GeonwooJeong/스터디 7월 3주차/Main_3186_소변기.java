import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_3186_소변기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int K = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		StringBuilder tmp = new StringBuilder(br.readLine());
		// 사용 완료를 표시할 변수
		boolean finished = false;
		// 사용중인 시간 변수
		int using = 0;
		// 비어있는 시간 변수
		int empty = 0;
		
		// 끝나고 나서도 체크해야 하는 경우가 있어서 L만큼 0을 뒤에 붙여준다.
		for (int i = 0; i < L; i++) {
			tmp.append('0');
		}
		
		String str = tmp.toString();
		
		for (int i = 0; i < N+L; i++) {
			int n = str.charAt(i)-'0';
			// 0일 경우
			if(n == 0) {
				// 사용중인 시간이 K보다 크면 사용완료를 true로 전환
				if(using >= K) {
					finished = true;
				}
				// 비어있는 시간 추가, 사용중인 시간 0초
				empty++;
				using = 0;
			// 1일 경우
			} else if (n == 1) {
				// 사용중인 시간 추가, 비어있는 시간 0초
				using++;
				empty = 0;
			}
			// 비어있는 시간이 L초가 되고, 사용완료 상태라면
			if(empty == L && finished) {
				sb.append(i+1).append('\n');
				finished = false;
			}
		}
		
		if(sb.length() == 0) {
			System.out.println("NIKAD");
		} else {
			System.out.println(sb.toString());
		}
	}

}
