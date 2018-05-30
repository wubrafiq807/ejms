package com.nazdaq.ejms;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.nazdaq.ejms.model.Candidate;
import com.nazdaq.ejms.beans.CandidateBeanReportFour;
import com.nazdaq.ejms.beans.CandidateBeanReportOne;
import com.nazdaq.ejms.beans.CandidateBeanReportThree;
import com.nazdaq.ejms.beans.CandidateBeanReportTwo;
import com.nazdaq.ejms.beans.CandidateProfileBean;
import com.nazdaq.ejms.beans.ExtensionLetterBean;
import com.nazdaq.ejms.model.CandidateProfile;
import com.nazdaq.ejms.service.CommonService;
import com.nazdaq.ejms.util.Constants;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JRViewer;
import net.sf.jasperreports.view.JasperViewer;

@Controller
public class ReportController implements Constants{

	@Autowired
	private CommonService commonService;
	
	//performanceReportForm
	@RequestMapping(value = { "/performanceReportForm" }, method = RequestMethod.GET)
	public ModelAndView performanceReportForm(Principal principal, 
			@ModelAttribute("candidateForm") Candidate candidate) {
		
		if(principal == null) {
			return new ModelAndView("redirect:/login");
		}
		
		Map <String, Object> model = new HashMap<String, Object>();					
		model.put("candidate", candidate);		
		return new ModelAndView("performanceReportForm", model);
		
	}
	
	//generatePerformaceReport
	@RequestMapping(value = { "/generatePerformaceReport" }, method = RequestMethod.GET)
	public ModelAndView generatePerformaceReport(Principal principal, 
			@ModelAttribute("candidateForm") Candidate candidate) {
		
		if(principal == null) {
			return new ModelAndView("redirect:/login");
		}
		
		Map <String, Object> model = new HashMap<String, Object>();					
		model.put("candidate", candidate);		
		return new ModelAndView("performanceReportForm", model);
		
	}

	@RequestMapping(value = { "/appLetter" }, method = RequestMethod.GET)
	@ResponseBody
	public void getreportAccountManagement(HttpServletResponse response, HttpServletRequest request,
			CandidateBeanReportOne candidateBeanReportOne, CandidateBeanReportTwo candidateBeanReportTwo,
			CandidateBeanReportThree candidateBeanReportThree, CandidateBeanReportFour candidateBeanReportFour)
			throws JRException, IOException, ParseException {
		
		Candidate candidate = (Candidate) commonService.getAnObjectByAnyUniqueColumn("Candidate", "id",
				request.getParameter("id").toString());
		
		if(candidate.getCompany().getCompanyKeyword().equals(DCIMCH)) {
			this.appLetterForDCIMCH(response, request, candidateBeanReportOne, candidateBeanReportTwo, candidateBeanReportThree, candidateBeanReportFour, candidate);
		} else {
			this.appLetterForSynergy(response, request, candidateBeanReportOne, candidateBeanReportTwo, candidateBeanReportThree, candidateBeanReportFour, candidate);
		}
		
	}
	
	
	@RequestMapping(value = { "/getJoiningLetter" }, method = RequestMethod.GET)
	@ResponseBody
	public void getJoiningLet(HttpServletResponse response, HttpServletRequest request,ExtensionLetterBean extensionLetterBean)
			throws JRException, IOException, ParseException {
		JRDataSource jRdataSource = null;
		Candidate candidate = (Candidate) commonService.getAnObjectByAnyUniqueColumn("Candidate", "id",
				request.getParameter("id").toString());
		CandidateProfile candidateProfile=(CandidateProfile) commonService.getAnObjectByAnyUniqueColumn("CandidateProfile", "candidate.id",
				request.getParameter("id").toString());
		String reference = "HR/AL/"+candidate.getCompany().getCompanyKeyword()+"/"+candidate.getRefNo();
		extensionLetterBean=new ExtensionLetterBean(candidate.getCandName(), candidate.getCompany().getOfficeAddress(), reference, this.getCustomDateFromDateFormate(candidate.getCandProfileApproveDate().toString()), candidate.getDesignation().getName(), candidate.getDepartment().getName(), this.getCustomDateFromDateFormate(candidate.getOldDateOfJoin().toString()), this.getCustomDateFromDateFormate(candidate.getDateOfJoin().toString()), candidateProfile.getAlIssuedBy(), candidateProfile.getAlIssuedByDesignation());
		
	
		InputStream jasperStream = null;
		
		List mylist = new ArrayList<>();
		
		mylist.add(extensionLetterBean);
		

		jasperStream = this.getClass().getResourceAsStream("/joiningLetter.jasper");
		

		Map<String, Object> params = new HashMap<>();
		Map<String, Object> datasourcemap = new HashMap<>();
		datasourcemap.put("extensionLetterBean", extensionLetterBean);
		jRdataSource = new JRBeanCollectionDataSource(mylist, false);

	
		
		

		params.put("datasource", jRdataSource);
		params.put("textBody", "With due respect I would like to inform you that I am "+candidate.getCandName()+" joining on "+this.getCustomDateFromDateFormate(candidate.getDateOfJoin())+" as "+candidate.getDesignation().getName()+" in "+candidate.getDepartment().getName()+" Department Under "+candidate.getCompany().getName()+".");
		
		
		// prepare report first for one
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, jRdataSource);
		

