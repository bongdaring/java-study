package prob5;

public class Prob5 {

	public static void main(String[] args) {
		int i = 1;
		
		while(i<=100) {
			
			if((i > 10 && (i/10)%3 == 0) && (i%10 != 0 && (i%10)%3 == 0)) {
				System.out.println(i + "짝짝");
			}
			else if((i > 10 && (i/10)%3 == 0) || ((i%10 > 0) && (i%10)%3 == 0)) {
				System.out.println(i + "짝");
			}
			i++;
		}
	}
}
