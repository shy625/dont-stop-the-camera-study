import java.util.Scanner;

public class BOJ_2205_S1_저울추만들기 {

	public static void main(String[] args) {
		Scanner scann = new Scanner(System.in);
		
		int N = scann.nextInt();
		// i번째 납덩어리와 합친 주석덩어리의 질량을 저장할 배열
		int [] arr = new int[N+1];
		// i번째 주석덩어리를 사용했는지 여부를 저정할 배열
		boolean [] used = new boolean[N+1];
		
		int weight = 1;
		
		// 납덩어리와 주석덩어리를 합쳐서 만들 수 있는 최대 무게
		while(weight <= N) {
			weight *= 2;
		}
		
		// 주석덩어리가 1개씩 밖에 없으므로, 큰 무게부터 확인한다.
		for (int i = N; i >= 1; i--) {
			// 합쳐서 만들 수 있는 2의 거듭제곱을 큰 순서대로 찾는다.
			for (int j = weight; j >= 1; j/=2) {
				// 해당 무게가 N보다 작고, 주석덩어리를 사용하지 않았다면 해당 무게룰 사용한다.
				if(j-i <= N && !used[j-i]) {
					arr[i] = j-i;
					used[j-i] = true;
					break;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 1; i <= N; i++) {
			sb.append(arr[i]+"\n");
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());

	}

}
