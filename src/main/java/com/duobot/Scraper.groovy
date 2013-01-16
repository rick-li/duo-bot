package com.duobot

class Scraper {
	static void main(String[] args){
		ShilehuiPatient s = new ShilehuiPatient()
		s.setStart(1)
		s.setLimit(2)
		s.doScrap()
	}
}
