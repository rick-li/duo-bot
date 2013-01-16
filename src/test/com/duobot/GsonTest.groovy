package com.duobot

import org.junit.Test

import com.google.common.collect.Lists
import com.google.common.collect.Maps
import com.google.gson.Gson

class GsonTest {
	@Test void serialize(){
		Contact a = new Contact()
		a.setName("test1")
		a.setAge("12")
		a.setCellphone("135648888522")
		a.setDesc("aaaa")
		a.setReleaseDate("2012/12/31")
		
		List<Contact> l = Lists.newArrayList()
		l.add(a)
		println new Gson().toJson(l)
		Map<String, List<Contact>> m = Maps.newHashMap()
		m.put("data", l)
		println(m)
		String sjson = new Gson().toJson(m)
		
		println sjson
	}
}
