package com.deepakchandwani.twilio;

import java.net.URI;
import java.net.URISyntaxException;

import com.twilio.Twilio;
import com.twilio.http.HttpMethod;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.PhoneNumber;

public class VoiceCallFromGetResource { 
	
	    
  public static void main(String[] args) throws URISyntaxException  {
	  
	   pushMessage();  }


public static void pushMessage() throws URISyntaxException {
	Twilio.init(AES.ACCOUNT_SID, AES.AUTH_TOKEN);

	    Call call = Call
				        .creator(
				        			/*new PhoneNumber("+15164020450"), 
				        			new PhoneNumber("+19173302704"),*/ 
				        			
				        			
				        			
				        		/*	
				        		 * 7678611012
				        		 * new PhoneNumber("+917678611012"), 
				        			new PhoneNumber("+919167187479"),*/
				        		
				        		/*new PhoneNumber("+917217796853"), 
			        			new PhoneNumber("+919167187479"),*/
			        			
				        		new PhoneNumber("+19174634744"),
			        			new PhoneNumber("+919167187479"),
				        		
				        		
			        			

//			        			new PhoneNumber("+19173302704"),
//			        			new PhoneNumber("+15164020450"), 
				        		
				        		/*new PhoneNumber("+19173302704"), 
			        			new PhoneNumber("+919167187479"),*/
				        			
			        			//				        			new URI("https://eaea93f0.ngrok.io/vishnuxml.xml")

				        			//new URI("https://a5f6060b.ngrok.io/vishnuxmlNavratri1.xml")
				        			//new URI("https://a5f6060b.ngrok.io/conference.xml")
				        			new URI("https://mukeshmatai.ngrok.io/vishnumishra/api/usa/?name=Mummy%20Ji")

				        		)
				        
	        .setMethod(HttpMethod.POST)
	        							// .setSendDigits("1234#")  optional
	        .create();
}
}