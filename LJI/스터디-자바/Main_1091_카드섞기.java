import java.util.Arrays;
import java.util.Scanner;

public class Main_1091_카드섞기 {

	static int N;
	static int answer;
	static int [] P;
	static int [] S;
	static int [] StartP;
	//최종적으로 P[]의 형태가 012012012형태가 되어야한다
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		N=scann.nextInt();
		
		P=new int[N];
		S=new int[N];
		
		answer=0;
		
		for (int i = 0; i < N; i++) {
			P[i]=scann.nextInt();
		}
		StartP=Arrays.copyOf(P, N);
		for (int i = 0; i < N; i++) {
			S[i]=scann.nextInt();
		}
		
		if(check()) {
			System.out.println(answer);
			return;
		}
		
		while(true) {//최종적으로 N*N정도면 될듯?
			shuffle();
			answer++;
			if(check()) {
				System.out.println(answer);
				return;
			}
			if(circle()) {//처음 배열과 같아지면 순환되고 정답을 찾을 수 없다는 뜻
				break;
			}
		}
		System.out.println("-1");
	}
	private static boolean circle() {
		for (int i = 0; i < N; i++) {
			if(P[i]!= StartP[i] ) return false;
		}
		return true;
	}
	private static void shuffle() {
		int [] newDeck=new int[N];
		for (int i = 0; i < N; i++) {
			newDeck[S[i]]=P[i];
		}
		P=newDeck;
		
	}
	private static boolean check() {
		for (int i = 0; i < N; i++) {
			if(P[i]!= i%3 ) return false;
		}
		return true;
	}

}
