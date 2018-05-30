package com.nazdaq.ejms;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.nazdaq.ejms.model.Candidate;
import com.nazdaq.ejms.service.CommonService;
import com.nazdaq.ejms.util.Constants;
import com.nazdaq.ejms.util.SendEmail;
import com.nazdaq.ejms.util.SendSms;

@ComponentScan({"com.nazdaq.ejms"})
@PropertySource("classpath:database.properties")
@Configuration
@EnableScheduling
public class SchedulerJob implements Constants {
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private JavaMailSender mailSender;
	
	private @Autowired HttpServletRequest request;
	
	@Value("${cc.email.addresss}")
	String ccEmailAddresss;
	
	@Value("${common.email.address}")
	String commonEmailAddress;
	
	@Value("${cc.email.addresss.dcimch}")
	String ccEmailAddresssDCIMCH;
	
	@Value("${common.email.address.dcimch}")
	String commonEmailAddressDCIMCH;
	
	java.util.Date date = new java.util.Date();
	Timestamp curTime = new Timestamp(date.getTime());
	
	// 3 days before from join date
	@SuppressWarnings("unchecked")
	public void candidateJoiningReminder(){
		
		//String url= "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/login";
		
		List<Candidate> remCandidateList = new ArrayList<Candidate>();
		
		List<Candidate> candidateList = (List<Candidate>)(Object)
				commonService.getObjectListByAnyColumn("Candidate", "status", CANDIDATE_ACCEPTED_AL);
		
		Date now = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		cal.add(Calendar.DAY_OF_MONTH, 3);
		Date laterDate = cal.getTime();
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String reminderDate = df.format(laterDate);
		
		for (Candidate candidate : candidateList) {
			if(candidate.getDateOfJoin().toString().equals(reminderDate.toString())) {
				remCandidateList.add(candidate);
			}
		}
		
		for (Candidate candidate : remCandidateList) {
			//send email
			SendEmail se = new SendEmail();	
			try {
				if(candidate.getCompany().getCompanyKeyword().equals(DCIMCH)) {					
					se.candidateJoiningReminderMail(mailSender, candidate, commonEmailAddressDCIMCH);
				}else {
					se.candidateJoiningReminderMail(mailSender, candidate, commonEmailAddress);
				}
				
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			//send sms
			String mobileNo = candidate.getCandMobileNo();			
			String formatedMobileNo = "";			
			if(mobileNo != null && !mobileNo.equals(""))  {
				formatedMobileNo = getFormatedMobileNo(mobileNo);
			}			
			if(!formatedMobileNo.equals("")){
				SendSms sendSms = new SendSms();
				sendSms.sendSmsTo(mobileNo, "Dear Mr./Ms. " + candidate.getCandName() + ",\nWe would like to remind you that coming " + candidate.getDateOfJoin().toString() + " is your joining date. Please check your email for details.");
			}
		}
	}
	
	// First question set sending to candidate 1 day's after from join date
	public void firstQuesSetSending() {
		
		//String url= "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/login";
		List<Candidate> remCandidateList = new ArrayList<Candidate>();
		
		List<Candidate> candidateList = (List<Candidate>)(Object)
				commonService.getObjectListByAnyColumn("Candidate", "status", JOINED);
		
		Date now = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		Date laterDate = cal.getTime();
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String reminderDate = df.format(laterDate);
		
		for (Candidate candidate : candidateList) {
			if(candidate.getEjmsJoiningDate().toString().equals(reminderDate.toString())) {
				remCandidateList.add(candidate);
			}
		}
		
		for (Candidate candidate : remCandidateList) {
			//ques sent
			candidate.setModifiedBy(SYSTEM_USER);
			candidate.setModifiedDate(new Date());
			candidate.setStatus(QUES_SET_ONE_SEND);
			commonService.saveOrUpdateModelObjectToDB(candidate);
			
			//send email
			SendEmail se = new SendEmail();	
			try {
				if(candidate.getCompany().getCompanyKeyword().equals(DCIMCH)) {	
					se.candidate1stQuestSentMail(mailSender, candidate, commonEmailAddressDCIMCH);
				}else {
					se.candidate1stQuestSentMail(mailSender, candidate, commonEmailAddress);
				}
				
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			//send sms
			String mobileNo = candidate.getCandMobileNo();			
			String formatedMobileNo = "";			
			if(mobileNo != null && !mobileNo.equals(""))  {
				formatedMobileNo = getFormatedMobileNo(mobileNo);
			}			
			if(!formatedMobileNo.equals("")){
				SendSms sendSms = new SendSms();
				sendSms.sendSmsTo(mobileNo, "Dear Mr./Ms. " + candidate.getCandName() + ",\nYour First Feedback Quest has been sent to Synergy EJMS. Please Check Your Email for Login Info. Thank You.");
			}
		}
		
	}
	
	// 2nd question set sending to candidate 7 days after from join date
	public void secondQuesSetSending() {
		
		//String url= "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/login";
		List<Candidate> remCandidateList = new ArrayList<Candidate>();
		List<Candidate> candidateList = (List<Candidate>)(Object)
				commonService.getObjectListByAnyColumn("Candidate", "status", QUES_SET_ONE_ANS_SUBMIT);
		
		Date now = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		cal.add(Calendar.DAY_OF_MONTH, -7);
		Date laterDate = cal.getTime();
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String reminderDate = df.format(laterDate);
		
		for (Candidate candidate : candidateList) {
			if(candidate.getEjmsJoiningDate().toString().equals(reminderDate.toString())) {
				remCandidateList.add(candidate);
			}
		}
		
		for (Candidate candidate : remCandidateList) {
			//ques sent
			candidate.setModifiedBy(SYSTEM_USER);
			candidate.setModifiedDate(new Date());
			candidate.setStatus(QUES_SET_TWO_SEND);
			commonService.saveOrUpdateModelObjectToDB(candidate);
			
			//send email
			SendEmail se = new SendEmail();	
			try {
				if(candidate.getCompany().getCompanyKeyword().equals(DCIMCH)) {	
					se.candidate2ndQuestSentMail(mailSender, candidate, commonEmailAddressDCIMCH);
				} else {
					se.candidate2ndQuestSentMail(mailSender, candidate, commonEmailAddress);
				}
				
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			//send sms
			String mobileNo = candidate.getCandMobileNo();			
			String formatedMobileNo = "";			
			if(mobileNo != null && !mobileNo.equals(""))  {
				formatedMobileNo = getFormatedMobileNo(mobileNo);
			}			
			if(!formatedMobileNo.equals("")){
				SendSms sendSms = new SendSms();
				sendSms.sendSmsTo(mobileNo, "Dear Mr./Ms. " + candidate.getCandName() + ",\nYour 2nd Feedback Quest has been sent to Synergy EJMS. Please Check Your Email for Login Info. Thank You.");
			}
		}
		
	}
	
	// 3rd question set sending to candidate 30 days after from join date
	public void thirdQuesSetSending() {
		
		//String url= "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/login";
		
		List<Candidate> remCandidateList = new ArrayList<Candidate>();
		List<Candidate> candidateList = (List<Candidate>)(Object)
				commonService.getObjectListByAnyColumn("Candidate", "status", QUES_SET_TWO_ANS_SUBMIT);
		
		Date now = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		cal.add(Calendar.DAY_OF_MONTH, -30);
		Date laterDate = cal.getTime();
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String reminderDate = df.format(laterDate);
		
		for (Candidate candidate : candidateList) {
			if(candidate.getEjmsJoiningDate().toString().equals(reminderDate.toString())) {
				remCandidateList.add(candidate);
			}
		}
		
		for (Candidate candidate : remCandidateList) {
			//ques sent
			candidate.setModifiedBy(SYSTEM_USER);
			candidate.setModifiedDate(new Date());
			candidate.setStatus(QUES_SET_THREE_SEND);
			commonService.saveOrUpdateModelObjectToDB(candidate);
			
			//send email
			SendEmail se = new SendEmail();	
			try {
				if(candidate.getCompany().getCompanyKeyword().equals(DCIMCH)) {	
					se.candidate3rdQuestSentMail(mailSender, candidate, commonEmailAddressDCIMCH);
				} else {
					se.candidate3rdQuestSentMail(mailSender, candidate, commonEmailAddress);
				}
				
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			//send sms
			String mobileNo = candidate.getCandMobileNo();			
			String formatedMobileNo = "";			
			if(mobileNo != null && !mobileNo.equals(""))  {
				formatedMobileNo = getFormatedMobileNo(mobileNo);
			}			
			if(!formatedMobileNo.equals("")){
				SendSms sendSms = new SendSms();
				sendSms.sendSmsTo(mobileNo, "Dear Mr./Ms. " + candidate.getCandName() + ",\nYour Final Feedback Quest has been sent to Synergy EJMS. Please Check Your Email for Login Info. Thank You.");
			}
		}
	}
	
	private String getFormatedMobileNo(String mobile){
		String mobileNo = "";
		String [] mobileNos = mobile.split(",");
		mobileNo = mobileNos[0];
		
		if(mobileNo.length() == 10){
			return "+880" + mobileNo;
		} else if(mobileNo.length() == 11){
			return "+88" + mobileNo;
		}else if(mobileNo.length() == 13){
			return "+" + mobileNo;
		} else if(mobileNo.length() == 14){
			return mobileNo;
		} else {
			return mobileNo = "";
		}
	}

}
