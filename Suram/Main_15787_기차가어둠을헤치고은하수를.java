import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main_15787_기차가어둠을헤치고은하수를 {
	
	static int N; 
	static int M; 
	static int[] trains;
	static HashSet<Integer> check = new HashSet<Integer>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		trains = new int[N+1];
		
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			int option = Integer.parseInt(st.nextToken());
			int train = Integer.parseInt(st.nextToken());
			int x = 0; 
			if(option == 1 || option == 2) {
				x = Integer.parseInt(st.nextToken());	
			}
			if(option == 1) {
				trains[train]= trains[train] | (1<<(x-1));
			}
			if (option == 2) {
				trains[train] = trains[train] & ~(1<<(x-1));
			}
			if (option == 3) {
				trains[train] = trains[train]<<1;
				trains[train] = trains[train] & ((1<<20)-1);
			}
			if (option== 4) {
				trains[train] = trains[train]>>1;
			}
		}
		
		for (int i = 1 ; i <= N ; i ++) {
			check.add(trains[i]);
		}
		System.out.println(check.size());
	}
}
