package prob02;

import java.util.Scanner;

public class GoodsApp {
	private static final int COUNT_GOODS = 3;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		Goods[] goods = new Goods[COUNT_GOODS];
		
		System.out.println("실행 결과:");
		for(int i = 0; i < goods.length; i++) {
			String[] good = scanner.nextLine().split(" ");
			int price = Integer.parseInt(good[1]);
			int count = Integer.parseInt(good[2]);
			goods[i] = new Goods(good[0], price, count);
		}
		
		System.out.println(goods[0].getName());
	
		for(int i = 0; i < goods.length; i++) {
			System.out.println(goods[i].getName()+
					"(가격:"+goods[i].getPrice()+")이 "+
					goods[i].getCount() + "개 입고 되었습니다.");
		}

		scanner.close();
	}
}
