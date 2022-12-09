class kakao_2018_lv2_방금그곡 {
    // 1. #이 붙은 음은 소문자로 치환해준다.
    // 2. 문자열을 적당히 나누어 time을 계산한다.
    // 3. time만큼 악보를 재설정한다.
    // 4. 악보와 멜로디를 비교하며 max 값을 갱신하고, 갱신될 때 마다 answer를 바꿔준다.
    public String solution(String m, String[] musicinfos) {
        String answer = "(None)";
        int max = -1;
        // 1번 과정(멜로디)
        while(m.contains("#")) {
            m = m.replace("A#", "a");
            m = m.replace("C#", "c");
            m = m.replace("D#", "d");
            m = m.replace("F#", "f");
            m = m.replace("G#", "g");
            m = m.replace("E#", "e");
        }
        
        for(int i=0;i<musicinfos.length;i++) {
            String [] arr = musicinfos[i].split(",");
            
            // 1번 과정(악보)
            arr[3] = arr[3].replace("A#", "a");
            arr[3] = arr[3].replace("C#", "c");
            arr[3] = arr[3].replace("D#", "d");
            arr[3] = arr[3].replace("F#", "f");
            arr[3] = arr[3].replace("G#", "g");
            arr[3] = arr[3].replace("E#", "e");
            
            // 2번 과정     
            String [] start = arr[0].split(":");
            String [] end = arr[1].split(":");
            
            int time = Integer.parseInt(end[1]) - Integer.parseInt(start[1]) + ((Integer.parseInt(end[0]) - Integer.parseInt(start[0])) * 60);
            
            // 3번 과정
            int cnt = 0;
            String str = "";
            while(cnt < time) {
                str += arr[3].charAt(cnt++ % arr[3].length());
            }
            
            // 4번 과정
            if(str.contains(m)) {
                if(max < str.length()) {
                    max = str.length();
                    answer = arr[2];
                }
            }
        }
        
        return answer;
    }
}
