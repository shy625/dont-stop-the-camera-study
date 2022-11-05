import java.util.*;

class Solution {
    public int solution(String s) {        
        int minLength = s.length();
        for (int i = 1; i <= s.length() / 2; i++) {
            minLength = Math.min(minLength, compressString(s, i));
        }
        return minLength;
    }
    
    private int compressString(String str, int unit) {
        List<String> unitList = new ArrayList<>();
        for (int i = 0; i < str.length(); i += unit) {
            String subStr = "";
            if (i + unit < str.length()) {
                subStr = str.substring(i, i + unit);
            } else {
                subStr = str.substring(i);
            }
            unitList.add(subStr);
        }
        
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (int i = 0; i < unitList.size() - 1; i++) {
            String cur = unitList.get(i);
            String next = unitList.get(i + 1);
            if (cur.equals(next)) {
                count++;
            } else {
                String compStr = cur;
                if (count > 1) {
                    compStr = count + compStr;
                } 
                sb.append(compStr);
                count = 1;
            }
        }
        if (count > 1) {
            sb.append(count);
        }
        sb.append(unitList.get(unitList.size() - 1));
        
        return sb.length();
    }
}

// 풀이 1
// 자르는 단위를 1개부터 전체길이 / 2 까지 시도 -> 시도 결과 가장 작은 것을 반환
// 그 이상은 어차피 반복될 수 없기에 시도할 필요 X
// 문자열 압축은 주어진 단위개수로 문자열을 잘라서 리스트에 저장
// 리스트를 앞에서부터 읽어가면서 동일한지, 다른지 체크
// 동일하면 개수를 증가시키고 다음으로 진행
// 다르면 그때까지의 개수와 해당 단위 문자열로 압축한 결과를 StringBuilder에 추가
// 리스트의 마지막까지 확인이 완료되면 만들어진 StringBuilder의 길이 반환