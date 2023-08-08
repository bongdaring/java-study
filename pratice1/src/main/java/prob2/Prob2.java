package prob2;

public class Prob2 {
	public static void main(String[] args) {
		/* 코드 작성 */
		for(int i = 0; i < 10; i++) {
			for(int j = i+1; j <= i+10; j++) {
				System.out.print(j);
			}
			System.out.println();
		}
	}
}
