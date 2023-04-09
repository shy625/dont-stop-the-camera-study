import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution_Lv2_파일명정렬 {

    // index 값도 저장하는거 고려
    class File implements Comparable<File> {

        String name;
        String head;
        int number;
        String tail;

        public File(String name, String head, int number, String tail) {
            this.name = name;
            this.head = head;
            this.number = number;
            this.tail = tail;
        }

        public int compareTo(File o) {
            if (this.head.equalsIgnoreCase(o.head)) {
                if (this.number == o.number) {
                    return this.tail.compareToIgnoreCase(o.tail);
                } else {
                    return this.number - o.number;
                }
            } else {
                return this.head.compareToIgnoreCase(o.head);
            }
        }
    }

    class Solution {

        public String[] solution(String[] files) {
            //fileList에 잘라서 넣기
            List<File> fileList = new ArrayList<>();
            for (int fileIndex = 0; fileIndex < files.length; fileIndex++) {
                String file = files[fileIndex];
                int numberIndex = 0;
                int tailIndex = 0;
                int typeIndex = 0;
                for (int nameIndex = 0; nameIndex < file.length(); nameIndex++) {
                    //해당위치가 숫자
                    if (numberIndex == 0 && Character.isDigit(file.charAt(nameIndex))) {
                        numberIndex = nameIndex;
                    }
                    //해당위치가 숫자가 아닐때
                    if (numberIndex != 0 && !Character.isDigit(file.charAt(nameIndex))) {
                        tailIndex = nameIndex;
                        break;
                    }
                }
                
                fileList.add(new File(file,
                        file.substring(0, numberIndex),
                        Integer.parseInt(file.substring(numberIndex, tailIndex)),
                        file.substring(tailIndex)));
            }
            Collections.sort(fileList);

            String[] answer = new String[fileList.size()];
            for (int index = 0; index < fileList.size(); index++) {
                answer[index] = fileList.get(index).name;
            }
            return answer;
        }
    }

}
