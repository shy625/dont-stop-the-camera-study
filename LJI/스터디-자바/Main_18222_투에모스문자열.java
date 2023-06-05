import java.util.Scanner;

public class Main_18222_투에모스문자열 {

//	0
//	01
//	0110
//	01101001  //내가 7번째 궁금하면?-> 반 쪼개서 3번째 위치 값 반전 -> 3번째 위치는 ? 반쪼개서 첫번쨰 위치 반전  , 따라서 첫번쨰는 0 ,3번째는 1, 7번째는 0 순이 된다 // 7-4=3 , 3-2=1
//  0110100110010110
	
	//규칙성 찾기
	//자리수가 2의 제곱
	//그전 문자열에  같은 위치를 반대로?
	
	static long size[];
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		
		long k=scann.nextLong();
		
		//long 크기 64비트//자리 크기 기록
		size=new long[64];
		size[0]=1;
		for (int i = 1; i < size.length; i++) {
			size[i]=size[i-1]*2;
		}
		
		int answer=find(k);
		System.out.println(answer);
	}
	private static int find(long k) {//내가 7번째 궁금하면?-> 반 쪼개서 3번째 위치 값 반전 -> 3번째 위치는 ? 반쪼개서 첫번쨰 위치 반전  , 따라서 첫번쨰는 0 ,3번째는 1, 7번째는 0 순이 된다
		if(k==1) return 0;//처음 시작 값
		
		for (int i = 0; i < 64; i++) {
			if(size[i]>=k) {//해당 값이 이번쪽에 있다 -> 내가 궁금한 곳은 k-전 사이즈의 값을 반대로 하는 것
				if(find(k-size[i-1])==1)return 0;
				else return 1;
			}
		}
		
		//차피 올일 없는 곳
		return 0;
	}

}
