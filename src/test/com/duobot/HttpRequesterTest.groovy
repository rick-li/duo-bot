package com.duobot

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.junit.Before
import org.junit.Test

class HttpRequesterTest {
	HttpRequester req
	@Before
	void init(){
		req = new HttpRequester()
	}
	@Test void get(){
		String result = req.get("http://www.shilehui.com", "gb2312")
		assert result
		println result
		Document doc = Jsoup.parse(result)
		assert doc.select("head").size()>0
		
	}
	
	@Test void post(){
	
	}

	
	@Test void test(){
		InputStream is = this.getClass().getClassLoader().findResource("config.properties").openStream()
		
		assert is
	}
}
