package com.nazdaq.ejms;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nazdaq.ejms.model.Candidate;
import com.nazdaq.ejms.model.CandidateProfile;
import com.nazdaq.ejms.model.Company;
import com.nazdaq.ejms.model.HREmployee;
import com.nazdaq.ejms.service.CommonService;
import com.nazdaq.ejms.util.Constants;

@Controller
@PropertySource("classpath:common.properties")
public class ProfileController implements Constants{

	@Autowired
	private CommonService commonService;
	
	@Value("${cc.email.addresss}")
	String ccEmailAddresss;
	
	@Value("${common.email.address}")
	String commonEmailAddress;
	
	@Value("${cc.email.addresss.dcimch}")
	String ccEmailAddresssDCIMCH;
	
	@Value("${common.email.address.dcimch}")
	String commonEmailAddressDCIMCH;
	
	//candidateProfileForm
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/candidateProfileForm", method = RequestMethod.GET)
	public ModelAndView candidateProfileForm (@ModelAttribute("candidateProfileForm") CandidateProfile candidateProfile, 
			BindingResult result, Principal principal, HttpServletRequest request){
		
		if(principal == null) {
			return new ModelAndView("redirect:/login");
		}
		
		String roleName = commonService.getAuthRoleName();
		
		boolean isAdmin = false;
		Candidate candidateDb = null;
		CandidateProfile cfDb = null;
		if(request.isUserInRole(ROLE_ADMIN) || request.isUserInRole(ROLE_ADMIN_SYNERGY) || request.isUserInRole(ROLE_ADMIN_DCIMCH)){
			isAdmin = true;
			if(candidateProfile.getId() != null) {
				candidateDb = (Candidate) commonService.getAnObjectByAnyUniqueColumn("Candidate", "id", candidateProfile.getId().toString());
			}
		} else {
			candidateDb = (Candidate) commonService.getAnObjectByAnyUniqueColumn("Candidate", "candId", principal.getName().toString());	
			boolean flag = true;
			if(candidateDb.getStatus().equals(SEND_TO_CANDIDATE)) {
				flag = false;				
			} 
			
			if(candidateDb.getStatus().equals(SUBMITTED)) {
				flag = false;
			}
			
			if(candidateDb.getStatus().equals(JOINED)) {
				flag = false;
			}
			
			/*if(candidateDb.getStatus().equals(APPROVED_APP_LETTER)) {
				flag = false;			
			}*/
			
			if(candidateDb.getStatus().equals(QUES_SET_ONE_ANS_SUBMIT)) {
				flag = false;
			}
			
			if(candidateDb.getStatus().equals(QUES_SET_TWO_ANS_SUBMIT)) {
				flag = false;
			}
			
			if(candidateDb.getStatus().equals(QUES_SET_THREE_ANS_SUBMIT)) {
				flag = false;
			}
			
			if(candidateDb.getStatus().equals(CANDIDATE_ACCEPTED_AL)) {
				//for candidate upload doc
				if(candidateDb.getUploadResignLetter().equals("0") || candidateDb.getUploadOthersLetter().equals("0")) {
					flag = true;
				} else {
					flag = false;
				}
				
			}
			
			if(candidateDb.getStatus().equals(CANDIDATE_REJECTED_AL)) {
				flag = false;
			}
			
			if(flag) {
				return new ModelAndView("redirect:/index");
			}
		}
		
		if(candidateDb != null) {			
			cfDb = (CandidateProfile) commonService.getAnObjectByAnyUniqueColumn("CandidateProfile", "candidate.id", candidateDb.getId().toString());
		}
			
		
		List<HREmployee> employeeList = (List<HREmployee>)(Object)
				commonService.getObjectListByAnyColumn("HREmployee", "active", "1");
		
		List<Company> companyList = (List<Company>)(Object)
				commonService.getObjectListByAnyColumn("Company", "active", "1");
		
		
		Map <String, Object> model = new HashMap<String, Object>();		
		model.put("candidateProfile", cfDb);
		model.put("employeeList", employeeList);
		model.put("companyList", companyList);
		model.put("candidate", candidateDb);
		model.put("isAdmin", isAdmin);
		return new ModelAndView("candidateProfileForm", model);
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/candidateUploadForm", method = RequestMethod.GET)
	public ModelAndView candidateUploadForm (@ModelAttribute("candidateForm") Candidate candidate, 
			BindingResult result, Principal principal, HttpServletRequest request){
		
		if(principal == null) {
			return new ModelAndView("redirect:/login");
		}
		
		Candidate candidateDb = (Candidate) commonService.getAnObjectByAnyUniqueColumn("Candidate", "candId", principal.getName().toString());	
		
		Map <String, Object> model = new HashMap<String, Object>();		
		model.put("candidate", candidateDb);
		return new ModelAndView("candidateUploadForm", model);
	}
	
	@RequestMapping(value = "/saveCandidateProfile", method = RequestMethod.POST)
	public @ResponseBody String saveCandidateProfile (@RequestBody String jesonString, Principal principal) 
			throws JsonGenerationException, JsonMappingException, Exception {
		
		String toJson = "";
		String result = "";
		boolean flag = true;
		
		Gson gson = new GsonBuilder().create();		
		CandidateProfile candidateProfileBean = gson.fromJson(jesonString, CandidateProfile.class);
		candidateProfileBean.setModifiedBy(principal.getName());
		
		if(candidateProfileBean.getCandidateId() != null && candidateProfileBean.getCandidateId() > 0) {
			CandidateProfile cpDb  = (CandidateProfile) 
					commonService.getAnObjectByAnyUniqueColumn("CandidateProfile", "candidate.id", candidateProfileBean.getCandidateId().toString());
			
			flag = this.updateCandidateProfile(candidateProfileBean, cpDb);
			
			Candidate candidateDb = cpDb.getCandidate();
			boolean isNameAndMobileChange = false;
			if(candidateProfileBean.getCandMobileNo() != null && candidateProfileBean.getCandMobileNo().trim().length() > 0) {
				candidateDb.setCandMobileNo(candidateProfileBean.getCandMobileNo());
				isNameAndMobileChange = true;
			}
			if(candidateProfileBean.getName() != null && candidateProfileBean.getName().trim().length() > 0) {
				candidateDb.setCandName(candidateProfileBean.getName());
				isNameAndMobileChange = true;
			}
			
			if(isNameAndMobileChange) {
				candidateDb.setModifiedBy(principal.getName());
				candidateDb.setModifiedDate(new Date());
				commonService.saveOrUpdateModelObjectToDB(candidateDb);
			}
			
		} else {
			flag = false;
		}
		
		
		if(flag) {
			result = "success";
		} else {
			result = "fail";
		}
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		toJson = ow.writeValueAsString(result);
		return toJson;
	}
	
	public boolean updateCandidateProfile(CandidateProfile cpBean, CandidateProfile cpdb) {
		boolean respone = true;
		//developer view
		cpdb.setModifiedBy(cpBean.getModifiedBy());
		cpdb.setModifiedDate(new Date());
		//general Info
		if(cpBean.getName() != null && cpBean.getName().trim().length() > 0)
		cpdb.setName(cpBean.getName());
			
		if(cpBean.getDateOfBirth() != null && cpBean.getDateOfBirth().trim().length() > 0)
		cpdb.setDateOfBirth(cpBean.getDateOfBirth());
		if(cpBean.getGender() != null && cpBean.getGender().trim().length() > 0)
		cpdb.setGender(cpBean.getGender());
		if(cpBean.getReligion() != null && cpBean.getReligion().trim().length() > 0)
		cpdb.setReligion(cpBean.getReligion());
		if(cpBean.getBloodGroup() != null && cpBean.getBloodGroup().trim().length() > 0)
		cpdb.setBloodGroup(cpBean.getBloodGroup());
		if(cpBean.getNidNo() != null && cpBean.getNidNo().trim().length() > 0)
		cpdb.setNidNo(cpBean.getNidNo());
		if(cpBean.getNationality() != null && cpBean.getNationality().trim().length() > 0)
		cpdb.setNationality(cpBean.getNationality());
		if(cpBean.getFatherName() != null && cpBean.getFatherName().trim().length() > 0)
		cpdb.setFatherName(cpBean.getFatherName());
		if(cpBean.getMotherName() != null && cpBean.getMotherName().trim().length() > 0)
		cpdb.setMotherName(cpBean.getMotherName());
		
		//address
		if(cpBean.getPermanentAddress() != null && cpBean.getPermanentAddress().trim().length() > 0)
		cpdb.setPermanentAddress(cpBean.getPermanentAddress());
		if(cpBean.getPresentAddress() != null && cpBean.getPresentAddress().trim().length() > 0)
		cpdb.setPresentAddress(cpBean.getPresentAddress());
		
		
		if(cpBean.getCandMobileNo() != null && cpBean.getCandMobileNo().trim().length() > 0)
		cpdb.setCandMobileNo(cpBean.getCandMobileNo());
		
		//cpdb.setEmailAddress(cpBean.getEmailAddress());
		if(cpBean.getEmerContPerson() != null && cpBean.getEmerContPerson().trim().length() > 0)
		cpdb.setEmerContPerson(cpBean.getEmerContPerson());
		if(cpBean.getEmerContNo() != null && cpBean.getEmerContNo().trim().length() > 0)
		cpdb.setEmerContNo(cpBean.getEmerContNo());
		
		//spouse and child
		if(cpBean.getMaritialStatus() != null && cpBean.getMaritialStatus().trim().length() > 0)
		cpdb.setMaritialStatus(cpBean.getMaritialStatus());
		if(cpBean.getSpouseName() != null && cpBean.getSpouseName().trim().length() > 0)
		cpdb.setSpouseName(cpBean.getSpouseName());
		if(cpBean.getSpouseDob() != null && cpBean.getSpouseDob().trim().length() > 0)
		cpdb.setSpouseDob(cpBean.getSpouseDob());
		if(cpBean.getSpouseContNo() != null && cpBean.getSpouseContNo().trim().length() > 0)
		cpdb.setSpouseContNo(cpBean.getSpouseContNo());
		
		if(cpBean.getChildOneName() != null && cpBean.getChildOneName().trim().length() > 0)
		cpdb.setChildOneName(cpBean.getChildOneName());
		if(cpBean.getChildOneGender() != null && cpBean.getChildOneGender().trim().length() > 0)
		cpdb.setChildOneGender(cpBean.getChildOneGender());
		if(cpBean.getChildOneDob() != null && cpBean.getChildOneDob().trim().length() > 0)
		cpdb.setChildOneDob(cpBean.getChildOneDob());
		
		if(cpBean.getChildTwoName() != null && cpBean.getChildTwoName().trim().length() > 0)
		cpdb.setChildTwoName(cpBean.getChildTwoName());
		if(cpBean.getChildTwoGender() != null && cpBean.getChildTwoGender().trim().length() > 0)
		cpdb.setChildTwoGender(cpBean.getChildTwoGender());
		if(cpBean.getChildTwoDob() != null && cpBean.getChildTwoDob().trim().length() > 0)
		cpdb.setChildTwoDob(cpBean.getChildTwoDob());
		
		//previous organization
		if(cpBean.getPrevOrgName1() != null && cpBean.getPrevOrgName1().trim().length() > 0)
		cpdb.setPrevOrgName1(cpBean.getPrevOrgName1());
		if(cpBean.getPrevOrgAdrs1() != null && cpBean.getPrevOrgAdrs1().trim().length() > 0)
		cpdb.setPrevOrgAdrs1(cpBean.getPrevOrgAdrs1());
		if(cpBean.getPrevOrgDesig1() != null && cpBean.getPrevOrgDesig1().trim().length() > 0)
		cpdb.setPrevOrgDesig1(cpBean.getPrevOrgDesig1());
		if(cpBean.getPrevOrgFrom1() != null && cpBean.getPrevOrgFrom1().trim().length() > 0)
		cpdb.setPrevOrgFrom1(cpBean.getPrevOrgFrom1());
		if(cpBean.getPrevOrgTo1() != null && cpBean.getPrevOrgTo1().trim().length() > 0)
		cpdb.setPrevOrgTo1(cpBean.getPrevOrgTo1());
		
		if(cpBean.getPrevOrgName2() != null && cpBean.getPrevOrgName2().trim().length() > 0)
		cpdb.setPrevOrgName2(cpBean.getPrevOrgName2());
		if(cpBean.getPrevOrgAdrs2() != null && cpBean.getPrevOrgAdrs2().trim().length() > 0)
		cpdb.setPrevOrgAdrs2(cpBean.getPrevOrgAdrs2());
		if(cpBean.getPrevOrgDesig2() != null && cpBean.getPrevOrgDesig2().trim().length() > 0)
		cpdb.setPrevOrgDesig2(cpBean.getPrevOrgDesig2());
		if(cpBean.getPrevOrgFrom2() != null && cpBean.getPrevOrgFrom2().trim().length() > 0)
		cpdb.setPrevOrgFrom2(cpBean.getPrevOrgFrom2());
		if(cpBean.getPrevOrgTo2() != null && cpBean.getPrevOrgTo2().trim().length() > 0)
		cpdb.setPrevOrgTo2(cpBean.getPrevOrgTo2());
		
		if(cpBean.getPrevOrgName3() != null && cpBean.getPrevOrgName3().trim().length() > 0)
		cpdb.setPrevOrgName3(cpBean.getPrevOrgName3());
		if(cpBean.getPrevOrgAdrs3() != null && cpBean.getPrevOrgAdrs3().trim().length() > 0)
		cpdb.setPrevOrgAdrs3(cpBean.getPrevOrgAdrs3());
		if(cpBean.getPrevOrgDesig3() != null && cpBean.getPrevOrgDesig3().trim().length() > 0)
		cpdb.setPrevOrgDesig3(cpBean.getPrevOrgDesig3());
		if(cpBean.getPrevOrgFrom3() != null && cpBean.getPrevOrgFrom3().trim().length() > 0)
		cpdb.setPrevOrgFrom3(cpBean.getPrevOrgFrom3());
		if(cpBean.getPrevOrgTo3() != null && cpBean.getPrevOrgTo3().trim().length() > 0)
		cpdb.setPrevOrgTo3(cpBean.getPrevOrgTo3());
		
		//educational qualification
		if(cpBean.getCandDegreeName1() != null && cpBean.getCandDegreeName1().trim().length() > 0)
		cpdb.setCandDegreeName1(cpBean.getCandDegreeName1());
		if(cpBean.getCandDegreeMajor1() != null && cpBean.getCandDegreeMajor1().trim().length() > 0)
		cpdb.setCandDegreeMajor1(cpBean.getCandDegreeMajor1());
		if(cpBean.getCandDegreePasYr1() != null && cpBean.getCandDegreePasYr1().trim().length() > 0)
		cpdb.setCandDegreePasYr1(cpBean.getCandDegreePasYr1());
		if(cpBean.getCandDegreeInst1() != null && cpBean.getCandDegreeInst1().trim().length() > 0)
		cpdb.setCandDegreeInst1(cpBean.getCandDegreeInst1());
		
		if(cpBean.getCandDegreeName2() != null && cpBean.getCandDegreeName2().trim().length() > 0)
		cpdb.setCandDegreeName2(cpBean.getCandDegreeName2());
		if(cpBean.getCandDegreeMajor2() != null && cpBean.getCandDegreeMajor2().trim().length() > 0)
		cpdb.setCandDegreeMajor2(cpBean.getCandDegreeMajor2());
		if(cpBean.getCandDegreePasYr2() != null && cpBean.getCandDegreePasYr2().trim().length() > 0)
		cpdb.setCandDegreePasYr2(cpBean.getCandDegreePasYr2());
		if(cpBean.getCandDegreeInst2() != null && cpBean.getCandDegreeInst2().trim().length() > 0)
		cpdb.setCandDegreeInst2(cpBean.getCandDegreeInst2());
		
		//others information of candidate
		if(cpBean.getCandidateTraining() != null && cpBean.getCandidateTraining().trim().length() > 0)
		cpdb.setCandidateTraining(cpBean.getCandidateTraining());
		if(cpBean.getOthersSkill() != null && cpBean.getOthersSkill().trim().length() > 0)
		cpdb.setOthersSkill(cpBean.getOthersSkill());
		if(cpBean.getRelativeName() != null && cpBean.getRelativeName().trim().length() > 0)
		cpdb.setRelativeName(cpBean.getRelativeName());
		if(cpBean.getRelativeDesig() != null && cpBean.getRelativeDesig().trim().length() > 0)
		cpdb.setRelativeDesig(cpBean.getRelativeDesig());
		if(cpBean.getReferName1() != null && cpBean.getReferName1().trim().length() > 0)
		cpdb.setReferName1(cpBean.getReferName1());
		if(cpBean.getReferOccuption1() != null && cpBean.getReferOccuption1().trim().length() > 0)
		cpdb.setReferOccuption1(cpBean.getReferOccuption1());
		if(cpBean.getReferAddress1() != null && cpBean.getReferAddress1().trim().length() > 0)
		cpdb.setReferAddress1(cpBean.getReferAddress1());
		if(cpBean.getReferContactNo1() != null && cpBean.getReferContactNo1().trim().length() > 0)
		cpdb.setReferContactNo1(cpBean.getReferContactNo1());
		if(cpBean.getReferName2() != null && cpBean.getReferName2().trim().length() > 0)
		cpdb.setReferName2(cpBean.getReferName2());
		if(cpBean.getReferOccuption2() != null && cpBean.getReferOccuption2().trim().length() > 0)
		cpdb.setReferOccuption2(cpBean.getReferOccuption2());
		if(cpBean.getReferAddress2() != null && cpBean.getReferAddress2().trim().length() > 0)
		cpdb.setReferAddress2(cpBean.getReferAddress2());
		if(cpBean.getReferContactNo2() != null && cpBean.getReferContactNo2().trim().length() > 0)
		cpdb.setReferContactNo2(cpBean.getReferContactNo2());
		
		//hr
		if(cpBean.getSupervisorName() != null && cpBean.getSupervisorName().trim().length() > 0)
		cpdb.setSupervisorName(cpBean.getSupervisorName());
		if(cpBean.getSupervisorEmail() != null && cpBean.getSupervisorEmail().trim().length() > 0)
		cpdb.setSupervisorEmail(cpBean.getSupervisorEmail());
		//cpdb.setDepartment(department);
		//cpdb.setDesignation(designation);
		if(cpBean.getGrade() != null && cpBean.getGrade().trim().length() > 0)
		cpdb.setGrade(cpBean.getGrade());
		if(cpBean.getEmpId() != null && cpBean.getEmpId().trim().length() > 0)
		cpdb.setEmpId(cpBean.getEmpId());
		if(cpBean.getOfficeMobileNo() != null && cpBean.getOfficeMobileNo().trim().length() > 0)
		cpdb.setOfficeMobileNo(cpBean.getOfficeMobileNo());
		if(cpBean.getBankAccNo() != null && cpBean.getBankAccNo().trim().length() > 0)
		cpdb.setBankAccNo(cpBean.getBankAccNo());
		
		if(cpBean.getGrossSalary() != null) {
			cpdb.setGrossSalary(cpBean.getGrossSalary());			
		}
		
		if(cpBean.getBasicSalary() != null) {
			cpdb.setBasicSalary(cpBean.getBasicSalary());			
		}
		
		if(cpBean.getMedicalIn() != null) {
			cpdb.setMedicalIn(cpBean.getMedicalIn());			
		}
		
		if(cpBean.getConveyance() != null) {
			cpdb.setConveyance(cpBean.getConveyance());			
		}
		
		if(cpBean.getHouseRent() != null) {
			cpdb.setHouseRent(cpBean.getHouseRent());			
		}
		
		if(cpBean.getProvidentFund() != null) {
			cpdb.setProvidentFund(cpBean.getProvidentFund());		
		}
		
		if(cpBean.getSalIncreaseAmt() != null) {
			cpdb.setSalIncreaseAmt(cpBean.getSalIncreaseAmt());		
		}
		
		if(cpBean.getSalaryOthersAmt() != null) {
			cpdb.setSalaryOthersAmt(cpBean.getSalaryOthersAmt());		
		}
		
		if(cpBean.getOfficeLocation() != null && cpBean.getOfficeLocation().trim().length() > 0)
		cpdb.setOfficeLocation(cpBean.getOfficeLocation());
		
		if(cpBean.getYearlyBonus() != null && cpBean.getYearlyBonus().trim().length() > 0)
		cpdb.setYearlyBonus(cpBean.getYearlyBonus());
		
		if(cpBean.getProvisionPeriod() != null && cpBean.getProvisionPeriod().trim().length() > 0)
		cpdb.setProvisionPeriod(cpBean.getProvisionPeriod());
		
		if(cpBean.getAlIssuedBy() != null && cpBean.getAlIssuedBy().trim().length() > 0)
		cpdb.setAlIssuedBy(cpBean.getAlIssuedBy());
		
		if(cpBean.getAlIssuedByDesignation() != null && cpBean.getAlIssuedByDesignation().trim().length() > 0)
		cpdb.setAlIssuedByDesignation(cpBean.getAlIssuedByDesignation());
		
		if(cpBean.getAlPreparedBy() != null && cpBean.getAlPreparedBy().trim().length() > 0)
		cpdb.setAlPreparedBy(cpBean.getAlPreparedBy());
		
		if(cpBean.getRemarks() != null && cpBean.getRemarks().trim().length() > 0)
		cpdb.setRemarks(cpBean.getRemarks());
		
		try {
			commonService.saveOrUpdateModelObjectToDB(cpdb);
			respone = true;
		}catch(Exception e){
			respone = false;
		}
		
		return respone;
	}
	
	
}
