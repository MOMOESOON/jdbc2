package koreait.jdbc.day3;

//메소드의 매개변수 또는 리턴 타입으로 사용하기위해
//테이블의 커럼 구성과 같은 클래스를 정의하여 값을 저장하기 위한 것입니다.
//VO: Value Object 는 불변객체로 equals 와 hashcode 재정의를 하여 set 또는 map 객체에서 활용,
public class StudentDto {
	private String stuno;
	private String name;
	private int age;
	private String address;
	
	public StudentDto() {
}
	
	public StudentDto(String stuno, String name, int age, String address) {
		
		this.stuno = stuno;
		this.name = name;
		this.age = age;
		this.address = address;
	}


	public String getStuno() {
		return stuno;
	}

	public void setStuno(String stuno) {
		this.stuno = stuno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		
		return "StudentDto [stuno=" + stuno + ", name=" + name + ", age=" + age + ", address=" + address+ "]";
	}
}	