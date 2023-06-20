package koreait.jdbc.day2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentSelectOneMenu2 {

	public static void main(String[] args) {
		Connection conn = OracleUtility.getConnection();
		
		System.out.println("::::::::::: 학생을 학번으로 조회하는 메뉴 :::::::::::");
		selecOneStudent(conn);
		
		OracleUtility.close(conn);

	}
	private static void selecOneStudent(Connection conn) {
		Scanner sc = new Scanner(System.in);
		String stuno="2023002";
		String sql = "select * from tbl_student where stuno = ?";
		try(
			PreparedStatement ps = conn.prepareStatement(sql);
			){
			ps.setString(1, stuno);
			ResultSet rs = ps.executeQuery();		//select 실행하기
			System.out.println("rs 객체의 구현 클래스는 " + rs.getClass().getName());
			
		if(rs.next()) {	//주의 : 테이블 컬러므이 구조를 알아야 인덱스를 정할 수 있습니다.
			System.out.println("학번" + rs.getString(1));
			System.out.println("학번" + rs.getString("stuno"));
			System.out.println("이름" + rs.getString(2));
			System.out.println("이름" + rs.getString("name"));
			System.out.println("나이" + rs.getString(3));
			System.out.println("나이" + rs.getString("age"));
			System.out.println("주소" + rs.getString(4));
			System.out.println("주소" + rs.getString("address"));
			System.out.println("조회 결과 세번째 컬럼의 값" + rs.getInt(3));
			System.out.println("조회 결과 네번째 컬럼의 값" + rs.getString(1));
			System.out.println("다음 조회 결과 행이 또 있을까요?" + rs.next());
		}else {
			System.out.println("<<조회 결과가 없습니다. >>");
		}
		}
		catch(SQLException e) {
			System.out.println("데이터 조회에 문제가 생겼습니다. 상세내용- "+ e.getMessage());
		}
	}
}

	


/*
	모든 학생 조회하는 StudentSelectAllMenu 클래스 : 1줄에 1개 행을 출력하세요
	과목명을 입력하면 해당 과목 조회하는 ScoreSelectWithSubject 클래스
		
		
*/		