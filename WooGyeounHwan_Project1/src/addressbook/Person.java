package addressbook;

import java.io.Serializable;

public class Person implements Serializable {
	//[멤버변수]
	public String name;
	public int age;
	//[기본 생성자]
	public Person() {}
	//[인자 생성자]
	public Person(String name, int age) {		
		this.name = name;
		this.age = age;
	}
	//[멤버 메소드]
	public String get() {
		return String.format("이름:%s,나이:%s",name,age);
	}
	public void print() {
		System.out.println(get());
	}
	
}
