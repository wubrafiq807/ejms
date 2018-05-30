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
		<c:choose>
			<c:when test="${edit}">
				<h2 class="pull-left" style="color: #428bca;">Candidate Update
					Form</h2>
			</c:when>
			<c:otherwise>
				<h2 class="pull-left" style="color: #428bca;">NEW Candidate
					Entry Form</h2>
			</c:otherwise>
		</c:choose>
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
								id="employeeForm" class="form-horizontal"
								modelAttribute="candidateForm" enctype="multipart/form-data"
								action="${pageContext.request.contextPath}/saveCandidate">
								<div class="row">
									<div class="col-lg-12">
										<div class="col-md-10">
											<div class="form-group">
												<label class="col-lg-2 control-label">ID :</label>
												<div class="col-lg-5">
													<form:input id="id" path="id" value="${candidate.id}"
														class="form-control" readonly="true" />
												</div>
											</div>
			
											<div class="form-group">
												<label class="col-lg-2 control-label">Candidate ID:
												</label>
												<div class="col-lg-5">
			
													<c:choose>
														<c:when test="${edit}">
															<form:input id="candId" path="candId"
																value="${candidate.candId}" class="form-control"
																readonly="true" disabled="true"/>
			
														</c:when>
														<c:otherwise>
															<form:input id="candId" path="candId" readonly="true" 
																value="${candidate.candId}" class="form-control"  disabled="true" />
														</c:otherwise>
													</c:choose>
												</div>
											</div>
			
											<div class="form-group">
												<label class="col-lg-2 control-label">Candidate Name <span
													class="red">*</span>:
												</label>
												<div class="col-lg-5">
													<form:input id="candName" path="candName"
																value="${candidate.candName}" class="form-control" />			
													<%-- <c:choose>
														<c:when test="${edit}">
			
															<form:input id="candName" path="candName"
																value="${candidate.candName}" class="form-control"
																readonly="true" />
			
														</c:when>
														<c:otherwise>
															<form:input id="candName" path="candName"
																value="${candidate.candName}" class="form-control" />
														</c:otherwise>
													</c:choose> --%>
												</div>
											</div>
			
											<div class="form-group">
												<label class="col-lg-2 control-label">Recruit By<span
													class="red">*</span>:
												</label>
												<div class="col-lg-5">
													<form:select path="recuriteById" id="recuriteById"
														class="form-control basic-single-select2">
			
														<form:option value="">Select One</form:option>
														<c:forEach items="${employeeList}" var="employee">
			
															<c:if test="${candidate.hrEmployee.id eq employee.id}">
																<form:option value="${employee.id}" selected="selected">${employee.name}-(${employee.empId})</form:option>
															</c:if>
															<c:if test="${candidate.hrEmployee.id ne employee.id}">
																<form:option value="${employee.id}">${employee.name}-(${employee.empId})</form:option>
															</c:if>
														</c:forEach>
													</form:select>
												</div>
											</div>
			
											<div class="form-group">
												<label class="col-lg-2 control-label">Company<span
													class="red">*</span>:
												</label>
												<div class="col-lg-5">
			
													<c:choose>
														<c:when test="${edit}">
															<form:select path="companyId" id="companyId"
																class="form-control" readOnly="readOnly">												
																<c:forEach items="${companyList}" var="company">
																	<c:if test="${candidate.company.id eq company.id}">
																		<form:option value="${company.id}" selected="selected">${company.name}</form:option>
																	</c:if>
																</c:forEach>
															</form:select>
														</c:when>
														<c:otherwise>
															<form:select path="companyId" id="companyId"
																class="form-control basic-single-select2">
			
																<form:option value="">Select One</form:option>
																<c:forEach items="${companyList}" var="company">
			
																	<c:if test="${candidate.company.id eq company.id}">
																		<form:option value="${company.id}" selected="selected">${company.name}</form:option>
																	</c:if>
																	<c:if test="${candidate.company.id ne company.id}">
																		<form:option value="${company.id}">${company.name}</form:option>
																	</c:if>
			
																</c:forEach>
															</form:select>
														</c:otherwise>
													</c:choose>
			
			
												</div>
											</div>
											<div class="form-group">
												<label class="col-lg-2 control-label">Department<span
													class="red">*</span>:
												</label>
												<div class="col-lg-5">
													<form:select path="departmentId" id="departmentId"
														class="form-control basic-single-select2">
			
			
														<form:option value="">Select One</form:option>
														<c:forEach items="${departmentList}" var="department">
															<%-- 												<form:option value="${department.id}">${department.name}</form:option> --%>
															<c:if test="${candidate.department.id eq department.id}">
																<form:option value="${department.id}" selected="selected">${department.name}</form:option>
															</c:if>
															<c:if test="${candidate.department.id ne department.id}">
																<form:option value="${department.id}">${department.name}</form:option>
															</c:if>
														</c:forEach>
													</form:select>
												</div>
											</div>
											<div class="form-group">
												<label class="col-lg-2 control-label">Designation<span
													class="red">*</span>:
												</label>
												<div class="col-lg-5">
													<form:select path="designationId" id="designationId"
														class="form-control basic-single-select2">
			
														<form:option value="">Select One</form:option>
														<c:forEach items="${designitionList}" var="designation">
															<c:if test="${candidate.designation.id eq designation.id}">
																<form:option value="${designation.id}" selected="selected">${designation.name}</form:option>
															</c:if>
															<c:if test="${candidate.designation.id ne designation.id}">
																<form:option value="${designation.id}">${designation.name} - (${designation.grade}) </form:option>
															</c:if>
														</c:forEach>
													</form:select>
												</div>
											</div>
			
											<div class="form-group">
												<label class="col-lg-2 control-label">Joining Date<span
													class="red">*</span>:
												</label>
												<div class="col-lg-5">
													<input type="eu-date" class="form-control" name="dateOfJoin"
														id="dateOfJoin" value="${candidate.dateOfJoin}">
												</div>
											</div>
			
											<div class="form-group">
												<label class="col-lg-2 control-label">Source<span
													class="red">*</span>:
												</label>
												<div class="col-lg-5">
													<form:input id="sourceOfCand" path="sourceOfCand"
														value="${candidate.sourceOfCand}" class="form-control" />
												</div>
											</div>
			
											
			
											<div class="form-group">
												<label class="col-lg-2 control-label">Candidate Email<span
													class="red">*</span>:
												</label>
												<div class="col-lg-5">
													<form:input id="candEmail" path="candEmail"
																value="${candidate.candEmail}" class="form-control" />			
													<%-- <c:choose>
														<c:when test="${edit}">
															<form:input id="candEmail" path="candEmail"
																value="${candidate.candEmail}" class="form-control"
																readonly="true" />
			
			
														</c:when>
														<c:otherwise>
															<form:input id="candEmail" path="candEmail"
																value="${candidate.candEmail}" class="form-control" />
														</c:otherwise>
													</c:choose> --%>
												</div>
											</div>
			
											<div class="form-group">
												<label class="col-lg-2 control-label">Candidate Mobile
													No<span class="red">*</span>:
												</label>
												<div class="col-lg-5">
													<form:input id="candMobileNo" path="candMobileNo"
														value="${candidate.candMobileNo}" placeholder="017XXXXXXXX"
														class="form-control" />
												</div>
											</div>
			
											<div class="form-group">
												<label class="col-lg-2 control-label">Remarks :</label>
												<div class="col-lg-5">
													<textarea id="remarks" class="form-control" name="remarks"
														rows="4" type="textarea">${candidate.remarks}</textarea>
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-lg-2 control-label">Photo:</label>
												<div class="col-lg-5">
													<input type="file" name="photo" id="photo" class="form-control" />
												</div>
											</div>
											
											<c:if test="${!edit}">
												<div class="hidden"> 
													<input type="file" name="appLetter" class="form-control hidden" />
												</div>	
											</c:if>
											
											<c:if test="${!allowExtedLetter}">
												<div class="hidden"> 
													<input type="file" name="extentionLetter" class="form-control hidden" />
												</div>	
											</c:if>
											
											<c:if test="${edit}">
												<c:choose>
													<c:when test="${candidate.status eq 3 || candidate.status eq 4 || candidate.status eq 5 || candidate.status eq 15}">
														<div class="form-group">
															<label class="col-lg-2 control-label">Upload App. Letter:</label>
															<div class="col-lg-5">
																<input type="file" name="appLetter" id="appLetter" class="form-control" />
															</div>
														</div>
													</c:when>
													<c:otherwise>
														<div class="hidden"> 
															<input type="file" name="appLetter" class="form-control hidden" />
														</div>														
													</c:otherwise>
												</c:choose>												
											</c:if>
											
											<c:if test="${allowExtedLetter}">
												<div class="form-group">
													<label class="col-lg-2 control-label">Extend Letter:</label>
													<div class="col-lg-5">
														<input type="file" name="extentionLetter" id="extentionLetter" class="form-control" />
													</div>
												</div>	
											</c:if>
			
											<div class="form-group">
												<c:choose>
													<c:when test="${edit}">
														<div class="col-lg-offset-2 col-lg-1 col-xs-4" id="">
															<button type="button" name="submit_btn" value="update"
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
															<button type="button" name="submit_btn" value="save"
																class="btn btn-sm btn-primary req-save-update-btn">
																Save</button>
														</div>
														<div class="col-lg-1 col-xs-4">
															<button type="reset" class="btn btn-sm btn-danger ">Reset</button>
														</div>
													</c:otherwise>
												</c:choose>
											</div>
										</div>
										<c:choose>
											<c:when test="${edit}">
												<div class="col-md-2">
													<div class="pull-right">
														<div class="col-lg-2">
															<img src='<c:url value="/candidatePhoto/${candidate.candId}"/>'
																alt="image"
																style="width: 200px; height: auto; border: solid 2px #36a9e1;" />
														</div>
													</div>
												</div>
												
												<c:if test="${candidate.uploadAppLetter eq 1}">
													<div class="col-md-2">
														<div class="pull-right">
															<div class="col-lg-2">
																<a class="btn btn-warning btn-sm" target="_blank" href='<c:url value="/candidateAppLetter/${candidate.candId}"/>.pdf'> Download App Letter</a>
																
															</div>
														</div>
													</div>
												</c:if>
												
											</c:when>
										</c:choose>
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
							$('.employeeList').select2();

							$('.req-save-update-btn')
									.click(
											function() {
												var flag = false;
												$(".req-save-update-btn").prop(
														'disabled', true);

												/* if ($('#candId').val().trim().length > 0) {
													flag = false;
												} else {
													flag = true;
													$(".req-save-update-btn")
															.prop('disabled',
																	false);
													w2alert("Please Candidate ID.");
													return flag;
												} */

												if ($('#candName').val().trim().length > 0) {
													flag = false;

												} else {
													flag = true;
													$(".req-save-update-btn")
															.prop('disabled',
																	false);
													w2alert("Please Input Candidate Name.");
													return flag;
												}

												if ($('#recuriteById').val()
														.trim().length > 0) {
													flag = false;
												} else {
													flag = true;
													$(".req-save-update-btn")
															.prop('disabled',
																	false);
													w2alert("Please Select Recruit BY.");
													return flag;
												}

												if ($('#companyId').val()
														.trim().length > 0) {
													flag = false;
												} else {
													flag = true;
													$(".req-save-update-btn")
															.prop('disabled',
																	false);
													w2alert("Please Select a Company.");
													return flag;
												}

												if ($('#departmentId').val()
														.trim().length > 0) {
													flag = false;
												} else {
													flag = true;
													$(".req-save-update-btn")
															.prop('disabled',
																	false);
													w2alert("Please Select a Department.");
													return flag;
												}
												if ($('#designationId').val()
														.trim().length > 0) {
													flag = false;
												} else {
													flag = true;
													$(".req-save-update-btn")
															.prop('disabled',
																	false);
													w2alert("Please Select a Designation.");
													return flag;
												}

												if ($('#dateOfJoin').val()
														.trim().length > 0) {
													flag = false;
												} else {
													flag = true;
													$(".req-save-update-btn")
															.prop('disabled',
																	false);
													w2alert("Please Input Joining Date.");
													return flag;
												}

												if ($('#sourceOfCand').val()
														.trim().length > 0) {
													flag = false;
												} else {
													flag = true;
													$(".req-save-update-btn")
															.prop('disabled',
																	false);
													w2alert("Please Input Source.");
													return flag;
												}

												if ($('#candEmail').val()
														.trim().length > 0) {
													var filter = validateEmail($('#candEmail').val());
													
													if (!filter.test($('#candEmail').val())) {
														  $(".req-save-update-btn").prop('disabled', false);
														  $("#candEmail").focus();
													      w2alert("Invalid Email Address");
													      return flag;
													  } else{
														  flag = false;
													  }
													
												} else {
													flag = true;
													$(".req-save-update-btn")
															.prop('disabled',
																	false);
													w2alert("Please Input Candidate Email.");
													return flag;
												}

												if ($('#candMobileNo').val()
														.trim().length > 0) {
													flag = false;
												} else {
													flag = true;
													$(".req-save-update-btn")
															.prop('disabled',
																	false);
													w2alert("Please Input Candidate Mobile No. Do not use any gap or special Charecter");
													return flag;
												}

												if (!flag) {
													$(".req-save-update-btn")
															.prop('disabled',
																	true);
													$('#employeeForm').submit();
												} else {
													$(".req-save-update-btn")
															.prop('disabled',
																	false);
													return flag;
												}
											});

						});

		/* $(document).on('blur',"#candEmail",function() {
			var checkCandidateEmailIdUrl = '<c:out value="${checkCandidateEmailId}"/>';
			var candidateId = $('#id').val();
				if ($('#candEmail').val().trim().length > 0) {
					$.ajax({
						type : "post",
						url : checkCandidateEmailIdUrl,
						async : false,
						data : JSON.stringify({
							candEmail : $(this).val(), candId : candidateId
						}),
						contentType : "application/json",
						success : function(response) {
							result = JSON.parse(response);
							if (result == 'success') {

							} else {
								$('#candEmail').val('');
								w2alert("This Candidate Already Exists. You Can't Use Duplicate Email.");
								return;
							}

						},
						error : function() {
						}
					});
				}
			}); */
			

			$(function () {
			    $('#appLetter').change(function () {
			        var val = $(this).val().toLowerCase(),
			            regex = new RegExp("(.*?)\.(pdf)$");

			        if (!(regex.test(val))) {
			            $(this).val('');
			            w2alert('Please select PDF file format only.');
			        }
			    });
			    
			    $('#extentionLetter').change(function () {
			        var val = $(this).val().toLowerCase(),
			            regex = new RegExp("(.*?)\.(pdf)$");

			        if (!(regex.test(val))) {
			            $(this).val('');
			            w2alert('Please select PDF file format only.');
			        }
			    });		    
			    
			    
			    $('#photo').change(function () {
			        var val = $(this).val().toLowerCase(),
			            regex = new RegExp("(.*?)\.(jpg|png)$");

			        if (!(regex.test(val))) {
			            $(this).val('');
			            w2alert('Please select JPG or PNG file format only.');
			        }
			    });
			    
			    /* $("#candEmail").on("blur", function(){
				    var email = $("#candEmail").val();
				    var filter = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
				    if (!filter.test(email)) {
				      w2alert("Invalid Email Address");
				      $("#candEmail").val('');
				    } 
				  }); */
			    
			});
			
		function validateEmail($email) {
			var email = $("#candEmail").val();
		    var filter = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
		    return filter;		     
		}
		
		/* function validateEmail($email) {
			  var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
			  return emailReg.test( $email );
		} */
		
		

		$(function() {
			var month = (new Date()).getMonth() + 1;
			var year = (new Date()).getFullYear();
			
			$('input[type=eu-date]').w2field('date',  { format: 'yyyy-mm-dd' });
			
			// US Format
			$('input[type=eu-date1]').w2field('date', {
				format : 'yyyy-mm-dd',
				end : $('input[type=eu-date2]')
			});
			$('input[type=eu-date2]').w2field('date', {
				format : 'yyyy-mm-dd',
				start : $('input[type=eu-date1]')
			});
		});
	</script>

</body>
</html>