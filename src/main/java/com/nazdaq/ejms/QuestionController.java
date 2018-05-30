package com.nazdaq.ejms;

import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.jfree.data.statistics.SimpleHistogramDataset;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nazdaq.ejms.beans.UserBean;
import com.nazdaq.ejms.model.Candidate;
import com.nazdaq.ejms.model.CandidateProfile;
import com.nazdaq.ejms.model.Company;
import com.nazdaq.ejms.model.HREmployee;
import com.nazdaq.ejms.model.QuestionSetOne;
import com.nazdaq.ejms.model.QuestionSetThree;
import com.nazdaq.ejms.model.QuestionSetTwo;
import com.nazdaq.ejms.model.User;
import com.nazdaq.ejms.model.UserRole;
import com.nazdaq.ejms.service.CommonService;
import com.nazdaq.ejms.util.Constants;
import com.nazdaq.ejms.util.OTPGenerator;
import com.nazdaq.ejms.util.SendEmail;
import com.nazdaq.ejms.util.SendSms;

import freemarker.template.SimpleDate;

@Controller
@PropertySource("classpath:common.properties")
public class QuestionController implements Constants {

	@Autowired
	private CommonService commonService;

	@Autowired
	private JavaMailSender mailSender;

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

	@RequestMapping(value = "/questionSetOneForm", method = RequestMethod.GET)
	public ModelAndView getNewEmployeeForm(@ModelAttribute("command") QuestionSetOne questionSetOne,
			BindingResult result, Principal principal) {

		if (principal == null) {
			return new ModelAndView("redirect:/login");
		}
		
		
		Candidate candidate = (Candidate) commonService.getAnObjectByAnyUniqueColumn("Candidate", "candId", principal.getName().toString());
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("candidate", candidate);
		
		if(candidate.getStatus().equals(QUES_SET_ONE_SEND)) {
			return new ModelAndView("questionSetOneForm", model);
		} else {
			return new ModelAndView("redirect:/index");
		}
	}

