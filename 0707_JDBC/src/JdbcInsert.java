
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcInsert {
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

			String sqlInsert = "insert into emp01 values (?,?,?,?,?,?,?,?)";
			
			psmt = con.prepareStatement(sqlInsert);
			
			psmt.setInt(1, 1111);
			psmt.setString(2, "ȫ�浿");
			psmt.setString(3, "MANAGER");
			psmt.setInt(4, 7788);
			psmt.setString(5, "21/07/07");
			psmt.setInt(6, 2000);
			psmt.setInt(7, 0);
			psmt.setInt(8, 10);
			
			psmt.executeUpdate();	// insert, update, delete
			
			System.out.println("������ ���� ����");
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DB ���� ����");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("DB ��� �Ұ�");
		}

	}
}
