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
	private static int maxVal=50;
	
	private AddressBookLogic() {
		//파일이 존재하는 확인하고
		//파일이 존재한다면 읽어서 생성하고 addrbook에 참조
		//파일이 없으면 자료구조 생성
		addressBook = new HashMap<Character, List<Address>>();
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
	public void saveAddressBook() {
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
	public void setAddress(
			String inName,String inAddr,String inAge,String inContact) {
		//정원이 찼는지 여부 판단
		if(addressBook.size()==maxVal) {
			System.out.println("주소록이 가득 찼습니다.");
			return;
		}
		//값 입력받고 유효성 검사
		System.out.println("이름을 입력하세요.");
		String name = isName(inName);
		System.out.println("주소를 입력하세요");
		String addr = isAddr(inAddr);
		System.out.println("나이");
		int age = isAge(inAge);
		System.out.println("연락처");
		String con = isContact(inContact);
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
	private String isName(String inputName) {
		boolean isCorrect = false;
		String name = "";
		while(!isCorrect) {
			try {
				name = inputName;
				isCorrect = Pattern.matches("^[가-힣]*$", name);
				if(isCorrect&&!name.equals(null)) break;
				else System.out.println("이름은 한글만 입력");
			} catch (Exception e) {
				System.out.println();
			}
		}
		return name;
	}
	private int isAge(String inputAge) {
		boolean isCorrect = false;
		int age = -1;
		while(!isCorrect) {
			try {
				String strInt = inputAge;
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
	private String isAddr(String inputAddr) {
		boolean isCorrect = false;
		String addr = "";
		while(!isCorrect) {
			try {
				addr = inputAddr;
				isCorrect = Pattern.matches("^[가-힣]*$", addr);
				if(isCorrect&&!addr.equals(null)) break;
				else System.out.println("주소는 한글만 입력");
			} catch (Exception e) {
				System.out.println("주소는 한글만 입력");
			}
		}
		return addr;
	}
	private String isContact(String inputContact) {
		boolean isCorrect = false;
		String con = "";
		while(!isCorrect) {
			try {
				con = inputContact;
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
	public Map<Character,List<Address>> getAddrBook(){
		return addressBook;
	}
	//3.수정 
	public void updateAddr(Address address) {
		//유효성 검사하고 업데이트
		address.setName(isName(address.getName()));
		address.setAge(isAge(Integer.valueOf(address.getAge()).toString()));
		address.setAddress(isAddr(address.getAddress()));
		address.setContact(isContact(address.getContact()));

	}
	//4.삭제 
	//Map<Character,List<Address>>
	public Address deleteAddr(String name) {
		//주소찾고
		Address address = searchByName(name);
		char key = CommonUtilities.getFirstCharacter(address.getName());
		return addressBook.get(key).remove(addressBook.get(key).indexOf(address));
	}
	//이름으로 검색
	public Address searchByName(String name) {
		System.out.println("성함을 알려주세요");
		while(true) {
			String inputName = isName(name);
			char key = CommonUtilities.getFirstCharacter(inputName);
			for (Address address : addressBook.get(key)) {
				if(address.getName().equals(inputName)) 
					return address;
			}
			System.out.println("존재하지 않는 이름이에요. 다시입력해주세요.");
		}
	}
	//5.검색 
	public List<Address> searchAddrBook(Object searchColumn,String searchTf) throws Exception {
		//번호받아서 속성별로 검색
		int input=0;
		if(searchColumn.equals("초성")) input=1;
		else if(searchColumn.equals("이름")) input=2;
		else if(searchColumn.equals("주소")) input=3;
		else if(searchColumn.equals("나이")) input=4;
		else if(searchColumn.equals("연락처")) input=5;
		
		List<Address> resultList = new Vector<Address>();
		
		switch(input) {
			case 1://초성
				char fInit;
//				if(CommonUtilities.isInit(searchTf)) {
					fInit=searchTf.charAt(0);
					resultList = addressBook.get(fInit);
					Collections.sort(resultList);
					for(Address address : resultList) {
						System.out.println(address);
//						resultList.add
					}
					break;
//				}else {
//					throw new Exception("한글 초성이 아닙니다");
//				}
			case 2://이름
				//이름 유효성 체크
				Set<Character> keys = addressBook.keySet();
				for (Character key : keys) {
					for (Address address : addressBook.get(key)) {
						if(address.getName().equals(searchTf))
							resultList.add(address);
					}
				}
				break;
			case 3://주소
				//주소 유효성 체크
				keys = addressBook.keySet();
				for (Character key : keys) {
					for (Address address : addressBook.get(key)) {
						if(address.getAddress().equals(searchTf))
							resultList.add(address);
					}
				}
				break;
			case 4://나이
				//나이 유효성 체크
				int age = Integer.parseInt(searchTf);
				keys = addressBook.keySet();
				for (Character key : keys) {
					for (Address address : addressBook.get(key)) {
						if(address.getAge()==age)
							resultList.add(address);
					}
				}
				break;
			case 5://연락처
				//연락처 유효성 체크
				keys = addressBook.keySet();
				for (Character key : keys) {
					for (Address address : addressBook.get(key)) {
						if(address.getContact().equals(searchTf))
							resultList.add(address);
					}
				}
				break;
		}
		return resultList;
	}

	//9.종료
	public void exitAddrBook() {
		System.out.println("프로그램을 종료합니다");
		System.exit(0);
	}
}