	@RequestMapping(value = "/questionSetTwoForm", method = RequestMethod.GET)
	public ModelAndView questionSetTwoForm(@ModelAttribute("command") QuestionSetTwo questionSetTwo,
			BindingResult result, Principal principal) {

		if (principal == null) {
			return new ModelAndView("redirect:/login");
		}

		Candidate candidate = (Candidate) commonService.getAnObjectByAnyUniqueColumn("Candidate", "candId", principal.getName().toString());
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("candidate", candidate);
		
		if(candidate.getStatus().equals(QUES_SET_TWO_SEND)) {
			return new ModelAndView("questionSetTwoForm", model);
		} else {
			return new ModelAndView("redirect:/index");
		}
		
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/questionSetOneAnsList", method = RequestMethod.GET)
	public ModelAndView questionSetOneAnsList(Principal principal, ModelMap model) {

		if (principal == null) {
			return new ModelAndView("redirect:/login");
		}
		List<QuestionSetOne> questionSetOneList = (List<QuestionSetOne>) (Object) commonService
				.getAllObjectList("QuestionSetOne");
		model.put("questionSetOneList", questionSetOneList);
		return new ModelAndView("questionSetOneAnsList", model);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/questionSetTwoAnsList", method = RequestMethod.GET)
	public ModelAndView questionSetTwoAnsList(Principal principal, ModelMap model) {

		if (principal == null) {
			return new ModelAndView("redirect:/login");
		}
		List<QuestionSetTwo> questionSetOneList = (List<QuestionSetTwo>) (Object) commonService
				.getAllObjectList("QuestionSetTwo");
		model.put("questionSetOneList", questionSetOneList);
		return new ModelAndView("questionSetTwoAnsList", model);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/questionSetThreeAnsList", method = RequestMethod.GET)
	public ModelAndView questionSetThreeAnsList(Principal principal, ModelMap model) {

		if (principal == null) {
			return new ModelAndView("redirect:/login");
		}
		List<QuestionSetThree> questionSetOneList = (List<QuestionSetThree>) (Object) commonService
				.getAllObjectList("QuestionSetThree");
		model.put("questionSetOneList", questionSetOneList);
		return new ModelAndView("questionSetThreeAnsList", model);
	}

	@RequestMapping(value = "/questionSetThreeForm", method = RequestMethod.GET)
	public ModelAndView questionSetThreeForm(@ModelAttribute("command") QuestionSetThree questionSetThree,
			BindingResult result, Principal principal) {

		if (principal == null) {
			return new ModelAndView("redirect:/login");
		}
		
		Candidate candidate = (Candidate) commonService.getAnObjectByAnyUniqueColumn("Candidate", "candId", principal.getName().toString());
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("candidate", candidate);
		
		if(candidate.getStatus().equals(QUES_SET_THREE_SEND)) {
			return new ModelAndView("questionSetThreeForm", model);
		} else {
			return new ModelAndView("redirect:/index");
		}
	}

	@ResponseBody
	@RequestMapping(value = "/saveQuestionSet", method = RequestMethod.POST)
	public ModelAndView saveQuestionSet(@ModelAttribute("command") QuestionSetOne questionSetOne, BindingResult result,
			Principal principal, HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws ParseException {

		if (principal == null) {
			return new ModelAndView("redirect:/login");
		}
		
		String url= "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/login";
		
		String userid = (String) session.getAttribute("userrId");
		//questionSetOne.setNpsResult(request.getParameter("radQ3").trim().toString());
		questionSetOne.setNpsResult(null);
		Candidate candidate = (Candidate) commonService.getAnObjectByAnyUniqueColumn("Candidate", "candId", userid);
		
		QuestionSetOne qs1 = (QuestionSetOne) commonService.getAnObjectByAnyUniqueColumn("QuestionSetOne", "candidate.id", candidate.getId().toString());
		
		if(candidate.getStatus().equals(QUES_SET_ONE_SEND) && qs1 == null) {
			questionSetOne.setCandidate(candidate);
			Date now = new Date();
			questionSetOne.setCreatedDate(now);
			questionSetOne.setCreatedBy(principal.getName());
			questionSetOne.setActive(true);
			commonService.saveOrUpdateModelObjectToDB(questionSetOne);
			
			SendEmail se = new SendEmail();	
			//feedback mail to hr
			try {
				if(candidate.getCompany().getCompanyKeyword().equals(DCIMCH)) {
					se.candidate1stFeedBackMail(mailSender, candidate, url, commonEmailAddressDCIMCH);
				} else {
					se.candidate1stFeedBackMail(mailSender, candidate, url, commonEmailAddress);
				}
				
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			
			candidate.setModifiedBy(principal.getName());
			candidate.setModifiedDate(new Date());
			candidate.setStatus(QUES_SET_ONE_ANS_SUBMIT);
			commonService.saveOrUpdateModelObjectToDB(candidate);	
		}
		
		return new ModelAndView("redirect:/index");
	}

	@ResponseBody
	@RequestMapping(value = "/saveQuestionSet2", method = RequestMethod.POST)
	public ModelAndView saveQuestionSet2(@ModelAttribute("command") QuestionSetTwo questionSetTwo, BindingResult result,
			Principal principal, HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws ParseException {

		if (principal == null) {
			return new ModelAndView("redirect:/login");
		}
		
		String url= "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/login";
		
		String userid = (String) session.getAttribute("userrId");
		//questionSetOne.setNpsResult(request.getParameter("radQ3").trim().toString());
		questionSetTwo.setNpsResult(null);
		Candidate candidate = (Candidate) commonService.getAnObjectByAnyUniqueColumn("Candidate", "candId", userid);
		
		QuestionSetTwo qs2 = (QuestionSetTwo) commonService.getAnObjectByAnyUniqueColumn("QuestionSetTwo", "candidate.id", candidate.getId().toString());
		
		if(candidate.getStatus().equals(QUES_SET_TWO_SEND) && qs2 == null) {
			questionSetTwo.setCandidate(candidate);
			Date now = new Date();
			questionSetTwo.setCreatedDate(now);
			questionSetTwo.setCreatedBy(principal.getName());
			questionSetTwo.setActive(true);
			commonService.saveOrUpdateModelObjectToDB(questionSetTwo);
			
			SendEmail se = new SendEmail();	
			//feedback mail to hr
			try {
				if(candidate.getCompany().getCompanyKeyword().equals(DCIMCH)) {
					se.candidate2ndFeedBackMail(mailSender, candidate, url, commonEmailAddressDCIMCH);
				} else {
					se.candidate2ndFeedBackMail(mailSender, candidate, url, commonEmailAddress);
				}
				
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			
			candidate.setModifiedBy(principal.getName());
			candidate.setModifiedDate(new Date());
			candidate.setStatus(QUES_SET_TWO_ANS_SUBMIT);
			commonService.saveOrUpdateModelObjectToDB(candidate);
		}
		
		return new ModelAndView("redirect:/index");
	}

	@ResponseBody
	@RequestMapping(value = "/saveQuestionSet3", method = RequestMethod.POST)
	public ModelAndView saveQuestionSet3(@ModelAttribute("command") QuestionSetThree questionSetThree,
			BindingResult result, Principal principal, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws ParseException {

		if (principal == null) {
			return new ModelAndView("redirect:/login");
		}
		
		String url= "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/login";
		
		String userid = (String) session.getAttribute("userrId");
		questionSetThree.setNpsResult(request.getParameter("radQ3").trim().toString());
		
		Candidate candidate = (Candidate) commonService.getAnObjectByAnyUniqueColumn("Candidate", "candId", userid);
		
		QuestionSetThree qs3 = (QuestionSetThree) commonService.getAnObjectByAnyUniqueColumn("QuestionSetThree", "candidate.id", candidate.getId().toString());
		
		if(candidate.getStatus().equals(QUES_SET_THREE_SEND) && qs3 == null) {
			questionSetThree.setCandidate(candidate);
			Date now = new Date();
			questionSetThree.setCreatedDate(now);
			questionSetThree.setActive(true);
			questionSetThree.setCreatedBy(principal.getName());
			commonService.saveOrUpdateModelObjectToDB(questionSetThree);
			
			SendEmail se = new SendEmail();	
			//feedback mail to hr
			try {
				if(candidate.getCompany().getCompanyKeyword().equals(DCIMCH)) {
					se.candidate3rdFeedBackMail(mailSender, candidate, url, commonEmailAddressDCIMCH);
				} else {
					se.candidate3rdFeedBackMail(mailSender, candidate, url, commonEmailAddress);
				}
				
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			
			candidate.setModifiedBy(principal.getName());
			candidate.setModifiedDate(new Date());
			candidate.setStatus(QUES_SET_THREE_ANS_SUBMIT);
			commonService.saveOrUpdateModelObjectToDB(candidate);
		}
		
		return new ModelAndView("redirect:/index");
	}
	
	//answer1
	@RequestMapping(value = "/answer1", method = RequestMethod.GET)
	public ModelAndView answer1(Candidate candidate, Principal principal, ModelMap modelMap) {

		if (principal == null) {
			return new ModelAndView("redirect:/login");
		}
		
		Candidate candidatedb = (Candidate) commonService.getAnObjectByAnyUniqueColumn("Candidate", "id", candidate.getId().toString());
		
		CandidateProfile cp = (CandidateProfile) commonService.getAnObjectByAnyUniqueColumn("CandidateProfile", "candidate.id", candidate.getId().toString());
		
		QuestionSetOne candidateAnswer = (QuestionSetOne) commonService.getAnObjectByAnyUniqueColumn("QuestionSetOne", "candidate.id", candidate.getId().toString());
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("candidateAnswer", candidateAnswer);
		model.put("titleHead", "1st day\'s Questionnaires Answer");
		model.put("candidate", candidatedb);
		model.put("candidateProfile", cp);
		return new ModelAndView("candidateAnswer1", model);
	}
	
	//answer2	
	@RequestMapping(value = "/answer2", method = RequestMethod.GET)
	public ModelAndView answer2(Candidate candidate, Principal principal) {

		if (principal == null) {
			return new ModelAndView("redirect:/login");
		}
		
		Candidate candidatedb = (Candidate) commonService.getAnObjectByAnyUniqueColumn("Candidate", "id", candidate.getId().toString());
		
		CandidateProfile cp = (CandidateProfile) commonService.getAnObjectByAnyUniqueColumn("CandidateProfile", "candidate.id", candidate.getId().toString());
		
		QuestionSetTwo candidateAnswer = (QuestionSetTwo) commonService.getAnObjectByAnyUniqueColumn("QuestionSetTwo", "candidate.id", candidate.getId().toString());
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("candidateAnswer", candidateAnswer);
		model.put("titleHead", "7th Day\'s Questionnaires Answer");
		model.put("candidate", candidatedb);
		model.put("candidateProfile", cp);
		return new ModelAndView("candidateAnswer2", model);
	}
	
	//answer3
	@RequestMapping(value = "/answer3", method = RequestMethod.GET)
	public ModelAndView answer3(Candidate candidate, Principal principal) {

		if (principal == null) {
			return new ModelAndView("redirect:/login");
		}
		
		Candidate candidatedb = (Candidate) commonService.getAnObjectByAnyUniqueColumn("Candidate", "id", candidate.getId().toString());
		
		CandidateProfile cp = (CandidateProfile) commonService.getAnObjectByAnyUniqueColumn("CandidateProfile", "candidate.id", candidate.getId().toString());
		
		QuestionSetThree candidateAnswer = (QuestionSetThree) commonService.getAnObjectByAnyUniqueColumn("QuestionSetThree", "candidate.id", candidate.getId().toString());
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("candidateAnswer", candidateAnswer);
		model.put("titleHead", "30th day\'s Questionnaires Answer");
		model.put("candidate", candidatedb);
		model.put("candidateProfile", cp);
		return new ModelAndView("candidateAnswer3", model);
	}

	private DateTime getCurrentdatetime() throws ParseException {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		DateTime date = DateTime.parse(dtf.format(now), DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));

		return date;
	}

}
