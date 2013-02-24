package com.duobot

class Bootstrap {
	static void main(String[] args){
		
		
		Shanai shanai = new Shanai()
		shanai.setIndexPath("/gyxm/grjzxm/?page=1")
		shanai.setPageTpl("/gyxm/grjzxm/?page=#PAGE_NO")
		shanai.setStart(1)
		shanai.setLimit(5)
//		shanai.setAllowSubmit(false)
		shanai.doScrap()
		
		

		
		
		Shilehui p = new Shilehui()
		p.setIndexPath("/AssistUI/Index.aspx?PassType=3")
		p.setPageTpl("/AssistUI/Index.aspx?p=&ct=&co=&Sid=-2&AgeS=&AgeS2=&PassType=3&keyword=&Disease=&Page=#PAGE_NO")
		p.setStart(1)
		p.setLimit(5)
		p.doScrap()
		
		Shilehui o = new Shilehui()
		o.setIndexPath("/AssistUI/Index.aspx?PassType=4")
		o.setPageTpl("/AssistUI/Index.aspx?p=&ct=&co=&Sid=-2&AgeS=&AgeS2=&PassType=4&keyword=&Disease=&Page=#PAGE_NO")
		o.setStart(1)
		o.setLimit(5)
		o.doScrap()
//		o.dohello()
		
		Ikcw i = new Ikcw()
		i.setPageTpl "../jiuzhu/wolaibangni.aspx?page=#PAGE_NO"
		i.setIndexPath "../jiuzhu/wolaibangni.aspx"
		i.setStart(1)
		i.setLimit(2)
		i.doScrap()
		
		Yaolan yaolan = new Yaolan()
		yaolan.setIndexPath("/board_h_40_1.aspx")
		yaolan.setPageTpl("/board_h_40_#PAGE_NO.aspx")
		yaolan.setStart(1)
		yaolan.setLimit(10)
		yaolan.setAllowSubmit(true)
		yaolan.doScrap()
		
	}
}
