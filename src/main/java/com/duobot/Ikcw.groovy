package com.duobot

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

import com.google.common.collect.Lists

class Ikcw extends ScraperTemplate{
	
	def blackNameList = [
		'医',
		'放射',
		'血',
		'疗',
		'大夫',
		'儿科',
		'内科'
	]


	@Override
	public void doConfig() {
		this.baseUrl = "http://www.ikcw.com/jiuzhu/"
		this.source = "癌症救助网"
		this.charset = "utf8"
		this.isDesc = true
	}
	

	@Override
	public List<String> getPatientPageList(String indexHtml) {
		println "Select page list"
		//println indexHtml
		List<String> pageList = Lists.newArrayList()
		Document doc = Jsoup.parse(indexHtml)
		println doc.select(".clz_wyqz_title a")

		doc.select(".clz_wyqz_title a").each{
			def url = baseUrl + it.attr("href")
			println "Patient url ${url}."
			pageList << url
		}
		return pageList
	}

	@Override
	public List<Contact> processPatientPage(String html, String patientUrl) {
//		println html
		Document doc = Jsoup.parse(html);
		List<Contact> contacts = Lists.newArrayList()
		if(!html) return contacts

		def releaseDate = "2013/01/01"
		try{
			def strReleaseDate = doc.select(".info small").get(0).text()
			if(strReleaseDate && strReleaseDate.length()>=10){
				releaseDate = strReleaseDate.substring(0, 10) 
			}
		}catch(Exception e){
			e.printStackTrace()
		}
		def patientbaseSel = "#ikdy table"
		
		Contact patient = getOneContact(patientbaseSel, doc.select(patientbaseSel).get(0))
		patient.setDesc(doc.title())
		patient.setUrl(patientUrl)
		patient.setReleaseDate(releaseDate)
		if(patient != Contact.NullContact){
			contacts << patient
			println patient
		}
		
		Contact prover = getOneContact("", doc.select(patientbaseSel).get(0))
		prover.setDesc(doc.title())
		prover.setUrl(patientUrl)
		prover.setReleaseDate(releaseDate)
		if(prover != Contact.NullContact){
			contacts << prover
			println prover
		}
		
		return contacts;
	}

	@Override
	public Contact getOneContact(String baseSelector, Element el) {
		Contact c = new Contact();
		def nameSel = baseSelector+"td:contains(名)"
		def genderSel = baseSelector+"td:contains(性)"
		def cellSel = baseSelector+"td:contains(机)"
		def ageSel = baseSelector+"td:contains(年)"
		def qqSel = baseSelector+"td:contains(Q)"
		def jobSel = baseSelector+"td:contains(与证明人关系)"

		def job =  getOneAttr(jobSel, el)


		if(job){
			if(this.blackNameList.find({job.contains(it)})){
				println "job ${job} is in black list."
				return Contact.NullContact
			}

		}
		c.setName(getOneAttr(nameSel, el))
		c.setGender(getOneAttr(genderSel, el))
		c.setAge(getOneAttr(ageSel, el))
		c.setCellphone(getOneAttr(cellSel, el))
		c.setQq(getOneAttr(qqSel, el))
		c.setSource(source)
		c.setDesc(job)
		return c
	}

	String getOneAttr(String labelSelector, Element el){
		if(el.select(labelSelector).size()>0){
			def valEl = el.select(labelSelector).get(0).nextElementSibling()
			if(valEl){
				def result = valEl.text()
				println result
				return result
			}
		}
		return ""
	}

}
