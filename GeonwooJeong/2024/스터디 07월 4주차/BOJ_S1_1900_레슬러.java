import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_S1_1900_레슬러 {
	static class Person implements Comparable<Person> {
		int idx;
		int power;
		int ring;
		
		public Person(int idx, int power, int ring) {
			this.idx = idx;
			this.power = power;
			this.ring = ring;
		}

		@Override
		public int compareTo(Person o) {
			return Integer.compare(o.power + o.ring*this.power, this.power + this.ring*o.power);
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		List<Person> list = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int power = Integer.parseInt(st.nextToken());
			int ring = Integer.parseInt(st.nextToken());
			
			list.add(new Person(i+1, power, ring));
		}
		
		Collections.sort(list);
		
		for(Person p : list) {
			System.out.println(p.idx);
		}
		
	}

}
