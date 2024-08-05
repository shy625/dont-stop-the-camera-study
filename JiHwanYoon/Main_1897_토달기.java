

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1897_토달기 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int d = Integer.parseInt(st.nextToken());
		String s = st.nextToken();
		// 각 인덱스마다 인덱스만큼의 길이를 가지는 단어를 저장하는 ArrayList
        ArrayList<ArrayList<String>> wordsForLen = new ArrayList<>();
        for (int i = 0; i <= 80; i++) {
        	wordsForLen.add(new ArrayList<>());
        }
        for (int i = 0; i < d; i++) {
            String word = br.readLine();
            wordsForLen.get(word.length()).add(word);
        }
        // 너비 우선 탐색을 활용
        Queue<String> q = new LinkedList<>();
        q.add(s);
        String answer = s;
        // 방문 배열을 HashSet을 활용해 구현
        ArrayList<HashSet<String>> visited = new ArrayList<>();
        for (int i = 0; i <= 80; i++) {
        	visited.add(new HashSet<>());
        }
        while (!q.isEmpty()) {
            String cur = q.poll();
            for (String next : wordsForLen.get(cur.length()+1)) {
                if (visited.get(next.length()).contains(next)) continue;
                // cur과 next가 한 글자 차이인지 확인
                int diff = 0;
                int i = 0;
                int j = 0;
                while (i < cur.length() && j < next.length() && diff <= 1) {
                    if (cur.charAt(i) != next.charAt(j)) diff++;
                    else i++;
                    j++;
                }
                if (diff <= 1) {
                	answer = next;
                    q.add(next);
                    visited.get(next.length()).add(next);
                }
            }
        }
        System.out.println(answer);
	}

}
