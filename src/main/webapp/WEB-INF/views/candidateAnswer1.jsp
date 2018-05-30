<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<!-- Title and other stuffs -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="keywords" content="">
<meta name="author" content="">

<c:url var="checkCandidateId" value="/checkCandId" />

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
	<script
	src="${pageContext.request.contextPath}/resource/js/additional-methods.min.js"></script>
	<script
	src="${pageContext.request.contextPath}/resource/js/jquery.validate.min.js"></script>
 <style type="text/css">
            form .error {
                color: #ff0000;

            }
          

        </style>
<style type="text/css">
.table td.fit, .table th.fit {
	white-space: nowrap;
	width: 1%;
}
</style>


</head>

<body>
	<!-- Page heading start -->
	<div class="page-head">
		<h2 class="pull-left" style="color: #428bca;">${titleHead}</h2>		
		
		<div class="pull-right">
			<c:if test="${candidate.uploadAppLetter eq 1}">
				<a class="btn btn-warning btn-sm custom-width" title="Download App Letter" target="_blank"
					href='<c:url value="/candidateAppLetter/${candidate.candId}"/>.pdf' 
					style="color: white;"><i class="fa fa-file-pdf-o" style="color:red"></i>&nbsp;&nbsp; Appointment Letter</a>
				</c:if>
		</div>
		<div class="clearfix"></div>
		<p class="red"> On a scale of 1 to 5, where 1=Very Dissatisfied and 5= Very Satisfied </p>
		<div class="clearfix"></div>
	</div>
	<!-- Page heading ends -->

	<!-- Matter -->

	<!--   <div class="matter"> -->
	<div class="container">

		<div class="row">

			<div class="col-md-12">


				<div class="widget wgreen">

					<div class="widget-head">
						<div class="pull-left">
							<p class="blue"> Candidate Name : ${candidateAnswer.candidate.candName} , 
							Candidate ID : ${candidateAnswer.candidate.candId}, 
							Employee Id : ${candidateProfile.empId}, 
							Company : ${candidateAnswer.candidate.company.name}, 
							Date of Joining : ${candidateAnswer.candidate.dateOfJoin} </p>
						</div>
						<div class="widget-icons pull-right">
							<a href="#" class="wminimize"><i class="fa fa-chevron-up"></i></a>
							<a href="#" class="wclose"><i class="fa fa-times"></i></a>
						</div>
						<div class="clearfix"></div>
					</div>

					<div class="widget-content">
						<div class="padd">

							<br />
					
							<!-- Form starts.  -->
							<form:form cssClass="form-horizontal" class="form-horizontal" > 
								<div class="form-group">
									<label class="col-lg-3 control-label">1. How was your 1st day experience? <span
										class="red">*</span>:
									</label>
									<div class="col-lg-5">	
										<%-- <p class="bold" style="color:#00000; font-size:2em;"> ${candidateAnswer.answerOne} </p> --%>
										<c:if test="${candidateAnswer.answerOne eq 1}"> 
											<p class="bold" style="color:#00000; font-size:2em;">Very Dissatisfied </p>
										</c:if>		
										<c:if test="${candidateAnswer.answerOne eq 2}"> 
											<p class="bold" style="color:#00000; font-size:2em;">Dissatisfied </p>
										</c:if>
										<c:if test="${candidateAnswer.answerOne eq 3}"> 
											<p class="bold" style="color:#00000; font-size:2em;">Neither Satisfied Nor Dissatisfied</p>
										</c:if>
										<c:if test="${candidateAnswer.answerOne eq 4}"> 
											<p class="bold" style="color:#00000; font-size:2em;">Satisfied </p>
										</c:if>
										<c:if test="${candidateAnswer.answerOne eq 5}"> 
											<p class="bold" style="color:#00000; font-size:2em;">Very Satisfied </p>
										</c:if>													
										
									</div>
								</div>
								<div class="form-group">
									<label class="col-lg-3 control-label">2. Are you comfortable in your work place? <span
										class="red">*</span>:
									</label>
									<div class="col-lg-5">
										<%-- <p class="bold" style="color:#00000; font-size:2em;"> ${candidateAnswer.answerTwo} </p> --%>
										<c:if test="${candidateAnswer.answerTwo eq 1}"> 
											<p class="bold" style="color:#00000; font-size:2em;">Very Dissatisfied </p>
										</c:if>		
										<c:if test="${candidateAnswer.answerTwo eq 2}"> 
											<p class="bold" style="color:#00000; font-size:2em;">Dissatisfied </p>
										</c:if>
										<c:if test="${candidateAnswer.answerTwo eq 3}"> 
											<p class="bold" style="color:#00000; font-size:2em;">Neither Satisfied Nor Dissatisfied</p>
										</c:if>
										<c:if test="${candidateAnswer.answerTwo eq 4}"> 
											<p class="bold" style="color:#00000; font-size:2em;">Satisfied </p>
										</c:if>
										<c:if test="${candidateAnswer.answerTwo eq 5}"> 
											<p class="bold" style="color:#00000; font-size:2em;">Very Satisfied </p>
										</c:if>
									</div>
								</div>
								<div class="form-group">
									<label class="col-lg-3 control-label">3. How much your team was friendly with you? <span
										class="red">*</span>:
									</label>
									<div class="col-lg-5">
										<%-- <p class="bold" style="color:#00000; font-size:2em;"> ${candidateAnswer.answerThree} </p> --%>
										<c:if test="${candidateAnswer.answerThree eq 1}"> 
											<p class="bold" style="color:#00000; font-size:2em;">Very Dissatisfied </p>
										</c:if>		
										<c:if test="${candidateAnswer.answerThree eq 2}"> 
											<p class="bold" style="color:#00000; font-size:2em;">Dissatisfied </p>
										</c:if>
										<c:if test="${candidateAnswer.answerThree eq 3}"> 
											<p class="bold" style="color:#00000; font-size:2em;">Neither Satisfied Nor Dissatisfied</p>
										</c:if>
										<c:if test="${candidateAnswer.answerThree eq 4}"> 
											<p class="bold" style="color:#00000; font-size:2em;">Satisfied </p>
										</c:if>
										<c:if test="${candidateAnswer.answerThree eq 5}"> 
											<p class="bold" style="color:#00000; font-size:2em;">Very Satisfied </p>
										</c:if>
									</div>
								</div>
								<div class="form-group">
									<label class="col-lg-3 control-label">4.Did you get your buddy?If yes,does he/she cooperate? <span
										class="red">*</span>:
									</label>
									<div class="col-lg-5">
										<%-- <p class="bold" style="color:#00000; font-size:2em;"> ${candidateAnswer.answerFour} </p> --%>
										<c:if test="${candidateAnswer.answerFour eq 1}"> 
											<p class="bold" style="color:#00000; font-size:2em;">Very Dissatisfied </p>
										</c:if>		
										<c:if test="${candidateAnswer.answerFour eq 2}"> 
											<p class="bold" style="color:#00000; font-size:2em;">Dissatisfied </p>
										</c:if>
										<c:if test="${candidateAnswer.answerFour eq 3}"> 
											<p class="bold" style="color:#00000; font-size:2em;">Neither Satisfied Nor Dissatisfied</p>
										</c:if>
										<c:if test="${candidateAnswer.answerFour eq 4}"> 
											<p class="bold" style="color:#00000; font-size:2em;">Satisfied </p>
										</c:if>
										<c:if test="${candidateAnswer.answerFour eq 5}"> 
											<p class="bold" style="color:#00000; font-size:2em;">Very Satisfied </p>
										</c:if>
									</div>
								</div>
								<div class="form-group">
									<label class="col-lg-3 control-label">5. How was your recruitment and selection process? <span
										class="red">*</span>:
									</label>
									<div class="col-lg-5">
										<%-- <p class="bold" style="color:#00000; font-size:2em;"> ${candidateAnswer.answerFive} </p> --%>
										<c:if test="${candidateAnswer.answerFive eq 1}"> 
											<p class="bold" style="color:#00000; font-size:2em;">Very Dissatisfied </p>
										</c:if>		
										<c:if test="${candidateAnswer.answerFive eq 2}"> 
											<p class="bold" style="color:#00000; font-size:2em;">Dissatisfied </p>
										</c:if>
										<c:if test="${candidateAnswer.answerFive eq 3}"> 
											<p class="bold" style="color:#00000; font-size:2em;">Neither Satisfied Nor Dissatisfied</p>
										</c:if>
										<c:if test="${candidateAnswer.answerFive eq 4}"> 
											<p class="bold" style="color:#00000; font-size:2em;">Satisfied </p>
										</c:if>
										<c:if test="${candidateAnswer.answerFive eq 5}"> 
											<p class="bold" style="color:#00000; font-size:2em;">Very Satisfied </p>
										</c:if>
									</div>
								</div>
								<%-- <div class="form-group">
									<label class="col-lg-2 control-label">Self Assesment <span
										class="red">*</span>:
									</label>
									<div class="col-lg-5">
										<p class="bold" style="color:#00000; font-size:2em;"> ${candidateAnswer.npsResult} </p>
									</div>
								</div>  --%>


							 </form:form> 
						</div>

					</div>
					<div class="widget-foot">
						<!-- Footer goes here -->
					</div>
				</div>

				<!-- Table -->

			</div>

		</div>

	</div>

	<!-- table ends -->

	<!-- Matter ends -->

	<!-- Mainbar ends -->
	<div class="clearfix"></div>

</body>
</html>