class kakao_2020_lv1_키패드누르기 {
    // leftX, rightX : 가운데 키패드가 아니면 1, 가운데면 0
    // leftY, rightY : 맨 위를 기준으로 어느 위치에 있는지
    public String solution(int[] numbers, String hand) {
        StringBuilder sb = new StringBuilder();
        int leftX = 1;
        int leftY = 3;
        int rightX = 1;
        int rightY = 3;
        
        for(int i=0;i<numbers.length;i++) {
            int n = numbers[i];
            if(n == 1 || n == 4 || n == 7) {
                sb.append("L");
                leftX = 1;
                leftY = n/3;
            } else if(n == 3 || n == 6 || n == 9) {
                sb.append("R");
                rightX = 1;
                rightY = n/3-1;
            } else {
                int Y = n/3;
                if(n == 0) Y = 3;
                int left = Math.abs(leftY - Y)+leftX;
                int right = Math.abs(rightY - Y)+rightX;
                if(left < right) {
                    sb.append("L");
                    leftX = 0;
                    leftY = Y;
                } else if(left > right) {
                    sb.append("R");
                    rightX = 0;
                    rightY = Y;
                } else if(left == right) {
                    if(hand.equals("left")) {
                        sb.append("L");
                        leftX = 0;
                        leftY = Y;
                    } else {
                        sb.append("R");
                        rightX = 0;
                        rightY = Y;
                    }
                }
            }
        }
        
        return sb.toString();
    }
}
