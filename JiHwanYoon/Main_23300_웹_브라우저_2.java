

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_23300_웹_브라우저_2 {
	// 방문한 웹 페이지 번호와 해당 웹 페이지를 연속해서 방문한 횟수를 저장하는 클래스
	static class Record {
		int n, cnt;
		public Record(int n, int cnt) {
			this.n = n;
			this.cnt = cnt;
		}
	}
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Integer.parseInt(st.nextToken()); // N은 입력받으나 사용하지 않음
		int Q = Integer.parseInt(st.nextToken());
		Stack<Record> backward = new Stack<>(); // 뒤로 가기 기록을 저장하기 위한 스택
		Stack<Record> forward = new Stack<>(); // 앞으로 가기 기록을 저장하기 위한 스택
		Record cur = new Record(0, 0); // 현재 방문 중인 웹 페이지
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			char command = st.nextToken().charAt(0);
			// 뒤로 가기 실행 시
			if (command == 'B') {
				// 뒤로 가기 공간에 기록이 없으면 무시
				if (backward.isEmpty()) continue;
				// 현재 보고 있는 웹 페이지를 앞으로 가기 공간에 저장
				if (!forward.isEmpty() && cur.n == forward.peek().n) {
					forward.peek().cnt++;
				} else {
					forward.push(new Record(cur.n, 1));
				}
				// 뒤로 가기 공간에서 방문한지 가장 최근의 페이지에 접속
				Record r = backward.peek();
				cur = new Record(r.n, 0);
				// 해당 페이지는 뒤로 가기 공간에서 삭제
				if (r.cnt == 1) {
					backward.pop();
				} else {
					r.cnt--;
				}
			// 앞으로 가기
			} else if (command == 'F') {
				// 앞으로 가기 공간에 기록이 없으면 무시
				if (forward.isEmpty()) continue;
				// 현재 보고 있는 웹 페이지를 뒤로 가기 공간에 저장
				if (!backward.isEmpty() && cur.n == backward.peek().n) {
					backward.peek().cnt++;
				} else {
					backward.push(new Record(cur.n, 1));
				}
				// 앞으로 가기 공간에서 방문한지 가장 최근의 페이지에 접속
				Record r = forward.peek();
				cur = new Record(r.n, 0);
				// 해당 페이지는 앞으로 가기 공간에서 삭제
				if (r.cnt == 1) {
					forward.pop();
				} else {
					r.cnt--;
				}
			// 웹 페이지 접속
			} else if (command == 'A') {
				// 앞으로 가기 공간에 저장된 페이지 모두 삭제
				forward.clear();
				// 현재 페이지를 뒤로 가기 공간에 추가
				if (cur.n != 0) {
					if (!backward.isEmpty() && cur.n == backward.peek().n) {
						backward.peek().cnt++;
					} else {
						backward.push(new Record(cur.n, 1));
					}
				}
				// 다음에 접속할 페이지가 현재 페이지로 갱신
				cur.n = Integer.parseInt(st.nextToken());
			// 압축
			} else if (command == 'C') {
				// 효율적인 풀이를 위해, 웹 페이지 방문 기록 저장 시 연속해서 방문한 횟수도 저장
				// 이를 통해 압축 시 각 기록의 연속해서 방문한 횟수만 1로 변경하여 효율적으로 풀이
				for (Record r : backward) {
					r.cnt = 1;
				}
			}
		}
		// 출력
		StringBuilder sb = new StringBuilder();
		sb.append(cur.n).append("\n");
		if (backward.isEmpty()) {
			sb.append("-1");
		} else {
			while (!backward.isEmpty()) {
				sb.append(backward.peek().n).append(" ");
				if (--backward.peek().cnt == 0) backward.pop();				
			}
			sb.setLength(sb.length()-1);
		}
		sb.append("\n");
		if (forward.isEmpty()) {
			sb.append("-1");
		} else {
			while (!forward.isEmpty()) {
				sb.append(forward.peek().n).append(" ");
				if (--forward.peek().cnt == 0) forward.pop();	
			}
			sb.setLength(sb.length()-1);
		}
		System.out.println(sb.toString());
	}
}
