package com.ssnc.poWels;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URI;
import java.util.Arrays;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.*;
import org.apache.http.client.*;
import org.apache.http.client.config.*;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.*;
import org.apache.http.message.BasicHeader;

import com.ssnc.poWels.config.sstopcCommonUtil;

@Component
public class runApplication implements CommandLineRunner{
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Override
	public void run(String... args) throws Exception {
		
		sstopcCommonUtil scu = new sstopcCommonUtil();
		try {
			
			String apiURL = scu.getWelsApprovalUrl();
			
			String empno = "empno";
			String requestHtml = "<html>request data html</html>";
			
			// make SOAP Body Data
			String BodyData = scu.getBodyData(empno, requestHtml);
			// make SOAP Xml
			String xml = scu.getSOAPXml(empno, BodyData);
			
			StringEntity xmlEntity = new StringEntity(xml);
			xmlEntity.setContentType(new BasicHeader("Content-Type", "text/xml; charset=utf-8"));
			
			//▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩ NetworkCredential JAVA
			URI uri = new URI(apiURL);
			HttpPost httpPost = new HttpPost(uri);
			httpPost.addHeader("Content-type", "text/xml; charset=utf-8");
			httpPost.setHeader(HttpHeaders.AUTHORIZATION, "Basic " + scu.getAuthorization());
			httpPost.addHeader("SOAPAction", "http://sap.com/xi/WebService/soap1.1");
			httpPost.setEntity(xmlEntity);
			
			RequestConfig requestConfig = RequestConfig.custom()
	                .setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM))
	                .setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC))
	                .build();

            HttpClient httpclient = HttpClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .setRedirectStrategy(new LaxRedirectStrategy())
                .build();
            
            HttpResponse response = httpclient.execute(httpPost);
            
           //▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩ get response data 
            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));
            
		    StringBuffer result = new StringBuffer();
		    String line = "";
		    while ((line = rd.readLine()) != null) {
		        result.append(line);
		    }			
			
		    //▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩ String to Document
			String xmlString = result.toString();
			Document docXml = null;
			try {
			      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			      DocumentBuilder builder = factory.newDocumentBuilder();
			      InputSource is = new InputSource( new StringReader( xmlString ) );
			      docXml = builder.parse( is );
			}catch( Exception ex ) {
				ex.printStackTrace();
			}
			
			//▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩ to extract xml element 

			NodeList nodes = docXml.getElementsByTagName("SOAP:Envelope");
			Element element = (Element) nodes.item(0);
			
			NodeList bodyList = element.getElementsByTagName("SOAP:Body");
			Element body = (Element) bodyList.item(0);
			
			NodeList ns1List = body.getElementsByTagName("ns1:MT_CMWE0010_ERP_R");
			Element ns1 = (Element) ns1List.item(0);
			
			
			//▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩ PO DATA 
		    NodeList headerList = ns1.getElementsByTagName("IF_HEADER");
		    Element header = (Element) headerList.item(0);
		    
		    /*▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩ get PO IF_Header data
		    String code = header.getElementsByTagName("PO_CODE").item(0).getFirstChild().getTextContent();
		    String sdate = header.getElementsByTagName("PO_SDATE").item(0).getFirstChild().getTextContent();
		    String stime = header.getElementsByTagName("PO_STIME").item(0).getFirstChild().getTextContent();
		    String user = header.getElementsByTagName("PO_USER").item(0).getFirstChild().getTextContent();
		    String msgid = header.getElementsByTagName("PO_MSGID").item(0).getFirstChild().getTextContent();
		    */
		    
		    //▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩ RESPONSE CODE 
		    String statis = ns1.getElementsByTagName("STATIS").item(0).getFirstChild().getTextContent(); //error code
		    String msg = ns1.getElementsByTagName("MESSAGE").item(0).getFirstChild().getTextContent(); //error message
			
		    String[] arrResult = msg.split(":");
		    
		    //▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩ make Wels Url
			String strWelsUrl = scu.getWelsUrl_Domain(arrResult);
            logger.debug("strWelsUrl >> "+strWelsUrl);
            
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
