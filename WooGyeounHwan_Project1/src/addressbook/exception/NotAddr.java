package addressbook.exception;

public class NotAddr extends Exception{
	public NotAddr() {
		super("나이가 아닙니다");
	}
	
	public NotAddr(String message) {
		super(message);
	}
}
