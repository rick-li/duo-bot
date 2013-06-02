package com.duobot

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

import com.google.common.collect.Lists

abstract class BasePhoneScraper extends ScraperTemplate {
	

	@Override
	public List<Contact> processPatientPage(String html, String patientUrl) {
		println "Processing ${patientUrl}"
//		println ("html is "+html)
		
		Document doc = Jsoup.parse(html);
		String doctxt = doc.text()
//		println doctxt;
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
		// TODO Auto-generated method stub
		return null;
	}

}
