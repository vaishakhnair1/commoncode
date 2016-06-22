package com.nair.aclservice.enums;

public enum Status {

	ACTIVE(1, "ACTIVE"),
	INACTIVE(0, "INACTIVE"),
	;
	
	Integer value;
	String name;

	private Status(Integer value, String name) {
		this.value = value;
		this.name = name;
	}
	
	public static Status getByName(String name){
		for(Status status : Status.values()){
			if(status.name.equals(name)){
				return status;
			}
		}
		return null;
	}
	
	public static Status getByValue(Integer value){
		for(Status status : Status.values()){
			if(status.value.equals(value)){
				return status;
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
