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
		<h2 class="pull-left" style="color: #428bca;">Performance Report</h2>
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
							<form:form cssClass="form-horizontal" method="GET"
								id="otDtlReportFormId" class="form-horizontal" modelAttribute="candidateForm"
								action="${pageContext.request.contextPath}/generatePerformaceReport">

								<div class="form-group" title="format: yyyy-mm-dd">
									<label class="col-lg-2 control-label">From Date <span
										class="red">*</span>:
									</label>
									<div class="col-lg-5">
										<!-- <input name="fromDate" id="fromDate" type="eu-date1" maxlength="10" size="60" style="width: 100%" title="format: yyyy-mm-dd"/> -->
										<input type="eu-date1" class="form-control" name="fromDate" id="fromDate" maxlength="10" size="60" style="width: 100%"  title="format: yyyy-mm-dd">
									</div>
								</div>

								<div class="form-group">
									<label class="col-lg-2 control-label" title="format: yyyy-mm-dd">To Date  <span
										class="red">*</span>: </label>
									<div class="col-lg-5">
										<input name="toDate" id="toDate"  class="form-control" type="eu-date2" maxlength="10" size="60" style="width: 100%" title="format: yyyy-mm-dd"/>
									</div>
								</div>
								
								<!-- <div class="form-group">
									<label class="col-lg-2 control-label">Report Format : </label>
									<div class="col-lg-5">
										<select id="reportFormat" name="reportFormat" class="form-control">											
											<option value="pdf">PDF</option>
											<option value="xlx">EXCEL</option>											
										</select>
									</div>
								</div>  -->

								<div class="form-group">

									<div class="col-lg-offset-2 col-lg-1 col-xs-4" id="">										
										<button type="button"
											class="btn btn-sm btn-primary btn-success ai-save-update-btn">
											Generate</button>
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
	
	<div class="clearfix"></div>

	<script type="text/javascript">
	$(function () {
	    var month = (new Date()).getMonth() + 1;
	    var year  = (new Date()).getFullYear();
	    // US Format
	    $('input[type=eu-date]').w2field('date',  { format: 'yyyy-mm-dd' });	    
	    $('input[type=eu-date1]').w2field('date', { format: 'yyyy-mm-dd', end: $('input[type=eu-date2]') });
	    $('input[type=eu-date2]').w2field('date', { format: 'yyyy-mm-dd', start: $('input[type=eu-date1]') });
	});
	 // list
	
		$(document).ready(function() {
			$('.ai-save-update-btn').click(function() {
				var flag = false;
				$(".ai-save-update-btn").prop('disabled', true);

				if ($('#fromDate').val().trim().length > 0) {
					flag = false;
				} else {
					flag = true;
					$(".ai-save-update-btn").prop('disabled', false);
					w2alert("Please Select From Date.");
					return flag;
				}

				if ($('#toDate').val().trim().length > 0) {
					flag = false;
				} else {
					flag = true;
					$(".ai-save-update-btn").prop('disabled', false);
					w2alert("Please Select To Date.");
					return flag;
				}				

				if (!flag) {
					$('#otDtlReportFormId').submit();
					 $(".ai-save-update-btn").prop('disabled', false);					
				} else {
					$(".ai-save-update-btn").prop('disabled', false);
					return flag;
				}
			});

		});
		
	</script>

</body>
</html>