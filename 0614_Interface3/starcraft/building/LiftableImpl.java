package starcraft.building;

public class LiftableImpl implements Liftable {

	@Override
	public void liftoff() {
		System.out.println("�ͺ���� ����! �̷��Ѵ�.");
	}

	@Override
	public void move(int x, int y) {
		System.out.println("�����δ�.");
	}

	@Override
	public void stop() {
		System.out.println("�����.");
	}

	@Override
	public void land() {
		System.out.println("�����Ѵ�.");
	}

}
