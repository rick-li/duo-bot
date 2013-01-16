package com.duobot
import static groovyx.net.http.ContentType.TEXT

import groovyx.net.http.HTTPBuilder

import java.nio.charset.Charset

import com.google.common.io.CharStreams
import com.google.common.io.Closeables
import com.google.common.io.OutputSupplier


class HttpRequester {
	Config cfg = Config.getInstance()
	String get(String sUrl, String charset){
		println "Get ${sUrl}."
		URL url = new URL(sUrl)
		def http = new HTTPBuilder(sUrl)
		String result = ""
		if(cfg.useProxy()){
			http.setProxy(cfg.getProxyHost(), cfg.getProxyPort(), "http")
		}
		return http.get(uri: url, contentType: TEXT){ resp->
			return resp.getEntity().getContent().getText(charset)
		}
	}

	void post(String sUrl, String data){
		println "Post ${sUrl} with ${data}."
		URL url = new URL(sUrl)
		def http = new HTTPBuilder(sUrl)
		if(cfg.useProxy()){
			http.setProxy(cfg.getProxyHost(), cfg.getProxyPort(), "http")
		}
		println data
		http.post(body: ["data": data]){resp ->
			println resp.getEntity().getContent().getText()
			println resp.statusLine.statusCode
		}
	}
}
