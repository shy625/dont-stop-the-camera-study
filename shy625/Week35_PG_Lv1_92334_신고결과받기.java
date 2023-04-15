import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        Map<String, Set<String>> reportedMap = new HashMap<>();
        
        for (String str : report) {
            String[] r = str.split(" ");
            Set<String> reportedSet = reportedMap.get(r[1]);
            if (reportedSet == null) {
                reportedSet = new HashSet<>();
            }
            reportedSet.add(r[0]);
            reportedMap.put(r[1], reportedSet);
        }
        
        int[] mailCounts = new int[id_list.length];
        for (Map.Entry<String, Set<String>> e : reportedMap.entrySet()) {
            Set<String> reportedSet = e.getValue();
            if (reportedSet.size() >= k) {
                for (String reporter : reportedSet) {
                    for (int i = 0; i < id_list.length; i++) {
                        if (id_list[i].equals(reporter)) {
                            mailCounts[i]++;
                        }
                    }
                }
            }
        }
        
        return mailCounts;
    }
}

// 풀이 1
// Map 사용, 유저id를 key로하여 자신을 신고한 유저 Set을 value로 저장
// Map에 저장된 유저마다 Set의 사이즈를 체크하여 사이즈가 k이상이면 Set에 있는 유저들이 받는 메일 개수 + 1