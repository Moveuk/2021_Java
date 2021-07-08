package sequence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class StudentManage {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		Connection con = null;
		PreparedStatement psmt = null;
		PreparedStatement psmt2 = null;
		ResultSet rs = null;
		
		int menu = 0;
		int no = 0;
		String name = "";
		int age = 0;
		String phone = "";
		String email = "";
		String search = "";
		String sql = "";
		String sqlUpdate = "";
		String sqlDelete = "";
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "scott";
		String pw = "TIGER";
		
		System.out.println("===========================");
		System.out.println("    SMHRD 학생관리 프로그램    ");
		System.out.println("===========================");
		
		while (true) {
			System.out.println("1.학생추가 2.전체학생조회 3.특정학생조회 4.학생정보수정 5.학생삭제 6.프로그램종료");
			System.out.print(" >> ");
			
			menu = sc.nextInt();
			
			switch (menu) {
			
			case 1 :
				System.out.println("등록할 학생의 정보를 입력하시오.");
				System.out.println("이름 : ");
				name = sc.next();
				System.out.println("나이 : ");
				age = sc.nextInt();
				System.out.println("전화번호 : ");
				phone = sc.next();
				System.out.println("이메일 : ");
				email = sc.next();
				
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					con = DriverManager.getConnection(url,id,pw);
					// 첫번째 번호는 sequence에서 자동으로 넣어주게 된다.
					sql = "insert into student values (studentSeq.nextval,?,?,?,?)";
					// 값입력
					psmt = con.prepareStatement(sql);
					psmt.setString(1, name);
					psmt.setInt(2, age);
					psmt.setString(3, phone);
					psmt.setString(4, email);
					
					// executeUpdate()는 리턴값으로 업데이트 횟수를 보내줌. 0보다 크면 데이터 처리 성공
					int cnt = psmt.executeUpdate();
					if (cnt>0) {
						System.out.println("학생추가 성공");
					} else {
						System.out.println("학생추가 실패");
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					// DB나 파일을 수정하고 난 후 반드시 종료 해주어야 한다. 그렇지 않으면 프로그램에서 계속 붙잡고 있어서 사용하지 못하게 된다.
					// 계속 붙잡고 있다보면 프로그램이 뻑나서 종료될 수 있다.
					try {
						psmt.close();
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				System.out.println();
				
				break;
			
			case 2 :
				System.out.println("=====SMHRD 학생 목록=====");
				
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					con = DriverManager.getConnection(url,id,pw);
					
					sql = "select rownum, a.* from student a order by no desc";
					// rownum, * 라고 하면 어느 테이블의 전부인지를 인식하지 못하고 a라는 별칭을 이용해서 a의 모든 칼럼을 불러오게끔 해야한다.
					psmt = con.prepareStatement(sql);
					// 해당 쿼리 내용을 rs 에 저장
					rs = psmt.executeQuery();
					
					// while 문을 통해 칼럼을 넘겨가며(rs.next()) rs에 저장된 DB를 출력시켜줌.
					System.out.println("row\t번호\t이름\t나이\t번호\t\t이메일");
					while (rs.next()) {
						int row = rs.getRow();
						no = rs.getInt(2);
						name = rs.getString(3);
						age = rs.getInt(4);
						phone = rs.getString(5);
						email = rs.getString(6);
						
						System.out.println(row + 
								"\t" + no + "\t"+ name + "\t"+ age + "\t"+ phone +"\t"+ email);
					} // print while end
					System.out.println();
					
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					// 생성한 반대로 꺼줘야한다.
					try {
						rs.close();
						psmt.close();
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
				
			case 3 :	// 특정 학생 조회
				System.out.println("=====특정 학생 조회=====");
				System.out.println();
				System.out.println("조회하고 싶은 학생의 이름을 넣어주세요");
				System.out.print(" >> ");
				search = sc.next();
//				no = sc.nextInt();
				System.out.println();
				
				try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con = DriverManager.getConnection(url,id,pw);
				
				sql = "select rownum, a.* from student a where name = ? order by no desc";
				// rownum, * 라고 하면 어느 테이블의 전부인지를 인식하지 못하고 a라는 별칭을 이용해서 a의 모든 칼럼을 불러오게끔 해야한다.
				
				psmt = con.prepareStatement(sql);
				psmt.setString(1, search);
//				psmt.setInt(1, no);
				
				// 해당 쿼리 내용을 rs 에 저장
				rs = psmt.executeQuery();
				
				// isList 값으로 값이 있는지 없는지에 따라 출력되는 정보 다르게 표현.
				boolean isList = false;
				
				// while 문을 통해 칼럼을 넘겨가며(rs.next()) rs에 저장된 DB를 출력시켜줌.
				System.out.println("row\t번호\t이름\t나이\t번호\t\t이메일");
				while (rs.next()) {
					int row = rs.getRow();
					no = rs.getInt(2);
					name = rs.getString(3);
					age = rs.getInt(4);
					phone = rs.getString(5);
					email = rs.getString(6);
					
					System.out.println(row + 
							"\t" + no + "\t"+ name + "\t"+ age + "\t"+ phone +"\t"+ email);
					isList = true ;
				} // print while end
				
				if (!isList) {
					System.out.println("검색된 학생이 없습니다.");
				}
				
				System.out.println();
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				// 생성한 반대로 꺼줘야한다.
				try {
					rs.close();
					psmt.close();
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println();
				
				break;
				
			case 4 :	//	학생정보수정
				System.out.println("=====학생 정보 수정=====");
				System.out.println();
				System.out.println("수정하고 싶은 학생의 번호를 넣어주세요");
				System.out.print(" >> ");
				no = sc.nextInt();
				System.out.println();
				
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					con = DriverManager.getConnection(url,id,pw);
					
					sql = "select rownum, a.* from student a where no = ? order by no desc";
					// rownum, * 라고 하면 어느 테이블의 전부인지를 인식하지 못하고 a라는 별칭을 이용해서 a의 모든 칼럼을 불러오게끔 해야한다.
					
					psmt = con.prepareStatement(sql);
					psmt.setInt(1, no);
					// 해당 쿼리 내용을 rs 에 저장
					rs = psmt.executeQuery();
					
					// isList 값으로 값이 있는지 없는지에 따라 출력되는 정보 다르게 표현.
					boolean isList = false;
					
					// while 문을 통해 칼럼을 넘겨가며(rs.next()) rs에 저장된 DB를 출력시켜줌.
					System.out.println("row\t번호\t이름\t나이\t번호\t\t이메일");
					while (rs.next()) {
						int row = rs.getRow();
						no = rs.getInt(2);
						name = rs.getString(3);
						age = rs.getInt(4);
						phone = rs.getString(5);
						email = rs.getString(6);
						
						System.out.println(row + 
								"\t" + no + "\t"+ name + "\t"+ age + "\t"+ phone +"\t"+ email);
						isList = true ;
					} // print while end
					
					if (!isList) {
						System.out.println("검색된 학생이 없습니다.\n");
						break;
					}
					
					System.out.println();
					
					System.out.println("변경하실 정보를 선택해 주세요. \n 1.이름 2.나이 3.연락처 4.이메일 5.전체변경 ");
					System.out.print(" >> ");
					int select = sc.nextInt();
					
					if (select == 1) {
						System.out.print("\n 변경할 이름을 적어주세요.\n >> ");
						name = sc.next();
					} else if (select == 2) {
						System.out.print("\n 변경할 나이를 적어주세요.\n >> ");
						age = sc.nextInt();
					} else if (select == 3) {
						System.out.print("\n 변경할 연락처을 적어주세요.\n >> ");
						phone = sc.next();
					} else if (select == 4) {
						System.out.print("\n 변경할 이메일를 적어주세요.\n >> ");
						email = sc.next();
					} else if (select == 5) {
						System.out.print("\n 변경할 이름을 적어주세요.\n >> ");
						name = sc.next();
						System.out.print("\n 변경할 나이를 적어주세요.\n >> ");
						age = sc.nextInt();
						System.out.print("\n 변경할 연락처을 적어주세요.\n >> ");
						phone = sc.next();
						System.out.print("\n 변경할 이메일를 적어주세요.\n >> ");
						email = sc.next();
					}
					
					sqlUpdate = "update student ";
					sqlUpdate += "set name = ?,age = ?, phone = ?, email = ? where no = ?";
					psmt2 = con.prepareStatement(sqlUpdate);
					
					psmt2.setString(1, name);
					psmt2.setInt(2, age);
					psmt2.setString(3, phone);
					psmt2.setString(4, email);
					psmt2.setInt(5, no);
					
					// executeUpdate()는 리턴값으로 업데이트 횟수를 보내줌. 0보다 크면 데이터 처리 성공
					int cnt = psmt.executeUpdate();
					if (cnt>0) {
						System.out.println("학생 정보 변경 성공");
					} else {
						System.out.println("학생 정보 변경 실패");
					}
					
					
					// 변경 후 확인
					rs = psmt.executeQuery();
					
					// while 문을 통해 칼럼을 넘겨가며(rs.next()) rs에 저장된 DB를 출력시켜줌.
					System.out.println("row\t번호\t이름\t나이\t번호\t\t이메일");
					while (rs.next()) {
						int row = rs.getRow();
						no = rs.getInt(2);
						name = rs.getString(3);
						age = rs.getInt(4);
						phone = rs.getString(5);
						email = rs.getString(6);
						
						System.out.println(row + 
								"\t" + no + "\t"+ name + "\t"+ age + "\t"+ phone +"\t"+ email);
					} // print while end
					System.out.println();
					
					
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				break;
				
			case 5 :	//	학생삭제
				System.out.println("=====학생 삭제=====");
				System.out.println();
				System.out.println("삭제하고 싶은 학생의 번호 넣어주세요");
				System.out.print(" >> ");
				no = sc.nextInt();
				System.out.println();
				
				try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con = DriverManager.getConnection(url,id,pw);
				
				// 삭제를 위한 sql 문
				sqlDelete = "delete from student where no = ?";
				// 삭제 후 더블체킹을 위한 sql 문
				sql = "select rownum, a.* from student a where no = ? order by no desc";
				
				psmt2 = con.prepareStatement(sqlDelete);
				psmt2.setInt(1, no);
				
				psmt = con.prepareStatement(sql);
				psmt.setInt(1, no);
				
				// 삭제 실행
				int cnt = psmt2.executeUpdate();
				if (cnt>0) {
					System.out.println("학생 정보 삭제 성공\n");
				} else {
					System.out.println("학생 정보 삭제 실패\n");
				}
				
				// 삭제 후 기존 조건으로 다시 검색 더블체킹
				rs = psmt.executeQuery();
				
				// isList 값으로 값이 있는지 없는지에 따라 출력되는 정보 다르게 표현.
				boolean isList = false;
				
				// while 문을 통해 칼럼을 넘겨가며(rs.next()) rs에 저장된 DB를 출력시켜줌.
				System.out.println("row\t번호\t이름\t나이\t번호\t\t이메일");
				while (rs.next()) {
					int row = rs.getRow();
					no = rs.getInt(2);
					name = rs.getString(3);
					age = rs.getInt(4);
					phone = rs.getString(5);
					email = rs.getString(6);
					
					System.out.println(row + 
							"\t" + no + "\t"+ name + "\t"+ age + "\t"+ phone +"\t"+ email);
					isList = true ;
				} // print while end
				
				if (!isList) {
					System.out.println("검색된 학생이 없습니다.");
				}
				
				System.out.println();
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				// 생성한 반대로 꺼줘야한다.
				try {
					rs.close();
					psmt.close();
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println();
				
				break;
				
			case 6 :	//	프로그램종료
				System.out.println("\n프로그램종료");
				return;
			} // switch end
			
		} // while end
	}

}
