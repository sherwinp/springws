package springws.usecase;


import java.io.IOException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ClientII {

	private static final String NAMESPACE = "http://www.springwssample.org/types";

    public static void main(String[] args){
    	try {
			SOAPConnection soapConn = SOAPConnectionFactory.newInstance().createConnection();
			
			SOAPMessage request = MessageFactory.newInstance().createMessage();
			
			SOAPBody soapBody = request.getSOAPPart().getEnvelope().getBody();
			
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			docBuilderFactory.setNamespaceAware(true);
			Document document = docBuilderFactory.newDocumentBuilder().newDocument();
	        Element messageElement = document.createElementNS(NAMESPACE, "SendMessageRequest");
	        messageElement.appendChild(addElementWithValue(document, "country","Germany"));
	        document.appendChild(messageElement);
			soapBody.addDocument(document);
			request.saveChanges();
			
			URL WSURL = new URL("http://localhost:8080/springws/ws/mraDataExchange");
			request.setProperty("SOAPAction","SendMessage");
			SOAPMessage response = soapConn.call(request, WSURL);		
			SOAPBody responseBody = response.getSOAPBody();
			
		} catch (UnsupportedOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SOAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    private static Element addElementWithValue(Document document, String element, String value){
        Element child = document.createElementNS(NAMESPACE, element);
        child.appendChild(document.createTextNode(value));
        return child;
    }
}
