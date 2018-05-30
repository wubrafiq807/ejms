package com.nazdaq.ejms.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.nazdaq.ejms.model.Candidate;
import com.nazdaq.ejms.model.CandidateProfile;
import com.nazdaq.ejms.model.User;

public class SendEmail implements Constants{	

	public static void main(String[] args) throws MessagingException {
		// TODO Auto-generated method stub
		//SendEmail se = new SendEmail();		
	}
	
	public void profileFormSubmitEmailToCandidate(JavaMailSender mailSender, User user, Candidate candidate, String url, String ccEmailAddresss, String commonEmailAddress) throws MessagingException, javax.mail.MessagingException{
		
		String candMail=candidate.getCandEmail();
		
		if(candMail.equals(null)||candMail.equals("")){
			candMail=commonEmailAddress;
		}
		
		// Create a new StringBuilder.
		StringBuilder builder = new StringBuilder();
		
		builder.append("<body topmargin=\"0\" rightmargin=\"0\" bottommargin=\"0\" leftmargin=\"0\" marginwidth=\"0\" marginheight=\"0\" width=\"100%\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; width: 100%; height: 100%; -webkit-font-smoothing: antialiased; text-size-adjust: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; line-height: 100%;\r\n" + 
				"	background-color: #F0F0F0;\r\n" + 
				"	color: #000000;\"\r\n" + 
				"	bgcolor=\"#F0F0F0\"\r\n" + 
				"	text=\"#000000\">\r\n" +  
				"<!-- Set message background color one again -->\r\n" + 
				"<table width=\"100%\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; width: 100%;\" class=\"background\"><tr><td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0;\"\r\n" + 
				"	bgcolor=\"#F0F0F0\">\r\n" + 
				"\r\n" + 
				"<!-- WRAPPER -->\r\n" + 
				"<!-- Set wrapper width (twice) -->\r\n" + 
				"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\"\r\n" + 
				"	width=\"560\" style=\"border-collapse: collapse; border-spacing: 0; padding: 0; width: inherit;\r\n" + 
				"	max-width: 560px;\" class=\"wrapper\">\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%;\r\n" + 
				"			padding-top: 20px;\r\n" + 
				"			padding-bottom: 20px;\">\r\n" + 
				"\r\n" + 
				"			<a target=\"_blank\" style=\"text-decoration: none;\"\r\n" + 
				"				href=\"https://www.syn-ergy.com\"><img border=\"0\" vspace=\"0\" hspace=\"0\"\r\n" + 
				"				src='http://www.syn-ergy.com/wp-content/themes/synergy/img/logo.jpg'" + 
				"				width=\"200\" height=\"60\"\r\n" + 
				"				alt=\"Logo\" title=\"Logo\" style=\"\r\n" + 
				"				color: #000000;\r\n" + 
				"				font-size: 10px; margin: 0; padding: 0; outline: none; text-decoration: none; -ms-interpolation-mode: bicubic; border: none; display: block;\" /></a>\r\n" + 
				"\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"<!-- End of WRAPPER -->\r\n" + 
				"</table>\r\n" + 
				"\r\n" + 
				"<!-- WRAPPER / CONTEINER -->\r\n" + 
				"<!-- Set conteiner background color -->\r\n" + 
				"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\"\r\n" + 
				"	bgcolor=\"#FFFFFF\"\r\n" + 
				"	width=\"560\" style=\"border-collapse: collapse; border-spacing: 0; padding: 0; width: inherit;\r\n" + 
				"	max-width: 560px;\" class=\"container\">\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%; font-size: 24px; font-weight: bold; line-height: 130%;\r\n" + 
				"			padding-top: 25px;\r\n" + 
				"			color: #000000;\r\n" + 
				"			font-family: sans-serif;\" class=\"header\">\r\n" + 
				"				You are registered &amp; your login info.\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0;\r\n" + 
				"			padding-top: 20px;\" class=\"hero\">				\r\n" + 
				"					\r\n" + 
				"					<tr style=\"background-color:#D9E2DF\">\r\n" + 
				"						<td align=\"left\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%; font-size: 17px; font-weight: 400; line-height: 160%;\r\n" + 
				"							padding-top: 25px; \r\n" + 
				"							color: #000000;\r\n" + 
				"							font-family: sans-serif;\" class=\"paragraph\">");
		
		
		builder.append("Dear Mr./Ms. " + candidate.getCandName() + ", <br/>");
		builder.append("Congratulation! Welcome to "+candidate.getCompany().getName()+". Your Candidate Id, user Id, Password are given bellow: ");
		builder.append("<br/> <br/>");
		builder.append("Candidate ID: " + candidate.getCandId() + " <br/>");
		builder.append("User Id :" + candidate.getCandId() + " <br/>");
		builder.append("Password :" + user.getPassword() + " <br/> <br/>");
		
		builder.append("You are requested to submit the <b>Candidate Profile Form<b> duly filled by you. <br/> <br/>");
		
		builder.append("Please don't lose information and don't share with others.");
		
		builder.append("</td>\r\n" + 
				"					</tr>\r\n" + 
				"				\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"	\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%;\r\n" + 
				"			padding-top: 25px;\r\n" + 
				"			padding-bottom: 5px;\" class=\"button\"><a\r\n" + 
				"			href=\"https://github.com/konsav/email-templates/\" target=\"_blank\" style=\"text-decoration: underline;\">\r\n" + 
				"				<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" style=\"max-width: 240px; min-width: 120px; border-collapse: collapse; border-spacing: 0; padding: 0;\"><tr><td align=\"center\" valign=\"middle\" style=\"padding: 12px 24px; margin: 0; text-decoration: underline; border-collapse: collapse; border-spacing: 0; border-radius: 4px; -webkit-border-radius: 4px; -moz-border-radius: 4px; -khtml-border-radius: 4px;\"\r\n" + 
				"					bgcolor=\"#E9703E\"><a target=\"_blank\" style=\"text-decoration: underline;\r\n" + 
				"					color: #FFFFFF; font-family: sans-serif; font-size: 17px; font-weight: 400; line-height: 120%;\"\r\n" + 
				"					href=" + url + ">\r\n" + 
				"						Click Here to View\r\n" + 
				"					</a>\r\n" + 
				"			</td></tr></table></a>\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"	<!-- LINE -->\r\n" + 
				"	<tr>	\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%;\r\n" + 
				"			padding-top: 25px;\" class=\"line\"><hr\r\n" + 
				"			color=\"#E0E0E0\" align=\"center\" width=\"100%\" size=\"1\" noshade style=\"margin: 0; padding: 0;\" />\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"	<!-- LINE -->\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%; font-size: 17px; font-weight: 400; line-height: 160%;\r\n" + 
				"			padding-top: 20px;\r\n" + 
				"			padding-bottom: 25px;\r\n" + 
				"			color: #000000;\r\n" + 
				"			font-family: sans-serif;\" class=\"paragraph\">\r\n" + 
				"				For Support: Send Email to <a href=\"mailto:support@ourteam.com\" target=\"_blank\" style=\"color: #127DB3; font-family: sans-serif; font-size: 17px; font-weight: 400; line-height: 160%;\">abu.taleb@dynamicsrv.com</a>\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"<!-- End of WRAPPER -->\r\n" + 
				"</table>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"<!-- End of SECTION / BACKGROUND -->\r\n" + 
				"</td></tr></table>\r\n" + 
				"\r\n" + 
				"</body>");
		
		// Convert to string. 
		String result = builder.toString();
		sendMailTo(mailSender, candMail,"New Candidate Registered! ("+ candidate.getCandId() +") For Mr./Ms "+ candidate.getCandName() + "", result, ccEmailAddresss);
	}
	
	public void profileFormSubmitEmailToHR(JavaMailSender mailSender, Candidate candidate, String url, String ccEmailAddresss, String commonEmailAddress) throws MessagingException, javax.mail.MessagingException{
		
		// Create a new StringBuilder.
		StringBuilder builder = new StringBuilder();
		
		builder.append("<body topmargin=\"0\" rightmargin=\"0\" bottommargin=\"0\" leftmargin=\"0\" marginwidth=\"0\" marginheight=\"0\" width=\"100%\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; width: 100%; height: 100%; -webkit-font-smoothing: antialiased; text-size-adjust: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; line-height: 100%;\r\n" + 
				"	background-color: #F0F0F0;\r\n" + 
				"	color: #000000;\"\r\n" + 
				"	bgcolor=\"#F0F0F0\"\r\n" + 
				"	text=\"#000000\">\r\n" +  
				"<!-- Set message background color one again -->\r\n" + 
				"<table width=\"100%\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; width: 100%;\" class=\"background\"><tr><td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0;\"\r\n" + 
				"	bgcolor=\"#F0F0F0\">\r\n" + 
				"\r\n" + 
				"<!-- WRAPPER -->\r\n" + 
				"<!-- Set wrapper width (twice) -->\r\n" + 
				"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\"\r\n" + 
				"	width=\"560\" style=\"border-collapse: collapse; border-spacing: 0; padding: 0; width: inherit;\r\n" + 
				"	max-width: 560px;\" class=\"wrapper\">\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%;\r\n" + 
				"			padding-top: 20px;\r\n" + 
				"			padding-bottom: 20px;\">\r\n" + 
				"\r\n" + 
				"			<a target=\"_blank\" style=\"text-decoration: none;\"\r\n" + 
				"				href=\"https://www.syn-ergy.com\"><img border=\"0\" vspace=\"0\" hspace=\"0\"\r\n" + 
				"				src='http://www.syn-ergy.com/wp-content/themes/synergy/img/logo.jpg'" + 
				"				width=\"200\" height=\"60\"\r\n" + 
				"				alt=\"Logo\" title=\"Logo\" style=\"\r\n" + 
				"				color: #000000;\r\n" + 
				"				font-size: 10px; margin: 0; padding: 0; outline: none; text-decoration: none; -ms-interpolation-mode: bicubic; border: none; display: block;\" /></a>\r\n" + 
				"\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"<!-- End of WRAPPER -->\r\n" + 
				"</table>\r\n" + 
				"\r\n" + 
				"<!-- WRAPPER / CONTEINER -->\r\n" + 
				"<!-- Set conteiner background color -->\r\n" + 
				"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\"\r\n" + 
				"	bgcolor=\"#FFFFFF\"\r\n" + 
				"	width=\"560\" style=\"border-collapse: collapse; border-spacing: 0; padding: 0; width: inherit;\r\n" + 
				"	max-width: 560px;\" class=\"container\">\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%; font-size: 24px; font-weight: bold; line-height: 130%;\r\n" + 
				"			padding-top: 25px;\r\n" + 
				"			color: #000000;\r\n" + 
				"			font-family: sans-serif;\" class=\"header\">\r\n" + 
				"				Candidate Profile Submitted.\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0;\r\n" + 
				"			padding-top: 20px;\" class=\"hero\">				\r\n" + 
				"					\r\n" + 
				"					<tr style=\"background-color:#D9E2DF\">\r\n" + 
				"						<td align=\"left\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%; font-size: 17px; font-weight: 400; line-height: 160%;\r\n" + 
				"							padding-top: 25px; \r\n" + 
				"							color: #000000;\r\n" + 
				"							font-family: sans-serif;\" class=\"paragraph\">");
		
		
		builder.append("Dear Concern, <br/>");
		builder.append("I have to filled up all required data of candidate profile and submiited to you using Synergy EJMS. I know that all filled information are truth.");
		builder.append("<br/> <br/>");		
		builder.append("Best Regurds <br/>");		
		builder.append(candidate.getCandName() + " <br/>");
		builder.append(candidate.getCandId() +"");
		
		builder.append("</td>\r\n" + 
				"					</tr>\r\n" + 
				"				\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"	\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%;\r\n" + 
				"			padding-top: 25px;\r\n" + 
				"			padding-bottom: 5px;\" class=\"button\"><a\r\n" + 
				"			href=\"https://github.com/konsav/email-templates/\" target=\"_blank\" style=\"text-decoration: underline;\">\r\n" + 
				"				<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" style=\"max-width: 240px; min-width: 120px; border-collapse: collapse; border-spacing: 0; padding: 0;\"><tr><td align=\"center\" valign=\"middle\" style=\"padding: 12px 24px; margin: 0; text-decoration: underline; border-collapse: collapse; border-spacing: 0; border-radius: 4px; -webkit-border-radius: 4px; -moz-border-radius: 4px; -khtml-border-radius: 4px;\"\r\n" + 
				"					bgcolor=\"#E9703E\"><a target=\"_blank\" style=\"text-decoration: underline;\r\n" + 
				"					color: #FFFFFF; font-family: sans-serif; font-size: 17px; font-weight: 400; line-height: 120%;\"\r\n" + 
				"					href=" + url + ">\r\n" + 
				"						Click Here to View\r\n" + 
				"					</a>\r\n" + 
				"			</td></tr></table></a>\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"	<!-- LINE -->\r\n" + 
				"	<tr>	\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%;\r\n" + 
				"			padding-top: 25px;\" class=\"line\"><hr\r\n" + 
				"			color=\"#E0E0E0\" align=\"center\" width=\"100%\" size=\"1\" noshade style=\"margin: 0; padding: 0;\" />\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"	<!-- LINE -->\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%; font-size: 17px; font-weight: 400; line-height: 160%;\r\n" + 
				"			padding-top: 20px;\r\n" + 
				"			padding-bottom: 25px;\r\n" + 
				"			color: #000000;\r\n" + 
				"			font-family: sans-serif;\" class=\"paragraph\">\r\n" + 
				"				For Support: Send Email to <a href=\"mailto:support@ourteam.com\" target=\"_blank\" style=\"color: #127DB3; font-family: sans-serif; font-size: 17px; font-weight: 400; line-height: 160%;\">abu.taleb@dynamicsrv.com</a>\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"<!-- End of WRAPPER -->\r\n" + 
				"</table>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"<!-- End of SECTION / BACKGROUND -->\r\n" + 
				"</td></tr></table>\r\n" + 
				"\r\n" + 
				"</body>");
		
		// Convert to string. 
		String result = builder.toString();
		sendMailTo(mailSender, commonEmailAddress, "Candidate Profile Submitted! ("+ candidate.getCandId() +") For Mr./Ms "+ candidate.getCandName() + "", result, candidate.getCandEmail());
	}
	
