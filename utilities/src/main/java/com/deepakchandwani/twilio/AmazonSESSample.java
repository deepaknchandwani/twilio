package com.deepakchandwani.twilio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class AmazonSESSample {

    // Replace sender@example.com with your "From" address.
    // This address must be verified.
    static final String FROM = "john@amgmedical.net";
    static final String FROMNAME = "John Muney";
	
    // Replace recipient@example.com with a "To" address. If your account 
    // is still in the sandbox, this address must be verified.
    static final String TO = "deepakchandwani@yahoo.com";
    
    // Replace smtp_username with your Amazon SES SMTP user name.
    static final String SMTP_USERNAME = "AKIAIWPCYQEKJMT3E2KQ";
    
    // Replace smtp_password with your Amazon SES SMTP password.
    static final String SMTP_PASSWORD = "AoX9mW+jHYnq8tdbLlamjbdDv6SerV7lLrL0OD9Vz2yV";
    
    // The name of the Configuration Set to use for this message.
    // If you comment out or remove this variable, you will also need to
    // comment out or remove the header below.
    static final String CONFIGSET = "amg";
    
    // Amazon SES SMTP host name. This example uses the US West (Oregon) region.
    // See http://docs.aws.amazon.com/ses/latest/DeveloperGuide/regions.html#region-endpoints
    // for more information.
    static final String HOST = "email-smtp.us-east-1.amazonaws.com";
    
    // The port you will connect to on the Amazon SES SMTP endpoint. 
    static final int PORT = 587;
    
    static final String SUBJECT = "Free Healthcare Proposal for Everyone";
    
    static final String BODY = String.join(
    	    System.getProperty("line.separator"),
    	    "<h1>Amazon SES SMTP Email Test</h1>",
    	    "<p>This email was sent with Amazon SES using the ", 
    	    "<a href='https://github.com/javaee/javamail'>Javamail Package</a>",
    	    " for <a href='https://www.java.com'>Java</a>."
    	);

    public static void main(String[] args) throws Exception {
    	
    	    
    	BufferedReader br = null;
		FileReader fr = null;

		try {

			//br = new BufferedReader(new FileReader(FILENAME));
			fr = new FileReader("C:\\Users\\citi_deepak\\Documents\\amg\\EMAILS_Dos.valids.csv");
			br = new BufferedReader(fr);

			String sCurrentLine;
			
			int i=0;
			

			while ((sCurrentLine = br.readLine()) != null ) {
				i++;
				//if (i< (1098)) continue ;
				if (i< (84090)) continue ;
/*				System.out.println("Original : "+sCurrentLine);
 * 
*/				if(sCurrentLine.trim().length()==0) continue;
				String[] customerDetails=sCurrentLine.split(",");
				
				//System.out.println(i++ +". : "+sCurrentLine);
				if (customerDetails.length<3) continue;
				
				String custNameL=customerDetails[0];
				String custNameF=customerDetails[1];
				String email=customerDetails[2];
				
				//System.out.println(custNameL + " " + custNameF +" " + email);
 				
		    	  sendMail( email , custNameL , custNameF , i);

 				     /*  System.out.println(i-8
 				    		   + " " + custNameL
 				    		  + " " + custNameF
 				    		 + " " + email);*/
						
			//if(i==808)break;
			//		if(i==1700)break;
				if(i==9800)break;


			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}


	    	/*   sendMail( "jmuney@amgmedicalgroup.com"); 
	    	   sendMail( "johnmuney2@gmail.com"); 
	    	   
	    	   sendMail( "deepakchandwani@yahoo.com" );
	    	   
	    	   sendMail( "deepaknchandwani@gmail.com" );*/
	    	   
	    	  // sendMail( "shrikanth_hl@yahoo.com");  
	    	  // sendMail( "shrikanth.hl@gmail.com");  
	    	   
	    	  /* sendMail( "khart@amgmedicalgroup.com");
	    	   sendMail( "maheshchandwani@gmail.com");*/

    }

	private static void sendMail(String emailAddress, String last , String first , int loopCount) throws MessagingException,
			UnsupportedEncodingException, AddressException,
			NoSuchProviderException, InterruptedException {
		// Create a Properties object to contain connection configuration information.
		
		Thread.sleep(400);
    	Properties props = System.getProperties();
    	props.put("mail.transport.protocol", "smtp");
    	props.put("mail.smtp.port", PORT); 
    	props.put("mail.smtp.starttls.enable", "true");
    	props.put("mail.smtp.auth", "true");

        // Create a Session object to represent a mail session with the specified properties. 
    	Session session = Session.getDefaultInstance(props);

        // Create a message with the specified information. 
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(FROM,FROMNAME));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(emailAddress));
        msg.setSubject(SUBJECT);
        //msg.setContent(text,"text/html");
        
        
        // Add a configuration set header. Comment or delete the 
        // next line if you are not using a configuration set
        msg.setHeader("X-SES-CONFIGURATION-SET", CONFIGSET);
        
											        // Create the message part
											        BodyPart messageBodyPart = new MimeBodyPart();
											
											        // Now set the actual message
											        messageBodyPart.setText("This is message body");
											        
											       // messageBodyPart.setContent(textBegin+emailAddress+textFinish,"text/html");
											        messageBodyPart.setContent(
											        		
											        		s+ 
											        		last.replace("\"", " ").trim()
											        		+", "+ first.trim().replace("\"", " ")+
											        		Remaining
											        		+emailAddress+textFinish,"text/html");

											      
													       // Create a multipar message
													        Multipart multipart = new MimeMultipart();
													
													        // Set text message part
													        multipart.addBodyPart(messageBodyPart);
													
													        // Part two is attachment
														      /*  messageBodyPart = new MimeBodyPart();
														        String filename = "amg.pdf";
														        DataSource source = new FileDataSource(filename);
														        messageBodyPart.setDataHandler(new DataHandler(source));
														        messageBodyPart.setFileName(filename);
														        multipart.addBodyPart(messageBodyPart);*/
													
													        // Send the complete message parts
													        msg.setContent(multipart);
            
        // Create a transport.
        Transport transport = session.getTransport();
                    
        // Send the message.
        try
        {
            System.out.println("Sending...");
            
            // Connect to Amazon SES using the SMTP username and password you specified above.
            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
        	
            // Send the email.
           transport.sendMessage(msg, msg.getAllRecipients());
            System.out.println("----------------------------------------------------------------------------------------------------------------------------");
			//System.out.println(i +". : "+sCurrentLine);
		        System.out.println();
			System.out.println(loopCount+".   Name : " + last.replace("\"", " ").trim()+ ", "+ first.replace("\"", " ").trim() + "  email : " + emailAddress );
			System.out.println(" Sent Time : "+new Date());
			System.out.println("Email sent!");
	        System.out.println();

		        System.out.println("----------------------------------------------------------------------------------------------------------------------------");

			
            
        }
        catch (Exception ex) {
            System.out.println("The email was not sent.");
            System.out.println("Error message: " + ex.getMessage());
        }
        finally
        {
            // Close and terminate the connection.
            transport.close();
        }
	}
	

			
			static String  Remaining= 
			" <br />\n"+
			" <br /> Please, join me in fight to change our healthcare system for better. I have a proposal to\n"+
			" do just that. It is being presented in <a href=\"https://www.youtube.com/watch?v=kcnRapkJy3w\">\n"+
			"<img src=\"https://s3.amazonaws.com/amg-media/play.jpg\"alt=\"Watch YouTube Video\" \n"+
			" width=\"76\" height=\"26\" />"+
			" YOUTUBE VIDEO</a> and <a href=\"https://s3.amazonaws.com/amg-media/amg.doc\">DOC</a>\n"+
			" / <a href=\"https://s3.amazonaws.com/amg-media/amg.pdf\">PDF</a> /\n"+
			" <a href=\"https://s3.amazonaws.com/amg-media/amg.txt\">Text</a> form. You can either watch or read, whichever\n"+
			" you prefer. If you agree with me, please spread it.\n"+
			" <br />\n"+
			" <br /> Thank you\n"+
			" <br />\n"+
			" <br /> John Muney MD\n"+
			" <br /> President\n"+
			" <br /> AMG Medical Group </strong>\n"+
			" </p>\n"+
			" \n"+
			" </td>\n"+
			" </tr>\n"+
			" <!--\n"+
			" <tr>\n"+
			" <td align=\"center\" bgcolor=\"#eeeeee\">\n"+
			" <img src=\"./beauty3_files/3.jpg\" width=\"200\" height=\"140\" />\n"+
			" </td>\n"+
			" <td align=\"center\" bgcolor=\"#eeeeee\">\n"+
			" <img src=\"./beauty3_files/4.jpg\" width=\"200\" height=\"140\" />\n"+
			" </td>\n"+
			" </tr>\n"+
			" \n"+
			" <tr>\n"+
			" <td bgcolor=\"#eeeeee\">\n"+
			" <p style=\"font-size:10px; font-family:Arial, Helvetica, sans-serif\">\n"+
			" <strong>Salve, Nome Utente</strong>\n"+
			" <br /> Lorem Ipsum \u00e8 un testo segnaposto utilizzato nel settore della tipografia e della stampa. Lorem\n"+
			" Ipsum \u00e8 considerato il testo segnaposto standard sin dal sedicesimo secolo\n"+
			" <a href=\"#\">leggi \u00bb</a>\n"+
			" </p>\n"+
			" </td>\n"+
			" <td bgcolor=\"#eeeeee\">\n"+
			" <p style=\"font-size:10px; font-family:Arial, Helvetica, sans-serif\">\n"+
			" <strong>Salve, Nome Utente</strong>\n"+
			" <br /> Lorem Ipsum \u00e8 un testo segnaposto utilizzato nel settore della tipografia e della stampa. Lorem\n"+
			" Ipsum \u00e8 considerato il testo segnaposto standard sin dal sedicesimo secolo\n"+
			" <a href=\"#\">leggi \u00bb</a>\n"+
			" </p>\n"+
			" </td>\n"+
			" </tr>\n"+
			" <tr>\n"+
			" <td colspan=\"2\">\n"+
			" <p style=\"font-size:10px; font-family:Arial, Helvetica, sans-serif\">Salve, Nome Utente,\n"+
			" <br /> Lorem Ipsum \u00e8 un testo segnaposto utilizzato nel settore della tipografia e della stampa. Lorem\n"+
			" Ipsum \u00e8 considerato il testo segnaposto standard sin dal sedicesimo secolo Salve, Nome Utente</p>\n"+
			" <p style=\"font-size:10px; font-family:Arial, Helvetica, sans-serif\">\n"+
			" Lorem Ipsum \u00e8 un testo segnaposto utilizzato nel settore della tipografia e della stampa. Lorem Ipsum \u00e8 considerato il testo\n"+
			" segnaposto standard sin dal sedicesimo secolo Salve, Nome Utente</p>\n"+
			" <p style=\"font-size:10px; font-family:Arial, Helvetica, sans-serif\">\n"+
			" Lorem Ipsum \u00e8 un testo segnaposto utilizzato nel settore della tipografia e della stampa. Lorem Ipsum \u00e8 considerato il testo\n"+
			" segnaposto standard sin dal sedicesimo secolo Salve, Nome Utente</p>\n"+
			" </td>\n"+
			" </tr> \n"+
			" -->\n"+
			" </table>\n"+
			" </td>\n"+
			" <td valign=\"top\">\n"+
			" <table border=\"0\" cellspacing=\"4\" cellpadding=\"0\" width=\"200\">\n"+
			" <tr>\n"+
			" <td style=\"border-bottom: 1px dashed #ccc; padding:4px\">\n"+
			" <p style=\" font-family:Arial, Helvetica, sans-serif; font-size:18px\">\n"+
			" <a href=\"https://www.youtube.com/watch?v=kcnRapkJy3w\">"+
			" <img src=\"https://s3.amazonaws.com/amg-media/watch.jpg\"alt=\"Watch YouTube Video\" \n"+
			" width=\"230\" height=\"230\" />"
			+ "<strong>Watch Video</strong></a>\n"+
			" </p>\n"+
			" </td>\n"+
			" </tr>\n"+
			" <!--\n"+
			" <tr>\n"+
			" <td style=\"border-bottom: 1px dashed #ccc; padding:4px\">\n"+
			" <p style=\" font-family:Arial, Helvetica, sans-serif; font-size:12px\">\n"+
			" <a href=\"https://s3.amazonaws.com/amg-media/amg.txt\"><strong>Download Text</strong></a>\n"+
			" </p>\n"+
			" </td>\n"+
			" </tr>\n"+
			" \n"+
			" <tr>\n"+
			" <td style=\"border-bottom: 1px dashed #ccc; padding:4px\">\n"+
			" <p style=\" font-family:Arial, Helvetica, sans-serif; font-size:12px\">\n"+
			" <a href=\"https://s3.amazonaws.com/amg-media/amg.doc\"><strong>Download DOC File</strong></a>\n"+
			" </p>\n"+
			" </td>\n"+
			" </tr>\n"+
			" -->\n"+
			" <tr>\n"+
			" <td style=\"border-bottom: 1px dashed #ccc; padding:4px\">\n"+
			" <p style=\" font-family:Arial, Helvetica, sans-serif; font-size:12px\">\n"+
			" <a href=\"https://s3.amazonaws.com/amg-media/amg.pdf\"><strong>Download PDF File</strong></a>\n"+
			" </p>\n"+
			" </td>\n"+
			" </tr>\n"+
			"\n"+
			" <tr>\n"+
			" <td style=\"border-bottom: 1px dashed #ccc; padding:4px\">\n"+
			" <p style=\" font-family:Arial, Helvetica, sans-serif; font-size:12px\">\n"+
			" \n"+
			" <strong>\n"+
			" <img src=\"https://www.amgmedicalgroup.com/wp-content/uploads/2017/04/location.jpg\">\n"+
			" <span><br>AMG MEDICAL GROUP<br>\n"+
			" 5 8th Avenue, 6th Fl.\n"+
			" <br> New York, NY 10018<br><br></span>\n"+
			" <img src=\"https://www.amgmedicalgroup.com/wp-content/uploads/2017/04/phone.jpg\"><span><br><strong>Toll Free:</strong>877-AMG-1027<br><br></span>\n"+
			" <img src=\"https://www.amgmedicalgroup.com/wp-content/uploads/2017/04/emil.jpg\"><span><br><strong>Email:</strong>khart@amgmedicalgroup.com<br></span>\n"+
			" <br></strong>\n"+
			" </p>\n"+
			" </td>\n"+
			"\n"+
			" </tr>\n"+
			" <!--\n"+
			" <tr>\n"+
			" <td style=\"border-bottom: 1px dashed #ccc; padding:4px\">\n"+
			" <p style=\" font-family:Arial, Helvetica, sans-serif; font-size:10px\">\n"+
			" <strong>Salve, Nome Utente</strong>\n"+
			" <br /> Lorem Ipsum \u00e8 un testo segnaposto utilizzato nel settore della tipografia e della stampa. Lorem\n"+
			" Ipsum \u00e8 considerato il testo segnaposto standard sin dal sedicesimo\n"+
			" <br />\n"+
			" <a href=\"#\">secolo \u00bb</a>\n"+
			" </p>\n"+
			" </td>\n"+
			" </tr>\n"+
			" <tr>\n"+
			" <td style=\"border-bottom: 1px dashed #ccc; padding:4px\">\n"+
			" <p style=\" font-family:Arial, Helvetica, sans-serif; font-size:10px\">\n"+
			" <strong>Salve, Nome Utente</strong>\n"+
			" <br /> Lorem Ipsum \u00e8 un testo segnaposto utilizzato nel settore della tipografia e della stampa. Lorem\n"+
			" Ipsum \u00e8 considerato il testo segnaposto standard sin dal sedicesimo\n"+
			" <br />\n"+
			" <a href=\"#\">secolo \u00bb</a>\n"+
			" </p>\n"+
			" </td>\n"+
			" </tr>\n"+
			" <tr>\n"+
			" <td style=\"border-bottom: 1px dashed #ccc; padding:4px\">\n"+
			" <p style=\" font-family:Arial, Helvetica, sans-serif; font-size:10px\">\n"+
			" <strong>Salve, Nome Utente</strong>\n"+
			" <br /> Lorem Ipsum \u00e8 un testo segnaposto utilizzato nel settore della tipografia e della stampa. Lorem\n"+
			" Ipsum \u00e8 considerato il testo segnaposto standard sin dal sedicesimo\n"+
			" <br />\n"+
			" <a href=\"#\">secolo \u00bb</a>\n"+
			" </p>\n"+
			" </td>\n"+
			" </tr>\n"+
			"\n"+
			" -->\n"+
			" </table>\n"+
			" </td>\n"+
			" </tr>\n"+
			" </table>\n"+
			" </td>\n"+
			" </tr>\n"+
			" <!--\n"+
			" <tr>\n"+
			" <!-- bgcolor=\"#0191B6\" original AMG Color -->\n"+
			" <td height=\"100\" align=\"center\"\n"+
			" bgcolor=\"#FFF\">\n"+
			" <table width=\"670\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n"+
			"\n"+
			" \n"+
			" <tr>\n"+
			" <td align=\"right\">\n"+
			" <p style=\"font-family:Arial, \n"+
			" Helvetica, sans-serif; \n"+
			" font-size:10px; \n"+
			" color:#FFF\"> \n"+
			"\n"+
			" <!--\n"+
			" <ul style=\"list-style: none;\" >\n"+
			" <li><img src=\"https://www.amgmedicalgroup.com/wp-content/uploads/2017/04/location.jpg\">\n"+
			" <span><br>AMG MEDICAL GROUP<br>\n"+
			" 5 8th Avenue, 6th Fl.\n"+
			" <br> New York, NY 10018<br><br></span></li>\n"+
			" <li><img src=\"https://www.amgmedicalgroup.com/wp-content/uploads/2017/04/phone.jpg\"><span><br><strong>Toll Free:</strong>877-AMG-1027<br><br></span></li>\n"+
			" <li><img src=\"https://www.amgmedicalgroup.com/wp-content/uploads/2017/04/emil.jpg\"><span><br><strong>Email:</strong>khart@amgmedicalgroup.com</span></li>\n"+
			" </ul>\n"+
			" \n"+
			" </p>\n"+
			"\n"+
			" </td>\n"+
			" </tr> -->\n"+
			" </table>\n"+
			"\n"+
			" </td>\n"+
			" </tr>\n"+
			" </table>\n"+
			"\n"+
			" </td>\n"+
			" </tr>\n"+
			" </table>\n"+
			" <a href=\"mailto:john@amgmedical.net?subject=unsubcribe%20";
			
			public static String textBegin="<html>\n"+
					"\n"+
					"<head>\n"+
					" <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n"+
					"</head>\n"+
					"\n"+
					"<body>\n"+
					" <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n"+
					" <tr>\n"+
					" <td valign=\"top\" bgcolor=\"#ffffff\">\n"+
					"\n"+
					"\n"+
					" <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"600\" align=\"center\">\n"+
					" <tr>\n"+
					" <td>\n"+
					" <table width=\"670\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"#FFFFFF\">\n"+
					" <tr>\n"+
					" <td colspan=\"2\" align=\"center\">\n"+
					"\n"+
					" <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n"+
					" <tr>\n"+
					" <td align=\"left\" style=\"padding:10px\">\n"+
					" <!-- put left top-->\n"+
					" <a href=\"https://www.youtube.com/watch?v=kcnRapkJy3w\">\n"+
					" <img src=\"https://s3.amazonaws.com/amg-media/john.jpg\" alt=\"\" width=\"460\"\n"+
					" height=\"242\" />\n"+
					" </a>\n"+
					" </td>\n"+
					" <td>\n"+
					" <a href=\"https://www.amgmedicalgroup.com/\">\n"+
					" <img src=\"https://www.amgmedicalgroup.com/wp-content/themes/amg/assets/images/logo.png\" width=\"230\"\n"+
					" height=\"120\" />\n"+
					" </a>\n"+
					" </td>\n"+
					"\n"+
					" </tr>\n"+
					" </table>\n"+
					" </td>\n"+
					" </tr>\n"+
					" <tr>\n"+
					" <td valign=\"top\">\n"+
					" <table border=\"0\" cellspacing=\"2\" cellpadding=\"4\">\n"+
					" <tr>\n"+
					" <td colspan=\"2\" bgcolor=\"#FFF\">\n"+
					" <!-- bottom left -->\n"+
					" <p style=\"font-family:Arial, Helvetica, sans-serif;\n"+
					" font-size:24px\">\n"+
					" <br />\n"+
					
					
					" <strong> Dear Healthcare Consumer,\n"+Remaining;
	
	public static String textFinish="\">to unsubcribe click</a>\n"+
			"</body>\n"+
			"\n"+
			"</html>";
    
    public static String text="<html>\n"+
    		"\n"+
    		"<head>\n"+
    		" <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n"+
    		"</head>\n"+
    		"\n"+
    		"<body>\n"+
    		" <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n"+
    		" <tr>\n"+
    		" <td valign=\"top\" bgcolor=\"#ffffff\">\n"+
    		"\n"+
    		"\n"+
    		" <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"600\" align=\"center\">\n"+
    		" <tr>\n"+
    		" <td>\n"+
    		" <table width=\"670\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"#FFFFFF\">\n"+
    		" <tr>\n"+
    		" <td colspan=\"2\" align=\"center\">\n"+
    		"\n"+
    		" <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n"+
    		" <tr>\n"+
    		" <td align=\"left\" style=\"padding:10px\">\n"+
    		" <!-- put left top-->\n"+
    		" <a href=\"https://www.youtube.com/watch?v=kcnRapkJy3w\">\n"+
    		" <img src=\"https://s3.amazonaws.com/amg-media/john.jpg\" alt=\"\" width=\"460\"\n"+
    		" height=\"242\" />\n"+
    		" </a>\n"+
    		" </td>\n"+
    		" <td>\n"+
    		" <a href=\"https://www.amgmedicalgroup.com/\">\n"+
    		" <img src=\"https://www.amgmedicalgroup.com/wp-content/themes/amg/assets/images/logo.png\" width=\"230\"\n"+
    		" height=\"120\" />\n"+
    		" </a>\n"+
    		" </td>\n"+
    		"\n"+
    		" </tr>\n"+
    		" </table>\n"+
    		" </td>\n"+
    		" </tr>\n"+
    		" <tr>\n"+
    		" <td valign=\"top\">\n"+
    		" <table border=\"0\" cellspacing=\"2\" cellpadding=\"4\">\n"+
    		" <tr>\n"+
    		" <td colspan=\"2\" bgcolor=\"#FFF\">\n"+
    		" <!-- bottom left -->\n"+
    		" <p style=\"font-family:Arial, Helvetica, sans-serif;\n"+
    		" font-size:18px\">\n"+
    		" <br />\n"+
    		" <strong> Dear Healthcare Consumer,\n"+
    		" <br />\n"+
    		" <br /> Please, join me in fight to change our healthcare system for better. I have a proposal to\n"+
    		" do just that. It is being presented in <a href=\"https://www.youtube.com/watch?v=kcnRapkJy3w\">\n"+
    		" YOUTUBE VIDEO</a> and <a href=\"https://s3.amazonaws.com/amg-media/amg.doc\">DOC</a>\n"+
    		" / <a href=\"https://s3.amazonaws.com/amg-media/amg.pdf\">PDF</a> /\n"+
    		" <a href=\"https://s3.amazonaws.com/amg-media/amg.txt\">Text</a> form. You can either watch or read, whichever\n"+
    		" you prefer. If you agree with me, please spread it.\n"+
    		" <br />\n"+
    		" <br /> Thank you\n"+
    		" <br />\n"+
    		" <br /> John Muney MD\n"+
    		" <br /> President\n"+
    		" <br /> AMG Medical Group </strong>\n"+
    		" </p>\n"+
    		" \n"+
    		" </td>\n"+
    		" </tr>\n"+
    		" <!--\n"+
    		" <tr>\n"+
    		" <td align=\"center\" bgcolor=\"#eeeeee\">\n"+
    		" <img src=\"./beauty3_files/3.jpg\" width=\"200\" height=\"140\" />\n"+
    		" </td>\n"+
    		" <td align=\"center\" bgcolor=\"#eeeeee\">\n"+
    		" <img src=\"./beauty3_files/4.jpg\" width=\"200\" height=\"140\" />\n"+
    		" </td>\n"+
    		" </tr>\n"+
    		" \n"+
    		" <tr>\n"+
    		" <td bgcolor=\"#eeeeee\">\n"+
    		" <p style=\"font-size:10px; font-family:Arial, Helvetica, sans-serif\">\n"+
    		" <strong>Salve, Nome Utente</strong>\n"+
    		" <br /> Lorem Ipsum \u00e8 un testo segnaposto utilizzato nel settore della tipografia e della stampa. Lorem\n"+
    		" Ipsum \u00e8 considerato il testo segnaposto standard sin dal sedicesimo secolo\n"+
    		" <a href=\"#\">leggi \u00bb</a>\n"+
    		" </p>\n"+
    		" </td>\n"+
    		" <td bgcolor=\"#eeeeee\">\n"+
    		" <p style=\"font-size:10px; font-family:Arial, Helvetica, sans-serif\">\n"+
    		" <strong>Salve, Nome Utente</strong>\n"+
    		" <br /> Lorem Ipsum \u00e8 un testo segnaposto utilizzato nel settore della tipografia e della stampa. Lorem\n"+
    		" Ipsum \u00e8 considerato il testo segnaposto standard sin dal sedicesimo secolo\n"+
    		" <a href=\"#\">leggi \u00bb</a>\n"+
    		" </p>\n"+
    		" </td>\n"+
    		" </tr>\n"+
    		" <tr>\n"+
    		" <td colspan=\"2\">\n"+
    		" <p style=\"font-size:10px; font-family:Arial, Helvetica, sans-serif\">Salve, Nome Utente,\n"+
    		" <br /> Lorem Ipsum \u00e8 un testo segnaposto utilizzato nel settore della tipografia e della stampa. Lorem\n"+
    		" Ipsum \u00e8 considerato il testo segnaposto standard sin dal sedicesimo secolo Salve, Nome Utente</p>\n"+
    		" <p style=\"font-size:10px; font-family:Arial, Helvetica, sans-serif\">\n"+
    		" Lorem Ipsum \u00e8 un testo segnaposto utilizzato nel settore della tipografia e della stampa. Lorem Ipsum \u00e8 considerato il testo\n"+
    		" segnaposto standard sin dal sedicesimo secolo Salve, Nome Utente</p>\n"+
    		" <p style=\"font-size:10px; font-family:Arial, Helvetica, sans-serif\">\n"+
    		" Lorem Ipsum \u00e8 un testo segnaposto utilizzato nel settore della tipografia e della stampa. Lorem Ipsum \u00e8 considerato il testo\n"+
    		" segnaposto standard sin dal sedicesimo secolo Salve, Nome Utente</p>\n"+
    		" </td>\n"+
    		" </tr> \n"+
    		" -->\n"+
    		" </table>\n"+
    		" </td>\n"+
    		" <td valign=\"top\">\n"+
    		" <table border=\"0\" cellspacing=\"4\" cellpadding=\"0\" width=\"200\">\n"+
    		" <tr>\n"+
    		" <td style=\"border-bottom: 1px dashed #ccc; padding:4px\">\n"+
    		" <p style=\" font-family:Arial, Helvetica, sans-serif; font-size:28px\">\n"+
    		" <a href=\"https://www.youtube.com/watch?v=kcnRapkJy3w\"><strong>Watch Video</strong></a>\n"+
    		" </p>\n"+
    		" </td>\n"+
    		" </tr>\n"+
    		" <tr>\n"+
    		" <td style=\"border-bottom: 1px dashed #ccc; padding:4px\">\n"+
    		" <p style=\" font-family:Arial, Helvetica, sans-serif; font-size:12px\">\n"+
    		" <a href=\"https://s3.amazonaws.com/amg-media/amg.txt\"><strong>Download Text</strong></a>\n"+
    		" </p>\n"+
    		" </td>\n"+
    		" </tr>\n"+
    		" <tr>\n"+
    		" <td style=\"border-bottom: 1px dashed #ccc; padding:4px\">\n"+
    		" <p style=\" font-family:Arial, Helvetica, sans-serif; font-size:12px\">\n"+
    		" <a href=\"https://s3.amazonaws.com/amg-media/amg.doc\"><strong>Download DOC File</strong></a>\n"+
    		" </p>\n"+
    		" </td>\n"+
    		" </tr>\n"+
    		" <tr>\n"+
    		" <td style=\"border-bottom: 1px dashed #ccc; padding:4px\">\n"+
    		" <p style=\" font-family:Arial, Helvetica, sans-serif; font-size:12px\">\n"+
    		" <a href=\"https://s3.amazonaws.com/amg-media/amg.pdf\"><strong>Download PDF File</strong></a>\n"+
    		" </p>\n"+
    		" </td>\n"+
    		" </tr>\n"+
    		"\n"+
    		" <tr>\n"+
    		" <td style=\"border-bottom: 1px dashed #ccc; padding:4px\">\n"+
    		" <p style=\" font-family:Arial, Helvetica, sans-serif; font-size:12px\">\n"+
    		" \n"+
    		" <strong>\n"+
    		" <img src=\"https://www.amgmedicalgroup.com/wp-content/uploads/2017/04/location.jpg\">\n"+
    		" <span><br>AMG MEDICAL GROUP<br>\n"+
    		" 5 8th Avenue, 6th Fl.\n"+
    		" <br> New York, NY 10018<br><br></span>\n"+
    		" <img src=\"https://www.amgmedicalgroup.com/wp-content/uploads/2017/04/phone.jpg\"><span><br><strong>Toll Free:</strong>877-AMG-1027<br><br></span>\n"+
    		" <img src=\"https://www.amgmedicalgroup.com/wp-content/uploads/2017/04/emil.jpg\"><span><br><strong>Email:</strong>khart@amgmedicalgroup.com<br></span>\n"+
    		" <br></strong>\n"+
    		" </p>\n"+
    		" </td>\n"+
    		"\n"+
    		" </tr>\n"+
    		" <!--\n"+
    		" <tr>\n"+
    		" <td style=\"border-bottom: 1px dashed #ccc; padding:4px\">\n"+
    		" <p style=\" font-family:Arial, Helvetica, sans-serif; font-size:10px\">\n"+
    		" <strong>Salve, Nome Utente</strong>\n"+
    		" <br /> Lorem Ipsum \u00e8 un testo segnaposto utilizzato nel settore della tipografia e della stampa. Lorem\n"+
    		" Ipsum \u00e8 considerato il testo segnaposto standard sin dal sedicesimo\n"+
    		" <br />\n"+
    		" <a href=\"#\">secolo \u00bb</a>\n"+
    		" </p>\n"+
    		" </td>\n"+
    		" </tr>\n"+
    		" <tr>\n"+
    		" <td style=\"border-bottom: 1px dashed #ccc; padding:4px\">\n"+
    		" <p style=\" font-family:Arial, Helvetica, sans-serif; font-size:10px\">\n"+
    		" <strong>Salve, Nome Utente</strong>\n"+
    		" <br /> Lorem Ipsum \u00e8 un testo segnaposto utilizzato nel settore della tipografia e della stampa. Lorem\n"+
    		" Ipsum \u00e8 considerato il testo segnaposto standard sin dal sedicesimo\n"+
    		" <br />\n"+
    		" <a href=\"#\">secolo \u00bb</a>\n"+
    		" </p>\n"+
    		" </td>\n"+
    		" </tr>\n"+
    		" <tr>\n"+
    		" <td style=\"border-bottom: 1px dashed #ccc; padding:4px\">\n"+
    		" <p style=\" font-family:Arial, Helvetica, sans-serif; font-size:10px\">\n"+
    		" <strong>Salve, Nome Utente</strong>\n"+
    		" <br /> Lorem Ipsum \u00e8 un testo segnaposto utilizzato nel settore della tipografia e della stampa. Lorem\n"+
    		" Ipsum \u00e8 considerato il testo segnaposto standard sin dal sedicesimo\n"+
    		" <br />\n"+
    		" <a href=\"#\">secolo \u00bb</a>\n"+
    		" </p>\n"+
    		" </td>\n"+
    		" </tr>\n"+
    		"\n"+
    		" -->\n"+
    		" </table>\n"+
    		" </td>\n"+
    		" </tr>\n"+
    		" </table>\n"+
    		" </td>\n"+
    		" </tr>\n"+
    		" <!--\n"+
    		" <tr>\n"+
    		" <!-- bgcolor=\"#0191B6\" original AMG Color -->\n"+
    		" <td height=\"100\" align=\"center\"\n"+
    		" bgcolor=\"#FFF\">\n"+
    		" <table width=\"670\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n"+
    		"\n"+
    		" \n"+
    		" <tr>\n"+
    		" <td align=\"right\">\n"+
    		" <p style=\"font-family:Arial, \n"+
    		" Helvetica, sans-serif; \n"+
    		" font-size:10px; \n"+
    		" color:#FFF\"> \n"+
    		"\n"+
    		" <!--\n"+
    		" <ul style=\"list-style: none;\" >\n"+
    		" <li><img src=\"https://www.amgmedicalgroup.com/wp-content/uploads/2017/04/location.jpg\">\n"+
    		" <span><br>AMG MEDICAL GROUP<br>\n"+
    		" 5 8th Avenue, 6th Fl.\n"+
    		" <br> New York, NY 10018<br><br></span></li>\n"+
    		" <li><img src=\"https://www.amgmedicalgroup.com/wp-content/uploads/2017/04/phone.jpg\"><span><br><strong>Toll Free:</strong>877-AMG-1027<br><br></span></li>\n"+
    		" <li><img src=\"https://www.amgmedicalgroup.com/wp-content/uploads/2017/04/emil.jpg\"><span><br><strong>Email:</strong>khart@amgmedicalgroup.com</span></li>\n"+
    		" </ul>\n"+
    		" \n"+
    		" </p>\n"+
    		"\n"+
    		" </td>\n"+
    		" </tr> -->\n"+
    		" </table>\n"+
    		"\n"+
    		" </td>\n"+
    		" </tr>\n"+
    		" </table>\n"+
    		"\n"+
    		" </td>\n"+
    		" </tr>\n"+
    		" </table>\n"+
    		"\n"+
    		"</body>\n"+
    		"\n"+
    		"</html>";
    
    static String s="<html>\n"+
    		"\n"+
    		"<head>\n"+
    		" <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n"+
    		"</head>\n"+
    		"\n"+
    		"<body>\n"+
    		" <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n"+
    		" <tr>\n"+
    		" <td valign=\"top\" bgcolor=\"#ffffff\">\n"+
    		"\n"+
    		"\n"+
    		" <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"600\" align=\"center\">\n"+
    		" <tr>\n"+
    		" <td>\n"+
    		" <table width=\"670\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"#FFFFFF\">\n"+
    		" <tr>\n"+
    		" <td colspan=\"2\" align=\"center\">\n"+
    		"\n"+
    		" <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n"+
    		" <tr>\n"+
    		" <td align=\"left\" style=\"padding:10px\">\n"+
    		" <!-- put left top-->\n"+
    		" <a href=\"https://www.youtube.com/watch?v=kcnRapkJy3w\">\n"+
    		" <img src=\"https://s3.amazonaws.com/amg-media/john.jpg\" alt=\"\" width=\"460\"\n"+
    		" height=\"242\" />\n"+
    		
			" <img src=\"https://s3.amazonaws.com/amg-media/play.jpg\"alt=\"Watch YouTube Video\"\n"+
			" width=\"220\" height=\"67\" />"
+
    		" </a>\n"+
    		" </td>\n"+
    		" <td>\n"+
    		" <a href=\"https://www.amgmedicalgroup.com/\">\n"+
    		" <img src=\"https://www.amgmedicalgroup.com/wp-content/themes/amg/assets/images/logo.png\" width=\"230\"\n"+
    		" height=\"120\" />\n"+
    		" </a>\n"+
    		" </td>\n"+
    		"\n"+
    		" </tr>\n"+
    		" </table>\n"+
    		" </td>\n"+
    		" </tr>\n"+
    		" <tr>\n"+
    		" <td valign=\"top\">\n"+
    		" <table border=\"0\" cellspacing=\"2\" cellpadding=\"4\">\n"+
    		" <tr>\n"+
    		" <td colspan=\"2\" bgcolor=\"#FFF\">\n"+
    		" <!-- bottom left -->\n"+
    		" <p style=\"font-family:Arial, Helvetica, sans-serif;\n"+
    		" font-size:18px\">\n"+
    		" <br />\n"+
    		" <strong> Dear ";
}