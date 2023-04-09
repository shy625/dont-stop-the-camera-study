import java.util.*;

class Solution {
    public String[] solution(String[] orders, int[] course) {
        Map<String, Integer> courseMap = new HashMap<>();
        
        for (String order : orders) {
            makeCourse(courseMap, course, order, 0, "");
        }
        
        List<String> resultList = new ArrayList<>();
        List<String> courseList = new ArrayList<>();
        for (int dishCount : course) {
            int maxCount = 0;
            for (Map.Entry<String, Integer> e : courseMap.entrySet()) {
                if (e.getValue() > 1) {
                    if (dishCount == e.getKey().length()) {
                        if (e.getValue() > maxCount) {
                            maxCount = e.getValue();
                            courseList.clear();
                            courseList.add(e.getKey());
                        } else if (e.getValue() == maxCount) {
                            courseList.add(e.getKey());
                        }
                    }
                }
            }
            resultList.addAll(courseList);
            courseList.clear();
        }
        
        Collections.sort(resultList);
        
        return resultList.toArray(new String[0]);
    }
    
    private void makeCourse(Map<String, Integer> courseMap, int[] dishCounts, String order, int idx, String course) {
        if (idx == order.length()) {
            for (int dishCount : dishCounts) {
                if (course.length() == dishCount) {
                    char[] courseCharArr = course.toCharArray();
                    Arrays.sort(courseCharArr);
                    course = String.valueOf(courseCharArr);
                    Integer count = courseMap.getOrDefault(course, 0);
                    courseMap.put(course, count + 1);
                    break;
                }
            }
            return;
        }
        makeCourse(courseMap, dishCounts, order, idx + 1, course);
        makeCourse(courseMap, dishCounts, order, idx + 1, course + order.charAt(idx));
    }
}

// 풀이 1
// 각 주문내용에 대해 부분집합을 사용하여 주문에서 나올 수 있는 코스 구함
// 구한 코스는 Map에 코스를 key로 하여 등장한 횟수를 값에 저장
// 단, 코스는 String 타입으로 동일한 구성의 코스라도 순서에 따라 Map에 다르게 저장될 수 있기 때문에 코스에 들어간 메뉴를 정렬하여 Map에 저장
// 모든 주문에 대해 부분집합을 돌려서 Map이 완성되었으면
// Map을 돌면서 주어진 코스의 메뉴 개수 배열을 돌면서 각 메뉴 개수 당 가장 많이 등장한 코스를 찾아서 최종 결과 리스트에 추가
// 단, 등장 횟수가 가장 많은 코스가 여러 개일 경우 다 추가될 수 있기 때문에 바로 추가하지 않고 중간 리스트를 두어 저장 후 최종 결과에 추가