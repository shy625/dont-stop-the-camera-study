import java.util.Scanner;

public class Main_1912_연속합 {

	static int N;
	static int [] arr;
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		N=scann.nextInt();

		arr=new int[N+1];
		for (int i = 1; i <= N; i++) {
			arr[i]=scann.nextInt();
		}
		
		//
		int [] add=new int[N+1];//연속으로 더해진 값이 양수일 때만 기록
		int [] answer=new int[N+1];//n번째까지 최대 연속 수의 값 배열
		
		if(arr[1]>=0)add[1]=arr[1];
		else add[1]=0;
		
		answer[1]=arr[1];
		
		for (int i = 2; i <=N; i++) {
			int temp=arr[i]+add[i-1];
			if(temp<0) {//그전에 이어온 총합이 0보다 작으면 이후에도 쓸 수 없다
				add[i]=0;
			}else { //-를 당해도 양수라면 후에 더한 값이 더 커진다면 최댓값에 쓰일 수 있다
				add[i]=temp;
			}
			
			if(add[i]!=0&& add[i]>answer[i-1]) {//새로운 원소를 더한 연속 배열의 값>그전까지의 최대합
				//answer[i]=Math.max( arr[i], add[i]);
				answer[i]=add[i];
			}else {//그 전까지의 최대값이 더 큰 경우
				answer[i]=Math.max(arr[i], answer[i-1]);
			}
		}
		
		System.out.println(answer[N]);
	}

}
