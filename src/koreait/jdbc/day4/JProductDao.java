package koreait.jdbc.day4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import koreait.jdbc.day2.OracleUtility;

public class JProductDao {

	private static void JProduct() {
		
	}
	public List<JProduct> selectByPname(String pname) throws SQLException{
		Connection connection = OracleUtility.getConnection();
		String sql = "select * from j_PRODUCT where pname like '%' || ? || '%' ";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, pname);
		ResultSet rs = ps.executeQuery();
		
		List<JProduct> list = new ArrayList<>();
		while(rs.next()) {
			list.add(new JProduct(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
			
		}
		return list;
		
	}
	public List<JProduct> selectAll() throws SQLException{
		Connection connection = OracleUtility.getConnection();
		String sql = "select * from j_PRODUCT";
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		List<JProduct> list = new ArrayList<>();
		while(rs.next()) {
			list.add(new JProduct(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
		}
		return list;
		
	}
	
}

