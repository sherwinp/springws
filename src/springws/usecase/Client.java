package springws.usecase;


import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.logging.Logger;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.soap.security.wss4j.Wss4jSecurityInterceptor;

import com.sun.xml.internal.ws.streaming.TidyXMLStreamReader;

public class Client {

	final static Logger LOGGER = Logger.getLogger(Client.class.getName());

//sample request XML
    private static String MESSAGE =
        "<SendMessageRequest xmlns=\"http://www.springwssample.org/types\"><country>Germany</country></SendMessageRequest>";

    public static void main(String[] args){
    	ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        StreamSource source = new StreamSource(new StringReader(MESSAGE));
        OutputStream out = new ByteArrayOutputStream();
        StreamResult result = new StreamResult(out);

//WebServiceTemplate provides the functionality for sending and receiving webservice messages.
        WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
        
        Wss4jSecurityInterceptor interceptor = (Wss4jSecurityInterceptor) ctx.getBean("wsSecurityInterceptor");
        setUsernameToken(interceptor, "mra", "mra1");
        webServiceTemplate.setInterceptors(new ClientInterceptor[]{interceptor});
        
        webServiceTemplate.sendSourceAndReceiveToResult("http://localhost:8080/springws/ws/mraDataExchange", source, result);

        LOGGER.info(out.toString());
    }
    private static void setUsernameToken(Wss4jSecurityInterceptor interceptor, String user, String pass) {
        interceptor.setSecurementUsername(user);
        interceptor.setSecurementPassword(pass);
    }
}
