package edu.kh.jdbc.common;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class CreateXMLFile {
	public static void main(String[] args) {
	
		// XML ( extensible markup language) : 단순화된 데이터 기술 형식
		// <> == <태그> 안에 의미를 부여하겠다
		
		// XML에 저장되는 데이터 형식은 MAP : <K:V> 형식이다.
		// Map<String, String>
		// Key,Value 모두 String(문자열) 형식
		
		// Properties 컬렉션 객체
		// = Map의 후손 클래스
		// Key, Value 모두 String(문자열) 형식
		// XML 파일을 읽고 쓰는데 특화된 메서드를 제공
		
//------------------------------------------------------------
		
		try {
			
		Scanner sc = new Scanner(System.in);
		
		// Properties 객체 생성
		Properties prop = new Properties();
		
		System.out.println("생성할 파일 이름 : ");
		String fileName = sc.nextLine();
		
		// 내가 만들어서 내보내니 OutPut
		
		// FileOutputSteam 생성
		// 파일명.xml 과 같이 확장자까지 붙도록 생성할것임
		
		// (); 에 파일명을 집어넣는 형식
			
			FileOutputStream fos = new FileOutputStream(fileName + ".xml");
			
		// Properties 객체를 이용하여 xml 파일 생성
		// xml파일로 저장하겠다. 
			prop.storeToXML(fos, fileName + ".xml file");
			
			System.out.println(fileName + ".xml file 생성 완료");
			
		//IOException이 최상위니까 바로 사용
			} catch (IOException e) {
			e.printStackTrace();
			}
	}
}