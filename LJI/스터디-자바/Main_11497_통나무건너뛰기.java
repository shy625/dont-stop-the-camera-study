import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_11497_통나무건너뛰기 {
	
	static int T,N;
	static PriorityQueue<Integer> line;
	static int max;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		T=Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			N=Integer.parseInt(br.readLine());
			line=new PriorityQueue<>(Collections.reverseOrder());
			StringTokenizer st=new StringTokenizer(br.readLine()," ");
			for (int i = 0; i <N; i++) {
				line.offer(Integer.parseInt(st.nextToken()));
			}
			
			max=0;
			
			int left=line.poll();
			int right=left;
			//처음 가장 높은 기둥에서 시작
			
			while(!line.isEmpty()) {
				int temp1=line.poll();
				int temp2;
				if(line.isEmpty()) {
					temp2=temp1;
				}else {
					temp2=line.poll();
				}
				
				int newDif=Math.max(left-temp1, right-temp2);
				
				max=Math.max(max, newDif);
				left=temp1;
				right=temp2;
			}
			
			max=Math.max(max, left-right);//마지막 연결
			
			System.out.println(max);
		}
	}

}
