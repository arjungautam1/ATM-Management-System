package updatedatm;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class PinNumber {
	private Scanner s;

	public void changePin() throws Exception
	{
		s = new Scanner(System.in);
		System.out.println("Enter your old pin number: ");
		int pin_no4=s.nextInt();
		System.out.println("Enter the new pin number:");
		int pin_no5=s.nextInt();
		String query5="update account set pin="+pin_no5+" where pin="+pin_no4+"";  
		Connection con=ConnectionManager.jdbcConnection();
		Statement st5=con.createStatement();
		st5.executeUpdate(query5);
			 try{
	            String host ="smtp.gmail.com" ;
	            String user = "laserarjun876@gmail.com";
	            String pass = "123456789arjun";
	            String to = "laserarjun@gmail.com";
	            String from = "laserarjun876@gmail.com";
	            String subject = "Pin number";
	            String messageText = " Your Atm Pin Number has been changed .Please Keep it safe .\\n\\tNever share your pin with anyone";
	            boolean sessionDebug = false;

	            Properties props = System.getProperties();

	            props.put("mail.smtp.starttls.enable", "true");
	            props.put("mail.smtp.host", host);
	            props.put("mail.smtp.port", "587");
	            props.put("mail.smtp.auth", "true");
	            props.put("mail.smtp.starttls.required", "true");

	    //    java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
	            Session mailSession = Session.getDefaultInstance(props, null);
	            mailSession.setDebug(sessionDebug);
	            Message msg = new MimeMessage(mailSession);
	            msg.setFrom(new InternetAddress(from));
	            InternetAddress[] address = {new InternetAddress(to)};
	            msg.setRecipients(Message.RecipientType.TO, address);
	            msg.setSubject(subject); msg.setSentDate(new Date());
	            msg.setText(messageText);

	           Transport transport=mailSession.getTransport("smtp");
	           transport.connect(host, user, pass);
	           transport.sendMessage(msg, msg.getAllRecipients());
	           transport.close();
	           System.out.println("message send successfully");
	        }catch(Exception e)
	        {
	            System.out.println(e);
	        }

	    }
	
	}


