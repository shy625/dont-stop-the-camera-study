import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_G5_23796_2_147_483_648게임 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 초기 상태를 저장할 배열
		long [][] arr = new long[8][8];
		for (int i = 0; i < 8; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 8; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 최종 상태를 저장할 배열
		long [][] ans = new long[8][8];
		
		// 방향키를 저장할 변수
		char comm = br.readLine().charAt(0);
		// 스택을 사용하여 같은 타일을 합친다.
		Stack<Long> stack = new Stack<>();
		
		switch (comm) {
		case 'U':
			// column 값 0~8에 대해 작업을 수행한다.
			for (int i = 0; i < 8; i++) {
				// 세로로 한 줄에서, 아래서부터 숫자들을 모두 stack에 담는다.
				for (int j = 7; j >= 0; j--) {
					if(arr[j][i] != 0) stack.add(arr[j][i]);
				}
				
				// 마찬가지로 세로로 한 줄에서, 스택에서 숫자를 꺼내보며 타일을 합쳐본다.
				for (int j = 0; j < 8; j++) {
					// 만약 stack에서 숫자를 모두 꺼냈다면 나머지 값들은 0인 상태로 작업을 종료한다.
					if(stack.size() == 0) break;
					
					// 숫자 1개를 꺼낸다.
					long n = stack.pop();
					
					// 만약 스택에 숫자가 남아있고, 다음 꺼낼 숫자가 현재 숫자와 같다면 합친다.
					if(stack.size() != 0 && stack.peek() == n) {
						n += stack.pop();
					}
					// ans 배열에 값을 순서대로 채워넣는다.
					ans[j][i] = n;
				}
			}
			
			break;
		// 나머지 경우에 대해서도 방향만 다르고 로직은 같다.
		case 'D':
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if(arr[j][i] != 0) stack.add(arr[j][i]);
				}
				
				for (int j = 7; j >= 0; j--) {
					if(stack.size() == 0) break;
					
					long n = stack.pop();
					
					if(stack.size() != 0 && stack.peek() == n) {
						n += stack.pop();
					}
					ans[j][i] = n;
				}
			}
			
			break;
		case 'L':
			for (int i = 0; i < 8; i++) {
				for (int j = 7; j >= 0; j--) {
					if(arr[i][j] != 0) stack.add(arr[i][j]);
				}
				
				for (int j = 0; j < 8; j++) {
					if(stack.size() == 0) break;
					
					long n = stack.pop();
					
					if(stack.size() != 0 && stack.peek() == n) {
						n += stack.pop();
					}
					ans[i][j] = n;
				}
			}
			
			break;
		case 'R':
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if(arr[i][j] != 0) stack.add(arr[i][j]);
				}
				
				for (int j = 7; j >= 0; j--) {
					if(stack.size() == 0) break;
					
					long n = stack.pop();
					
					if(stack.size() != 0 && stack.peek() == n) {
						n += stack.pop();
					}
					ans[i][j] = n;
				}
			}
			
			break;
		default:
			break;
		}
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				sb.append(ans[i][j]+" ");
			}
			sb.setLength(sb.length()-1);
			sb.append("\n");
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
		
	}

}
