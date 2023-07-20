import java.util.Scanner;

public class BOJ_S1_20921_그렇고그런사이 {

	public static void main(String[] args) {
		Scanner scann = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int N = scann.nextInt();
		int K = scann.nextInt();
		int origin = N;
		
		boolean [] used = new boolean[N+1];
		
		while(K > 0) {
			// 답이 없을 경우를 체크하기 위한 boolean 변수
			boolean check = false;
			if(K >= N-1) {
				// K가 N-1보다 큰 경우, 남아있는 수중에 가장 큰 수를 사용하고 해당 숫자를 사용했음 처리 해준다.
				sb.append(N+" ");
				used[N] = true;
				//  K를 (N-1) 만큼 빼준다.
				K -= (N-1);
				// 사용할 수 있는 최대 숫자를 1 낮춘다.
				N--;
			} else {
				// K가 N-1보다 작을 경우 남아있는 수중에 가장 큰 수를 사용하지 못해서 어떤 수를 사용할지 계산하기 위한 변수
				// K+1인 값부터 사용할 수 있다.
				int tmp = K+1;
				while(true) {
					// 이미 사용했던 적이 있는 숫자는 다시 사용할 수 없어서 -1한 수를 다시 체크해본다.
					if(used[tmp]) {
						tmp--;
					// 조건에 맞고, 사용했던 적이 없으면 그 수를 사용한다.
					} else {
						break;
					}
					
					// K+1부터 0까지 모든 수를 사용했을 경우 무한 루프문을 탈출하기 위한 if문
					if(tmp == 0) {
						check = true;
						break;
					}
				}
				
				sb.append(tmp+" ");
				used[tmp] = true;
				K -= tmp;
			}
			// K+1 ~ 0까지 모든 숫자를 사용했다면 답을 구할 수 없다.
			if(check) {
				System.out.println("답이 없음");
				break;
			}
		}
		
		// 1부터 N까지 숫자 중 사용하지 않은 숫자를 차례대로 나열한다.
		for (int i = 1; i <= origin; i++) {
			if(!used[i]) sb.append(i+" ");
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
		

	}

}
