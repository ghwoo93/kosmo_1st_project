package addressbook.exception;

public class NameException extends Exception{
	public NameException() {
		super("이름이 아닙니다");
	}
	
	public NameException(String message) {
		super(message);
	}
}
