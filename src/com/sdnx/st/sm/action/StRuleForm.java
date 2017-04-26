package com.sdnx.st.sm.action;

import java.util.List;

import com.sdnx.st.sm.base.BaseForm;
import com.sdnx.st.sm.vo.StObjectVo;
import com.sdnx.st.sm.vo.StStructrueVo;

public class StRuleForm extends BaseForm{
	//模型编码
	private String modelcode;
	//模型类别
	private String classification;
	//是否进入查看页面
	private String showtext;
	//结构编码
	private String struccode;
	//树结构url
	private String treeUrl;
	//规则结构信息
	private StStructrueVo strucVo = new StStructrueVo();
	//对象信息
	private List<StObjectVo> objectList;
	//是否显示属性code
	private String isShowCode;
	
	public String getIsShowCode() {
		return isShowCode;
	}
	public void setIsShowCode(String isShowCode) {
		this.isShowCode = isShowCode;
	}
	public String getTreeUrl() {
		return treeUrl;
	}
	public void setTreeUrl(String treeUrl) {
		this.treeUrl = treeUrl;
	}
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
	public List<StObjectVo> getObjectList() {
		return objectList;
	}
	public void setObjectList(List<StObjectVo> objectList) {
		this.objectList = objectList;
	}
	public StStructrueVo getStrucVo() {
		return strucVo;
	}
	public void setStrucVo(StStructrueVo strucVo) {
		this.strucVo = strucVo;
	}
	public String getModelcode() {
		return modelcode;
	}
	public void setModelcode(String modelcode) {
		this.modelcode = modelcode;
	}
	public String getShowtext() {
		return showtext;
	}
	public void setShowtext(String showtext) {
		this.showtext = showtext;
	}
	public String getStruccode() {
		return struccode;
	}
	public void setStruccode(String struccode) {
		this.struccode = struccode;
	}
	
}
