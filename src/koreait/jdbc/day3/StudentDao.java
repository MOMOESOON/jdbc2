package koreait.jdbc.day3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import koreait.jdbc.day2.OracleUtility;

//DAO : Data Access(접근-읽기와 쓰기) Object
//		ㄴ SQL 실행 메소드를 모아 놓은 클래스.

/* StudentDao 의 내용을 요약
 * insert,update 는 sql 파라미터에 전달한 데이터의 타입을 dto
 * delete
 */

public class StudentDao {
	//나중에 db를 쉽게 코딩 하기위한 프레임워크를 사용하면 Exception 처리 안해도 됩니다.
	public int insert(StudentDto student) throws SQLException{
		
		Connection connection = OracleUtility.getConnection();
		
		String sql = "insert into TBL_STUDENT values(?,?,?,?)";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1,student.getStuno() );
		ps.setString(2,student.getName() );
		ps.setInt(3,student.getAge() );
		ps.setString(4,student.getAddress() );
		int result = ps.executeUpdate();
		
		ps.close();
		connection.close();
		return result;
	}
	

	public int update(StudentDto student) throws SQLException{
		Connection connection = OracleUtility.getConnection();
		String sql = "update TBL_STUDENT set age = ? , address = ? where stuno = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ps.setInt(1, student.getAge());
		ps.setString(2, student.getAddress());
		ps.setString(3, student.getStuno());
		int result = ps.executeUpdate();
		ps.close();
		OracleUtility.close(connection);
		
		return result;
	}
	
	public int delete(String stuno) throws SQLException{
		Connection connection  = OracleUtility.getConnection();
		String sql = "DELETE FROM TBL_STUDENT WHERE STUNO = ?";
		PreparedStatement ps =connection.prepareStatement(sql);
		
		ps.setString(1, stuno);
		int result = ps.executeUpdate();
		
		ps.close();
		connection.close();
		
		return result;
	}
	public StudentDto select(String stuno) throws SQLException {
		Connection connection = OracleUtility.getConnection();
		String sql = "select * from TBL_STUDENT where stuno = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ps.setString(1, stuno);
		ResultSet rs = ps.executeQuery();
		StudentDto result = null;
		if(rs.next());{
			//String stuno2 = rs.getString(1);
			String name = rs.getString(2);
			int age = rs.getInt(3);
			String address = rs.getString(4);
			result = new StudentDto(stuno, name, age, address);
		}
		return result;
		 
		
		
	}
	public List<StudentDto> selectAll() throws SQLException{
		Connection connection = OracleUtility.getConnection();
		String sql = "select * from TBL_STUDENT";
		PreparedStatement ps = connection.prepareStatement(sql);
		
		List<StudentDto> results= new ArrayList<>();
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			StudentDto dto = new StudentDto(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4));
			results.add(dto);
		}
		return results;
	}
}
	//select *from TBL_STUDENT where stuno = '2023002'; 실행을위한
	//   ㄴ selectOne 메소드 정의
	

