package com.sdnx.st.constants;

public enum Term {
	lessHalfYear("1",0,6), //��������
	halfYearToOne("2",6,12), //���굽һ��
	oneYearToThree("3",12,36),//һ�굽����
	ThreeYearToFive("4",36,60),//���굽����
	overFive("5",60, Integer.MAX_VALUE);//��������
	private String code;
	private int down;
	private int up;
	private Term(String code, int down, int up){
		this.code = code;
		this.down = down;
		this.up = up;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getDown() {
		return down;
	}
	public void setDown(int down) {
		this.down = down;
	}
	public int getUp() {
		return up;
	}
	public void setUp(int up) {
		this.up = up;
	}
	
	//��ȡ���ޱ���
	public static String getTermCode(int term){
		for(Term t : values()){
			if(term > t.down && term <= t.up)
				return t.getCode();
		}
		return null;
	}
}
