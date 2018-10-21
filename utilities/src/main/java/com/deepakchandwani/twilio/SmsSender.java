package com.deepakchandwani.twilio;

// Install the Java helper library from twilio.com/docs/libraries/java
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SmsSender {


	  public static void main(String[] args) {
        Twilio.init(AES.ACCOUNT_SID, AES.AUTH_TOKEN);

        Message message = Message
                .creator(
                		
                		new PhoneNumber("+19173302704"), // to
                		//new PhoneNumber("+13476592454"), // to
                        new PhoneNumber("+12017201515"), // from
                        //"Where's Wallace?"
                        "At We Care we are focused on providing your family with the most qualified, trustworthy and experienced candidate specialist available. \r\n" + 
                        "We accomplish this through extensive interviewing and diligent screening, which is the foundation of our Standard Screening Process.\r\n" + 
                        "Positions we place\r\n" + 
                        "Baby sitters \r\n" + 
                        "Nanny\r\n" + 
                        "Housekeeper \r\n" + 
                        "Waiter/Host\r\n" + 
                        "Dish washer \r\n" + 
                        "Cashier \r\n" + 
                        "Front Desk\r\n" + 
                        "Cooks\r\n" + 
                        "Elderly caregivers \r\n" + 
                        "Full Time (5 days / week) Full-Day\r\n" + 
                        "#Call / Text +15164020450\r\n" + 
                        "Email : mataimonty@gmail.com\r\n" + 
                        "Midtown New York, NY\r\n" + 
                        "\r\n" + 
                        ""
                		)
                .create();

        System.out.println(message.getSid());
    }
}

