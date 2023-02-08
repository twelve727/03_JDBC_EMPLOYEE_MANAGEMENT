package edu.kh.jdbc.model.vo;

public class TestVO {

	private int testNO;
	private String testTitle;
	private String testCOntent;
	
	// 기본 생성자
	public TestVO() {}

	//매개변수 생성자(모든 필드 초기화)
	public TestVO(int testNO, String testTitle, String testCOntent) {
		super();
		this.testNO = testNO;
		this.testTitle = testTitle;
		this.testCOntent = testCOntent;
	}

	
	//겟셋
	public int getTestNO() {
		return testNO;
	}

	public void setTestNO(int testNO) {
		this.testNO = testNO;
	}

	public String getTestTitle() {
		return testTitle;
	}

	public void setTestTitle(String testTitle) {
		this.testTitle = testTitle;
	}

	public String getTestCOntent() {
		return testCOntent;
	}

	public void setTestCOntent(String testCOntent) {
		this.testCOntent = testCOntent;
	}

	// toString() 오버라이딩
	@Override
	public String toString() {
		return "TestVO [testNO=" + testNO + ", testTitle=" + testTitle + ", testCOntent=" + testCOntent + "]";
		
	}
}