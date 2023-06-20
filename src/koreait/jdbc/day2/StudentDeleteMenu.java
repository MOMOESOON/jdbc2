package koreait.jdbc.day2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentDeleteMenu {

	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "iclass";
		String password = "0419";
		
		
		System.out.println("::::::::::::::::: 학생 정보 삭제 메뉴 입니다. ::::::::::::::::");
		try (Connection conn = DriverManager.getConnection(url, user, password);) {
			deleteStudent(conn);
			
		} catch (Exception e) {
			System.out.println("오류 메시지 = " + e);
		}
	}
	private static void deleteStudent(Connection connection) throws  SQLException {
		Scanner sc = new Scanner(System.in);
		String stuno;
		String sql ="delete from tbl_student where stuno = ?";
		System.out.println("학생 정보 0000은 삭제 취소 입니다.");
		
		stuno= sc.nextLine();
		if(stuno.equals("0000")) {
		System.out.println("학생 정보 삭제를 취소합니다.");
		return;
		
	}
		System.out.println("삭제할 학번을 입력하세요");
		stuno=sc.nextLine();
		
		try(
				PreparedStatement ps = connection.prepareStatement(sql);
				){
					ps.setString(1, stuno);
							
					int count = ps.executeUpdate();		
					System.out.println("학생 정보 수정" + count + "건이 완료되었씁니다.");
				}catch (SQLException e) {
					System.out.println("데이터 수정에 문제가 생겼습니다. 상세내용-" + e.getMessage());
				}

	}
}
