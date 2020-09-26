package addressbook.exception;

public class NotContact extends Exception{
	public NotContact() {
		super("나이가 아닙니다");
	}
	
	public NotContact(String message) {
		super(message);
	}
}
