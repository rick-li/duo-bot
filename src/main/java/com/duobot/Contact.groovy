package com.duobot

class Contact {
	String name
	String age
	String gender
	String source
	String url
	String releaseDate
	String cellphone
	String qq
	String desc
	
	static Contact NullContact
	
	static{
		NullContact = new Contact()
	}
	
	String toString(){
		return com.google.common.base.Objects.toStringHelper(this).toString()
	}
}
