import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_20529_심리적거리 {

	static  int N,T;
	static int min;
	static ArrayList<String> list;
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		T=Integer.parseInt(br.readLine());
		for (int t = 0; t <T; t++) {
			N=Integer.parseInt(br.readLine());
			StringTokenizer st=new StringTokenizer(br.readLine());
			
			Map<String, Integer> map=new HashMap<String, Integer>();
			boolean isEnd=false;
			for (int i = 0; i < N; i++) {
				String temp=st.nextToken();
				if(!map.containsKey(temp)) {
					map.put(temp, 1);
				}else {
					int tmp=map.get(temp)+1;
					map.put(temp, tmp);
					if(tmp==3) {
						isEnd=true;
						break;
					}
				}
			}
			if(isEnd) {
				System.out.println(0);
				continue;
			}
			
			list=new ArrayList<>();
			for (Map.Entry<String, Integer> cur : map.entrySet()) {
				String mbti=cur.getKey();
				int cnt=cur.getValue();
				
				for (int i = 0; i < cnt; i++) {
					list.add(mbti);
				}
			}
			
			N=list.size();
			
			min=Integer.MAX_VALUE;
			nCr(0,0,new int[3]);
			System.out.println(min);
		}
	}
	private static void nCr(int cnt, int start,int[] choice) {
		if(cnt==3) {
			String a=list.get(choice[0]);
			String b=list.get(choice[1]);
			String c=list.get(choice[2]);
			
			int ab=getDist(a,b);
			int bc=getDist(b, c);
			int ac=getDist(a, c);
			min=Integer.min(min, ab+bc+ac);
			return;
		}
		
		for (int i = start; i < N; i++) {
			choice[cnt]=i;
			nCr(cnt+1,i+1,choice);
		}
	}
	private static int getDist(String a, String b) {
		int dist=0;
		for (int i = 0; i < 4; i++) {
			if(a.charAt(i)!=b.charAt(i)) dist++;
		}
		return dist;
	}

}
