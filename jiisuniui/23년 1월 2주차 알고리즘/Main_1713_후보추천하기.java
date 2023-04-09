package BOJ;

import java.util.Set;
import java.util.Map;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

// 비어있는 사진틀이 없을 때
// 1. 추천받은 횟수가 가장 적은 학생 사진 삭제
// 2. 추천횟수 중복, 그 중 게시된지 오래된 사진 삭제
// -> 삭제된 경우, 추천횟수 0으로 초기화

public class Main_1713_후보추천하기 {

    static class Student {
        int number;
        int recommend;
        int time;

        public Student(int number) {
            this.number = number;
        }

        public Student(int number, int time) {
            this.number = number;
            this.time = time;
            this.recommend = 1;
        }

        public int getNumber() {
            return this.number;
        }

        public int getRecommend() {
            return this.recommend;
        }

        public int getTime() {
            return this.time;
        }

        public void addRecommend() {
            this.recommend++;
        }

        public void setRecommend(int recommend) {
            this.recommend = recommend;
        }

        public void setTime(int time) {
            this.time = time;
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner scann = new Scanner(System.in);
        int N = scann.nextInt(); // 사진 틀의 최대 갯수
        int R = scann.nextInt(); // 총 추천 횟수
        int[] recommend = new int[R];
        for (int i = 0; i < R; i++) {
            recommend[i] = scann.nextInt();
        }

        Map<Integer, Student> postStudents = new HashMap<>();
        for (int time = 0; time < R; time++) {
            if (postStudents.containsKey(recommend[time])) {
                postStudents.get(recommend[time]).addRecommend();
            }

            else if (postStudents.size() == N) {
                int removeWho = findLowRecommend(postStudents);
                postStudents.remove(removeWho);
                postStudents.put(recommend[time], new Student(recommend[time], time));
            } else {
                postStudents.put(recommend[time], new Student(recommend[time], time));
            }
        }

        List<Integer> keySet = new ArrayList<>(postStudents.keySet());
        // 키 값으로 오름차순 정렬
        Collections.sort(keySet);

        for (int i : keySet) {
            System.out.print(i + " ");
        }
        System.out.println();

        scann.close();

    }


    // 비어있는 사진틀이 없을 때
    // 1. 추천받은 횟수가 가장 적은 학생 사진 삭제
    // 2. 추천횟수 중복, 그 중 게시된지 오래된 사진 삭제
    // -> 삭제된 경우, 추천횟수 0으로 초기화

    public static int findLowRecommend(Map<Integer, Student> postStudent) {
        Student s = new Student(0);
        s.setRecommend(Integer.MAX_VALUE);
        s.setTime(Integer.MAX_VALUE);
        postStudent.put(0, s);
        int lowStudent = 0;

        for (int i : postStudent.keySet()) {
            if (postStudent.get(i).getRecommend() < postStudent.get(lowStudent).getRecommend()) {
                lowStudent = i;
            } else if (postStudent.get(i).getRecommend() == postStudent.get(lowStudent).getRecommend()) {
                if (postStudent.get(i).getTime() < postStudent.get(lowStudent).getTime()) {
                    lowStudent = i;
                }
            }
        }

        postStudent.remove(0);
        return lowStudent;
    }

}
