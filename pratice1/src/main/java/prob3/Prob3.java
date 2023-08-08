package prob3;

import java.util.Scanner;

public class Prob3 {
	
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		System.out.print("숫자를 입력하세요:");
		int num = scanner.nextInt();
		int sum = 0;
		
		if(num%2 == 1) {
			for(int i = 1; i<=num;i++) {
				if(i%2 == 1) {
					sum += i;
				}
			}
		} else {
			for(int i = 1; i<=num;i++) {
				if(i%2 == 0) {
					sum += i;
				}
			}
		}

		System.out.println("결과 값 : " + sum);

		/* 코드 작성 */
		
		scanner.close();
	}
}
