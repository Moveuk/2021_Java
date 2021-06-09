package field_polymorphism;

public class CarExample {
	public static void main(String[] args) {
		Car car = new Car();
		
		for(int i=1; i<=10; i++) {
			int problemLocation = car.run();
			switch(problemLocation) {
				case 1:
					System.out.println("�տ��� HankookTire�� ��ü");
//					car.frontLeftTire = new HankookTire("�տ���", 15);
					car.tires[0] = new HankookTire("�տ���", 15);
					break;
				case 2:
					System.out.println("�տ����� KumhoTire�� ��ü");
					car.tires[1] = new KumhoTire("�տ�����", 13);	
					break;
				case 3:
					System.out.println("�ڿ��� HankookTire�� ��ü");
					car.tires[2] = new HankookTire("�ڿ���", 14);	
					break;
				case 4:
					System.out.println("�ڿ����� KumhoTire�� ��ü");
					car.tires[3] = new KumhoTire("�ڿ�����", 17);		
					break;
			}
			
			// master
//			for(int i=1; i<=5; i++) {
//				int problemLocation = car.run();
//				if(problemLocation != 0) {
//					System.out.println(car.tires[problemLocation-1].location + " HankookTire�� ��ü");
//					car.tires[problemLocation-1] = new HankookTire(car.tires[problemLocation-1].location, 15);
//				}
			System.out.println("----------------------------------------");
		}
	}
}
