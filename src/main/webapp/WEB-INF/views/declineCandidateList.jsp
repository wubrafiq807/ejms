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

<link href="resource/w2ui/w2ui-1.5.rc1.min.css" rel="stylesheet">
<link href="resource/w2ui/w2ui-1.5.rc1.css" rel="stylesheet">
<script src="resource/w2ui/w2ui-1.5.rc1.min.js"></script>
<script src="resource/w2ui/w2ui-1.5.rc1.js"></script>

<link href="resource/dataTable/buttons.dataTables.min.css"
	rel="stylesheet">
<script src="resource/dataTable/jquery-1.12.3.js"></script>
<script src="resource/dataTable/jquery.dataTables.min.js"></script>
<script src="resource/dataTable/dataTables.buttons.min.js"></script>
<script src="resource/dataTable/buttons.flash.min.js"></script>
<script src="resource/dataTable/jszip.min.js"></script>
<script src="resource/dataTable/pdfmake.min.js"></script>
<script src="resource/dataTable/vfs_fonts.js"></script>
<script src="resource/dataTable/buttons.html5.min.js"></script>
<script src="resource/dataTable/buttons.print.min.js"></script>




<style type="text/css">
td img {
	display: block;
	margin-left: auto;
	margin-right: auto;
}

.centered {
	width: 50px;
	margin: 0px, auto, 0px, auto;
}
</style>



<script type="text/javascript">
	function goBack() {
		window.history.back();
	}

	$(document).ready(function() {
		var table = $('#table1').DataTable({
			dom : 'Bfrtip',
			buttons : [ 'copy', 'csv', {
				extend : 'excel',
				exportOptions : {
					columns : ':visible'
				}
			}, 'print', {
				extend : 'pdfHtml5',
				orientation : 'landscape',
				pageSize : 'LEGAL',
				exportOptions : {
					columns : ':visible'
				}
			} ]
		});

		$('a.toggle-vis').on('click', function(e) {
			e.preventDefault();
			var column = table.column($(this).attr('data-column'));
			column.visible(!column.visible());
		});

		$('input.toggle-vis').on('change', function(e) {
			e.preventDefault();
			var column = table.column($(this).attr('data-column'));
			column.visible(!column.visible());
		});

	});

	function confirmationDelete(id) {
		// href="removeDbConnect?id=${dbCon.id}"
		var contextPath = $('#contextPath').val();
		var url = contextPath + '/removeAi?id=' + id;
		w2confirm('Are you sure remove this permanently?',
				function btn(answer) {
					if (answer == 'Yes') {
						window.location = url;
					} else {
						//
					}
				});
		return false;
	}
</script>



</head>

