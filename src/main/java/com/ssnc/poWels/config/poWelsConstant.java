package com.ssnc.poWels.config;

public class poWelsConstant {
    public final static int MONDAY = 0;
    public final static int TUESDAY = 1;
    public final static int WEDNESDDAY = 2;
    public final static int THURSDAY = 3;
    public final static int FRIDAY = 4;
    public final static int SATURDAY = 5;
    public final static int SUNDAY = 6;

    public final static String NotFound ="404";
    public final static String NotFoundMessage ="Service Not Found";    

    public final static String InternalServerError ="500";    
    public final static String InternalServerErrorMessage ="Internal Server Error";    

    public final static String JobOK ="200";
    public final static String JobOKMessage ="Working Done";
    
    public final static String InsertOK ="210";
    public final static String InsertOKMessage ="SAVE - Working Done";    
    public final static String DeleteOK ="230";
    public final static String DeleteOKMessage ="DELETE - Working Done";    
    public final static String UpdateOK ="240";
    public final static String UpdateOKMessage ="UPDATE - Working Done";    
    
    public final static String InsertNotOK ="910";
    public final static String InsertNotOKMessage ="Fail-Duplicate Key"; 
    public final static String EmptyNotOK ="920";
    public final static String EmptyNotOKMessage ="Empty Data - Working Fail"; 
    public final static String DeleteNotOK ="930";
    public final static String DeleteNotOKMessage ="DELETE - Working Fail";  
    public final static String UpdateNotOK ="940";
    public final static String UpdateNotOKMessage ="UPDATE - Working Fail";   
    
    public final static String MethodArgumentNotOK ="940";
    public final static String MethodArgumentNotOKMessage ="Invalid Arguments";    

    public final static String LengthNotOK ="950";
    public final static String LengthNotOKMessage ="Invalid Length(Data) for DB";    

    public final static String AgentVersionNotOK = "860";
    public final static String AgentVersionNotOKMessage ="Not Mached AgentVersion. Please Re-Install Agent";   
    
    public final static String AgentAlreadyInstalledNotOK = "861";
    public final static String AgentAlreadyInstalledNotOKMessage ="Already Installed Agent. Please Contact to Administrator";   
    
    public final static String InsertProcessNotOK = "760";
    public final static String InsertProcessNotOKMessage ="Already Requested for TakeoutPC. Please Check Your Requests";   

    public final static String GroupwareInterfaceOK = "770";
    public final static String GroupwareInterfaceOKMessage ="Working Done.";   
    public final static String GroupwareInterfaceNotOK = "771";
    public final static String GroupwareInterfaceNotOKMessage ="Working Fail.";   

    
    public final static String IPApiInfoCallNotOK = "610";
    public final static String IPApiInfoCallNotOKMessage ="Fail to connect IPAPI Service. Please Contact to Administrator";   

    public final static String UserNotOK = "620";
    public final static String UserNotOKMessage ="User Not Found. Please Contact to Administrator";   

    public final static String MailNotOK = "630";
    public final static String MailNotOKMessage ="Mail Sending Error. Please Contact to Administrator";   

	public final static String ApvNotOK = "640";
    public final static String ApvNotOKMessage ="Approve Error. Please Contact to Administrator";   

	public final static String NeedGWNotOK = "650";
    public final static String NeedGWNotOKMessage ="Need to Approve. Please Contact to Administrator";   


}
