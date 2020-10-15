package addressbook;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

import addressbook.panel.MyFrame;

public class AddressBookApp {
	public static void main(String[] args) throws NumberFormatException, IOException {
		AddressBookLogic logic = AddressBookLogic.getInstance();
//		while(true) {
//			logic.printMainMenu();
//			int inputMenuNum = logic.getMenuNumber();
//			logic.separateMainMenu(inputMenuNum);
//		}
		new MyFrame();
//		logic.loadAddr();
	}
}
