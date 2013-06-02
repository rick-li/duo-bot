package com.duobot

import java.util.List;

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

import com.google.common.collect.Lists

class Yayjy extends BasePhoneScraper {

	@Override
	public void doConfig() {
		this.baseUrl = "http://www.yayjy.com/"
		this.source = "求助网"
		this.charset = "gb2312"
	}

	@Override
	public List<String> getPatientPageList(String indexHtml) {
		println "Select page list"
		//println indexHtml
		List<String> pageList = Lists.newArrayList()
		Document doc = Jsoup.parse(indexHtml)
		println doc.select("#body_data #body_data1 .t2")

		doc.select("#body_data #body_data1 .t2").each{
//			window.open('list.asp?id=3033');
			
			
			def url = baseUrl + it.attr("onclick").replaceAll("window.open\\(\'", "").replaceAll("\'\\)", "").replaceAll(";", "");
			
			println "Patient url ${url}."
			pageList << url
		}
		return pageList
	}

	@Override
	public List<Contact> postProcessContacts(List<Contact> contacts) {
		contacts.each {
			contact ->
				contact.setDesc(contact.getDesc().replaceAll("求助网|友爱1\\+1|寻人网|求医网|奉献网|爱心网|赠送网 - ", "").replaceAll("|", ""))
		}
		return contacts;
	}

}
