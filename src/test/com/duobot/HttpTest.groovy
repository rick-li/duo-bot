package com.duobot

import groovyx.net.http.HTTPBuilder
import static groovyx.net.http.Method.GET
import static groovyx.net.http.ContentType.TEXT
import static groovyx.net.http.ContentType.HTML
import org.junit.Test

class HttpTest {
	@Test void testHttp(){
		//URL url = new URL("http://www.shilehui.com/AssistUI/Index.aspx?p=&ct=&co=&Sid=-2&AgeS=&AgeS2=&PassType=3&keyword=&Disease=&Page=1")
		URL url = new URL("http://www.google.com")
		def http = new HTTPBuilder(url)
		http.setProxy("webproxy.ssmb.com", 8080, "http")
//		http.setProxy("127.0.0.1", 9999, "http")
		println http.get(uri: url, contentType: TEXT){ resp->
			return resp.getEntity().getContent().getText()
		}
//		http.request(GET, HTML){req ->
//			response.success = { resp->
//				println resp.getEntity().getContent().getText()
//			}
//		}

	}
}