	public void candidateJoinedMailToAdminAndIT(JavaMailSender mailSender, Candidate candidate, CandidateProfile profile, String url, String photoLink, String toEmailAddress, String ccEmailAddresss) throws MessagingException, javax.mail.MessagingException{
		
			
		// Create a new StringBuilder.
		StringBuilder builder = new StringBuilder();
		
		builder.append("<body topmargin=\"0\" rightmargin=\"0\" bottommargin=\"0\" leftmargin=\"0\" marginwidth=\"0\" marginheight=\"0\" width=\"100%\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; width: 100%; height: 100%; -webkit-font-smoothing: antialiased; text-size-adjust: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; line-height: 100%;\r\n" + 
				"	background-color: #F0F0F0;\r\n" + 
				"	color: #000000;\"\r\n" + 
				"	bgcolor=\"#F0F0F0\"\r\n" + 
				"	text=\"#000000\">\r\n" +  
				"<!-- Set message background color one again -->\r\n" + 
				"<table width=\"100%\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; width: 100%;\" class=\"background\"><tr><td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0;\"\r\n" + 
				"	bgcolor=\"#F0F0F0\">\r\n" + 
				"\r\n" + 
				"<!-- WRAPPER -->\r\n" + 
				"<!-- Set wrapper width (twice) -->\r\n" + 
				"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\"\r\n" + 
				"	width=\"560\" style=\"border-collapse: collapse; border-spacing: 0; padding: 0; width: inherit;\r\n" + 
				"	max-width: 560px;\" class=\"wrapper\">\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%;\r\n" + 
				"			padding-top: 20px;\r\n" + 
				"			padding-bottom: 20px;\">\r\n" + 
				"\r\n" + 
				"			<a target=\"_blank\" style=\"text-decoration: none;\"\r\n" + 
				"				href=\"https://www.syn-ergy.com\"><img border=\"0\" vspace=\"0\" hspace=\"0\"\r\n" + 
				"				src='http://www.syn-ergy.com/wp-content/themes/synergy/img/logo.jpg'" + 
				"				width=\"200\" height=\"60\"\r\n" + 
				"				alt=\"Logo\" title=\"Logo\" style=\"\r\n" + 
				"				color: #000000;\r\n" + 
				"				font-size: 10px; margin: 0; padding: 0; outline: none; text-decoration: none; -ms-interpolation-mode: bicubic; border: none; display: block;\" /></a>\r\n" + 
				"\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"<!-- End of WRAPPER -->\r\n" + 
				"</table>\r\n" + 
				"\r\n" + 
				"<!-- WRAPPER / CONTEINER -->\r\n" + 
				"<!-- Set conteiner background color -->\r\n" + 
				"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\"\r\n" + 
				"	bgcolor=\"#FFFFFF\"\r\n" + 
				"	width=\"560\" style=\"border-collapse: collapse; border-spacing: 0; padding: 0; width: inherit;\r\n" + 
				"	max-width: 560px;\" class=\"container\">\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%; font-size: 24px; font-weight: bold; line-height: 130%;\r\n" + 
				"			padding-top: 25px;\r\n" + 
				"			color: #000000;\r\n" + 
				"			font-family: sans-serif;\" class=\"header\">\r\n" + 
				"				New Employee Joined!\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0;\r\n" + 
				"			padding-top: 20px;\" class=\"hero\">				\r\n" + 
				"					\r\n" + 
				"					<tr style=\"background-color:#D9E2DF\">\r\n" + 
				"						<td align=\"left\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%; font-size: 17px; font-weight: 400; line-height: 160%;\r\n" + 
				"							padding-top: 25px; \r\n" + 
				"							color: #000000;\r\n" + 
				"							font-family: sans-serif;\" class=\"paragraph\">");
		
		
		builder.append("Dear All, <br/>");
		builder.append("Good Morning!");
		builder.append("<br/>");
		builder.append("<p> This is to inform you all that 1 (One) new employee has joined in "+candidate.getCompany().getName()+" at "+candidate.getDateOfJoin()+"."
				+ "For the further proceeding we need to update the n-bio, finger punch, mail ID and need to provide Sim card. </p>");
		builder.append("New Employee Name :" + candidate.getCandName() + " <br/>");
		builder.append("Designation :" + candidate.getDesignation().getName() + " <br/>");
		builder.append("Department :" + candidate.getDepartment().getName() + " <br/>");
		builder.append("Company ID :" + profile.getEmpId() + " <br/>");
		builder.append("Office Location  :" + profile.getOfficeLocation() + " <br/>");
		builder.append("Mobile No :" + candidate.getCandMobileNo() + " <br/>");
		builder.append("Blood Group :" + profile.getBloodGroup() + " <br/>");
		builder.append("Joining Date :" + candidate.getDateOfJoin() + " <br/>");
		builder.append("Photo : <a target='_blank' href='" + photoLink + "'> Photo Link</a> <br/>");
		
		builder.append("<br/>");
		builder.append("<p>Requirement: </p>");
		builder.append("For Admin:</br>");
		builder.append("1. Providing Sim Card (If required)<br/>");
		builder.append("2. ID Card (If required)<br/>");
		builder.append("<p></p>");
		builder.append("For IT:<br/>");
		builder.append("1. Finger Punch,<br/>");
		builder.append("2. Mail ID, <br/>");
		builder.append("3. Software update for employee list.<br/>");
		if(candidate.getCompany().getCompanyKeyword().equals(DCIMCH)) {
			builder.append("4. ID Card (If required)<br/>");
		}
		
		
		builder.append("<p></p>");
		builder.append("<p>We need your kind support to update employee information.<p>");
		
		builder.append("<p></p>");
		builder.append("Regards & Thanks,<br/>");
		builder.append("Human Resource Department<br/>");
		builder.append("<p><p>");
		
		builder.append("</td>\r\n" + 
				"					</tr>\r\n" + 
				"				\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"	<!-- LINE -->\r\n" + 
				"	<tr>	\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%;\r\n" + 
				"			padding-top: 25px;\" class=\"line\"><hr\r\n" + 
				"			color=\"#E0E0E0\" align=\"center\" width=\"100%\" size=\"1\" noshade style=\"margin: 0; padding: 0;\" />\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"	<!-- LINE -->\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%; font-size: 17px; font-weight: 400; line-height: 160%;\r\n" + 
				"			padding-top: 20px;\r\n" + 
				"			padding-bottom: 25px;\r\n" + 
				"			color: #000000;\r\n" + 
				"			font-family: sans-serif;\" class=\"paragraph\">\r\n" + 
				"				For Support: Send Email to <a href=\"mailto:support@ourteam.com\" target=\"_blank\" style=\"color: #127DB3; font-family: sans-serif; font-size: 17px; font-weight: 400; line-height: 160%;\">abu.taleb@dynamicsrv.com</a>\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"<!-- End of WRAPPER -->\r\n" + 
				"</table>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"<!-- End of SECTION / BACKGROUND -->\r\n" + 
				"</td></tr></table>\r\n" + 
				"\r\n" + 
				"</body>");
		
		// Convert to string. 
		String result = builder.toString();
		sendMailTo(mailSender, toEmailAddress,"New Employee Joined! ("+ candidate.getCandId() +") For Mr./Ms "+ candidate.getCandName() + "", result, ccEmailAddresss);
	}
	
	public void candidateIntriduceMailToAll(JavaMailSender mailSender, Candidate candidate, CandidateProfile profile, String url, String ccEmailAddresss, Integer circular) throws MessagingException, javax.mail.MessagingException{
		
		String emailTo=profile.getSupervisorEmail();
		
		if(emailTo.equals(null) || emailTo.trim().equals("")){
			emailTo="synergy.hrms@gmail.com";
		}
		
		// Create a new StringBuilder.
		StringBuilder builder = new StringBuilder();
		
		builder.append("<body topmargin=\"0\" rightmargin=\"0\" bottommargin=\"0\" leftmargin=\"0\" marginwidth=\"0\" marginheight=\"0\" width=\"100%\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; width: 100%; height: 100%; -webkit-font-smoothing: antialiased; text-size-adjust: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; line-height: 100%;\r\n" + 
				"	background-color: #F0F0F0;\r\n" + 
				"	color: #000000;\"\r\n" + 
				"	bgcolor=\"#F0F0F0\"\r\n" + 
				"	text=\"#000000\">\r\n" +  
				"<!-- Set message background color one again -->\r\n" + 
				"<table width=\"100%\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; width: 100%;\" class=\"background\"><tr><td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0;\"\r\n" + 
				"	bgcolor=\"#F0F0F0\">\r\n" + 
				"\r\n" + 
				"<!-- WRAPPER -->\r\n" + 
				"<!-- Set wrapper width (twice) -->\r\n" + 
				"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\"\r\n" + 
				"	width=\"560\" style=\"border-collapse: collapse; border-spacing: 0; padding: 0; width: inherit;\r\n" + 
				"	max-width: 560px;\" class=\"wrapper\">\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%;\r\n" + 
				"			padding-top: 20px;\r\n" + 
				"			padding-bottom: 20px;\">\r\n" + 
				"\r\n" + 
				"			<a target=\"_blank\" style=\"text-decoration: none;\"\r\n" + 
				"				href=\"https://www.syn-ergy.com\"><img border=\"0\" vspace=\"0\" hspace=\"0\"\r\n" + 
				"				src='http://www.syn-ergy.com/wp-content/themes/synergy/img/logo.jpg'" + 
				"				width=\"200\" height=\"60\"\r\n" + 
				"				alt=\"Logo\" title=\"Logo\" style=\"\r\n" + 
				"				color: #000000;\r\n" + 
				"				font-size: 10px; margin: 0; padding: 0; outline: none; text-decoration: none; -ms-interpolation-mode: bicubic; border: none; display: block;\" /></a>\r\n" + 
				"\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"<!-- End of WRAPPER -->\r\n" + 
				"</table>\r\n" + 
				"\r\n" + 
				"<!-- WRAPPER / CONTEINER -->\r\n" + 
				"<!-- Set conteiner background color -->\r\n" + 
				"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\"\r\n" + 
				"	bgcolor=\"#FFFFFF\"\r\n" + 
				"	width=\"560\" style=\"border-collapse: collapse; border-spacing: 0; padding: 0; width: inherit;\r\n" + 
				"	max-width: 560px;\" class=\"container\">\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%; font-size: 24px; font-weight: bold; line-height: 130%;\r\n" + 
				"			padding-top: 25px;\r\n" + 
				"			color: #000000;\r\n" + 
				"			font-family: sans-serif;\" class=\"header\">\r\n" + 
				"			<a target=\"_blank\" style=\"text-decoration: none;\"\r\n" + 
				"				href=\"#\"><img border=\"0\" vspace=\"0\" hspace=\"0\"\r\n" + 
				"				src='http://hhstaffingservices.com/wp-content/uploads/2016/04/Make-New-Employees-Feel-Welcome.jpg'" + 
				"				width=\"500\" height=\"250\"\r\n" + 
				"				alt=\"Welcome\" title=\"Welcome\" style=\"\r\n" + 
				"				color: #000000;\r\n" + 
				"				font-size: 10px; margin: 0; padding: 0; outline: none; text-decoration: none; -ms-interpolation-mode: bicubic; border: none; display: block;\" /></a>\r\n" +
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0;\r\n" + 
				"			padding-top: 20px;\" class=\"hero\">				\r\n" + 
				"					\r\n" + 
				"					<tr style=\"background-color:#D9E2DF\">\r\n" + 
				"						<td align=\"left\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%; font-size: 17px; font-weight: 400; line-height: 160%;\r\n" + 
				"							padding-top: 25px; \r\n" + 
				"							color: #000000;\r\n" + 
				"							font-family: sans-serif;\" class=\"paragraph\">");
		
		
		if(circular == 1) {
			builder.append("Dear All, <br/>");
		}else {
			builder.append("Dear Sir, <br/>");
		}
		builder.append("Good Morning!");
		builder.append("<br/>");
		if(profile.getGender() != null && profile.getGender().equals("Male")) {
			builder.append("<p> We are delighted to introduce " + candidate.getCandName() + ", our new colleague joined on " + candidate.getDateOfJoin() + " in " + candidate.getCompany().getName() + " as " + candidate.getDesignation().getName() + " in " + candidate.getDepartment().getName() + " department. We hope that his new journey with us will be very successful and prosperous and wish him all the best for his future career. </p>");
		} else {
			builder.append("<p> We are delighted to introduce " + candidate.getCandName() + ", our new colleague joined on " + candidate.getDateOfJoin() + " in " + candidate.getCompany().getName() + " as " + candidate.getDesignation().getName() + " in " + candidate.getDepartment().getName() + " department. We hope that her new journey with us will be very successful and prosperous and wish her all the best for her future career. </p>");
		}
		
		builder.append("<p></p>");
		builder.append("Regards & Thanks,</br>");
		builder.append("Human Resource Department</br>");
		builder.append("<p><p>");
		
		builder.append("</td>\r\n" + 
				"					</tr>\r\n" + 
				"				\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"	<!-- LINE -->\r\n" + 
				"	<tr>	\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%;\r\n" + 
				"			padding-top: 25px;\" class=\"line\"><hr\r\n" + 
				"			color=\"#E0E0E0\" align=\"center\" width=\"100%\" size=\"1\" noshade style=\"margin: 0; padding: 0;\" />\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"	<!-- LINE -->\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%; font-size: 17px; font-weight: 400; line-height: 160%;\r\n" + 
				"			padding-top: 20px;\r\n" + 
				"			padding-bottom: 25px;\r\n" + 
				"			color: #000000;\r\n" + 
				"			font-family: sans-serif;\" class=\"paragraph\">\r\n" + 
				"				For Support: Send Email to <a href=\"mailto:support@ourteam.com\" target=\"_blank\" style=\"color: #127DB3; font-family: sans-serif; font-size: 17px; font-weight: 400; line-height: 160%;\">abu.taleb@dynamicsrv.com</a>\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"<!-- End of WRAPPER -->\r\n" + 
				"</table>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"<!-- End of SECTION / BACKGROUND -->\r\n" + 
				"</td></tr></table>\r\n" + 
				"\r\n" + 
				"</body>");
		
		// Convert to string. 
		String result = builder.toString();
		
		if(circular == 1) {
			sendMailTo(mailSender, emailTo,"New Employee Introduce! ("+ candidate.getCandId() +") For Mr./Ms "+ candidate.getCandName() + "", result, ccEmailAddresss);
		} else {
			sendMailToWithoutCC(mailSender, emailTo,"New Employee Introduce! ("+ candidate.getCandId() +") For Mr./Ms "+ candidate.getCandName() + "", result);
		}
		
	}
	
