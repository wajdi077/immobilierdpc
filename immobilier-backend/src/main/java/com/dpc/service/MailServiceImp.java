package com.dpc.service;

import java.io.File;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.dpc.domain.Artisant;
import com.dpc.domain.Mail;
import com.dpc.repository.ArtisantRepository;
import com.dpc.repository.MailService;
@Service

public class MailServiceImp implements MailService {
	

	 @Autowired
	    JavaMailSender mailSender;
	 
	 
	 
	 

	 @Autowired
	    private ArtisantRepository artisantRepository;


	
		
		public void EnvoyerEmail(String to , String subject,String msg) {
			
			
			
			   MimeMessage message = mailSender.createMimeMessage();  

		        try {
		            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);


		            mimeMessageHelper.setSubject(subject);
		            mimeMessageHelper.setFrom("immo.devis18@gmail.com");
		            mimeMessageHelper.setTo(to);
	                mimeMessageHelper.setText(msg,true);
	                // Add a resource as an attachment
                     mailSender.send(message);	            
		            
		 
		        } catch (MessagingException e) {
		            e.printStackTrace();
		        }
			
		}

	
	
		  public void sendEmail(Object object) {
			  
			  Artisant artisan = (Artisant) object;
		 
		 
		        MimeMessagePreparator preparator = getContentWtihAttachementMessagePreparator(artisan);
		 
		        try {
		            mailSender.send(preparator);
		            System.out.println("Message With Attachement has been sent.............................");
		            preparator = getContentAsInlineResourceMessagePreparator(artisan);
		            mailSender.send(preparator);
		            System.out.println("Message With Inline Resource has been sent.........................");
		        } catch (MailException ex) {
		            System.err.println(ex.getMessage());
		        }
		    }
		 
		  private MimeMessagePreparator getContentWtihAttachementMessagePreparator(final Artisant artisan) {
			  
		        MimeMessagePreparator preparator = new MimeMessagePreparator() {
		 
		            public void prepare(MimeMessage mimeMessage) throws Exception {
		                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true,"UTF-8");
		 
		                helper.setSubject("Votre demander été bien enregistré ");
		                helper.setFrom("immo.devis18@gmail.com");
		                helper.setTo(artisan.getEmail());
		                System.out.println(artisan.getEmail());
		                String content = "Dear " + artisan.getNom()+" + " + artisan.getPrenom()
		               + ", thank you for placing order. Your order id is " + artisan.getEmail() +" + "+ artisan.getUser().getPassword();
		                System.out.println(content);
		 
		                helper.setText(content);
		                
		 
		                // Add a resource as an attachment
		                helper.setText("<html><body><p>" + content + "</p><img src='cid:company-logo'></body></html>", true);
		                helper.addInline("company-logo", new ClassPathResource("immo devis.bmp"));
		              
		 
		            }
		        };
		        return preparator;
		    }
		 
		  private MimeMessagePreparator getContentAsInlineResourceMessagePreparator(final Artisant artisan) {
			  
		        MimeMessagePreparator preparator = new MimeMessagePreparator() {
		 
		            public void prepare(MimeMessage mimeMessage) throws Exception {
		                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

		                helper.setSubject("Votre demander été bien enregistré ");
		                helper.setFrom("immo.devis18@gmail.com");
		                helper.setTo(artisan.getEmail());
		                System.out.println(artisan.getEmail());
		            
		                String content = "Dear " + artisan.getNom()+" + " + artisan.getPrenom()
		               + ", thank you for placing order. Your order id is " + artisan.getEmail() +" + "+ artisan.getUser().getPassword();
		 
		                System.out.println(content);
		 
		                // Add an inline resource.
		                // use the true flag to indicate you need a multipart message
		                helper.setText("<html><body><p>" + content + "</p><img src='cid:company-logo'></body></html>", true);
		                helper.addInline("company-logo", new ClassPathResource("immo devis.bmp"));
		            }
		        };
		        return preparator;
		        
		    }

	
		  @Autowired
		  public void EmailService(JavaMailSender mailSender) {
		    this.mailSender = mailSender;
		  }
		  
		  @Async
		  public void sendEmail(SimpleMailMessage email) {
		    mailSender.send(email);
		  }



		@Override
		public void EnvoyerEmail(Artisant artisan) {
			// TODO Auto-generated method stub
			
		}

}
