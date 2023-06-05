import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1414_불우이웃돕기 {

	//전체길이 구하고 최소 거리만 빼주면?
	
	static int N;
	static int total;
	static PriorityQueue<Edge> edgeList;
	static int parents[];
	static int cnt;
	static class Edge implements Comparable<Edge> {
        int from, to, weight;
 
        public Edge(int from, int to, int weight) {
            super();
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
 
        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
        
    }
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		total=0;
		
		edgeList=new PriorityQueue<>();
		parents=new int[N];
		for (int i = 0; i < N; i++) {
			//부모 초기화
			parents[i]=i;
			String str = br.readLine();	
			for (int j = 0; j < N; j++) {
				int length=ChangeToLength(str.charAt(j));
				if(i==j) {//자기 자신을 잇는 건 간선 추가 X
					total += length;
					continue;
				}
				//간선 없음
				if(length==0) continue;
				
				//간선 있음
				edgeList.offer(new Edge(i, j, length));
				total += length;
			}
		}
		
		//최소 스패닝 계산
		int mst=0;
		cnt=0;
		while(!edgeList.isEmpty()) {
			Edge cur=edgeList.poll();
			if(union(cur.from, cur.to)) {
				mst+=cur.weight;
				cnt++;
			}
			if(cnt==N-1) break;
		}
		
		if(cnt==N-1) {
			System.out.println(total-mst);
		}else {
			System.out.println(-1);
		}
	}

	private static int ChangeToLength(char c) {
		if(c=='0')
			return 0;
		
		if(Character.isLowerCase(c)) {//소문자
			return 1+(c-'a');
		}else {//대문자
			return 27+(c-'A');
		}
	}

	private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        
        if(aRoot == bRoot) return false;
        parents[aRoot] = bRoot;
        return true;
    }
 
    private static int find(int a) {
        if(a == parents[a]) return a;
        return parents[a] = find(parents[a]);
    }
}
