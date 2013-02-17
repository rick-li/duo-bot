package com.duobot

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

import com.google.common.collect.Lists

class Yaolan extends ScraperTemplate {

	@Override
	public void doConfig() {
		this.baseUrl = "http://bbs.yaolan.com"
		this.source = "摇篮网"
		this.charset = "utf-8"

	}

	@Override
	public List<String> getPatientPageList(String indexHtml) {
		println "Select page list"
		List<String> pageList = Lists.newArrayList()
		Document doc = Jsoup.parse(indexHtml)
		println doc.select(".hot span a")

		doc.select(".hot span a").each{
			def url = baseUrl + it.attr("href")
			println "Patient url ${url}."
			pageList << url
		}
		return pageList
	}

	@Override
	public List<Contact> processPatientPage(String html, String patientUrl) {
		println "Processing ${patientUrl}"
		Document doc = Jsoup.parse(html);
		String doctxt = doc.text()
		//println doctxt
		List<Contact> contacts = Lists.newArrayList()
		Contact patient = new Contact()
		patient.setDesc(doc.title())
		patient.setUrl(patientUrl)
		patient.setSource(this.source)
		patient.setReleaseDate(RegexUtil.extractDate(doctxt))
		patient.setCellphone(RegexUtil.extractPhonenum(doctxt))
		patient.setName("无")
		contacts << patient
		
		
		return contacts;
	}

	@Override
	public Contact getOneContact(String baseSelector, Element el) {
		return null;
	}

}
