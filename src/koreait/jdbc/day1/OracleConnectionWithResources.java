package koreait.jdbc.day1;

import java.sql.Connection;
import java.sql.DriverManager;

// 다른 DBMS 클라이언트 프로그램과 같이 DB를 사용할 수 있는 동작을 구현.
// 제일 먼저 해야할 것은 '데이터베이스 연결'
//
public class OracleConnectionWithResources {

	public static void main(String[] args) {
		
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String driver = "oracle.jdbc.driver.OracleDriver";
		String  user = "iclass";
		String password = "0419";
		
		//try with resources 형식 : try에 자원객체 선언하기
		try (	
			Connection conn= DriverManager.getConnection(url,user,password);
			){
			
			//현재 버전에서는 DriverManager 실행시키므로 생략 가능
			//Class.forName(driver);
		
			System.out.println("연결 상태 =" + conn);
			if(conn!=null)
				System.out.println("오라클 데이터베이스 연결 성공!!");
			else
				System.out.println("오라클 데이터베이스 연결 실패!!");
			
		}catch(Exception e) {	
			System.out.println("ClassNotFoundException = 드라이버 경로가 잘못됬습니다.");
			System.out.println("SQLException = url 또는 user 또는 password 가 잘못됬습니다.");
			System.out.println("오류메시지 = " + e.getMessage());
			e.printStackTrace();
			
		}
	}
}

