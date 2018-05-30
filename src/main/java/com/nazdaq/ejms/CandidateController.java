package com.nazdaq.ejms;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nazdaq.ejms.beans.UserBean;
import com.nazdaq.ejms.model.Candidate;
import com.nazdaq.ejms.model.CandidateProfile;
import com.nazdaq.ejms.model.Company;
import com.nazdaq.ejms.model.Department;
import com.nazdaq.ejms.model.Designation;
import com.nazdaq.ejms.model.HREmployee;
import com.nazdaq.ejms.model.User;
import com.nazdaq.ejms.model.UserRole;
import com.nazdaq.ejms.service.CommonService;
import com.nazdaq.ejms.util.Constants;
import com.nazdaq.ejms.util.OTPGenerator;
import com.nazdaq.ejms.util.SendEmail;
import com.nazdaq.ejms.util.SendSms;

@Controller
@PropertySource("classpath:common.properties")
public class CandidateController implements Constants{
	
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
	
	@Value("${new.employee.join.synergy.to}")
	String joinSynergyTo;
	
	@Value("${new.employee.join.synergy.cc}")
	String joinSynergyCC;
	
	@Value("${new.employee.join.dcimch.to}")
	String joinDcimchTo;
	
	@Value("${new.employee.join.dcimch.cc}")
	String joinDcimchCC;
	
	//@Value("${intriduce.employee.synergy.to}")
	//String intriduceSynergyTo;
	
	@Value("${intriduce.employee.synergy.cc}")
	String intriduceSynergyCC;
	
	//@Value("${intriduce.employee.dcimch.to}")
	//String intriduceDcimchTo;
	
	@Value("${intriduce.employee.dcimch.cc}")
	String intriduceDcimchCC;
	
	
	@Value("${intriduce.employee.lexicon.cc}")
	String intriduceLexiconCC;
	
	@Value("${intriduce.employee.nazdaq.cc}")
	String intriduceNazdaqCC;
	
	@Value("${intriduce.employee.nel.cc}")
	String intriduceNelCC;
	
	@Value("${intriduce.employee.meditech.cc}")
	String intriduceMeditechCC;
	
	@Value("${intriduce.employee.ardent.cc}")
	String intriduceArdentCC;
	
	@Value("${intriduce.employee.creative.cc}")
	String intriduceCreativeCC;
	
	@Value("${intriduce.employee.kumh.cc}")
	String intriduceKumhCC;
	
	@Value("${intriduce.employee.nppl.cc}")
	String intriduceNpplCC;
	
