import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14569_시간표짜기 {

	static int N,M;
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		// 강의 후보
		N=Integer.parseInt(br.readLine());
		long [] Lecture=new long[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int length=Integer.parseInt(st.nextToken());
			long temp=0;
			for (int j = 0; j < length; j++) {
				temp+=(long)1<<Integer.parseInt(st.nextToken());	
			}
			Lecture[i]=temp;
		}
		
		//학생 목록
		M=Integer.parseInt(br.readLine());
		long [] Stu=new long[M];
		for (int i = 0; i < M; i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int length=Integer.parseInt(st.nextToken());
			long temp=0;
			for (int j = 0; j < length; j++) {
				temp+=(long)1<<Integer.parseInt(st.nextToken());	
			}
			Stu[i]=temp;
		}
		
		//비교
		StringBuilder sb=new StringBuilder();
		for (int i = 0; i < M; i++) {
			int lecture=0;//가능한 강의 수
			for (int j = 0; j < N; j++) {
				if(Lecture[j]== (Lecture[j]&Stu[i])) {
					lecture++;
				}
			}
			sb.append(lecture).append('\n');
		}
		System.out.println(sb.toString());
	}

}
