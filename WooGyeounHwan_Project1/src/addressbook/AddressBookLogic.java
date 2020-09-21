package addressbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;
import java.util.regex.Pattern;

import common.utility.CommonUtilities;

/*
 * 최대값은 수동입력으로하고 맨처음에 언어 입력을 받아서 언어 다르게
 */
public class AddressBookLogic {
	private static Map<Character,List<Address>> addressBook;
	private static Scanner sc;
	private static int maxVal;
	
	private AddressBookLogic() {
		//파일이 존재하는 확인하고
		//파일이 존재한다면 읽어서 생성하고 addrbook에 참조
		//파일이 없으면 자료구조 생성
		addressBook = new HashMap<Character, List<Address>>();
		sc = new Scanner(System.in);
		System.out.println("최대 값을 정하시오");
		maxVal=Integer.parseInt(sc.nextLine());
		getAddressBook();
	}
	
	@SuppressWarnings("unchecked")
	private void getAddressBook() {
		try {
			ObjectInputStream ois = 
					new ObjectInputStream(
							new FileInputStream(
									"src/addressbook/Address.dat"));
			addressBook=
					(Map<Character,List<Address>>)ois.readObject();
		} catch (FileNotFoundException e) {
			e.getMessage();
		} catch (IOException e) {
			e.getMessage();
		} catch (ClassNotFoundException e) {
			e.getMessage();
		}
	}
	//Map<Character,List<Address>> addressBook
	private void saveAddressBook() {
		try {
			ObjectOutputStream oos = 
					new ObjectOutputStream(
							new FileOutputStream(
									"src/addressbook/Address.dat"));
			oos.writeObject(addressBook);
		} catch (FileNotFoundException e) {
			e.getMessage();
		} catch (IOException e) {
			e.getMessage();
		}
		
	}

	public static AddressBookLogic getInstance() {
		return new AddressBookLogic();
	}
	
	//0.메인 메뉴 출력
	public void printMainMenu() {
		System.out.println("====================메인메뉴====================");
		System.out.println("  1.입력 2.출력 3.수정 4.삭제 5.검색 6.파일저장 9.종료");
		System.out.println("==============================================");
		System.out.println("메인 메뉴 번호를 입력하세요?");
	}
	//0_1.서브 메뉴 출력
	public void printSubMenu(int mainMenu) {
		System.out.println("====================서브메뉴====================");
		switch (mainMenu) {
		case 3://수정
			System.out.println("       1.이름 2.나이 3.주소 4.연락처 5.이전");
			break;
		case 5://검색
			System.out.println("      1.초성 2.이름 3.나이 4.주소 5.연락처 6.이전");
			break;
		default:
			System.out.println("메뉴에 없는 번호입니다");
			break;
		}
		System.out.println("==============================================");
		System.out.println("서브 메뉴 번호를 입력하세요");
	}
	//_1.메뉴 분기처리용
	public void separateMainMenu(int inputMenuNum) throws NumberFormatException, IOException {
		switch(inputMenuNum) {
			case 1://입력
				setAddress();
				break;
			case 2://출력
				printAddr();
				break;
			case 3://수정
				while(true) {
					printSubMenu(3);
					int inputSubMenuNum = getMenuNumber();
					try {
						updateAddr(inputSubMenuNum);
						break;
					}catch (Exception e) {
						System.out.println("메뉴는 숫자만 입력하세요");
					}
				}
				break;
			case 4://삭제
				deleteAddr();
				break;
			case 5://검색
				while(true) {
					printSubMenu(5);
					int inputSubMenuNum = getMenuNumber();
					try{
						searchAddrBook(inputSubMenuNum);
						break;
					}catch(Exception e){
						System.out.println("메뉴는 숫자만 입력하세요");
					}
				}
				break;
			case 6://파일 저장
				saveAddressBook();
				break;
			case 9://종료
				exitAddrBook();
				break;
			default://예외
				System.out.println("메뉴에 없는 번호입니다");
				break;
		}
	}

