import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main_2143_두배열의합 {
	//배열을 누적합으로 저장
	static int T,n,m;
	static int [] A;
	static int [] B;
	static ArrayList<Integer> Alist,Blist;
	static long answer;
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T=Integer.parseInt(br.readLine());
		//a배열 입력
		n=Integer.parseInt(br.readLine());
		A=new int [n];
		st=new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			A[i]=Integer.parseInt(st.nextToken());
		}
		
		Alist=new ArrayList<>();
		for (int i = 0; i < n; i++) {
			int sum = 0;
			for (int j = i; j < n; j++) {
				sum += A[j];
				Alist.add(sum);
			}
		}
		//b배열 입력
		m=Integer.parseInt(br.readLine());
		B=new int [m];
		st=new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			B[i]=Integer.parseInt(st.nextToken());
		}
		
		Blist=new ArrayList<>();
		for (int i = 0; i < m; i++) {
			int sum = 0;
			for (int j = i; j < m; j++) {
				sum += B[j];
				Blist.add(sum);
			}
		}
		
		Collections.sort(Alist);
		Collections.sort(Blist);
		
		//////////계산해보자
		answer=0;//답 갯수 세기
		count();
		
		System.out.println(answer);
	}
	
	//투포인터 알고리즘 공부하자
	private static void count() {
		
		int pa = 0;
		int pb = Blist.size() - 1;

		while (pa < Alist.size() && pb >= 0) {

			long sum = Alist.get(pa) + Blist.get(pb);

			if (sum == T) {

				int a = Alist.get(pa);
				int b = Blist.get(pb);
				long aCnt = 0;
				long bCnt = 0;

				while (pa < Alist.size() && Alist.get(pa) == a) {
					aCnt++;
					pa++;
				}
				while (pb >= 0 && Blist.get(pb) == b) {
					bCnt++;
					pb--;
				}

				answer += aCnt * bCnt;
			}

			else if (sum < T)
				pa++;

			else if (sum > T)
				pb--;
		}
	}

}