<body>

	<input type="hidden" value="${pageContext.request.contextPath}"
		id="contextPath">

	<div class="page-head">
		<h2 class="pull-left" style="color: #428bca;">Deleted &amp;
			Declined Candidate List</h2>
		<div class="clearfix"></div>
	</div>

	<!--   <div class="matter"> -->
	<div class="container">

		<div class="row">

			<div class="col-md-12">

				<!-- Table -->

				<div class="row">

					<div class="col-md-12">

						<div class="widget">

							<div class="widget-head">
								<div class="pull-left">Deleted &amp; Declined Candidate
									List Details</div>
								<div class="widget-icons pull-right">
									<a href="#" class="wminimize"><i class="fa fa-chevron-up"></i></a>
									<a href="#" class="wclose"><i class="fa fa-times"></i></a>
								</div>
								<div class="clearfix"></div>
							</div>

							<div>
								<label><input type="checkbox" class="toggle-vis"
									data-column="0" checked="checked"> Sl. No</label> <label><input
									type="checkbox" class="toggle-vis" data-column="1"
									checked="checked"> Candidate ID</label> <label><input
									type="checkbox" class="toggle-vis" data-column="2"
									checked="checked">Candidate Name</label> <label><input
									type="checkbox" class="toggle-vis" data-column="3"
									checked="checked"> Recruit By</label> <label><input
									type="checkbox" class="toggle-vis" data-column="4"
									checked="checked"> Recruit For</label> <label><input
									type="checkbox" class="toggle-vis" data-column="5"
									checked="checked"> Source</label> <label><input
									type="checkbox" class="toggle-vis" data-column="6"
									checked="checked"> Date of Join</label> <label><input
									type="checkbox" class="toggle-vis" data-column="7"
									checked="checked"> Email</label> <label><input
									type="checkbox" class="toggle-vis" data-column="8"
									checked="checked"> Contact No</label> <label><input
									type="checkbox" class="toggle-vis" data-column="9"
									checked="checked"> Status</label> 
									
									<label><input
									type="checkbox" class="toggle-vis" data-column="10"
									checked="checked"> Decline Reason</label> 
									
									<label><input
									type="checkbox" class="toggle-vis" data-column="11"
									checked="checked"> Decline Date</label> 
									
									<label><input
									type="checkbox" class="toggle-vis" data-column="12"
									checked="checked"> Remarks</label> 
									
									<label><input
									type="checkbox" class="toggle-vis" data-column="13"
									checked="checked"> Action</label>
							</div>

							<div class="widget-content">

								<div class="table-responsive">


									<c:if test="${!empty declineCandidateList}">
										<table class="table table-bordered table-hover" id="table1">
											<thead>
												<tr style="background-color: #428bca; color: white">
													<th class="text-center">Sl. No.</th>
													<th class="text-center">Candidate ID</th>
													<th class="text-center">Candidate Name</th>
													<th class="text-center">Recruit By</th>
													<th class="text-center">Recruit For</th>
													<th class="text-center">Source</th>
													<th class="text-center">Date Of Join</th>
													<th class="text-center">Email</th>
													<th class="text-center">Contact No</th>
													<th class="text-center">Status</th>
													<th class="text-center">Decline Reason</th>
													<th class="text-center">Decline Date</th>
													<th class="text-center">Remarks</th>
													<th class="text-center">Action</th>



												</tr>
											</thead>
											<tbody>
												<c:forEach items="${declineCandidateList}" var="candidate"
													varStatus="cand">
													<tr>
														<td class="text-center">${cand.count}</td>
														<td class="text-center">${candidate.candId}</td>
														<td class="text-center">${candidate.candName}</td>
														<td class="text-center">${candidate.hrEmployee.name}
															- (${candidate.hrEmployee.empId})</td>
														<td class="text-center">${candidate.company.name}</td>
														<td class="text-center">${candidate.sourceOfCand}</td>
														<td class="text-center">${candidate.dateOfJoin}</td>
														<td class="text-center">${candidate.candEmail}</td>
														<td class="text-center">${candidate.candMobileNo}</td>
														<td class="text-center"><c:if
																test="${candidate.status eq 2}">
																<p class="btn btn-warning btn-sm">Deleted</p>
															</c:if> <c:if test="${candidate.status eq 6}">
																<p class="btn btn-danger btn-sm">Declined</p>
															</c:if></td>
														<td class="text-center">${candidate.declineReason}</td>
														<td class="text-center">${candidate.declineDate}</td>
														<td class="text-center">${candidate.remarks}</td>
														<td align="center">
															<c:if test="${candidate.status eq 6}">
															<a class="btn btn-sm btn-info custom-width" title="Profile View" target="_blank"
																href="${pageContext.request.contextPath}/candidateProfileForm?id=${candidate.id}"
																style="color: white;"><i class="fa fa-street-view"> </i></a>	
															</c:if>													
														</td>
													</tr>
												</c:forEach>

											</tbody>
										</table>
									</c:if>
								</div>


							</div>
						</div>

					</div>

				</div>

			</div>

		</div>

	</div>
	<!-- table ends -->
	<!-- Mainbar ends -->
	<div class="clearfix"></div>




</body>
</html>