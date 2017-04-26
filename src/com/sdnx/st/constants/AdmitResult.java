package com.sdnx.st.constants;

public enum AdmitResult {
	pass(-4),
	split(-3),
	back(-2),
	refuse(-1);
	private int result;
	private AdmitResult(int result){
		this.result = result;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	
	public static void main(String[] args){
		int a = 1;
		System.out.println(a++);
	}
	
	/**
	 * �ж�׼�����Ƿ�Ϊ���ܾ����˻�
	 * @param result
	 * @return
	 */
	public static final boolean resultIsRefuse(Integer result){
		if(result == null){
			return false;
		}
		if(result.intValue() == back.result || result.intValue() == refuse.result){
			return true;
		}
		return false;
	}
	
	/**
	 * �ж�׼�����Ƿ���ͨ��
	 * @param result
	 * @return
	 */
	public static final boolean resultIsSplit(Integer result){
		if(result == null){
			return false;
		}
		if(result.intValue() != pass.result){
			return true;
		}
		return false;
	}
	
}