	public void candidateJoiningReminderMail(JavaMailSender mailSender, Candidate candidate, String commonEmailAddress) throws MessagingException, javax.mail.MessagingException{
		
		String candMail=candidate.getCandEmail();
		
		if(candMail.equals(null)||candMail.equals("")){
			candMail=commonEmailAddress;
		}
		
		// Create a new StringBuilder.
		StringBuilder builder = new StringBuilder();
		
		builder.append("<body topmargin=\"0\" rightmargin=\"0\" bottommargin=\"0\" leftmargin=\"0\" marginwidth=\"0\" marginheight=\"0\" width=\"100%\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; width: 100%; height: 100%; -webkit-font-smoothing: antialiased; text-size-adjust: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; line-height: 100%;\r\n" + 
				"	background-color: #F0F0F0;\r\n" + 
				"	color: #000000;\"\r\n" + 
				"	bgcolor=\"#F0F0F0\"\r\n" + 
				"	text=\"#000000\">\r\n" +  
				"<!-- Set message background color one again -->\r\n" + 
				"<table width=\"100%\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; width: 100%;\" class=\"background\"><tr><td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0;\"\r\n" + 
				"	bgcolor=\"#F0F0F0\">\r\n" + 
				"\r\n" + 
				"<!-- WRAPPER -->\r\n" + 
				"<!-- Set wrapper width (twice) -->\r\n" + 
				"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\"\r\n" + 
				"	width=\"560\" style=\"border-collapse: collapse; border-spacing: 0; padding: 0; width: inherit;\r\n" + 
				"	max-width: 560px;\" class=\"wrapper\">\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%;\r\n" + 
				"			padding-top: 20px;\r\n" + 
				"			padding-bottom: 20px;\">\r\n" + 
				"\r\n" + 
				"			<a target=\"_blank\" style=\"text-decoration: none;\"\r\n" + 
				"				href=\"https://www.syn-ergy.com\"><img border=\"0\" vspace=\"0\" hspace=\"0\"\r\n" + 
				"				src='http://www.syn-ergy.com/wp-content/themes/synergy/img/logo.jpg'" + 
				"				width=\"200\" height=\"60\"\r\n" + 
				"				alt=\"Logo\" title=\"Logo\" style=\"\r\n" + 
				"				color: #000000;\r\n" + 
				"				font-size: 10px; margin: 0; padding: 0; outline: none; text-decoration: none; -ms-interpolation-mode: bicubic; border: none; display: block;\" /></a>\r\n" + 
				"\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"<!-- End of WRAPPER -->\r\n" + 
				"</table>\r\n" + 
				"\r\n" + 
				"<!-- WRAPPER / CONTEINER -->\r\n" + 
				"<!-- Set conteiner background color -->\r\n" + 
				"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\"\r\n" + 
				"	bgcolor=\"#FFFFFF\"\r\n" + 
				"	width=\"560\" style=\"border-collapse: collapse; border-spacing: 0; padding: 0; width: inherit;\r\n" + 
				"	max-width: 560px;\" class=\"container\">\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%; font-size: 24px; font-weight: bold; line-height: 130%;\r\n" + 
				"			padding-top: 25px;\r\n" + 
				"			color: #000000;\r\n" + 
				"			font-family: sans-serif;\" class=\"header\">\r\n" + 
				"				Joining Reminder\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0;\r\n" + 
				"			padding-top: 20px;\" class=\"hero\">				\r\n" + 
				"					\r\n" + 
				"					<tr style=\"background-color:#D9E2DF\">\r\n" + 
				"						<td align=\"left\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%; font-size: 17px; font-weight: 400; line-height: 160%;\r\n" + 
				"							padding-top: 25px; \r\n" + 
				"							color: #000000;\r\n" + 
				"							font-family: sans-serif;\" class=\"paragraph\">");
		
		
		builder.append("Dear Mr./Ms. " + candidate.getCandName() + ", <br/>");
		builder.append("<p>This is a cordial reminder regarding your joining date. We would like to remind you that coming (Date) is your joining date. So we hope to see you at that day on time as a member of our family.</p>");
		builder.append("<br/>");
		builder.append("<p> <b> N.B: On your joining date, you are requested to bring all the necessary documents as mention below: </b></p>");
		
		builder.append("<p> </p>");
		
		builder.append("* Photocopy of All Educational Certificates <br/>");
		builder.append("* Photocopy of Experience Certificates <br/>");
		builder.append("* Recent Passport Size Photo (4 Copy) <br/>");
		builder.append("* Photocopy Of National ID Card <br/>");
		builder.append("* Resignation Letter Copy <br/>");
		builder.append("* E-Tin Certificate<br/>");
		
		builder.append("<br/> <br/>");
		
		builder.append("<p><b>Sincerely </b> </p> <br/>");		
		builder.append("<p><b>Human Resource Department</b></p> ");
		
		builder.append("</td>\r\n" + 
				"					</tr>\r\n" + 
				"				\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"	\r\n" + 
				"\r\n" + 
				"	<!-- LINE -->\r\n" + 
				"	<tr>	\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%;\r\n" + 
				"			padding-top: 25px;\" class=\"line\"><hr\r\n" + 
				"			color=\"#E0E0E0\" align=\"center\" width=\"100%\" size=\"1\" noshade style=\"margin: 0; padding: 0;\" />\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"	<!-- LINE -->\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%; font-size: 17px; font-weight: 400; line-height: 160%;\r\n" + 
				"			padding-top: 20px;\r\n" + 
				"			padding-bottom: 25px;\r\n" + 
				"			color: #000000;\r\n" + 
				"			font-family: sans-serif;\" class=\"paragraph\">\r\n" + 
				"				For Support: Send Email to <a href=\"mailto:abu.taleb@dynamicsrv.com\" target=\"_blank\" style=\"color: #127DB3; font-family: sans-serif; font-size: 17px; font-weight: 400; line-height: 160%;\">abu.taleb@dynamicsrv.com</a>\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"<!-- End of WRAPPER -->\r\n" + 
				"</table>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"<!-- End of SECTION / BACKGROUND -->\r\n" + 
				"</td></tr></table>\r\n" + 
				"\r\n" + 
				"</body>");
		
		// Convert to string. 
		String result = builder.toString();
		sendMailToWithoutCC(mailSender, candMail,"Joining Reminder! ("+ candidate.getCandId() +") For Mr./Ms "+ candidate.getCandName() + "", result);
	}
	
	
	public void candidateProfileApproveMail(JavaMailSender mailSender, Candidate candidate, String url, String commonEmailAddress) throws MessagingException, javax.mail.MessagingException{
		
		String candMail=candidate.getCandEmail();
		
		if(candMail.equals(null)||candMail.equals("")){
			candMail=commonEmailAddress;
		}
		
		// Create a new StringBuilder.
		StringBuilder builder = new StringBuilder();
		
		builder.append("<body topmargin=\"0\" rightmargin=\"0\" bottommargin=\"0\" leftmargin=\"0\" marginwidth=\"0\" marginheight=\"0\" width=\"100%\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; width: 100%; height: 100%; -webkit-font-smoothing: antialiased; text-size-adjust: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; line-height: 100%;\r\n" + 
				"	background-color: #F0F0F0;\r\n" + 
				"	color: #000000;\"\r\n" + 
				"	bgcolor=\"#F0F0F0\"\r\n" + 
				"	text=\"#000000\">\r\n" +  
				"<!-- Set message background color one again -->\r\n" + 
				"<table width=\"100%\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; width: 100%;\" class=\"background\"><tr><td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0;\"\r\n" + 
				"	bgcolor=\"#F0F0F0\">\r\n" + 
				"\r\n" + 
				"<!-- WRAPPER -->\r\n" + 
				"<!-- Set wrapper width (twice) -->\r\n" + 
				"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\"\r\n" + 
				"	width=\"560\" style=\"border-collapse: collapse; border-spacing: 0; padding: 0; width: inherit;\r\n" + 
				"	max-width: 560px;\" class=\"wrapper\">\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%;\r\n" + 
				"			padding-top: 20px;\r\n" + 
				"			padding-bottom: 20px;\">\r\n" + 
				"\r\n" + 
				"			<a target=\"_blank\" style=\"text-decoration: none;\"\r\n" + 
				"				href=\"https://www.syn-ergy.com\"><img border=\"0\" vspace=\"0\" hspace=\"0\"\r\n" + 
				"				src='http://www.syn-ergy.com/wp-content/themes/synergy/img/logo.jpg'" + 
				"				width=\"200\" height=\"60\"\r\n" + 
				"				alt=\"Logo\" title=\"Logo\" style=\"\r\n" + 
				"				color: #000000;\r\n" + 
				"				font-size: 10px; margin: 0; padding: 0; outline: none; text-decoration: none; -ms-interpolation-mode: bicubic; border: none; display: block;\" /></a>\r\n" + 
				"\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"<!-- End of WRAPPER -->\r\n" + 
				"</table>\r\n" + 
				"\r\n" + 
				"<!-- WRAPPER / CONTEINER -->\r\n" + 
				"<!-- Set conteiner background color -->\r\n" + 
				"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\"\r\n" + 
				"	bgcolor=\"#FFFFFF\"\r\n" + 
				"	width=\"560\" style=\"border-collapse: collapse; border-spacing: 0; padding: 0; width: inherit;\r\n" + 
				"	max-width: 560px;\" class=\"container\">\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%; font-size: 24px; font-weight: bold; line-height: 130%;\r\n" + 
				"			padding-top: 25px;\r\n" + 
				"			color: #000000;\r\n" + 
				"			font-family: sans-serif;\" class=\"header\">\r\n" + 
				"				Profile Approved!\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0;\r\n" + 
				"			padding-top: 20px;\" class=\"hero\">				\r\n" + 
				"					\r\n" + 
				"					<tr style=\"background-color:#D9E2DF\">\r\n" + 
				"						<td align=\"left\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%; font-size: 17px; font-weight: 400; line-height: 160%;\r\n" + 
				"							padding-top: 25px; \r\n" + 
				"							color: #000000;\r\n" + 
				"							font-family: sans-serif;\" class=\"paragraph\">");
		
		
		builder.append("Dear Mr./Ms. " + candidate.getCandName() + ", <br/>");
		//builder.append("<p> Your profile has been approved. now you can download your appointment letter form Synergy-EJMS. Please Click the bellow link and login to our system than download your appointment letter.</p>");
		builder.append("<p>Your profile has been approved. Now you can download your appointment letter form Synergy-EJMS. Please click the below link to login into our system and download your appointment letter.</p>");	
		//remove by tasnim
		//builder.append("<p></p>");	
		//builder.append("<p>As token acceptance please click on reply.</p>");
		builder.append("<p></p>");
		builder.append("<p><b>Sincerely </b> </p>");		
		builder.append("<p><b>Human Resource Department</b></p> ");
		
		builder.append("</td>\r\n" + 
				"					</tr>\r\n" + 
				"				\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"	\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%;\r\n" + 
				"			padding-top: 25px;\r\n" + 
				"			padding-bottom: 5px;\" class=\"button\"><a\r\n" + 
				"			href=\"https://github.com/konsav/email-templates/\" target=\"_blank\" style=\"text-decoration: underline;\">\r\n" + 
				"				<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" style=\"max-width: 240px; min-width: 120px; border-collapse: collapse; border-spacing: 0; padding: 0;\"><tr><td align=\"center\" valign=\"middle\" style=\"padding: 12px 24px; margin: 0; text-decoration: underline; border-collapse: collapse; border-spacing: 0; border-radius: 4px; -webkit-border-radius: 4px; -moz-border-radius: 4px; -khtml-border-radius: 4px;\"\r\n" + 
				"					bgcolor=\"#E9703E\"><a target=\"_blank\" style=\"text-decoration: underline;\r\n" + 
				"					color: #FFFFFF; font-family: sans-serif; font-size: 17px; font-weight: 400; line-height: 120%;\"\r\n" + 
				"					href=" + url + ">\r\n" + 
				"						Click Here to View\r\n" + 
				"					</a>\r\n" + 
				"			</td></tr></table></a>\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"	<!-- LINE -->\r\n" + 
				"	<tr>	\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%;\r\n" + 
				"			padding-top: 25px;\" class=\"line\"><hr\r\n" + 
				"			color=\"#E0E0E0\" align=\"center\" width=\"100%\" size=\"1\" noshade style=\"margin: 0; padding: 0;\" />\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"	<!-- LINE -->\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%; font-size: 17px; font-weight: 400; line-height: 160%;\r\n" + 
				"			padding-top: 20px;\r\n" + 
				"			padding-bottom: 25px;\r\n" + 
				"			color: #000000;\r\n" + 
				"			font-family: sans-serif;\" class=\"paragraph\">\r\n" + 
				"				For Support: Send Email to <a href=\"mailto:support@ourteam.com\" target=\"_blank\" style=\"color: #127DB3; font-family: sans-serif; font-size: 17px; font-weight: 400; line-height: 160%;\">abu.taleb@dynamicsrv.com</a>\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"<!-- End of WRAPPER -->\r\n" + 
				"</table>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"<!-- End of SECTION / BACKGROUND -->\r\n" + 
				"</td></tr></table>\r\n" + 
				"\r\n" + 
				"</body>");
		
		// Convert to string. 
		String result = builder.toString();
		sendMailToWithoutCC(mailSender, candMail,"Candidate Profile Approved! ("+ candidate.getCandId() +") For Mr./Ms "+ candidate.getCandName() + "", result);
	}
	
