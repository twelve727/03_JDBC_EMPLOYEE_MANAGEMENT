package edu.kh.jdbc.common;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class LoadXMLFile {

	public static void main(String[] args) {

		
		
		// XML 파일 읽어오기(Properties, FileInputStream)
		
		try {
			Properties prop = new Properties(); // Map<String, String>
			
			//  driver.xml 파일을 읽어오기 위한 InputStream 객체 생성
			FileInputStream fis = new FileInputStream("driver.xml");
			
			// 연결된 driver.xml 파일에 있는 내용을 모두 읽어와
			// Properties 객체에 k:v 형식으로 저장
			prop.loadFromXML(fis);
			
			System.out.println(prop);
			
			// Property : 속성(데이터)
			// prop.getProperty("key") : key가 일치하는 속성(데이터)를 얻어옴
			
			String driver = prop.getProperty("driver");
			String url = prop.getProperty("url");
			String user = prop.getProperty("user");
			String pw = prop.getProperty("password");
					
			// 디버깅
			// 값을 한 눈에 확인한다.
			// 행 줄에 더블클릭 -> o 생성 -> 실행 옆 벌레모양
			
			System.out.println();
			
			// driver.xml 파일에서 읽어온 값들을 이용해 Connection 생성
			Class.forName(driver); // "oracle.jdbc.driver.OracleDriver"
			Connection conn = DriverManager.getConnection(url, user, pw);
			
			System.out.println(conn);
			
//--------------------------------------------------------------------------
			
			
			/* 왜 xml 파일을 이용해서 DB 연결 정보를 읽어와야 하나?
			 * 
			 * 1. 코드 중복 제거
			 * 2. 유지보수 수정 용이
			 
			 * 3. !! 재 컴파일 진행하지 않기 위해서 !!
			  
				 *  코드가 길수록 컴파일에 소요되는 시간이 크다
				 *  코드 수정으로 인한 컴파일 소요시간 없애기 위함
				 * (파일 내용 읽어오는 코드만 작성해두면,
				 * 	Java 코드 수정 없이 [파일 내용만 수정하면]
				 *	 재 컴파일이 수정되지 않는다.
				 
			 * 4. XML 파일에 작성된 문자열 형태를 그대로 읽어오기 때문에
			  
			 	* SQL 작성시 더욱 편리해짐
			 	* (DAO SELECT문과 반대의 이점으로
			 	* 띄어쓰기 에러를 자동으로 잡아줌)	
		*/
			
//--------------------------------------------------------------------------			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
