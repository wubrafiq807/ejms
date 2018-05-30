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

p {
	font-size: 15px;
	color: #000000;
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
		<h2 class="pull-left" style="color: #428bca;">Offer Letter</h2>
		<input type="hidden" value="${pageContext.request.contextPath}"
			id="contextPath">
		<div class="pull-right">
			<c:if test="${candidate.uploadAppLetter eq 1}">
				<a class="btn btn-warning btn-sm custom-width"
					title="Download App Letter" target="_blank"
					href='<c:url value="/candidateAppLetter/${candidate.candId}"/>.pdf'
					style="color: white;"><i class="fa fa-file-pdf-o"
					style="color: red"></i>&nbsp;&nbsp; Appointment Letter</a>
			</c:if>
		</div>
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
							<p class="blue">Candidate Name : ${candidate.candName} ,
								Candidate ID : ${candidate.candId}, Company :
								${candidate.company.name}, Date of Joining :
								${candidate.dateOfJoin}</p>
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
							<form:form cssClass="form-horizontal" class="form-horizontal">
								<div class="form-group">
									<div class="col-lg-10 control-form">
										<p>Dear Concern,</p>
										<p>
											I read and understand all terms and conditions of the <b>Appointment
												Letter</b> and I am cordially accepting this Appointment Letter.
										</p>
										<br />
										<b>
										<p>Best Regards</p>
										<p>${candidate.candName}</p>
										<p>${candidate.candId}</p>
										</b>
										<br /> <br />
										<h3 class="bold underline">N: B: if you accept the
											Appointment letter please click on Accept, If not click
											Reject.</h3>
									</div>
								</div>

								<div class="form-group">
									<div class="col-lg-offset-2 col-lg-1 col-xs-4" id="">
										<a class="btn btn-sm btn-success"
											id="app_letter_accept_btn_id" href="#"
											onclick="doAccept(${candidate.id})">Accept</a>
									</div>
									<div class="col-lg-1 col-xs-4">
										<a class="btn btn-sm btn-danger" id="app_letter_reject_btn_id"
											href="#" onclick="doDecline(${candidate.id})">Reject</a>
									</div>
								</div>


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
	<script type="text/javascript">
		function doAccept(candId){	
			var contextPath = $('#contextPath').val();
			
			w2confirm('Do you want to Accept this Offer?', function btn(answer) {
			    if(answer == 'Yes'){
			    	window.location.href = contextPath+"/alAcceptedByCandidate?id="+candId;
			    } 
			});
		}
		
		function doDecline(candId){
			
			var contextPath = $('#contextPath').val();	
			
			w2confirm('Are you sure reject this offer?', function btn(answer) {
			    if(answer == 'Yes'){
			    	//
			    	w2popup.open({
					    title   : 'Reject Reason',
					    body    : '<input id="candId" type="hidden" value="'+candId+'"><textarea placeholder="Please input reason here" id="declineReason" class="form-control"> </textarea> <br/> <p id="worngOtpMsg" style="color:green;">Please Input Reject Reason Here.</p>',
					    buttons : '<button id="modalDeclineSubmitButton"> Submit </button>',
					    modal           : true,
					    width           : 500,
					    height          : 200,
					    showClose       : true,
					    keyboard        : false,
					    mouse 			: false
					});
			    } 
			});
		}

		$(document).on("click","#modalDeclineSubmitButton",function() {
			var contextPath = $('#contextPath').val();	
			var candId = $('#candId').val();
			var declineReason = $('#declineReason').val().trim();
			if(declineReason != null && declineReason.length > 0){
				$('#worngOtpMsg').text('Please Wait...');
				$('#worngOtpMsg').css( "color", "green" )		
				window.location.href = contextPath+"/alRejectedByCandidate?id="+candId+"&rejectReasonByCandidate="+declineReason;
			} else {
				$('#worngOtpMsg').text('Reject Reason can not be empty or null.');
				$('#worngOtpMsg').css( "color", "red" )
			}
			
		});
	</script>
</body>
</html>