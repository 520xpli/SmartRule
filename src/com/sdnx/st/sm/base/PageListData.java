package com.sdnx.st.sm.base;

import java.util.ArrayList;
import java.util.List;

public class PageListData {
	/**
	 * 页数
	 */
	private int pageSize = 10;
	/**
	 * 总数量
	 */
	private int count;
	/**
	 * 第几页
	 */
	private int page = 1;
	/**
	 * 分页结果数据
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
			result.append("<a href='#' onclick=" + prev + ">上一页 </a> ");
		}else{
			result.append("<a>上一页 </a> ");
		}
		
		result.append("第<input type='hidden' value='" + page + "' name='footer.page' id='page'/>" + page + "页 ");
		String next = "\"$('#page').val(parseInt($('#page').val()) + 1);query();\"";
		if(page != totalCount){
			result.append("<a href='#' onclick=" + next + ">下一页</a> ");
		}else{
			result.append("<a >下一页</a> ");
		}
		result.append("总条数:" + count + ", ");
		result.append("共" + totalCount + "页");
		result.append("</div>");
		result.append("</div>");
		return result.toString();
	}
	
}
