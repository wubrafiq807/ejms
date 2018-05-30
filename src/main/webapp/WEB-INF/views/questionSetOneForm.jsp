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

		<h2 class="pull-left" style="color: #428bca;">1st day's Questionnaires</h2>		
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
							<form:form cssClass="form-horizontal" method="POST" name="reg"
								id="questionSetOneForm" class="form-horizontal"
								modelAttribute="command"
								action="${pageContext.request.contextPath}/saveQuestionSet">

								<div class="form-group">
									<label class="col-lg-3 control-label">1. How was your 1st day experience? <span
										class="red">*</span>:
									</label>
									<div class="col-lg-9">
										<%-- <form:input id="answerOne" path="answerOne"
											class="form-control" /> --%>
										<input type="radio" name="answerOne" value="1" id="answerOne">&nbsp;Very Dissatisfied&nbsp;&nbsp;&nbsp;
										<input type="radio" name="answerOne" value="2" id="answerOne">&nbsp;Dissatisfied&nbsp;&nbsp;&nbsp;
										<input type="radio" name="answerOne" value="3" id="answerOne">&nbsp;Neither Satisfied Nor Dissatisfied&nbsp;&nbsp;&nbsp;
										<input type="radio" name="answerOne" value="4" id="answerOne">&nbsp;Satisfied&nbsp;&nbsp;&nbsp;
										<input type="radio" name="answerOne" value="5" id="answerOne" checked="checked">&nbsp;Very Satisfied
									</div>
								</div>
								<div class="form-group">
									<label class="col-lg-3 control-label">2. Are you comfortable in your work place? <span
										class="red">*</span>:
									</label>
									<div class="col-lg-9">
										<%-- <form:input id="answerTwo" path="answerTwo"
											class="form-control" /> --%>
										<input type="radio" name="answerTwo" value="1" id="answerTwo">&nbsp;Very Dissatisfied&nbsp;&nbsp;&nbsp;
										<input type="radio" name="answerTwo" value="2" id="answerTwo">&nbsp;Dissatisfied&nbsp;&nbsp;&nbsp;
										<input type="radio" name="answerTwo" value="3" id="answerTwo">&nbsp;Neither Satisfied Nor Dissatisfied&nbsp;&nbsp;&nbsp;
										<input type="radio" name="answerTwo" value="4" id="answerTwo">&nbsp;Satisfied&nbsp;&nbsp;&nbsp;
										<input type="radio" name="answerTwo" value="5" id="answerTwo" checked="checked">&nbsp;Very Satisfied
									</div>
								</div>
								<div class="form-group">
									<label class="col-lg-3 control-label">3. How much your team was friendly with you? <span
										class="red">*</span>:
									</label>
									<div class="col-lg-9">
										<%-- <form:input id="answerThree" path="answerThree"
											class="form-control" /> --%>
										<input type="radio" name="answerThree" value="1" id="answerThree">&nbsp;Very Dissatisfied&nbsp;&nbsp;&nbsp;
										<input type="radio" name="answerThree" value="2" id="answerThree">&nbsp;Dissatisfied&nbsp;&nbsp;&nbsp;
										<input type="radio" name="answerThree" value="3" id="answerThree">&nbsp;Neither Satisfied Nor Dissatisfied&nbsp;&nbsp;&nbsp;
										<input type="radio" name="answerThree" value="4" id="answerThree">&nbsp;Satisfied&nbsp;&nbsp;&nbsp;
										<input type="radio" name="answerThree" value="5" id="answerThree" checked="checked">&nbsp;Very Satisfied
									</div>
								</div>
								<div class="form-group">
									<label class="col-lg-3 control-label">4.Did you get your buddy?If yes,does he/she cooperate? <span
										class="red">*</span>:
									</label>
									<div class="col-lg-9">
										<%-- <form:input id="answerFour" path="answerFour"
											class="form-control" /> --%>
										<input type="radio" name="answerFour" value="1" id="answerFour">&nbsp;Very Dissatisfied&nbsp;&nbsp;&nbsp;
										<input type="radio" name="answerFour" value="2" id="answerFour">&nbsp;Dissatisfied&nbsp;&nbsp;&nbsp;
										<input type="radio" name="answerFour" value="3" id="answerFour">&nbsp;Neither Satisfied Nor Dissatisfied&nbsp;&nbsp;&nbsp;
										<input type="radio" name="answerFour" value="4" id="answerFour">&nbsp;Satisfied&nbsp;&nbsp;&nbsp;
										<input type="radio" name="answerFour" value="5" id="answerFour" checked="checked">&nbsp;Very Satisfied
									</div>
								</div>
								<div class="form-group">
									<label class="col-lg-3 control-label">5. How was your recruitment and selection process? <span
										class="red">*</span>:
									</label>
									<div class="col-lg-9">
										<%-- <form:input id="answerFive" path="answerFive"
											class="form-control" /> --%>
										<input type="radio" name="answerFive" value="1" id="answerFive">&nbsp;Very Dissatisfied&nbsp;&nbsp;&nbsp;
										<input type="radio" name="answerFive" value="2" id="answerFive">&nbsp;Dissatisfied&nbsp;&nbsp;&nbsp;
										<input type="radio" name="answerFive" value="3" id="answerFive">&nbsp;Neither Satisfied Nor Dissatisfied&nbsp;&nbsp;&nbsp;
										<input type="radio" name="answerFive" value="4" id="answerFive">&nbsp;Satisfied&nbsp;&nbsp;&nbsp;
										<input type="radio" name="answerFive" value="5" id="answerFive" checked="checked">&nbsp;Very Satisfied
									</div>
								</div>
								<!-- <div class="form-group">
									<label class="col-lg-2 control-label">Set Assesment <span
										class="red">*</span>:
									</label>
									<div class="col-lg-5">
										<table class="table table-striped">
											<tr>
											<td></td>
												<td>0</td>
												<td>1</td>
												<td>2</td>
												<td>3</td>
												<td>4</td>
												<td>5</td>
												<td>6</td>
												<td>7</td>
												<td>8</td>
												<td>9</td>
												<td>10</td>
												<td></td>

											</tr>
											<tr>
											<td>Low</td>
												<td><input type="radio" name="radQ3" value="0" id="q0"
													title="strongly agree" /></td>
													<td><input type="radio" name="radQ3" value="1" id="q1"
													title="strongly agree" /></td>
													<td><input type="radio" name="radQ3" value="2" id="q2"
													title="strongly agree" /></td>
													<td><input type="radio" name="radQ3" value="3" id="q3"
													title="strongly agree" /></td>
													<td><input type="radio" name="radQ3" value="4" id="q4"
													title="strongly agree" /></td>
													<td><input type="radio" name="radQ3" value="5" id="q5"
													title="strongly agree" /></td>
													<td><input type="radio" name="radQ3" value="6" id="q6"
													title="strongly agree" /></td>
													<td><input type="radio" name="radQ3" value="7" id="q7"
													title="strongly agree" /></td>
													<td><input type="radio" name="radQ3" value="7" id="q8"
													title="strongly agree" /></td>
													<td><input type="radio" name="radQ3" value="9" id="q9"
													title="strongly agree" /></td>
													<td><input type="radio" name="radQ3" checked value="10" id="q10"
													title="strongly agree" /></td>
												<td>High</td>

											</tr>
										</table>
									</div>
								</div> -->


								<div class="form-group">
									<c:choose>
										<c:when test="${edit}">
											<div class="col-lg-offset-2 col-lg-1 col-xs-4" id="">
												<button type="button"
													class="btn btn-sm btn-primary btn-success req-save-update-btn">
													Update</button>
											</div>
											<div class="col-lg-1 col-xs-4">

												<a class="btn btn-sm btn-danger"
													href="${pageContext.request.contextPath}/">Cancel</a>
											</div>
										</c:when>
										<c:otherwise>
											<div class="col-lg-offset-2 col-lg-1 col-xs-4" id="">
												<button type="submit"
													class="btn btn-sm btn-primary req-save-update-btn">
													Submit</button>
											</div>
											<div class="col-lg-1 col-xs-4">
												<button type="reset" class="btn btn-sm btn-danger ">Reset</button>
											</div>
										</c:otherwise>
									</c:choose>
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

 <script>
            $(function () {
                // Initialize form validation on the registration form.
                // It has the name attribute "registration"
                $("form[name='reg']").validate({
                    // Specify validation rules
                    rules: {
                        // The key name on the left side is the name attribute
                        // of an input field. Validation rules are defined
                        // on the right side
                        answerOne: "required",
                        answerTwo: "required",
                        answerThree: "required",
                        answerFour: "required",
                        answerFive: "required",


                    },
                    // Specify validation error messages
                    messages: {
                    	answerOne: "Please enter answer one",
                    	answerTwo: "Please enter answer two",
                    	answerThree: "Please enter answer three",
                    	answerFour: "Please enter answer four",
                    	answerFive: "Please enter answer five"
                    },
                    // Make sure the form is submitted to the destination defined
                    // in the "action" attribute of the form when valid
                    submitHandler: function (form) {
                        form.submit();
                    }
                });
            });

        </script>
	


</body>
</html>