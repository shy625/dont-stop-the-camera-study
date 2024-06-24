import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_17615_볼모으기 {

	static int N;
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		char [] C=br.readLine().toCharArray();
		
		int totalR=0;
		int totalB=0;
		int LR=0,LB=0;
		int RR=0,RB=0;
		
		boolean stillRed=true;
		boolean stillBlue=true;
		for (int i = 0; i < N; i++) {
			if(C[i]=='R') {//R인 경우
				totalR++;
				if(stillBlue) stillBlue=false;
				
				if(stillRed) LR++;
			}else {//B인 경우
				totalB++;
				if(stillRed)stillRed=false;
				
				if(stillBlue) LB++;
			}
		}
		
		stillRed=true;
		stillBlue=true;
		for (int i = N-1; i >=0; i--) {
			if(!stillBlue && !stillRed) break;
			
			if(C[i]=='R') {
				if(stillBlue) stillBlue=false;
				
				if(stillRed) RR++;
			}else {
				if(stillRed)stillRed=false;
				
				if(stillBlue) RB++;
			}
		}
		
		
		int min=Integer.MAX_VALUE;
		
		//왼쪽 Red 오른쪽 Blue
		//빨간공 이동
		min=Math.min(min, totalR-LR);
		//파란공 이동
		min=Math.min(min, totalB-RB);
		//왼쪽 Blue 오른쪽 Red
		//빨간공 이동
		min=Math.min(min, totalR-RR);
		//파란공 이동
		min=Math.min(min, totalB-LB);
		
		System.out.println(min);
	}

}
