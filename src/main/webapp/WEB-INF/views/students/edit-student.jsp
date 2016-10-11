<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<tiles:insertDefinition name="mainTemplate">
	<tiles:putAttribute name="title" value="Edit Student" />
	<tiles:putAttribute name="body">
		<div class="container content dialog">
			<div class="panel panel-default">
				<div class="panel-heading">Edit Student</div>
				<div class="panel-body">
					<form class="form-horizontal" method="post" enctype="multipart/form-data"
						action='<c:url value="/student/edit" />'>
						<div class="form-group">
							<label for="firstName" class="control-label col-sm-4">
								</label>
							<div class="col-sm-8">
								<img src='<c:url value="/student/photo/${student.id}"/>'
									class="thumbnail" />
							</div>
						</div>
						<div class="form-group">
							<label for="studentPhoto" class="control-label col-sm-4">
								Photo:</label>
							<div class="col-sm-8">
								<input type="file" name="studentPhoto">
							</div>
						</div>
						<div class="form-group">
							<label for="firstName" class="control-label col-sm-4">First
								Name:</label>
							<div class="col-sm-8">
								<input type="text" name="firstName" class="form-control"
									value="${student.firstName}" />
							</div>
						</div>
						<div class="form-group">
							<label for="firstName" class="control-label col-sm-4">Last
								Name:</label>
							<div class="col-sm-8">
								<input type="text" name="lastName" class="form-control"
									value="${student.lastName}" />
							</div>
						</div>
						<div class="form-group">
							<label for="age" class="control-label col-sm-4">Age:</label>
							<div class="col-sm-8">
								<input type="text" name="age" class="form-control"
									value="${student.age}" />
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-sm-4">Gender:</label>
							<div class="col-sm-8">
								<div class="checkbox">
									<label> <input type="radio" name="gender" value="M"
										<c:if test="${student.gender eq 'M'.charAt(0)}">checked="checked"</c:if> />
										Male
									</label> <label> <input type="radio" name="gender" value="F"
										<c:if test="${student.gender == 'F'.charAt(0)}">checked="checked"</c:if> />
										Female
									</label>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-4 col-sm-8">
								<input type="hidden" name="studentId" value="${student.id}" />
								<button type="submit" class="btn btn-default ">Update</button>
								&nbsp; <a href='<c:url value="/student/" />'
									class="btn btn-default ">Cancel</a>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>