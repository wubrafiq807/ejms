<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<!-- Title and other stuffs -->
<!-- <title>Dashboard - Lexicon Merchandise</title> -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="keywords" content="">
<meta name="author" content="">

<link
	href="${pageContext.request.contextPath}/resource/select2/select2.min.css"
	rel="stylesheet" type="text/css">
<script
	src="${pageContext.request.contextPath}/resource/select2/select2.min.js"></script>

<link
	href="${pageContext.request.contextPath}/resource/w2ui/w2ui-1.5.rc1.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resource/w2ui/w2ui-1.5.rc1.css"
	rel="stylesheet">
<script
	src="${pageContext.request.contextPath}/resource/w2ui/w2ui-1.5.rc1.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resource/w2ui/w2ui-1.5.rc1.js"></script> 
	
<!-- <script type="text/javascript" src="http://rawgit.com/vitmalina/w2ui/master/dist/w2ui.min.js"></script>
<link rel="stylesheet" type="text/css" href="http://rawgit.com/vitmalina/w2ui/master/dist/w2ui.min.css" /> -->

<style type="text/css">
.table td.fit, .table th.fit {
	white-space: nowrap;
	width: 1%;
}
</style>


</head>

<body>
	<!-- Page heading start -->
	<input type="hidden" value="${pageContext.request.contextPath}" id="contextPath">
	<div class="page-head">
		<h2 class="pull-left" style="color: #428bca;">Candidate Profile
			Entry From</h2>
		<!-- <div class="clearfix"></div> -->
		
		<div class="pull-right">
			<c:if test="${candidate.status ge 5}">
				<c:if test="${candidate.uploadAppLetter eq 1}">
				<a class="btn btn-warning btn-sm custom-width" title="Download App Letter" target="_blank"
					href='<c:url value="/candidateAppLetter/${candidate.candId}"/>.pdf' 
					style="color: white;"><i class="fa fa-file-pdf-o" style="color:red"></i>&nbsp;&nbsp; Appointment Letter</a>
				</c:if>
				<c:if test="${candidate.uploadExtendLetter eq 1}">
				<a class="btn btn-info btn-sm custom-width" title="Download Extention Letter" target="_blank"
					href='<c:url value="/candidateExtendLetter/${candidate.candId}"/>.pdf' 
					style="color: white;"><i class="fa fa-external-link" style="color:red"></i>&nbsp;&nbsp; Extend Letter</a>
				</c:if>
		 	</c:if>
		 	
		 	<sec:authorize access="!hasRole('ROLE_CANDIDATE')">
		 		<c:if test="${candidate.status ge 4}">
		 			<a class="btn btn-primary btn-sm custom-width" title="Download Candidate Profile" target="_blank"
					href='${pageContext.request.contextPath}/candProfileReport?id=${candidate.id}' 
					style="color: white;"><i class="fa fa-download" aria-hidden="true"></i>&nbsp;&nbsp; Download Profile</a>
		 		</c:if>
		 	
		 	</sec:authorize>
		</div>
		<div class="clearfix"></div>
	</div>
	<!-- Page heading ends -->
	
	<div class="hidden"> 
		<!-- development issue -->
		<input type="hidden" value="${candidate.candId}" id="pcandId">
		<input type="hidden" value="${candidate.id}" id="pcandidateId">
		<input type="hidden" value="${isAdmin}" id="isAdmin">
		
		<!-- general info -->
		<input type="hidden" value="${candidateProfile.name}" id="pname">
		<input type="hidden" value="${candidateProfile.dateOfBirth}" id="pdateOfBirth">
		<input type="hidden" value="${candidateProfile.gender}" id="pgender">
		<input type="hidden" value="${candidateProfile.religion}" id="preligion">
		<input type="hidden" value="${candidateProfile.bloodGroup}" id="pbloodGroup">
		<input type="hidden" value="${candidateProfile.nidNo}" id="pnidNo">
		<input type="hidden" value="${candidateProfile.nationality}" id="pnationality">
		<input type="hidden" value="${candidateProfile.fatherName}" id="pfatherName">
		<input type="hidden" value="${candidateProfile.motherName}" id="pmotherName">
		
		<!-- address -->
		<input type="hidden" value="${candidateProfile.permanentAddress}" id="ppermanentAddress">
		<input type="hidden" value="${candidateProfile.presentAddress}" id="ppresentAddress">
		<input type="hidden" value="${candidateProfile.candMobileNo}" id="pcandMobileNo">
		<input type="hidden" value="${candidateProfile.emailAddress}" id="pemailAddress">
		<input type="hidden" value="${candidateProfile.emerContNo}" id="pemerContNo">
		<input type="hidden" value="${candidateProfile.emerContPerson}" id="pemerContPerson">
		
		<!-- spouse and child -->
		<input type="hidden" value="${candidateProfile.maritialStatus}" id="pmaritialStatus">
		<input type="hidden" value="${candidateProfile.spouseName}" id="pspouseName">
		<input type="hidden" value="${candidateProfile.spouseDob}" id="pspouseDob">
		<input type="hidden" value="${candidateProfile.spouseContNo}" id="pspouseContNo">
		<input type="hidden" value="${candidateProfile.childOneName}" id="pchildOneName">
		<input type="hidden" value="${candidateProfile.childOneGender}" id="pchildOneGender">
		<input type="hidden" value="${candidateProfile.childOneDob}" id="pchildOneDob">
		<input type="hidden" value="${candidateProfile.childTwoName}" id="pchildTwoName">
		<input type="hidden" value="${candidateProfile.childTwoGender}" id="pchildTwoGender">
		<input type="hidden" value="${candidateProfile.childTwoDob}" id="pchildTwoDob">
		
		<!-- prev experience -->
		<input type="hidden" value="${candidateProfile.prevOrgName1}" id="pprevOrgName1">
		<input type="hidden" value="${candidateProfile.prevOrgAdrs1}" id="pprevOrgAdrs1">
		<input type="hidden" value="${candidateProfile.prevOrgFrom1}" id="pprevOrgFrom1">
		<input type="hidden" value="${candidateProfile.prevOrgTo1}" id="pprevOrgTo1">
		<input type="hidden" value="${candidateProfile.prevOrgDesig1}" id="pprevOrgDesig1">
		
		<input type="hidden" value="${candidateProfile.prevOrgName2}" id="pprevOrgName2">
		<input type="hidden" value="${candidateProfile.prevOrgAdrs2}" id="pprevOrgAdrs2">
		<input type="hidden" value="${candidateProfile.prevOrgFrom2}" id="pprevOrgFrom2">
		<input type="hidden" value="${candidateProfile.prevOrgTo2}" id="pprevOrgTo2">
		<input type="hidden" value="${candidateProfile.prevOrgDesig2}" id="pprevOrgDesig2">
		
		<input type="hidden" value="${candidateProfile.prevOrgName3}" id="pprevOrgName3">
		<input type="hidden" value="${candidateProfile.prevOrgAdrs3}" id="pprevOrgAdrs3">
		<input type="hidden" value="${candidateProfile.prevOrgFrom3}" id="pprevOrgFrom3">
		<input type="hidden" value="${candidateProfile.prevOrgTo3}" id="pprevOrgTo3">
		<input type="hidden" value="${candidateProfile.prevOrgDesig3}" id="pprevOrgDesig3">
		
		<!-- edeucation of cand -->
		<input type="hidden" value="${candidateProfile.candDegreeName1}" id="pcandDegreeName1">
		<input type="hidden" value="${candidateProfile.candDegreeMajor1}" id="pcandDegreeMajor1">
		<input type="hidden" value="${candidateProfile.candDegreePasYr1}" id="pcandDegreePasYr1">
		<input type="hidden" value="${candidateProfile.candDegreeInst1}" id="pcandDegreeInst1">
		
		<input type="hidden" value="${candidateProfile.candDegreeName2}" id="pcandDegreeName2">
		<input type="hidden" value="${candidateProfile.candDegreeMajor2}" id="pcandDegreeMajor2">
		<input type="hidden" value="${candidateProfile.candDegreePasYr2}" id="pcandDegreePasYr2">
		<input type="hidden" value="${candidateProfile.candDegreeInst2}" id="pcandDegreeInst2">
		
		<!-- others information of cand -->
		<input type="hidden" value="${candidateProfile.candidateTraining}" id="pcandidateTraining">
		<input type="hidden" value="${candidateProfile.othersSkill}" id="pothersSkill">
		<input type="hidden" value="${candidateProfile.relativeName}" id="prelativeName">
		<input type="hidden" value="${candidateProfile.relativeDesig}" id="prelativeDesig">
		<input type="hidden" value="${candidateProfile.referName1}" id="preferName1">
		<input type="hidden" value="${candidateProfile.referOccuption1}" id="preferOccuption1">
		<input type="hidden" value="${candidateProfile.referAddress1}" id="preferAddress1">
		<input type="hidden" value="${candidateProfile.referContactNo1}" id="preferContactNo1">
		<input type="hidden" value="${candidateProfile.referName2}" id="preferName2">
		<input type="hidden" value="${candidateProfile.referOccuption2}" id="preferOccuption2">
		<input type="hidden" value="${candidateProfile.referAddress2}" id="preferAddress2">
		<input type="hidden" value="${candidateProfile.referContactNo2}" id="preferContactNo2">
		
		<!-- hr department fillup -->
		<sec:authorize access="hasRole('ROLE_ADMIN') or hasRole('ROLE_ADMIN_SYNERGY') or hasRole('ROLE_ADMIN_DCIMCH')">		
		<input type="hidden" value="${candidateProfile.supervisorName}" id="psupervisorName">
		<input type="hidden" value="${candidateProfile.supervisorEmail}" id="psupervisorEmail">
		<input type="hidden" value="${candidateProfile.department.id}" id="pdepartmentId">
		<input type="hidden" value="${candidateProfile.designation.id}" id="pdesignationId">
		<input type="hidden" value="${candidateProfile.grade}" id="pgrade">
		<input type="hidden" value="${candidateProfile.empId}" id="pempId">
		<input type="hidden" value="${candidateProfile.officeMobileNo}" id="pofficeMobileNo">
		<input type="hidden" value="${candidateProfile.bankAccNo}" id="pbankAccNo">
		<input type="hidden" value="${candidateProfile.grossSalary}" id="pgrossSalary">
		<input type="hidden" value="${candidateProfile.basicSalary}" id="pbasicSalary">
		<input type="hidden" value="${candidateProfile.houseRent}" id="phouseRent">
		<input type="hidden" value="${candidateProfile.medicalIn}" id="pmedicalIn">
		<input type="hidden" value="${candidateProfile.conveyance}" id="pconveyance">
		<input type="hidden" value="${candidateProfile.providentFund}" id="pprovidentFund">
		<input type="hidden" value="${candidateProfile.officeLocation}" id="pofficeLocation">
		<input type="hidden" value="${candidateProfile.yearlyBonus}" id="pyearlyBonus">
		<input type="hidden" value="${candidateProfile.provisionPeriod}" id="pprovisionPeriod">
		<input type="hidden" value="${candidateProfile.salIncreaseAmt}" id="psalIncreaseAmt">
		<input type="hidden" value="${candidateProfile.salaryOthersAmt}" id="psalaryOthersAmt">
		<input type="hidden" value="${candidateProfile.remarks}" id="premarks">
		<input type="hidden" value="${candidateProfile.alPreparedBy}" id="palPreparedBy">
		
		<input type="hidden" value="${candidateProfile.alIssuedBy}" id="palIssuedBy">
		<input type="hidden" value="${candidateProfile.alIssuedByDesignation}" id="palIssuedByDesignation">
		 </sec:authorize>
	</div>

	<!-- Matter -->

	<!--   <div class="matter"> -->
	<div class="container">

		<div class="row">

			<div class="col-md-12">


				<div class="widget wgreen">

					<div class="widget-head">
						<div class="pull-left">
							<h2 class="btn btn-info"> Candidate Name : ${candidate.candName}, Candidate ID : ${candidate.candId}, Date of Joining : ${candidate.dateOfJoin}  </h2>
							<c:if test="${!empty message}">
								<p class="red">${message}</p>
							</c:if>
						</div> 
						
						<div class="widget-icons pull-right">
						
							<a href="#" class="wminimize"><i class="fa fa-chevron-up"></i></a>
							<a href="#" class="wclose"><i class="fa fa-times"></i></a>
						</div>
						<div class="clearfix"></div>
					</div>

					<div class="widget-content">
						<div class="padd">
							<%-- <c:if test="${candidate.status eq 14}">
							<c:if test="${candidate.uploadResignLetter eq '0' or candidate.uploadOthersLetter eq '0'}">
								<div>
							 		<form:form cssClass="form-horizontal" method="POST"
									id="candidateForm" class="form-horizontal"
									modelAttribute="candidateProfileForm" enctype="multipart/form-data"
									action="${pageContext.request.contextPath}/saveCandidateByCandidate">
									<div class="row">
										<div class="col-lg-12">
											<div class="col-md-10">
											
												<div class="hidden">
													<form:input id="id" path="id" value="${candidate.id}"
														class="form-control" readonly="true" />
												</div>
											
												<div class="form-group">
												
												</div>
												<c:if test="${candidate.uploadResignLetter eq '1'}">
													<input type="file" name="resignLetter" id="resignLetter" class="form-control hidden" />
												</c:if>
												
												<c:if test="${candidate.uploadOthersLetter eq '1'}">
													<input type="file" name="othersDoc" id="othersDoc" class="form-control hidden" />
												</c:if>
												
												<c:if test="${candidate.uploadResignLetter eq '0'}">
													<div class="form-group">
														<!-- <h1 class="col-xs-offset-2">Status upon Resign letter submission box:</h1> -->
														<h4 class="col-xs-offset-2">You have to submit your resign letter within 3 days.</h4>
														<label class="col-lg-2 control-label">Upload Resign Letter:</label>
														<div class="col-lg-5">
															<input type="file" name="resignLetter" id="resignLetter" class="form-control" />
														</div>
													</div>
												</c:if>
												
												<c:if test="${candidate.uploadOthersLetter eq '0'}">
													<div class="form-group">
														<!-- <h1 class="col-xs-offset-2">Status upon others document submission box:</h1> -->
														<h4 class="col-xs-offset-2">You can upload your educational certificates and other documents.</h4>
														<h4 class="col-xs-offset-2"> <b> N:B </b> For uploading more than one file please zip the file.</h4>
														<label class="col-lg-2 control-label">Upload Others Doc(s):</label>
														<div class="col-lg-5">
															<input type="file" name="othersDoc" id="othersDoc" class="form-control" />
														</div>
													</div>
												</c:if>
												
												<div class="form-group">
													<div class="col-lg-offset-2 col-lg-1 col-xs-4" id="">
														<button type="submit" name="submit_btn" value="save"
															class="btn btn-sm btn-primary req-save-update-btn">Upload</button>
													</div>
												</div>
											
											</div>
										</div>
									</div>									
								</form:form>
								</div>
							</c:if>
							</c:if> --%>
							<br />
							<sec:authorize access="hasRole('ROLE_CANDIDATE')">
								<c:if test="${candidate.status eq 3}"> 
									<div class="center">						
										<p class="btn btn-primary btn-sm candidate_profile_submit"> Submit </p>
									</div>
								</c:if>
								<c:if test="${candidate.status eq 4}"> 
									<div class="center">						
										<p class="btn-success"> Profile Submitted Successfully.</p>
									</div>
								</c:if>
								<c:if test="${candidate.status eq 5}"> 
									<div class="center">						
										<p class="btn-success"> Appointment Letter Accepted by You &amp; you are Joining.</p>
									</div>
								</c:if>
								<c:if test="${candidate.status eq 16}"> 
									<div class="center">						
										<p class="btn-success"> You are Joined.</p>
									</div>
								</c:if>
								<c:if test="${candidate.status eq 15}"> 
									<div class="center">						
										<p class="btn-success"> Appointment Letter Rejected by You.</p>
									</div>
								</c:if>
								<c:if test="${candidate.status eq 8}"> 
									<div class="center">						
										<p class="btn-success"> First Feedback Submitted Successfully.</p>
									</div>
								</c:if>
								<c:if test="${candidate.status eq 10}"> 
									<div class="center">						
										<p class="btn-success"> Second Feedback Submitted Successfully.</p>
									</div>
								</c:if>
								
								<c:if test="${candidate.status eq 12}"> 
									<div class="center">						
										<p class="btn-success"> Final Feedback Submitted Successfully.</p>
									</div>
								</c:if>
								
								<c:if test="${candidate.status eq 14}"> 
									<div class="center">						
										<p class="btn-success"> Ready to Join</p>
									</div>
								</c:if>
							</sec:authorize>
							<!-- Form starts.  -->
							<div id="form" style="width: 100%;">
								<div class="w2ui-page page-0">
								
									<div class="w2ui-field">
										<label>Name:</label>
										<div>
											<input name="name" id="name" type="text" maxlength="100"
												size="60" style="width: 100%"/>
										</div>
									</div>
									<div class="w2ui-field">
										<label title="format: yyyy-mm-dd">Date of Birth:</label>
										<div>
											<input name="dateOfBirth" id="dateOfBirth" type="eu-date" maxlength="10"
												size="60"  style="width: 100%" title="format: yyyy-mm-dd"  class="form-control"/>
										</div>
									</div>
									<div class="w2ui-field">
										<label>Gender:</label>
										<div>
											<input name="gender" id="gender" type="gender_list" maxlength="10"
												size="60"  style="width: 100%"/>
											
										</div>
									</div>
									<div class="w2ui-field">
										<label>Religion:</label>
										<div>
											<input name="religion" id="religion" type="religion_list" maxlength="15"
												size="60"  style="width: 100%"/>
										</div>
									</div>
									<div class="w2ui-field">
										<label>Blood Group:</label>
										<div>
											<input name="bloodGroup" type="blood_group_list" id="bloodGroup" maxlength="5" size="60"  style="width: 100%"/>
										</div>
									</div>
									<div class="w2ui-field">
										<label>NID/Birth/PP No.:</label>
										<div>
											<input name="nidNo" id="nidNo" type="int" maxlength="20" size="60"  style="width: 100%"/>
										</div>
									</div>
									<div class="w2ui-field">
										<label>Nationality:</label>
										<div>
											<input name="nationality" id="nationality" type="text" maxlength="50" size="60" style="width: 100%"/>
										</div>
									</div>
									<div class="w2ui-field">
										<label>Father's Name:</label>
										<div>
											<input name="fatherName" id="fatherName" type="text" maxlength="100" size="60" style="width: 100%"/>
										</div>
									</div>
									<div class="w2ui-field">
										<label>Mother's Name:</label>
										<div>
											<input name="motherName" id="motherName" type="text" maxlength="100" size="60" style="width: 100%"/>
										</div>
									</div>
									
								</div>
								<div class="w2ui-page page-1">
									<div class="w2ui-field">
										<label>Present Address:</label>
										<div>
											<textarea name="permanentAddress" type="text" id="permanentAddress"
												style="width: 100%; height: 80px; resize: none" maxlength="255"></textarea>
										</div>
									</div>
									<div class="w2ui-field">
										<label>Perma. Address:</label>
										<div>
											<textarea name="presentAddress" type="text" id="presentAddress"
												style="width: 100%; height: 80px; resize: none" maxlength="255"></textarea>
										</div>
									</div>
									<div class="w2ui-field">
										<label>Mobile Number:</label>
										<div>
											<input name="candMobileNo" id="candMobileNo" type="text" maxlength="11" style="width: 100%" />
										</div>
									</div>
									<div class="w2ui-field">
										<label>Email ID:</label>
										<div>
											<input name="emailAddress" id="emailAddress" type="email" maxlength="50" size="60" style="width: 100%"/>
										</div>
									</div>
									<div class="w2ui-field">
										<label title="Emergency Contact Person">Emer. Cont. Person:</label>
										<div>
											<input name="emerContPerson" id="emerContPerson" type="text" maxlength="100" size="60" style="width: 100%"/>
										</div>
									</div>
									<div class="w2ui-field">
										<label title="Emergency Mobile No.">Emer. Mobile No.:</label>
										<div>
											<input name="emerContNo" id="emerContNo" type="text" maxlength="11" size="60" style="width: 100%"/>
										</div>
									</div>
								</div>
								<div class="w2ui-page page-2">
									<div class="w2ui-field">
										<label>Maritial Status*:</label>
										<div>
											<input name="maritialStatus" id="maritialStatus" type="maritialStatus" maxlength="100" size="60" style="width: 100%"/>
										</div>
									</div>
									<div class="w2ui-field">
										<label>Spouse Name:</label>
										<div>
											<input name="spouseName" id="spouseName" type="text" maxlength="100" size="60" style="width: 100%"/>
										</div>
									</div>
									<div class="w2ui-field">
										<label title="format: yyyy-mm-dd">Birth Date:</label>
										<div>
											<input  class="form-control" name="spouseDob" id="spouseDob" type="eu-date" maxlength="10" size="60" style="width: 100%" title="format: yyyy-mm-dd"/>
										</div>
									</div>
									<div class="w2ui-field">
										<label>Contact No.:</label>
										<div>
											<input name="spouseContNo" id="spouseContNo" type="text" maxlength="100" size="60" style="width: 100%"/>
										</div>
									</div>
									<div class="w2ui-field">
										<label>Child Name(1):</label>
										<div>
											<input name="childOneName" id="childOneName" type="text" maxlength="100" size="60" style="width: 100%"/>
										</div>
									</div>
									<div class="w2ui-field">
										<label>Gender:</label>
										<div>
											<!-- <input name="childOneGender" id="childOneGender" type="text" maxlength="100" size="60" style="width: 100%"/> -->
											<input name="childOneGender" id="childOneGender" type="gender_list" maxlength="10"
												size="60"  style="width: 100%"/>
										</div>
									</div>
									<div class="w2ui-field">
										<label title="format: yyyy-mm-dd">Birth Date:</label>
										<div>
											<input class="form-control" name="childOneDob" id="childOneDob" type="eu-date" maxlength="10" size="60" style="width: 100%" title="format: yyyy-mm-dd"/>
										</div>
									</div>
									<div class="w2ui-field">
										<label>Child Name(2):</label>
										<div>
											<input name="childTwoName" id="childTwoName" type="text" maxlength="100" size="60" style="width: 100%"/>
										</div>
									</div>
									<div class="w2ui-field">
										<label>Gender:</label>
										<div>
											<!-- <input name="childTwoGender" id="childTwoGender" type="text" maxlength="100" size="60" style="width: 100%"/> -->
											<input name="childTwoGender" id="childTwoGender" type="gender_list" maxlength="10"
												size="60"  style="width: 100%"/>
										</div>
									</div>
									<div class="w2ui-field">
										<label title="format: yyyy-mm-dd">Birth Date:</label>
										<div>
											<input class="form-control" name="childTwoDob" id="childTwoDob" type="eu-date" maxlength="10" size="60" style="width: 100%" title="format: yyyy-mm-dd"/>
										</div>
									</div>
								</div>
								
								<div class="w2ui-page page-3">
									<div class="w2ui-field">
										<label>Name(1):</label>
										<div>
											<input name="prevOrgName1" id="prevOrgName1" type="text" maxlength="100" size="60" style="width: 100%"/>
										</div>
									</div>
									<div class="w2ui-field">
										<label>Designation:</label>
										<div> 
											<input name="prevOrgDesig1" id="prevOrgDesig1" type="text" maxlength="100" size="60" style="width: 100%"/>
										</div>
									</div>
									<div class="w2ui-field">
										<label title="format: yyyy-mm-dd">From:</label>
										<div>
											<input class="form-control" name="prevOrgFrom1" id="prevOrgFrom1" type="eu-date1" maxlength="10" size="60" style="width: 100%" title="format: yyyy-mm-dd"/>
										</div>
									</div>
									<div class="w2ui-field">
										<label title="format: yyyy-mm-dd">To:</label>
										<div>
											<input class="form-control" name="prevOrgTo1" id="prevOrgTo1" type="eu-date2" maxlength="10" size="60" style="width: 100%" title="format: yyyy-mm-dd"/>
										</div>
									</div>
									<div class="w2ui-field">
										<label>Address:</label>
										<div>
											<input name="prevOrgAdrs1" id="prevOrgAdrs1" type="text" maxlength="100" size="60" style="width: 100%"/>
										</div>
									</div>
									
									<div class="w2ui-field">
										<label>Name(2):</label>
										<div>
											<input name="prevOrgName2" id="prevOrgName2" type="text" maxlength="100" size="60" style="width: 100%"/>
										</div>
									</div>
									<div class="w2ui-field">
										<label>Designation:</label>
										<div>
											<input name="prevOrgDesig2" id="prevOrgDesig2" type="text" maxlength="100" size="60" style="width: 100%"/>
										</div>
									</div>
									<div class="w2ui-field">
										<label title="format: yyyy-mm-dd">From:</label>
										<div>
											<input class="form-control" name="prevOrgFrom2" id="prevOrgFrom2" type="eu-date3" maxlength="10" size="60" style="width: 100%" title="format: yyyy-mm-dd"/>
										</div>
									</div>
									<div class="w2ui-field">
										<label title="format: yyyy-mm-dd">To:</label>
										<div>
											<input class="form-control" name="prevOrgTo2" id="prevOrgTo2" type="eu-date4" maxlength="10" size="60" style="width: 100%" title="format: yyyy-mm-dd"/>
										</div>
									</div>
									<div class="w2ui-field">
										<label>Address:</label>
										<div>
											<input name="prevOrgAdrs2" id="prevOrgAdrs2" type="text" maxlength="100" size="60" style="width: 100%"/>
										</div>
									</div>
									
									<div class="w2ui-field">
										<label>Name(3):</label>
										<div>
											<input name="prevOrgName3" id="prevOrgName3" type="text" maxlength="100" size="60" style="width: 100%"/>
										</div>
									</div>
									<div class="w2ui-field">
										<label>Designation:</label>
										<div>
											<input name="prevOrgDesig3" id="prevOrgDesig3" type="text" maxlength="100" size="60" style="width: 100%"/>
										</div>
									</div>
									<div class="w2ui-field">
										<label title="format: yyyy-mm-dd">From:</label>
										<div>
											<input class="form-control" name="prevOrgFrom3" id="prevOrgFrom3" type="eu-date5" maxlength="10" size="60" style="width: 100%" title="format: yyyy-mm-dd"/>
										</div>
									</div>
									<div class="w2ui-field">
										<label title="format: yyyy-mm-dd">To:</label>
										<div>
											<input class="form-control" name="prevOrgTo3" id="prevOrgTo3" type="eu-date6" maxlength="10" size="60" style="width: 100%" title="format: yyyy-mm-dd"/>
										</div>
									</div>
									<div class="w2ui-field">
										<label>Address:</label>
										<div>
											<input name="prevOrgAdrs3" id="prevOrgAdrs3" type="text" maxlength="100" size="60" style="width: 100%"/>
										</div>
									</div>
								</div>
								
								<div class="w2ui-page page-4">
									<div class="w2ui-field">
										<label>Degree(1):</label>
										<div> 
											<input name="candDegreeName1" id="candDegreeName1" type="text" maxlength="100" size="60" style="width: 100%"/>
										</div>
									</div>
									<div class="w2ui-field">
										<label>Desciline/Major:</label>
										<div>
											<input name="candDegreeMajor1" id="candDegreeMajor1" type="text" maxlength="100" size="60" style="width: 100%"/>
										</div>
									</div>
									<div class="w2ui-field">
										<label>Institute:</label>
										<div>
											<input name="candDegreeInst1" id="candDegreeInst1" type="text" maxlength="100" size="60" style="width: 100%"/>
										</div>
									</div>
									<div class="w2ui-field">
										<label>Passing Year:</label>
										<div>
											<input name="candDegreePasYr1" id="candDegreePasYr1" type="text" maxlength="4" size="60" style="width: 100%"/>
										</div>
									</div>
									
									<div class="w2ui-field">
										<label>Degree(2):</label>
										<div>
											<input name="candDegreeName2" id="candDegreeName2" type="text" maxlength="100" size="60" style="width: 100%"/>
										</div>
									</div>
									<div class="w2ui-field">
										<label>Desciline/Major:</label>
										<div>
											<input name="candDegreeMajor2" id="candDegreeMajor2" type="text" maxlength="100" size="60" style="width: 100%"/>
										</div>
									</div>
									<div class="w2ui-field">
										<label>Institute:</label>
										<div>
											<input name="candDegreeInst2" id="candDegreeInst2" type="text" maxlength="100" size="60" style="width: 100%"/>
										</div>
									</div>
									<div class="w2ui-field">
										<label>Passing Year:</label>
										<div>
											<input name="candDegreePasYr2" id="candDegreePasYr2" type="int" maxlength="4"  size="60" style="width: 100%"/>
										</div>
									</div>
									
								</div>
								
								<div class="w2ui-page page-5">								
									<div class="w2ui-field">
										<label>Training:</label>
										<div>
											<textarea name="candidateTraining" type="text" id="candidateTraining"
												style="width: 100%; height: 80px; resize: none" maxlength="255"></textarea>
										</div>
									</div>
									<div class="w2ui-field">
										<label>Others Skill:</label>
										<div>
											<textarea name="othersSkill" type="text" id="othersSkill"
												style="width: 100%; height: 80px; resize: none" maxlength="255"></textarea>
										</div>
									</div>									
									
									<div class="w2ui-field">
										<label title="Name of Relative in the company">Relative Name:</label>
										<div>
											<input name="relativeName" id="relativeName" type="text" maxlength="100"  size="60" style="width: 100%" title="Name of Relative in the company"/>
										</div>
									</div>	
									
									<div class="w2ui-field">
										<label title="Designation of Relative in the company">Relative's Desig.:</label>
										<div>
											<input name="relativeDesig" id="relativeDesig" type="text" maxlength="100"  size="60" style="width: 100%" title="Designation of Relative in the company"/>
										</div>
									</div>	
									
									<div class="w2ui-field">
										<label>References:</label>	
										<div></div>					
									</div>
									
									<div class="w2ui-field">
										<label title="Name of Refrence (1)">Name(1):</label>
										<div>
											<input name="referName1" id="referName1" type="text" maxlength="100"  size="60" style="width: 100%" title="Name of Refrence (1)"/>
										</div>
									</div>
									<div class="w2ui-field">
										<label title="Occupation of Refrence (1)">Occupation(1):</label>
										<div>
											<input name="referOccuption1" id="referOccuption1" type="text" maxlength="100"  size="60" style="width: 100%" title="Occupation of Refrence (1)"/>
										</div>
									</div>
									
									<div class="w2ui-field">
										<label title="Address of Refrence (1)">Address(1):</label>
										<div>
											<input name="referAddress1" id="referAddress1" type="text" maxlength="100"  size="60" style="width: 100%" title="Name of Address (1)"/>
										</div>
									</div>
									
									<div class="w2ui-field">
										<label title="Contact No of Refrence (1)">Contact(1):</label>
										<div>
											<input name="referContactNo1" id="referContactNo1" type="text" maxlength="100"  size="60" style="width: 100%" title="Contact No of Refrence (1)"/>
										</div>
									</div>
									
									<div class="w2ui-field">
										<label title="Name of Refrence (2)">Name(2):</label>
										<div>
											<input name="referName2" id="referName2" type="text" maxlength="100"  size="60" style="width: 100%" title="Name of Refrence (2)"/>
										</div>
									</div>
									<div class="w2ui-field">
										<label title="Occupation of Refrence (2)">Occupation(2):</label>
										<div>
											<input name="referOccuption2" id="referOccuption2" type="text" maxlength="100"  size="60" style="width: 100%" title="Occupation of Refrence (2)"/>
										</div>
									</div>
									
									<div class="w2ui-field">
										<label title="Address of Refrence (2)">Address(2):</label>
										<div>
											<input name="referAddress2" id="referAddress2" type="text" maxlength="100"  size="60" style="width: 100%" title="Name of Address (2)"/>
										</div>
									</div>
									
									<div class="w2ui-field">
										<label title="Contact No of Refrence (2)">Contact(2):</label>
										<div>
											<input name="referContactNo2" id="referContactNo2" type="text" maxlength="100"  size="60" style="width: 100%" title="Contact No of Refrence (2)"/>
										</div>
									</div>
														
								</div>
								
								<sec:authorize access="hasRole('ROLE_ADMIN') or hasRole('ROLE_ADMIN_SYNERGY') or hasRole('ROLE_ADMIN_DCIMCH')">
								
								<div class="w2ui-page page-6">
									<div class="w2ui-field">
										<label>Report. Manager:</label>
										<div>
											<input name="supervisorName" id="supervisorName" type="text" maxlength="100"  size="60" style="width: 100%"/>
										</div>
									</div>
									<div class="w2ui-field">
										<label>Manager's Email:</label>
										<div>
											<input name="supervisorEmail" id="supervisorEmail" type="text" maxlength="50"  size="60" style="width: 100%"/>
										</div>
									</div>
									<div class="w2ui-field hidden">
										<label>Department:</label>
										<div>
											<input name="departmentId" id="departmentId" type="hidden" maxlength="3"  size="60" style="width: 100%"/>
										</div>
									</div>
									<div class="w2ui-field hidden">
										<label>Designation:</label>
										<div>
											<input name="designationId" id="designationId" type="hidden" maxlength="3"  size="60" style="width: 100%"/>
										</div>
									</div>
									<div class="w2ui-field">
										<label>Grade:</label>
										<div>
											<input name="grade" id="grade" type="grade" maxlength="10"  size="60" style="width: 100%"/>
										</div>
									</div>
									<div class="w2ui-field">
										<label>Employee ID:</label>
										<div>
											<input name="empId" id="empId" type="text" maxlength="10"  size="60" style="width: 100%"/>
										</div>
									</div>
									<div class="w2ui-field">
										<label>Office Mobile No.:</label>
										<div>
											<input name="officeMobileNo" id="officeMobileNo" type="text" maxlength="15"  size="60" style="width: 100%"/>
										</div>
									</div>
									<div class="w2ui-field">
										<label>Bank Acc.No.:</label>
										<div>
											<input name="bankAccNo" id="bankAccNo" type="text" maxlength="35"  size="60" style="width: 100%"/>
										</div>
									</div>
									<div class="w2ui-field">
										<label>Gross Salary:</label>
										<div>
											<input name="grossSalary" id="grossSalary" type="int" maxlength="7"  size="60" style="width: 100%"/>
										</div>
									</div>
									<div class="w2ui-field">
										<label>Basic(%):</label>
										<div>
											<input name="basicSalary" id="basicSalary" type="int" maxlength="3"  size="60" style="width: 100%"/>
										</div>
									</div>
									<div class="w2ui-field">
										<label>House Rent(%):</label>
										<div> 
											<input name="houseRent" id="houseRent" type="int" maxlength="3"  size="60" style="width: 100%"/>
										</div>
									</div>
									<div class="w2ui-field">
										<label>Medical(%):</label>
										<div>
											<input name="medicalIn" id="medicalIn" type="int" maxlength="3"  size="60" style="width: 100%"/>
										</div>
									</div>
									<div class="w2ui-field">
										<label>Conveyance(%):</label>
										<div>
											<input name="conveyance" id="conveyance" type="int" maxlength="3"  size="60" style="width: 100%"/>
										</div>
									</div>
									
									<div class="w2ui-field">
										<label>Others(%):</label>
										<div>
											<input name="salaryOthersAmt" id="salaryOthersAmt" type="int" maxlength="3"  size="60" style="width: 100%"/>
										</div>
									</div>
									
									
									<div class="w2ui-field">
										<label>PF(%):</label>
										<div>
											<input name="providentFund" id="providentFund" type="int" maxlength="3"  size="60" style="width: 100%"/>
										</div>
									</div>
									
									<div class="w2ui-field">
										<label>Increase Amount:</label>
										<div>
											<input name="salIncreaseAmt" id="salIncreaseAmt" type="int" maxlength="10"  size="60" style="width: 100%"/>
										</div>
									</div>
									
									<div class="w2ui-field">
										<label>Office Location:</label>
										<div>
											<input name="officeLocation" id="officeLocation" type="text" maxlength="100"  size="60" style="width: 100%"/>
										</div>
									</div>
									<div class="w2ui-field">
										<label>Bonus:</label>
										<div>
											<input name="yearlyBonus" id="yearlyBonus" type=text maxlength="50"  size="60" style="width: 100%"/>
										</div>
									</div>
									
									<div class="w2ui-field">
										<label>Probation Period:</label>
										<div>
											<input name="provisionPeriod" id="provisionPeriod" type="text" maxlength="50"  size="60" style="width: 100%"/>
											<input name="candId" id="candId" type="hidden" value="${candidate.candId}"/>
										</div>
									</div>
									
									<div class="w2ui-field">
										<label>Issued By:</label>
										<div>
											<input name="alIssuedBy" id="alIssuedBy" type="text" maxlength="100"  size="60" style="width: 100%"/>
										</div>
									</div>
									
									<div class="w2ui-field">
										<label>Desig. of Issuer:</label>
										<div>
											<input name="alIssuedByDesignation" id="alIssuedByDesignation" type="text" maxlength="100"  size="60" style="width: 100%"/>											
										</div>
									</div>
									
									<div class="w2ui-field">
										<label>AL Prepared By:</label>
										<div>
											<input name="alPreparedBy" id="alPreparedBy" type="text" maxlength="100"  size="60" style="width: 100%"/>											
										</div>
									</div>
									
									
									
									<div class="w2ui-field">
										<label>Remarks:</label>
										<div>
											<textarea name="remarks" type="text" id="remarks"
												style="width: 100%; height: 80px; resize: none"  maxlength="255"  size="60" style="width: 100%"></textarea>
										</div>
									</div>
									
								</div>
								</sec:authorize>
								<sec:authorize access="hasRole('ROLE_CANDIDATE')">
								<c:if test="${candidate.status eq 3 || candidate.status eq 4}"> 
									<div class="w2ui-buttons">									
										<button class="w2ui-btn" name="save">Save</button>
									</div>
								</c:if>
								</sec:authorize>
								
								<sec:authorize access="hasRole('ROLE_ADMIN') or hasRole('ROLE_ADMIN_SYNERGY') or hasRole('ROLE_ADMIN_DCIMCH')">
								<c:if test="${candidate.status eq 3 || candidate.status eq 4 || candidate.status eq 5 || candidate.status eq 15}"> 
								<div class="w2ui-buttons">									
									<button class="w2ui-btn" name="save">Save</button>
								</div>
								</c:if>
								</sec:authorize>
							</div>
						</div>
						
						<sec:authorize access="hasRole('ROLE_CANDIDATE')">
							<c:if test="${candidate.status eq 3}"> 
								<div class="center">						
									<p class="btn btn-primary btn-sm candidate_profile_submit"> Submit </p>
								</div>
							</c:if>
						</sec:authorize>

					</div>
					<div class="widget-foot">
						<!-- Footer goes here -->
					</div>
				</div>

				<!-- Table -->

			</div>

		</div>

	</div>

	<div class="clearfix"></div>

	<script type="text/javascript">
	var saveCPURL = $('#contextPath').val()+'/saveCandidateProfile';
	
	var tabs = [], fields = [];
	
	if($('#isAdmin').val() == 'true'){
		tabs = [
	        { id: 'tab1', caption: 'General Info.' },
	        { id: 'tab2', caption: 'Address'},
	        { id: 'tab3', caption: 'Spouse & Child' },
	        { id: 'tab4', caption: 'Previous Organization' },
	        { id: 'tab5', caption: 'Educational Info' },
	        { id: 'tab6', caption: 'Others' },
	        { id: 'tab7', caption: 'HR Data' }  
	    ];
	} else {
		tabs = [
	        { id: 'tab1', caption: 'General Info.' },
	        { id: 'tab2', caption: 'Address'},
	        { id: 'tab3', caption: 'Spouse & Child' },
	        { id: 'tab4', caption: 'Previous Organization' },
	        { id: 'tab5', caption: 'Educational Info' },
	        { id: 'tab6', caption: 'Others' }
	    ];
	}
	
	if($('#isAdmin').val() == 'true'){
		fields = [
            { field: 'name', type: 'text', required: true },
            { field: 'dateOfBirth', type: 'eu-date', required: true}, 
            { field: 'gender',   type: 'text', required: true },
            { field: 'religion', type: 'text', required: true },
            { field: 'bloodGroup', type: 'text', required: true },
            { field: 'nidNo', type: 'int', required: true },
            { field: 'nationality', type: 'text', required: true },
            { field: 'fatherName', type: 'text', required: true },
            { field: 'motherName', type: 'text', required: true },
            
            { field: 'permanentAddress', type: 'text', required: true },
            { field: 'presentAddress', type: 'text', required: true },
            { field: 'candMobileNo', type: 'text', required: true },
            { field: 'emailAddress', disabled:true, type: 'text', required: true },
            { field: 'emerContPerson', type: 'text', required: true },
            { field: 'emerContNo', type: 'text', required: true },
            
            { field: 'maritialStatus', type: 'text', required: true },
            { field: 'spouseName', type: 'text'},
            { field: 'spouseDob', type: 'text' },
            { field: 'spouseContNo', type: 'text'},
            { field: 'childOneName', type: 'text'},
            { field: 'childOneGender', type: 'text'},
            { field: 'childOneDob', type: 'text'},
            { field: 'childTwoName', type: 'text'},
            { field: 'childTwoGender', type: 'text'},
            { field: 'childTwoDob', type: 'text'},	
            
            { field: 'prevOrgName1', type: 'text'},
            { field: 'prevOrgDesig1', type: 'text'},
            { field: 'prevOrgFrom1', type: 'text'},
            { field: 'prevOrgTo1', type: 'text'},
            { field: 'prevOrgAdrs1', type: 'text'},
            { field: 'prevOrgName2', type: 'text'},
            { field: 'prevOrgDesig2', type: 'text'},
            { field: 'prevOrgFrom2', type: 'text'},
            { field: 'prevOrgTo2', type: 'text'},
            { field: 'prevOrgAdrs2', type: 'text'},
            { field: 'prevOrgName3', type: 'text'},
            { field: 'prevOrgDesig3', type: 'text'},
            { field: 'prevOrgFrom3', type: 'text'},
            { field: 'prevOrgTo3', type: 'text'},
            { field: 'prevOrgAdrs3', type: 'text'},
            
            { field: 'candDegreeName1', type: 'text', required: true },
            { field: 'candDegreeMajor1', type: 'text', required: true },
            { field: 'candDegreePasYr1', type: 'int', required: true },
            { field: 'candDegreeInst1', type: 'text', required: true },
            { field: 'candDegreeName2', type: 'text'},
            { field: 'candDegreeMajor2', type: 'text'},
            { field: 'candDegreePasYr2', type: 'int'},
            { field: 'candDegreeInst2', type: 'text'},
            
            { field: 'candidateTraining', type: 'text'},
            { field: 'othersSkill', type: 'text'},
            { field: 'relativeName', type: 'text'},
            { field: 'relativeDesig', type: 'text'},
            { field: 'referName1', type: 'text'},
            { field: 'referOccuption1', type: 'text'},
            { field: 'referAddress1', type: 'text'},
            { field: 'referContactNo1', type: 'text'},
            { field: 'referName2', type: 'text'},
            { field: 'referOccuption2', type: 'text'},
            { field: 'referAddress2', type: 'text'},
            { field: 'referContactNo2', type: 'text'},            
            
            { field: 'supervisorName', type: 'text'},
            { field: 'supervisorEmail', type: 'text'},
            { field: 'designationId', type: 'text'},
            { field: 'departmentId', type: 'text'},
            { field: 'grade', type: 'text'},
            { field: 'empId', disabled:true, type: 'text'},
            { field: 'officeMobileNo', type: 'text'},
            { field: 'bankAccNo', type: 'text'},
            { field: 'grossSalary', type: 'int'},
            { field: 'basicSalary', type: 'int'},
            { field: 'houseRent', type: 'int'},
            { field: 'medicalIn', type: 'int'},
            { field: 'conveyance', type: 'int'},
            { field: 'providentFund', type: 'text'}, 
            { field: 'salIncreaseAmt', type: 'int'},
            { field: 'salaryOthersAmt', type: 'int'},
            { field: 'officeLocation', type: 'text'},
            { field: 'yearlyBonus', type: 'text'},
            { field: 'provisionPeriod', type: 'text' },
            { field: 'alIssuedBy', type: 'text' },
            { field: 'alIssuedByDesignation', type: 'text' },
            { field: 'alPreparedBy', type: 'text' },
            { field: 'remarks', type: 'text' },
            { field: 'candId', type: 'text' }
        ];
	} else {
		fields = [
            { field: 'name', type: 'text', required: true },
            { field: 'dateOfBirth', type: 'eu-date', required: true}, 
            { field: 'gender',   type: 'text', required: true },
            { field: 'religion', type: 'text', required: true },
            { field: 'bloodGroup', type: 'text', required: true },
            { field: 'nidNo', type: 'int', required: true },
            { field: 'nationality', type: 'text', required: true },
            { field: 'fatherName', type: 'text', required: true },
            { field: 'motherName', type: 'text', required: true },
            
            { field: 'permanentAddress', type: 'text', required: true },
            { field: 'presentAddress', type: 'text', required: true },
            { field: 'candMobileNo', type: 'int', required: true },
            { field: 'emailAddress', disabled:true, type: 'text', required: true },
            { field: 'emerContPerson', type: 'text', required: true },
            { field: 'emerContNo', type: 'text', required: true },
            
            { field: 'maritialStatus', type: 'text', required: true },
            { field: 'spouseName', type: 'text'},
            { field: 'spouseDob', type: 'text' },
            { field: 'spouseContNo', type: 'text'},
            { field: 'childOneName', type: 'text'},
            { field: 'childOneGender', type: 'text'},
            { field: 'childOneDob', type: 'text'},
            { field: 'childTwoName', type: 'text'},
            { field: 'childTwoGender', type: 'text'},
            { field: 'childTwoDob', type: 'text'},	
            
            { field: 'prevOrgName1', type: 'text'},
            { field: 'prevOrgDesig1', type: 'text'},
            { field: 'prevOrgFrom1', type: 'text'},
            { field: 'prevOrgTo1', type: 'text'},
            { field: 'prevOrgAdrs1', type: 'text'},
            { field: 'prevOrgName2', type: 'text'},
            { field: 'prevOrgDesig2', type: 'text'},
            { field: 'prevOrgFrom2', type: 'text'},
            { field: 'prevOrgTo2', type: 'text'},
            { field: 'prevOrgAdrs2', type: 'text'},
            { field: 'prevOrgName3', type: 'text'},
            { field: 'prevOrgDesig3', type: 'text'},
            { field: 'prevOrgFrom3', type: 'text'},
            { field: 'prevOrgTo3', type: 'text'},
            { field: 'prevOrgAdrs3', type: 'text'},
            
            { field: 'candDegreeName1', type: 'text', required: true },
            { field: 'candDegreeMajor1', type: 'text', required: true },
            { field: 'candDegreePasYr1', type: 'int', required: true },
            { field: 'candDegreeInst1', type: 'text', required: true },
            { field: 'candDegreeName2', type: 'text'},
            { field: 'candDegreeMajor2', type: 'text'},
            { field: 'candDegreePasYr2', type: 'int'},
            { field: 'candDegreeInst2', type: 'text'},
            
            { field: 'candidateTraining', type: 'text'},
            { field: 'othersSkill', type: 'text'},
            { field: 'relativeName', type: 'text'},
            { field: 'relativeDesig', type: 'text'},
            { field: 'referName1', type: 'text'},
            { field: 'referOccuption1', type: 'text'},
            { field: 'referAddress1', type: 'text'},
            { field: 'referContactNo1', type: 'text'},
            { field: 'referName2', type: 'text'},
            { field: 'referOccuption2', type: 'text'},
            { field: 'referAddress2', type: 'text'},
            { field: 'referContactNo2', type: 'text'}
        ];
	}
	
	$(function () {
	    $('#form').w2form({ 
	        name   : 'candidateProfileForm',
	        fields : fields,
	        tabs: tabs,
	        actions: {
	            reset: function () {	                
	            },
	            save: function () {
	            	
	            	var formData = this.record;
	            	formData.candidateId = $('#pcandidateId').val();
	            	formData.candId = $('#pcandId').val();
	            	
	                $.ajax({
	    			    type : "post",
	    			    url : saveCPURL,
	    			    async : false,
	    			    data : JSON.stringify(formData),
	    			    contentType : "application/json",
	    			    success : function(response) {
	    			    	result = JSON.parse(response);
	    			    	if(result == 'success'){
	    			    		w2alert("Data Save Successfuly.");
	    			    	}else {
	    			    		w2alert("Please Try Again.");
	    			    	}
	    			    	
	    			    },
	    			    error : function() {
	    			    	w2alert("Server Error!");
	    			    }
	    			   });
	            }
	        }
	    });
	    
	 	
	    
	});
	</script>
	
	<script type="text/javascript">
	$(function () {
	    var month = (new Date()).getMonth() + 1;
	    var year  = (new Date()).getFullYear();
	    // US Format
	    $('input[type=eu-date]').w2field('date',  { format: 'yyyy-mm-dd' });
	    
	    $('input[type=eu-date1]').w2field('date', { format: 'yyyy-mm-dd', end: $('input[type=eu-date2]') });
	    $('input[type=eu-date2]').w2field('date', { format: 'yyyy-mm-dd', start: $('input[type=eu-date1]') });
	    
	    $('input[type=eu-date3]').w2field('date', { format: 'yyyy-mm-dd', end: $('input[type=eu-date4]') });
	    $('input[type=eu-date4]').w2field('date', { format: 'yyyy-mm-dd', start: $('input[type=eu-date3]') });
	    
	    $('input[type=eu-date5]').w2field('date', { format: 'yyyy-mm-dd', end: $('input[type=eu-date6]') });
	    $('input[type=eu-date6]').w2field('date', { format: 'yyyy-mm-dd', start: $('input[type=eu-date5]') });
	    
	 // list
	    var gender = ['Male', 'Female', 'Other'];
	    $('input[type=gender_list]').w2field('list', { items: gender });
	    //$('input[type=gender_list]').data('selected', { id: 1, text: 'Select One'}).data('w2field').refresh();
	    
	    var religion = ['Islam', 'Hindu', 'Christan', 'Buddha',  'Other'];	    
	    $('input[type=religion_list]').w2field('list', { items: religion });
	    //$('input[type=religion_list]').data('selected', { id: 1, text: 'Select One'}).data('w2field').refresh();
	    
	    var blood_group_list = ['A+', 'A-', 'B+', 'B-',  'AB+', 'AB-', 'O+', 'O-'];	    
	    $('input[type=blood_group_list]').w2field('list', { items: blood_group_list });
	    //$('input[type=blood_group_list]').data('selected', { id: 0, text: 'Select One'}).data('w2field').refresh();
	    
	    
	    var maritialStatus = ['Married', 'Unmarried', 'Divorced', 'Other'];	    
	    $('input[type=maritialStatus]').w2field('list', { items: maritialStatus });
	    //$('input[type=maritialStatus]').data('selected', { id: 0, text: 'Select One'}).data('w2field').refresh();
	    
	    
	    var grade_list = ['C-1', 'C-2', 'C-3', 'C-4', 'C-5', 'C-6', 'C-7', 'C-8', 'C-9', 'C-10', 'Grade 1', 'Grade 2', 'Grade 3', 'Grade 4', 'Grade 5', 'Grade 6', 'Grade 7', 'Grade 8'];	    
	    $('input[type=grade]').w2field('list', { items: grade_list });
	    //$('input[type=grade]').data('selected', { id: 0, text: 'Select One'}).data('w2field').refresh();
	    
	    //general Info
	    $('#name').val($('#pname').val());
	    w2ui['candidateProfileForm'].record['name'] = $('#pname').val(); 
	    
	    $('#dateOfBirth').val($('#pdateOfBirth').val());
	    w2ui['candidateProfileForm'].record['dateOfBirth'] = $('#pdateOfBirth').val(); 
	    
	    $('#gender').val($('#pgender').val());
	    w2ui['candidateProfileForm'].record['gender'] = $('#pgender').val(); 
	    
	    $('#religion').val($('#preligion').val());
	    w2ui['candidateProfileForm'].record['religion'] = $('#preligion').val();
	    
	    $('#bloodGroup').val($('#pbloodGroup').val());
	    w2ui['candidateProfileForm'].record['bloodGroup'] = $('#pbloodGroup').val();
	    
	    $('#nidNo').val($('#pnidNo').val());
	    w2ui['candidateProfileForm'].record['nidNo'] = $('#pnidNo').val();
	    
	    $('#nationality').val($('#pnationality').val());
	    w2ui['candidateProfileForm'].record['nationality'] = $('#pnationality').val();
	    
	    $('#fatherName').val($('#pfatherName').val());
	    w2ui['candidateProfileForm'].record['fatherName'] = $('#pfatherName').val();
	    
	    $('#motherName').val($('#pmotherName').val());
	    w2ui['candidateProfileForm'].record['motherName'] = $('#pmotherName').val();
	    
	    //address
	    $('#permanentAddress').val($('#ppermanentAddress').val());
	    w2ui['candidateProfileForm'].record['permanentAddress'] = $('#ppermanentAddress').val();
	    
	    $('#presentAddress').val($('#ppresentAddress').val());
	    w2ui['candidateProfileForm'].record['presentAddress'] = $('#ppresentAddress').val();
	    
	    $('#candMobileNo').val($('#pcandMobileNo').val());
	    w2ui['candidateProfileForm'].record['candMobileNo'] = $('#pcandMobileNo').val();
	    
	    $('#emailAddress').val($('#pemailAddress').val());
	    w2ui['candidateProfileForm'].record['emailAddress'] = $('#pemailAddress').val();
	    
	    $('#emerContNo').val($('#pemerContNo').val());
	    w2ui['candidateProfileForm'].record['emerContNo'] = $('#pemerContNo').val();
	    
	    $('#emerContPerson').val($('#pemerContPerson').val());
	    w2ui['candidateProfileForm'].record['emerContPerson'] = $('#pemerContPerson').val();
	    //spouse and child
	    $('#maritialStatus').val($('#pmaritialStatus').val());
	    w2ui['candidateProfileForm'].record['maritialStatus'] = $('#pmaritialStatus').val();
	    
	    $('#spouseName').val($('#pspouseName').val());
	    w2ui['candidateProfileForm'].record['spouseName'] = $('#pspouseName').val();
	    
	    $('#spouseDob').val($('#pspouseDob').val());
	    w2ui['candidateProfileForm'].record['spouseDob'] = $('#pspouseDob').val();
	    
	    $('#spouseContNo').val($('#pspouseContNo').val());
	    w2ui['candidateProfileForm'].record['spouseContNo'] = $('#pspouseContNo').val();
	    
	    $('#childOneName').val($('#pchildOneName').val());
	    w2ui['candidateProfileForm'].record['childOneName'] = $('#pchildOneName').val();
	    
	    $('#childOneGender').val($('#pchildOneGender').val());
	    w2ui['candidateProfileForm'].record['childOneGender'] = $('#pchildOneGender').val();
	    
	    $('#childOneDob').val($('#pchildOneDob').val());
	    w2ui['candidateProfileForm'].record['childOneDob'] = $('#pchildOneDob').val();
	    
	    $('#childTwoName').val($('#pchildTwoName').val());
	    w2ui['candidateProfileForm'].record['childTwoName'] = $('#pchildTwoName').val();
	    
	    $('#childTwoGender').val($('#pchildTwoGender').val());
	    w2ui['candidateProfileForm'].record['childTwoGender'] = $('#pchildTwoGender').val();
	    
	    $('#childTwoDob').val($('#pchildTwoDob').val());
	    w2ui['candidateProfileForm'].record['childTwoDob'] = $('#pchildTwoDob').val();
	    //prev org
	    $('#prevOrgName1').val($('#pprevOrgName1').val());
	    w2ui['candidateProfileForm'].record['prevOrgName1'] = $('#pprevOrgName1').val();
	    
	    $('#prevOrgAdrs1').val($('#pprevOrgAdrs1').val());
	    w2ui['candidateProfileForm'].record['prevOrgAdrs1'] = $('#pprevOrgAdrs1').val();
	    
	    $('#prevOrgFrom1').val($('#pprevOrgFrom1').val());
	    w2ui['candidateProfileForm'].record['prevOrgFrom1'] = $('#pprevOrgFrom1').val();
	    
	    $('#prevOrgTo1').val($('#pprevOrgTo1').val());
	    w2ui['candidateProfileForm'].record['prevOrgTo1'] = $('#pprevOrgTo1').val();
	    
	    $('#prevOrgDesig1').val($('#pprevOrgDesig1').val());
	    w2ui['candidateProfileForm'].record['prevOrgDesig1'] = $('#pprevOrgDesig1').val();
	    
	    
	    $('#prevOrgName2').val($('#pprevOrgName2').val());
	    w2ui['candidateProfileForm'].record['prevOrgName2'] = $('#pprevOrgName2').val();
	    
	    $('#prevOrgAdrs2').val($('#pprevOrgAdrs2').val());
	    w2ui['candidateProfileForm'].record['prevOrgAdrs2'] = $('#pprevOrgAdrs2').val();
	    
	    $('#prevOrgFrom2').val($('#pprevOrgFrom2').val());
	    w2ui['candidateProfileForm'].record['prevOrgFrom2'] = $('#pprevOrgFrom2').val();
	    
	    $('#prevOrgTo2').val($('#pprevOrgTo2').val());
	    w2ui['candidateProfileForm'].record['prevOrgTo2'] = $('#pprevOrgTo2').val();
	    
	    $('#prevOrgDesig2').val($('#pprevOrgDesig2').val());
	    w2ui['candidateProfileForm'].record['prevOrgDesig2'] = $('#pprevOrgDesig2').val();
	    
	    
	    $('#prevOrgName3').val($('#pprevOrgName3').val());
	    w2ui['candidateProfileForm'].record['prevOrgName3'] = $('#pprevOrgName3').val();
	    
	    $('#prevOrgAdrs3').val($('#pprevOrgAdrs3').val());
	    w2ui['candidateProfileForm'].record['prevOrgAdrs3'] = $('#pprevOrgAdrs3').val();
	    
	    $('#prevOrgFrom3').val($('#pprevOrgFrom3').val());
	    w2ui['candidateProfileForm'].record['prevOrgFrom3'] = $('#pprevOrgFrom3').val();
	    
	    $('#prevOrgTo3').val($('#pprevOrgTo3').val());
	    w2ui['candidateProfileForm'].record['prevOrgTo3'] = $('#pprevOrgTo3').val();
	    
	    $('#prevOrgDesig3').val($('#pprevOrgDesig3').val());
	    w2ui['candidateProfileForm'].record['prevOrgDesig3'] = $('#pprevOrgDesig3').val();
	    //educational 
	    $('#candDegreeName1').val($('#pcandDegreeName1').val());
	    w2ui['candidateProfileForm'].record['candDegreeName1'] = $('#pcandDegreeName1').val();
	    
	    $('#candDegreeMajor1').val($('#pcandDegreeMajor1').val());
	    w2ui['candidateProfileForm'].record['candDegreeMajor1'] = $('#pcandDegreeMajor1').val();
	    
	    $('#candDegreePasYr1').val($('#pcandDegreePasYr1').val());
	    w2ui['candidateProfileForm'].record['candDegreePasYr1'] = $('#pcandDegreePasYr1').val();
	    
	    $('#candDegreeInst1').val($('#pcandDegreeInst1').val());
	    w2ui['candidateProfileForm'].record['candDegreeInst1'] = $('#pcandDegreeInst1').val();
	    
	    
	    $('#candDegreeName2').val($('#pcandDegreeName2').val());
	    w2ui['candidateProfileForm'].record['candDegreeName2'] = $('#pcandDegreeName2').val();
	    
	    $('#candDegreeMajor2').val($('#pcandDegreeMajor2').val());
	    w2ui['candidateProfileForm'].record['candDegreeMajor2'] = $('#pcandDegreeMajor2').val();
	    
	    $('#candDegreePasYr2').val($('#pcandDegreePasYr2').val());
	    w2ui['candidateProfileForm'].record['candDegreePasYr2'] = $('#pcandDegreePasYr2').val();
	    
	    $('#candDegreeInst2').val($('#pcandDegreeInst2').val());
	    w2ui['candidateProfileForm'].record['candDegreeInst2'] = $('#pcandDegreeInst2').val();
	    
	  	//others information of candidate 
	  	$('#candidateTraining').val($('#pcandidateTraining').val());
	    w2ui['candidateProfileForm'].record['candidateTraining'] = $('#pcandidateTraining').val();
	    
	    $('#othersSkill').val($('#pothersSkill').val());
	    w2ui['candidateProfileForm'].record['othersSkill'] = $('#pothersSkill').val();
	    
	    $('#relativeName').val($('#prelativeName').val());
	    w2ui['candidateProfileForm'].record['relativeName'] = $('#prelativeName').val();
	    
	    $('#relativeDesig').val($('#prelativeDesig').val());
	    w2ui['candidateProfileForm'].record['relativeDesig'] = $('#prelativeDesig').val();
	    
	    $('#referName1').val($('#preferName1').val());
	    w2ui['candidateProfileForm'].record['referName1'] = $('#preferName1').val();
	    
	    $('#referOccuption1').val($('#preferOccuption1').val());
	    w2ui['candidateProfileForm'].record['referOccuption1'] = $('#preferOccuption1').val();
	    
	    $('#referAddress1').val($('#preferAddress1').val());
	    w2ui['candidateProfileForm'].record['referAddress1'] = $('#preferAddress1').val();
	    
	    $('#referContactNo1').val($('#preferContactNo1').val());
	    w2ui['candidateProfileForm'].record['referContactNo1'] = $('#preferContactNo1').val();
	    
	    $('#referName2').val($('#preferName2').val());
	    w2ui['candidateProfileForm'].record['referName2'] = $('#preferName2').val();
	    
	    $('#referOccuption2').val($('#preferOccuption2').val());
	    w2ui['candidateProfileForm'].record['referOccuption2'] = $('#preferOccuption2').val();
	    
	    $('#referAddress2').val($('#preferAddress2').val());
	    w2ui['candidateProfileForm'].record['referAddress2'] = $('#preferAddress2').val();
	    
	    $('#referContactNo2').val($('#preferContactNo2').val());
	    w2ui['candidateProfileForm'].record['referContactNo2'] = $('#preferContactNo2').val();
	    
	    //hr
	   // w2alert($('#isAdmin').val());
	     if($('#isAdmin').val() == 'true'){
		    $('#supervisorName').val($('#psupervisorName').val());
		    w2ui['candidateProfileForm'].record['supervisorName'] = $('#psupervisorName').val();
		    
		    $('#supervisorEmail').val($('#psupervisorEmail').val());
		    w2ui['candidateProfileForm'].record['supervisorEmail'] = $('#psupervisorEmail').val();
		    
		    $('#grade').val($('#pgrade').val());
		    w2ui['candidateProfileForm'].record['grade'] = $('#pgrade').val();
		    
		    $('#empId').val($('#pempId').val());
		    w2ui['candidateProfileForm'].record['empId'] = $('#pempId').val();
		    
		    $('#officeMobileNo').val($('#pofficeMobileNo').val());
		    w2ui['candidateProfileForm'].record['officeMobileNo'] = $('#pofficeMobileNo').val();
		    
		    $('#bankAccNo').val($('#pbankAccNo').val());
		    w2ui['candidateProfileForm'].record['bankAccNo'] = $('#pbankAccNo').val();
		    
		    $('#grossSalary').val($('#pgrossSalary').val());
		    w2ui['candidateProfileForm'].record['grossSalary'] = $('#pgrossSalary').val();
		    
		    $('#basicSalary').val($('#pbasicSalary').val());
		    w2ui['candidateProfileForm'].record['basicSalary'] = $('#pbasicSalary').val();
		    
		    $('#houseRent').val($('#phouseRent').val());
		    w2ui['candidateProfileForm'].record['houseRent'] = $('#phouseRent').val();
		    
		    $('#medicalIn').val($('#pmedicalIn').val());
		    w2ui['candidateProfileForm'].record['medicalIn'] = $('#pmedicalIn').val();
		    
		    $('#conveyance').val($('#pconveyance').val());
		    w2ui['candidateProfileForm'].record['conveyance'] = $('#pconveyance').val();
		    
		    $('#providentFund').val($('#pprovidentFund').val());
		    w2ui['candidateProfileForm'].record['providentFund'] = $('#pprovidentFund').val();
		    
		    $('#officeLocation').val($('#pofficeLocation').val());
		    w2ui['candidateProfileForm'].record['officeLocation'] = $('#pofficeLocation').val();
		    
		    $('#yearlyBonus').val($('#pyearlyBonus').val());
		    w2ui['candidateProfileForm'].record['yearlyBonus'] = $('#pyearlyBonus').val();
		    
		    $('#provisionPeriod').val($('#pprovisionPeriod').val());
		    w2ui['candidateProfileForm'].record['provisionPeriod'] = $('#pprovisionPeriod').val();
		    
		    $('#salIncreaseAmt').val($('#psalIncreaseAmt').val());
		    w2ui['candidateProfileForm'].record['salIncreaseAmt'] = $('#psalIncreaseAmt').val();
		    
		    $('#salaryOthersAmt').val($('#psalaryOthersAmt').val());
		    w2ui['candidateProfileForm'].record['salaryOthersAmt'] = $('#psalaryOthersAmt').val();		    
		    
		    $('#alIssuedBy').val($('#palIssuedBy').val());
		    w2ui['candidateProfileForm'].record['alIssuedBy'] = $('#palIssuedBy').val();
		    
		    $('#alIssuedByDesignation').val($('#palIssuedByDesignation').val());
		    w2ui['candidateProfileForm'].record['alIssuedByDesignation'] = $('#palIssuedByDesignation').val();
		    
		    $('#alPreparedBy').val($('#palPreparedBy').val());
		    w2ui['candidateProfileForm'].record['alPreparedBy'] = $('#palPreparedBy').val();
		    
		    
		    $('#remarks').val($('#premarks').val());
		    w2ui['candidateProfileForm'].record['remarks'] = $('#premarks').val();
	     }
	    
	    
	    if($('#pgender').val() == ""){
	    	$('input[type=gender_list]').data('selected', { id: "", text: 'Select One'}).data('w2field').refresh();
	    } else {
	    	$('input[type=gender_list]').data('selected', { id: "", text: $('#pgender').val()}).data('w2field').refresh();
	    }
	    
	    if($('#preligion').val() == ""){
	    	$('input[type=religion_list]').data('selected', { id: "", text: 'Select One'}).data('w2field').refresh();
	    } else {
	    	$('input[type=religion_list]').data('selected', { id: "", text: $('#preligion').val()}).data('w2field').refresh();
	    }
	    
	    if($('#pbloodGroup').val() == ""){
	    	$('input[type=blood_group_list]').data('selected', { id: "", text: 'Select One'}).data('w2field').refresh();
	    } else {
	    	$('input[type=blood_group_list]').data('selected', { id: "", text: $('#pbloodGroup').val()}).data('w2field').refresh();
	    }
	    
	    if($('#pmaritialStatus').val() == ""){
	    	$('input[type=maritialStatus]').data('selected', { id: "", text: 'Select One'}).data('w2field').refresh();
	    } else {
	    	$('input[type=maritialStatus]').data('selected', { id: "", text: $('#pmaritialStatus').val()}).data('w2field').refresh();
	    }
	    
	    if($('#isAdmin').val() == 'true'){
		    if($('#pgrade').val() == ""){
		    	$('input[type=grade]').data('selected', { id: "", text: 'Select One'}).data('w2field').refresh();
		    } else {
		    	$('input[type=grade]').data('selected', { id: "", text: $('#pgrade').val()}).data('w2field').refresh();
		    }
	    }
	    
	    
	    w2ui['form'].refresh();
	    
	 });
