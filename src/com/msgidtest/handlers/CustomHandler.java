package com.msgidtest.handlers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class CustomHandler implements SOAPHandler <SOAPMessageContext> {

	private boolean isServer = false;

	@Override
	public boolean handleMessage(SOAPMessageContext context) {

		Boolean isOutGoing = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

		if(!isOutGoing && isServer){
			printMessageID(context);
		}else if(isOutGoing && !isServer){
			printMessageID(context);
		}

		return true;
	}

	@Override
	public boolean handleFault(SOAPMessageContext context) {
		return true;
	}

	@Override
	public void close(MessageContext context) {

	}

	@Override
	public Set<QName> getHeaders() {

		return null;
	}

	private void printMessageID(SOAPMessageContext ctx){
		try {
			
			if(isServer){
				System.out.println("MessageID on server:");
			}else{
				System.out.println("MessageID in client:");
			}

			SOAPMessage smsg = ctx.getMessage();

			try {

				ByteArrayOutputStream bout = new ByteArrayOutputStream();

				smsg.writeTo(bout);

				String msg = bout.toString("UTF-8");

				Pattern pattern = Pattern.compile("uuid:[^<]*");

				Matcher matcher = pattern.matcher(msg);
				while(matcher.find()){
					System.out.println(matcher.group());
				}


			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SOAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setIsServer(){
		this.isServer = true;
	}

}


