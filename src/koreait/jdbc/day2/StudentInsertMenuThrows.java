package koreait.jdbc.day2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentInsertMenuThrows {
	String str;
	int num;

	public static void main(String[] args) {
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "iclass";
		String password = "0419";
		
		System.out.println("::::::::::::::::::::학생 등록 메뉴입니다.::::::::::::::::::::");

		try (Connection conn = DriverManager.getConnection(url, user, password);) {
			insertStudent(conn);
			
		} catch (Exception e) {
			System.out.println("오류 메시지 = " + e);
		}

	}// main

	private static void insertStudent(Connection connection) throws  SQLException {
		Scanner sc = new Scanner(System.in);
		
		String sql = "insert into TBL_STUDENT values(?,?,?,?)";
		String name,stuno,adress,temp;
		int age;
		int count =0;
		PreparedStatement ps = connection.prepareStatement(sql);
		while (true) { 
			System.out.println("학생번호 입력시 0000입력은 종료입니다.");
			System.out.print("학번을 입력하세요 >>> ");
			stuno = sc.nextLine();

			if (stuno.equals("0000")) {
				System.out.println("학생 등록을 중지합니다.");
				
				break;
			}
			

			System.out.print("이름을 입력하세요 >>> ");
			name = sc.nextLine();
			

			System.out.print("나이을 입력하세요(10이상, 30세 이하) >>> ");
			temp=sc.nextLine();
												//sc.nextLine();

			System.out.print("주소를 입력하세요 >>> ");
			adress = sc.nextLine();
			try {
				age= Integer.parseInt(temp);
				ps.setString(1, stuno);
				ps.setString(2, name);
				ps.setInt(3, age);
				ps.setString(4, adress);
				ps.execute();
				
			}catch(SQLException e) {
				System.out.println("잘못된 데이터 입력입니다. 다시 입력하세요");
			}catch(NumberFormatException e) {
				System.out.println("나이 입력이 잘못 되었습니다. 정수값을 입력해주세요");
			}

			System.out.println("------------------------------------------------");
		}//while
		ps.close();
		sc.close();
		System.out.println("입력한 학생정보가 성공적으로 등록되었습니다..");

	}
}
