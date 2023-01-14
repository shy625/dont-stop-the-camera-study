import java.util.Scanner;

public class Main_1484_다이어트 {

	static int G;
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		G=scann.nextInt();
		
		int now=1;//현재 몸무게
		int pre=1;//이전 몸무게
		boolean can=false;
		while(Math.pow(now, 2)-Math.pow(now-1, 2)<=G) {
			if(Math.pow(now, 2)-Math.pow(pre, 2)==G) {
				if(!can) can=true;
				System.out.println(now++);
			}
			else if(Math.pow(now, 2)-Math.pow(pre, 2)>G)pre++;
			else now++;
		}
		
		if(!can)System.out.println("-1");
	}

}
