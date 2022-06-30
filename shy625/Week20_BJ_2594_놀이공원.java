import java.io.*;
import java.util.*;

public class Week20_BJ_2594_놀이공원 {

    static class Ride implements Comparable<Ride> {
        int start;
        int end;

        Ride(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Ride o) {
            return Integer.compare(start, o.start);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());
        Ride[] rides = new Ride[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = stringToMinute(st.nextToken());
            int end = stringToMinute(st.nextToken());
            rides[i] = new Ride(start, end);
        }

        Arrays.sort(rides);

        int maxRestTime = rides[0].start - 610;
        int restStart = rides[0].end, restEnd = 1320;
        for (int i = 1; i < rides.length; i++) {
            if (restStart < rides[i].start) {
                int restTime = rides[i].start - restStart - 20;
                maxRestTime = Math.max(maxRestTime, restTime);
            }
            restStart = rides[i].end;
        }
        if (restStart < restEnd) {
            maxRestTime = Math.max(maxRestTime, restEnd - restStart - 10);
        }

        System.out.println(maxRestTime > 0 ? maxRestTime : 0);

        br.close();
    }

    private static int stringToMinute(String str) {
        int hour = Integer.parseInt(str.substring(0, 2));
        int minute = Integer.parseInt(str.substring(2));
        return hour * 60 + minute;
    }
}
