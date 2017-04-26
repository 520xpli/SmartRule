package com.sdnx.st.dp;

import com.sdnx.st.dp.model.RuleRequestObject;
import com.sdnx.st.dp.model.RuleResponseObject;

public interface StCalculatorInterface {
	RuleResponseObject calculateRule(RuleRequestObject requestObject);
}
