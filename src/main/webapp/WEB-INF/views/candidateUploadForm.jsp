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

<c:url var="checkCandidateEmailId" value="/checkEmailId" />

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

<style type="text/css">
.table td.fit, .table th.fit {
	white-space: nowrap;
	width: 1%;
}

.col-xs-1, .col-sm-1, .col-md-1, .col-lg-1, .col-xs-2, .col-sm-2, .col-md-2, .col-lg-2, .col-xs-3, .col-sm-3, .col-md-3, .col-lg-3, .col-xs-4, .col-sm-4, .col-md-4, .col-lg-4, .col-xs-5, .col-sm-5, .col-md-5, .col-lg-5, .col-xs-6, .col-sm-6, .col-md-6, .col-lg-6, .col-xs-7, .col-sm-7, .col-md-7, .col-lg-7, .col-xs-8, .col-sm-8, .col-md-8, .col-lg-8, .col-xs-9, .col-sm-9, .col-md-9, .col-lg-9, .col-xs-10, .col-sm-10, .col-md-10, .col-lg-10, .col-xs-11, .col-sm-11, .col-md-11, .col-lg-11, .col-xs-12, .col-sm-12, .col-md-12, .col-lg-12 {
    position: relative;
    min-height: 1px;
    padding-left: 15px;
    padding-right: 15px;
    margin-top: 2px;
}
</style>

<script type="text/javascript">
$(document).ready(function() {
    $('.basic-single-select2').select2();
});
</script>
</head>

<body>
	<!-- Page heading start -->
	<div class="page-head">
		<h2 class="pull-left" style="color: #428bca;">Document Update Form</h2>
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
						<div class="pull-left"></div>
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
							<form:form cssClass="form-horizontal" method="POST"
									id="candidateForm" class="form-horizontal"
									modelAttribute="candidateForm" enctype="multipart/form-data"
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
														<button type="button" name="submit_btn" value="Upload"
															class="btn btn-sm btn-primary req-save-update-btn">Upload</button>
													</div>
												</div>
											
											</div>
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
	
	$(document)
	.ready(
			function() {
				
				$('.req-save-update-btn').click(function() {
					var flag = false;
					$(".req-save-update-btn").prop(
							'disabled', true);

					if ($('#resignLetter').val().trim().length > 0 || $('#othersDoc').val().trim().length > 0) {
						flag = false;

					} else {
						flag = true;
						$(".req-save-update-btn")
								.prop('disabled',
										false);
						w2alert("Please Upload File.");
						return flag;
					}
					

					if (!flag) {
						$(".req-save-update-btn")
								.prop('disabled',
										true);
						$('#candidateForm').submit();
					} else {
						$(".req-save-update-btn")
								.prop('disabled',
										false);
						return flag;
					}
				});

			});
	
		$(function () {
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
		    	    
		});
	</script>

</body>
</html>