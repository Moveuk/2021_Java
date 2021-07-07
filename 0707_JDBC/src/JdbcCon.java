
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class JdbcCon {

	public static void main(String[] args) {

		// ����̹� ������ �޴� ��ü
		Connection con = null;
		// Select �� ����� �ޱ� ���� �뵵�� ResultSet �ʱ�ȭ
		ResultSet rs = null;
		// ������ ó���� ���� �뵵�� ��ü �ʱ�ȭ
		PreparedStatement psmt = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Ŭ���� ��ã�� ��츦 ����� ClassNotFoundException ���� ó����.
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String id = "scott";
			String pw = "TIGER";

			// DB ���� �õ��ϴ� �ڵ� (�ּ�,���̵�,��й�ȣ)
			con = DriverManager.getConnection(url, id, pw);
			// ���н� DB ������ �� �ȵ� ���̴�.
			// �� �ڵ� ������ SQLException ���� ó���� �ʿ��ϴ�.

			System.out.println("DB ���� ����" + "\n");

			String sql = "select * from dept";

			// ��ɾ� ���¸� ����̹��� ���� ���� psmt ��ü�� �����ص�.
			psmt = con.prepareStatement(sql);
			// �������� ������ ��ȸ�� �Ǿ���. execute ����
			rs = psmt.executeQuery();

			// ���� rs �� null �̸� ��ȸ�� �̷������ �ʾҰ�, ���� �ִٸ� ���������� ����(��ȸ)�� �� ���� ����.

			// rs�� �״�� ����ϸ� ������ ���� �ּҰ� ����.
			System.out.println("rx�� �ּҰ� : ");
			System.out.println(rs + "\n");
			// oracle.jdbc.driver.OracleResultSetImpl@431cd9b2

			// �÷��� ����
			System.out.println("�μ���ȣ\t�μ���\t\t�ٹ�����");

			while (rs.next()) {
				// next �޼ҵ� : ���� ���� ���� true ����, ���� ���� false ����
				// ��ġ Iterator�� hasNext()�� ���� ���.

				// dept���� DEPTNO / DNAME / LOC �� ������.
				// Ÿ�� : int / String / String
				int deptno = rs.getInt("deptno");
				String dname = rs.getString("dname");
				String loc = rs.getString("loc");
				// ����ϱ� ���ϱ� ���Ͽ� ������ �ʱ�ȭ.

				if (dname.equals("SALES")) {
					System.out.println(deptno + "\t" + dname + "\t\t" + loc);
				} else {
					System.out.println(deptno + "\t" + dname + "\t" + loc);
				}
			}

			// deptno 10 ���� �μ� ���
			// ���ϵ� ī��(?)�� �̿��ؼ� ���� ����� ����� ���.
			// ������ ������ �� ���ϵ� ī�忡 ���ϴ� ���� �־��� �� �ִ�.
			String sql2 = "select * from dept where deptno = ?";

			psmt = con.prepareStatement(sql2);
			// ù��° ���� : ����ǥ�� ��ġ ex: insert ������ ��� ���� �־��� ���� ?�� ����.
			// �ι�° ���� : ?�� �ְ� ���� ��
			psmt.setInt(1, 10);

			rs = psmt.executeQuery();

			System.out.println();
			System.out.println("�μ���ȣ\t�μ���\t\t�ٹ�����");

			while (rs.next()) {
				int deptno = rs.getInt("deptno");
				String dname = rs.getString("dname");
				String loc = rs.getString("loc");

				if (dname.equals("SALES")) {
					System.out.println(deptno + "\t" + dname + "\t\t" + loc);
				} else {
					System.out.println(deptno + "\t" + dname + "\t" + loc);
				}
			}

			// �ٹ� ������ ���� ����
			String sql3 = "select * from dept where loc = ?";

			psmt = con.prepareStatement(sql3);
			// ù��° ���� : ����ǥ�� ��ġ ex: insert ������ ��� ���� �־��� ���� ?�� ����.
			// �ι�° ���� : ?�� �ְ� ���� ��
			psmt.setString(1, "DALLAS");

			rs = psmt.executeQuery();

			System.out.println();
			System.out.println("�μ���ȣ\t�μ���\t\t�ٹ�����");

			while (rs.next()) {
				int deptno = rs.getInt("deptno");
				String dname = rs.getString("dname");
				String loc = rs.getString("loc");

				if (dname.equals("SALES")) {
					System.out.println(deptno + "\t" + dname + "\t\t" + loc);
				} else {
					System.out.println(deptno + "\t" + dname + "\t" + loc);
				}
			}

			// ����
			// emp01 ���̺��� �μ� ��ȣ�� 30�� ����� ��� ������ ��ȸ.

			// EMPNO ENAME JOB MGR HIREDATE SAL COMM DEPTNO

			String sql4 = "select * from emp01 where deptno = ?";

			psmt = con.prepareStatement(sql4);
			psmt.setInt(1, 30);

			rs = psmt.executeQuery();

			System.out.println();
			System.out.println("���\t����̸�\t��å\t\t�ҼӸŴ���\t��볯¥\t\t����\t������\t�μ���ȣ");

			while (rs.next()) {
				int empno = rs.getInt("empno");
				String ename = rs.getString("ename");
				String job = rs.getString("job");
				String mgr = rs.getString("mgr");
				String hiredate = (rs.getString("hiredate")).substring(0, 10); // �ú��ʱ��� �����°� substring���� ��������
				int sal = rs.getInt("sal");
				int comm = rs.getInt("comm");
				int deptno = rs.getInt("deptno");

				// Ʋ�ȸ´°� �ٲ���.
				if (job.length() >= 8) {
					System.out.println(empno + "\t" + ename + "\t" + job + "\t" + mgr + "\t" + hiredate + "\t" + sal
							+ "\t" + comm + "\t" + deptno);
				} else {
					System.out.println(empno + "\t" + ename + "\t" + job + "\t\t" + mgr + "\t" + hiredate + "\t" + sal
							+ "\t" + comm + "\t" + deptno);
				}

			}

			// ---------------

			// JDBC Insert

//			String sqlInsert = "insert into imp01";
//			sqlInsert += "values(111,'ȫ�浿','MANAGER',7788,'21/07/07',2000,'',10)";
//			
//			String sqlInsert = "insert into emp01 values (?,?,?,?,?,?,?,?)";
//			
//			psmt = con.prepareStatement(sqlInsert);
//			
//			psmt.setInt(1, 1111);
//			psmt.setString(2, "ȫ�浿");
//			psmt.setString(3, "MANAGER");
//			psmt.setInt(4, 7788);
//			psmt.setString(5, "21/07/07");
//			psmt.setInt(6, 2000);
//			psmt.setInt(7, 0);
//			psmt.setInt(8, 10);
//			
//			psmt.executeUpdate();  // insert, update, delete �� �� ���
//			System.out.println("������ ���� ����");

			// ȫ�浿 �Է� Ȯ��
			// emp01 ���̺��� �μ� ��ȣ�� 30�� ����� ��� ������ ��ȸ.

			// EMPNO ENAME JOB MGR HIREDATE SAL COMM DEPTNO

			String sql5 = "select * from emp01";

			psmt = con.prepareStatement(sql5);

			rs = psmt.executeQuery();

			System.out.println();
			System.out.println("���\t����̸�\t��å\t\t�ҼӸŴ���\t��볯¥\t\t����\t������\t�μ���ȣ");

			while (rs.next()) {
				int empno = rs.getInt("empno");
				String ename = rs.getString("ename");
				String job = rs.getString("job");
				String mgr = rs.getString("mgr");
				String hiredate = (rs.getString("hiredate")).substring(0, 10); // �ú��ʱ��� �����°� substring���� ��������
				int sal = rs.getInt("sal");
				int comm = rs.getInt("comm");
				int deptno = rs.getInt("deptno");

				// Ʋ�ȸ´°� �ٲ���.
				if (job.length() >= 8) {
					System.out.println(empno + "\t" + ename + "\t" + job + "\t" + mgr + "\t" + hiredate + "\t" + sal
							+ "\t" + comm + "\t" + deptno);
				} else {
					System.out.println(empno + "\t" + ename + "\t" + job + "\t\t" + mgr + "\t" + hiredate + "\t" + sal
							+ "\t" + comm + "\t" + deptno);
				}

			}
			System.out.println("\n-----------\n");
			// ---------------

			// Commit�� ����ȭ

//			con.setAutoCommit(false);

			// setAutoCommit�� false�� �ϸ� Commit�� �ڵ��� �ƴ϶� �������� ����� �Ѵ�.

			// ---------------

			// update
			String sqlUpdate = "update emp01 set ename = '�̼���' where empno = ?";

			psmt = con.prepareStatement(sqlUpdate);
			psmt.setInt(1, 1111);

			int result = psmt.executeUpdate(); // insert, update, delete �� �� ���

			if (result > 0) {
				System.out.println("������ ���� ����");
				con.commit();
			} else {
				System.out.println("������ ���� ����");
				con.rollback();
			}

			// �̼��� ���� Ȯ��
			// EMPNO ENAME JOB MGR HIREDATE SAL COMM DEPTNO

			String sql6 = "select * from emp01";

			psmt = con.prepareStatement(sql6);

			rs = psmt.executeQuery();

			System.out.println();
			System.out.println("���\t����̸�\t��å\t\t�ҼӸŴ���\t��볯¥\t\t����\t������\t�μ���ȣ");

			while (rs.next()) {
				int empno = rs.getInt("empno");
				String ename = rs.getString("ename");
				String job = rs.getString("job");
				String mgr = rs.getString("mgr");
				String hiredate = (rs.getString("hiredate")).substring(0, 10); // �ú��ʱ��� �����°� substring���� ��������
				int sal = rs.getInt("sal");
				int comm = rs.getInt("comm");
				int deptno = rs.getInt("deptno");

				if (job.length() >= 8) {
					System.out.println(empno + "\t" + ename + "\t" + job + "\t" + mgr + "\t" + hiredate + "\t" + sal
							+ "\t" + comm + "\t" + deptno);
				} else {
					System.out.println(empno + "\t" + ename + "\t" + job + "\t\t" + mgr + "\t" + hiredate + "\t" + sal
							+ "\t" + comm + "\t" + deptno);
				}

			}

			System.out.println("\n-----------\n");
			// ---------------

			// delete �̼��� ����
			String sqlDelete = "delete from emp01 where empno = ? ";

			psmt = con.prepareStatement(sqlDelete);
			psmt.setInt(1, 1111);

			int resultDelete = psmt.executeUpdate(); // insert, update, delete �� �� ���

			if (result > 0) {
				System.out.println("������ ���� ����");
				con.commit();
			} else {
				System.out.println("������ ���� ����");
				con.rollback();
			}

			// �̼��� ���� Ȯ��
			// EMPNO ENAME JOB MGR HIREDATE SAL COMM DEPTNO

			String sql7 = "select * from emp01";

			psmt = con.prepareStatement(sql7);

			rs = psmt.executeQuery();

			System.out.println();
			System.out.println("���\t����̸�\t��å\t\t�ҼӸŴ���\t��볯¥\t\t����\t������\t�μ���ȣ");

			while (rs.next()) {
				int empno = rs.getInt("empno");
				String ename = rs.getString("ename");
				String job = rs.getString("job");
				String mgr = rs.getString("mgr");
				String hiredate = (rs.getString("hiredate")).substring(0, 10); // �ú��ʱ��� �����°� substring���� ��������
				int sal = rs.getInt("sal");
				int comm = rs.getInt("comm");
				int deptno = rs.getInt("deptno");

				if (job.length() >= 8) {
					System.out.println(empno + "\t" + ename + "\t" + job + "\t" + mgr + "\t" + hiredate + "\t" + sal
							+ "\t" + comm + "\t" + deptno);
				} else {
					System.out.println(empno + "\t" + ename + "\t" + job + "\t\t" + mgr + "\t" + hiredate + "\t" + sal
							+ "\t" + comm + "\t" + deptno);
				}

			}

			System.out.println("\n-----------\n");
			// ---------------

			// ����
			// �����ȣ ����� �޿� �μ��� �޿����
			// emp, dept, salgrade

			String sqlExample = "Select e.empno, e.ename, e.sal, d.dname, s.grade from emp01 e, dept d, salgrade s where e.deptno = d.deptno and e.sal between s.losal and s.hisal";

			psmt = con.prepareStatement(sqlExample);
			rs = psmt.executeQuery();

			System.out.println();
			System.out.println("�����ȣ\t�����\t�޿�\t�μ���\t\t�޿����");

			while (rs.next()) {
				int empno = rs.getInt("empno");
				String ename = rs.getString("ename");
				int sal = rs.getInt("sal");
				String dname = rs.getString("dname");
				String salgrade = rs.getString("grade");

				if (dname.length()<8) {
					System.out.println(empno + "\t" + ename + "\t" + sal + "\t" + dname + "\t\t" + salgrade);					
				} else {
					System.out.println(empno + "\t" + ename + "\t" + sal + "\t" + dname + "\t" + salgrade);					
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DB ���� ����");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("DB ��� �Ұ�");
		}

	}

}
