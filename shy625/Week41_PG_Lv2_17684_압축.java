import java.util.*;

class Solution {
    public int[] solution(String msg) {
        Map<String, Integer> dictionary = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            String word = String.valueOf((char) ('A' + i));
            dictionary.put(word, i + 1);
        }
        
        List<Integer> idxList = new ArrayList<>();
        int startIdx = 0, endIdx = 0;
        while (startIdx < msg.length()) {
            for (int i = startIdx + 1; i <= msg.length(); i++) {
                String subStr = msg.substring(startIdx, i);
                if (!dictionary.containsKey(subStr)) {
                    break;
                }
                endIdx = i;
            }
            String maxWord = msg.substring(startIdx, endIdx);
            idxList.add(dictionary.get(maxWord));
            if (endIdx < msg.length()) {
                String newWord = maxWord + msg.charAt(endIdx);
                dictionary.put(newWord, dictionary.size() + 1);
            }
            startIdx = endIdx;
        }
        
        int[] answer = new int[idxList.size()];
        for (int i = 0; i < idxList.size(); i++) {
            answer[i] = idxList.get(i);
        }
        return answer;
    }
}

// 풀이 1
// 문제에 설명된 압축 로직대로 코드 구현
// 사전은 Map<String, Integer> 로 구현 → 단어의 사전 포함 여부 확인 시 O(1)에 가능
// List로 사전 구현 시 단어의 사전 포함 여부 확인 시 O(n) 소요