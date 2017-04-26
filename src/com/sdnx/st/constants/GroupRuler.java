package com.sdnx.st.constants;

public enum GroupRuler {
	first(0,6,"T","通过"),
	second(6,7,"3","分流"),
	thrid(7,Integer.MAX_VALUE,"3","分流");
	private int down;
	private int up;
	private String result;
	private String desc;
	private GroupRuler(int down, int up, String result, String desc){
		this.down = down;
		this.up = up;
		this.result = result;
		this.desc = desc;
	}
	
	public static String getResult(int score){
		for(GroupRuler g : values()){
			if(score >= g.down && score < g.up)
				return g.result;
		}
		return null;
	}
}
