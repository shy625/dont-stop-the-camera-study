package algoStudy_10월_2주차;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution_lv1_신고_결과_받기 {

	public static void main(String[] args) {
		int[] result = solution(new String[] {"muzi", "frodo", "apeach", "neo"}, new String[] {"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"}, 2);
		System.out.println(Arrays.toString(result));
	}
	
	public static int[] solution(String[] id_list, String[] report, int k) {
        int N = id_list.length;
        // 각 유저별로 처리 결과 메일을 받은 횟수
        int[] answer = new int[N];
        // 각 유저의 아이디와 해당 유저가 받은 신고 횟수를 key와 value로 가지는 map
        Map<String, Integer> nums = new HashMap<>();
        // 각 유저의 아이디와 해당 유저가 신고한 유저의 아이디들의 집합을 key와 value로 가지는 map
        Map<String, Set<String>> map = new HashMap<>();
        // 초기화
        for (String s : id_list) {
            map.put(s, new HashSet<>());
            nums.put(s, 0);
        }
        // 신고 내용에 따라 map과 nums를 갱신한다.
        for (String s : report) {
            StringTokenizer st = new StringTokenizer(s);
            String caller = st.nextToken();
            String called = st.nextToken();
            if (!map.get(caller).contains(called)) {
                map.get(caller).add(called);
                nums.put(called, nums.get(called)+1);
            }
        }
        // 각 유저에 대해 처리 결과 메일을 받은 횟수를 구한다.
        for (int i = 0; i < N; i++) {
            Set<String> set = map.get(id_list[i]);
            // 신고한 유저가 k번 이상의 신고를 받아 정지를 받은 경우에만 처리 결과 메일을 받는다.
            for (String s : set) {
                if (nums.get(s) >= k) answer[i]++;
            }
        }
        return answer;
    }

}
