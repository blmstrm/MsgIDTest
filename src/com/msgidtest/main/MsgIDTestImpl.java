package com.msgidtest.main;
import javax.jws.HandlerChain;
import javax.jws.WebService;
import javax.xml.ws.soap.Addressing;

import com.msgidtest.interfaces.MsgIDTest;
@Addressing(enabled=true, required=true)
@WebService(endpointInterface = "com.msgidtest.interfaces.MsgIDTest")
@HandlerChain(file="handler-chain-server.xml")
public class MsgIDTestImpl implements MsgIDTest {

	@Override
	public String getMessage() {
		return "A message from the server.";
	}
}
 