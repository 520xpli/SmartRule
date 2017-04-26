package com.sdnx.st.constants;

public class ObjectCode {
	public static final int admit = 1;
	public static final int limitAmount = 2;
	public static final int rate = 3;
	public static final int firstTrial = 4;
	public static final int lastTrial = 5;
	public static final int processTrend = 6;
	public static final String rateSurvey = "1";
	public static final String rateBase = "2";
	public static final String rateCredit = "3";

	public enum Admit {
		AdmitBorrower, AdmitWarrant, AdmitRelateCompany, AdmitFamily;
	}

	public enum LimitAmount {
		LimitAmountBorrower, LimitAmountWarrant;
	}

	public enum Rate {
		RateSurvey,RateBase,RateCredit;
	}

	public enum FirstTrial {
		FirstTrial;
	}

	public enum LastTrial {
		LastTrial;
	}
	
	public enum ProcessTrend {
		ApprovalProcessTrend;
	}
	public static String getObjectCode(int type, String objectType) {
		String result = "";
		if(type < admit | type > processTrend)
			return result;
		if (type == admit) {
			if(ObjectType.borrower.getCode().equals(objectType))
				return Admit.AdmitBorrower.toString();
			else if(ObjectType.warrant.getCode().equals(objectType))
				return Admit.AdmitWarrant.toString();
			else if(ObjectType.relateCompany.getCode().equals(objectType))
				return Admit.AdmitRelateCompany.toString();
			else if(ObjectType.family.getCode().equals(objectType))
				return Admit.AdmitFamily.toString();
			
		} else if (type == limitAmount) {
			if(ObjectType.borrower.getCode().equals(objectType))
				return LimitAmount.LimitAmountBorrower.toString();
			else if(ObjectType.warrant.getCode().equals(objectType))
				return LimitAmount.LimitAmountWarrant.toString();;
		} else if (type == rate) {
			if(rateSurvey.equals(objectType))
				return Rate.RateSurvey.toString();
			else if(rateBase.equals(objectType))
				return Rate.RateBase.toString();
			else if(rateCredit.equals(objectType))
				return Rate.RateCredit.toString();
		} else if (type == firstTrial) {
				return FirstTrial.FirstTrial.toString();
		} else if (type == lastTrial) {
				return LastTrial.LastTrial.toString();
		} else if (type == processTrend) {
				return ProcessTrend.ApprovalProcessTrend.toString();
		}
		return result;
	}
}
