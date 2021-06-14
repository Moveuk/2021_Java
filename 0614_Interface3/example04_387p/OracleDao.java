package example04_387p;

public class OracleDao implements DataAccessObject {
	DaoImpl di = new DaoImpl();

	String DBname = "Oracle";
	
	@Override
	public void select() {
		System.out.print(DBname);
		di.select();
	}

	@Override
	public void insert() {
		System.out.print(DBname);
		di.insert();
	}

	@Override
	public void update() {
		System.out.print(DBname);
		di.update();
	}

	@Override
	public void delete() {
		System.out.print(DBname);
		di.delete();
	}
}