	public void alRejectedByCandidateMailToHRD(JavaMailSender mailSender, Candidate candidate, String url, String commonEmailAddress) throws MessagingException, javax.mail.MessagingException{
		
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		String rejectdate = df.format(candidate.getRejectedByCandidateDate());
		// Create a new StringBuilder.
		StringBuilder builder = new StringBuilder();
		
		builder.append("<body topmargin=\"0\" rightmargin=\"0\" bottommargin=\"0\" leftmargin=\"0\" marginwidth=\"0\" marginheight=\"0\" width=\"100%\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; width: 100%; height: 100%; -webkit-font-smoothing: antialiased; text-size-adjust: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; line-height: 100%;\r\n" + 
				"	background-color: #F0F0F0;\r\n" + 
				"	color: #000000;\"\r\n" + 
				"	bgcolor=\"#F0F0F0\"\r\n" + 
				"	text=\"#000000\">\r\n" +  
				"<!-- Set message background color one again -->\r\n" + 
				"<table width=\"100%\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; width: 100%;\" class=\"background\"><tr><td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0;\"\r\n" + 
				"	bgcolor=\"#F0F0F0\">\r\n" + 
				"\r\n" + 
				"<!-- WRAPPER -->\r\n" + 
				"<!-- Set wrapper width (twice) -->\r\n" + 
				"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\"\r\n" + 
				"	width=\"560\" style=\"border-collapse: collapse; border-spacing: 0; padding: 0; width: inherit;\r\n" + 
				"	max-width: 560px;\" class=\"wrapper\">\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%;\r\n" + 
				"			padding-top: 20px;\r\n" + 
				"			padding-bottom: 20px;\">\r\n" + 
				"\r\n" + 
				"			<a target=\"_blank\" style=\"text-decoration: none;\"\r\n" + 
				"				href=\"https://www.syn-ergy.com\"><img border=\"0\" vspace=\"0\" hspace=\"0\"\r\n" + 
				"				src='http://www.syn-ergy.com/wp-content/themes/synergy/img/logo.jpg'" + 
				"				width=\"200\" height=\"60\"\r\n" + 
				"				alt=\"Logo\" title=\"Logo\" style=\"\r\n" + 
				"				color: #000000;\r\n" + 
				"				font-size: 10px; margin: 0; padding: 0; outline: none; text-decoration: none; -ms-interpolation-mode: bicubic; border: none; display: block;\" /></a>\r\n" + 
				"\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"<!-- End of WRAPPER -->\r\n" + 
				"</table>\r\n" + 
				"\r\n" + 
				"<!-- WRAPPER / CONTEINER -->\r\n" + 
				"<!-- Set conteiner background color -->\r\n" + 
				"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\"\r\n" + 
				"	bgcolor=\"#FFFFFF\"\r\n" + 
				"	width=\"560\" style=\"border-collapse: collapse; border-spacing: 0; padding: 0; width: inherit;\r\n" + 
				"	max-width: 560px;\" class=\"container\">\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%; font-size: 24px; font-weight: bold; line-height: 130%;\r\n" + 
				"			padding-top: 25px;\r\n" + 
				"			color: #000000;\r\n" + 
				"			font-family: sans-serif;\" class=\"header\">\r\n" + 
				"				Appointment Letter Rejected By Candidate!\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0;\r\n" + 
				"			padding-top: 20px;\" class=\"hero\">				\r\n" + 
				"					\r\n" + 
				"					<tr style=\"background-color:#D9E2DF\">\r\n" + 
				"						<td align=\"left\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%; font-size: 17px; font-weight: 400; line-height: 160%;\r\n" + 
				"							padding-top: 25px; \r\n" + 
				"							color: #000000;\r\n" + 
				"							font-family: sans-serif;\" class=\"paragraph\">");
		
		
		builder.append("Dear Sir/Madam, <br/>");
		builder.append("<p> I have read and understand all terms and conditions of the Appointment Letter but I am rejecting the Appointment Letter for the below reason: </p>");
		builder.append("<p></p>");	
		builder.append("<p>Reject Reason : "+candidate.getRejectReasonByCandidate()+"</p>");			
		builder.append("<p>Reject Date : "+rejectdate+"</p>");
		builder.append("<p></p>");	
		builder.append("<p>Best Regards</p>");
		builder.append("<b>"+candidate.getCandName()+" </b> <br/>");		
		builder.append("<b>"+candidate.getCandId()+"</b><br/><br/>");
		
		builder.append("</td>\r\n" + 
				"					</tr>\r\n" + 
				"				\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"	\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%;\r\n" + 
				"			padding-top: 25px;\r\n" + 
				"			padding-bottom: 5px;\" class=\"button\"><a\r\n" + 
				"			href=\"https://github.com/konsav/email-templates/\" target=\"_blank\" style=\"text-decoration: underline;\">\r\n" + 
				"				<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" style=\"max-width: 240px; min-width: 120px; border-collapse: collapse; border-spacing: 0; padding: 0;\"><tr><td align=\"center\" valign=\"middle\" style=\"padding: 12px 24px; margin: 0; text-decoration: underline; border-collapse: collapse; border-spacing: 0; border-radius: 4px; -webkit-border-radius: 4px; -moz-border-radius: 4px; -khtml-border-radius: 4px;\"\r\n" + 
				"					bgcolor=\"#E9703E\"><a target=\"_blank\" style=\"text-decoration: underline;\r\n" + 
				"					color: #FFFFFF; font-family: sans-serif; font-size: 17px; font-weight: 400; line-height: 120%;\"\r\n" + 
				"					href=" + url + ">\r\n" + 
				"						Click Here to View\r\n" + 
				"					</a>\r\n" + 
				"			</td></tr></table></a>\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"	<!-- LINE -->\r\n" + 
				"	<tr>	\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%;\r\n" + 
				"			padding-top: 25px;\" class=\"line\"><hr\r\n" + 
				"			color=\"#E0E0E0\" align=\"center\" width=\"100%\" size=\"1\" noshade style=\"margin: 0; padding: 0;\" />\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"	<!-- LINE -->\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%; font-size: 17px; font-weight: 400; line-height: 160%;\r\n" + 
				"			padding-top: 20px;\r\n" + 
				"			padding-bottom: 25px;\r\n" + 
				"			color: #000000;\r\n" + 
				"			font-family: sans-serif;\" class=\"paragraph\">\r\n" + 
				"				For Support: Send Email to <a href=\"mailto:support@ourteam.com\" target=\"_blank\" style=\"color: #127DB3; font-family: sans-serif; font-size: 17px; font-weight: 400; line-height: 160%;\">abu.taleb@dynamicsrv.com</a>\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"<!-- End of WRAPPER -->\r\n" + 
				"</table>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"<!-- End of SECTION / BACKGROUND -->\r\n" + 
				"</td></tr></table>\r\n" + 
				"\r\n" + 
				"</body>");
		
		// Convert to string. 
		String result = builder.toString();
		sendMailToWithoutCC(mailSender, commonEmailAddress,"Appointment Letter Rejected Candidate. ("+ candidate.getCandId() +") For Mr./Ms "+ candidate.getCandName() + "", result);
	}
	
	public void alAcceptedByCandidateMailToHRD(JavaMailSender mailSender, Candidate candidate, String url, String commonEmailAddress) throws MessagingException, javax.mail.MessagingException{
		
		/*DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		String rejectdate = df.format(candidate.getRejectedByCandidateDate());*/
		// Create a new StringBuilder.
		StringBuilder builder = new StringBuilder();
		
		builder.append("<body topmargin=\"0\" rightmargin=\"0\" bottommargin=\"0\" leftmargin=\"0\" marginwidth=\"0\" marginheight=\"0\" width=\"100%\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; width: 100%; height: 100%; -webkit-font-smoothing: antialiased; text-size-adjust: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; line-height: 100%;\r\n" + 
				"	background-color: #F0F0F0;\r\n" + 
				"	color: #000000;\"\r\n" + 
				"	bgcolor=\"#F0F0F0\"\r\n" + 
				"	text=\"#000000\">\r\n" +  
				"<!-- Set message background color one again -->\r\n" + 
				"<table width=\"100%\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; width: 100%;\" class=\"background\"><tr><td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0;\"\r\n" + 
				"	bgcolor=\"#F0F0F0\">\r\n" + 
				"\r\n" + 
				"<!-- WRAPPER -->\r\n" + 
				"<!-- Set wrapper width (twice) -->\r\n" + 
				"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\"\r\n" + 
				"	width=\"560\" style=\"border-collapse: collapse; border-spacing: 0; padding: 0; width: inherit;\r\n" + 
				"	max-width: 560px;\" class=\"wrapper\">\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%;\r\n" + 
				"			padding-top: 20px;\r\n" + 
				"			padding-bottom: 20px;\">\r\n" + 
				"\r\n" + 
				"			<a target=\"_blank\" style=\"text-decoration: none;\"\r\n" + 
				"				href=\"https://www.syn-ergy.com\"><img border=\"0\" vspace=\"0\" hspace=\"0\"\r\n" + 
				"				src='http://www.syn-ergy.com/wp-content/themes/synergy/img/logo.jpg'" + 
				"				width=\"200\" height=\"60\"\r\n" + 
				"				alt=\"Logo\" title=\"Logo\" style=\"\r\n" + 
				"				color: #000000;\r\n" + 
				"				font-size: 10px; margin: 0; padding: 0; outline: none; text-decoration: none; -ms-interpolation-mode: bicubic; border: none; display: block;\" /></a>\r\n" + 
				"\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"<!-- End of WRAPPER -->\r\n" + 
				"</table>\r\n" + 
				"\r\n" + 
				"<!-- WRAPPER / CONTEINER -->\r\n" + 
				"<!-- Set conteiner background color -->\r\n" + 
				"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\"\r\n" + 
				"	bgcolor=\"#FFFFFF\"\r\n" + 
				"	width=\"560\" style=\"border-collapse: collapse; border-spacing: 0; padding: 0; width: inherit;\r\n" + 
				"	max-width: 560px;\" class=\"container\">\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%; font-size: 24px; font-weight: bold; line-height: 130%;\r\n" + 
				"			padding-top: 25px;\r\n" + 
				"			color: #000000;\r\n" + 
				"			font-family: sans-serif;\" class=\"header\">\r\n" + 
				"				Appointment Letter Accepted By Candidate!\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0;\r\n" + 
				"			padding-top: 20px;\" class=\"hero\">				\r\n" + 
				"					\r\n" + 
				"					<tr style=\"background-color:#D9E2DF\">\r\n" + 
				"						<td align=\"left\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%; font-size: 17px; font-weight: 400; line-height: 160%;\r\n" + 
				"							padding-top: 25px; \r\n" + 
				"							color: #000000;\r\n" + 
				"							font-family: sans-serif;\" class=\"paragraph\">");
		
		
		builder.append("Dear Sir/Madam, <br/>");
		builder.append("<p> I have read and understand all terms and conditions of the Appointment Letter and I am cordially accepting this Appointment Letter.</p>");		
		builder.append("<p></p>");	
		builder.append("<p>Best Regards</p>");
		builder.append("<b>"+candidate.getCandName()+" </b> <br/>");		
		builder.append("<b>"+candidate.getCandId()+"</b><br/><br/>");
		
		builder.append("</td>\r\n" + 
				"					</tr>\r\n" + 
				"				\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"	\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%;\r\n" + 
				"			padding-top: 25px;\r\n" + 
				"			padding-bottom: 5px;\" class=\"button\"><a\r\n" + 
				"			href=\"https://github.com/konsav/email-templates/\" target=\"_blank\" style=\"text-decoration: underline;\">\r\n" + 
				"				<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" style=\"max-width: 240px; min-width: 120px; border-collapse: collapse; border-spacing: 0; padding: 0;\"><tr><td align=\"center\" valign=\"middle\" style=\"padding: 12px 24px; margin: 0; text-decoration: underline; border-collapse: collapse; border-spacing: 0; border-radius: 4px; -webkit-border-radius: 4px; -moz-border-radius: 4px; -khtml-border-radius: 4px;\"\r\n" + 
				"					bgcolor=\"#E9703E\"><a target=\"_blank\" style=\"text-decoration: underline;\r\n" + 
				"					color: #FFFFFF; font-family: sans-serif; font-size: 17px; font-weight: 400; line-height: 120%;\"\r\n" + 
				"					href=" + url + ">\r\n" + 
				"						Click Here to View\r\n" + 
				"					</a>\r\n" + 
				"			</td></tr></table></a>\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"	<!-- LINE -->\r\n" + 
				"	<tr>	\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%;\r\n" + 
				"			padding-top: 25px;\" class=\"line\"><hr\r\n" + 
				"			color=\"#E0E0E0\" align=\"center\" width=\"100%\" size=\"1\" noshade style=\"margin: 0; padding: 0;\" />\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"	<!-- LINE -->\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%; font-size: 17px; font-weight: 400; line-height: 160%;\r\n" + 
				"			padding-top: 20px;\r\n" + 
				"			padding-bottom: 25px;\r\n" + 
				"			color: #000000;\r\n" + 
				"			font-family: sans-serif;\" class=\"paragraph\">\r\n" + 
				"				For Support: Send Email to <a href=\"mailto:support@ourteam.com\" target=\"_blank\" style=\"color: #127DB3; font-family: sans-serif; font-size: 17px; font-weight: 400; line-height: 160%;\">abu.taleb@dynamicsrv.com</a>\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"<!-- End of WRAPPER -->\r\n" + 
				"</table>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"<!-- End of SECTION / BACKGROUND -->\r\n" + 
				"</td></tr></table>\r\n" + 
				"\r\n" + 
				"</body>");
		
		// Convert to string. 
		String result = builder.toString();
		sendMailToWithoutCC(mailSender, commonEmailAddress,"Appointment Letter Accepted Candidate. ("+ candidate.getCandId() +") For Mr./Ms "+ candidate.getCandName() + "", result);
	}
	
