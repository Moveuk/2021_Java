# Sequence
Key Word : Sequence    

<hr/>

 ## 시퀀스(Sequence)란?
    
시퀀스란 자동으로 순차적으로 증가하는 순번을 반환하는 데이터베이스 객체입니다. 보통 PK값에 중복값을 방지하기위해 사용합니다. 예를들어 게시판에 글이 하나 추가될때마다 글번호(PK)가 생겨야 한다고 해보겠습니다. 만약 100번까지 글 번호가 생성되어있다면 그 다음 글이 추가가 되었을 경우 글 번호가 101으로 하나의 ROW를 생성해주어야 할것입니다. 이때 101이라는 숫자를 얻으려면 기존 글번호중 가장 큰 값에 +1을 하는 로직을 어딘가에 넣어야하는데 시퀀스를 사용하면 이러한 로직이 필요없이 데이터베이스에 ROW가 추가될때마다 자동으로 +1을 시켜주어 매우 편리합니다.    

<br/>
<hr/>

## 시퀀스(Sequence)를 이용한 insert 예제

<br/><br/>
<hr/>

### Student 테이블과 StudentSeq 시퀀스 생성
   
 먼저 SQL Developer 에서 Student 테이블과 StudentSequence를 하나 만들어주었다.

```sql
create table student(
    no number(2) primary key,
    name varchar2(10),
    age number(3),
    phone varchar(15),
    email varchar(30)
);
    
create SEQUENCE studentSeq
INCREMENT BY 1
START WITH 1;

select * from student;
```




<br/><br/>
<hr/>

### 1. 학생추가 기능 구현

 시퀀스를 이용해서 no 값은 자동으로 들어가게 하고 우리는 다른 속성들만 정의하여 학생들의 데이터를 넣도록 만들었다.

```java
public class StudentManage {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		int menu = 0;
		int no = 0;
		String name = "";
		int age = 0;
		String phone = "";
		String email = "";
		String search = "";
		String sql = "";
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "scott";
		String pw = "TIGER";
		
		System.out.println("===========================");
		System.out.println("    SMHRD 학생관리 프로그램    ");
		System.out.println("===========================");
		
		while (true) {
			System.out.println("1.학생추가 2.전체학생조회 3.특정학생조회 4.학생정보수정 5.학생삭제 6.프로그램종료");
			System.out.println();
			
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
					sql = "insert into student values (studentsSeq.nextval,?,?,?,?)";
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
			
			} // while end
			
		} // while end
	}

}
```

<br/>
  
 DB나 파일을 수정하고 난 후 반드시 종료 해주어야 한다. 그렇지 않으면 프로그램에서 계속 붙잡고 있어서 사용하지 못하게 된다. 계속 붙잡고 있다보면 프로그램이 뻑나서 종료될 수 있다.       
 
```java
	try {
		psmt.close();
		con.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
```



<br/><br/>
<hr/>

### 2. 전체학생조회 기능 구현




```java
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
						System.out.println();
					} // print while end
					
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
```



<br/><br/>
<hr/>

### 3. 특정학생조회 기능 구현

`sql = "select rownum, a.* from student a where name = ? order by no desc";`에 와일드 카드를 이용해서 setString 해주어서 검색해준다.

 문제는 하다가 물음표 부분에서 오히려 따옴표('?')가 없어야 작동한다.


```java
		case 3 :	// 특정 학생 조회
			System.out.println("=====특정 학생 조회=====");
			System.out.println();
			System.out.println("조회하고 싶은 학생의 이름을 넣어주세요");
			System.out.print(">>");
			search = sc.next();
			System.out.println();

			try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url,id,pw);

			sql = "select rownum, a.* from student a where name = ? order by no desc";
			// rownum, * 라고 하면 어느 테이블의 전부인지를 인식하지 못하고 a라는 별칭을 이용해서 a의 모든 칼럼을 불러오게끔 해야한다.

			psmt = con.prepareStatement(sql);
			psmt.setString(1, search);
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

			if (isList == false) {
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
```




<br/><br/>
<hr/>

### 4. 학생정보수정 기능 구현

























<br/><br/>
<hr/>

### 5. 학생정보삭제 기능 구현

```java
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
```




<br/><br/>
<hr/>

### 5. 프로그램종료 기능 구현

```java
	case 6 :	//	프로그램종료
		System.out.println("\n프로그램종료");
		return;
```















