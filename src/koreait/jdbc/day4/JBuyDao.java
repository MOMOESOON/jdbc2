package koreait.jdbc.day4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import koreait.jdbc.day2.OracleUtility;

//DAO 에는 입력과 출력은 포함시키지 않습니다. 오직 어떤 형식의 데이터를 받아서
//어떤 SQL을 실행하여, 어떤 값을 리턴할 것인가를 중점을 두고 정의하면 됩니다.
//DTO는 데이터를 저장하는 목적의 클래스, DAO는 어떤 동작을 할
public class JBuyDao {		

	
			//*트랜잭션을 처리하는 예시 : auto commit 을 해제하고 직접 commit을 합니다.
			//try catch 를 직접하세요.throws 아닙니다.
			public int insertMany(List<JBuyDto> carts) {
				Connection conn = OracleUtility.getConnection();
			//5. 상품 구매(결제)하기 - 장바구니의 데이터를 `구매` 테이블에 입력하기 (여러개 insert)	
				String sql ="insert into j_buy \n" + 
						"values (jbuy_seq.nextval , ? ,?,?,sysdate)";
				int count = 0;
				PreparedStatement ps = null;
				try {
					conn.setAutoCommit(false);			//auto commit 설정 - false
					ps = conn.prepareStatement(sql);
					for(JBuyDto b : carts) {
						ps.setString(1,b.getCustomid());
						ps.setString(2,b.getPcode());
						ps.setInt(3,b.getQuantity());
						count += ps.executeUpdate();
					}
					conn.commit();				//커밋
				}catch (SQLException e) {
					System.out.println("장바구니 상품 구매하기 예외 : " + e.getMessage());
					System.out.println("장바구니 상품 구매를 취소합니다.");
					try {
						conn.rollback();		//롤백
					} catch (SQLException e1) {
					}
				}
				return count;
			}
			public List<MyPageBuy> mypageBuy(String customid) throws SQLException{
				Connection  connection = OracleUtility.getConnection();
				String sql ="select buy_date , p.pcode , pname, quantity, price, quantity*price total from j_buy b\r\n"
						+ "join j_product p\r\n"
						+ "on p.pcode=b.pcode\r\n"
						+ "and b.custom_id= ?\r\n"
						+ "order by buy_date desc";
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setString(1, customid);
				ResultSet rs = ps.executeQuery();
				List<MyPageBuy> list = new ArrayList<>();
				while(rs.next()) {
					list.add(new MyPageBuy(rs.getDate(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5),rs.getLong(6)));
				}
				ps.close();
				connection.close();
				return list;
				
			}
			public long myMoney(String customid) throws SQLException {
				Connection connection = OracleUtility.getConnection();
				String sql ="select sum(total) from mypage_buy where custom_id = ?";
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setString(1, customid);
				
				ResultSet rs =ps.executeQuery();
				long total = 0l;
				while(rs.next()) {
					total= rs.getLong(1);
				}
				return total;
				
			}
			
			
			
			public int insert(JBuyDto buy) {
				// TODO Auto-generated method stub
				return 1;
			}
			public JBuyDto selectOne(int buyseq) throws SQLException{
				Connection conn = OracleUtility.getConnection();
				String sql = "select * from j_buy where buy_seq = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, buyseq);
				JBuyDto buy = null;
				ResultSet rs = ps.executeQuery();
				if(rs.next())
					buy = new JBuyDto(rs.getInt(1), rs.getNString(2), rs.getString(3), rs.getInt(4), rs.getDate(5));
				
				return null;
			}
			
			
			}
		
	

