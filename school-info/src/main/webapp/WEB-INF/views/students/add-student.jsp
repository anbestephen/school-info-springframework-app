<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<tiles:insertDefinition name="mainTemplate">
	<tiles:putAttribute name="title" value="Add Student" />
	<tiles:putAttribute name="body">
		<div class="container content dialog">
			<div class="panel panel-default">
				<div class="panel-heading">Add Student</div>
				<div class="panel-body">
					<form class="form-horizontal" method="post" enctype="multipart/form-data"
						action='<c:url value="/student/add" />'>
						<div class="form-group">
							<label for="firstName" class="control-label col-sm-4">First
								Name:</label>
							<div class="col-sm-8">
								<input type="text" name="firstName" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label for="firstName" class="control-label col-sm-4">Last
								Name:</label>
							<div class="col-sm-8">
								<input type="text" name="lastName" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label for="age" class="control-label col-sm-4">Age:</label>
							<div class="col-sm-8">
								<input type="text" name="age" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-4">Gender:</label>
							<div class="col-sm-8">
								<div class="checkbox">
									<label> <input type="radio" name="gender" value="M"
										checked /> Male
									</label> <label> <input type="radio" name="gender" value="F" />
										Female
									</label>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label for="age" class="control-label col-sm-4">Photo:</label>
							<div class="col-sm-8">
								<input type="file" name="studentPhoto" class="" />
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-4 col-sm-8">
								<button type="submit" class="btn btn-default ">Save
									Student</button>
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