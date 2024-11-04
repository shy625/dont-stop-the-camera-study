import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_31423_신촌통폐합계획 {
	static class Node {
		// 대학교 이름
		String name;
		// 연결된 대학교 중 바로 다음 대학교 노드번호
		int next;
		// 연결된 대학교 중 가장 마지막 대학교 노드 번호
		int last;
		
		public Node(String name, int next, int last) {
			super();
			this.name = name;
			this.next = next;
			this.last = last;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		Node [] arr = new Node[N];
		StringBuilder sb = new StringBuilder();
		
		// i번째 대학교 정보를 초기화한다. next와 last는 자기 자신으로 초기화
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			arr[i] = new Node(str, i, i);
		}
		
		// 가장 앞에 오는 대학교의 idx를 저장할 변수
		int idx = -1;
		
		for (int i = 0; i < N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			// 앞의 대학교 번호
			int a = Integer.parseInt(st.nextToken())-1;
			// 뒤의 대학교 번호
			int b = Integer.parseInt(st.nextToken())-1;
			
			// a 대학교의 가장 끝에 오는 대학교를 찾는다.
			// a 대학교 맨 뒤에 b 대학교를 붙여야 하므로, a 대학교 last의 next는 b가 된다.
			arr[arr[a].last].next = b;
			// b 대학교도 다른 대학교와 합쳐졌을 수 있으므로,
			// a 대학교의 마지막을 b 대학교의 마지막으로 갱신한다.
			arr[a].last = arr[b].last;
			
			// 가장 앞의 대학교를 찾기 위한 조건문
			if(i == N-2) idx = a;
		}
		
		// idx를 계속 변경해나가면서 sb에 쌓아나간다.
		for (int i = 0; i < N; i++) {
			sb.append(arr[idx].name);
			idx = arr[idx].next;
		}
		
		System.out.println(sb.toString());
		
	}

}
