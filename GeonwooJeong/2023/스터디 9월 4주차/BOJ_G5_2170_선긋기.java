import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Line implements Comparable<Line> {
	int start;
	int end;
	
	public Line(int start, int end) {
		this.start = start;
		this.end = end;
	}

	@Override
	public int compareTo(Line o) {
		return Integer.compare(this.start, o.start);
	}
	
}

public class BOJ_G5_2170_선긋기 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		// 선들을 저장할 배열
		Line [] arr = new Line[N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			arr[i] = new Line(a, b);
		}
		
		// 배열을 정렬해준다. 정렬 기준은 시작점이 된다.
		Arrays.sort(arr);
		
		// 그려진 선의 총 길이를 저장할 변수
		int ans = 0;
		// 초기 시작점은 첫번째 선의 시작점이다.
		int start = arr[0].start;
		// 초기 끝점은 첫번째 선의 끝점이다.
		int end = arr[0].end;
		
		for(int i = 1; i < N; i++) {
			Line now = arr[i];
			
			// 이전 선의 끝점보다 현재 선의 시작점이 크다면, 두 선이 이어지지 않고 끊긴 경우이다.
			// 이러한 경우에는 이전 선의 길이를 ans에 더해주고, 새롭게 선의 시작점과 끝점을 지정해준다.
			if(now.start > end) {
				ans += end-start;
				start = now.start;
				end = now.end;
			// 선이 이어진 상태에서 끝점만 늘어나는 경우에는 끝점 정보만 갱신해주면 된다.
			} else if(now.end > end) {
				end = now.end;
			}
		}
		
		// for문을 나와서도 지금까지 이어진 선의 길이를 계산해서 ans에 더해준다.
		ans += end-start;
		
		System.out.println(ans);
		
		
	}

}
