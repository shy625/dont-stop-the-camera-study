import java.util.*;

class kakao_2022_lv1_성격유형검사하기 {
	
    public String solution(String[] survey, int[] choices) {
        HashMap<Character, Integer> mbti = new HashMap<>();
        mbti.put('R', 0);
        mbti.put('T', 0);
        mbti.put('C', 0);
        mbti.put('F', 0);
        mbti.put('J', 0);
        mbti.put('M', 0);
        mbti.put('A', 0);
        mbti.put('N', 0);
        
        StringBuilder sb = new StringBuilder();
        
        for(int i=0;i<survey.length;i++) {
            String str = survey[i];
            char c = 'a';
            if(choices[i]<=4) {
                c = str.charAt(0);
            } else {
                c = str.charAt(1);
            }
            int value = mbti.get(c);
            value += Math.abs(choices[i]-4);
            mbti.put(c, value);
        }
        
        if(mbti.get('R') >= mbti.get('T')) {
            sb.append('R');
        } else {
            sb.append('T');
        }
        
        if(mbti.get('C') >= mbti.get('F')) {
            sb.append('C');
        } else {
            sb.append('F');
        }
        
        if(mbti.get('J') >= mbti.get('M')) {
            sb.append('J');
        } else {
            sb.append('M');
        }
        
        if(mbti.get('A') >= mbti.get('N')) {
            sb.append('A');
        } else {
            sb.append('N');
        }
        
        return sb.toString();
    }
}
