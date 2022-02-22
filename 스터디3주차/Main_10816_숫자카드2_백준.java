import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_10816_숫자카드2_백준 {

	static int N,M;
	static int[] card;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		StringTokenizer st=new StringTokenizer(br.readLine());
		card=new int[N];
		for (int i = 0; i < N; i++) {
			card[i]=Integer.parseInt(st.nextToken());
		}
		Arrays.sort(card);//정렬
		
		M=Integer.parseInt(br.readLine());
		Map<Integer, Integer> count=new HashMap<>();
		st=new StringTokenizer(br.readLine());
		StringBuilder sb=new StringBuilder();
		for (int i = 0; i < M; i++) {
			int findNum=Integer.parseInt(st.nextToken());
			if(!count.containsKey(findNum)) {//미리 찾아놓은게 없다면
				int temp=dinarySearch(N,findNum);//배열 사이즈,찾는 숫자
				count.put(findNum, temp);//hashMap에 요소 넣기
			}
			sb.append(count.get(findNum)).append(" ");
		}
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	private static int dinarySearch(int n, int num) {//이분탐색
		int first=0;
		int last=N-1;
		int mid=0;
		while(first<=last) {
			mid=(first+last)/2;//중간값
			
			if(num==card[mid]) {//일치값 찾음
				return findSame(mid);
			}
			else {
				if(num<card[mid]) {
					last=mid-1;
				}else {//찾는 값이 중간값보다 크면
					first=mid+1;
				}
			}
		}
		return 0;//찾는 값 없음
	}
	private static int findSame(int mid) {//같은 개 여러개 있다면 갯수 세기
		int n=card[mid];
		int cnt=1;//가운데 값은 찾았다
		int i=mid-1;
		while(i>=0&&card[i--]==n) {
			cnt++;
		}
		
		i=mid+1;
		while(i<N&&card[i++]==n) {
			cnt++;
		}
		
		return cnt;
	}

}
