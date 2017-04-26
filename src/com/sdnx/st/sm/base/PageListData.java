package com.sdnx.st.sm.base;

import java.util.ArrayList;
import java.util.List;

public class PageListData {
	/**
	 * ҳ��
	 */
	private int pageSize = 10;
	/**
	 * ������
	 */
	private int count;
	/**
	 * �ڼ�ҳ
	 */
	private int page = 1;
	/**
	 * ��ҳ�������
	 */
	private List dataArray = new ArrayList<>();
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List getDataArray() {
		return dataArray;
	}
	public void setDataArray(List dataArray) {
		this.dataArray = dataArray;
	}
	
	@Override
	public String toString(){
		StringBuffer result = new StringBuffer();
		int totalCount = count / pageSize;
		if(count % pageSize != 0 && count != 0){
			totalCount++;
		}
		result.append("<div style='margin:0 auto;width:80%;'>");
		result.append("<div style='border:1px solid #ddd;width:fit-content;width:-webkit-fit-content;width:-moz-fit-content;'>");
		String prev = "\"$('#page').val(parseInt($('#page').val()) - 1);query();\"";
		if(page != 1){
			result.append("<a href='#' onclick=" + prev + ">��һҳ </a> ");
		}else{
			result.append("<a>��һҳ </a> ");
		}
		
		result.append("��<input type='hidden' value='" + page + "' name='footer.page' id='page'/>" + page + "ҳ ");
		String next = "\"$('#page').val(parseInt($('#page').val()) + 1);query();\"";
		if(page != totalCount){
			result.append("<a href='#' onclick=" + next + ">��һҳ</a> ");
		}else{
			result.append("<a >��һҳ</a> ");
		}
		result.append("������:" + count + ", ");
		result.append("��" + totalCount + "ҳ");
		result.append("</div>");
		result.append("</div>");
		return result.toString();
	}
	
}