</script>

<script type="text/javascript">
	var contextPath = $('#contextPath').val();	
	$(document).on("click", ".candidate_profile_submit", function(event){
		w2confirm('Do you wanna submit your information? <br/><span class="red">N.B: After Submit you will be not able to change any information. </span>', function btn(answer) {
		    if(answer == 'Yes'){
		    	$(".candidate_profile_submit").prop('disabled', true);		    	
		    	window.location.href = contextPath+"/submitCandidateProfile";
		    } else {
		    	$(".candidate_profile_submit").prop('disabled', false);
		    }
		});		
	});
	
	$(document).on("blur", "#supervisorEmail", function(event){
		    var email = $("#supervisorEmail").val();
		    var filter = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
		    if (!filter.test(email)) {
		    	$(this).val('');
		      	w2alert("Invalid Email Address");		      	
		      	return;
		    } 
		  }); 
	
	
	/* $(function () {
	    $('#resignLetter').change(function () {
	        var val = $(this).val().toLowerCase(),
	            regex = new RegExp("(.*?)\.(pdf)$");

	        if (!(regex.test(val))) {
	            $(this).val('');
	            w2alert('Please select PDF file format only.');
	        }
	    });
	    
	    $('#othersDoc').change(function () {
	        var val = $(this).val().toLowerCase(),
	            regex = new RegExp("(.*?)\.(zip)$");

	        if (!(regex.test(val))) {
	            $(this).val('');
	            w2alert('Please select Zip file format only.');
	        }
	    });		    
	    	    
	}); */
	
	
</script>

</body>
</html>