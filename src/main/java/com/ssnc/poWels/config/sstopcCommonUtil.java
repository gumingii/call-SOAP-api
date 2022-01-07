package com.ssnc.poWels.config;

import java.io.FileReader;
import java.util.Properties;

public class sstopcCommonUtil {
	
	//▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩
	// Wels Url setting
	//▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩
	public String getWelsUrl_Domain(String[] arrResult) throws Exception{
		FileReader resources= new FileReader("e1groupware.properties"); 
    	Properties properties = new Properties();
    	properties.load(resources);
    	
    	String domain = properties.getProperty("welsUrl_Domain");
    	String url =  domain+"/Approval/Services/OpenApprovalDoc.aspx?OpenURL=" + domain  + "/Approval/Workdoc/OpenDocument.aspx?wdid=";
    	url = url + arrResult[1].replace(" ", "");
    	url = url + "&foid="+ getheader_FormId() + "&mode=r&schema=A007";
		
		return url;
	}
	
	public String gethttpPortBaseUrl() throws Exception{
		FileReader resources= new FileReader("e1groupware.properties"); 
    	Properties properties = new Properties();
    	properties.load(resources);
    	
    	String url = properties.getProperty("http_portBaseUrl");
    	return url;
	}
	
	public String getWelsApprovalUrl() throws Exception{
		FileReader resources= new FileReader("e1groupware.properties"); 
    	Properties properties = new Properties();
    	properties.load(resources);
    	
    	String url = properties.getProperty("http_portBaseUrl");
    	
		url += "?senderParty=&"
			+ "senderService="+getSenderService()+"&"
			+ "receiverParty=&"
			+ "receiverService=&"
			+ "interface="+getInterface()+"&"
			+ "interfaceNamespace="+getInterfaceNamespace();
		
		return url;
	}
	
	
	//▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩
	// Header setting
	//▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩
	public String getheader_FormId() throws Exception{
		FileReader resources= new FileReader("e1groupware.properties"); 
    	Properties properties = new Properties();
    	properties.load(resources);
      
    	String str = properties.getProperty("header_FormId");
    	return str;
	}
	
	public String getheader_LegacyId() throws Exception{
		FileReader resources= new FileReader("e1groupware.properties"); 
    	Properties properties = new Properties();
    	properties.load(resources);
      
    	String str = properties.getProperty("header_LegacyId");
    	return str;
	}
	
	public String getBodyData(String empno, String requestHtml) throws Exception{
		
		String xml = "<USERID>{UserId}</USERID>"+
					 "<FORMID>{FormId}</FORMID>"+
					 "<LEGACYID>{LegacyId}</LEGACYID>"+
					 "<REQXML>{ReqXml}</REQXML>";
		
		 xml = xml.replace("{UserId}", empno)
				 .replace("{FormId}", getheader_FormId())
				 .replace("{LegacyId}", getheader_LegacyId())
				 .replace("{ReqXml}", requestHtml);
		return xml;
	}
	
	//▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩
	// XML setting
	//▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩▩
	public String getSOAPXml(String empno, String BodyData) throws Exception {
		
		String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"+
			 	 "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:cmw=\"{InterfaceNamespace}\">"+
			 	 	"<soapenv:Header/>"+
			 	 	"<soapenv:Body>"+
			 	 		"<{xmlInterface}>"+
			 	 			BodyData+
				 	 	"</{xmlInterface}>"+
				 	 "</soapenv:Body>"+
			 	 "</soapenv:Envelope>";
   
		xml = xml.replace("{InterfaceNamespace}", getInterfaceNamespace())
				 .replace("{xmlInterface}", getXmlInterface());
		
	   return xml;

	}
	public String getXmlInterface() throws Exception {
		FileReader resources= new FileReader("e1groupware.properties"); 
    	Properties properties = new Properties();
    	properties.load(resources);

    	String str = properties.getProperty("xml_interface");
    	return str;
	}
	public String getSenderService() throws Exception {
		FileReader resources= new FileReader("e1groupware.properties"); 
    	Properties properties = new Properties();
    	properties.load(resources);
    	
    	String str = properties.getProperty("senderService");
    	return str;
	}
	public String getInterface() throws Exception {
		FileReader resources= new FileReader("e1groupware.properties"); 
    	Properties properties = new Properties();
    	properties.load(resources);

    	String str = properties.getProperty("interface");
    	return str;
	}
	public String getInterfaceNamespace() throws Exception {
		FileReader resources= new FileReader("e1groupware.properties"); 
    	Properties properties = new Properties();
    	properties.load(resources);

    	String str = properties.getProperty("interfaceNamespace");
    	return str;
	}
	public String getAuthorization() throws Exception {
		FileReader resources= new FileReader("e1groupware.properties"); 
    	Properties properties = new Properties();
    	properties.load(resources);
    	
    	String str = properties.getProperty("authorization");
    	return str;
	}
	
}
	