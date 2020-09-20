package addressbook;
//내부 정적 클래스: 빌더 패턴(클래스  설계시 디자인 패턴의 하나)으로 객체 생성에 사용
/*
* 빌더 패턴:
* 객체 생성과 관련된 디자인패턴
* 인자가 많은 객체를 생성할때 유리
*/
class Member{
	//1]멤버 변수는 private으로
	//필수 항목]
	private String id;
	private String name;
	//선택 항목]
	private String tel;
	private String addr;
	
	@Override
	public String toString() {
		return "회원[아이디=" + id + ",이름=" + name + ",연락처=" + tel + ",주소=" + addr + "]";
	}
	
	//6]내부 정적 클래스 타입(Builder)을 인자로 받는 생성자 정의
	public Member(Builder builder) {
		//멤버변수 초기화]
		this.id = builder.id;
		this.name = builder.name;
		this.tel= builder.tel;
		this.addr = builder.addr;
	}
	
	//2]내부 정적 클래스
	static class Builder{
		//3]외부 클래스와 똑같은 멤버변수를 갖는다
		//필수 항목]
		private String id;
		private String name;
		//선택 항목]
		private String tel;
		private String addr;
		//4]내부 클래스 인자생성자(필수항목만 받는 생성자]
		public  Builder(String id,String name) {
			this.id=id;
			this.name=name;
		}

		//5]멤버변수(선택항목)를 초기화 하는 세터(반환타입은 Builder)
		public Builder setAddr(String addr) {
			this.addr=addr;
			return this;
		}
		public Builder setTel(String tel) {
			this.tel=tel;
			return this;
		}
		//7]외부 클래스 타입(Member)을 반환하는  메소드 
		public Member build() {
			return new Member(this);
		}
		
	}//////////////////
}
public class InnerStaticBuilderApp {

	public static void main(String[] args) {
		//필수항목만 갖고 객체 생성
		Member member1 = new Member.Builder("KIM", "김길동").build();
		System.out.println(member1);
		
		Member member2 = new Member.Builder("LEE", "이길동")
						.setTel("010-1234-5678")
						.build();
		System.out.println(member2);
		
		Member member3 = new Member.Builder("PARK", "박길동")
				.setTel("010-1234-5678")
				.setAddr("가산동")
				.build();
		System.out.println(member3);
	}//////////////main

}/////////////////class
