package example04_387p;

public class DaoImpl implements DataAccessObject {
	
	@Override
	public void select() {
		System.out.println(" DB에서 검색");
	}

	@Override
	public void insert() {
		System.out.println(" DB에 삽입");
	}

	@Override
	public void update() {
		System.out.println(" DB를 수정");
	}

	@Override
	public void delete() {
		System.out.println(" DB에서 삭제");
	}

}