	public void candidateGreatingMail(JavaMailSender mailSender, Candidate candidate, String url, String commonEmailAddress) throws MessagingException, javax.mail.MessagingException{
		
		String candMail=candidate.getCandEmail();
		
		if(candMail.equals(null)||candMail.equals("")){
			candMail=commonEmailAddress;
		}
		
		// Create a new StringBuilder.
		StringBuilder builder = new StringBuilder();
		
		builder.append("<body topmargin=\"0\" rightmargin=\"0\" bottommargin=\"0\" leftmargin=\"0\" marginwidth=\"0\" marginheight=\"0\" width=\"100%\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; width: 100%; height: 100%; -webkit-font-smoothing: antialiased; text-size-adjust: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; line-height: 100%;\r\n" + 
				"	background-color: #F0F0F0;\r\n" + 
				"	color: #000000;\"\r\n" + 
				"	bgcolor=\"#F0F0F0\"\r\n" + 
				"	text=\"#000000\">\r\n" +  
				"<!-- Set message background color one again -->\r\n" + 
				"<table width=\"100%\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; width: 100%;\" class=\"background\"><tr><td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0;\"\r\n" + 
				"	bgcolor=\"#F0F0F0\">\r\n" + 
				"\r\n" + 
				"<!-- WRAPPER -->\r\n" + 
				"<!-- Set wrapper width (twice) -->\r\n" + 
				"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\"\r\n" + 
				"	width=\"560\" style=\"border-collapse: collapse; border-spacing: 0; padding: 0; width: inherit;\r\n" + 
				"	max-width: 560px;\" class=\"wrapper\">\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%;\r\n" + 
				"			padding-top: 20px;\r\n" + 
				"			padding-bottom: 20px;\">\r\n" + 
				"\r\n" + 
				"			<a target=\"_blank\" style=\"text-decoration: none;\"\r\n" + 
				"				href=\"https://www.syn-ergy.com\"><img border=\"0\" vspace=\"0\" hspace=\"0\"\r\n" + 
				"				src='http://www.syn-ergy.com/wp-content/themes/synergy/img/logo.jpg'" + 
				"				width=\"200\" height=\"60\"\r\n" + 
				"				alt=\"Logo\" title=\"Logo\" style=\"\r\n" + 
				"				color: #000000;\r\n" + 
				"				font-size: 10px; margin: 0; padding: 0; outline: none; text-decoration: none; -ms-interpolation-mode: bicubic; border: none; display: block;\" /></a>\r\n" + 
				"\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"<!-- End of WRAPPER -->\r\n" + 
				"</table>\r\n" + 
				"\r\n" + 
				"<!-- WRAPPER / CONTEINER -->\r\n" + 
				"<!-- Set conteiner background color -->\r\n" + 
				"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\"\r\n" + 
				"	bgcolor=\"#FFFFFF\"\r\n" + 
				"	width=\"560\" style=\"border-collapse: collapse; border-spacing: 0; padding: 0; width: inherit;\r\n" + 
				"	max-width: 560px;\" class=\"container\">\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%; font-size: 24px; font-weight: bold; line-height: 130%;\r\n" + 
				"			padding-top: 25px;\r\n" + 
				"			color: #000000;\r\n" + 
				"			font-family: sans-serif;\" class=\"header\">\r\n" + 
				"			<a target=\"_blank\" style=\"text-decoration: none;\"\r\n" + 
				"				href=\"#\"><img border=\"0\" vspace=\"0\" hspace=\"0\"\r\n" + 
				"				src='https://s1.card-images.com/products/festive-balloons-welcome-card_9864XU_Z.jpg'" + 
				"				width=\"500\" height=\"250\"\r\n" + 
				"				alt=\"Welcome\" title=\"Welcome\" style=\"\r\n" + 
				"				color: #000000;\r\n" + 
				"				font-size: 10px; margin: 0; padding: 0; outline: none; text-decoration: none; -ms-interpolation-mode: bicubic; border: none; display: block;\" /></a>\r\n" +
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0;\r\n" + 
				"			padding-top: 20px;\" class=\"hero\">				\r\n" + 
				"					\r\n" + 
				"					<tr style=\"background-color:#D9E2DF\">\r\n" + 
				"						<td align=\"left\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%; font-size: 17px; font-weight: 400; line-height: 160%;\r\n" + 
				"							padding-top: 25px; \r\n" + 
				"							color: #000000;\r\n" + 
				"							font-family: sans-serif;\" class=\"paragraph\">");
		
		
		builder.append("Dear Mr./Ms. " + candidate.getCandName() + ", <br/>");
		builder.append("Good Morning!");			
		builder.append("<br/>");		
		builder.append("<p>We are so pleased to welcome you to your new assignment in our organization. We are hopeful that with your fresh ideas and enthusiasm, you will make a new achievement for the organization. We are all looking forward to working with you and we believe you are going to be a great fit for this organization.</p>");		
		//builder.append("<br/>");	
		builder.append("<p>Thank you so much and all the best for your new journey.</p> ");		
		//builder.append("<br/>");	
		builder.append("<p><b>Best Regards</b></p> ");
		//builder.append("<br/>");	
		builder.append("<p><b>Human Resource Department</b></p> "); 
		
		builder.append("<br/>");	
		
		//builder.append("<h3> <b> N.B.: Please Login to EJMS from bellow link and submit your feedback to HRD about first day's experience by tomorrow. </b> </h3>");
		
		builder.append("</td>\r\n" + 
				"					</tr>\r\n" + 
				"				\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"	\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%;\r\n" + 
				"			padding-top: 25px;\r\n" + 
				"			padding-bottom: 5px;\" class=\"button\"><a\r\n" + 
				"			href=\"https://github.com/konsav/email-templates/\" target=\"_blank\" style=\"text-decoration: underline;\">\r\n" + 
				"				<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" style=\"max-width: 240px; min-width: 120px; border-collapse: collapse; border-spacing: 0; padding: 0;\"><tr><td align=\"center\" valign=\"middle\" style=\"padding: 12px 24px; margin: 0; text-decoration: underline; border-collapse: collapse; border-spacing: 0; border-radius: 4px; -webkit-border-radius: 4px; -moz-border-radius: 4px; -khtml-border-radius: 4px;\"\r\n" + 
				"					bgcolor=\"#E9703E\"><a target=\"_blank\" style=\"text-decoration: underline;\r\n" + 
				"					color: #FFFFFF; font-family: sans-serif; font-size: 17px; font-weight: 400; line-height: 120%;\"\r\n" + 
				"					href=" + url + ">\r\n" + 
				"						Click Here to View\r\n" + 
				"					</a>\r\n" + 
				"			</td></tr></table></a>\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"	<!-- LINE -->\r\n" + 
				"	<tr>	\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%;\r\n" + 
				"			padding-top: 25px;\" class=\"line\"><hr\r\n" + 
				"			color=\"#E0E0E0\" align=\"center\" width=\"100%\" size=\"1\" noshade style=\"margin: 0; padding: 0;\" />\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"	<!-- LINE -->\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%; font-size: 17px; font-weight: 400; line-height: 160%;\r\n" + 
				"			padding-top: 20px;\r\n" + 
				"			padding-bottom: 25px;\r\n" + 
				"			color: #000000;\r\n" + 
				"			font-family: sans-serif;\" class=\"paragraph\">\r\n" + 
				"				For Support: Send Email to <a href=\"mailto:support@ourteam.com\" target=\"_blank\" style=\"color: #127DB3; font-family: sans-serif; font-size: 17px; font-weight: 400; line-height: 160%;\">abu.taleb@dynamicsrv.com</a>\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"<!-- End of WRAPPER -->\r\n" + 
				"</table>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"<!-- End of SECTION / BACKGROUND -->\r\n" + 
				"</td></tr></table>\r\n" + 
				"\r\n" + 
				"</body>");
		
		// Convert to string. 
		String result = builder.toString();
		sendMailToWithoutCC(mailSender, candMail,"Welcome! ("+ candidate.getCandId() +") For Mr./Ms "+ candidate.getCandName() + "", result);
	}
	
	public void candidate1stQuestSentMail(JavaMailSender mailSender, Candidate candidate, String commonEmailAddress) throws MessagingException, javax.mail.MessagingException{
		
		String candMail=candidate.getCandEmail();
		
		if(candMail.equals(null)||candMail.equals("")){
			candMail=commonEmailAddress;
		}
		
		// Create a new StringBuilder.
		StringBuilder builder = new StringBuilder();
		
		builder.append("<body topmargin=\"0\" rightmargin=\"0\" bottommargin=\"0\" leftmargin=\"0\" marginwidth=\"0\" marginheight=\"0\" width=\"100%\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; width: 100%; height: 100%; -webkit-font-smoothing: antialiased; text-size-adjust: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; line-height: 100%;\r\n" + 
				"	background-color: #F0F0F0;\r\n" + 
				"	color: #000000;\"\r\n" + 
				"	bgcolor=\"#F0F0F0\"\r\n" + 
				"	text=\"#000000\">\r\n" +  
				"<!-- Set message background color one again -->\r\n" + 
				"<table width=\"100%\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; width: 100%;\" class=\"background\"><tr><td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0;\"\r\n" + 
				"	bgcolor=\"#F0F0F0\">\r\n" + 
				"\r\n" + 
				"<!-- WRAPPER -->\r\n" + 
				"<!-- Set wrapper width (twice) -->\r\n" + 
				"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\"\r\n" + 
				"	width=\"560\" style=\"border-collapse: collapse; border-spacing: 0; padding: 0; width: inherit;\r\n" + 
				"	max-width: 560px;\" class=\"wrapper\">\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%;\r\n" + 
				"			padding-top: 20px;\r\n" + 
				"			padding-bottom: 20px;\">\r\n" + 
				"\r\n" + 
				"			<a target=\"_blank\" style=\"text-decoration: none;\"\r\n" + 
				"				href=\"https://www.syn-ergy.com\"><img border=\"0\" vspace=\"0\" hspace=\"0\"\r\n" + 
				"				src='http://www.syn-ergy.com/wp-content/themes/synergy/img/logo.jpg'" + 
				"				width=\"200\" height=\"60\"\r\n" + 
				"				alt=\"Logo\" title=\"Logo\" style=\"\r\n" + 
				"				color: #000000;\r\n" + 
				"				font-size: 10px; margin: 0; padding: 0; outline: none; text-decoration: none; -ms-interpolation-mode: bicubic; border: none; display: block;\" /></a>\r\n" + 
				"\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"<!-- End of WRAPPER -->\r\n" + 
				"</table>\r\n" + 
				"\r\n" + 
				"<!-- WRAPPER / CONTEINER -->\r\n" + 
				"<!-- Set conteiner background color -->\r\n" + 
				"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\"\r\n" + 
				"	bgcolor=\"#FFFFFF\"\r\n" + 
				"	width=\"560\" style=\"border-collapse: collapse; border-spacing: 0; padding: 0; width: inherit;\r\n" + 
				"	max-width: 560px;\" class=\"container\">\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%; font-size: 24px; font-weight: bold; line-height: 130%;\r\n" + 
				"			padding-top: 25px;\r\n" + 
				"			color: #000000;\r\n" + 
				"			font-family: sans-serif;\" class=\"header\">\r\n" + 
				"				First Question Set Sent For Feedback!\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" +  
				"	</tr>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0;\r\n" + 
				"			padding-top: 20px;\" class=\"hero\">				\r\n" + 
				"					\r\n" + 
				"					<tr style=\"background-color:#D9E2DF\">\r\n" + 
				"						<td align=\"left\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%; font-size: 17px; font-weight: 400; line-height: 160%;\r\n" + 
				"							padding-top: 25px; \r\n" + 
				"							color: #000000;\r\n" + 
				"							font-family: sans-serif;\" class=\"paragraph\">");
		
		
		builder.append("Dear Mr./Ms. " + candidate.getCandName() + ", <br/>");
		builder.append("Good Morning!");			
		builder.append("<br/>");		
		builder.append("<p>First Question set already sent to you througing Synergy EJMS. Your feedback is very emergency for us. Its urgent for HRD due to First day's experience.</p>");		
		//builder.append("<br/>");	
		builder.append("<p>Thank you so much.</p> ");		
		//builder.append("<br/>");	
		builder.append("<p><b>Best Regards</b></p> ");
		//builder.append("<br/>");	
		builder.append("<p><b>Human Resource Department</b></p> "); 
		
		builder.append("<br/>");	
		
		//builder.append("<h3> <b> N.B.: Please Login to EJMS from bellow link and submit your feedback as early as possible to HRD about recruitement process. </b> </h3>");
		builder.append("<h3> <b> N.B.: Please Login to EJMS and submit your feedback to HRD about First day's experience by tomorrow. </b> </h3>");
		
		builder.append("</td>\r\n" + 
				"					</tr>\r\n" + 
				"				\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"	\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"	<!-- LINE -->\r\n" + 
				"	<tr>	\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%;\r\n" + 
				"			padding-top: 25px;\" class=\"line\"><hr\r\n" + 
				"			color=\"#E0E0E0\" align=\"center\" width=\"100%\" size=\"1\" noshade style=\"margin: 0; padding: 0;\" />\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"	<!-- LINE -->\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%; font-size: 17px; font-weight: 400; line-height: 160%;\r\n" + 
				"			padding-top: 20px;\r\n" + 
				"			padding-bottom: 25px;\r\n" + 
				"			color: #000000;\r\n" + 
				"			font-family: sans-serif;\" class=\"paragraph\">\r\n" + 
				"				For Support: Send Email to <a href=\"mailto:support@ourteam.com\" target=\"_blank\" style=\"color: #127DB3; font-family: sans-serif; font-size: 17px; font-weight: 400; line-height: 160%;\">abu.taleb@dynamicsrv.com</a>\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"<!-- End of WRAPPER -->\r\n" + 
				"</table>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"<!-- End of SECTION / BACKGROUND -->\r\n" + 
				"</td></tr></table>\r\n" + 
				"\r\n" + 
				"</body>");
		
		// Convert to string. 
		String result = builder.toString();
		sendMailToWithoutCC(mailSender, candMail,"First Question Set Sent For Feedback! ("+ candidate.getCandId() +") For Mr./Ms "+ candidate.getCandName() + "", result);
	}
	
