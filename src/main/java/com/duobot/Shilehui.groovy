package com.duobot

import java.util.List;
import java.util.concurrent.Callable
import java.util.concurrent.CompletionService
import java.util.concurrent.ExecutorCompletionService
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

import com.google.common.collect.Lists
import com.google.gson.Gson



class Shilehui extends ScraperTemplate {
	
	
//	String storeUrl = "http://127.0.0.1/duo-bot/index.php?r=patient/createPatients"




//	HttpRequester requester = new HttpRequester()
//	ExecutorService exec = Executors.newFixedThreadPool(20)

	void doConfig(){
		this.baseUrl = "http://www.shilehui.com"
		this.source = "施乐会"
		this.charset = "gb2312"
		this.blackNameList = [
			'医',
			'放射',
			'血',
			'疗',
			'大夫',
			'儿科',
			'内科'
		]
	}


	List<String> getPatientPageList(String indexHtml){
		println "Select page list"
		//println indexHtml
		List<String> pageList = Lists.newArrayList()
		Document doc = Jsoup.parse(indexHtml)
		println doc.select("header")
		println doc.select(".donate_info_content_title a")

		doc.select(".donate_info_content_title a").each{
			def url = baseUrl + it.attr("href")
			println "Patient url ${url}."
			pageList << url
		}
		return pageList
	}

	List<Contact> processPatientPage(String html, String patientUrl){
		println "Processing ${patientUrl}"
		Document doc = Jsoup.parse(html);
		List<Contact> contacts = Lists.newArrayList()


		def patientbaseSel = "#Left_Assist1_spanReceipt .AssitTab tr "
		Contact patient = getOneContact(patientbaseSel, doc)
		patient.setDesc(doc.title())
		patient.setUrl(patientUrl)
		if(patient != Contact.NullContact){
			contacts << patient
			println patient
		}


		def starterBaseSel = "#Left_Assist1_spanshow .AssitTab tr "
		Contact starter = getOneContact(starterBaseSel, doc)
		starter.setDesc("发起人　"+starter.getDesc())
		starter.setUrl(patientUrl)
		if(starter != Contact.NullContact){
			contacts << starter
			println starter
		}

		def proverBaseSel = "#Left_Assist1_spanshow_wjy .AssitTab tr "
		Contact prover = getOneContact(proverBaseSel, doc)
		prover.setDesc("证明人　"+prover.getDesc())
		prover.setUrl(patientUrl)
		if(prover != Contact.NullContact){
			contacts << prover
			println prover
		}

		println "page ${patientUrl} completed."
		return contacts
	}

	Contact getOneContact(String baseSelector, Element el){
		Contact c = new Contact();
		def dateSel = "span:contains(发布时间)"
		def releaseDate = "2013/01/01"
		if(getOneAttr(dateSel, el).length()>=10)
			releaseDate = getOneAttr(dateSel, el).substring(0, 10)
		def nameSel = baseSelector+"td:contains(名)"
		def genderSel = baseSelector+"td:contains(性)"
		def cellSel = baseSelector+"td:contains(机)"
		def ageSel = baseSelector+"td:contains(年)"
		def qqSel = baseSelector+"td:contains(Q)"
		def jobSel = baseSelector+"td:contains(职)"

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
		c.setReleaseDate(releaseDate)
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


	@Override
	public List<Contact> postProcessContacts(List<Contact> contacts) {
		return contacts;
	}


}