	//1.입력
	//_1메뉴입력
	public int getMenuNumber() {
		int menuNum = -1;
		while(true) {
			try {
				String menu = sc.nextLine();
				menuNum = Integer.parseInt(menu);
				return menuNum;
			} catch (Exception e) {
				System.out.println("메뉴는 숫자만 입력하시오");
			}
		}
	}
	//_2주소입력
	void setAddress() {
		//정원이 찼는지 여부 판단
		if(addressBook.size()==maxVal) {
			System.out.println("주소록이 가득 찼습니다.");
			return;
		}
		//값 입력받고 유효성 검사
		System.out.println("이름을 입력하세요.");
		String name = isName();
		System.out.println("주소를 입력하세요");
		String addr = isAddr();
		System.out.println("나이");
		int age = isAge();
		System.out.println("연락처");
		String con = isContact();
		//Map<Character,List<Address>>
		//1]밸류 타입을 null로 초기화
		List<Address> addrListByInit = null;
		//2]초성 (ㄱ,ㄴ,ㄷ,.....ㅎ)얻기
		char key = CommonUtilities.getFirstCharacter(name);
		//3]맵 컬렉션(addressBook)에 key값이 존재하는지 판단
		if(!addressBook.containsKey(key)) {
			//키값이 없는 경우.즉 맵 컬렉션에 해당 키값이 저장이 안되어 있는 경우
			//value타입인 List<Address>객체 생성
			addrListByInit = new Vector<Address>();
		}else {
			//키값이 존재한다면
			//해당 키값으로 기존에 저장된 객체를 얻어온다
			addrListByInit = addressBook.get(key);
		}
		//주소록에 저장
		//입력한 객체를 추가
		addrListByInit.add(new Address.Builder(name, addr).setAge(age).setContact(con).build());
		//4]맵 컬렉션에 키값으로 저장
		addressBook.put(key, addrListByInit);
	}
	//Address 클래스 멤버 유효성 검사
	private String isName() {
		boolean isCorrect = false;
		String name = "";
		while(!isCorrect) {
			try {
				name = sc.nextLine();
				isCorrect = Pattern.matches("^[가-힣]*$", name);
				if(isCorrect&&!name.equals(null)) break;
				else System.out.println("이름은 한글만 입력");
			} catch (Exception e) {
				System.out.println("이름은 한글만 입력");
			}
		}
		return name;
	}
	private int isAge() {
		boolean isCorrect = false;
		int age = -1;
		while(!isCorrect) {
			try {
				String strInt = sc.nextLine();
				isCorrect = 
						Pattern.matches("^[0-9]*$", strInt);
				if(isCorrect) {
					age = Integer.parseInt(strInt);
					break;
				}else System.out.println("나이는 숫자만 입력");
			} catch (Exception e) {
				System.out.println("나이는 숫자만 입력");
			}
		}
		return age;
	}
	private String isAddr() {
		boolean isCorrect = false;
		String addr = "";
		while(!isCorrect) {
			try {
				addr = sc.nextLine();
				isCorrect = Pattern.matches("^[가-힣]*$", addr);
				if(isCorrect&&!addr.equals(null)) break;
				else System.out.println("주소는 한글만 입력");
			} catch (Exception e) {
				System.out.println("주소는 한글만 입력");
			}
		}
		return addr;
	}
	private String isContact() {
		boolean isCorrect = false;
		String con = "";
		while(!isCorrect) {
			try {
				con = sc.nextLine();
				isCorrect = Pattern.matches("^[0-9]*$", con);
				if(isCorrect&&!con.equals(null)) break;
				else System.out.println("번호는 숫자만 입력");
			} catch (Exception e) {
				System.out.println("번호는 숫자만 입력");
			}
		}
		return con;
	}
	
