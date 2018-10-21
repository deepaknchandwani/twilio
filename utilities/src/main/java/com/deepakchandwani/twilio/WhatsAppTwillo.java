package com.deepakchandwani.twilio;

import com.twilio.Twilio; 
import com.twilio.converter.Promoter; 
import com.twilio.rest.api.v2010.account.Message; 
import com.twilio.type.PhoneNumber; 
 
import java.net.URI; 
import java.math.BigDecimal; 
 
public class WhatsAppTwillo { 
    
    public static void main(String[] args) { 
        Twilio.init(AES.ACCOUNT_SID, AES.AUTH_TOKEN); 
        Message message = Message.creator( 
                new com.twilio.type.PhoneNumber("whatsapp:+919871472496"), 
                new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),  
                "Your appointment is coming up on Oct 3 at 8:50PM")      
            .create(); 
 
        System.out.println(message.getSid()); 
    } 
}