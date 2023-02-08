package edu.kh.jdbc1;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.jdbc.driver.OracleDriver;

public class JDBCExample {
	public static void main(String[] args) {
		
		// JDBC : Java에서 DB에 연결(접근)할 수 있게 해주는 Java Programming API
		//												(Java에 기본 내장된 인터페이스)
		// java.sql 패키지에서 제공
		
		// * JDBC를 이용한 애플리케이션을 만들 때 필요한 것
		// 1. Java의 JDBC 관련 인터페이스
		// 2. DBMS(Oracle)
		// 3. Oracle에서 Java 애플리케이션과 연결할 때 사용할
		//    JDBC를 상속 받아 구현한 클래스 모음(ojdbc11.jar 라이브러리)
		//		-> OracleDriver.class (JDBC 드라이버) 이용
		
		// -> 오라클에서 제공하는 Java와 연결하기 위한 라이브러리
		//     (OracleDrivaer 제공)
		
		
		// 1단계 : JDBC 객체 참조 변수 선언 (java.sql패키지의 인터페이스)
		
		Connection conn = null;
		// DB 연결 정보를 담은 객체
		// -> DBMS 타입, 이름, IP, Port, 계정명, 비밀번호 저장 
		// -> DBeaver의 계정 접속 방법과 유사함
		// * Java와 DB 사이를 연결해주는 통로(Stream과 유사함)
		
		
		Statement stmt = null;
		// Connection을 통해
		// SQL 문을 DB에 전달하여 실행하고 
		// 생성된 결과(ResultSet, 성공한 행의 개수) 를 반환(Java)하는 데 사용되는 객체
		
		
		ResultSet rs = null;
		// SELECT 질의 성공 시 반환되는데 
		// 조회 결과 집합을 나타내는 객체
		
		
		try {
			// 2단계 : 참조 변수에 알맞은 객체 대입
			
			// 1. DB 연결에 필요한 Oracle JDBC Driver 메모리에 로드하기
													// -> 객체로 만들어 두기
			Class.forName("oracle.jdbc.driver.OracleDriver");
						// ( 패키지명 + 클래스명 )
			// -> () 안에 작성된 클래스의 객체를 반환
			// -> 메모리에 객체가 생성되어지고 JDBC 필요 시 알아서 참조해서 사용
			// --> 생략해도 자동으로 메모리 로드가 진행됨(명시적으로 작성하는걸 권장)
			/* 버전업되면서 안써도 된다고는 하는데.. 명시적으로 작성하는것이 아직까지는 더 좋습니다.
			 * forName에 마우스 올려두면 throws ClassNotFoundException 에 관한 명세가 있음
			 * 오타나면 못쓴다는겁니다. catch문에서 해당 Exception 처리를 해줍니다.
			 * */
			
			// 2. 연결 정보를 담은 Connection을 생성
			//	-> DriverManager객체를 이용해서 Connection 객체를 만들어 얻어옴!
			/* Connection이라는애가 new Connection을 가지고 있습니다. 
			 * 얘한테 연결정보를 전달해주면 커넥션 객체를 만들게 됩니다!
			 * */
			
			String type = "jdbc:oracle:thin:@"; // JDBC 드라이버의 종류
			/* 위에 1번에 적은 Class.forName("oracle.jdbc.driver.OracleDriver"); 가 무슨
			 * 타입인지 적어주는겁니다. 오타 안나게 주의!! 
			 * jdbc의 oracle에서 thin이라는 타입이다. 
			 * 이거는 사용할때 라이브러리 제공하는데에서 문서화해서 제공하고있습니다.
			 * 그냥 이렇게 적는거구나만 알고계시면됩니다
			 * */
			
			String ip = "192.168.219.117"; // DB 서버 컴퓨터 IP
			// localhost  ==  127.0.0.1  (loop back ip)
			/* 둘다 안되는 사람은 cmd 창에서 ipconfig 쳐서 ip 얻어와야함 */
			
			String port = ":1521"; // 포트번호
			// 1521(기본값)
				
			String sid = ":XE"; // DB 이름
			
			String user = "kh";
			
			String pw = "kh1234";
			
			// DriverManager : 
			// 메모리에 로드된 JDBC 드라이버를 이용해서
			// Connection 객체를 만드는 역할
			/* 이름이 드라이버 관리자라는 뜻인데, 이걸 사용해서 커넥션 만드는일을 할 수 있다. 
			 * 안에 열어보면 static final 로 되어있습니다.
			 * 객체로 만들어서 쓰는게 아니라 무조건 클래스명 써서 해라 입니다.
			 * DriverManager. 하시면 getConnection 이라는게 3개 있습니다. 
			 * 이거 선택(3번째꺼)
			 * */
			conn = DriverManager.getConnection(type + ip + port + sid, user, pw); 
			/* user, pw는 이미 있어요. url에 나머지를 다 더해주면 됩니다. 
				type + ip + port + sid 
				1단계에서 만들어둔 conn 이라는데에 대입을 해줍니다.
				에러가 뜰껀데, SQLException 이라는 예외가 뜹니다. catch문 처리해줘야합니다. */
			// SQLException : DB 관련 최상위 예외 (== 데이터베이스에 관련된 모든 예외)
			
			// 중간 확인
			// System.out.println(conn);
			// oracle.jdbc.driver.T4CConnection@6c40365c -- 커넥션 객체값 주소
			/* 이렇게 뜨면 정상 ! */
			
			
			// 3. SQL 작성
			// ** JAVA에서 작성되는 SQL은 마지막에 ;(세미콜론)을 찍지 않아야 한다! **
			String sql = "SELECT EMP_ID, EMP_NAME, SALARY, HIRE_DATE FROM EMPLOYEE";
			
			
			// 4. Statement 객체 생성
			// -> Connection 객체를 통해서 생성
			/* Connection 안에서 운행하는 셔틀버스같은 존재입니다. 
			 * 셔틀버스에 위에만든 sql을 태워야합니다! 
			 * 일단 셔틀버스를 만들어야해요!
			 * */
			stmt = conn.createStatement();
			
			
			// 5. 생성된 Statement 객체에
			//    sql을 적재하여 실행한 후
			//    결과를 반환 받아와서
			//    rs 변수에 저장
			rs = stmt.executeQuery(sql);
			// executeQuery() : SELECT문 수행 메서드, ResultSet 반환
			/*익스큐트쿼리
			 * 얘를 실행을 하면 sql을 태운 셔틀버스(Statement)가 db로 가서 실행을 시킵니다.
			 * 이 결과를 다시 Statement에(셔틀버스)에 담아줍니다.
			 * 그거를 java로 가지고 돌아옵니다!
			 * */
			
			
			// 3단계 : SQL을 수행해서 반환 받은 결과(ResultSet)를
			//			한 행씩 접근해서 컬럼 값 얻어오기
			
			while(rs.next()) {
				// rs.next() : rs가 참조하는 ResultSet 객체의
				//			첫 번째 컬럼부터 순서대로 한 행씩 이동하며
				//			다음 행이 있을 경우 true를 반환 
				//			없으면 false 반환
				
				
				// rs.get자료형("컬럼명")
				String empId = rs.getString("EMP_ID"); 
				// 현재 행의 "EMP_ID" 문자열 컬럼의 값을 얻어옴
				
				String empName = rs.getString("EMP_NAME"); 
				// 현재 행의 "EMP_NAME" 문자열 컬럼의 값을 얻어옴
				
				int salary = rs.getInt("SALARY");
				// 현재 행의 "SALARY" 숫자(정수) 컬럼의 값을 얻어옴
				
				// java.sql.Date
				Date hireDate =  rs.getDate("HIRE_DATE");
				// -> java.util.Date도 가능하긴 함 
				
				
				// 조회 결과 출력
				System.out.printf("사번 : %s / 이름 : %s / 급여 : %d / 입사일 : %s\n",
									empId, empName, salary, hireDate.toString() );
				
				// java.sql.Date의 toString()은 yyyy-mm-dd 형식으로 오버라이딩 되어있음.
			}
			
			
		} catch(ClassNotFoundException e) {
			System.out.println("JDBC 드라이버 경로가 잘못 작성되었습니다.");
		
		} catch(SQLException e) {
			e.printStackTrace();
			
			
		} finally {
			/*
			 * 
				자바랑 디비랑 연결하는거잖아요
				데이터통신하는건데 이때 스트림 개념이 적용됩니다
				커넥션이니 이런식으로 이름은 좀 다른데 공통점이있습니다
				스트림쓰면 끝에 마지막에 자원반환 클로즈 해줬었죠? 이 개념이 똑같이 적용됩니다
				공통적으로 close()를 쓰게 됩니다
				그래서 try catch finally 구문이 필요합니다!

			 * */
			// 4단계 : 사용한 JDBC 객체 자원 반환( close() )
			// ResultSet, Statement, Connection 닫기 (생성 역순으로 닫는 것을 권장)
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
}


