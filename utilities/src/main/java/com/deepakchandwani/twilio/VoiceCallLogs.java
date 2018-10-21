package com.deepakchandwani.twilio;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

// Install the Java helper library from twilio.com/docs/java/install

 
 
import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Call;

public class VoiceCallLogs {
   
    public static void main(String[] args) throws JsonProcessingException {
        Twilio.init(AES.ACCOUNT_SID, AES.AUTH_TOKEN);
        ResourceSet<Call> calls = Call.reader().read();
        ObjectMapper om = new ObjectMapper();
        om.enable(SerializationFeature.INDENT_OUTPUT); //pretty print
        int i=0;
         for(Call record : calls) {
        	
             //System.out.println(om.writeValueAsString(record));
             
             System.out.println(++i + ".    Call To : "  +record.getTo() +"\t"+record.getStartTime() + "\t " +record.getEndTime());
             System.out.println("\t Duration " + record.getDuration() + " seconds  \t   Cost   "+ record.getPrice() +  " " + record.getPriceUnit());
             System.out.println();
        }
    }
}
