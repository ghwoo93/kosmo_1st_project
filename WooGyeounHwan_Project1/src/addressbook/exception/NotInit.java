package addressbook.exception;

public class NotInit extends Exception{
	public NotInit() {
		super("초성이 아닙니다");
	}
	
	public NotInit(String message) {
		super(message);
	}
}
