package koreait.jdbc.day1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

// 다른 DBMS 클라이언트 프로그램과 같이 DB를 사용할 수 있는 동작을 구현.
// 제일 먼저 해야할 것은 '데이터베이스 연결'
//
public class InsertDMLUsingParameter {

	public static void main(String[] args) {
		
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
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
			
			//db연결 완료 후에 sql 실행하기.
			
			String sql = "insert into TBL_STUDENT values (?,?,?,?)";		//insert sql 작성
			
			//PreparedStatement 객체를 생성하면서 실행할 sql을 설정합니다.
			//PreparedStatement 객체는 Connection 객체 메소드로 만듭니다. Connection 구현객체는 dbms 종류에 따라
			//생성되고 PreparedStatement 객체도 그에 따라 구현 객체가 결정됩니다.
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//'prepareStatement() 메소드는 객체를 생성'해서 리턴합니다.
			
			//매개변수에 값 전달하기 : 매개변수의 데이터형식 순서 문자열,문자열,정수,문자열
			//setXXXX 메소드의 첫번째 인자는 매개변수의 인덱스, 두번째 인자는 값입니다. XXXX는 매개변수의 타입. 
			pstmt.setString(1,"2023003");
			pstmt.setString(2,"김땡사");
			pstmt.setInt(3,17);
			pstmt.setString(4,"강원도");
			
			pstmt.execute();
			pstmt.close();
			
		}catch(Exception e) {	
			System.out.println("ClassNotFoundException = 드라이버 경로가 잘못됬습니다.");
			System.out.println("SQLException = url 또는 user 또는 password 가 잘못됬습니다.");
			System.out.println("오류메시지 = " + e.getMessage());
			e.printStackTrace();
			
		}
		//conn.close()를 명시적으로 실행할 필요가 없습니다. 자동 close
	
	}

}
/*
 * Statement 인터페이스는 sql 쿼리 처리와 관련된 방법을 정의합니다.
 * 객체는 sql 쿼리문을 데이터베이스에 전송합니다. Connection 객체를 통해서 만듭니다.
 * 
 * PreparedStatement는 Statement의 자식 인터페이스.
 * 특징은 sql을 먼저 컴파일하고 sql 실행에 필요한 값은 실행할 때 매개변수로 전달하는 방식입니다.
 * 
 * 
 * 
 * 
 */
 

