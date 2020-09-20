package addressbook;

import java.io.Serializable;

import common.utility.CommonUtilities;
//주소를 상속받는 도로명주소와 지번주소
//빌더 패턴 추가
public class Address implements Comparable<Address>,Serializable {
	//필수 항목
	String name;
	String address;
	String contact;
	int age;
	
	public Address(
			String name, int age, String address, String contact) {
		this.name = name;
		this.age = age;
		this.address = address;
		this.contact = contact;
	}
	
	public String get() {
		return String.format(
				"[초성:%s, 이름:%s, 나이:%s, 주소:%s, 연락처:%s]",
				CommonUtilities.getFirstCharacter(name),
				name,age,address,contact);
	}
	
	@Override
	public String toString() {
		return String.format(
				"[이름:%s, 나이:%s, 주소:%s, 연락처:%s]", 
				name,age,address,contact);
	}

	@Override
	public int compareTo(Address target) {
		return this.name.compareTo(target.name);
	}
	
}