	//2.출력 
	public void printAddr() {
		Set<Character> keys = addressBook.keySet();
		for (Character cha : keys) {
			for (Address addr : addressBook.get(cha)) {
				System.out.println(addr.toString());
			}
		}
	}
	//3.수정 
	public void updateAddr(int input) {
		//유효성 검사하고 업데이트
		Address address = searchByName();
		switch(input) {
			case 1://이름
				System.out.println("이름을 입력하세요.");
				address.setName(isName());
				break;
			case 2://나이
				System.out.println("나이를 입력하세요.");
				address.setAge(isAge());
				break;
			case 3://주소
				System.out.println("주소를 입력하세요");
				address.setAddress(isAddr());
				break;
			case 4://연락처
				System.out.println("연락처를 입력하세요");
				address.setContact(isContact());
				break;
			case 5://이전
				break;
			default:
				System.out.println("메뉴에 없는 번호입니다");
				break;
		}
	}
	//4.삭제 
	//Map<Character,List<Address>>
	public void deleteAddr() {
		//주소찾고
		Address address = searchByName();
		char key = CommonUtilities.getFirstCharacter(address.getName());
		addressBook.get(key).remove(address);
	}
	//이름으로 검색
	public Address searchByName() {
		System.out.println("성함을 알려주세요");
		while(true) {
			String inputName = sc.nextLine();
			char key = CommonUtilities.getFirstCharacter(inputName);
			for (Address address : addressBook.get(key)) {
				if(address.getName().equals(inputName)) 
					return address;
			}
			System.out.println("존재하지 않는 이름이에요. 다시입력해주세요.");
		}
	}
	//5.검색 
	public void searchAddrBook(int input) {
		//번호받아서 속성별로 검색
		switch(input) {
			case 1://초성
				System.out.println("초성이 어떻게 되나요?");
				char key;
				while(true) {
					try {
						key =sc.nextLine().charAt(0);
						break;
					}catch(Exception e){
						System.out.println("한글 모음만 입력하세요");
					}
				}
				List<Address> resultList = addressBook.get(key);
				Collections.sort(resultList);
				for(Address address : resultList) {
					System.out.println(address);
				}
				break;
			case 2://이름
				System.out.println("이름이 어떻게 되나요?");
				String name = sc.nextLine();
				key = CommonUtilities.getFirstCharacter(name);
				resultList = addressBook.get(key);
				for (Address address : resultList) {
					if(address.getName().equals(name))
						System.out.println(address);
				}
				break;
			case 3://나이
				System.out.println("나이가 어떻게 되나요?");
				int age = Integer.parseInt(sc.nextLine());
				Set<Character> resultSet = addressBook.keySet();
				for (Character cha : resultSet) {
					for (Address address : addressBook.get(cha)) {
						if(address.getAge()==age)
							System.out.println(address);
					}
				}
				break;
			case 4://주소
				System.out.println("사는 곳이 어떻게 되나요?");
				String addr = sc.nextLine();
				key = CommonUtilities.getFirstCharacter(addr);
				resultList = addressBook.get(key);
				for (Address address : resultList) {
					if(address.getAddress().equals(addr))
						System.out.println(address);
				}
				break;
			case 5://연락처
				System.out.println("연락처가 어떻게 되나요?");
				String contact = sc.nextLine();
				key = CommonUtilities.getFirstCharacter(contact);
				resultList = addressBook.get(key);
				for (Address address : resultList) {
					if(address.getContact().equals(contact))
						System.out.println(address);
				}
				break;
			case 6://이전
				break;
			default:
				System.out.println("메뉴에 없는 번호입니다");
				break;
		}
	}
	//6.파일저장
	//Map<Character,List<Address>> 초성 이름 나이 주소 연락처
	public void saveAddr() throws IOException {
		//데이터소스 AddressBookLogic.addressBook
		//데이터목적지 src/addressbook/AddressBook.txt
		FileOutputStream fos = new FileOutputStream("src/addressbook/AddressBook.txt");
		for (Character init : addressBook.keySet()) {
			for (Address address : addressBook.get(init)) {
				fos.write(address.toString().getBytes());
				fos.flush();
			}
		}
		fos.close();
	}
	//_1.파일불러오기
	public void loadAddr() throws IOException {
		//데이터소스 src/addressbook/AddressBook.txt
		//데이터목적지 AddressBookLogic.addressBook
		FileInputStream fis = 
				new FileInputStream("src/addressbook/AddressBook.txt");
		OutputStream out = System.out;
		out.write(fis.read());
		out.flush();
		out.close();
//		Byte.toString();
		
	}
	//9.종료
	public void exitAddrBook() {
		System.out.println("프로그램을 종료합니다");
		System.exit(0);
	}
}