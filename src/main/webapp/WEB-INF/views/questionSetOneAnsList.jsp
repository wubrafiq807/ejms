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
		<h2 class="pull-left" style="color: #428bca;">Questions Set One Answer List</h2>
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
								<div class="pull-left">Questions Set One Ans List</div>
								<div class="widget-icons pull-right">
									<a href="#" class="wminimize"><i class="fa fa-chevron-up"></i></a>
									<a href="#" class="wclose"><i class="fa fa-times"></i></a>
								</div>
								<div class="clearfix"></div>
							</div>

							

							<div class="widget-content">

								<div class="table-responsive">


									<c:if test="${!empty questionSetOneList}">
										<table class="table table-bordered table-hover" id="table1">
											<thead>
												<tr style="background-color: #428bca; color: white">
													<th class="text-center">Sl. No.</th>
													<th class="text-center">Candidate ID</th>
													<th class="text-center">Candidate Name</th>
													<th class="text-center">Answer One</th>
													<th class="text-center">Answer Two</th>
													<th class="text-center">Answer Three</th>
													<th class="text-center">Answer Four</th>
													<th class="text-center">Answer Five</th>
													<th class="text-center">Ratings</th>
													<th class="text-center">Action</th>



												</tr>
											</thead>
											<tbody>
												<c:forEach items="${questionSetOneList}" var="list"
													varStatus="cand">
													<tr>
														<td class="text-center">${cand.count}</td>
														<td class="text-center">${list.candidate.id}</td>
														<td class="text-center">${list.candidate.candName}</td>
														<td class="text-center">${list.answerOne}</td>
														<td class="text-center">${list.answerTwo}</td>
														<td class="text-center">${list.answerThree}</td>
														<td class="text-center">${list.answerFour}</td>
														<td class="text-center">${list.answerFive}</td>
														<td class="text-center">${list.npsResult}</td>
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