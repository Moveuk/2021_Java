package example04_387p;

public class DaoImpl implements DataAccessObject {
	
	@Override
	public void select() {
		System.out.println(" DB���� �˻�");
	}

	@Override
	public void insert() {
		System.out.println(" DB�� ����");
	}

	@Override
	public void update() {
		System.out.println(" DB�� ����");
	}

	@Override
	public void delete() {
		System.out.println(" DB���� ����");
	}

}
