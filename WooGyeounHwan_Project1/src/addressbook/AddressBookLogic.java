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

import addressbook.exception.NotAddr;
import addressbook.exception.NotAge;
import addressbook.exception.NotContact;
import addressbook.exception.NotInit;
import addressbook.exception.NameException;
import common.utility.CommonUtilities;

/*
 * 최대값은 수동입력으로하고 맨처음에 언어 입력을 받아서 언어 다르게
 */
public class AddressBookLogic {
	private static Map<Character,List<Address>> addressBook;
	private static Scanner sc;
	private static int maxVal=50;
	
	private AddressBookLogic() {
		addressBook = new HashMap<Character, List<Address>>();
		getAddressBook();
	}
	
	@SuppressWarnings("unchecked")
	private void getAddressBook() {
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(
						new FileInputStream(
								"src/addressbook/Address.dat"));
			addressBook=
					(Map<Character,List<Address>>)ois.readObject();
		} catch (FileNotFoundException e) {
			e.getMessage();
		} catch (ClassNotFoundException e) {
			e.getMessage();
		} catch (IOException e) {
			e.getMessage();
		} finally {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void saveAddressBook() {
		ObjectOutputStream oos=null;
		try {
			oos = new ObjectOutputStream(
						new FileOutputStream(
								"src/addressbook/Address.dat"));
			oos.writeObject(addressBook);
		} catch (FileNotFoundException e) {
			e.getMessage();
		} catch (IOException e) {
			e.getMessage();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static AddressBookLogic getInstance() {
		return new AddressBookLogic();
	}
	
	//_2주소입력
	public void setAddress(
			String inName,String inAddr,String inAge,String inContact) 
					throws NameException, NotAddr, NotAge, NotContact {
		if(addressBook.size()==maxVal) {
			System.out.println("주소록이 가득 찼습니다.");
			return;
		}
		
		Set<Character> keys = addressBook.keySet();
		for (Character key : keys) {
			for (Address address : addressBook.get(key)) {
				if(address.getName().equals(inName))
					throw new NameException("존재하는 이름입니다.");
			}
		}
		
		String name = CommonUtilities.isName(inName);
		String addr = CommonUtilities.isAddr(inAddr);
		int age=0;
		if(!inAge.equals(""))
			age = CommonUtilities.isAge(inAge);
		String con=null;
		if(!inContact.equals(""))
			con = CommonUtilities.isContact(inContact);
		List<Address> addrListByInit = null;
		char key = CommonUtilities.getFirstCharacter(name);
		if(!addressBook.containsKey(key)) {
			addrListByInit = new Vector<Address>();
		}else {
			addrListByInit = addressBook.get(key);
		}
		
		addrListByInit.add(new Address.Builder(name, addr).setAge(age).setContact(con).build());
		addressBook.put(key, addrListByInit);
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
	public void updateAddr(Address address) 
			throws NameException, NotAge, NotAddr, NotContact  {
		//유효성 검사하고 업데이트
		address.setName(
				CommonUtilities.isName(address.getName()));
		address.setAge(
				CommonUtilities.isAge(
						Integer.valueOf(address.getAge()).toString()));
		address.setAddress(
				CommonUtilities.isAddr(address.getAddress()));
		address.setContact(
				CommonUtilities.isContact(address.getContact()));

	}
	//4.삭제 
	//Map<Character,List<Address>>
	public Address deleteAddr(String name) throws NameException   {
		//주소찾고
		Address address = searchByName(name);
		if(!address.equals(null)) {
			char key = 
					CommonUtilities.getFirstCharacter(
							address.getName());
			return addressBook.get(key).remove(
					addressBook.get(key).indexOf(address));
		}else throw new NameException("존재하지 않는 이름입니다");
	}
	//이름으로 검색
	public Address searchByName(String name) throws NameException {
		String inputName = CommonUtilities.isName(name);
		char key = CommonUtilities.getFirstCharacter(inputName);
		for (Address address : addressBook.get(key)) {
			if(address.getName().equals(inputName)) 
				return address;
		}
		throw new NameException("존재하지 않는 이름입니다");
	}
	//5.검색 
	public List<Address> searchAddrBook(
			Object searchColumn,String searchTf) 
					throws NotInit, NameException, NotAddr, NotAge, NotContact  {
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
				fInit=searchTf.charAt(0);
				CommonUtilities.isInit(fInit);
				resultList = addressBook.get(fInit);
				Collections.sort(resultList);
				break;
			case 2://이름
				//이름 유효성 체크
				CommonUtilities.isName(searchTf);
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
				CommonUtilities.isAddr(searchTf);
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
				CommonUtilities.isAge(searchTf);
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
						if(address.getContact()!=null&&address.getContact().equals(searchTf)) {
							resultList.add(address);
						}
					}
				}
				break;
		}
		return resultList;
	}

	//9.종료
	public void exitAddrBook() {
		System.exit(0);
	}
}