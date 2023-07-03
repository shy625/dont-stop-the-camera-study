import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_1997_박스포장 {
	static int n, w, b, h;
	static int [][] board;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		// 박스들을 넣을 판 배열
		board = new int[b][w];
		// 현재 판의 높이를 저장할 변수
		int nowH = 0;
		
		for (int i = 0; i < n; i++) {
			h = Integer.parseInt(br.readLine());
			// 현재 포장해야할 박스를 저장할 배열
			int [][] box = new int[h][w];
			
			// 박스 정보를 box 배열에 저장한다. 계산하기 쉽게 하기 위해 int로 저장한다.
			for (int r = 0; r < h; r++) {
				String str = br.readLine();
				for (int c = 0; c < w; c++) {
					char cc = str.charAt(c);
					if(cc == '.') {
						box[r][c] = 0;
					} else {
						box[r][c] = 1;
					}
				}
			}
			
			// 박스를 판에 새로 넣을 때의 총 높이를 계산할 변수
			int stair = 0;
			
			// 현재의 판에 박스를 최대한 내려갈 수 있을 때까지 내려보낸다.
			while(stair <= b-h && check(stair, box)) {
				stair++;
			}
			
			// 현재 박스를 판에 넣을 수 없는 경우 새로운 판으로 교체한다.
			if(stair == 0) {
				sb.append(nowH+" ");
				
				for (int r = 0; r < b; r++) {
					for (int c = 0; c < w; c++) {
						board[r][c] = 0;
					}
				}
				
				// 새로운 판에서 박스를 끝까지 내려보낼 수 있다.
				stair = b-h+1;
			}
			
			// 높이를 새로 계산해준다.
			nowH = b-(--stair);
			
			// 현재의 판에 박스를 끼워넣는 작업
			for (int r = stair; r < stair+h; r++) {
				for (int c = 0; c < w; c++) {
					board[r][c] += box[r - stair][c];
				}
			}
			
		}
		sb.append(nowH+" ");
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());

	}

	private static boolean check(int stair, int[][] box) {
		for (int i = stair; i < stair+h; i++) {
			for (int j = 0; j < w; j++) {
				// 새로 들어온 박스에 들어갈 자리에 원래 있던 판이 차있다면 false를 리턴한다.
				if(board[i][j] == 1 && box[i - stair][j] == 1) {
					return false;
				}
			}
		}
		
		return true;
	}

}
