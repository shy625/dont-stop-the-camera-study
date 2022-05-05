import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_11578_팀원모집 {
	
	static int N,M;
	static ArrayList<ArrayList<Integer>> stu;
	static int min,clear;
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st=new StringTokenizer(br.readLine()," ");
		
		clear=0;
		
		N=Integer.parseInt(st.nextToken().trim());
		for (int i = 0; i < N; i++) {
			clear=(clear<<1)+1;
		}
		M=Integer.parseInt(st.nextToken().trim());
		stu=new ArrayList<>();
		
		for (int i = 0; i < M; i++) {
			st=new StringTokenizer(br.readLine()," ");
			stu.add(new ArrayList<>());
			int canSolve=Integer.parseInt(st.nextToken().trim());
			for (int j = 0; j < canSolve; j++) {
				int temp=Integer.parseInt(st.nextToken().trim())-1;
				stu.get(i).add(temp);
			}
		}
		
		//학생 배열 초기화 끝
		min=Integer.MAX_VALUE;
		subset(0,0,0);
		
		//System.out.println(min==Integer.MAX_VALUE?-1:min);
		
		if(min==Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(min);
	}
	private static void subset(int cnt, int value,int num) {
		if(value==clear) {
			min=Math.min(min, num);
			return;
		}
		
		if(cnt==M) {
			return;
		}
		
		//cnt 학생 추가
		int temp=value;
		
		int size=stu.get(cnt).size();
		//문제 수 자리 체크
		for (int i = 0; i < size; i++) {
			int problem=stu.get(cnt).get(i);
			temp = temp | (1<<problem);
		}
		subset(cnt+1, temp, num+1);
		//cnt 학생 제거
		
		temp=value;
		subset(cnt+1, temp, num);
	}

}
