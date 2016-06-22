package com.nair.aclservice.enums;

public enum AccessLevels {

	READ(1, "READ"),
	WRITE(2, "WRITE"),
	DELETE(3, "DELETE"),
	;
	
	Integer value;
	String name;

	private AccessLevels(Integer value, String name) {
		this.value = value;
		this.name = name;
	}
	
	public static AccessLevels getByName(String name){
		for(AccessLevels accessLevels : AccessLevels.values()){
			if(accessLevels.name.equals(name)){
				return accessLevels;
			}
		}
		return null;
	}
	
	public static AccessLevels getByValue(Integer value){
		for(AccessLevels accessLevels : AccessLevels.values()){
			if(accessLevels.value.equals(value)){
				return accessLevels;
			}
		}
		return null;
	}

	public Integer getValue() {
		return value;
	}

	public String getName() {
		return name;
	}
	
}
