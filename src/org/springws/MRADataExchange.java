package org.springws;


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


@Endpoint
public class MRADataExchange {

    private static final String NAMESPACE = "http://www.springwssample.org/types";
   
    @PayloadRoot(localPart = "SendMessageRequest", namespace = "http://www.springwssample.org/types")
    @ResponsePayload
    public Element SendMessage(@RequestPayload Element request, MessageContext context) throws ParserConfigurationException, SOAPException {
    
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Element messageElement = document.createElementNS(NAMESPACE, "SendMessageResponse");
        messageElement.appendChild(addElementWithValue(document, "city", "Hamburg"));
        messageElement.appendChild(addElementWithValue(document, "country", "Germany"));
        messageElement.appendChild(addElementWithValue(document, "isDeliver", "true"));
        
        return messageElement;
    }
   
    private Element addElementWithValue(Document document, String element, String value){
        Element child = document.createElementNS(NAMESPACE, element);
        child.appendChild(document.createTextNode(value));
        return child;
    }
}