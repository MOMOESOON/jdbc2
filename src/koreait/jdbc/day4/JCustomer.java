package koreait.jdbc.day4;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Getter
@ToString
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class JCustomer {		//DTO : JBuy , JProduct
	
	private String customid;
	private String name;
	private String email;
	private int age;
	private Date regDate;
}