	@Value("${intriduce.employee.apple.cc}")
	String intriduceAppleCC;
	
	
	java.util.Date date = new java.util.Date();
	Timestamp curTime = new Timestamp(date.getTime());
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/editEmployee", method = RequestMethod.GET)
	public ModelAndView editEmployee (@ModelAttribute("candidateForm") Candidate candidate,
			BindingResult result, Principal principal,HttpServletRequest request){
		
		if(principal == null) {
			return new ModelAndView("redirect:/login");
		}
		
		List<HREmployee> employeeList = (List<HREmployee>)(Object)
				commonService.getObjectListByAnyColumn("HREmployee", "active", "1");
		
		List<Company> companyList = (List<Company>)(Object)
				commonService.getObjectListByAnyColumn("Company", "active", "1");
		
		List<Department> departmentList=(List<Department>) (Object)commonService.getAllObjectList("Department");
		List<Designation> designitionList=(List<Designation>) (Object)commonService.getAllObjectList("Designation");
		candidate=(Candidate)commonService.getAnObjectByAnyUniqueColumn("Candidate", "id", request.getParameter("id").trim().toString());
		
		String oDoj = candidate.getOldDateOfJoin();
		String doj = candidate.getDateOfJoin();
		Integer oDoj1 = Integer.parseInt(oDoj.replaceAll("-", ""));
		Integer doj1 = Integer.parseInt(doj.replaceAll("-", ""));
		
		boolean allowExtedLetter =  false;
		
		if(candidate.getUploadExtendLetter().equals("0")) {
			if(doj1 > oDoj1) {
				 allowExtedLetter =  true;
			}
		}
		
		
		Map <String, Object> model = new HashMap<String, Object>();		
		model.put("candidate", candidate);
		model.put("edit", true);
		model.put("allowExtedLetter", allowExtedLetter);
		model.put("employeeList", employeeList);
		model.put("companyList", companyList);
		model.put("designitionList", designitionList);
		model.put("departmentList", departmentList);
		return new ModelAndView("candidateForm", model);
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/candidateForm", method = RequestMethod.GET)
	public ModelAndView getNewEmployeeForm (@ModelAttribute("candidateForm") Candidate candidate, 
			BindingResult result, Principal principal, HttpServletRequest request){
		
		if(principal == null) {
			return new ModelAndView("redirect:/login");
		}
		
		Map <String, Object> model = new HashMap<String, Object>();
		
		List<HREmployee> employeeList = (List<HREmployee>)(Object)
				commonService.getObjectListByAnyColumn("HREmployee", "active", "1");		
		
		List<Company> companyList = (List<Company>)(Object)
				commonService.getObjectListByAnyColumn("Company", "active", "1");
		
		List<Department> departmentList=(List<Department>) (Object)commonService.getAllObjectList("Department");
		List<Designation> designitionList=(List<Designation>) (Object)commonService.getAllObjectList("Designation");
		
		if(request.isUserInRole(ROLE_ADMIN)){
			model.put("employeeList", employeeList);
			model.put("companyList", companyList);
			model.put("designitionList", designitionList);			
		}
		
		if(request.isUserInRole(ROLE_ADMIN_SYNERGY)){
			List<HREmployee> employeeListWD = new ArrayList<HREmployee>();
			List<Company> companyListWD = new ArrayList<Company>();
			List<Designation> designitionListWD = new ArrayList<Designation>();		
			for (HREmployee hrEmployee : employeeList) {
				if(!hrEmployee.getRemarks().equals(DCIMCH)) {
					employeeListWD.add(hrEmployee);
				}
			}
			for (Company company : companyList) {
				if(!company.getCompanyKeyword().equals(DCIMCH)) {
					companyListWD.add(company);
				}
			}
			for ( Designation designation: designitionList) {
				if(!designation.getRemarks().equals(DCIMCH)) {
					designitionListWD.add(designation);
				}
			}			
			model.put("employeeList", employeeListWD);
			model.put("companyList", companyListWD);
			model.put("designitionList", designitionListWD);
		}
		
		if(request.isUserInRole(ROLE_ADMIN_DCIMCH)){
			List<HREmployee> employeeListD = new ArrayList<HREmployee>();
			List<Company> companyListD = new ArrayList<Company>();
			List<Designation> designitionListD = new ArrayList<Designation>();		
			for (HREmployee hrEmployee : employeeList) {
				if(hrEmployee.getRemarks().equals(DCIMCH)) {
					employeeListD.add(hrEmployee);
				}
			}
			for (Company company : companyList) {
				if(company.getCompanyKeyword().equals(DCIMCH)) {
					companyListD.add(company);
				}
			}
			for ( Designation designation: designitionList) {
				if(designation.getRemarks().equals(DCIMCH)) {
					designitionListD.add(designation);
				}
			}			
			model.put("employeeList", employeeListD);
			model.put("companyList", companyListD);
			model.put("designitionList", designitionListD);
		}
		
		model.put("departmentList", departmentList);		
		model.put("candidate", null);
		model.put("edit", false);
		model.put("allowExtedLetter", false);		
		return new ModelAndView("candidateForm", model);
	}
	
	@RequestMapping(value = "/saveCandidateByCandidate", method = RequestMethod.POST)
	public ModelAndView saveCandidateByCandidate (@ModelAttribute("candidateProfileForm") Candidate candidate, 
			BindingResult result, Principal principal, HttpServletRequest request, 
			@RequestParam(value="resignLetter", required=false) MultipartFile resignLetter, 
			@RequestParam(value="othersDoc", required=false) MultipartFile othersDoc) throws Exception{
		
		if(principal == null) {
			return new ModelAndView("redirect:/login");
		}
		
		if(candidate.getId() != null) {
			Candidate candidateDb = (Candidate) commonService.getAnObjectByAnyUniqueColumn("Candidate", "id", candidate.getId().toString());
			boolean flag = false;
			if(candidateDb.getId() != null) {
				if (! resignLetter.isEmpty()) {
					processarAvatarResignLetter(candidateDb, resignLetter);
					candidateDb.setUploadResignLetter("1");
					flag = true;
				}
				
				if (! othersDoc.isEmpty()) {
					processarAvatarOthersDoc(candidateDb, othersDoc);
					candidateDb.setUploadOthersLetter("1");
					flag = true;
				}
				
				if(flag) {
					commonService.saveOrUpdateModelObjectToDB(candidateDb);
				}
			}
		}
		
		return new ModelAndView("redirect:/index");
	}
	
	@RequestMapping(value = "/saveCandidate", method = RequestMethod.POST)
	public ModelAndView saveOrUpdateCandidate (@ModelAttribute("candidateForm") Candidate candidate, 
			BindingResult result, Principal principal, HttpServletRequest request, 
			@RequestParam(value="photo", required=false) MultipartFile photo, 
			@RequestParam(value="appLetter", required=false) MultipartFile appLetter, 
			@RequestParam(value="extentionLetter", required=false) MultipartFile extentionLetter) throws Exception{
		
		if(principal == null) {
			return new ModelAndView("redirect:/login");
		}
		
		
		
		Company company = (Company) commonService.getAnObjectByAnyUniqueColumn("Company", "id", candidate.getCompanyId().toString());
		
		HREmployee hrEmployee = (HREmployee) commonService.getAnObjectByAnyUniqueColumn("HREmployee", "id", candidate.getRecuriteById().toString());
		Designation designation = (Designation) commonService.getAnObjectByAnyUniqueColumn("Designation", "id", candidate.getDesignationId().toString());
		Department department = (Department) commonService.getAnObjectByAnyUniqueColumn("Department", "id", candidate.getDepartmentId().toString());
		Map <String, Object> model = new HashMap<String, Object>();
		candidate.setDepartment(department);
		candidate.setDesignation(designation);
		if(candidate.getId() != null) {
			Candidate candidateDb = (Candidate) commonService.getAnObjectByAnyUniqueColumn("Candidate", "id", candidate.getId().toString());
			
			if (! photo.isEmpty()) {
				processarAvatar(candidateDb, photo);
			}
			
			if (! appLetter.isEmpty()) {
				processarAvatarAppLetter(candidateDb, appLetter);
				candidateDb.setUploadAppLetter("1");
			}
			
			
			if (! extentionLetter.isEmpty()) {
				processarAvatarExtendLetter(candidateDb, extentionLetter);
				candidateDb.setUploadExtendLetter("1");
			}
			
			//recommand by tasnim start
			candidateDb.setCandName(candidate.getCandName());
			candidateDb.setCandEmail(candidate.getCandEmail());
			CandidateProfile candProfile = (CandidateProfile)commonService.getAnObjectByAnyUniqueColumn("CandidateProfile", "candidate.id", candidateDb.getId().toString());
			candProfile.setName(candidate.getCandName());
			candProfile.setCandMobileNo(candidate.getCandMobileNo());
			candProfile.setEmailAddress(candidate.getCandEmail());
			candProfile.setModifiedBy(principal.getName());
			candProfile.setModifiedDate(new Date());
			commonService.saveOrUpdateModelObjectToDB(candProfile);
			//recommand by tasnim end
			
			candidateDb.setModifiedBy(principal.getName());
			candidateDb.setModifiedDate(new Date());
			//candidateDb.setCompany(company); 
			candidateDb.setHrEmployee(hrEmployee);
			candidateDb.setDepartment(department);
			candidateDb.setDesignation(designation);
			candidateDb.setCandMobileNo(candidate.getCandMobileNo());
			candidateDb.setRemarks(candidate.getRemarks());
			candidateDb.setSourceOfCand(candidate.getSourceOfCand());
			candidateDb.setDateOfJoin(candidate.getDateOfJoin());
			commonService.saveOrUpdateModelObjectToDB(candidateDb);
		} else {
			//Integer lastCandId = (Integer)commonService.getMaxValueByObjectAndColumn("Candidate", "id");
			Integer lastCandId = (Integer)commonService.getMaxValueByObjectAndTwoColumn("Candidate", "id", "company.id", company.getId().toString());
			
			Candidate lastCandidate = null;
			if(lastCandId != null) {
				lastCandidate =  (Candidate) commonService.getAnObjectByAnyUniqueColumn("Candidate", "id", lastCandId.toString());
			}
			
			if (! photo.isEmpty()) {
				processarAvatar(candidate, photo);
			}
			
			if (! appLetter.isEmpty()) {
				processarAvatarAppLetter(candidate, appLetter);
				candidate.setUploadAppLetter("1");
			}
			
			candidate.setCompany(company);
			candidate.setHrEmployee(hrEmployee);
			candidate.setCreatedBy(principal.getName());
			candidate.setCreatedDate(new Date());
			candidate.setStatus(SAVED);
			candidate.setActive(true);
			candidate.setOldDateOfJoin(candidate.getDateOfJoin());
			String refNo = this.getReferenceNo(lastCandidate);
			candidate.setRefNo(refNo);
			//recommnand by sandip das (candidate Id should be automatic)
			candidate.setCandId(company.getCompanyKeyword()+""+refNo);
			
			commonService.saveOrUpdateModelObjectToDB(candidate);	
			
			
		}
		
		return new ModelAndView("redirect:/pendCandidateList", model);
	}
	
	private void processarAvatar(Candidate candidate, MultipartFile photo) {
		File diretorio = new File("/ejms/upload/photo");
		if (! diretorio.exists()) {
			diretorio.mkdirs();
		}
		try {
			FileOutputStream arquivo = new FileOutputStream(diretorio.getAbsolutePath() + "/" + candidate.getCandId() + ".jpg");
			arquivo.write(photo.getBytes());
			arquivo.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}		
	}
	
	private void processarAvatarAppLetter(Candidate candidate, MultipartFile appLetter) {
		File diretorio = new File("/ejms/upload/appLetter");
		if (! diretorio.exists()) {
			diretorio.mkdirs();
		}
		try {
			FileOutputStream arquivo = new FileOutputStream(diretorio.getAbsolutePath() + "/" + candidate.getCandId() + ".pdf");
			arquivo.write(appLetter.getBytes());
			arquivo.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}		
	}
	
	
	private void processarAvatarExtendLetter(Candidate candidate, MultipartFile extendLetter) {
		File diretorio = new File("/ejms/upload/extendLetter");
		if (! diretorio.exists()) {
			diretorio.mkdirs();
		}
		try {
			FileOutputStream arquivo = new FileOutputStream(diretorio.getAbsolutePath() + "/" + candidate.getCandId() + ".pdf");
			arquivo.write(extendLetter.getBytes());
			arquivo.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}		
	}
	
	private void processarAvatarResignLetter(Candidate candidate, MultipartFile resignLetter) {
		File diretorio = new File("/ejms/upload/resignLetter");
		if (! diretorio.exists()) {
			diretorio.mkdirs();
		}
		try {
			FileOutputStream arquivo = new FileOutputStream(diretorio.getAbsolutePath() + "/" + candidate.getCandId() + ".pdf");
			arquivo.write(resignLetter.getBytes());
			arquivo.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}		
	}
	
	private void processarAvatarOthersDoc(Candidate candidate, MultipartFile othersDoc) {
		File diretorio = new File("/ejms/upload/othersDoc");
		if (! diretorio.exists()) {
			diretorio.mkdirs();
		}
		try {
			FileOutputStream arquivo = new FileOutputStream(diretorio.getAbsolutePath() + "/" + candidate.getCandId() + ".pdf");
			arquivo.write(othersDoc.getBytes());
			arquivo.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}		
	}
	
	@RequestMapping("/candidatePhoto/{candId}")
	@ResponseBody
	public byte[] photo(@PathVariable("candId") String candId) throws IOException {
		File arquivo = new File("/ejms/upload/photo/" + candId + ".jpg");
		
		if (! arquivo.exists()) {
			arquivo = new File("/ejms/upload/photo/photo.jpg");
		}
		
		byte[] resultado = new byte[(int)arquivo.length()];
		FileInputStream input = new FileInputStream(arquivo);
		input.read(resultado);
		input.close();
		return resultado;
	}
	
	@RequestMapping("/candidateAppLetter/{candId}")
	@ResponseBody
	public byte[] candidateAppLetter(@PathVariable("candId") String candId) throws IOException {
		File arquivo = new File("/ejms/upload/appLetter/" + candId + ".pdf");
		
		if (! arquivo.exists()) {
			arquivo = new File("/ejms/upload/appLetter/sample.pdf");
		}
		
		byte[] resultado = new byte[(int)arquivo.length()];
		FileInputStream input = new FileInputStream(arquivo);
		input.read(resultado);
		input.close();
		return resultado;
	}
	
	@RequestMapping("/candidateExtendLetter/{candId}")
	@ResponseBody
	public byte[] candidateExtendLetter(@PathVariable("candId") String candId) throws IOException {
		File arquivo = new File("/ejms/upload/extendLetter/" + candId + ".pdf");
		
		if (! arquivo.exists()) {
			arquivo = new File("/ejms/upload/extendLetter/sample.pdf");
		}
		
		byte[] resultado = new byte[(int)arquivo.length()];
		FileInputStream input = new FileInputStream(arquivo);
		input.read(resultado);
		input.close();
		return resultado;
	}
	
	
	//pendCandidateList
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/pendCandidateList", method = RequestMethod.GET)
	public ModelAndView pendCandidateList (Candidate candidate, 
			BindingResult result, Principal principal, HttpServletRequest request) throws Exception{
		
		if(principal == null) {
			return new ModelAndView("redirect:/login");
		}
		
		Map <String, Object> model = new HashMap<String, Object>();
		
		List<String> statusList = new ArrayList<String>();
		statusList.add(SAVED);
		statusList.add(SEND_TO_CANDIDATE);
		statusList.add(SUBMITTED);
		statusList.add(APPROVED_APP_LETTER);
		statusList.add(CANDIDATE_ACCEPTED_AL);
		statusList.add(CANDIDATE_REJECTED_AL);
		List<Candidate> pendCandidateList = (List<Candidate>) (Object)
				commonService.getObjectListByAnyColumnValueList("Candidate", "status", statusList);
		//model.put("pendCandidateList", pendCandidateList);
		if(request.isUserInRole(ROLE_ADMIN)){
			model.put("pendCandidateList", pendCandidateList);
		}
		
		if(request.isUserInRole(ROLE_ADMIN_SYNERGY)){			
			List<Candidate> pendCandidateListWD = new ArrayList<Candidate>();
			for (Candidate cand : pendCandidateList) {
				if(!cand.getCompany().getCompanyKeyword().equals(DCIMCH)) {
					pendCandidateListWD.add(cand);
				}
			}
			
			model.put("pendCandidateList", pendCandidateListWD);
		}
		
		if(request.isUserInRole(ROLE_ADMIN_DCIMCH)){			
			List<Candidate> pendCandidateListD = new ArrayList<Candidate>();
			for (Candidate cand : pendCandidateList) {
				if(cand.getCompany().getCompanyKeyword().equals(DCIMCH)) {
					pendCandidateListD.add(cand);
				}
			}
			
			model.put("pendCandidateList", pendCandidateListD);
		}
		
		return new ModelAndView("candidatePendingList", model);
	}
	
	//adminDashboard
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/adminDashboard", method = RequestMethod.GET)
	public ModelAndView adminDashboard (Candidate candidate, 
			BindingResult result, Principal principal, HttpServletRequest request) throws Exception{
		
		if(principal == null) {
			return new ModelAndView("redirect:/login");
		}
		
		Date now = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String today = df.format(now);
		
		Map <String, Object> model = new HashMap<String, Object>();
		
		//all pending list
		List<String> statusList = new ArrayList<String>();
		statusList.add(SAVED);
		statusList.add(SEND_TO_CANDIDATE);
		statusList.add(SUBMITTED);
		statusList.add(APPROVED_APP_LETTER);
		statusList.add(CANDIDATE_ACCEPTED_AL);
		statusList.add(CANDIDATE_REJECTED_AL);
		List<Candidate> pendCandidateList = (List<Candidate>) (Object)
				commonService.getObjectListByAnyColumnValueList("Candidate", "status", statusList);
			
		
		//todays joined Candidate List
		List<String> statusList2 = new ArrayList<String>();
		statusList2.add(JOINED);	
		List<Candidate> joinedCandidateList = (List<Candidate>) (Object)
				commonService.getObjectListByAnyColumnValueListAndOneColumn("Candidate", "status", statusList2,  "ejmsJoiningDate", today);
		
		
		//todays joined Candidate List			
		List<Candidate> todaysJoiningCandidateList = (List<Candidate>) (Object)
						commonService.getObjectListByAnyColumnValueListAndOneColumn("Candidate", "status", statusList,  "dateOfJoin", today);
		
		
		// all joined candidate list
		List<String> statusList3 = new ArrayList<String>();
		statusList3.add(JOINED);
		statusList3.add(QUES_SET_ONE_SEND);
		statusList3.add(QUES_SET_ONE_ANS_SUBMIT);
		statusList3.add(QUES_SET_TWO_SEND);
		
		statusList3.add(QUES_SET_TWO_ANS_SUBMIT);
		statusList3.add(QUES_SET_THREE_SEND);
		statusList3.add(QUES_SET_THREE_ANS_SUBMIT);
		
		List<Candidate> allJoinedCandidateList = (List<Candidate>) (Object)
				commonService.getObjectListByAnyColumnValueList("Candidate", "status", statusList3);
		
		
		//return
		if(request.isUserInRole(ROLE_ADMIN)){
			model.put("pendCandidateList", pendCandidateList);	
			model.put("joinedCandidateList", joinedCandidateList);
			model.put("todaysJoiningCandidateList", todaysJoiningCandidateList);
			model.put("allJoinedCandidateList", allJoinedCandidateList);
		}
		
		if(request.isUserInRole(ROLE_ADMIN_SYNERGY)){
			List<Candidate> pendCandidateListWD = new ArrayList<Candidate>();
			for (Candidate cand : pendCandidateList) {
				if(!cand.getCompany().getCompanyKeyword().equals(DCIMCH)) {
					pendCandidateListWD.add(cand);
				}
			}
			List<Candidate> joinedCandidateListWD = new ArrayList<Candidate>();
			for (Candidate cand : joinedCandidateList) {
				if(!cand.getCompany().getCompanyKeyword().equals(DCIMCH)) {
					joinedCandidateListWD.add(cand);
				}
			}
			List<Candidate> todaysJoiningCandidateListWD = new ArrayList<Candidate>();
			for (Candidate cand : todaysJoiningCandidateList) {
				if(!cand.getCompany().getCompanyKeyword().equals(DCIMCH)) {
					todaysJoiningCandidateListWD.add(cand);
				}
			}
			List<Candidate> allJoinedCandidateListWD = new ArrayList<Candidate>();
			for (Candidate cand : allJoinedCandidateList) {
				if(!cand.getCompany().getCompanyKeyword().equals(DCIMCH)) {
					allJoinedCandidateListWD.add(cand);
				}
			}
			
			model.put("pendCandidateList", pendCandidateListWD);	
			model.put("joinedCandidateList", joinedCandidateListWD);
			model.put("todaysJoiningCandidateList", todaysJoiningCandidateListWD);
			model.put("allJoinedCandidateList", allJoinedCandidateListWD);
		}
		
		if(request.isUserInRole(ROLE_ADMIN_DCIMCH)){
			List<Candidate> pendCandidateListD = new ArrayList<Candidate>();
			for (Candidate cand : pendCandidateList) {
				if(cand.getCompany().getCompanyKeyword().equals(DCIMCH)) {
					pendCandidateListD.add(cand);
				}
			}
			List<Candidate> joinedCandidateListD = new ArrayList<Candidate>();
			for (Candidate cand : joinedCandidateList) {
				if(cand.getCompany().getCompanyKeyword().equals(DCIMCH)) {
					joinedCandidateListD.add(cand);
				}
			}
			List<Candidate> todaysJoiningCandidateListD = new ArrayList<Candidate>();
			for (Candidate cand : todaysJoiningCandidateList) {
				if(cand.getCompany().getCompanyKeyword().equals(DCIMCH)) {
					todaysJoiningCandidateListD.add(cand);
				}
			}
			List<Candidate> allJoinedCandidateListD = new ArrayList<Candidate>();
			for (Candidate cand : allJoinedCandidateList) {
				if(cand.getCompany().getCompanyKeyword().equals(DCIMCH)) {
					allJoinedCandidateListD.add(cand);
				}
			}
			
			model.put("pendCandidateList", pendCandidateListD);	
			model.put("joinedCandidateList", joinedCandidateListD);
			model.put("todaysJoiningCandidateList", todaysJoiningCandidateListD);
			model.put("allJoinedCandidateList", allJoinedCandidateListD);
		}
		
		return new ModelAndView("adminDashboard", model);
	}
	
	//todaysJoinCandidates
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/todaysJoinCandidates", method = RequestMethod.GET)
	public ModelAndView todaysJoinCandidates (Candidate candidate, 
			BindingResult result, Principal principal, HttpServletRequest request) throws Exception{
		
		if(principal == null) {
			return new ModelAndView("redirect:/login");
		}
		
		Map <String, Object> model = new HashMap<String, Object>();
		Date now = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String today = df.format(now);
		
		List<String> statusList = new ArrayList<String>();
		statusList.add(JOINED);
		
		List<Candidate> joinedCandidateList = (List<Candidate>) (Object)
				commonService.getObjectListByAnyColumnValueListAndOneColumn("Candidate", "status", statusList,  "ejmsJoiningDate", today);
		
		//model.put("joinedCandidateList", joinedCandidateList);
		if(request.isUserInRole(ROLE_ADMIN)){			
			model.put("joinedCandidateList", joinedCandidateList);
		}
		
		if(request.isUserInRole(ROLE_ADMIN_SYNERGY)){			
			List<Candidate> joinedCandidateListWD = new ArrayList<Candidate>();
			for (Candidate cand : joinedCandidateList) {
				if(!cand.getCompany().getCompanyKeyword().equals(DCIMCH)) {
					joinedCandidateListWD.add(cand);
				}
			}			
			model.put("joinedCandidateList", joinedCandidateListWD);
		}
		
		if(request.isUserInRole(ROLE_ADMIN_DCIMCH)){			
			List<Candidate> joinedCandidateListD = new ArrayList<Candidate>();
			for (Candidate cand : joinedCandidateList) {
				if(cand.getCompany().getCompanyKeyword().equals(DCIMCH)) {
					joinedCandidateListD.add(cand);
				}
			}			
			model.put("joinedCandidateList", joinedCandidateListD);
		}
		
		return new ModelAndView("todaysJoinCandList", model);
	}
	
	//joinedCandidateList
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/joinedCandidateList", method = RequestMethod.GET)
	public ModelAndView joinedCandidateList (Candidate candidate, 
			BindingResult result, Principal principal, HttpServletRequest request) throws Exception{
		
		if(principal == null) {
			return new ModelAndView("redirect:/login");
		}
		
		Map <String, Object> model = new HashMap<String, Object>();
		
		List<String> statusList = new ArrayList<String>();
		statusList.add(JOINED);
		statusList.add(QUES_SET_ONE_SEND);
		statusList.add(QUES_SET_ONE_ANS_SUBMIT);
		statusList.add(QUES_SET_TWO_SEND);
		
		statusList.add(QUES_SET_TWO_ANS_SUBMIT);
		statusList.add(QUES_SET_THREE_SEND);
		statusList.add(QUES_SET_THREE_ANS_SUBMIT);
		
		List<Candidate> joinedCandidateList = (List<Candidate>) (Object)
				commonService.getObjectListByAnyColumnValueList("Candidate", "status", statusList);
		
		//model.put("joinedCandidateList", joinedCandidateList);
		if(request.isUserInRole(ROLE_ADMIN)){
			model.put("joinedCandidateList", joinedCandidateList);
		}
		
		if(request.isUserInRole(ROLE_ADMIN_SYNERGY)){			
			List<Candidate> joinedCandidateListWD = new ArrayList<Candidate>();
			for (Candidate cand : joinedCandidateList) {
				if(!cand.getCompany().getCompanyKeyword().equals(DCIMCH)) {
					joinedCandidateListWD.add(cand);
				}
			}
			
			model.put("joinedCandidateList", joinedCandidateListWD);
		}
		
		if(request.isUserInRole(ROLE_ADMIN_DCIMCH)){			
			List<Candidate> joinedCandidateListD = new ArrayList<Candidate>();
			for (Candidate cand : joinedCandidateList) {
				if(cand.getCompany().getCompanyKeyword().equals(DCIMCH)) {
					joinedCandidateListD.add(cand);
				}
			}
			
			model.put("joinedCandidateList", joinedCandidateListD);
		}
		
		return new ModelAndView("joinedCandidateList", model);
	}
		
	//declineCandidateList
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/declineCandidateList", method = RequestMethod.GET)
	public ModelAndView declineCandidateList (Candidate candidate, 
			BindingResult result, Principal principal, HttpServletRequest request) throws Exception{
		
		if(principal == null) {
			return new ModelAndView("redirect:/login");
		}
		
		Map <String, Object> model = new HashMap<String, Object>();
		
		List<String> statusList = new ArrayList<String>();
		statusList.add(DELETED);
		statusList.add(DECLINE);
		
		List<Candidate> declineCandidateList = (List<Candidate>) (Object)
				commonService.getObjectListByAnyColumnValueList("Candidate", "status", statusList);
		
		//model.put("declineCandidateList", declineCandidateList);
		if(request.isUserInRole(ROLE_ADMIN)){
			model.put("declineCandidateList", declineCandidateList);
		}
		
		if(request.isUserInRole(ROLE_ADMIN_SYNERGY)){			
			List<Candidate> declineCandidateListWD = new ArrayList<Candidate>();
			for (Candidate cand : declineCandidateList) {
				if(!cand.getCompany().getCompanyKeyword().equals(DCIMCH)) {
					declineCandidateListWD.add(cand);
				}
			}
			
			model.put("declineCandidateList", declineCandidateListWD);
		}
		
		if(request.isUserInRole(ROLE_ADMIN_DCIMCH)){			
			List<Candidate> declineCandidateListD = new ArrayList<Candidate>();
			for (Candidate cand : declineCandidateList) {
				if(cand.getCompany().getCompanyKeyword().equals(DCIMCH)) {
					declineCandidateListD.add(cand);
				}
			}
			
			model.put("declineCandidateList", declineCandidateListD);
		}
		
		return new ModelAndView("declineCandidateList", model);
	}	
	
	//recruitmentHistory
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/recruitmentHistory", method = RequestMethod.GET)
	public ModelAndView recruitmentHistory (Candidate candidate, 
			BindingResult result, Principal principal, HttpServletRequest request) throws Exception{
		
		if(principal == null) {
			return new ModelAndView("redirect:/login");
		}
		
		Map <String, Object> model = new HashMap<String, Object>();
		
		List<String> statusList = new ArrayList<String>();
		statusList.add(RECRUIT_PROCESS_COMPLETED);
		
		List<Candidate> recruitmentHistory = (List<Candidate>) (Object)
				commonService.getObjectListByAnyColumnValueList("Candidate", "status", statusList);
		
		//model.put("recruitmentHistory", recruitmentHistory);
		if(request.isUserInRole(ROLE_ADMIN)){
			model.put("recruitmentHistory", recruitmentHistory);
		}
		
		if(request.isUserInRole(ROLE_ADMIN_SYNERGY)){			
			List<Candidate> recruitmentHistoryWD = new ArrayList<Candidate>();
			for (Candidate cand : recruitmentHistory) {
				if(!cand.getCompany().getCompanyKeyword().equals(DCIMCH)) {
					recruitmentHistoryWD.add(cand);
				}
			}
			
			model.put("recruitmentHistory", recruitmentHistoryWD);
		}
		
		if(request.isUserInRole(ROLE_ADMIN_DCIMCH)){			
			List<Candidate> recruitmentHistoryD = new ArrayList<Candidate>();
			for (Candidate cand : recruitmentHistory) {
				if(cand.getCompany().getCompanyKeyword().equals(DCIMCH)) {
					recruitmentHistoryD.add(cand);
				}
			}
			
			model.put("recruitmentHistory", recruitmentHistoryD);
		}
		
		return new ModelAndView("recruitmentHistory", model);
	}	
	
	@RequestMapping(value = "/resendEmailToCandidate", method = RequestMethod.GET)
	public ModelAndView resendEmailToCandidate (Candidate candidate, 
			BindingResult result, Principal principal, HttpServletRequest request) throws Exception{
		
		if(principal == null) {
			return new ModelAndView("redirect:/login");
		}
		String url= "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/login";
		
		Map <String, Object> model = new HashMap<String, Object>();
		Candidate candidateDb = (Candidate) commonService.getAnObjectByAnyUniqueColumn("Candidate", "id", candidate.getId().toString());
		User user = (User) commonService.getAnObjectByAnyUniqueColumn("User", "userName", candidateDb.getCandId().toString());
		
		if(null!= user.getEmail()){
			SendEmail se = new SendEmail();	
			try {
				if(candidateDb.getCompany().getCompanyKeyword().equals(DCIMCH)) {
					se.profileFormSubmitEmailToCandidate(mailSender, user, candidateDb, url, ccEmailAddresssDCIMCH, commonEmailAddressDCIMCH);
				} else {
					se.profileFormSubmitEmailToCandidate(mailSender, user, candidateDb, url, ccEmailAddresss, commonEmailAddress);
				}
				
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}
		
		//send SMS to Candidate
		String mobileNo = candidateDb.getCandMobileNo();
		//SendSms sms = new SendSms();
		//
		String formatedMobileNo = "";
		
		if(mobileNo != null && !mobileNo.equals(""))  {
			formatedMobileNo = getFormatedMobileNo(mobileNo);
		}
		
		if(!formatedMobileNo.equals("")){
			SendSms sendSms = new SendSms();
			//sendSms.sendSmsTo(mobileNo, "Dear Mr./Ms. " + candidateDb.getCandName() + ",\nYou are Registerd for Synergy EJMS. Please Check Your Email for Login Info. Thank You.");
			sendSms.sendSmsTo(mobileNo, "Dear Mr./Ms. " + candidateDb.getCandName() + ",\nYou are registered for our Joining Tool in "+candidateDb.getCompany().getName()+". Please check your Email ("+candidateDb.getCandEmail()+") for login info.\nRegards\nHRD");
		}
		
		return new ModelAndView("redirect:/pendCandidateList", model);
	}
	
	@RequestMapping(value = "/sendToCandidate", method = RequestMethod.GET)
	public ModelAndView sendToCandidate (Candidate candidate, 
			BindingResult result, Principal principal, HttpServletRequest request) throws Exception{
		
		if(principal == null) {
			return new ModelAndView("redirect:/login");
		}
		
		Map <String, Object> model = new HashMap<String, Object>();
		
		Candidate candidateDb = (Candidate) commonService.getAnObjectByAnyUniqueColumn("Candidate", "id", candidate.getId().toString());
		
		//Candidate Profile Create
		CandidateProfile cfDb = (CandidateProfile) commonService.getAnObjectByAnyUniqueColumn("CandidateProfile", "candidate.id", candidate.getId().toString());
		if(cfDb == null) {
			CandidateProfile cf = new CandidateProfile();
			cf.setCandidate(candidateDb);
			cf.setName(candidateDb.getCandName());
			cf.setActive(true);
			cf.setCandMobileNo(candidateDb.getCandMobileNo());
			cf.setEmailAddress(candidateDb.getCandEmail());
			if(candidateDb.getDesignation() != null) {
				cf.setGrade(candidateDb.getDesignation().getGrade());
			}
			
			if(candidateDb.getCompany().getCompanyKeyword().equals(DCIMCH)) {
				cf.setGrossSalary(0.0);
				cf.setBasicSalary(40.0);
				cf.setHouseRent(20.0);
				cf.setMedicalIn(10.0);
				cf.setConveyance(10.0);
				cf.setSalaryOthersAmt(20.0);
				cf.setProvidentFund(0.0);
				cf.setSalIncreaseAmt(0.0);
				cf.setYearlyBonus("0");
				cf.setProvisionPeriod("0");
			} else {
				cf.setGrossSalary(0.0);
				cf.setBasicSalary(60.0);
				cf.setHouseRent(25.0);
				cf.setMedicalIn(10.0);
				cf.setConveyance(5.0);
				cf.setSalaryOthersAmt(0.0);
				cf.setProvidentFund(0.0);
				cf.setSalIncreaseAmt(0.0);
				cf.setYearlyBonus("0");
				cf.setProvisionPeriod("0");
			}
			
			commonService.saveOrUpdateModelObjectToDB(cf);
		}
		
		
		//User Create & Send Email to Candidate
		User user = (User) commonService.getAnObjectByAnyUniqueColumn("User", "userName", candidateDb.getCandId().toString());
		OTPGenerator otpg = new OTPGenerator();
		String genPassword = otpg.getGeneratedPassword(6);
		
		if(user == null) {
			UserBean userBean = new UserBean(null, candidateDb.getCandId(), genPassword, candidateDb.getCandEmail(), "1", candidateDb.getCandName(), "", 
					curTime, candidateDb.getCandMobileNo(), ROLE_CANDIDATE);
			userBean.setInsertedBy(principal.getName());
			this.saveUser(userBean, request, candidateDb);
		}
		
		//send SMS to Candidate
		String mobileNo = candidateDb.getCandMobileNo();
		//SendSms sms = new SendSms();
		//
		String formatedMobileNo = "";
		
		if(mobileNo != null && !mobileNo.equals(""))  {
			formatedMobileNo = getFormatedMobileNo(mobileNo);
		}
		
		if(!formatedMobileNo.equals("")){
			SendSms sendSms = new SendSms();
			//sendSms.sendSmsTo(mobileNo, "Dear Mr./Ms. " + candidateDb.getCandName() + ",\nYou are Registerd for Synergy EJMS. Please Check Your Email for Login Info. Thank You.");
			sendSms.sendSmsTo(mobileNo, "Dear Mr./Ms. " + candidateDb.getCandName() + ",\nYou are registered for our Joining Tool in "+candidateDb.getCompany().getName()+". Please check your Email ("+candidateDb.getCandEmail()+") for login info.\nRegards\nHRD");
		}
		//change status
		candidateDb.setStatus(SEND_TO_CANDIDATE);
		candidateDb.setModifiedBy(principal.getName());
		candidateDb.setModifiedDate(new Date());
		commonService.saveOrUpdateModelObjectToDB(candidateDb);	
		
		return new ModelAndView("redirect:/pendCandidateList", model);
	}
	
	@RequestMapping(value = "/recruitmentProcessClose", method = RequestMethod.GET)
	public ModelAndView recruitmentProcessClose (Candidate candidate, Principal principal) throws Exception{
		
		if(principal == null) {
			return new ModelAndView("redirect:/login");
		}
		
		Map <String, Object> model = new HashMap<String, Object>();
		
		Candidate candidateDb = (Candidate) commonService.getAnObjectByAnyUniqueColumn("Candidate", "id", candidate.getId().toString());
		
		//User disbaled
		User user = (User) commonService.getAnObjectByAnyUniqueColumn("User", "userName", candidateDb.getCandId().toString());
		user.setStatus("0");
		user.setUpdateDate(curTime);
		user.setUpdatedBy(principal.getName());
		commonService.saveOrUpdateModelObjectToDB(user);	
		
		//change status for process completed
		candidateDb.setStatus(RECRUIT_PROCESS_COMPLETED);
		candidateDb.setModifiedBy(principal.getName());
		candidateDb.setModifiedDate(new Date());
		commonService.saveOrUpdateModelObjectToDB(candidateDb);	
		
		return new ModelAndView("redirect:/recruitmentHistory", model);
	}
	
	@RequestMapping(value = "/deleteCandidate", method = RequestMethod.GET)
	public ModelAndView deleteCandidate (Candidate candidate, Principal principal, HttpServletRequest request) throws Exception{
		
		if(principal == null) {
			return new ModelAndView("redirect:/login");
		}
		
		Map <String, Object> model = new HashMap<String, Object>();
		
		Candidate candidateDb = (Candidate) commonService.getAnObjectByAnyUniqueColumn("Candidate", "id", request.getParameter("id").toString());		
		
		//change status for delete Candidate
		candidateDb.setStatus(DELETED);
		candidateDb.setModifiedBy(principal.getName());
		candidateDb.setModifiedDate(new Date());
		commonService.saveOrUpdateModelObjectToDB(candidateDb);	
		
		return new ModelAndView("redirect:/declineCandidateList", model);
	}
	
	@RequestMapping(value = "/declineCandidate", method = RequestMethod.GET)
	public ModelAndView declineCandidate (Candidate candidate, Principal principal, HttpServletRequest request) throws Exception{
		
		if(principal == null) {
			return new ModelAndView("redirect:/login");
		}
		
		Map <String, Object> model = new HashMap<String, Object>();
		
		//date
		Date now = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String today = df.format(now);
		
		Candidate candidateDb = (Candidate) commonService.getAnObjectByAnyUniqueColumn("Candidate", "id", candidate.getId().toString());		
		
		//change status for decline candidate
		candidateDb.setStatus(DECLINE);
		candidateDb.setDeclineBy(principal.getName());
		candidateDb.setDeclineReason(candidate.getDeclineReason());
		candidateDb.setDeclineDate(today);
		candidateDb.setModifiedBy(principal.getName());
		candidateDb.setModifiedDate(new Date());
		commonService.saveOrUpdateModelObjectToDB(candidateDb);	
		
		User candUser =  (User) commonService.getAnObjectByAnyUniqueColumn("User", "userName", candidateDb.getCandId().toString());	
		candUser.setStatus("0");
		candUser.setUpdatedBy(principal.getName());
		candUser.setUpdateDate(curTime);
		commonService.saveOrUpdateModelObjectToDB(candUser);	
		
		return new ModelAndView("redirect:/declineCandidateList", model);
	}
	
	@RequestMapping(value = "/alRejectedByCandidate", method = RequestMethod.GET)
	public ModelAndView alRejectedByCandidate (Candidate candidate, Principal principal, HttpServletRequest request) throws Exception{
		
		if(principal == null) {
			return new ModelAndView("redirect:/login");
		}
		
		String url= "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/login";	
		
		Map <String, Object> model = new HashMap<String, Object>();
		
		//date
		Date now = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String today = df.format(now);
		
		Candidate candidateDb = (Candidate) commonService.getAnObjectByAnyUniqueColumn("Candidate", "id", candidate.getId().toString());		
		//change status for decline candidate
		candidateDb.setStatus(CANDIDATE_REJECTED_AL);
		candidateDb.setRejectedByCandidateDate(now);
		candidateDb.setRejectReasonByCandidate(candidate.getRejectReasonByCandidate());
		candidateDb.setModifiedBy(principal.getName());
		candidateDb.setModifiedDate(new Date());
				
		//email to HRD
		SendEmail se = new SendEmail();	
		try {
			if(candidateDb.getCompany().getCompanyKeyword().equals(DCIMCH)) {
				se.alRejectedByCandidateMailToHRD(mailSender, candidateDb, url, commonEmailAddressDCIMCH);
			} else {
				se.alRejectedByCandidateMailToHRD(mailSender, candidateDb, url, commonEmailAddress);
			}
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//update candidate
		commonService.saveOrUpdateModelObjectToDB(candidateDb);	
		
		return new ModelAndView("redirect:/index", model);
	}
	
	@RequestMapping(value = "/candidateResponse", method = RequestMethod.GET)
	public ModelAndView candidateResponse (Candidate candidate, Principal principal, HttpServletRequest request) throws Exception{
		
		if(principal == null) {
			return new ModelAndView("redirect:/login");
		}
		
		Map <String, Object> model = new HashMap<String, Object>();
		Candidate candidateDb = (Candidate) commonService.getAnObjectByAnyUniqueColumn("Candidate", "candId", principal.getName().toString());	
		
		model.put("candidate", candidateDb);
		
		if(candidateDb.getStatus().equals(APPROVED_APP_LETTER)) {
			return new ModelAndView("candidateResponse", model);
		} else {
			return new ModelAndView("redirect:/index");
		}		
	}
	
	
	@RequestMapping(value = "/alAcceptedByCandidate", method = RequestMethod.GET)
	public ModelAndView alAcceptedByCandidate (Candidate candidate, Principal principal, HttpServletRequest request) throws Exception{
		
		if(principal == null) {
			return new ModelAndView("redirect:/login");
		}
		
		String url= "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/login";	
		
		Map <String, Object> model = new HashMap<String, Object>();
		
		//date
		Date now = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String today = df.format(now);
		
		Candidate candidateDb = (Candidate) commonService.getAnObjectByAnyUniqueColumn("Candidate", "id", candidate.getId().toString());		
		
		//email to HRD
		SendEmail se = new SendEmail();	
		try {
			if(candidateDb.getCompany().getCompanyKeyword().equals(DCIMCH)) {
				se.alAcceptedByCandidateMailToHRD(mailSender, candidateDb, url, commonEmailAddressDCIMCH);
			} else {
				se.alAcceptedByCandidateMailToHRD(mailSender, candidateDb, url, commonEmailAddress);
			}
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//change status for decline candidate
		candidateDb.setStatus(CANDIDATE_ACCEPTED_AL);		
		candidateDb.setModifiedBy(principal.getName());
		candidateDb.setModifiedDate(new Date());
		commonService.saveOrUpdateModelObjectToDB(candidateDb);	
		
		return new ModelAndView("redirect:/index", model);
	}
	
	//joinCandidate
	@RequestMapping(value = "/joinCandidate", method = RequestMethod.GET)
	public ModelAndView joinCandidate (Candidate candidate, Principal principal, HttpServletRequest request, RedirectAttributes attributes) throws Exception{
		
		if(principal == null) {
			return new ModelAndView("redirect:/login");
		}
		
		Integer circular = Integer.parseInt(request.getParameter("circular"));
		
		String url= "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/login";		
		
		CandidateProfile candProfileCheckExist = (CandidateProfile) commonService.getAnObjectByAnyUniqueColumn("CandidateProfile", "empId", candidate.getEmpId().toString());
		
		if(candProfileCheckExist == null) {		
			Map <String, Object> model = new HashMap<String, Object>();
			
			Candidate candidateDb = (Candidate) commonService.getAnObjectByAnyUniqueColumn("Candidate", "id", request.getParameter("id").toString());	
			
			CandidateProfile candProfile = (CandidateProfile) commonService.getAnObjectByAnyUniqueColumn("CandidateProfile", "candidate.id", candidateDb.getId().toString());
			candProfile.setEmpId(candidate.getEmpId());
			candProfile.setModifiedBy(principal.getName());
			candProfile.setModifiedDate(new Date());
			
			//send email to candidate must
			SendEmail se = new SendEmail();	
			// greeting to candidate
			try {
				if(candidateDb.getCompany().getCompanyKeyword().equals(DCIMCH)) {
					se.candidateGreatingMail(mailSender, candidateDb, url, ccEmailAddresssDCIMCH);
				}else {
					se.candidateGreatingMail(mailSender, candidateDb, url, ccEmailAddresss);
				}
				
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			
			// necessary link to candidate must
			try {
				if(candidateDb.getCompany().getCompanyKeyword().equals(DCIMCH)) {
					se.candidateNecessaryLinkMail(mailSender, candidateDb, url, ccEmailAddresssDCIMCH);
				}else {
					se.candidateNecessaryLinkMail(mailSender, candidateDb, url, ccEmailAddresss);
				}
				
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			
			//send email to admin and it must
			try {
				String photoLink = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/candidatePhoto/" + candidateDb.getCandId() + ".jpg";
				if(candidateDb.getCompany().getCompanyKeyword().equals(DCIMCH)) {
					se.candidateJoinedMailToAdminAndIT(mailSender, candidateDb, candProfile, url, photoLink, joinDcimchTo, joinDcimchCC);
				} 
				
				if(candidateDb.getCompany().getCompanyKeyword().equals(SYNERGY))  {
					se.candidateJoinedMailToAdminAndIT(mailSender, candidateDb, candProfile, url, photoLink, joinSynergyTo, joinSynergyCC);
				}
				
				if(candidateDb.getCompany().getCompanyKeyword().equals(LEXICON))  {
					se.candidateJoinedMailToAdminAndIT(mailSender, candidateDb, candProfile, url, photoLink, joinSynergyTo, joinSynergyCC);
				}
				
				if(candidateDb.getCompany().getCompanyKeyword().equals(NAZDAQ))  {
					se.candidateJoinedMailToAdminAndIT(mailSender, candidateDb, candProfile, url, photoLink, joinSynergyTo, joinSynergyCC);
				}
				
				if(candidateDb.getCompany().getCompanyKeyword().equals(MEDITECH))  {
					se.candidateJoinedMailToAdminAndIT(mailSender, candidateDb, candProfile, url, photoLink, joinSynergyTo, joinSynergyCC);
				}
				
				if(candidateDb.getCompany().getCompanyKeyword().equals(NORTH_EGG))  {
					//se.candidateJoinedMailToAdminAndIT(mailSender, candidateDb, candProfile, url, photoLink, joinSynergyTo, joinSynergyCC);
				}
				
				if(candidateDb.getCompany().getCompanyKeyword().equals(CREATIVE_TRADE))  {
					//se.candidateJoinedMailToAdminAndIT(mailSender, candidateDb, candProfile, url, photoLink, joinSynergyTo, joinSynergyCC);
				}
				
				if(candidateDb.getCompany().getCompanyKeyword().equals(ARDENT_SYSTEM))  {
					//se.candidateJoinedMailToAdminAndIT(mailSender, candidateDb, candProfile, url, photoLink, joinSynergyTo, joinSynergyCC);
				}
				
				if(candidateDb.getCompany().getCompanyKeyword().equals(KASIR_UDDIN_HOSPITAL))  {
					//se.candidateJoinedMailToAdminAndIT(mailSender, candidateDb, candProfile, url, photoLink, joinSynergyTo, joinSynergyCC);
				}
				
				if(candidateDb.getCompany().getCompanyKeyword().equals(NPPL))  {
					//se.candidateJoinedMailToAdminAndIT(mailSender, candidateDb, candProfile, url, photoLink, joinSynergyTo, joinSynergyCC);
				}
				
				if(candidateDb.getCompany().getCompanyKeyword().equals(APPLE_CERAMICS))  {
					//se.candidateJoinedMailToAdminAndIT(mailSender, candidateDb, candProfile, url, photoLink, joinSynergyTo, joinSynergyCC);
				}
				
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			
			
			// send email to all employees if yes
			// 1 ==  yes. do you want to circular mail submit
			//if(circular == 1) {
				try {
					if(candidateDb.getCompany().getCompanyKeyword().equals(SYNERGY)){
						se.candidateIntriduceMailToAll(mailSender, candidateDb, candProfile, url, intriduceSynergyCC, circular);
					}
					
					if(candidateDb.getCompany().getCompanyKeyword().equals(LEXICON)) {
						se.candidateIntriduceMailToAll(mailSender, candidateDb, candProfile, url, intriduceLexiconCC, circular);
					}
					
					if(candidateDb.getCompany().getCompanyKeyword().equals(DCIMCH)) {
						se.candidateIntriduceMailToAll(mailSender, candidateDb, candProfile, url, intriduceDcimchCC, circular);
					} 
					
					if(candidateDb.getCompany().getCompanyKeyword().equals(NORTH_EGG)) {
						se.candidateIntriduceMailToAll(mailSender, candidateDb, candProfile, url, intriduceNelCC, circular);
					}
					
					if(candidateDb.getCompany().getCompanyKeyword().equals(NAZDAQ)) {
						se.candidateIntriduceMailToAll(mailSender, candidateDb, candProfile, url, intriduceNazdaqCC, circular);
					}
					
					if(candidateDb.getCompany().getCompanyKeyword().equals(MEDITECH)) {
						se.candidateIntriduceMailToAll(mailSender, candidateDb, candProfile, url, intriduceMeditechCC, circular);
					}
					
					if(candidateDb.getCompany().getCompanyKeyword().equals(ARDENT_SYSTEM)) {
						se.candidateIntriduceMailToAll(mailSender, candidateDb, candProfile, url, intriduceArdentCC, circular);
					} 
					
					if(candidateDb.getCompany().getCompanyKeyword().equals(CREATIVE_TRADE)) {
						se.candidateIntriduceMailToAll(mailSender, candidateDb, candProfile, url, intriduceCreativeCC, circular);
					} 					
										
					if(candidateDb.getCompany().getCompanyKeyword().equals(NPPL)) {
						se.candidateIntriduceMailToAll(mailSender, candidateDb, candProfile, url, intriduceNpplCC, circular);
					}
					
					if(candidateDb.getCompany().getCompanyKeyword().equals(KASIR_UDDIN_HOSPITAL)) {
						se.candidateIntriduceMailToAll(mailSender, candidateDb, candProfile, url, intriduceKumhCC, circular);
					}					
					
					if(candidateDb.getCompany().getCompanyKeyword().equals(APPLE_CERAMICS)) {
						se.candidateIntriduceMailToAll(mailSender, candidateDb, candProfile, url, intriduceAppleCC, circular);
					}
					
				} catch (MessagingException e) {
					e.printStackTrace();
				}
			//}			
			
			//send sms to candidate
			String mobileNo = candidateDb.getCandMobileNo();			
			String formatedMobileNo = "";			
			if(mobileNo != null && !mobileNo.equals(""))  {
				formatedMobileNo = getFormatedMobileNo(mobileNo);
			}			
			if(!formatedMobileNo.equals("")){
				SendSms sendSms = new SendSms();
				sendSms.sendSmsTo(mobileNo, "Dear " + candidateDb.getCandName() + ",\nGood Morning!\nWe are so pleased to welcome you to your new assignment in our organization. Please check your email for details.");
			}
			
			Date now = new Date();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String today = df.format(now);
			
			//change status for delete Candidate
			candidateDb.setStatus(JOINED);
			candidateDb.setModifiedBy(principal.getName());
			candidateDb.setModifiedDate(new Date());
			
			candidateDb.setEjmsJoiningDate(today);
			
			candidateDb.setEmployeeId(candidate.getEmpId().toString());
			commonService.saveOrUpdateModelObjectToDB(candProfile);	
			commonService.saveOrUpdateModelObjectToDB(candidateDb);	
			return new ModelAndView("redirect:/todaysJoinCandidates", model);
		} else {
			attributes.addFlashAttribute("successMsg", "This Employee ID Already Exist. Please Try again with another ID.");
			return new ModelAndView("redirect:/pendCandidateList");
		}
		
	}
	
	//submitCandidateProfile
	@RequestMapping(value = "/submitCandidateProfile", method = RequestMethod.GET)
	public ModelAndView submitCandidateProfile (Principal principal, HttpServletRequest request){
		
		if(principal == null) {
			return new ModelAndView("redirect:/login");
		}
		
		String url= "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/login";
		
		Candidate candidateDb = (Candidate) commonService.getAnObjectByAnyUniqueColumn("Candidate", "candId", principal.getName().toString());
		
		SendEmail se = new SendEmail();	
		// profile submit mail to hr
		try {
			if(candidateDb.getCompany().getCompanyKeyword().equals(DCIMCH)) {
				se.profileFormSubmitEmailToHR(mailSender, candidateDb, url, ccEmailAddresssDCIMCH, commonEmailAddressDCIMCH);
			} else {
				se.profileFormSubmitEmailToHR(mailSender, candidateDb, url, ccEmailAddresss, commonEmailAddress);
			}
					
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		
		candidateDb.setModifiedBy(principal.getName());
		candidateDb.setModifiedDate(new Date());
		candidateDb.setStatus(SUBMITTED);
		commonService.saveOrUpdateModelObjectToDB(candidateDb);		
		return new ModelAndView("redirect:/index");
	}
	
	//submitCandidateProfile
	@RequestMapping(value = "/approveCandidateProfile", method = RequestMethod.GET)
	public ModelAndView approveCandidateProfile (Candidate candidate, BindingResult result, Principal principal, HttpServletRequest request){
		
		if(principal == null) {
			return new ModelAndView("redirect:/login");
		}
		
		String url= "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/login";
		
		Date now = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Candidate candidateDb = (Candidate) commonService.getAnObjectByAnyUniqueColumn("Candidate", "id", candidate.getId().toString());
		
		//send email to candidate
		SendEmail se = new SendEmail();	
		try {
			if(candidateDb.getCompany().getCompanyKeyword().equals(DCIMCH)) {
				se.candidateProfileApproveMail(mailSender, candidateDb, url, commonEmailAddressDCIMCH);
			} else{
				se.candidateProfileApproveMail(mailSender, candidateDb, url, commonEmailAddress);
			}
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		//send sms to candidate
		String mobileNo = candidateDb.getCandMobileNo();			
		String formatedMobileNo = "";			
		if(mobileNo != null && !mobileNo.equals(""))  {
			formatedMobileNo = getFormatedMobileNo(mobileNo);
		}			
		if(!formatedMobileNo.equals("")){
			SendSms sendSms = new SendSms();
			sendSms.sendSmsTo(mobileNo, "Dear " + candidateDb.getCandName() + ",\nYour profile has been approved. now, you can download appointment letter from EJMS. Please check your email for details.");
		}
		
		candidateDb.setModifiedBy(principal.getName());
		candidateDb.setModifiedDate(now);
		candidateDb.setStatus(APPROVED_APP_LETTER);
		candidateDb.setCandProfileApproveDate(df.format(now));
		commonService.saveOrUpdateModelObjectToDB(candidateDb);		
		return new ModelAndView("redirect:/index");
	}
	
	//submitCandidateProfile
	@RequestMapping(value = "/resendALToCandidate", method = RequestMethod.GET)
	public ModelAndView resendALToCandidate (Candidate candidate, BindingResult result, Principal principal, HttpServletRequest request){
		
		if(principal == null) {
			return new ModelAndView("redirect:/login");
		}
		
		String url= "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/login";
		
		Date now = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Candidate candidateDb = (Candidate) commonService.getAnObjectByAnyUniqueColumn("Candidate", "id", candidate.getId().toString());
		
		//send email to candidate
		SendEmail se = new SendEmail();	
		try {
			if(candidateDb.getCompany().getCompanyKeyword().equals(DCIMCH)) {
				se.candidateProfileApproveMail(mailSender, candidateDb, url, commonEmailAddressDCIMCH);
			} else {
				se.candidateProfileApproveMail(mailSender, candidateDb, url, commonEmailAddress);
			}
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		//send sms to candidate
		String mobileNo = candidateDb.getCandMobileNo();			
		String formatedMobileNo = "";			
		if(mobileNo != null && !mobileNo.equals(""))  {
			formatedMobileNo = getFormatedMobileNo(mobileNo);
		}			
		if(!formatedMobileNo.equals("")){
			SendSms sendSms = new SendSms();
			sendSms.sendSmsTo(mobileNo, "Dear " + candidateDb.getCandName() + ",\nAppointment Letter has been Resend to you. now, you can download appointment letter from EJMS. Please check your email for details.");
		}
		
		candidateDb.setModifiedBy(principal.getName());
		candidateDb.setModifiedDate(now);
		candidateDb.setStatus(APPROVED_APP_LETTER);
		candidateDb.setCandProfileApproveDate(df.format(now));
		commonService.saveOrUpdateModelObjectToDB(candidateDb);		
		return new ModelAndView("redirect:/index");
	}
	
	@RequestMapping(value = {"/checkEmailId"}, method = RequestMethod.POST)
	private @ResponseBody String checkEmailId(@RequestBody String jesonString, Principal principal) 
			throws JsonGenerationException, JsonMappingException, Exception {
		String toJson = "";
		String result = "fail";	
		Gson gson = new GsonBuilder().create();
		
		Candidate candidateBean = gson.fromJson(jesonString, Candidate.class);
		
		Candidate candidateDb = (Candidate) commonService.getAnObjectByAnyUniqueColumn("Candidate", "candEmail", candidateBean.getCandEmail().toString());
		
		if (candidateDb == null) {
			result = "success";
		} 
		
		if (candidateBean.getCandId().length() > 0) {
			if(candidateDb.getId().toString().equals(candidateBean.getCandId().toString())){
				result = "success";
			}
		}
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		toJson = ow.writeValueAsString(result);
		return toJson;
	}
	
	public void saveUser(UserBean userBean, HttpServletRequest request, Candidate candidate) throws NoSuchAlgorithmException {
		
		String url= "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/login";
		User user = prepareModel(userBean);		
		
		String authority = userBean.getAuth();		
		boolean checkId=true;		
		if(null==user.getId()){
			checkId=false;
		}		
		
		commonService.saveOrUpdateModelObjectToDB(user);
		insertUserRole(user,authority);		 
		if(!checkId){
			if(null!= user.getEmail()){
				SendEmail se = new SendEmail();	
				try {
					if(candidate.getCompany().getCompanyKeyword().equals(DCIMCH)) {
						se.profileFormSubmitEmailToCandidate(mailSender, user, candidate, url, ccEmailAddresssDCIMCH, commonEmailAddressDCIMCH);
					} else {
						se.profileFormSubmitEmailToCandidate(mailSender, user, candidate, url, ccEmailAddresss, commonEmailAddress);
					}
					
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}			
		}
	}
	
	
	
	private void insertUserRole(User u, String authority){
		UserRole ur=new UserRole();
		ur.setUserId(u.getId());
		ur.setAuthority(authority);
		commonService.saveOrUpdateModelObjectToDB(ur);
	}
	
	private User prepareModel(UserBean userBean){
		User user = new User();
		user.setuName(userBean.getuName());
		user.setId(userBean.getId());
		user.setPassword(userBean.getPass());
		user.setEmail(userBean.getEmail());
		user.setStatus(userBean.getStatus());
		user.setFirstName(userBean.getfName());
		user.setLastName(userBean.getlName());
		user.setDescription(userBean.getDesc());
		user.setInsertedBy(userBean.getInsertedBy());
		user.setInsertDate(curTime);
		user.setUpdateDate(curTime);
		userBean.setId(null);
		return user;
	}
	
	//added by taleb
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
	
	private String getReferenceNo(Candidate lastCandidate){
		String refNo = "001";
		if(lastCandidate != null){
			Integer ref = 1;
			
			if(lastCandidate.getRefNo() != null) {
				 ref = Integer.parseInt(lastCandidate.getRefNo())+1;
			} 
			
			if(ref.toString().length() == 1) {
				refNo = "00"+ref;
			} else if (ref.toString().length() == 2) {
				refNo = "0"+ref;
			} else {
				refNo = ref.toString();
			}
		}
		return refNo;
	}
}
