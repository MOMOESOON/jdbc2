package koreait.jdbc.day2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentSelectAllMenu {

	public static void main(String[] args) {
		Connection conn = OracleUtility.getConnection();
		
		System.out.println(":::::::::::::모든 학생을 조회 하는 메뉴:::::::::::::");
		StudentSelectAllMenu(conn);
		OracleUtility.close(conn);

	}
	private static void StudentSelectAllMenu(Connection conn) {
		String sql = "select * from tbl_student";
		try(
				PreparedStatement ps = conn.prepareStatement(sql);
				){
			ResultSet rs = ps.executeQuery();		//select 실행하기
			while(rs.next()) {
				System.out.printf("학번:%s,이름:%s,나이:%d,주소:%s\n",rs.getString(1),
						rs.getString(2),rs.getInt(3),rs.getString(4));
			}
				
				
			
			
			}
			catch(SQLException e) {
				System.out.println("데이터 조회에 문제가 생겼습니다. 상세내용- "+ e.getMessage());
				//결과 집합을 모두 소모했음 -> 조회 결과가 없는데 rs.getxxxx 메소드 실행할 때의 예외 메시지.
			}
			
		}
	}