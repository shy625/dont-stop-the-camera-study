import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main_11725_트리의부모찾기 {
	static int N; 
	static int[] result;
	static int[][]map; 
	static int count;
	static HashMap<Integer, ArrayList<Integer>> numbers = new HashMap<Integer, ArrayList<Integer>>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st; 
		
		N = Integer.parseInt(br.readLine());
		result = new int[N+1];
		count = (N-1);
		for (int i = 0 ; i <= N ; i++) {
			ArrayList<Integer> e = new ArrayList<>();
			numbers.put(i, e);
		}
		for (int i = 0 ; i < N-1 ; i++) {
			st=  new StringTokenizer(br.readLine());
			int one = Integer.parseInt(st.nextToken());
			int two = Integer.parseInt(st.nextToken());
			numbers.get(one).add(two);
			numbers.get(two).add(one);
		} // 입력 처리 - 연결 양 끝을 ArrayList에 넣어 준다. 
		
		findP(1); // 가장 먼저 1을 넣어 준다. 
		
		for (int i = 2 ; i <= N ; i++) {
			System.out.println(result[i]);
		}
	}
	
	public static void findP(int index) {
		if (numbers.get(index).size() == 0) return; // 연결된 노드가 없으면 리턴한다. 
		while(numbers.get(index).size()>0) {
			int next = numbers.get(index).get(0); // 주어진 인덱스가 이어진 가장 첫번째 노드 = next 
			numbers.get(index).remove(0); 
			numbers.get(next).remove(numbers.get(next).indexOf(new Integer(index))); // 연결된 양 끝을 모두 지워준다. 
			result[next]= index; // next의 parent는 index
			findP(next);
		}
	}
	
}
