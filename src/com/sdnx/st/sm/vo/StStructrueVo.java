package com.sdnx.st.sm.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StStructrueVo {
	// 编号
	private String id;
	// 模型编码
	private String modelcode;
	// 结构编码
	private String struccode;
	// 父结构编码
	private String parentstruccode;
	// 层级
	private Short level;
	// 结构名称
	private String strucname;
	// 描述
	private String description;
	// 执行顺序
	private Integer seq;
	// 对象间执行规则
	private String objectrule;
	// 最后操作时间
	private Date lastopertime;
	// 规则模块特色
	private StModuleVo stModuleVo = new StModuleVo();
	// 规则特色
	private StRuleVo stRuleVo = new StRuleVo();
	// 子结构信息
	private List<StStructrueVo> childList = new ArrayList<StStructrueVo>();
	// 父结构信息
	private StStructrueVo parent;
	// 具体规则信息
	private List<StRuleDetailVo> detailList = new ArrayDetailList();
	// 提示信息
	private String prompt;
	// 类型
	private String classification;
	// 关联编码
	private String relatecode;
	// 扩展字段
	private String misc;
	//是否叶子节点
	private String isleaf;
	// 规则结果
	private String ruleresult;
	//规则数据
	private String ruleData;
	
	public String getRuleData() {
		return ruleData;
	}

	public void setRuleData(String ruleData) {
		this.ruleData = ruleData;
	}

	public String getRuleresult() {
		return ruleresult;
	}

	public void setRuleresult(String ruleresult) {
		this.ruleresult = ruleresult;
	}
	// 用于列表数据的提交
	private class ArrayDetailList extends ArrayList<StRuleDetailVo> {
		public StRuleDetailVo get(int index) {
			if (index >= super.size()) {
				int length = index - super.size() + 1;
				for (int i = 0; i < length; i++) {
					super.add(new StRuleDetailVo());
				}
			}
			return super.get(index);
		}
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public String getRelatecode() {
		return relatecode;
	}

	public void setRelatecode(String relatecode) {
		this.relatecode = relatecode;
	}

	public String getMisc() {
		return misc;
	}

	public void setMisc(String misc) {
		this.misc = misc;
	}

	public String getPrompt() {
		return prompt;
	}

	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	public List<StRuleDetailVo> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<StRuleDetailVo> detailList) {
		this.detailList = detailList;
	}

	public StStructrueVo getParent() {
		return parent;
	}

	public void setParent(StStructrueVo parent) {
		this.parent = parent;
	}

	public List<StStructrueVo> getChildList() {
		return childList;
	}

	public void setChildList(List<StStructrueVo> childList) {
		this.childList = childList;
	}

	public StRuleVo getStRuleVo() {
		return stRuleVo;
	}

	public void setStRuleVo(StRuleVo stRuleVo) {
		this.stRuleVo = stRuleVo;
	}

	public StModuleVo getStModuleVo() {
		return stModuleVo;
	}

	public void setStModuleVo(StModuleVo stModuleVo) {
		this.stModuleVo = stModuleVo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getModelcode() {
		return modelcode;
	}

	public void setModelcode(String modelcode) {
		this.modelcode = modelcode;
	}

	public String getStruccode() {
		return struccode;
	}

	public void setStruccode(String struccode) {
		this.struccode = struccode;
	}

	public String getParentstruccode() {
		return parentstruccode;
	}

	public void setParentstruccode(String parentstruccode) {
		this.parentstruccode = parentstruccode;
	}

	public Short getLevel() {
		return level;
	}

	public void setLevel(Short level) {
		this.level = level;
	}

	public String getStrucname() {
		return strucname;
	}

	public void setStrucname(String strucname) {
		this.strucname = strucname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getObjectrule() {
		return objectrule;
	}

	public void setObjectrule(String objectrule) {
		this.objectrule = objectrule;
	}

	public Date getLastopertime() {
		return lastopertime;
	}

	public void setLastopertime(Date lastopertime) {
		this.lastopertime = lastopertime;
	}

	public String getIsleaf() {
		return isleaf;
	}

	public void setIsleaf(String isleaf) {
		this.isleaf = isleaf;
	}

	
}
