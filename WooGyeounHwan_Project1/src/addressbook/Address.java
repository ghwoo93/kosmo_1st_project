package addressbook;

import java.io.Serializable;

import common.utility.CommonUtilities;
//주소를 상속받는 도로명주소와 지번주소
//빌더 패턴 추가
public class Address implements Comparable<Address>,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3379715247003786044L;
	//1]멤버 변수는 private으로
	//필수 항목
	private String name;
	private String address;
	//선택 항목
	private String contact;
	private int age;
	
	//6]내부 정적 클래스 타입(Builder)을 인자로 받는 생성자 정의
	public Address(Builder builder) {
		this.name = builder.name;
		this.age = builder.age;
		this.address = builder.address;
		this.contact = builder.contact;
	}
	//2]내부 정적 클래스
	static class Builder{
		//3]외부 클래스와 똑같은 멤버변수를 갖는다
		//필수 항목
		private String name;
		private String address;
		//선택 항목
		private String contact;
		private int age;
		
		//4]내부 클래스 인자생성자(필수항목만 받는 생성자]
		public Builder(
				String name,String address) {
			this.name=name;
			this.address=address;
		}
		//5]멤버변수(선택항목)를 초기화 하는 세터(반환타입은 Builder)
		public Builder setContact(String contact) {
			this.contact=contact;
			return this;
		}
		public Builder setAge(int age) {
			this.age=age;
			return this;
		}
		//7]외부 클래스 타입(Member)을 반환하는  메소드 
		public Address build() {
			return new Address(this);
		}
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String get() {
		return String.format(
				"[초성:%s, 이름:%s, 나이:%s, 주소:%s, 연락처:%s]",
				CommonUtilities.getFirstCharacter(name),
				name,age,address,contact);
	}
	
	@Override
	public String toString() {
		return get();
	}

	@Override
	public int compareTo(Address target) {
		return this.name.compareTo(target.name);
	}

	
}
