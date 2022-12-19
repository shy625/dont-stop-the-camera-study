import java.util.*;

class kakao_2019_lv2_튜플 {
    // 1. "}"를 기준으로 split 해준다. (가장 마지막의 }는 먼저 없애고 시작한다.)
    // 2. 가장 앞의 2개의 문자를 없앤다. (이렇게 하면 숫자만 남는다.)
    // 3. 배열을 문자열의 길이로 sort 해준다.
    // 4. 문자열을 숫자로 모두 바꿔서 처음 만나는 숫자만 List에 넣는다. (HashSet 사용)
    // 5. List의 순서대로 answer에 넣어준다.
    // 6. 좀 멍청하게 푼듯..
    public int[] solution(String s) {
        Set<Integer> set = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        
        // 1번 과정
        s = s.substring(0, s.length()-1);
        String [] arr = s.split("}");
        
        int length = arr.length;
        int [] answer = new int[length];
        
        // 2번 과정
        for(int i=0;i<length;i++) {
            String tmp = arr[i];
            tmp = tmp.substring(2, tmp.length());
            arr[i] = tmp;
        }
        
        Arrays.sort(arr, (String s1, String s2) -> s1.length() - s2.length());
        
        for(int i=0;i<length;i++) {
            String str = arr[i];
            String [] arr2 = str.split(",");
            for(int j=0;j<arr2.length;j++) {
                String tmp = arr2[j];
                int num = Integer.parseInt(tmp);
                if(!set.contains(num)) list.add(num);
                set.add(num);
            }
        }
        
        int idx = 0;
        for(int n : list) {
            answer[idx++] = n;
        }
        
        return answer;
    }
}
