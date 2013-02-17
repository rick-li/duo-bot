package com.duobot

import org.junit.Assert
import org.junit.Test


class RegexUtilTest {
	@Test void extractPhone(){
		def num = RegexUtil.extractPhonenum("吕  政：13911801064（孩子爸爸）")
		Assert.assertEquals("13911801064", num)
		
		def num2 = RegexUtil.extractPhonenum("8127984121391180106412313")
		Assert.assertEquals("", num2)
		
		def num3 = RegexUtil.extractPhonenum("1233=13911801064")
		Assert.assertEquals("", num3)
		
		def num4 = RegexUtil.extractPhonenum(" 123 18211801064 23423")
		Assert.assertEquals("18211801064", num4)
	}
	
	@Test void extractDate(){
		def date = RegexUtil.extractDate("asdfsafdak 2012-10-15 10:23asdfdsaf")
		Assert.assertEquals("2012-10-15", date)
		
		def date2 = RegexUtil.extractDate("asdfsafdak 2012-1-15 10:23asdfdsaf")
		Assert.assertEquals("2012-1-15", date2)
	}
}
