package com.duobot

import java.util.concurrent.Callable
import java.util.concurrent.CompletionService
import java.util.concurrent.ExecutorCompletionService
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

import org.jsoup.nodes.Element

import com.google.common.collect.Lists
import com.google.gson.Gson

abstract class ScraperTemplate {
	String baseUrl 
	String indexPath
	String pageTpl
	int start = 1
	int limit = 99
	def blackNameList = []
	String source
	String charset
	boolean isDesc
	boolean allowSubmit = true

	HttpRequester requester = new HttpRequester()
	ExecutorService exec = Executors.newFixedThreadPool(2)
	void doScrap(){
		doConfig();
		print baseUrl
		print indexPath
		String indexUrl = baseUrl + indexPath
		String rootHtml = requester.get(indexUrl, charset);
		List<String> indexPageList = getIndexPageList(rootHtml)
		indexPageList.each {
			List<Contact> result = Lists.newArrayList();
			def indexPageUrl = it
			CompletionService<List<Contact>> serv =
					new ExecutorCompletionService<List<Contact>>(exec);

			List<String> pageList = getPatientPageList(requester.get(indexPageUrl, charset))
			pageList.each {
				final def patientPageUrl = it
				println "Start a new thread "

				serv.submit({return processPatientPage(requester.get(patientPageUrl, charset), patientPageUrl)} as Callable)
			}

			println "Thread creation completed."

			(0..pageList.size()-1).each{
				println "taking result"
				result.addAll(serv.take().get())
			}
			println "result retrieved."
			if(result)
				submitResults(postProcessContacts(result))
		}
		exec.shutdown()
	}

	
	List<String> getIndexPageList(String rootHtml){
		List<String> pageList = Lists.newArrayList()
		def pages = (start .. limit) 
		if(isDesc){
			//get last page dynamicly
			pages = (limit .. start) 
		}
		pages.each {
			pageList << baseUrl + pageTpl.replace("#PAGE_NO", it.toString())
		}
		return pageList
	}
	void submitResults(List<Contact> results){
		String strResult = new Gson().toJson(results)
		println strResult
		if(!allowSubmit) return
		println "submiting results"
		requester.post("http://www.sicpc.com/duo-bot/index.php?r=patient/createPatients", strResult)
//		requester.post("http://127.0.0.1/duo-bot/index.php?r=patient/createPatients", strResult)
	}
	abstract List<Contact> postProcessContacts(List<Contact> contacts);
	
	abstract void doConfig();

	abstract List<String> getPatientPageList(String indexHtml);

	abstract List<Contact> processPatientPage(String html, String patientUrl);

	abstract Contact getOneContact(String baseSelector, Element el)

}
