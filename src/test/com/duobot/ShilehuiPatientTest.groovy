package com.duobot

import static org.mockito.Mockito.*
import java.nio.charset.Charset

import org.junit.Before
import org.junit.Test

import com.google.common.io.Resources

class ShilehuiPatientTest {
	ShilehuiPatient s

	@Before void init(){
		s = new ShilehuiPatient()
	}

	@Test void testGroovyFind(){
		def l = [100, 200, 300]
		assert l.find({it>250}) == 300
	}

	@Test void getIndexPageList(){

		s.setStart(1)
		s.setLimit(100)
		List<String> pages = s.getIndexPageList("");
		assert pages.size() == 100
		assert pages.get(0).contains("Page=1")
	}
	
	@Test void getPatientPageList(){
		String testHtml = Resources.toString(Resources.getResource("shilehui-patient-index.html"), Charset.forName("utf-8"));
		List<String> list = s.getPatientPageList(testHtml)
		assert list.size()>5
		println list
	}
	
	@Test void submitResults(){
		HttpRequester req = mock(HttpRequester.class)
		
		s.setRequester(req)
	}

	@Test void processPatientPage(){
		String testHtml = Resources.toString(Resources.getResource("shilehui-patient.html"), Charset.forName("utf-8"));
		def patientUrl = "aaaaaaaaaaaaa.html"
		List<Contact> contacts = s.processPatientPage(testHtml, patientUrl)
		Contact patient = contacts.get(0);
		assert patient.getName() == "吴茜"
		assert patient.getAge() == "14"
		assert patient.getCellphone() == "15290302041"
		assert patient.getGender() == "女"
		assert patient.getReleaseDate() == "2012/12/31"
		assert patient.getUrl() == patientUrl
		Contact starter = contacts.get(1);
		assert starter.getCellphone() == "13598230628"
		assert starter.getQq() == "2426989982"
		assert starter.getDesc().contains("发起")
		assert starter.getUrl() == patientUrl
		Contact prover = contacts.get(2);
		assert prover == Contact.NullContact
		assert prover.getUrl() == patientUrl

	}
}
