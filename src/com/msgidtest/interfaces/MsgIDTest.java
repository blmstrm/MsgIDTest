package com.msgidtest.interfaces;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT,
use = SOAPBinding.Use.LITERAL,
parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface MsgIDTest {

	@WebMethod String getMessage();
}