		response.setContentType("application/x-pdf");
		response.setHeader("Content-disposition", "inline; filename="+"joinig_"+reference+".pdf");
		final OutputStream outStream = response.getOutputStream();
		// JasperViewer.viewReport(jasperPrint, false);
		JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
	
	
	}
	
	@RequestMapping(value = { "/getExtension" }, method = RequestMethod.GET)
	@ResponseBody
	public void getExtension(HttpServletResponse response, HttpServletRequest request,ExtensionLetterBean extensionLetterBean)
			throws JRException, IOException, ParseException {
		JRDataSource jRdataSource = null;
		Candidate candidate = (Candidate) commonService.getAnObjectByAnyUniqueColumn("Candidate", "id",
				request.getParameter("id").toString());
		CandidateProfile candidateProfile=(CandidateProfile) commonService.getAnObjectByAnyUniqueColumn("CandidateProfile", "candidate.id",
				request.getParameter("id").toString());
		String reference = "HR/AL/"+candidate.getCompany().getCompanyKeyword()+"/"+candidate.getRefNo();
		extensionLetterBean=new ExtensionLetterBean(candidate.getCandName(), candidateProfile.getPresentAddress(), reference, this.getCustomDateFromDateFormate(candidate.getCandProfileApproveDate().toString()), candidate.getDesignation().getName(), candidate.getDepartment().getName(), this.getCustomDateFromDateFormate(candidate.getOldDateOfJoin().toString()), this.getCustomDateFromDateFormate(candidate.getDateOfJoin().toString()), candidateProfile.getAlIssuedBy(), candidateProfile.getAlIssuedByDesignation());
		
	
		InputStream jasperStream = null;
		
		List mylist = new ArrayList<>();
		
		mylist.add(extensionLetterBean);
		

		jasperStream = this.getClass().getResourceAsStream("/ExtensionLetter.jasper");
		

		Map<String, Object> params = new HashMap<>();
		Map<String, Object> datasourcemap = new HashMap<>();
		datasourcemap.put("extensionLetterBean", extensionLetterBean);
		jRdataSource = new JRBeanCollectionDataSource(mylist, false);

	
		
		

		params.put("datasource", jRdataSource);
		params.put("textBody", "This has reference to our appointment letter no: "+reference+" dated "+this.getCustomDateFromDateFormate( candidate.getCandProfileApproveDate().toString())+" offering you employment in our organization as ‘"+candidate.getDesignation().getName()+"’in "+candidate.getDepartment().getName()+" Department, effective "+this.getCustomDateFromDateFormate(candidate.getOldDateOfJoin())+". ");
		
		
		// prepare report first for one
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, jRdataSource);
		

		response.setContentType("application/x-pdf");
		response.setHeader("Content-disposition", "inline; filename="+"extension_"+reference+".pdf");
		final OutputStream outStream = response.getOutputStream();
		// JasperViewer.viewReport(jasperPrint, false);
		JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
	
	
	}
	
	@SuppressWarnings("rawtypes")
	public void appLetterForSynergy(HttpServletResponse response, HttpServletRequest request,
			CandidateBeanReportOne candidateBeanReportOne, CandidateBeanReportTwo candidateBeanReportTwo,
			CandidateBeanReportThree candidateBeanReportThree, CandidateBeanReportFour candidateBeanReportFour, Candidate candidate)
			throws JRException, IOException, ParseException{
		JRDataSource jRdataSource = null;
		JRDataSource jRdataSourceSecond = null;
		JRDataSource jRdataSourceThird = null;
		JRDataSource jRdataSourceFour = null;
		
		CandidateProfile candidateProfile = (CandidateProfile) commonService
				.getAnObjectByAnyUniqueColumn("CandidateProfile", "candidate", candidate.getId().toString());
		String reference = "HR/AL/"+candidate.getCompany().getCompanyKeyword()+"/"+candidate.getRefNo();
		String provisionpriod="7.You will be on probation for a period of "+candidateProfile.getProvisionPeriod().toString()+" months from the date of joining, which may be/ may not be extended for further period depending on your performance. ";
		String officeAddress="6.Your initial place of joining will be at:"+candidate.getCompany().getOfficeAddress()+".However, your services are transferable anywhere in Bangladesh in accordance with Company's rules for the time being in force.";
		String yearlyBonusString="12. Bonus as per company rule: On probation 50%, On confirmation "+candidateProfile.getYearlyBonus()+"% on your gross salary. ";
		String comapnyNameFirstBeanUpdate="13.You shall, during your service with us, devote your whole time and attention to the Company's business entrusted to you and you shall not engage yourself directly or indirectly in any business or service other than the Company's business or service and shall not engage in any profession or vocation which competes with activities of our organization or conflicts with your position at "+candidate.getCompany().getName()+"";
	    Date date=java.util.Calendar.getInstance().getTime();  
	    if (Integer.parseInt(candidateProfile.getProvisionPeriod().trim().toString()) <= 0
				&& Integer.parseInt(candidateProfile.getYearlyBonus().trim().toString()) <= 0) {
	    	
	    	comapnyNameFirstBeanUpdate=comapnyNameFirstBeanUpdate.replace("13.You shall,", "11.You shall,");
	    }
	    
	    if (Integer.parseInt(candidateProfile.getProvisionPeriod().trim().toString()) > 0
				&& Integer.parseInt(candidateProfile.getYearlyBonus().trim().toString()) <= 0) {
	    	
	    	comapnyNameFirstBeanUpdate=comapnyNameFirstBeanUpdate.replace("13.You shall,", "12.You shall,");
	    }
	    if (Integer.parseInt(candidateProfile.getProvisionPeriod().trim().toString()) <= 0
				&& Integer.parseInt(candidateProfile.getYearlyBonus().trim().toString()) > 0) {
	    	comapnyNameFirstBeanUpdate=comapnyNameFirstBeanUpdate.replace("13.You shall,", "12.You shall,");
	    	yearlyBonusString=yearlyBonusString.replace("12. Bonus as per company", "11. Bonus as per company");
	    }
	 // param for first one
	 		Date now = new Date();
	 		DateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy");
	 		String refDate = "";
	 		if(candidate.getCandProfileApproveDate() != null && candidate.getCandProfileApproveDate().trim().length() > 0) {
	 			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	 			Date candDate = df.parse(candidate.getCandProfileApproveDate());
	 			refDate = sdf.format(candDate);
	 		} else {
	 			refDate = sdf.format(now);
	 		}
	   // System.out.println(date);  
		String referenceAndCurrentDate="Further to our offer of appointment No:"+reference+" dated "+refDate+", you will be entitled to the following perquisites and allowances particulars. ";
		candidateBeanReportOne = new CandidateBeanReportOne(candidate.getCandName(), candidate.getCandMobileNo(),
				candidate.getDepartment().getName(), candidate.getDesignation().getName(), candidateProfile.getGrade(),
				candidateProfile.getSupervisorName(), candidateProfile.getPresentAddress(),
				officeAddress, candidate.getDateOfJoin(),
				provisionpriod, yearlyBonusString,comapnyNameFirstBeanUpdate);
		candidateBeanReportTwo = new CandidateBeanReportTwo(comapnyNameFirstBeanUpdate);
		candidateBeanReportThree = new CandidateBeanReportThree(candidate.getCandName(),
				candidate.getHrEmployee().getName(),candidateProfile.getAlIssuedBy(),candidateProfile.getAlIssuedByDesignation());
//		candidateBeanReportFour = new CandidateBeanReportFour(candidate.getCandName(),
//				candidate.getHrEmployee().getName(), candidate.getDesignation().getName(),referenceAndCurrentDate,candidateProfile.getGrossSalary());
		candidateBeanReportFour=new CandidateBeanReportFour(candidate.getCandName(), candidate.getHrEmployee().getName(), candidate.getDesignation().getName(), candidateProfile.getGrossSalary(), referenceAndCurrentDate, candidateProfile.getAlIssuedBy(), candidateProfile.getAlIssuedByDesignation());
		candidateBeanReportFour.setBasicSalary(candidateProfile.getBasicSalary());
		candidateBeanReportFour.setHouseRent(candidateProfile.getHouseRent());
		candidateBeanReportFour.setConveyance(candidateProfile.getConveyance());
		candidateBeanReportFour.setMedicalIn(candidateProfile.getMedicalIn());
		
		InputStream jasperStream = null;
		InputStream jasperStreamSecond = null;
		InputStream jasperStreamThird = null;
		InputStream jasperStreamFour = null;
		List mylist = new ArrayList<>();
		List mylistSecond = new ArrayList<>();
		List mylistThird = new ArrayList<>();
		List mylistFour = new ArrayList<>();
		mylist.add(candidateBeanReportOne);
		mylistSecond.add(candidateBeanReportTwo);
		mylistThird.add(candidateBeanReportThree);
		mylistFour.add(candidateBeanReportFour);

		jasperStreamFour = this.getClass().getResourceAsStream("/empjoinFour.jasper");
		if (Integer.parseInt(candidateProfile.getProvisionPeriod().trim().toString()) <= 0
				&& Integer.parseInt(candidateProfile.getYearlyBonus().trim().toString()) <= 0) {
			jasperStream = this.getClass().getResourceAsStream("/empjoinOne_second.jasper");
			jasperStreamSecond = this.getClass().getResourceAsStream("/empjoinTwo_second.jasper");
			jasperStreamThird = this.getClass().getResourceAsStream("/empjoinThree_second.jasper");

		} else if (Integer.parseInt(candidateProfile.getProvisionPeriod().trim().toString()) > 0
				&& Integer.parseInt(candidateProfile.getYearlyBonus().trim().toString()) <= 0) {

			jasperStream = this.getClass().getResourceAsStream("/empjoinOne_three.jasper");
			jasperStreamSecond = this.getClass().getResourceAsStream("/empjoinTwo _three.jasper");
			jasperStreamThird = this.getClass().getResourceAsStream("/empjoinThree_three.jasper");
		} else if (Integer.parseInt(candidateProfile.getProvisionPeriod().trim().toString()) <= 0
				&& Integer.parseInt(candidateProfile.getYearlyBonus().trim().toString()) > 0) {

			jasperStream = this.getClass().getResourceAsStream("/empjoinOneFour.jasper");
			jasperStreamSecond = this.getClass().getResourceAsStream("/empjoinTwo _three.jasper");
			jasperStreamThird = this.getClass().getResourceAsStream("/empjoinThree_three.jasper");
		}

		else {

			jasperStream = this.getClass().getResourceAsStream("/empjoinOne.jasper");
			jasperStreamSecond = this.getClass().getResourceAsStream("/empjoinTwo.jasper");
			jasperStreamThird = this.getClass().getResourceAsStream("/empjoinThree.jasper");
		}

		Map<String, Object> params = new HashMap<>();
		Map<String, Object> datasourcemap = new HashMap<>();
		datasourcemap.put("candidateBeanReportOne", candidateBeanReportOne);
		jRdataSource = new JRBeanCollectionDataSource(mylist, false);

		Map<String, Object> paramsSecond = new HashMap<>();
		Map<String, Object> datasourcemapSecond = new HashMap<>();
		datasourcemapSecond.put("candidateBeanReportTwo", candidateBeanReportTwo);
		jRdataSourceSecond = new JRBeanCollectionDataSource(mylistSecond, false);

		Map<String, Object> paramsThird = new HashMap<>();
		Map<String, Object> datasourcemapThird = new HashMap<>();
		datasourcemapThird.put("candidateBeanReportThree", candidateBeanReportThree);
		jRdataSourceThird = new JRBeanCollectionDataSource(mylistThird, false);

		Map<String, Object> paramsFour = new HashMap<>();
		Map<String, Object> datasourcemapFour = new HashMap<>();
		datasourcemapFour.put("candidateBeanReportFour", candidateBeanReportFour);
		jRdataSourceFour = new JRBeanCollectionDataSource(mylistFour, false);
		
		
		

		params.put("datasource", jRdataSource);
		params.put("cand_id", candidate.getCandId().toString());
		params.put("approved_date", refDate);
		params.put("reference", reference);
		 if (((Integer.parseInt(candidateProfile.getProvisionPeriod().trim().toString()) <= 0
					&& Integer.parseInt(candidateProfile.getYearlyBonus().trim().toString()) <= 0)) ||((Integer.parseInt(candidateProfile.getProvisionPeriod().trim().toString()) <= 0
							&& Integer.parseInt(candidateProfile.getYearlyBonus().trim().toString()) > 0))) {
			 params.put("joiningdate", "7.Your appointment will be effective "+this.getCustomDateFromDateFormate(candidate.getDateOfJoin().trim().toString())+".");
		 }
		 if (Integer.parseInt(candidateProfile.getProvisionPeriod().trim().toString()) > 0
					&& Integer.parseInt(candidateProfile.getYearlyBonus().trim().toString()) <= 0) {
			 params.put("joiningdate", "8.Your appointment will be effective "+this.getCustomDateFromDateFormate(candidate.getDateOfJoin().trim().toString())+".");
		 }
		 if (Integer.parseInt(candidateProfile.getProvisionPeriod().trim().toString()) > 0
					&& Integer.parseInt(candidateProfile.getYearlyBonus().trim().toString()) > 0) {
			 params.put("joiningdate", "8.Your appointment will be effective "+this.getCustomDateFromDateFormate(candidate.getDateOfJoin().trim().toString())+".");
		 }
		 
		// params for second one
		paramsSecond.put("datasource", jRdataSourceSecond);
		paramsSecond.put("cand_id", candidate.getCandId().toString());
		// params for third one
		paramsThird.put("datasource", jRdataSourceThird);
		paramsThird.put("cand_id", candidate.getCandId().toString());
		// params for four one
		paramsFour.put("datasource", jRdataSourceFour);
		paramsFour.put("cand_id", candidate.getCandId().toString());
		paramsFour.put("reference", reference);
	
		paramsFour.put("basicSalary_pr", "Basic ("+candidateProfile.getBasicSalary().intValue()+"%) ");
		paramsFour.put("houseRent_pr", "House Rent ("+candidateProfile.getHouseRent().intValue()+"%)");
		paramsFour.put("medicalIn_pr", "Medical ("+candidateProfile.getMedicalIn().intValue()+"%)");
		paramsFour.put("conveyance_pr", "Conveyance ("+candidateProfile.getConveyance().intValue()+"%)");
		String featurereview = "However, you will be entitled to any future review of your compensation in accordance to the company’s practice. It will be linked to your performance and will be at the discretion of the management.";
		String salary = "You will be paid a fixed consolidated amount of Tk. (" + candidateProfile.getGrossSalary().intValue()
				+ "/-) only per month.";
		if (candidateProfile.getProvidentFund() !=null && candidateProfile.getProvidentFund()>0) {
			featurereview = featurereview + " ( After Successfully Completion of your probation period "
					+ candidateProfile.getProvidentFund().intValue()
					+ " % of your basic salary will be deducted from your salary every month as Provident fund).";
		}
		if (candidateProfile.getSalIncreaseAmt()!=null && candidateProfile.getSalIncreaseAmt()>0) {
			salary=salary+" After completion of your probation period your salary will be ("+candidateProfile.getSalIncreaseAmt().intValue()+"/-).";
		}

		paramsFour.put("feturereview", featurereview);
		paramsFour.put("salary", salary);
		// prepare report first for one
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, jRdataSource);
		// prepare report for second one
		JasperReport jasperReportSecond = (JasperReport) JRLoader.loadObject(jasperStreamSecond);
		JasperPrint jasperPrintSecond = JasperFillManager.fillReport(jasperReportSecond, paramsSecond,
				jRdataSourceSecond);
		// Prepare report for third one
		JasperReport jasperReportThird = (JasperReport) JRLoader.loadObject(jasperStreamThird);
		JasperPrint jasperPrintThird = JasperFillManager.fillReport(jasperReportThird, paramsThird, jRdataSourceThird);

		JasperReport jasperReportFour = (JasperReport) JRLoader.loadObject(jasperStreamFour);
		JasperPrint jasperPrintFour = JasperFillManager.fillReport(jasperReportFour, paramsFour, jRdataSourceFour);
		// merge second page
		List pagesSecond = jasperPrintSecond.getPages();
		for (int j = 0; j < pagesSecond.size(); j++) {
			JRPrintPage objectSecond = (JRPrintPage) pagesSecond.get(j);
			jasperPrint.addPage(objectSecond);

		}
		// merge Third
		List pagesThird = jasperPrintThird.getPages();
		for (int j = 0; j < pagesThird.size(); j++) {
			JRPrintPage objectThree = (JRPrintPage) pagesThird.get(j);
			jasperPrint.addPage(objectThree);

		}
		// merge Third
		List pagesFour = jasperPrintFour.getPages();
		for (int j = 0; j < pagesFour.size(); j++) {
			JRPrintPage objectFour = (JRPrintPage) pagesFour.get(j);
			jasperPrint.addPage(objectFour);

		}

		//JRViewer jrviewer = new JRViewer(jasperPrint);
		// export to PDF
		//response.setContentType("application/x-pdf");
		//response.setHeader("Content-disposition", "inline; filename="+candidate.getCandId()+"_"+reference+".pdf");
		//final OutputStream outStream = response.getOutputStream();		
		//JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
		
		// export to Word
		ServletOutputStream outputStream = null;
		response.setHeader("Content-Disposition", "inline; filename="+candidate.getCandId()+"_"+reference+".docx");
        response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        outputStream = response.getOutputStream();
        JRDocxExporter exporter = new JRDocxExporter();
       
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
       exporter.setParameter(JRExporterParameter.IGNORE_PAGE_MARGINS, Boolean.TRUE); ;
        
        exporter.exportReport();
	}
	private String getMonth(int month) {
		String months[]= {"One","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten","Eleven","Twelve"};
		String monthStr="";
		for (int i = 1; i <=12; i++) {
			if (i==month) {
				monthStr=months[i-1];
				break;
			}
		}
		return monthStr;
		
	}
	
	private String  getCustomDateFromDateFormate(String date) {
		String customDate="";
		String months[]= {"January","February","March","April","May","June","July","August","September","October","Novermber","December"};
		String[] dateUnit=date.split("-");
		customDate=dateUnit[2]+" ";
		for (int i = 1; i <=12; i++) {
			if (Integer.parseInt(dateUnit[1])==i) {
				customDate=customDate +months[i-1]+", "+dateUnit[0];
				break;
			}
		}
		return customDate;
		
	}
	@SuppressWarnings("rawtypes")
	public void appLetterForDCIMCH(HttpServletResponse response, HttpServletRequest request,
			CandidateBeanReportOne candidateBeanReportOne, CandidateBeanReportTwo candidateBeanReportTwo,
			CandidateBeanReportThree candidateBeanReportThree, CandidateBeanReportFour candidateBeanReportFour, Candidate candidate)
			throws JRException, IOException, ParseException{
		JRDataSource jRdataSource = null;
		JRDataSource jRdataSourceSecond = null;
		JRDataSource jRdataSourceThird = null;
		JRDataSource jRdataSourceFour = null;
		
		CandidateProfile candidateProfile = (CandidateProfile) commonService
				.getAnObjectByAnyUniqueColumn("CandidateProfile", "candidate", candidate.getId().toString());
		String dateOfJoining="4.Your appointment will be effective from the date of joining which shall be as soon as possible but not later than "+this.getCustomDateFromDateFormate(candidate.getDateOfJoin().toString())+" failing which this appointment will stand automatically withdrawn.";
		String superVisorName="5.You will report to "+candidateProfile.getSupervisorName()+" or any other person assigned by hospital authority.";
		String provisionPeriod=candidateProfile.getProvisionPeriod();
		if (candidateProfile.getProvisionPeriod() != null && Integer.parseInt(candidateProfile.getProvisionPeriod().trim().toString()) >0) {
			
          provisionPeriod="6.You will be on probation for a period of "+candidateProfile.getProvisionPeriod()+" ("+this.getMonth(Integer.parseInt(candidateProfile.getProvisionPeriod()))+") months from the date of joining, which may be/ may not be extended for further period depending on your performance.";
			
		}
		String reference = "HR/AL/"+candidate.getCompany().getCompanyKeyword()+"/"+candidate.getRefNo();
		// param for first one
		Date now = new Date();
		DateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy");
		String refDate = "";
		if(candidate.getCandProfileApproveDate() != null && candidate.getCandProfileApproveDate().trim().length() > 0) {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date candDate = df.parse(candidate.getCandProfileApproveDate());
			refDate = sdf.format(candDate);
		} else {
			refDate = sdf.format(now);
		}
		String referenceAndCurrentDate="Further to our offer of appointment No:"+reference+" dated "+refDate+", you will be entitled to the following perquisites and allowances particulars. ";
		candidateBeanReportOne = new CandidateBeanReportOne(candidate.getCandName(), candidate.getCandMobileNo(),
				candidate.getDepartment().getName(), candidate.getDesignation().getName(), candidateProfile.getGrade(),
				superVisorName, candidateProfile.getPresentAddress(),candidate.getCompany().getOfficeAddress(),dateOfJoining,
				provisionPeriod, candidateProfile.getYearlyBonus(),candidate.getCompany().getName());
		candidateBeanReportTwo = new CandidateBeanReportTwo(candidate.getCompany().getName());
		candidateBeanReportThree = new CandidateBeanReportThree(candidate.getCandMobileNo(),
				candidate.getHrEmployee().getName(),candidate.getHrEmployee().getDepartment());
		candidateBeanReportThree.setAlIssuedBy(candidateProfile.getAlIssuedBy());
		candidateBeanReportThree.setAlIssuedByDesignation(candidateProfile.getAlIssuedByDesignation());
		candidateBeanReportFour = new CandidateBeanReportFour(candidate.getCandName(),
				candidate.getHrEmployee().getName(), candidate.getDesignation().getName(),
				candidateProfile.getGrossSalary(),candidate.getHrEmployee().getDepartment());
		candidateBeanReportFour.setAlIssuedBy(candidateProfile.getAlIssuedBy());
		candidateBeanReportFour.setAlIssuedByDesignation(candidateProfile.getAlIssuedByDesignation());
		candidateBeanReportFour.setReferenceAndCurrentDate(referenceAndCurrentDate);
		candidateBeanReportFour.setBasicSalary(candidateProfile.getBasicSalary());
		candidateBeanReportFour.setHouseRent(candidateProfile.getHouseRent());
		candidateBeanReportFour.setConveyance(candidateProfile.getConveyance());
		candidateBeanReportFour.setMedicalIn(candidateProfile.getMedicalIn());
		candidateBeanReportFour.setSalaryOthersAmt(candidateProfile.getSalaryOthersAmt());
		InputStream jasperStream = null;
		InputStream jasperStreamSecond = null;
		InputStream jasperStreamThird = null;
		InputStream jasperStreamFour = null;
		List mylist = new ArrayList<>();
		List mylistSecond = new ArrayList<>();
		List mylistThird = new ArrayList<>();
		List mylistFour = new ArrayList<>();
		mylist.add(candidateBeanReportOne);
		mylistSecond.add(candidateBeanReportTwo);
		mylistThird.add(candidateBeanReportThree);
		mylistFour.add(candidateBeanReportFour);

		jasperStreamFour = this.getClass().getResourceAsStream("/empjoinDCMCHFour.jasper");
		if (Integer.parseInt(candidateProfile.getProvisionPeriod().trim().toString()) <= 0) {
			jasperStream = this.getClass().getResourceAsStream("/empjoinDCMCHOne_second.jasper");
			jasperStreamSecond = this.getClass().getResourceAsStream("/empjoinDCMCHTwo_second.jasper");
			jasperStreamThird = this.getClass().getResourceAsStream("/empjoinDCMCHThree_second.jasper");

		} 


		else {

			jasperStream = this.getClass().getResourceAsStream("/empjoinDCMCHOne.jasper");
			jasperStreamSecond = this.getClass().getResourceAsStream("/empjoinDCMCHTwo.jasper");
			jasperStreamThird = this.getClass().getResourceAsStream("/empjoinDCMCHThree.jasper");
		}

		Map<String, Object> params = new HashMap<>();
		Map<String, Object> datasourcemap = new HashMap<>();
		datasourcemap.put("candidateBeanReportOne", candidateBeanReportOne);
		jRdataSource = new JRBeanCollectionDataSource(mylist, false);

		Map<String, Object> paramsSecond = new HashMap<>();
		Map<String, Object> datasourcemapSecond = new HashMap<>();
		datasourcemapSecond.put("candidateBeanReportTwo", candidateBeanReportTwo);
		jRdataSourceSecond = new JRBeanCollectionDataSource(mylistSecond, false);

		Map<String, Object> paramsThird = new HashMap<>();
		Map<String, Object> datasourcemapThird = new HashMap<>();
		datasourcemapThird.put("candidateBeanReportThree", candidateBeanReportThree);
		jRdataSourceThird = new JRBeanCollectionDataSource(mylistThird, false);

		Map<String, Object> paramsFour = new HashMap<>();
		Map<String, Object> datasourcemapFour = new HashMap<>();
		datasourcemapFour.put("candidateBeanReportFour", candidateBeanReportFour);
		jRdataSourceFour = new JRBeanCollectionDataSource(mylistFour, false);
		
		

		params.put("datasource", jRdataSource);
		params.put("cand_id", candidate.getCandId().toString());
		params.put("approved_date", refDate);
		params.put("reference", reference);
		params.put("appLetter", "Appointment Letter as "+candidate.getDesignation().getName()+" "+candidate.getDepartment().getName()+".");
		// params for second one
		paramsSecond.put("datasource", jRdataSourceSecond);
		paramsSecond.put("cand_id", candidate.getCandId().toString());
		// params for third one
		paramsThird.put("datasource", jRdataSourceThird);
		paramsThird.put("cand_id", candidate.getCandId().toString());
		// params for four one
		paramsFour.put("datasource", jRdataSourceFour);
		paramsFour.put("cand_id", candidate.getCandId().toString());
		paramsFour.put("reference", reference);
		paramsFour.put("basicSalary_pr", "Basic ("+candidateProfile.getBasicSalary().intValue()+"%) ");
		paramsFour.put("houseRent_pr", "House Rent ("+candidateProfile.getHouseRent().intValue()+"%)");
		paramsFour.put("medicalIn_pr", "Medical ("+candidateProfile.getMedicalIn().intValue()+"%)");
		paramsFour.put("conveyance_pr", "Conveyance ("+candidateProfile.getConveyance().intValue()+"%)");
		paramsFour.put("others_pr", "Others ("+candidateProfile.getSalaryOthersAmt().intValue()+"%)");
		String featurereview = "However, you will be entitled to any future review of your compensation in accordance to the company’s practice. It will be linked to your performance and will be at the discretion of the management.";
		String salary = "You will be paid a fixed consolidated amount of Tk. (" + candidateProfile.getGrossSalary().intValue()
				+ "/-) only per month.";
		if (candidateProfile.getProvidentFund() !=null && candidateProfile.getProvidentFund()>0) {
			if ( candidateProfile.getProvidentFund()>0.0) {
				featurereview = featurereview + " (After Successfully Completion of your probation period "
						+ candidateProfile.getProvidentFund().intValue()
						+ " % of your basic salary will be deducted from your salary every month as Provident fund).";
			}
			
		}
		if ((candidateProfile.getSalIncreaseAmt()!=null && candidateProfile.getSalIncreaseAmt()>0) &&Integer.parseInt(candidateProfile.getProvisionPeriod())>0) {
			salary=salary+"After completion of your probation period your salary will be ("+candidateProfile.getSalIncreaseAmt().intValue()+"/-)";
		}

		paramsFour.put("feturereview", featurereview);
		paramsFour.put("salary", salary);
		// prepare report first for one
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, jRdataSource);
		// prepare report for second one
		JasperReport jasperReportSecond = (JasperReport) JRLoader.loadObject(jasperStreamSecond);
		JasperPrint jasperPrintSecond = JasperFillManager.fillReport(jasperReportSecond, paramsSecond,
				jRdataSourceSecond);
		// Prepare report for third one
		JasperReport jasperReportThird = (JasperReport) JRLoader.loadObject(jasperStreamThird);
		JasperPrint jasperPrintThird = JasperFillManager.fillReport(jasperReportThird, paramsThird, jRdataSourceThird);

		JasperReport jasperReportFour = (JasperReport) JRLoader.loadObject(jasperStreamFour);
		JasperPrint jasperPrintFour = JasperFillManager.fillReport(jasperReportFour, paramsFour, jRdataSourceFour);
		// merge second page
		List pagesSecond = jasperPrintSecond.getPages();
		for (int j = 0; j < pagesSecond.size(); j++) {
			JRPrintPage objectSecond = (JRPrintPage) pagesSecond.get(j);
			jasperPrint.addPage(objectSecond);

		}
		// merge Third
		List pagesThird = jasperPrintThird.getPages();
		for (int j = 0; j < pagesThird.size(); j++) {
			JRPrintPage objectThree = (JRPrintPage) pagesThird.get(j);
			jasperPrint.addPage(objectThree);

		}
		// merge Third
		List pagesFour = jasperPrintFour.getPages();
		for (int j = 0; j < pagesFour.size(); j++) {
			JRPrintPage objectFour = (JRPrintPage) pagesFour.get(j);
			jasperPrint.addPage(objectFour);

		}

		//JRViewer jrviewer = new JRViewer(jasperPrint);
		//export to pdf
		//response.setContentType("application/x-pdf");
		//response.setHeader("Content-disposition", "inline; filename="+candidate.getCandId()+"_"+reference+".pdf");
		//final OutputStream outStream = response.getOutputStream();
		//JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
		
		// export to Word
		ServletOutputStream outputStream = null;
		response.setHeader("Content-Disposition", "inline; filename="+candidate.getCandId()+"_"+reference+".docx");
        response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        outputStream = response.getOutputStream();
        JRDocxExporter exporter = new JRDocxExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
        exporter.setParameter(JRExporterParameter.IGNORE_PAGE_MARGINS, Boolean.TRUE); ;
        exporter.exportReport();
	}
	
	@RequestMapping(value = { "/candProfileReport" }, method = RequestMethod.GET)
	@ResponseBody
	public void getCandidateProfileReport(HttpServletResponse response, HttpServletRequest request)
			throws JRException, IOException, ParseException {
		JRDataSource jRdataSource = null;
		Candidate candidate = (Candidate) commonService.getAnObjectByAnyUniqueColumn("Candidate", "id",
				request.getParameter("id").toString());
		CandidateProfile candidateProfile=(CandidateProfile) commonService.getAnObjectByAnyUniqueColumn("CandidateProfile", "candidate.id",
				request.getParameter("id").toString());
		
		String reference = "HR/AL/"+candidate.getCompany().getCompanyKeyword()+"/"+candidate.getRefNo();
				
	
		InputStream jasperStream = null;
		
		List<CandidateProfileBean> profileList = this.candidateProfileBeanList(candidateProfile, candidate);
		

		jasperStream = this.getClass().getResourceAsStream("/CandidateProfile.jasper");		

		Map<String, Object> params = new HashMap<>();
		//Map<String, Object> datasourcemap = new HashMap<>();
		//datasourcemap.put("candidateProfile", candidateProfile);
		jRdataSource = new JRBeanCollectionDataSource(profileList, false);

		params.put("datasource", jRdataSource);
		params.put("candTitle", "Employee Profile (" + candidate.getCandId() + ")");
		params.put("candSubTitle", "Company Name : " + candidate.getCompany().getName() + ", Ref. No: " + reference);
				
		// prepare report first for one
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, jRdataSource);
		
		
		//export to pdf
		/*response.setContentType("application/x-pdf");
		response.setHeader("Content-disposition", "inline; filename="+"cand_profile_"+reference+".pdf");
		final OutputStream outStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);*/
		
		//export to xls
		response.addHeader("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		response.setHeader("Content-disposition", "inline; filename="+"cand_profile_"+reference+".xlsx");
	    ServletOutputStream outputStream = response.getOutputStream();
	    net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter exporter = new net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter();
	    exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	    exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,outputStream);
	    exporter.exportReport();
	    outputStream.flush();
	    outputStream.close();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
	}
	
	private List<CandidateProfileBean> candidateProfileBeanList(CandidateProfile candProfile, Candidate candidate) throws ParseException{
		List<CandidateProfileBean> profileList = new ArrayList<CandidateProfileBean>();
		CandidateProfileBean candName = new CandidateProfileBean(1, 1, "Name", candProfile.getName(), "");
		profileList.add(candName);
		
		CandidateProfileBean dob = new CandidateProfileBean(2, 2, "Date Of Birth", this.dateFormat(candProfile.getDateOfBirth()), "DD-MM-YYYY");
		profileList.add(dob);
		
		CandidateProfileBean gender = new CandidateProfileBean(3, 3, "Gender", candProfile.getGender(), "");
		profileList.add(gender);
		
		CandidateProfileBean candName4 = new CandidateProfileBean(4, 4, "Religion", candProfile.getReligion(), "");
		profileList.add(candName4);
		
		CandidateProfileBean candName5 = new CandidateProfileBean(5, 5, "Blood Group", candProfile.getBloodGroup(), "");
		profileList.add(candName5);
		
		CandidateProfileBean candNam6 = new CandidateProfileBean(6, 6, "NID/Birth ID/Passport No", candProfile.getNidNo(), "");
		profileList.add(candNam6);
		
		CandidateProfileBean candName7 = new CandidateProfileBean(7, 7, "Nationality", candProfile.getNationality(), "");
		profileList.add(candName7);
		
		CandidateProfileBean candName8 = new CandidateProfileBean(8, 8, "Father's Name", candProfile.getFatherName(), "");
		profileList.add(candName8);
		
		CandidateProfileBean candName9 = new CandidateProfileBean(9, 9, "Mother's Name", candProfile.getMotherName(), "");
		profileList.add(candName9);
		
		CandidateProfileBean candName10 = new CandidateProfileBean(10, 10, "Permanent Address", candProfile.getPermanentAddress(), "");
		profileList.add(candName10);
		
		CandidateProfileBean candName11 = new CandidateProfileBean(11, 11, "Present Address", candProfile.getPresentAddress(), "");
		profileList.add(candName11);
		
		CandidateProfileBean candName12 = new CandidateProfileBean(12, 12, "Mobile Number", candProfile.getCandMobileNo(), "");
		profileList.add(candName12);
		
		CandidateProfileBean candName13 = new CandidateProfileBean(13, 13, "Email ID", candidate.getCandEmail(), "");
		profileList.add(candName13);
		
		CandidateProfileBean candName14 = new CandidateProfileBean(14, 14, "Emergency Contact Person", candProfile.getEmerContPerson(), "");
		profileList.add(candName14);
		
		CandidateProfileBean candName15 = new CandidateProfileBean(15, 15, "Mobile No", candProfile.getEmerContNo(), "");
		profileList.add(candName15);
		
		CandidateProfileBean candName16 = new CandidateProfileBean(16, 16, "Maritial Status", candProfile.getMaritialStatus(), "");
		profileList.add(candName16);
		
		CandidateProfileBean candName17 = new CandidateProfileBean(17, 17, "Spouse Name", candProfile.getSpouseName(), "");
		profileList.add(candName17);
		
		CandidateProfileBean candName18 = new CandidateProfileBean(18, 18, "Birth Date", this.dateFormat(candProfile.getSpouseDob()), "DD-MM-YYYY");
		profileList.add(candName18);
		
		CandidateProfileBean candName19 = new CandidateProfileBean(19, 19, "Contact No.", candProfile.getSpouseContNo(), "");
		profileList.add(candName19);
		
		CandidateProfileBean candName20 = new CandidateProfileBean(20, 20, "Child Name (1)", candProfile.getChildOneName(), "");
		profileList.add(candName20);
		
		CandidateProfileBean candName21 = new CandidateProfileBean(21, 21, "Gender", candProfile.getChildOneGender(), "");
		profileList.add(candName21);
		
		CandidateProfileBean candName22 = new CandidateProfileBean(22, 22, "Birth Date", this.dateFormat(candProfile.getChildOneDob()), "DD-MM-YYYY");
		profileList.add(candName22);
		
		CandidateProfileBean candName23 = new CandidateProfileBean(23, 23, "Child Name (2)", candProfile.getChildTwoName(), "");
		profileList.add(candName23);
		
		CandidateProfileBean candName24 = new CandidateProfileBean(24, 24, "Gender", candProfile.getChildTwoGender(), "");
		profileList.add(candName24);
		
		CandidateProfileBean candName25 = new CandidateProfileBean(25, 25, "Birth Date", this.dateFormat(candProfile.getChildTwoDob()), "DD-MM-YYYY");
		profileList.add(candName25);
		
		CandidateProfileBean candName26 = new CandidateProfileBean(26, 26, "Previous Organization Name (1)", candProfile.getPrevOrgName1(), "");
		profileList.add(candName26);
		
		CandidateProfileBean candName27 = new CandidateProfileBean(27, 27, "Designation", candProfile.getPrevOrgDesig1(), "");
		profileList.add(candName27);
		
		CandidateProfileBean candName28 = new CandidateProfileBean(28, 28, "From", this.dateFormat(candProfile.getPrevOrgFrom1()), "DD-MM-YYYY");
		profileList.add(candName28);
		
		CandidateProfileBean candName29 = new CandidateProfileBean(29, 29, "To", this.dateFormat(candProfile.getPrevOrgTo1()), "DD-MM-YYYY");
		profileList.add(candName29);
		
		CandidateProfileBean candName30 = new CandidateProfileBean(30, 30, "Address", candProfile.getPrevOrgAdrs1(), "");
		profileList.add(candName30);
		
		CandidateProfileBean candName31 = new CandidateProfileBean(31, 31, "Previous Organization Name (2)", candProfile.getPrevOrgName2(), "");
		profileList.add(candName31);
		
		CandidateProfileBean candName32 = new CandidateProfileBean(32, 32, "Designation", candProfile.getPrevOrgDesig2(), "");
		profileList.add(candName32);
		
		CandidateProfileBean candName33 = new CandidateProfileBean(33, 33, "From", this.dateFormat(candProfile.getPrevOrgFrom2()), "DD-MM-YYYY");
		profileList.add(candName33);
		
		CandidateProfileBean candName34 = new CandidateProfileBean(34, 34, "To", this.dateFormat(candProfile.getPrevOrgTo2()), "DD-MM-YYYY");
		profileList.add(candName34);
		
		CandidateProfileBean candName35 = new CandidateProfileBean(35, 35, "Address", candProfile.getPrevOrgAdrs2(), "");
		profileList.add(candName35);
		
		CandidateProfileBean candName36 = new CandidateProfileBean(36, 36, "Previous Organization Name (3)", candProfile.getPrevOrgName3(), "");
		profileList.add(candName36);
		
		CandidateProfileBean candName37 = new CandidateProfileBean(37, 37, "Designation", candProfile.getPrevOrgDesig3(), "");
		profileList.add(candName37);
		
		CandidateProfileBean candName38 = new CandidateProfileBean(38, 38, "From", this.dateFormat(candProfile.getPrevOrgFrom3()), "DD-MM-YYYY");
		profileList.add(candName38);
		
		CandidateProfileBean candName39 = new CandidateProfileBean(39, 39, "To", this.dateFormat(candProfile.getPrevOrgTo3()), "DD-MM-YYYY");
		profileList.add(candName39);
		
		CandidateProfileBean candName40 = new CandidateProfileBean(40, 40, "Address", candProfile.getPrevOrgAdrs3(), "");
		profileList.add(candName40);
		
		CandidateProfileBean candName41 = new CandidateProfileBean(41, 41, "Degree - 1", candProfile.getCandDegreeName1(), "");
		profileList.add(candName41);
		
		CandidateProfileBean candName42 = new CandidateProfileBean(42, 42, "Discipline/Major", candProfile.getCandDegreeMajor1(), "");
		profileList.add(candName42);
		
		CandidateProfileBean candName43 = new CandidateProfileBean(43, 43, "Institute", candProfile.getCandDegreeInst1(), "");
		profileList.add(candName43);
		
		CandidateProfileBean candName44 = new CandidateProfileBean(44, 44, "Passing Year", candProfile.getCandDegreePasYr1(), "");
		profileList.add(candName44);
		
		CandidateProfileBean candName45 = new CandidateProfileBean(45, 45, "Degree - 2", candProfile.getCandDegreeName2(), "");
		profileList.add(candName45);
		
		CandidateProfileBean candName46 = new CandidateProfileBean(46, 46, "Discipline/Major", candProfile.getCandDegreeMajor2(), "");
		profileList.add(candName46);
		
		CandidateProfileBean candName47 = new CandidateProfileBean(47, 47, "Institute", candProfile.getCandDegreeInst2(), "");
		profileList.add(candName47);
		
		CandidateProfileBean candName48 = new CandidateProfileBean(48, 48, "Passing Year", candProfile.getCandDegreePasYr2(), "");
		profileList.add(candName48);
		
		CandidateProfileBean candName49 = new CandidateProfileBean(49, 49, "Training", candProfile.getCandidateTraining(), "");
		profileList.add(candName49);
		
		CandidateProfileBean candName50 = new CandidateProfileBean(50, 50, "Others Skill", candProfile.getOthersSkill(), "");
		profileList.add(candName50);
		
		CandidateProfileBean candName51 = new CandidateProfileBean(51, 51, "Relative's Name", candProfile.getRelativeName(), "");
		profileList.add(candName51);
		
		CandidateProfileBean candName52 = new CandidateProfileBean(52,52, "Designation", candProfile.getRelativeDesig(), "");
		profileList.add(candName52);
		
		CandidateProfileBean candName53 = new CandidateProfileBean(53, null, "Reference", "", "");
		profileList.add(candName53);
		
		CandidateProfileBean candName54 = new CandidateProfileBean(54, 53, "Name - 1", candProfile.getReferName1(), "");
		profileList.add(candName54);
		
		CandidateProfileBean candName55 = new CandidateProfileBean(55,54, "Ocupation", candProfile.getReferOccuption1(), "");
		profileList.add(candName55);
		
		CandidateProfileBean candName56 = new CandidateProfileBean(56, 55, "Address", candProfile.getReferAddress1(), "");
		profileList.add(candName56);
		
		CandidateProfileBean candName57 = new CandidateProfileBean(57, 56, "Contact No.", candProfile.getReferContactNo1(), "");
		profileList.add(candName57);
		
		CandidateProfileBean candName58 = new CandidateProfileBean(58, 57, "Name - 2", candProfile.getReferName2(), "");
		profileList.add(candName58);
		
		CandidateProfileBean candName59 = new CandidateProfileBean(59, 58, "Ocupation", candProfile.getReferOccuption2(), "");
		profileList.add(candName59);
		
		CandidateProfileBean candName60 = new CandidateProfileBean(60, 59, "Address", candProfile.getReferAddress2(), "");
		profileList.add(candName60);
		
		CandidateProfileBean candName61 = new CandidateProfileBean(61,60, "Contact No.", candProfile.getReferContactNo2(), "");
		profileList.add(candName61);
		
		CandidateProfileBean candName62 = new CandidateProfileBean(62, 61, "Reporting Manager", candProfile.getSupervisorName(), "");
		profileList.add(candName62);
		
		CandidateProfileBean candName63 = new CandidateProfileBean(63, 62, "Manager's Email ID", candProfile.getSupervisorEmail(), "");
		profileList.add(candName63);
		
		CandidateProfileBean candName64 = new CandidateProfileBean(64, 63, "Department", candProfile.getCandidate().getDepartment().getName(), "");
		profileList.add(candName64);
		
		CandidateProfileBean candName65 = new CandidateProfileBean(65, 64, "Designation", candProfile.getCandidate().getDesignation().getName(), "");
		profileList.add(candName65);
		
		CandidateProfileBean candName66 = new CandidateProfileBean(66, 65, "Grade", candProfile.getGrade(), "");
		profileList.add(candName66);
		
		CandidateProfileBean candName67 = new CandidateProfileBean(67, 66, "Employee ID", candProfile.getEmpId(), "");
		profileList.add(candName67);
		
		CandidateProfileBean candName68 = new CandidateProfileBean(68, 67, "Office Mobile No.", candProfile.getOfficeMobileNo(), "");
		profileList.add(candName68);
		
		CandidateProfileBean candName69 = new CandidateProfileBean(69, 68, "Bank Account No", candProfile.getBankAccNo(), "");
		profileList.add(candName69);
		
		CandidateProfileBean candName70 = new CandidateProfileBean(70, 69, "Gross Salary", candProfile.getGrossSalary().toString(), "");
		profileList.add(candName70);
		
		CandidateProfileBean candName71 = new CandidateProfileBean(71, 70, "Basic ("+ candProfile.getBasicSalary() +")", (candProfile.getGrossSalary()/100*candProfile.getBasicSalary())+"", "");
		profileList.add(candName71);
		
		CandidateProfileBean candName72 = new CandidateProfileBean(72, 71, "House Rent("+ candProfile.getHouseRent() +")", (candProfile.getGrossSalary()/100*candProfile.getHouseRent())+"", "");
		profileList.add(candName72);
		
		CandidateProfileBean candName73 = new CandidateProfileBean(73, 72, "Medical("+ candProfile.getMedicalIn() +")", (candProfile.getGrossSalary()/100*candProfile.getMedicalIn())+"", "");
		profileList.add(candName73);
		
		CandidateProfileBean candName74 = new CandidateProfileBean(74, 73, "Conveyance("+ candProfile.getConveyance() +")", (candProfile.getGrossSalary()/100*candProfile.getConveyance())+"", "");
		profileList.add(candName74);
		
		CandidateProfileBean candName75 = new CandidateProfileBean(75, 74, "Others("+ candProfile.getSalaryOthersAmt() +")", (candProfile.getGrossSalary()/100*candProfile.getSalaryOthersAmt())+"", "");
		profileList.add(candName75);
		
		CandidateProfileBean candName76 = new CandidateProfileBean(76, 75, "Provident Fund("+ candProfile.getProvidentFund() +")", (candProfile.getGrossSalary()/100*candProfile.getProvidentFund())+"", "");
		profileList.add(candName76);
		
		CandidateProfileBean candName77 = new CandidateProfileBean(77, 76, "Increase Amount", candProfile.getSalIncreaseAmt().toString(), "");
		profileList.add(candName77);
		
		CandidateProfileBean candName78 = new CandidateProfileBean(78, 77, "Office Location", candProfile.getOfficeLocation(), "");
		profileList.add(candName78);
		
		CandidateProfileBean candName79 = new CandidateProfileBean(79, 78, "Date of Joining", candProfile.getName(), "");
		profileList.add(candName79);
		
		CandidateProfileBean candName80 = new CandidateProfileBean(80, 79, "Bonus", candProfile.getYearlyBonus(), "");
		profileList.add(candName80);
		
		CandidateProfileBean candName81 = new CandidateProfileBean(81, 80, "Probation Period", candProfile.getProvisionPeriod(), "");
		profileList.add(candName81);
		
		/*CandidateProfileBean candName82 = new CandidateProfileBean(82, 81, "Probation Period", candProfile.getName(), "");
		profileList.add(candName82);*/
		
		CandidateProfileBean candName82 = new CandidateProfileBean(82, 81, "Issued By", candProfile.getAlIssuedBy(), "");
		profileList.add(candName82);
		
		CandidateProfileBean candName83 = new CandidateProfileBean(83, 82, "Designation of Issuer", candProfile.getAlIssuedByDesignation(), "");
		profileList.add(candName83);
		
		CandidateProfileBean candName84 = new CandidateProfileBean(84, 83, "Prepared By", candProfile.getAlPreparedBy(), "");
		profileList.add(candName84);
		
		CandidateProfileBean candName85 = new CandidateProfileBean(85, 84, "Remarks", candProfile.getRemarks(), "");
		profileList.add(candName85);
		
		return profileList;
	}
	
	@SuppressWarnings("null")
	private String dateFormat (String date) throws ParseException {
		String responseDate = "";
		if(date != null && date.length() > 0) {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date result = df.parse(date);
			
			DateFormat df1 = new SimpleDateFormat("dd-MM-yyyy");
			responseDate = df1.format(result);
		}		
		return responseDate;
	}
	private double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();
	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    double tmp = Math.floor(value);
	    return (double) tmp / factor;
	} 

}