	public void candidate2ndQuestSentMail(JavaMailSender mailSender, Candidate candidate, String commonEmailAddress) throws MessagingException, javax.mail.MessagingException{
		
		String candMail=candidate.getCandEmail();
		
		if(candMail.equals(null)||candMail.equals("")){
			candMail=commonEmailAddress;
		}
		
		// Create a new StringBuilder.
		StringBuilder builder = new StringBuilder();
		
		builder.append("<body topmargin=\"0\" rightmargin=\"0\" bottommargin=\"0\" leftmargin=\"0\" marginwidth=\"0\" marginheight=\"0\" width=\"100%\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; width: 100%; height: 100%; -webkit-font-smoothing: antialiased; text-size-adjust: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; line-height: 100%;\r\n" + 
				"	background-color: #F0F0F0;\r\n" + 
				"	color: #000000;\"\r\n" + 
				"	bgcolor=\"#F0F0F0\"\r\n" + 
				"	text=\"#000000\">\r\n" +  
				"<!-- Set message background color one again -->\r\n" + 
				"<table width=\"100%\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; width: 100%;\" class=\"background\"><tr><td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0;\"\r\n" + 
				"	bgcolor=\"#F0F0F0\">\r\n" + 
				"\r\n" + 
				"<!-- WRAPPER -->\r\n" + 
				"<!-- Set wrapper width (twice) -->\r\n" + 
				"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\"\r\n" + 
				"	width=\"560\" style=\"border-collapse: collapse; border-spacing: 0; padding: 0; width: inherit;\r\n" + 
				"	max-width: 560px;\" class=\"wrapper\">\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%;\r\n" + 
				"			padding-top: 20px;\r\n" + 
				"			padding-bottom: 20px;\">\r\n" + 
				"\r\n" + 
				"			<a target=\"_blank\" style=\"text-decoration: none;\"\r\n" + 
				"				href=\"https://www.syn-ergy.com\"><img border=\"0\" vspace=\"0\" hspace=\"0\"\r\n" + 
				"				src='http://www.syn-ergy.com/wp-content/themes/synergy/img/logo.jpg'" + 
				"				width=\"200\" height=\"60\"\r\n" + 
				"				alt=\"Logo\" title=\"Logo\" style=\"\r\n" + 
				"				color: #000000;\r\n" + 
				"				font-size: 10px; margin: 0; padding: 0; outline: none; text-decoration: none; -ms-interpolation-mode: bicubic; border: none; display: block;\" /></a>\r\n" + 
				"\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"<!-- End of WRAPPER -->\r\n" + 
				"</table>\r\n" + 
				"\r\n" + 
				"<!-- WRAPPER / CONTEINER -->\r\n" + 
				"<!-- Set conteiner background color -->\r\n" + 
				"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\"\r\n" + 
				"	bgcolor=\"#FFFFFF\"\r\n" + 
				"	width=\"560\" style=\"border-collapse: collapse; border-spacing: 0; padding: 0; width: inherit;\r\n" + 
				"	max-width: 560px;\" class=\"container\">\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%; font-size: 24px; font-weight: bold; line-height: 130%;\r\n" + 
				"			padding-top: 25px;\r\n" + 
				"			color: #000000;\r\n" + 
				"			font-family: sans-serif;\" class=\"header\">\r\n" + 
				"				Second Question Set Sent For Feedback!\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" +  
				"	</tr>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0;\r\n" + 
				"			padding-top: 20px;\" class=\"hero\">				\r\n" + 
				"					\r\n" + 
				"					<tr style=\"background-color:#D9E2DF\">\r\n" + 
				"						<td align=\"left\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%; font-size: 17px; font-weight: 400; line-height: 160%;\r\n" + 
				"							padding-top: 25px; \r\n" + 
				"							color: #000000;\r\n" + 
				"							font-family: sans-serif;\" class=\"paragraph\">");
		
		
		builder.append("Dear Mr./Ms. " + candidate.getCandName() + ", <br/>");
		builder.append("Good Morning!");			
		builder.append("<br/>");		
		builder.append("<p>Second Question set already sent to you througing Synergy EJMS. Your feedback is very emergency for us. Its urgent for HRD due to 7 days experience.</p>");		
		//builder.append("<br/>");	
		builder.append("<p>Thank you so much.</p> ");		
		//builder.append("<br/>");	
		builder.append("<p><b>Best Regards</b></p> ");
		//builder.append("<br/>");	
		builder.append("<p><b>Human Resource Department</b></p> "); 
		
		builder.append("<br/>");	
		
		//builder.append("<h3> <b> N.B.: Please Login to EJMS from bellow link and submit your feedback as early as possible to HRD about recruitement process. </b> </h3>");
		builder.append("<h3> <b> N.B.: Please Login to EJMS and submit your feedback to HRD about 7 days experience by tomorrow. </b> </h3>");
		
		builder.append("</td>\r\n" + 
				"					</tr>\r\n" + 
				"				\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"	\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"	<!-- LINE -->\r\n" + 
				"	<tr>	\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%;\r\n" + 
				"			padding-top: 25px;\" class=\"line\"><hr\r\n" + 
				"			color=\"#E0E0E0\" align=\"center\" width=\"100%\" size=\"1\" noshade style=\"margin: 0; padding: 0;\" />\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"	<!-- LINE -->\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%; font-size: 17px; font-weight: 400; line-height: 160%;\r\n" + 
				"			padding-top: 20px;\r\n" + 
				"			padding-bottom: 25px;\r\n" + 
				"			color: #000000;\r\n" + 
				"			font-family: sans-serif;\" class=\"paragraph\">\r\n" + 
				"				For Support: Send Email to <a href=\"mailto:support@ourteam.com\" target=\"_blank\" style=\"color: #127DB3; font-family: sans-serif; font-size: 17px; font-weight: 400; line-height: 160%;\">abu.taleb@dynamicsrv.com</a>\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"<!-- End of WRAPPER -->\r\n" + 
				"</table>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"<!-- End of SECTION / BACKGROUND -->\r\n" + 
				"</td></tr></table>\r\n" + 
				"\r\n" + 
				"</body>");
		
		// Convert to string. 
		String result = builder.toString();
		sendMailToWithoutCC(mailSender, candMail,"Second Question Set Sent For Feedback! ("+ candidate.getCandId() +") For Mr./Ms "+ candidate.getCandName() + "", result);
	}
	
	public void candidate2ndFeedBackMail(JavaMailSender mailSender, Candidate candidate, String url, String commonEmailAddress) throws MessagingException, javax.mail.MessagingException{
		
		// Create a new StringBuilder.
		StringBuilder builder = new StringBuilder();
		
		builder.append("<body topmargin=\"0\" rightmargin=\"0\" bottommargin=\"0\" leftmargin=\"0\" marginwidth=\"0\" marginheight=\"0\" width=\"100%\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; width: 100%; height: 100%; -webkit-font-smoothing: antialiased; text-size-adjust: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; line-height: 100%;\r\n" + 
				"	background-color: #F0F0F0;\r\n" + 
				"	color: #000000;\"\r\n" + 
				"	bgcolor=\"#F0F0F0\"\r\n" + 
				"	text=\"#000000\">\r\n" +  
				"<!-- Set message background color one again -->\r\n" + 
				"<table width=\"100%\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; width: 100%;\" class=\"background\"><tr><td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0;\"\r\n" + 
				"	bgcolor=\"#F0F0F0\">\r\n" + 
				"\r\n" + 
				"<!-- WRAPPER -->\r\n" + 
				"<!-- Set wrapper width (twice) -->\r\n" + 
				"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\"\r\n" + 
				"	width=\"560\" style=\"border-collapse: collapse; border-spacing: 0; padding: 0; width: inherit;\r\n" + 
				"	max-width: 560px;\" class=\"wrapper\">\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%;\r\n" + 
				"			padding-top: 20px;\r\n" + 
				"			padding-bottom: 20px;\">\r\n" + 
				"\r\n" + 
				"			<a target=\"_blank\" style=\"text-decoration: none;\"\r\n" + 
				"				href=\"https://www.syn-ergy.com\"><img border=\"0\" vspace=\"0\" hspace=\"0\"\r\n" + 
				"				src='http://www.syn-ergy.com/wp-content/themes/synergy/img/logo.jpg'" + 
				"				width=\"200\" height=\"60\"\r\n" + 
				"				alt=\"Logo\" title=\"Logo\" style=\"\r\n" + 
				"				color: #000000;\r\n" + 
				"				font-size: 10px; margin: 0; padding: 0; outline: none; text-decoration: none; -ms-interpolation-mode: bicubic; border: none; display: block;\" /></a>\r\n" + 
				"\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"<!-- End of WRAPPER -->\r\n" + 
				"</table>\r\n" + 
				"\r\n" + 
				"<!-- WRAPPER / CONTEINER -->\r\n" + 
				"<!-- Set conteiner background color -->\r\n" + 
				"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\"\r\n" + 
				"	bgcolor=\"#FFFFFF\"\r\n" + 
				"	width=\"560\" style=\"border-collapse: collapse; border-spacing: 0; padding: 0; width: inherit;\r\n" + 
				"	max-width: 560px;\" class=\"container\">\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%; font-size: 24px; font-weight: bold; line-height: 130%;\r\n" + 
				"			padding-top: 25px;\r\n" + 
				"			color: #000000;\r\n" + 
				"			font-family: sans-serif;\" class=\"header\">\r\n" + 
				"				Second Feedback From Candidate!\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" +  
				"	</tr>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0;\r\n" + 
				"			padding-top: 20px;\" class=\"hero\">				\r\n" + 
				"					\r\n" + 
				"					<tr style=\"background-color:#D9E2DF\">\r\n" + 
				"						<td align=\"left\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%; font-size: 17px; font-weight: 400; line-height: 160%;\r\n" + 
				"							padding-top: 25px; \r\n" + 
				"							color: #000000;\r\n" + 
				"							font-family: sans-serif;\" class=\"paragraph\">");
		
		
		builder.append("Dear Concern, <br/>");
		builder.append("<p>Please check the Synergy EJMS. My 7 days experience feedback has been submitted.</p>");	
		builder.append("<p>Thank You.</p>");	
		builder.append("<p><b>Best Regards</b></p> ");
		builder.append("<p><b>" + candidate.getCandName() + "</b></p> "); 
		builder.append("<p><b>" + candidate.getCandId() + "</b></p> "); 
		builder.append("<br/>");
		
		builder.append("</td>\r\n" + 
				"					</tr>\r\n" + 
				"				\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"	\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%;\r\n" + 
				"			padding-top: 25px;\r\n" + 
				"			padding-bottom: 5px;\" class=\"button\"><a\r\n" + 
				"			href=\"https://github.com/konsav/email-templates/\" target=\"_blank\" style=\"text-decoration: underline;\">\r\n" + 
				"				<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" style=\"max-width: 240px; min-width: 120px; border-collapse: collapse; border-spacing: 0; padding: 0;\"><tr><td align=\"center\" valign=\"middle\" style=\"padding: 12px 24px; margin: 0; text-decoration: underline; border-collapse: collapse; border-spacing: 0; border-radius: 4px; -webkit-border-radius: 4px; -moz-border-radius: 4px; -khtml-border-radius: 4px;\"\r\n" + 
				"					bgcolor=\"#E9703E\"><a target=\"_blank\" style=\"text-decoration: underline;\r\n" + 
				"					color: #FFFFFF; font-family: sans-serif; font-size: 17px; font-weight: 400; line-height: 120%;\"\r\n" + 
				"					href=" + url + ">\r\n" + 
				"						Click Here to View\r\n" + 
				"					</a>\r\n" + 
				"			</td></tr></table></a>\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"	<!-- LINE -->\r\n" + 
				"	<tr>	\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%;\r\n" + 
				"			padding-top: 25px;\" class=\"line\"><hr\r\n" + 
				"			color=\"#E0E0E0\" align=\"center\" width=\"100%\" size=\"1\" noshade style=\"margin: 0; padding: 0;\" />\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"	<!-- LINE -->\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%; font-size: 17px; font-weight: 400; line-height: 160%;\r\n" + 
				"			padding-top: 20px;\r\n" + 
				"			padding-bottom: 25px;\r\n" + 
				"			color: #000000;\r\n" + 
				"			font-family: sans-serif;\" class=\"paragraph\">\r\n" + 
				"				For Support: Send Email to <a href=\"mailto:support@ourteam.com\" target=\"_blank\" style=\"color: #127DB3; font-family: sans-serif; font-size: 17px; font-weight: 400; line-height: 160%;\">abu.taleb@dynamicsrv.com</a>\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"<!-- End of WRAPPER -->\r\n" + 
				"</table>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"<!-- End of SECTION / BACKGROUND -->\r\n" + 
				"</td></tr></table>\r\n" + 
				"\r\n" + 
				"</body>");
		
		// Convert to string. 
		String result = builder.toString();
		sendMailToWithoutCC(mailSender, commonEmailAddress,"Second Feedback from! ("+ candidate.getCandId() +") For Mr./Ms "+ candidate.getCandName() + "", result);
	}
	
