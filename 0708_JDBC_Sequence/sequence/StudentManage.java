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
		System.out.println("    SMHRD �л����� ���α׷�    ");
		System.out.println("===========================");
		
		while (true) {
			System.out.println("1.�л��߰� 2.��ü�л���ȸ 3.Ư���л���ȸ 4.�л��������� 5.�л����� 6.���α׷�����");
			System.out.print(" >> ");
			
			menu = sc.nextInt();
			
			switch (menu) {
			
			case 1 :
				System.out.println("����� �л��� ������ �Է��Ͻÿ�.");
				System.out.println("�̸� : ");
				name = sc.next();
				System.out.println("���� : ");
				age = sc.nextInt();
				System.out.println("��ȭ��ȣ : ");
				phone = sc.next();
				System.out.println("�̸��� : ");
				email = sc.next();
				
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					con = DriverManager.getConnection(url,id,pw);
					// ù��° ��ȣ�� sequence���� �ڵ����� �־��ְ� �ȴ�.
					sql = "insert into student values (studentSeq.nextval,?,?,?,?)";
					// ���Է�
					psmt = con.prepareStatement(sql);
					psmt.setString(1, name);
					psmt.setInt(2, age);
					psmt.setString(3, phone);
					psmt.setString(4, email);
					
					// executeUpdate()�� ���ϰ����� ������Ʈ Ƚ���� ������. 0���� ũ�� ������ ó�� ����
					int cnt = psmt.executeUpdate();
					if (cnt>0) {
						System.out.println("�л��߰� ����");
					} else {
						System.out.println("�л��߰� ����");
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					// DB�� ������ �����ϰ� �� �� �ݵ�� ���� ���־�� �Ѵ�. �׷��� ������ ���α׷����� ��� ����� �־ ������� ���ϰ� �ȴ�.
					// ��� ����� �ִٺ��� ���α׷��� ������ ����� �� �ִ�.
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
				System.out.println("=====SMHRD �л� ���=====");
				
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					con = DriverManager.getConnection(url,id,pw);
					
					sql = "select rownum, a.* from student a order by no desc";
					// rownum, * ��� �ϸ� ��� ���̺��� ���������� �ν����� ���ϰ� a��� ��Ī�� �̿��ؼ� a�� ��� Į���� �ҷ����Բ� �ؾ��Ѵ�.
					psmt = con.prepareStatement(sql);
					// �ش� ���� ������ rs �� ����
					rs = psmt.executeQuery();
					
					// while ���� ���� Į���� �Ѱܰ���(rs.next()) rs�� ����� DB�� ��½�����.
					System.out.println("row\t��ȣ\t�̸�\t����\t��ȣ\t\t�̸���");
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
					// ������ �ݴ�� ������Ѵ�.
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
				
			case 3 :	// Ư�� �л� ��ȸ
				System.out.println("=====Ư�� �л� ��ȸ=====");
				System.out.println();
				System.out.println("��ȸ�ϰ� ���� �л��� �̸��� �־��ּ���");
				System.out.print(" >> ");
				search = sc.next();
//				no = sc.nextInt();
				System.out.println();
				
				try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con = DriverManager.getConnection(url,id,pw);
				
				sql = "select rownum, a.* from student a where name = ? order by no desc";
				// rownum, * ��� �ϸ� ��� ���̺��� ���������� �ν����� ���ϰ� a��� ��Ī�� �̿��ؼ� a�� ��� Į���� �ҷ����Բ� �ؾ��Ѵ�.
				
				psmt = con.prepareStatement(sql);
				psmt.setString(1, search);
//				psmt.setInt(1, no);
				
				// �ش� ���� ������ rs �� ����
				rs = psmt.executeQuery();
				
				// isList ������ ���� �ִ��� �������� ���� ��µǴ� ���� �ٸ��� ǥ��.
				boolean isList = false;
				
				// while ���� ���� Į���� �Ѱܰ���(rs.next()) rs�� ����� DB�� ��½�����.
				System.out.println("row\t��ȣ\t�̸�\t����\t��ȣ\t\t�̸���");
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
					System.out.println("�˻��� �л��� �����ϴ�.");
				}
				
				System.out.println();
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				// ������ �ݴ�� ������Ѵ�.
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
				
			case 4 :	//	�л���������
				System.out.println("=====�л� ���� ����=====");
				System.out.println();
				System.out.println("�����ϰ� ���� �л��� ��ȣ�� �־��ּ���");
				System.out.print(" >> ");
				no = sc.nextInt();
				System.out.println();
				
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					con = DriverManager.getConnection(url,id,pw);
					
					sql = "select rownum, a.* from student a where no = ? order by no desc";
					// rownum, * ��� �ϸ� ��� ���̺��� ���������� �ν����� ���ϰ� a��� ��Ī�� �̿��ؼ� a�� ��� Į���� �ҷ����Բ� �ؾ��Ѵ�.
					
					psmt = con.prepareStatement(sql);
					psmt.setInt(1, no);
					// �ش� ���� ������ rs �� ����
					rs = psmt.executeQuery();
					
					// isList ������ ���� �ִ��� �������� ���� ��µǴ� ���� �ٸ��� ǥ��.
					boolean isList = false;
					
					// while ���� ���� Į���� �Ѱܰ���(rs.next()) rs�� ����� DB�� ��½�����.
					System.out.println("row\t��ȣ\t�̸�\t����\t��ȣ\t\t�̸���");
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
						System.out.println("�˻��� �л��� �����ϴ�.\n");
						break;
					}
					
					System.out.println();
					
					System.out.println("�����Ͻ� ������ ������ �ּ���. \n 1.�̸� 2.���� 3.����ó 4.�̸��� 5.��ü���� ");
					System.out.print(" >> ");
					int select = sc.nextInt();
					
					if (select == 1) {
						System.out.print("\n ������ �̸��� �����ּ���.\n >> ");
						name = sc.next();
					} else if (select == 2) {
						System.out.print("\n ������ ���̸� �����ּ���.\n >> ");
						age = sc.nextInt();
					} else if (select == 3) {
						System.out.print("\n ������ ����ó�� �����ּ���.\n >> ");
						phone = sc.next();
					} else if (select == 4) {
						System.out.print("\n ������ �̸��ϸ� �����ּ���.\n >> ");
						email = sc.next();
					} else if (select == 5) {
						System.out.print("\n ������ �̸��� �����ּ���.\n >> ");
						name = sc.next();
						System.out.print("\n ������ ���̸� �����ּ���.\n >> ");
						age = sc.nextInt();
						System.out.print("\n ������ ����ó�� �����ּ���.\n >> ");
						phone = sc.next();
						System.out.print("\n ������ �̸��ϸ� �����ּ���.\n >> ");
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
					
					// executeUpdate()�� ���ϰ����� ������Ʈ Ƚ���� ������. 0���� ũ�� ������ ó�� ����
					int cnt = psmt.executeUpdate();
					if (cnt>0) {
						System.out.println("�л� ���� ���� ����");
					} else {
						System.out.println("�л� ���� ���� ����");
					}
					
					
					// ���� �� Ȯ��
					rs = psmt.executeQuery();
					
					// while ���� ���� Į���� �Ѱܰ���(rs.next()) rs�� ����� DB�� ��½�����.
					System.out.println("row\t��ȣ\t�̸�\t����\t��ȣ\t\t�̸���");
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
				
			case 5 :	//	�л�����
				System.out.println("=====�л� ����=====");
				System.out.println();
				System.out.println("�����ϰ� ���� �л��� ��ȣ �־��ּ���");
				System.out.print(" >> ");
				no = sc.nextInt();
				System.out.println();
				
				try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con = DriverManager.getConnection(url,id,pw);
				
				// ������ ���� sql ��
				sqlDelete = "delete from student where no = ?";
				// ���� �� ����üŷ�� ���� sql ��
				sql = "select rownum, a.* from student a where no = ? order by no desc";
				
				psmt2 = con.prepareStatement(sqlDelete);
				psmt2.setInt(1, no);
				
				psmt = con.prepareStatement(sql);
				psmt.setInt(1, no);
				
				// ���� ����
				int cnt = psmt2.executeUpdate();
				if (cnt>0) {
					System.out.println("�л� ���� ���� ����\n");
				} else {
					System.out.println("�л� ���� ���� ����\n");
				}
				
				// ���� �� ���� �������� �ٽ� �˻� ����üŷ
				rs = psmt.executeQuery();
				
				// isList ������ ���� �ִ��� �������� ���� ��µǴ� ���� �ٸ��� ǥ��.
				boolean isList = false;
				
				// while ���� ���� Į���� �Ѱܰ���(rs.next()) rs�� ����� DB�� ��½�����.
				System.out.println("row\t��ȣ\t�̸�\t����\t��ȣ\t\t�̸���");
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
					System.out.println("�˻��� �л��� �����ϴ�.");
				}
				
				System.out.println();
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				// ������ �ݴ�� ������Ѵ�.
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
				
			case 6 :	//	���α׷�����
				System.out.println("\n���α׷�����");
				return;
			} // switch end
			
		} // while end
	}

}
