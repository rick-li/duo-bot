package com.duobot

import org.junit.Test

class ConfigTest {
	@Test void getProxy(){
		Config c = Config.getInstance()
		println c.useProxy()
		println c.getProxyHost()
		println c.getProxyPort()
		assert c.getProxyHost().contains("webproxy")
		assert c.getProxyPort() == 8080
	}
}