	public void candidate1stFeedBackMail(JavaMailSender mailSender, Candidate candidate, String url, String commonEmailAddress) throws MessagingException, javax.mail.MessagingException{
		
		// Create a new StringBuilder.
		StringBuilder builder = new StringBuilder();
		
		builder.append("<body topmargin=\"0\" rightmargin=\"0\" bottommargin=\"0\" leftmargin=\"0\" marginwidth=\"0\" marginheight=\"0\" width=\"100%\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; width: 100%; height: 100%; -webkit-font-smoothing: antialiased; text-size-adjust: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; line-height: 100%;\r\n" + 
				"	background-color: #F0F0F0;\r\n" + 
				"	color: #000000;\"\r\n" + 
				"	bgcolor=\"#F0F0F0\"\r\n" + 
				"	text=\"#000000\">\r\n" +  
				"<!-- Set message background color one again -->\r\n" + 
				"<table width=\"100%\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; width: 100%;\" class=\"background\"><tr><td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0;\"\r\n" + 
				"	bgcolor=\"#F0F0F0\">\r\n" + 
				"\r\n" + 
				"<!-- WRAPPER -->\r\n" + 
				"<!-- Set wrapper width (twice) -->\r\n" + 
				"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\"\r\n" + 
				"	width=\"560\" style=\"border-collapse: collapse; border-spacing: 0; padding: 0; width: inherit;\r\n" + 
				"	max-width: 560px;\" class=\"wrapper\">\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%;\r\n" + 
				"			padding-top: 20px;\r\n" + 
				"			padding-bottom: 20px;\">\r\n" + 
				"\r\n" + 
				"			<a target=\"_blank\" style=\"text-decoration: none;\"\r\n" + 
				"				href=\"https://www.syn-ergy.com\"><img border=\"0\" vspace=\"0\" hspace=\"0\"\r\n" + 
				"				src='http://www.syn-ergy.com/wp-content/themes/synergy/img/logo.jpg'" + 
				"				width=\"200\" height=\"60\"\r\n" + 
				"				alt=\"Logo\" title=\"Logo\" style=\"\r\n" + 
				"				color: #000000;\r\n" + 
				"				font-size: 10px; margin: 0; padding: 0; outline: none; text-decoration: none; -ms-interpolation-mode: bicubic; border: none; display: block;\" /></a>\r\n" + 
				"\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"<!-- End of WRAPPER -->\r\n" + 
				"</table>\r\n" + 
				"\r\n" + 
				"<!-- WRAPPER / CONTEINER -->\r\n" + 
				"<!-- Set conteiner background color -->\r\n" + 
				"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\"\r\n" + 
				"	bgcolor=\"#FFFFFF\"\r\n" + 
				"	width=\"560\" style=\"border-collapse: collapse; border-spacing: 0; padding: 0; width: inherit;\r\n" + 
				"	max-width: 560px;\" class=\"container\">\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%; font-size: 24px; font-weight: bold; line-height: 130%;\r\n" + 
				"			padding-top: 25px;\r\n" + 
				"			color: #000000;\r\n" + 
				"			font-family: sans-serif;\" class=\"header\">\r\n" + 
				"				First Feedback From Candidate!\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" +  
				"	</tr>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0;\r\n" + 
				"			padding-top: 20px;\" class=\"hero\">				\r\n" + 
				"					\r\n" + 
				"					<tr style=\"background-color:#D9E2DF\">\r\n" + 
				"						<td align=\"left\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%; font-size: 17px; font-weight: 400; line-height: 160%;\r\n" + 
				"							padding-top: 25px; \r\n" + 
				"							color: #000000;\r\n" + 
				"							font-family: sans-serif;\" class=\"paragraph\">");
		
		
		builder.append("Dear Concern, <br/>");
		builder.append("<p>Please check the Synergy EJMS. My First day's experience feedback has been submitted.</p>");	
		builder.append("<p>Thank You.</p>");	
		builder.append("<p><b>Best Regards</b></p> ");
		builder.append("<p><b>" + candidate.getCandName() + "</b></p> "); 
		builder.append("<p><b>" + candidate.getCandId() + "</b></p> "); 
		builder.append("<br/>");
		
		builder.append("</td>\r\n" + 
				"					</tr>\r\n" + 
				"				\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"	\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%;\r\n" + 
				"			padding-top: 25px;\r\n" + 
				"			padding-bottom: 5px;\" class=\"button\"><a\r\n" + 
				"			href=\"https://github.com/konsav/email-templates/\" target=\"_blank\" style=\"text-decoration: underline;\">\r\n" + 
				"				<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" style=\"max-width: 240px; min-width: 120px; border-collapse: collapse; border-spacing: 0; padding: 0;\"><tr><td align=\"center\" valign=\"middle\" style=\"padding: 12px 24px; margin: 0; text-decoration: underline; border-collapse: collapse; border-spacing: 0; border-radius: 4px; -webkit-border-radius: 4px; -moz-border-radius: 4px; -khtml-border-radius: 4px;\"\r\n" + 
				"					bgcolor=\"#E9703E\"><a target=\"_blank\" style=\"text-decoration: underline;\r\n" + 
				"					color: #FFFFFF; font-family: sans-serif; font-size: 17px; font-weight: 400; line-height: 120%;\"\r\n" + 
				"					href=" + url + ">\r\n" + 
				"						Click Here to View\r\n" + 
				"					</a>\r\n" + 
				"			</td></tr></table></a>\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"	<!-- LINE -->\r\n" + 
				"	<tr>	\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%;\r\n" + 
				"			padding-top: 25px;\" class=\"line\"><hr\r\n" + 
				"			color=\"#E0E0E0\" align=\"center\" width=\"100%\" size=\"1\" noshade style=\"margin: 0; padding: 0;\" />\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"	<!-- LINE -->\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%; font-size: 17px; font-weight: 400; line-height: 160%;\r\n" + 
				"			padding-top: 20px;\r\n" + 
				"			padding-bottom: 25px;\r\n" + 
				"			color: #000000;\r\n" + 
				"			font-family: sans-serif;\" class=\"paragraph\">\r\n" + 
				"				For Support: Send Email to <a href=\"mailto:support@ourteam.com\" target=\"_blank\" style=\"color: #127DB3; font-family: sans-serif; font-size: 17px; font-weight: 400; line-height: 160%;\">abu.taleb@dynamicsrv.com</a>\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"<!-- End of WRAPPER -->\r\n" + 
				"</table>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"<!-- End of SECTION / BACKGROUND -->\r\n" + 
				"</td></tr></table>\r\n" + 
				"\r\n" + 
				"</body>");
		
		// Convert to string. 
		String result = builder.toString();
		sendMailToWithoutCC(mailSender, commonEmailAddress,"First Feedback from! ("+ candidate.getCandId() +") For Mr./Ms "+ candidate.getCandName() + "", result);
	}

	public void candidate3rdFeedBackMail(JavaMailSender mailSender, Candidate candidate, String url, String commonEmailAddress) throws MessagingException, javax.mail.MessagingException{
		
		// Create a new StringBuilder.
		StringBuilder builder = new StringBuilder();
		
		builder.append("<body topmargin=\"0\" rightmargin=\"0\" bottommargin=\"0\" leftmargin=\"0\" marginwidth=\"0\" marginheight=\"0\" width=\"100%\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; width: 100%; height: 100%; -webkit-font-smoothing: antialiased; text-size-adjust: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; line-height: 100%;\r\n" + 
				"	background-color: #F0F0F0;\r\n" + 
				"	color: #000000;\"\r\n" + 
				"	bgcolor=\"#F0F0F0\"\r\n" + 
				"	text=\"#000000\">\r\n" +  
				"<!-- Set message background color one again -->\r\n" + 
				"<table width=\"100%\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; width: 100%;\" class=\"background\"><tr><td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0;\"\r\n" + 
				"	bgcolor=\"#F0F0F0\">\r\n" + 
				"\r\n" + 
				"<!-- WRAPPER -->\r\n" + 
				"<!-- Set wrapper width (twice) -->\r\n" + 
				"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\"\r\n" + 
				"	width=\"560\" style=\"border-collapse: collapse; border-spacing: 0; padding: 0; width: inherit;\r\n" + 
				"	max-width: 560px;\" class=\"wrapper\">\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%;\r\n" + 
				"			padding-top: 20px;\r\n" + 
				"			padding-bottom: 20px;\">\r\n" + 
				"\r\n" + 
				"			<a target=\"_blank\" style=\"text-decoration: none;\"\r\n" + 
				"				href=\"https://www.syn-ergy.com\"><img border=\"0\" vspace=\"0\" hspace=\"0\"\r\n" + 
				"				src='http://www.syn-ergy.com/wp-content/themes/synergy/img/logo.jpg'" + 
				"				width=\"200\" height=\"60\"\r\n" + 
				"				alt=\"Logo\" title=\"Logo\" style=\"\r\n" + 
				"				color: #000000;\r\n" + 
				"				font-size: 10px; margin: 0; padding: 0; outline: none; text-decoration: none; -ms-interpolation-mode: bicubic; border: none; display: block;\" /></a>\r\n" + 
				"\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"<!-- End of WRAPPER -->\r\n" + 
				"</table>\r\n" + 
				"\r\n" + 
				"<!-- WRAPPER / CONTEINER -->\r\n" + 
				"<!-- Set conteiner background color -->\r\n" + 
				"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\"\r\n" + 
				"	bgcolor=\"#FFFFFF\"\r\n" + 
				"	width=\"560\" style=\"border-collapse: collapse; border-spacing: 0; padding: 0; width: inherit;\r\n" + 
				"	max-width: 560px;\" class=\"container\">\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%; font-size: 24px; font-weight: bold; line-height: 130%;\r\n" + 
				"			padding-top: 25px;\r\n" + 
				"			color: #000000;\r\n" + 
				"			font-family: sans-serif;\" class=\"header\">\r\n" + 
				"				Third Feedback From Candidate!\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" +  
				"	</tr>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0;\r\n" + 
				"			padding-top: 20px;\" class=\"hero\">				\r\n" + 
				"					\r\n" + 
				"					<tr style=\"background-color:#D9E2DF\">\r\n" + 
				"						<td align=\"left\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%; font-size: 17px; font-weight: 400; line-height: 160%;\r\n" + 
				"							padding-top: 25px; \r\n" + 
				"							color: #000000;\r\n" + 
				"							font-family: sans-serif;\" class=\"paragraph\">");
		
		
		builder.append("Dear Concern, <br/>");
		builder.append("<p>Please check the Synergy EJMS. My 30 days experience feedback has been submitted.</p>");	
		builder.append("<p>Thank You.</p>");	
		builder.append("<p><b>Best Regards</b></p> ");
		builder.append("<p><b>" + candidate.getCandName() + "</b></p> "); 
		builder.append("<p><b>" + candidate.getCandId() + "</b></p> "); 
		builder.append("<br/>");
		
		builder.append("</td>\r\n" + 
				"					</tr>\r\n" + 
				"				\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"	\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%;\r\n" + 
				"			padding-top: 25px;\r\n" + 
				"			padding-bottom: 5px;\" class=\"button\"><a\r\n" + 
				"			href=\"https://github.com/konsav/email-templates/\" target=\"_blank\" style=\"text-decoration: underline;\">\r\n" + 
				"				<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" style=\"max-width: 240px; min-width: 120px; border-collapse: collapse; border-spacing: 0; padding: 0;\"><tr><td align=\"center\" valign=\"middle\" style=\"padding: 12px 24px; margin: 0; text-decoration: underline; border-collapse: collapse; border-spacing: 0; border-radius: 4px; -webkit-border-radius: 4px; -moz-border-radius: 4px; -khtml-border-radius: 4px;\"\r\n" + 
				"					bgcolor=\"#E9703E\"><a target=\"_blank\" style=\"text-decoration: underline;\r\n" + 
				"					color: #FFFFFF; font-family: sans-serif; font-size: 17px; font-weight: 400; line-height: 120%;\"\r\n" + 
				"					href=" + url + ">\r\n" + 
				"						Click Here to View\r\n" + 
				"					</a>\r\n" + 
				"			</td></tr></table></a>\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"	<!-- LINE -->\r\n" + 
				"	<tr>	\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%;\r\n" + 
				"			padding-top: 25px;\" class=\"line\"><hr\r\n" + 
				"			color=\"#E0E0E0\" align=\"center\" width=\"100%\" size=\"1\" noshade style=\"margin: 0; padding: 0;\" />\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"	<!-- LINE -->\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%; font-size: 17px; font-weight: 400; line-height: 160%;\r\n" + 
				"			padding-top: 20px;\r\n" + 
				"			padding-bottom: 25px;\r\n" + 
				"			color: #000000;\r\n" + 
				"			font-family: sans-serif;\" class=\"paragraph\">\r\n" + 
				"				For Support: Send Email to <a href=\"mailto:support@ourteam.com\" target=\"_blank\" style=\"color: #127DB3; font-family: sans-serif; font-size: 17px; font-weight: 400; line-height: 160%;\">abu.taleb@dynamicsrv.com</a>\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"<!-- End of WRAPPER -->\r\n" + 
				"</table>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"<!-- End of SECTION / BACKGROUND -->\r\n" + 
				"</td></tr></table>\r\n" + 
				"\r\n" + 
				"</body>");
		
		// Convert to string. 
		String result = builder.toString();
		sendMailToWithoutCC(mailSender, commonEmailAddress,"Third Feedback from! ("+ candidate.getCandId() +") For Mr./Ms "+ candidate.getCandName() + "", result);
	}
	
