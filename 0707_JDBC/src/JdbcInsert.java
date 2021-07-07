
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcInsert {
	public static void main(String[] args) {

		// 드라이버 정보를 받는 객체
		Connection con = null;
		// Select 된 결과를 받기 위한 용도의 ResultSet 초기화
		ResultSet rs = null;
		// 쿼리문 처리를 위한 용도의 객체 초기화
		PreparedStatement psmt = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 클래스 못찾을 경우를 대비해 ClassNotFoundException 예외 처리함.
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String id = "scott";
			String pw = "TIGER";

			// DB 연결 시도하는 코드 (주소,아이디,비밀번호)
			con = DriverManager.getConnection(url, id, pw);

			String sqlInsert = "insert into emp01 values (?,?,?,?,?,?,?,?)";
			
			psmt = con.prepareStatement(sqlInsert);
			
			psmt.setInt(1, 1111);
			psmt.setString(2, "홍길동");
			psmt.setString(3, "MANAGER");
			psmt.setInt(4, 7788);
			psmt.setString(5, "21/07/07");
			psmt.setInt(6, 2000);
			psmt.setInt(7, 0);
			psmt.setInt(8, 10);
			
			psmt.executeUpdate();	// insert, update, delete
			
			System.out.println("데이터 삽입 성공");
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DB 연결 실패");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("DB 사용 불가");
		}

	}
}
