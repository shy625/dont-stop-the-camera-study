import java.util.*;

class Solution {
    
    private Map<String, String> userMap = new HashMap<>();
    
    public String[] solution(String[] records) {
        List<String[]> logList = new ArrayList<>();
        
        for (String record : records) {
            String[] split = record.split(" ");
            if (split[0].equals("Enter")) {
                logList.add(new String[] { split[0], split[1] });
                userMap.put(split[1], split[2]);
            } else if (split[0].equals("Leave")) {
                logList.add(new String[] { split[0], split[1] });
            } else {
                userMap.put(split[1], split[2]);
            }
        }
        
        int n = logList.size();
        String[] result = new String[n];
        for (int i = 0; i < n; i++) {
            String[] log = logList.get(i);
            result[i] = makeMsg(log[1], log[0]);
        }
        
        return result;
    }
    
    private String makeMsg(String id, String state) {
        String result = userMap.get(id);
        if (state.equals("Enter")) {
            result += "님이 들어왔습니다.";
        } else {
            result += "님이 나갔습니다.";
        }
        return result;
    }
}

// 풀이 1
// 유저 최종 닉네임 정보는 id - nickname 구조의 Map으로 저장
// 주어진 기록을 읽어가면서 입, 퇴장 정보는 별도의 List에 저장하고 입장 및 이름 변경 시 Map에 업데이트
// 모든 기록 확인이 끝나면 입, 퇴장 List에 따라 결과 메시지를 만들어서 반환

// 다른 풀이 1
// 전체적인 로직은 동일
// 유저 정보와 입, 퇴장 로그를 클래스화
// 입, 퇴장, 이름 변경 기록 확인 시 문자열 전체 비교가 아닌 첫 단어만 비교 → 조금 더 효율적
// 입, 퇴장 기록을 메시지 배열로 변환 시 람다 사용
// 메시지 포맷 별도의 상수로 저장하여 사용