	public void candidate3rdQuestSentMail(JavaMailSender mailSender, Candidate candidate, String commonEmailAddress) throws MessagingException, javax.mail.MessagingException{
		
		String candMail=candidate.getCandEmail();
		
		if(candMail.equals(null)||candMail.equals("")){
			candMail=commonEmailAddress;
		}
		
		// Create a new StringBuilder.
		StringBuilder builder = new StringBuilder();
		
		builder.append("<body topmargin=\"0\" rightmargin=\"0\" bottommargin=\"0\" leftmargin=\"0\" marginwidth=\"0\" marginheight=\"0\" width=\"100%\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; width: 100%; height: 100%; -webkit-font-smoothing: antialiased; text-size-adjust: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; line-height: 100%;\r\n" + 
				"	background-color: #F0F0F0;\r\n" + 
				"	color: #000000;\"\r\n" + 
				"	bgcolor=\"#F0F0F0\"\r\n" + 
				"	text=\"#000000\">\r\n" +  
				"<!-- Set message background color one again -->\r\n" + 
				"<table width=\"100%\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; width: 100%;\" class=\"background\"><tr><td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0;\"\r\n" + 
				"	bgcolor=\"#F0F0F0\">\r\n" + 
				"\r\n" + 
				"<!-- WRAPPER -->\r\n" + 
				"<!-- Set wrapper width (twice) -->\r\n" + 
				"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\"\r\n" + 
				"	width=\"560\" style=\"border-collapse: collapse; border-spacing: 0; padding: 0; width: inherit;\r\n" + 
				"	max-width: 560px;\" class=\"wrapper\">\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%;\r\n" + 
				"			padding-top: 20px;\r\n" + 
				"			padding-bottom: 20px;\">\r\n" + 
				"\r\n" + 
				"			<a target=\"_blank\" style=\"text-decoration: none;\"\r\n" + 
				"				href=\"https://www.syn-ergy.com\"><img border=\"0\" vspace=\"0\" hspace=\"0\"\r\n" + 
				"				src='http://www.syn-ergy.com/wp-content/themes/synergy/img/logo.jpg'" + 
				"				width=\"200\" height=\"60\"\r\n" + 
				"				alt=\"Logo\" title=\"Logo\" style=\"\r\n" + 
				"				color: #000000;\r\n" + 
				"				font-size: 10px; margin: 0; padding: 0; outline: none; text-decoration: none; -ms-interpolation-mode: bicubic; border: none; display: block;\" /></a>\r\n" + 
				"\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"<!-- End of WRAPPER -->\r\n" + 
				"</table>\r\n" + 
				"\r\n" + 
				"<!-- WRAPPER / CONTEINER -->\r\n" + 
				"<!-- Set conteiner background color -->\r\n" + 
				"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\"\r\n" + 
				"	bgcolor=\"#FFFFFF\"\r\n" + 
				"	width=\"560\" style=\"border-collapse: collapse; border-spacing: 0; padding: 0; width: inherit;\r\n" + 
				"	max-width: 560px;\" class=\"container\">\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%; font-size: 24px; font-weight: bold; line-height: 130%;\r\n" + 
				"			padding-top: 25px;\r\n" + 
				"			color: #000000;\r\n" + 
				"			font-family: sans-serif;\" class=\"header\">\r\n" + 
				"				Final Question Set Sent For Feedback!\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" +  
				"	</tr>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0;\r\n" + 
				"			padding-top: 20px;\" class=\"hero\">				\r\n" + 
				"					\r\n" + 
				"					<tr style=\"background-color:#D9E2DF\">\r\n" + 
				"						<td align=\"left\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%; font-size: 17px; font-weight: 400; line-height: 160%;\r\n" + 
				"							padding-top: 25px; \r\n" + 
				"							color: #000000;\r\n" + 
				"							font-family: sans-serif;\" class=\"paragraph\">");
		
		
		builder.append("Dear Mr./Ms. " + candidate.getCandName() + ", <br/>");
		builder.append("Good Morning!");			
		builder.append("<br/>");		
		builder.append("<p>Final Question set already sent to you througing Synergy EJMS. Your feedback is very emergency for us. Its urgent for HRD due to 30 days experience.</p>");		
		//builder.append("<br/>");	
		builder.append("<p>Thank you so much.</p> ");		
		//builder.append("<br/>");	
		builder.append("<p><b>Best Regards</b></p> ");
		//builder.append("<br/>");	
		builder.append("<p><b>Human Resource Department</b></p> "); 
		
		builder.append("<br/>");	
		
		//builder.append("<h3> <b> N.B.: Please Login to EJMS from bellow link and submit your feedback as early as possible to HRD about recruitement process. </b> </h3>");
		builder.append("<h3> <b> N.B.: Please Login to EJMS and submit your feedback to HRD about 30 days experience by tomorrow. </b> </h3>");
		
		builder.append("</td>\r\n" + 
				"					</tr>\r\n" + 
				"				\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"	\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"	<!-- LINE -->\r\n" + 
				"	<tr>	\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%;\r\n" + 
				"			padding-top: 25px;\" class=\"line\"><hr\r\n" + 
				"			color=\"#E0E0E0\" align=\"center\" width=\"100%\" size=\"1\" noshade style=\"margin: 0; padding: 0;\" />\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"	<!-- LINE -->\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%; font-size: 17px; font-weight: 400; line-height: 160%;\r\n" + 
				"			padding-top: 20px;\r\n" + 
				"			padding-bottom: 25px;\r\n" + 
				"			color: #000000;\r\n" + 
				"			font-family: sans-serif;\" class=\"paragraph\">\r\n" + 
				"				For Support: Send Email to <a href=\"mailto:support@ourteam.com\" target=\"_blank\" style=\"color: #127DB3; font-family: sans-serif; font-size: 17px; font-weight: 400; line-height: 160%;\">abu.taleb@dynamicsrv.com</a>\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"<!-- End of WRAPPER -->\r\n" + 
				"</table>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"<!-- End of SECTION / BACKGROUND -->\r\n" + 
				"</td></tr></table>\r\n" + 
				"\r\n" + 
				"</body>");
		
		// Convert to string. 
		String result = builder.toString();
		sendMailToWithoutCC(mailSender, candMail,"Final Question Set Sent For Feedback! ("+ candidate.getCandId() +") For Mr./Ms "+ candidate.getCandName() + "", result);
	}
	
	public void candidateNecessaryLinkMail(JavaMailSender mailSender, Candidate candidate, String url, String commonEmailAddress) throws MessagingException, javax.mail.MessagingException{
		
		String candMail=candidate.getCandEmail();
		
		if(candMail.equals(null)||candMail.equals("")){
			candMail=commonEmailAddress;
		}
		
		// Create a new StringBuilder.
		StringBuilder builder = new StringBuilder();
		
		builder.append("<body topmargin=\"0\" rightmargin=\"0\" bottommargin=\"0\" leftmargin=\"0\" marginwidth=\"0\" marginheight=\"0\" width=\"100%\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; width: 100%; height: 100%; -webkit-font-smoothing: antialiased; text-size-adjust: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; line-height: 100%;\r\n" + 
				"	background-color: #F0F0F0;\r\n" + 
				"	color: #000000;\"\r\n" + 
				"	bgcolor=\"#F0F0F0\"\r\n" + 
				"	text=\"#000000\">\r\n" +  
				"<!-- Set message background color one again -->\r\n" + 
				"<table width=\"100%\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; width: 100%;\" class=\"background\"><tr><td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0;\"\r\n" + 
				"	bgcolor=\"#F0F0F0\">\r\n" + 
				"\r\n" + 
				"<!-- WRAPPER -->\r\n" + 
				"<!-- Set wrapper width (twice) -->\r\n" + 
				"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\"\r\n" + 
				"	width=\"560\" style=\"border-collapse: collapse; border-spacing: 0; padding: 0; width: inherit;\r\n" + 
				"	max-width: 560px;\" class=\"wrapper\">\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%;\r\n" + 
				"			padding-top: 20px;\r\n" + 
				"			padding-bottom: 20px;\">\r\n" + 
				"\r\n" + 
				"			<a target=\"_blank\" style=\"text-decoration: none;\"\r\n" + 
				"				href=\"https://www.syn-ergy.com\"><img border=\"0\" vspace=\"0\" hspace=\"0\"\r\n" + 
				"				src='http://www.syn-ergy.com/wp-content/themes/synergy/img/logo.jpg'" + 
				"				width=\"200\" height=\"60\"\r\n" + 
				"				alt=\"Logo\" title=\"Logo\" style=\"\r\n" + 
				"				color: #000000;\r\n" + 
				"				font-size: 10px; margin: 0; padding: 0; outline: none; text-decoration: none; -ms-interpolation-mode: bicubic; border: none; display: block;\" /></a>\r\n" + 
				"\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"<!-- End of WRAPPER -->\r\n" + 
				"</table>\r\n" + 
				"\r\n" + 
				"<!-- WRAPPER / CONTEINER -->\r\n" + 
				"<!-- Set conteiner background color -->\r\n" + 
				"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\"\r\n" + 
				"	bgcolor=\"#FFFFFF\"\r\n" + 
				"	width=\"560\" style=\"border-collapse: collapse; border-spacing: 0; padding: 0; width: inherit;\r\n" + 
				"	max-width: 560px;\" class=\"container\">\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%; font-size: 24px; font-weight: bold; line-height: 130%;\r\n" + 
				"			padding-top: 25px;\r\n" + 
				"			color: #000000;\r\n" + 
				"			font-family: sans-serif;\" class=\"header\">\r\n" + 
				"				Necessary Link\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0;\r\n" + 
				"			padding-top: 20px;\" class=\"hero\">				\r\n" + 
				"					\r\n" + 
				"					<tr style=\"background-color:#D9E2DF\">\r\n" + 
				"						<td align=\"left\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%; font-size: 17px; font-weight: 400; line-height: 160%;\r\n" + 
				"							padding-top: 25px; \r\n" + 
				"							color: #000000;\r\n" + 
				"							font-family: sans-serif;\" class=\"paragraph\">");
		
		
		builder.append("Dear Mr./Ms. " + candidate.getCandName() + ", <br/>");	
		
		builder.append("<p><b>Please Check the Necessary Link from bellow, download bank account info and download company policis.</b></p>");
		
		if(candidate.getCompany().getCompanyKeyword().equals(DCIMCH)) {
			builder.append("1. Company website : <a href='http://www.dcimch.com/'> http://www.dcimch.com </a> <br/>");
			builder.append("2. Company Policies    : <a href='https://bit.ly/2KsfvME'> Download Polices </a> <br/>");
			builder.append("3. Bank Account Info    : <a href='https://bit.ly/2DYoykT'> Download Bank Account Info </a>");
		}else {
			builder.append("1. Company website : <a href='http://www.syn-ergy.com/'> http://www.syn-ergy.com </a> <br/>");
			builder.append("2. Domain Link  : <a href='https://outlook.office.com'> https://outlook.office.com </a> <br/>");
			builder.append("3. Leave Management link    : <a href='http://www.synergy-lms.com/login'> http://www.synergy-lms.com/login </a> <br/>");
			builder.append("4. Travel Management link    : <a href='http://www.synergy-lms.com/tms/login'> http://www.synergy-lms.com/tms/login </a> <br/>");
			builder.append("5. Company Policies    : <a href='https://bit.ly/2pM0jBl'> Download Polices </a> <br/>");
			builder.append("6. Bank Account Info    : <a href='https://bit.ly/2DYoykT'> Download Bank Account Info </a>");
		}		
		
		builder.append("<p><b>Best Regards</b></p> ");
		//builder.append("<br/>");	
		builder.append("<p><b>Human Resource Department</b></p> "); 	
		
		builder.append("<br/>");	
		
		//builder.append("<h3> <b> N.B.: Please Login to EJMS from bellow link and submit your feedback to HRD about first day's experience by tomorrow. </b> </h3>");
		
		builder.append("</td>\r\n" + 
				"					</tr>\r\n" + 
				"				\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"	\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%;\r\n" + 
				"			padding-top: 25px;\r\n" + 
				"			padding-bottom: 5px;\" class=\"button\"><a\r\n" + 
				"			href=\"https://github.com/konsav/email-templates/\" target=\"_blank\" style=\"text-decoration: underline;\">\r\n" + 
				"				<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" style=\"max-width: 240px; min-width: 120px; border-collapse: collapse; border-spacing: 0; padding: 0;\"><tr><td align=\"center\" valign=\"middle\" style=\"padding: 12px 24px; margin: 0; text-decoration: underline; border-collapse: collapse; border-spacing: 0; border-radius: 4px; -webkit-border-radius: 4px; -moz-border-radius: 4px; -khtml-border-radius: 4px;\"\r\n" + 
				"					bgcolor=\"#E9703E\"><a target=\"_blank\" style=\"text-decoration: underline;\r\n" + 
				"					color: #FFFFFF; font-family: sans-serif; font-size: 17px; font-weight: 400; line-height: 120%;\"\r\n" + 
				"					href=" + url + ">\r\n" + 
				"						Click Here to View\r\n" + 
				"					</a>\r\n" + 
				"			</td></tr></table></a>\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"	<!-- LINE -->\r\n" + 
				"	<tr>	\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%;\r\n" + 
				"			padding-top: 25px;\" class=\"line\"><hr\r\n" + 
				"			color=\"#E0E0E0\" align=\"center\" width=\"100%\" size=\"1\" noshade style=\"margin: 0; padding: 0;\" />\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"	<!-- LINE -->\r\n" + 
				"\r\n" + 
				"	<tr>\r\n" + 
				"		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%; font-size: 17px; font-weight: 400; line-height: 160%;\r\n" + 
				"			padding-top: 20px;\r\n" + 
				"			padding-bottom: 25px;\r\n" + 
				"			color: #000000;\r\n" + 
				"			font-family: sans-serif;\" class=\"paragraph\">\r\n" + 
				"				For Support: Send Email to <a href=\"mailto:support@ourteam.com\" target=\"_blank\" style=\"color: #127DB3; font-family: sans-serif; font-size: 17px; font-weight: 400; line-height: 160%;\">abu.taleb@dynamicsrv.com</a>\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"\r\n" + 
				"<!-- End of WRAPPER -->\r\n" + 
				"</table>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"<!-- End of SECTION / BACKGROUND -->\r\n" + 
				"</td></tr></table>\r\n" + 
				"\r\n" + 
				"</body>");
		
		// Convert to string. 
		String result = builder.toString();
		sendMailToWithoutCC(mailSender, candMail,"Necessary Link ("+ candidate.getCandId() +") For Mr./Ms "+ candidate.getCandName() + "", result);
	}
	
	public void testMail(JavaMailSender mailSender, String ccEmailAddresss, String email) throws MessagingException, javax.mail.MessagingException{
		String empMail=email;
		
		// Create a new StringBuilder.
		StringBuilder builder = new StringBuilder();
		
		builder.append("<body>");
		builder.append("<h1> Test </h1>");
		builder.append("</body>");
		
		// Convert to string. 
		String result = builder.toString();
		sendMailTo(mailSender, empMail,"Test Mail", result, ccEmailAddresss);
	}

	private String sendMailTo(JavaMailSender mailSender, String to, String b, String c, String ccEmailAddresss) throws MessagingException, javax.mail.MessagingException{
		
		
		String [] ccAll = ccEmailAddresss.split(",");
		Address[] ia = null;
		if(ccEmailAddresss != null && ccEmailAddresss.trim().length() > 0) {
			ia = new InternetAddress[ccAll.length];
		    int i = 0;
		    for (String address : ccAll) {
		        ia[i] = new InternetAddress(address);
		        i++;
		    }
		}
	    
		// creates a simple e-mail object
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(to.trim());
		email.setSubject(b);
		email.setText(c);
		email.setFrom("HRMSMailService");
		MimeMessage message = mailSender.createMimeMessage();
		if(ccEmailAddresss != null && ccEmailAddresss.trim().length() > 0) {
			message.addRecipients(RecipientType.CC, ia);
		}
		
		MimeMessageHelper helper = new MimeMessageHelper(message, false, "utf-8");	
	    helper.setFrom(email.getFrom());
	    helper.setTo(email.getTo());
	    helper.setSubject(email.getSubject());
	    helper.setText("<html><body>"+email.getText()+"</body></html>",true);
		
		// sends the e-mail
		mailSender.send(message);		
		// forwards to the view named "Result"
		return "Result";
	}
	
	private String sendMailToWithoutCC(JavaMailSender mailSender, String to, String b, String c) throws MessagingException, javax.mail.MessagingException{
		
		// creates a simple e-mail object
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(to.trim());
		email.setSubject(b);
		email.setText(c);
		email.setFrom("HRMSMailService");
		MimeMessage message = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message, false, "utf-8");	
	    helper.setFrom(email.getFrom());
	    helper.setTo(email.getTo());
	    helper.setSubject(email.getSubject());
	    helper.setText("<html><body>"+email.getText()+"</body></html>",true);
		
		// sends the e-mail
		mailSender.send(message);		
		// forwards to the view named "Result"
		return "Result";
	}
	
	public boolean isValidEmailAddress(String email) {
		boolean result = true;
		   try {
		      InternetAddress emailAddr = new InternetAddress(email);
		      emailAddr.validate();
		   } catch (Exception ex) {
		      result = false;
		   }
		return result;
	}

}
