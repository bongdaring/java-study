package chapter03;

import mypackage.Goods2;

public class Goods2App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Goods2 goods = new Goods2();
		
		//public: 접근 제한이 없다.
		goods.name = "camera";
		
		//protected : 같은 패키지에서 접근이 가능하다
//		goods.price = 10000;
		
		//디폴트는 같은 패키지에서 접근이 가능하다
//		goods.countStock = 10;

	}

}
