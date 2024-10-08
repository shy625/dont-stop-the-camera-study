

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_23796_2147483648_게임 {
	static int N;
	static long[][] arr;
	static ArrayList<Queue<Long>> ques;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = 8;
		arr = new long[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Long.parseLong(st.nextToken());
			}
		}
		// 각 명령어에 대해 큐를 통한 시뮬레이션을 진행한다.
		// U, D의 경우 각 열에 있는 숫자들을, L, R의 경우 각 행에 있는 숫자들을 큐에 넣는다.
		// 그리고 큐에서 숫자들을 하나씩 꺼내면서 합치는 게 가능한 경우에 한해 합치는 작업을 모든 숫자들을 방문할 때까지 반복한다.
		// 마지막으로 위 작업을 통해 남은 숫자들을 방향에 맞게 배열에 채운다.
		ques = new ArrayList<>();
		for (int i = 0; i < 8; i++) {
			ques.add(new LinkedList<>());
		}
		char command = br.readLine().charAt(0);
		long[][] result = new long[N][N];
		if (command == 'U') {
			for (int j = 0; j < N; j++) {
				for (int i = 0; i < N; i++) {
					if (arr[i][j] != 0) ques.get(j).offer(arr[i][j]);
				}
			}
			simulate();
			for (int j = 0; j < N; j++) {
				for (int i = 0; !ques.get(j).isEmpty(); i++) {
					result[i][j] = ques.get(j).poll();
				}
			}
		} else if (command == 'D') {
			for (int j = 0; j < N; j++) {
				for (int i = N-1; i >= 0; i--) {
					if (arr[i][j] != 0) ques.get(j).offer(arr[i][j]);
				}
			}
			simulate();
			for (int j = 0; j < N; j++) {
				for (int i = N-1; !ques.get(j).isEmpty(); i--) {
					result[i][j] = ques.get(j).poll();
				}
			}
		} else if (command == 'L') {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (arr[i][j] != 0) ques.get(i).offer(arr[i][j]);
				}
			}
			simulate();
			for (int i = 0; i < N; i++) {
				for (int j = 0; !ques.get(i).isEmpty(); j++) {
					result[i][j] = ques.get(i).poll();
				}
			}
		} else if (command == 'R') {
			for (int i = 0; i < N; i++) {
				for (int j = N-1; j >= 0; j--) {
					if (arr[i][j] != 0) ques.get(i).offer(arr[i][j]);
				}
			}
			simulate();
			for (int i = 0; i < N; i++) {
				for (int j = N-1; !ques.get(i).isEmpty(); j--) {
					result[i][j] = ques.get(i).poll();
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(result[i][j]).append(" ");
			}
			sb.setLength(sb.length()-1);
			sb.append("\n");
		}
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	private static void simulate() {
		for (int i = 0; i < N; i++) {
			Queue<Long> q = ques.get(i);
			int qLen = q.size();
			while (qLen > 0) {
				long n = q.poll();
				qLen--;
				if (qLen > 0 && q.peek() == n) {
					qLen--;
					q.poll();
					q.offer(n*2);
				} else {
					q.offer(n);
				}
			}
		}
	}

}
