import java.io.*;
import java.util.*;

class kakao_2019_lv2_오픈채팅방 {
    // 1. hashmap 2개 사용 (유저, 로그)
    // 2. 유저 맵은 <아이디, 닉네임>이 들어간다.
    // 3. 로그 리스트는 list를 사용해서 String[0]은 커맨드, String[1]은 아이디를 넣는다.
    // 4-1. record를 하나씩 검사해서 enter면 유저 맵에 무조건 저장
    // 4-2. 로그 리스트에는 Enter, 아이디를 넣는다.
    // 5-1. leave면 로그 리스트에만 Enter, 아이디를 넣는다.
    // 6. change면 유저 맵에 새로 저장한다.
    // 7. 모든 과정이 끝나고 로그 리스트에서 하나씩 꺼내서 result를 구성한다.
    
    public String[] solution(String[] record) {
        Map<String, String> userMap = new HashMap<>();
        List<String[]> logList = new ArrayList<>();
        
        for(int i=0;i<record.length;i++) {
            String [] str = record[i].split(" ");
            String command = str[0];
            String id = str[1];
            
            if(command.equals("Enter")) {
                String nickname = str[2];
                userMap.put(id, nickname);
                logList.add(new String[] {command, id});
            } else if(command.equals("Leave")) {
                logList.add(new String[] {command, id});
            } else if(command.equals("Change")) {
                String nickname = str[2];
                userMap.put(id, nickname);
            }
        }
        
        int size = logList.size();
        String [] result = new String[size];
        int idx = 0;
        
        for(String [] str : logList) {
            StringBuilder sb = new StringBuilder();
            
            String command = str[0];
            String id = str[1];
            String nickname = userMap.get(id);
            
            sb.append(nickname).append("님이 ");
            
            if(command.equals("Enter")) {
                sb.append("들어왔습니다.");
            } else if(command.equals("Leave")) {
                sb.append("나갔습니다.");
            }
            result[idx++] = sb.toString();
        }

        return result;
    }
}
