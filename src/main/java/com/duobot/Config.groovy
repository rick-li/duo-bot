package com.duobot

import com.google.common.io.Resources

class Config {
	Properties props
	static Config instance = new Config() 
	private Config(){
		props = new Properties()
		props.load(Resources.getResource("config.properties").openStream());
	}
	static Config getInstance(){
		return instance
	}
	boolean useProxy() {
		return Boolean.parseBoolean(props.get("use.proxy", "false"))
	}
	
	String getProxyHost(){
		return props.get("proxy.host")
	}
	
	int getProxyPort(){
		return Integer.parseInt(props.get("proxy.port").toString().trim())
	}
}
