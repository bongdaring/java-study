package chapter03;

public class GoodsApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Goods camera = new Goods();
		camera.setName("Nikon");
		camera.setPrice(400000);
		camera.setCountStock(10);
		camera.setCountSold(20);
		
		//정보은닉
		camera.setPrice(-1);
		
		//countOfGoods 테스트
		Goods goods2 = new Goods();
		Goods goods3 = new Goods();
		Goods goods4 = new Goods();
		
		System.out.println(Goods.countOfGoods);
		
		System.out.println("상품이름 : " + camera.getName() + 
				", 가격 : "+ camera.getPrice() + 
				", 재고개수 : "+ camera.getCountStock() +
				", 팔린 개수 : " + camera.getCountSold());
	}

}
