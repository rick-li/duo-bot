package com.duobot

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

import com.google.common.collect.Lists

class AixinQiao extends BasePhoneScraper {

	@Override
	public List<Contact> postProcessContacts(List<Contact> contacts) {
		return contacts;
	}

	@Override
	public void doConfig() {
		this.baseUrl = "http://www.axqcn.com/"
		this.source = "爱心桥"
		this.charset = "gb2312"
	}

	@Override
	public List<String> getPatientPageList(String indexHtml) {
		println "Select page list"
		List<String> pageList = Lists.newArrayList()
		Document doc = Jsoup.parse(indexHtml)
		println doc.select(".listbg .listA")

		doc.select(".listbg .listA").each{
			
			def url = baseUrl + it.attr("href");
			
			println "Patient url ${url}."
			pageList << url
		}
		return pageList
	}

}
