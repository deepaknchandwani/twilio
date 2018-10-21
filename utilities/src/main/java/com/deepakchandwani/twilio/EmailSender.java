package com.deepakchandwani.twilio;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
public class EmailSender {
	
	public static void main(String[] args) {
		sendMail();
		
	}
	
	public static Multipart getMailText() throws MessagingException {

		String emailText="<!DOCTYPE html>\n"+
				"<html>\n"+
				"<head>\n"+
				"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"+
				"<style>\n"+
				"body, html {\n"+
				" height: 100%;\n"+
				" margin: 0;\n"+
				"}\n"+
				"\n"+
				".bg {\n"+
				" /* The image used */\n"+
				" background-image: url(\"https://s3.amazonaws.com/citicode/montu.gif\");\n"+
				"\n"+
				" /* Full height */\n"+
				" height: 100%; \n"+
				"\n"+
				" /* Center and scale the image nicely */\n"+
				" background-position: center;\n"+
				" background-repeat: no-repeat;\n"+
				" background-size: cover;\n"+
				"}\n"+
				"</style>\n"+
				"</head>\n"+
				"<body>\n"+
				"\n"+
				"<div class=\"bg\">\n"+
				"\n"+
				"<h3>montymatai@gmail.com</h3>\n"+
				"<img src=\"https://s3.amazonaws.com/citicode/montu.gif\" alt=\"Mukesh Matai\">\n"+
				"</div>\n"+
				"<img src=\"https://mukeshmatai.ngrok.io/vishnumishra/api/user/?id=deepakchandwani1@yahoo.com\" alt=\"Mukesh Matai\">\n"+
				"\n"+
				"</body>\n"+
				"</html>\n"+
				"\n"+
				"\n"+
				"";	
		 BodyPart messageBodyPart = new MimeBodyPart();
			
	        // Now set the actual message
	        messageBodyPart.setText("This is message body");
	        
	       // messageBodyPart.setContent(textBegin+emailAddress+textFinish,"text/html");
	        messageBodyPart.setContent(    		
	        		emailText        		
	        		,"text/html");

	      
			       // Create a multipar message
			        Multipart multipart = new MimeMultipart();
			
			        // Set text message part
			        multipart.addBodyPart(messageBodyPart);
			
			  return multipart;      

	}
	
    public static void sendMail() {
    	
        //Setting up configurations for the email connection to the Google SMTP server using TLS
        Properties props = new Properties();
        props.put("mail.smtp.host", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.from", "deepakchandwani@yahoo.com");

        //Establishing a session with required user details
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("deepaknchandwani@gmail.com", "Saimehar1972@");
            }
        });
        try {
            //Creating a Message object to set the email content
            MimeMessage msg = new MimeMessage(session);
            //Storing the comma seperated values to email addresses
            
//            String to = "recepient1@email.com,recepient2@gmail.com";
            String to = "xyz@kuntasmnbkjhh.com";

            
            /*Parsing the String with defualt delimiter as a comma by marking the boolean as true and storing the email
            addresses in an array of InternetAddress objects*/
            InternetAddress[] address = InternetAddress.parse(to, true);
            //Setting the recepients from the address variable
            msg.setRecipients(Message.RecipientType.TO, address);
            String timeStamp = new SimpleDateFormat("yyyymmdd_hh-mm-ss").format(new Date());
            msg.setSubject("Sample Mail  Generated for Mukesh Matai from PPT : " + timeStamp);
            msg.setSentDate(new Date());
            msg.setContent(getMailText());
            //msg.setText("Sampel System Generated mail");
            
            msg.setHeader("XPriority", "1");
            Transport.send(msg);
            System.out.println("Mail has been sent successfully");
        } catch (MessagingException mex) {
            System.out.println("Unable to send an email" + mex);
        }
    }
}