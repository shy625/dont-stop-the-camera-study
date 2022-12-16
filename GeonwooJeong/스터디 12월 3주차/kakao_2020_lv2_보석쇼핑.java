import java.io.*;
import java.util.*;

class kakao_2020_lv2_보석쇼핑 {
    // 1. HashSet에 gems 배열을 넣어서 보석의 종류와 길이를 알아낸다.
    // 2. 먼저 right를 +1씩 늘려가면서 map에 한 번도 찾지 않은 보석들을 1로 넣어준다.
    // 3. 위의 과정 중, 이미 있는 보석은 +1해서 다시 넣어준다.
    // 4. 모든 보석을 찾았으면, 왼쪽부터 left를 +1씩 늘려가면서 map에 저장된 보석들을 -1해서 다시 넣는다.
    // 5. 위의 과정에서, 보석의 value가 0이 되면 해당 보석을 제거하고, 2번 과정부터 다시 진행한다.
    // 6. 2~5의 과정을 진행하면서 가장 짧았던 거리의 left와 right 값을 head와 tail에 저장해놓는다.
    // 7. 2~6의 과정을 반복하면서 배열의 끝에 도달하면 마무리한다.
    // 8. answer에 head와 tail 값을 넣어준다.
    
    public int[] solution(String[] gems) {
        int [] answer = new int[2];
        
        Set<String> set = new HashSet<>();
        Map<String, Integer> map = new HashMap<>();
        
        // 1번 과정
        for(String str : gems) {
            set.add(str);
        }
        
        int gemL = set.size();
        int arrL = gems.length;
        
        int dist = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;
        int head = 0;
        int tail = 0;
        
        while(true) {
            // 4번 과정
            if(map.size() == gemL) {
                String gem = gems[left];
                map.put(gem, map.get(gem) - 1);
                
                // 5번 과정
                if(map.get(gem) == 0) {
                    map.remove(gem);
                }
                left++;
            // 7번 과정
            } else if(right == arrL) {
                break;
            // 2~3번 과정
            } else {
                String gem = gems[right];
                int value = 0;
                if(map.containsKey(gem)) value = map.get(gem);
                
                map.put(gem, value+1);
                right++;
            }
            
            // 6번 과정
            if(map.size() == gemL) {
                if(right - left < dist) {
                    dist = right - left;
                    head = left + 1;
                    tail = right;
                }
            }
        }
        
        answer[0] = head;
        answer[1] = tail;
        
        return answer;
    }
}
