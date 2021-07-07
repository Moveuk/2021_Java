# JDBC (Java DataBase Connectivity) 
Key Word : JDBC(Java DataBase Connectivity), build path, libraries, SQL, JAR, CRUD, Create, Insert, Delete, Update


<hr/>

 ## JDBC (Java DataBase Connectivity) : 연동 환경 구성
    
1. 원하는 프로젝트를 우클릭하여 빌드 패스 설정을 하기 위해 `Configure Build Path`를 클릭하여 창을 연다.   
   
![image](https://user-images.githubusercontent.com/84966961/124691610-0bca9600-df17-11eb-9297-4d5a7ea201fb.png)   
   
<br/><br/>
<hr/>
   
2. 빌드 패스에서 외부 라이브러리를 등록해준다. 우리는 SQL 라이브러리를 사용하기 위하여 Libraries 탭으로 이동하여 Add Ecternal JARs 버튼을 눌러 라이브러리를 추가한다. 자바는 JAR 확장자로 라이브러리를 제공한다.     
      
<br/>

![image](https://user-images.githubusercontent.com/84966961/124691798-6b28a600-df17-11eb-809c-2f305248f402.png)   
     
<br/><br/>
<hr/>
   
3. SQL에서 제공하는 라이브러리를 찾기 위하여 다음 폴더 주소로 이동한다. `C:\oraclexe\app\oracle\product\11.2.0\server\jdbc\lib`에는 `ojdbc6.jar`라는 파일이 있는데 이것을 통해 SQL 라이브러리를 연동할 수 있다. 
      
<br/>

![image](https://user-images.githubusercontent.com/84966961/124691865-885d7480-df17-11eb-909c-0c080d51b4c0.png)   
     
<br/><br/>
<hr/>
   
4. 외부 라이브러리가 배포된 것을 알 수 있다. 패키지 익스플로러에서도 추가된 외부 라이브러리를 확인할 수 있다.
      
<br/>

![image](https://user-images.githubusercontent.com/84966961/124691916-9a3f1780-df17-11eb-965c-032108b5b068.png)   
      
<br/>

![image](https://user-images.githubusercontent.com/84966961/124691974-b5aa2280-df17-11eb-936a-be1eed7fa826.png)   
   
<br/><br/>
<hr/>
   
5. 전역변수 확인하는 방법(xe 부분)
   
 ```
 jdbc:oracle:thin:@localhost:1521:xe
 ```   
   
 default 설정을 변경하지 않았다면 앞부분의 인자들은(1521) 모두 같을 것이며 사용자 환경에 따라 끝부분의 전역변수 값 xe만 다를 수 있기 때문에 확인을 한다.
   
 `C:\oraclexe\app\oracle\product\11.2.0\server\network\ADMIN` 폴더 주소로 이동하여 `tnsnames.ora` 파일을 메모장으로 열어 `SERVICE_NAME = XE` 부분을 확인 해보자.   
      
<br/>

![image](https://user-images.githubusercontent.com/84966961/124692295-4ed93900-df18-11eb-92b1-ccc09e042a09.png)   
      
<br/>

![image](https://user-images.githubusercontent.com/84966961/124692317-5bf62800-df18-11eb-9609-6b92f4efcaea.png)   
   
<br/><br/>
<hr/>
  
## JDBC 코딩 순서

① JDBC 드라이버 로딩

② 데이터베이스 connection 연결

③ 쿼리(sql)문장을 실행하기 위한 Statement / PreparedStatement / CallableStatement 객체 생성

④ 쿼리 실행

⑤ 쿼리 실행의 결과값(int, ResultSet) 사용

⑥ 사용된 객체 종료(ResultSet, Statement / PreparedStatement / CallableStatement , Connection)
  
   
<br/><br/>
<hr/>
  
### DB 연동  

이제는 이클립스에서 프로젝트 내부에 클래스를 만들어 연동해주도록 하자. 다음과 같이 코드를 작성하면 된다. DB에 연결하기 위하여 URL 주소값, ID, 비밀번호가 필요하므로 초기화해준다.

**JdbcCon.java**
```java
public class JdbcCon {

	public static void main(String[] args) {

		Connection con = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 클래스 못찾을 경우를 대비해 ClassNotFoundException 예외 처리함.
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String id = "scott";
			String pw = "TIGER";
			
			// DB 연결 시도하는 코드 (주소,아이디,비밀번호)
			con = DriverManager.getConnection(url,id,pw);
			// 실패시 DB 연결이 잘 안된 것이다.
			// 이 코드 때문에 SQLException 예외 처리가 필요하다.
			
			System.out.println("DB 연결 성공");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DB 연결 실패");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("DB 사용 불가");
		} 
		
	}

}
```

![image](https://user-images.githubusercontent.com/84966961/124694734-cf9a3400-df1c-11eb-9ac3-9a0063204d14.png)


 데이터베이스를 연동해서 사용할때 CRUD 만 가능하다.
 Create, Read, Update, Delete


<br><br>
<hr>

 ### 연동하여 dept 정보 받아오기

**JdbcCon**
```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
			con = DriverManager.getConnection(url,id,pw);
			// 실패시 DB 연결이 잘 안된 것이다.
			// 이 코드 때문에 SQLException 예외 처리가 필요하다.
			
			System.out.println("DB 연결 성공"+"\n");
			
			String sql = "select * from dept";
			
			// 명령어 상태를 드라이버로 보낸 것을 psmt 객체에 저장해둠.
			psmt = con.prepareStatement(sql);
			// 쿼리문이 실제로 조회가 되어짐. execute 실행
			rs = psmt.executeQuery();
			
			// 만약 rs 가 null 이면 조회가 이루어지지 않았고, 값이 있다면 정상적으로 쿼리(조회)가 된 것일 것임.
			
			// rs를 그대로 출력하면 다음과 같이 주소가 나옴.
			System.out.println("rx의 주소값 : ");
			System.out.println(rs+"\n");
			// oracle.jdbc.driver.OracleResultSetImpl@431cd9b2
			
			// 컬럼명 설정
			System.out.println("부서번호\t부서명\t\t근무지역");
			
			while (rs.next()) {
				// next 메소드 : 값이 있을 때는 true 리턴, 없을 때는 false 리턴
				// 마치 Iterator의 hasNext()와 같은 기능.
				
				// dept에는 DEPTNO / DNAME / LOC 가 존재함. 
				// 타입 :		int	 / String / String
				int deptno = rs.getInt("deptno");
				String dname = rs.getString("dname");
				String loc = rs.getString("loc");
				// 사용하기 편하기 위하여 변수로 초기화.
				
				System.out.println(deptno+"\t"+dname+"\t"+loc);
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
```

   
<br/><br/>
<hr/>
   
 ### 와일드 카드(?)를 이용해서 쿼리 양식을 만드는 방식

```java
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
```

   
<br/><br/>
<hr/>
   
   
 ### 와일드 카드(?)를 이용해서 쿼리 양식을 만드는 방식 2

 근무 지역은 String이므로 setString 메소드를 사용한다.    

```java
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
```


   
<br/><br/>
<hr/>
   
 ## 연습용 DB table 만들기

1. 기존의 emp01 테이블을 지운다.    
  
 ![image](https://user-images.githubusercontent.com/84966961/124702686-c6649380-df2b-11eb-89ce-7d80682c1f94.png)   
   
  없어서 오류가 뜸.   
  
![image](https://user-images.githubusercontent.com/84966961/124702710-cf556500-df2b-11eb-9611-724e3b2d1fb7.png)   
   
2. emp01 생성   

![image](https://user-images.githubusercontent.com/84966961/124702778-01ff5d80-df2c-11eb-94cc-fe0ec0d91bfa.png)   

![image](https://user-images.githubusercontent.com/84966961/124702902-41c64500-df2c-11eb-97cf-943ee1021a74.png)



<br/><br/>
<hr/>

### 예제

emp01 테이블에서 부서 번호가 30인 사원의 모든 정보를 조회.

```java
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
				if (job.length()>=8) {
					System.out.println(empno + "\t" + ename + "\t" + job + "\t" + mgr + "\t" + hiredate + "\t" + sal + "\t" + comm + "\t" + deptno);					
				} else {
					System.out.println(empno + "\t" + ename + "\t" + job + "\t\t" + mgr + "\t" + hiredate + "\t" + sal + "\t" + comm + "\t" + deptno);					
				}
				
			}
```

**결과 화면**   
![image](https://user-images.githubusercontent.com/84966961/124704110-7b984b00-df2e-11eb-9311-2ed0cc5cc77b.png)




<br/><br/>
<hr/>

### JDBC Insert, update, delete

 Insert를 할 때는 다음과 같이 직접적으로 넣어줄 수 있다.   

```java
			String sqlInsert = "insert into imp01";
			sqlInsert += "values(111,'홍길동','MANAGER',7788,'21/07/07',2000,'',10)";
			
```
   
 하지만 검색 쿼리할 때 처럼 와일드 카드(?)를 사용하여 형식을 받아 직접 넣어줄 수 도 있다.   

```java
			String sqlInsert = "insert into emp01 values(?,?,?,?,?,?,?,?)";
			
			psmt = con.prepareStatement(sqlInsert);
			
			psmt.setInt(1, 1111);
			psmt.setString(2, "홍길동");
			psmt.setString(3, "MANAGER");
			psmt.setInt(4, 7788);
			psmt.setString(5, "21/07/07");
			psmt.setInt(6, 2000);
			psmt.setString(7, "");
			psmt.setInt(8, 10);
```

 JDBC에서 insert, update, delete를 할 때는 `psmt.executeUpdate()` 메소드를 사용하여 변경사항들을 수정한다.

```java
  psmt.executeUpdate();
```

실행하면 다음과 같이 sqldeveloper에서 들어가 있는 것을 확인할 수 있다.

![image](https://user-images.githubusercontent.com/84966961/124705588-de8ae180-df30-11eb-8c5c-0c925994cf45.png)


<br><br>

#### 홍길동 입력 확인

 이클립스 내에서도 확인이 가능하다.


```
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
				if (job.length()>=8) {
					System.out.println(empno + "\t" + ename + "\t" + job + "\t" + mgr + "\t" + hiredate + "\t" + sal + "\t" + comm + "\t" + deptno);					
				} else {
					System.out.println(empno + "\t" + ename + "\t" + job + "\t\t" + mgr + "\t" + hiredate + "\t" + sal + "\t" + comm + "\t" + deptno);					
				}
				
			}
``` 

![image](https://user-images.githubusercontent.com/84966961/124705823-388ba700-df31-11eb-8dea-b684762e1bce.png)



<br><br>
<hr/>

### Transaction의 Commit

   
 트랜잭션(Transaction)을 할 때는 insert, update, delete를 이용하여 수정을 할 때이다. 데이터의 무결성을 위하여 트랜잭션은 수동으로 해주는 것이 좋은데 자바에서는 `Connection con`을 이용하여 수동으로 단위별 트랜잭션 커밋을 할 수 있다.  
 
 **Commit의 수동화**   
			
 setAutoCommit을 false로 하면 Commit이 자동이 아니라 수동으로 해줘야 한다.   
    
```java
  con.setAutoCommit(false);
  
  con.commit();
```


 또한, 이클립스에서 `psmt.executeUpdate()`를 이용하여 업데이트를 했을 때 반환되는 타입은 int이다. 이 때 int에는 쿼리문의 횟수가 반환되어 진다. 그렇기 때문에 우리가 다음 코드에서 실제 업데이트가 성공이 된지는 알 수 없다.   

```java
psmt.executeUpdate();	// insert, update, delete
			
System.out.println("데이터 삽입 성공");
```

 그렇기 때문에 쿼리문이 실행된 횟수를 반환해주는 int값을 이용해 다음과 같이 변경된 정보를 확인 가능하다.   

```java
			int result = psmt.executeUpdate();	
			
			if(result > 0) {
				System.out.println("데이터 삽입 성공");
				con.commit();				
			} else {
				System.out.println("데이터 삽입 실패");
				con.rollback();
			}
```

 실패했을 때는 rollback을 통해서 데이터의 안전성을 보안한다.   
   


<br><br>
<hr/>

### Update : 홍길동 -> 이순신

 다음은 `홍길동`을 `이순신`으로 update한 예제이다.   

![image](https://user-images.githubusercontent.com/84966961/124708690-93bf9880-df35-11eb-9e9a-c931fb0efdfc.png)

```java
			// update
			String sqlUpdate = "update emp01 set ename = '이순신' where empno = ?";
			
			psmt = con.prepareStatement(sqlUpdate);
			psmt.setInt(1, 1111);
			
			int result = psmt.executeUpdate();	 // insert, update, delete  할 때 사용
			
			if(result > 0) {
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
				
				if (job.length()>=8) {
					System.out.println(empno + "\t" + ename + "\t" + job + "\t" + mgr + "\t" + hiredate + "\t" + sal + "\t" + comm + "\t" + deptno);					
				} else {
					System.out.println(empno + "\t" + ename + "\t" + job + "\t\t" + mgr + "\t" + hiredate + "\t" + sal + "\t" + comm + "\t" + deptno);					
				}
				
			}
```



<br><br>
<hr/>

### Delete : 홍길동 -> 이순신

 다음은 이순신을 삭제하기 위한 코드이다. 사원 번호(empno) 가 `1111`인 정보를 골라 삭제하였다.

```java
// delete 이순신 삭제
			String sqlDelete = "delete from emp01 where empno = ? ";
			
			psmt = con.prepareStatement(sqlDelete);
			psmt.setInt(1, 1111);
			
			int resultDelete = psmt.executeUpdate();	 // insert, update, delete  할 때 사용
			
			if(result > 0) {
				System.out.println("데이터 삭제 성공");
				con.commit();				
			} else {
				System.out.println("데이터 삭제 실패");
				con.rollback();
			}
```


**결과 화면**   
   
![image](https://user-images.githubusercontent.com/84966961/124709934-0bda8e00-df37-11eb-8a6d-a7513575a4ef.png)   
   


<br><br>
<hr/>

## 마무리 문제 : 

'[emp01, dept, salgrade]' 테이블을 이용하여 '{사원번호 사원명 급여 부서명 급여등급}'의 컬럼들을 쿼리하여 보이도록 만드시오.

```java
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
```

 단순 쿼리 조회 문제이다. sqlExample 변수만 sql 문법에 맞게 잘 선언만 할 수 있다면 쉽게 풀 수 있다.

**결과 화면**   
   
![image](https://user-images.githubusercontent.com/84966961/124712704-9e306100-df3a-11eb-9b2d-3ab1def5af69.png)








