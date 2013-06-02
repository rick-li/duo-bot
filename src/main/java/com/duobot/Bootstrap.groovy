package com.duobot

class Bootstrap {
	static void main(String[] args){
		
		
		
//		return;
	/*	
		Ikcw i = new Ikcw()
		i.setPageTpl "../jiuzhu/wolaibangni.aspx?page=#PAGE_NO"
		i.setIndexPath "../jiuzhu/wolaibangni.aspx"
		i.setStart(1)
		i.setLimit(2)
		i.doScrap()
*/
		
		Yaolan yaolan = new Yaolan()
		yaolan.setIndexPath("/board_h_40_1.aspx")
		yaolan.setPageTpl("/board_h_40_#PAGE_NO.aspx")
		yaolan.setStart(1)
		yaolan.setLimit(10)
		yaolan.setAllowSubmit(true)
		yaolan.doScrap()
//return
		
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
//		return;

		Yayjy yayjy4 = new Yayjy();
		yayjy4.setIndexPath("/index.asp?id=4&district=");
		yayjy4.setPageTpl("/index.asp?id=4&district=&page=#PAGE_NO");
		yayjy4.setStart(1);
		yayjy4.setLimit(1)
//		yayjy4.setAllowSubmit(false)
		yayjy4.doScrap()
//		return;
		
		Yayjy yayjy5 = new Yayjy();
		yayjy5.setIndexPath("/index.asp?id=5&district=");
		yayjy5.setPageTpl("/index.asp?id=5&district=&page=#PAGE_NO");
		yayjy5.setStart(1);
		yayjy5.setLimit(1)
//		yayjy5.setAllowSubmit(false)
		yayjy5.doScrap()
		

				
		Shanai shanai = new Shanai()
		shanai.setIndexPath("/gyxm/grjzxm/?page=1")
		shanai.setPageTpl("/gyxm/grjzxm/?page=#PAGE_NO")
		shanai.setStart(1)
		shanai.setLimit(3)
//		shanai.setAllowSubmit(false)
		shanai.doScrap()
		
		AixinQiao aixinQiao = new AixinQiao();
		aixinQiao.setPageTpl("/Article/ShowNew.asp?page=#PAGE_NO")
		aixinQiao.setIndexPath("/Article/ShowNew.asp");
		aixinQiao.setStart(1);
		aixinQiao.setLimit(2);
//		aixinQiao.setAllowSubmit(false);
		aixinQiao.doScrap();

		
		
		
	}
}
