package com.msgidtest.client;


import java.net.MalformedURLException;
import java.net.URL;

import javax.jws.HandlerChain;
import javax.xml.namespace.QName;

import javax.xml.ws.Service;

import com.msgidtest.handlers.CustomHandlerResolver;
import com.msgidtest.interfaces.MsgIDTest;

@HandlerChain(file="handler-chain-client.xml")
public class MsgClient {
	
	public static void main (String [] args){
		simpleSOAPTest();
	}

	private static void simpleSOAPTest(){
		URL url;
		try {
			url = new URL("http://localhost:8080/MsgIDTest/test?wsdl");

			QName qname = new QName("http://main.msgidtest.com/", "MsgIDTestImplService");

			Service service = Service.create(url, qname);
	
			service.setHandlerResolver(new CustomHandlerResolver());

			MsgIDTest test = service.getPort(MsgIDTest.class);

			System.out.println("\n"+test.getMessage());

		} catch (MalformedURLException e) {
				e.printStackTrace();
		}
	}
}
