import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        int value = 0;

        // Scanner는 java.util 패키지에 있는 클래스로써 키보드로 부터 값을 입력받는다던지 할 때 유용하게 사용할 수 있는 클래스
        Scanner scan = new Scanner(System.in);
        //키보드로부터 값을 입력받을 수 있는 Scanner객체가 생성된다. 

        do{
            value = scan.nextInt(); // Scanner클래스를 이용하여 키보드로 부터 숫자값을 입력받는다.
            System.out.println("입력받은 수 : " + value);  
        }while(value != 10);  // 입력받은 값이 10이 아닐 경우에는 계속 반복한다.

        System.out.println("반복문 종료");
        // do {
            
        // } while (false);
        // Scanner scan = new Scanner(System.in);
        // int month = 0;
        // int day = 0;
        // System.out.print("월을 입력해 주세요: ");
        // month = scan.nextInt();
        // System.out.print("일을 입력해 주세요: ");
        // day = scan.nextInt(); // switch문 안에 if문 사용가능

        // System.out.printf("%d월 %d일은 ", month, day);

        // switch (month) {
        //     case 1:
        //     System.out.print("물병자리");
        //         if (day >= 20) {
        //         } else {
        //             System.out.print("염소자리");
        //         }
        //         break;
        //     case 2:
        //         if (day >= 19) {
        //             System.out.print("물고기자리");
        //         } else {
        //             System.out.print("물병자리");
        //         }
        //         break;
        //     case 3:
        //         if (day >= 21) {
        //             System.out.print("양자리");
        //         } else {
        //             System.out.print("물고기자리");
        //         }
        //         break;
        //     case 4:
        //         if (day >= 20) {
        //             System.out.print("황소자리");
        //         } else {
        //             System.out.print("양자리");
        //         }
        //         break;
        //     case 5:
        //         if (day >= 21) {
        //             System.out.print("쌍둥이자리");
        //         } else {
        //             System.out.print("황소자리");
        //         }
        //         break;
        //     case 6:
        //         if (day >= 22) {
        //             System.out.print("게자리");
        //         } else {
        //             System.out.print("쌍둥이자리");
        //         }
        //         break;
        //     case 7:
        //         if (day >= 23) {
        //             System.out.print("사자자리");
        //         } else {
        //             System.out.print("게자리");
        //         }
        //         break;
        //     case 8:
        //         if (day >= 23) {
        //             System.out.print("처녀자리");
        //         } else {
        //             System.out.print("사자자리");
        //         }
        //         break;
        //     case 9:
        //         if (day >= 24) {
        //             System.out.print("천칭자리");
        //         } else {
        //             System.out.print("처녀자리");
        //         }
        //         break;
        //     case 10:
        //         if (day >= 23) {
        //             System.out.print("전갈자리");
        //         } else {
        //             System.out.print("천칭자리");
        //         }
        //         break;
        //     case 11:
        //         if (day >= 23) {
        //             System.out.print("사수자리");
        //         } else {
        //             System.out.print("전갈자리");
        //         }
        //         break;
        //     case 12:
        //         if (day >= 25) {
        //             System.out.print("염소자리");
        //         } else {
        //             System.out.print("사수자리");
        //         }
        //         break;
        // }
        // System.out.printf( "입니다.");


    }
}