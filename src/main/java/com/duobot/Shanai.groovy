
package com.duobot
import java.util.regex.Pattern



import java.util.List;
import java.util.regex.Pattern
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

import com.google.common.collect.Lists

class Shanai extends ScraperTemplate{

	@Override
	public void doConfig() {
		this.baseUrl = "http://www.shanai365.com/"
		this.source = "善爱网"
		this.charset = "utf-8"
	}

	@Override
	public List<String> getPatientPageList(String indexHtml) {
		println "Select page list"
		//println indexHtml
		List<String> pageList = Lists.newArrayList()
		Document doc = Jsoup.parse(indexHtml)
		println doc.select(".bm_c .xs2 a")

		doc.select(".bm_c .xs2 a").each{
			def url = baseUrl + it.attr("href")
			if(url.contains("donation.aspx")){
				return
			}
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

	@Override
	public List<Contact> postProcessContacts(List<Contact> contacts) {
		// TODO Auto-generated method stub
		return contacts;
	}

}
