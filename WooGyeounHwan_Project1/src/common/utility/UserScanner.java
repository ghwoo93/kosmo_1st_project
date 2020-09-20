package common.utility;

import java.util.Scanner;

public class UserScanner {
	
	private UserScanner() {}
	
	private static class LazyHolder {
		public static final Scanner sc = new Scanner(System.in); 
	}

	public static String getInstance() {
		return LazyHolder.sc.nextLine();
	}
	
	//
	public void closeSc(){
		LazyHolder.sc.close();
	}
	
}
