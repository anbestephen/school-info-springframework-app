<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<c:if test="${!empty message}">
	<div class="alert alert-success"><i class="fa fa-check-square-o fa-lg"></i> &nbsp;&nbsp;${message}</div>
</c:if>

<c:if test="${!empty error}">
	<div class="alert alert-danger"><i class="fa fa-exclamation-triangle fa-lg"></i> &nbsp;&nbsp;${error}</div>
</c:if>
