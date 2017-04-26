package com.sdnx.st.se.checker;

import java.util.List;
import java.util.Map;

import com.sdnx.st.constants.YesNo;
import com.sdnx.st.dp.model.PublicData;
import com.sdnx.st.se.constants.CalculatorConstant;
import com.sdnx.st.se.constants.DataType;
import com.sdnx.st.se.constants.ErrorCode;
import com.sdnx.st.se.init.StInit;
import com.sdnx.st.se.result.StResultObject;
import com.sdnx.st.sm.vo.StObjectPropertyVo;
import com.sdnx.st.sm.vo.StObjectVo;
import com.sdnx.st.util.StUtil;

public class StEngineChecker {
	@SuppressWarnings("unchecked")
	public static StResultObject checkData(List<Map<String, Object>> dataSource, String typeCode, PublicData pd){
		StResultObject ro = new StResultObject();
		ro.setCode(CalculatorConstant.PASS_CODE);
		
		//�ж��Ƿ��з��������Ĺ�����ϵ
		Map<String, Object> ruleTypeMap = StInit.getRuleData(pd, typeCode);
		if (ruleTypeMap == null) {
			ro.setCode(ErrorCode.notHaveRule.getCode());
			ro.setSingleRuleDescription(ErrorCode.notHaveRule.getDesc());
			return ro;
		}
		
		//����Դ��֤
		if(dataSource == null || dataSource.size() == 0){
			ro.setCode(ErrorCode.dataSourceIsNull.getCode());
			ro.setSingleRuleDescription(ErrorCode.dataSourceIsNull.getDesc());
			return ro;
		}
		// ������֤
		for (Map<String, Object> obj : dataSource) {
			Object objectCode = obj.get(CalculatorConstant.OBJECT_FLAG);
			//�ж��Ƿ��ж����ʶ
			if (objectCode == null) {
				ro.setCode(ErrorCode.notHaveObjectFlag.getCode());
				ro.setSingleRuleDescription(ErrorCode.notHaveObjectFlag.getDesc());
				return ro;
			}
			// ��ȡ��ʼ���Ķ���map
			Map<String, StObjectVo> objMap = (Map<String, StObjectVo>) StInit.getStRuleMapData()
					.get(CalculatorConstant.OBJ_MAP_KEY);
			StObjectVo soVo = objMap.get(objectCode);
			if (soVo == null) {
				ro.setCode(ErrorCode.objectNotMatch.getCode());
				ro.setSingleRuleDescription(ErrorCode.objectNotMatch.getDesc());
				return ro;
			}
			List<StObjectPropertyVo> sops = soVo.getPropertyList();
			if (sops == null || sops.size() == 0) {
				ro.setCode(ErrorCode.notInitObjectProperty.getCode());
				ro.setSingleRuleDescription(ErrorCode.notInitObjectProperty.getDesc());
				return ro;
			}
			// ����������֤
			for (StObjectPropertyVo sop : sops) {

				String isNull = sop.getIsnull(), ename = sop.getPropertykey();

				Map<String, String> edatas = sop.getEdatas();
				Object value = obj.get(ename);
				String dataType = sop.getDatatypecode();
				if (value == null && YesNo.no.getCode().equals(isNull)) {
					ro.setCode(ErrorCode.dataCannotNull.getCode());
					ro.setSingleRuleDescription(soVo.getObjectname() + sop.getPropertyname() + ErrorCode.dataCannotNull.getDesc());
					return ro;
				}
				else if(value == null){
					continue;
				}
				else {
					// �ж������ֵ����Ƿ���ڴ�ö��ֵ
					if (edatas != null && edatas.size() > 0) {
						String evalue = edatas.get(value);
						if (evalue == null) {
							ro.setCode(ErrorCode.propertyNotMatchEnum.getCode());
							ro.setSingleRuleDescription(soVo.getObjectname() + sop.getPropertyname() + ErrorCode.propertyNotMatchEnum.getDesc());
							return ro;
						}
					}

					// �����ж�
					if (DataType.age.getCode().equals(dataType)) {
						if (value != null) {
							int a = 0;
							try {
								a = Integer.valueOf(String.valueOf(value));
							} catch (Exception e) {
								a = -1;
							}
							if (a < 0 || a > 200) {
								ro.setCode(ErrorCode.ageIsError.getCode());
								ro.setSingleRuleDescription(soVo.getObjectname() + sop.getPropertyname() + ErrorCode.ageIsError.getDesc());
								return ro;
							}
						}
					} else if (DataType.date.getCode().equals(dataType)) {
						if (!StUtil.isValidDate((String) value)) {
							ro.setCode(ErrorCode.dateIsError.getCode());
							ro.setSingleRuleDescription(soVo.getObjectname() + sop.getPropertyname() + ErrorCode.dateIsError.getDesc());
							return ro;
						}
					} else if (DataType.times.getCode().equals(dataType)) {
						// ����
						double n = 0;
						try {
							n = Double.valueOf(String.valueOf(value));
						} catch (Exception e) {
							n = -2;
						}
						if (n < -1) {
							ro.setCode(ErrorCode.timesIsError.getCode());
							ro.setSingleRuleDescription(soVo.getObjectname() + sop.getPropertyname() + ErrorCode.timesIsError.getDesc());
							return ro;
						}
					} else if (DataType.term.getCode().equals(dataType)) {
						// ����������֤
						int n = 0;
						try {
							n = Integer.valueOf(String.valueOf(value));
						} catch (Exception e) {
							n = -1;
						}
						if (n < 0) {
							ro.setCode(ErrorCode.termIsError.getCode());
							ro.setSingleRuleDescription(soVo.getObjectname() + sop.getPropertyname() + ErrorCode.termIsError.getDesc());
							return ro;
						}

					} else if (DataType.amount.getCode().equals(dataType)) {
						// �����֤
						double n = 0;
						try {
							n = Double.valueOf(String.valueOf(value));
						} catch (Exception e) {
							e.printStackTrace();
							n = Double.MIN_VALUE;
						}
						if (n == Double.MIN_VALUE) {
							ro.setCode(ErrorCode.amountIsError.getCode());
							ro.setSingleRuleDescription(soVo.getObjectname() + sop.getPropertyname() + ErrorCode.amountIsError.getDesc());
							return ro;
						}

					}
				}
			}

		}

		return ro;
	}
}
