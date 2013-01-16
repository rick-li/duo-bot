package com.duobot
import org.junit.Test

import com.google.common.collect.Lists
import com.google.common.collect.Maps
import com.google.gson.Gson


class PostTest{

	
	@Test void test(){
		
		println 'test'
		def r = new HttpRequester()
		//def d = ""
		Contact a = new Contact()
		a.setName("test1")
		a.setAge("12")
		a.setCellphone("135648888520")
		a.setDesc("aaaa")
		a.setReleaseDate("2012/12/31")
		a.setUrl("aaaaa.html")
		List<Contact> l = Lists.newArrayList()
		l.add(a)
		println new Gson().toJson(l)
		String sjson = new Gson().toJson(l)

		println sjson
		r.post("http://127.0.0.1/duo-bot/index.php?r=patient/createPatients", sjson)
		
		
	}
}