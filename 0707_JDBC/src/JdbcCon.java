
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class JdbcCon {

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
			// 실패시 DB 연결이 잘 안된 것이다.
			// 이 코드 때문에 SQLException 예외 처리가 필요하다.

			System.out.println("DB 연결 성공" + "\n");

			String sql = "select * from dept";

			// 명령어 상태를 드라이버로 보낸 것을 psmt 객체에 저장해둠.
			psmt = con.prepareStatement(sql);
			// 쿼리문이 실제로 조회가 되어짐. execute 실행
			rs = psmt.executeQuery();

			// 만약 rs 가 null 이면 조회가 이루어지지 않았고, 값이 있다면 정상적으로 쿼리(조회)가 된 것일 것임.

			// rs를 그대로 출력하면 다음과 같이 주소가 나옴.
			System.out.println("rx의 주소값 : ");
			System.out.println(rs + "\n");
			// oracle.jdbc.driver.OracleResultSetImpl@431cd9b2

			// 컬럼명 설정
			System.out.println("부서번호\t부서명\t\t근무지역");

			while (rs.next()) {
				// next 메소드 : 값이 있을 때는 true 리턴, 없을 때는 false 리턴
				// 마치 Iterator의 hasNext()와 같은 기능.

				// dept에는 DEPTNO / DNAME / LOC 가 존재함.
				// 타입 : int / String / String
				int deptno = rs.getInt("deptno");
				String dname = rs.getString("dname");
				String loc = rs.getString("loc");
				// 사용하기 편하기 위하여 변수로 초기화.

				if (dname.equals("SALES")) {
					System.out.println(deptno + "\t" + dname + "\t\t" + loc);
				} else {
					System.out.println(deptno + "\t" + dname + "\t" + loc);
				}
			}

			// deptno 10 번인 부서 출력
			// 와일드 카드(?)를 이용해서 쿼리 양식을 만드는 방법.
			// 쿼리를 실행할 때 와일드 카드에 원하는 값을 넣어줄 수 있다.
			String sql2 = "select * from dept where deptno = ?";

			psmt = con.prepareStatement(sql2);
			// 첫번째 인자 : 물음표의 위치 ex: insert 에서는 모든 값을 넣어줄 때는 ?가 많다.
			// 두번째 인자 : ?에 넣고 싶은 값
			psmt.setInt(1, 10);

			rs = psmt.executeQuery();

			System.out.println();
			System.out.println("부서번호\t부서명\t\t근무지역");

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

			// 근무 지역에 대한 쿼리
			String sql3 = "select * from dept where loc = ?";

			psmt = con.prepareStatement(sql3);
			// 첫번째 인자 : 물음표의 위치 ex: insert 에서는 모든 값을 넣어줄 때는 ?가 많다.
			// 두번째 인자 : ?에 넣고 싶은 값
			psmt.setString(1, "DALLAS");

			rs = psmt.executeQuery();

			System.out.println();
			System.out.println("부서번호\t부서명\t\t근무지역");

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

			// 예제
			// emp01 테이블에서 부서 번호가 30인 사원의 모든 정보를 조회.

			// EMPNO ENAME JOB MGR HIREDATE SAL COMM DEPTNO

			String sql4 = "select * from emp01 where deptno = ?";

			psmt = con.prepareStatement(sql4);
			psmt.setInt(1, 30);

			rs = psmt.executeQuery();

			System.out.println();
			System.out.println("사원\t사원이름\t직책\t\t소속매니저\t고용날짜\t\t월급\t성과금\t부서번호");

			while (rs.next()) {
				int empno = rs.getInt("empno");
				String ename = rs.getString("ename");
				String job = rs.getString("job");
				String mgr = rs.getString("mgr");
				String hiredate = (rs.getString("hiredate")).substring(0, 10); // 시분초까지 나오는것 substring으로 제거해줌
				int sal = rs.getInt("sal");
				int comm = rs.getInt("comm");
				int deptno = rs.getInt("deptno");

				// 틀안맞는거 바꿔줌.
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
//			sqlInsert += "values(111,'홍길동','MANAGER',7788,'21/07/07',2000,'',10)";
//			
//			String sqlInsert = "insert into emp01 values (?,?,?,?,?,?,?,?)";
//			
//			psmt = con.prepareStatement(sqlInsert);
//			
//			psmt.setInt(1, 1111);
//			psmt.setString(2, "홍길동");
//			psmt.setString(3, "MANAGER");
//			psmt.setInt(4, 7788);
//			psmt.setString(5, "21/07/07");
//			psmt.setInt(6, 2000);
//			psmt.setInt(7, 0);
//			psmt.setInt(8, 10);
//			
//			psmt.executeUpdate();  // insert, update, delete 할 때 사용
//			System.out.println("데이터 삽입 성공");

			// 홍길동 입력 확인
			// emp01 테이블에서 부서 번호가 30인 사원의 모든 정보를 조회.

			// EMPNO ENAME JOB MGR HIREDATE SAL COMM DEPTNO

			String sql5 = "select * from emp01";

			psmt = con.prepareStatement(sql5);

			rs = psmt.executeQuery();

			System.out.println();
			System.out.println("사원\t사원이름\t직책\t\t소속매니저\t고용날짜\t\t월급\t성과금\t부서번호");

			while (rs.next()) {
				int empno = rs.getInt("empno");
				String ename = rs.getString("ename");
				String job = rs.getString("job");
				String mgr = rs.getString("mgr");
				String hiredate = (rs.getString("hiredate")).substring(0, 10); // 시분초까지 나오는것 substring으로 제거해줌
				int sal = rs.getInt("sal");
				int comm = rs.getInt("comm");
				int deptno = rs.getInt("deptno");

				// 틀안맞는거 바꿔줌.
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

			// Commit의 수동화

//			con.setAutoCommit(false);

			// setAutoCommit을 false로 하면 Commit이 자동이 아니라 수동으로 해줘야 한다.

			// ---------------

			// update
			String sqlUpdate = "update emp01 set ename = '이순신' where empno = ?";

			psmt = con.prepareStatement(sqlUpdate);
			psmt.setInt(1, 1111);

			int result = psmt.executeUpdate(); // insert, update, delete 할 때 사용

			if (result > 0) {
				System.out.println("데이터 변경 성공");
				con.commit();
			} else {
				System.out.println("데이터 변경 실패");
				con.rollback();
			}

			// 이순신 변경 확인
			// EMPNO ENAME JOB MGR HIREDATE SAL COMM DEPTNO

			String sql6 = "select * from emp01";

			psmt = con.prepareStatement(sql6);

			rs = psmt.executeQuery();

			System.out.println();
			System.out.println("사원\t사원이름\t직책\t\t소속매니저\t고용날짜\t\t월급\t성과금\t부서번호");

			while (rs.next()) {
				int empno = rs.getInt("empno");
				String ename = rs.getString("ename");
				String job = rs.getString("job");
				String mgr = rs.getString("mgr");
				String hiredate = (rs.getString("hiredate")).substring(0, 10); // 시분초까지 나오는것 substring으로 제거해줌
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

			// delete 이순신 삭제
			String sqlDelete = "delete from emp01 where empno = ? ";

			psmt = con.prepareStatement(sqlDelete);
			psmt.setInt(1, 1111);

			int resultDelete = psmt.executeUpdate(); // insert, update, delete 할 때 사용

			if (result > 0) {
				System.out.println("데이터 삭제 성공");
				con.commit();
			} else {
				System.out.println("데이터 삭제 실패");
				con.rollback();
			}

			// 이순신 삭제 확인
			// EMPNO ENAME JOB MGR HIREDATE SAL COMM DEPTNO

			String sql7 = "select * from emp01";

			psmt = con.prepareStatement(sql7);

			rs = psmt.executeQuery();

			System.out.println();
			System.out.println("사원\t사원이름\t직책\t\t소속매니저\t고용날짜\t\t월급\t성과금\t부서번호");

			while (rs.next()) {
				int empno = rs.getInt("empno");
				String ename = rs.getString("ename");
				String job = rs.getString("job");
				String mgr = rs.getString("mgr");
				String hiredate = (rs.getString("hiredate")).substring(0, 10); // 시분초까지 나오는것 substring으로 제거해줌
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

			// 문제
			// 사원번호 사원명 급여 부서명 급여등급
			// emp, dept, salgrade

			String sqlExample = "Select e.empno, e.ename, e.sal, d.dname, s.grade from emp01 e, dept d, salgrade s where e.deptno = d.deptno and e.sal between s.losal and s.hisal";

			psmt = con.prepareStatement(sqlExample);
			rs = psmt.executeQuery();

			System.out.println();
			System.out.println("사원번호\t사원명\t급여\t부서명\t\t급여등급");

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
			System.out.println("DB 연결 실패");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("DB 사용 불가");
		}

	}

}
