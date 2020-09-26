package addressbook.exception;

public class NotAge extends Exception{
	public NotAge() {
		super("나이가 아닙니다");
	}
	
	public NotAge(String message) {
		super(message);
	}
}
