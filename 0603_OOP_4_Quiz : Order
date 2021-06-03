// Quiz : Order 클래스를 만들어서 orderPrint 메소드를 이용해 다음 표를 출력하시오.
// 인스턴스를 만들어 생성자 초기화 당시에 변수 값을 넣을 것.
주문번호 		  : 2021060300001
주문자 아이디	: abc123
주문날짜 		  : 2021/3/12
주문자 이름   : 홍길동
주문상품번호 	: PD03214-12
배송주소 	  	: 서울시 영등포구

//-----------------------------------------------------------------------

public class Order {
	
	long orderNum;
	String id;
	String orderDate;
	String name;
	String pNum;
	String address;
	
	
	public Order(long orderNum, String id,String orderDate,String name,String pNum,String address) {
		
		this.orderNum = orderNum;
		this.id = id;
		this.orderDate = orderDate;
		this.name = name;
		this.pNum = pNum;
		this.address = address;
		
	}
	
	void orderPrint() {
		System.out.println("주문번호 \t\t: "+orderNum);
		System.out.println("주문자 아이디\t: "+id);
		System.out.println("주문날짜 \t\t: "+orderDate);
		System.out.println("주문자 이름 \t: "+name);
		System.out.println("주문상품번호 \t: "+pNum);
		System.out.println("배송주소 \t\t: "+address);
	}

	
	//생성자 생성 기능 :
	// Source > Generate Constructor using Fields.. 
	// 필드 구분해서 자동으로 생성자 만들어줌.
//	public Order(long orderNum, String id, String orderDate, String name, String pNum, String address) {
//		super();
//		this.orderNum = orderNum;
//		this.id = id;
//		this.orderDate = orderDate;
//		this.name = name;
//		this.pNum = pNum;
//		this.address = address;
//	}
//	
}

// 실행 클래스 ------------------------------------------------
public class OrderTest {

	public static void main(String[] args) {
		
		Order OrderNum1 = new Order(2021060300001L, "abc123", "2021/3/12", "홍길동", "PD03214-12", "서울시 영등포구");

		OrderNum1.orderPrint();

	}

}
