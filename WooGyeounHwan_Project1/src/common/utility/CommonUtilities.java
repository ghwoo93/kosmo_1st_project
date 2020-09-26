package common.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtilities {
	
	public static boolean isNumber(String value) {
		for(int i=0;i<value.length();i++) {
			/*
			if( !(Character.isDigit(Character.codePointAt(value, i) ) ) ){
				return false;
			}*/
			if(!(value.codePointAt(i)>='0'&&value.codePointAt(i)<='9')) {
				return false;
			}
		}
		return true;
		
//		for(int i=0;i<value.length();i++) {
//			int codeValue = Character.codePointAt(value, i);
//			if(!(codeValue>='0'&&codeValue<='9'))
//				return false;
//		}
//		return true;
	}
	
	//두 날짜 차이를 반환하는 메소드]
	//반환 타입:long
	//매개 변수:String타입의 두 날짜,날짜 패턴,구분자(단위)
	public static long getDifferenceDates(
			String stFDate,
			String stSDate,
			String pattern,
			char delim) throws ParseException {
		//1]매개 변수에 저장된 pattern으로 SimpleDateFormat객체 생성
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		//2]
		Date fDate = dateFormat.parse(stFDate);
		Date sDate = dateFormat.parse(stSDate);
		//3]
		long fTime = fDate.getTime();
		long sTime = sDate.getTime();
		//4]
		long diff = sTime-fTime;
		//5]
		switch(Character.toUpperCase(delim)) {
			case 'D':return diff/(24*60*60*1000);
			case 'H':return diff/(60*60*1000);
			case 'M':return diff/(60*1000);
			default: return diff/(1000);
		}
	}
	//이름에서 초성을 구해서 초성을 반환하는 메소드
	public static char getFirstCharacter(String name) {
		//김길동 > ㄱ 박길동 > ㅂ 홍길동 > ㅎ
		Character init = name.trim().toCharArray()[0];
		//방법1
		char[] startChar = 
			{'가','나','다','라','마','바','사','아','자','차','카','타','파','하'};
		char[] endChar = 
			{'낗','닣','띻','맇','밓','빟','앃','잏','찧','칳','킿','팋','핗','힣'};
		char[] returnChar = 
			{'ㄱ','ㄴ','ㄷ','ㄹ','ㅁ','ㅂ','ㅅ','ㅇ','ㅈ','ㅊ','ㅋ','ㅌ','ㅍ','ㅎ'};
		for(int i=0;i<startChar.length;i++) {
			if(init>=startChar[i] && init<=endChar[i])
				return returnChar[i];
		}
		return '0';
	}

	public static boolean isInit(String input) {
		char cha = input.charAt(0);
		char[] startChar = 
			{'가','나','다','라','마','바','사','아','자','차','카','타','파','하'};
		char[] endChar = 
			{'낗','닣','띻','맇','밓','빟','앃','잏','찧','칳','킿','팋','핗','힣'};
		char[] returnChar = 
			{'ㄱ','ㄴ','ㄷ','ㄹ','ㅁ','ㅂ','ㅅ','ㅇ','ㅈ','ㅊ','ㅋ','ㅌ','ㅍ','ㅎ'};
		for(int i=0;i<startChar.length;i++) {
			if(cha>=startChar[i] && cha<=endChar[i])
				return true;
		}
		return false;
	}
}
