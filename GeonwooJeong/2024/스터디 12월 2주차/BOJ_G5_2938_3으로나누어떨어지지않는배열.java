import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G5_2938_3으로나누어떨어지지않는배열 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		// 3으로 나누었을 때 나머지가 각각 1, 2, 0인 숫자들을 q에 넣는다.
		Queue<Integer> q1 = new ArrayDeque<>();
		Queue<Integer> q2 = new ArrayDeque<>();
		Queue<Integer> q3 = new ArrayDeque<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(st.nextToken());
			
			if(n % 3 == 1) q1.add(n);
			else if(n % 3 == 2) q2.add(n);
			else q3.add(n);
		}
		
		// 만약 나머지가 1, 2인 그룹만 있는 경우 어떻게 배열해도 조건을 만족할 수 없다.
		if(q3.isEmpty() && !q1.isEmpty() && !q2.isEmpty()) {
			System.out.println(-1);
			System.exit(0);
		}
		
		// 3그룹부터 사용할건지를 여부를 정할 변수 (숫자를 배치할 때, q1+q2와 q3 중 크기가 더 큰 그룹을 먼저 사용함)
		boolean flag = q1.size() + q2.size() < q3.size() ? true : false;
		
		// 1, 2그룹 중 작은 그룹
		Queue<Integer> small = q1.size() <= q2.size() ? q1 : q2;
		// 1, 2그룹 중 큰 그룹
		Queue<Integer> big = q1.size() > q2.size() ? q1 : q2;
		
		// 1, 2그룹이나 3그룹 중 모두 사용한 그룹이 생길 때까지 일단 배치해본다.
		while(!q3.isEmpty() && !big.isEmpty()) {
			if(flag) {
				sb.append(q3.poll()+" ");
			} else {
				if(!small.isEmpty()) {
					sb.append(small.poll()+" ");
				} else {
					sb.append(big.poll()+" ");
				}
			}
			
			flag = !flag;
		}
		
		// 3그룹만 남았는데, 3그룹 크기가 2 이상이면 조건을 만족할 수 없다. (3그룹끼리 더하면 무조건 3의 배수)
		if(q3.size() > 2) {
			System.out.println(-1);
			System.exit(0);
		}
		
		// 3그룹이 1개만 남았으면 마저 배치해준다.
		if(!q3.isEmpty()) {
			while(!q3.isEmpty()) {
				sb.append(q3.poll()+" ");
			}
		// 큰 그룹이 남았으면 마저 배치해준다.
		} else if(!big.isEmpty()) {
			while(!big.isEmpty()) {
				sb.append(big.poll()+" ");
			}
			
			// 만약 3그룹의 수가 현저히 적어 작은 그룹의 숫자도 남았을 경우
			// 작은 그룹을 맨앞에 배치하고, 큰 그룹을 맨뒤에 배치하면 조건을 만족할 수 있다.
			if(!small.isEmpty()) {
				StringBuilder sb2 = new StringBuilder();
				while(!small.isEmpty()) {
					sb2.append(small.poll()+" ");
				}
				
				sb.setLength(sb.length()-1);
				System.out.println(sb2.toString()+sb.toString());
				System.exit(0);
			}
		} 
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
		
	}

}
