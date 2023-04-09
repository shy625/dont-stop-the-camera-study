import java.util.*;

class kakao_2022_lv2_주차요금계산 {
    // 1. 시간은 :을 기점으로 시간, 분으로 나누어서 최종적으로 분으로 계산한다.
    // 2. 입차일 땐, 차량번호를 Key, 시간을 Value로 해서 TreeMap에 저장한다.
    // (Why TreeMap? 차량 번호가 작은 자동차부터 출력)
    // 3. 출차일 땐, 차량번호로 시간을 가져와서 총 몇 분동안 주차했는지 계산한다.
    // 4. 주의. 출차 내역이 없으면 23:59에 출차한 것으로 계산한다.
    // map에서 carNum 삭제.
    // 5. 계산한 시간으로 주차 요금을 낸다.
    // 6. 계산식 : ((시간-기본시간)/단위시간) * 단위요금 (버림이 아닌 올림해야함!!!!)
    
    public int[] solution(int[] fees, String[] records) {
        TreeMap<String, Integer> map = new TreeMap<>();
        TreeMap<String, Integer> list = new TreeMap<>();
        
        for(String str : records) {
            String [] arr = str.split(" ");
            String [] times = arr[0].split(":");
            int hour = Integer.parseInt(times[0]);
            int minute = Integer.parseInt(times[1]);
            int time = hour*60 + minute;
            String carNum = arr[1];
            
            if(arr[2].equals("IN")) {
                map.put(carNum, time);
            } else {
                int past = map.get(carNum);
                int cal = time-past;
                
                if(list.containsKey(carNum)) {
                    int pastTime = list.get(carNum);
                    list.put(carNum, pastTime+cal);
                } else {
                    list.put(carNum, cal);
                }
                
                map.remove(carNum);
            }
        }
        
        int size = map.size();
        
        for(int i=0;i<size;i++) {
            int time = 23*60 + 59;
            String carNum = map.firstKey();
            
            int past = map.get(carNum);
            int cal = time-past;
                
            if(list.containsKey(carNum)) {
                int pastTime = list.get(carNum);
                list.put(carNum, pastTime+cal);
            } else {
                list.put(carNum, cal);
            }  
            
            map.remove(carNum);
        }
        
        size = list.size();
        int [] answer = new int[size];
        
        for(int i=0;i<size;i++) {
            String carNum = list.firstKey();
            double time = (double)list.get(carNum);
            int res = 0;
            
            if(time <= fees[0]) {
                res = fees[1];
            } else {
                res = (int) (fees[1] + (Math.ceil((time-fees[0])/fees[2]))*fees[3]);
            }
            
            answer[i] = res;
            list.remove(carNum);
        }
        
        return answer;
    }
}
