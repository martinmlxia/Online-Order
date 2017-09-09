
<%@ page contentType="text/html; charset=utf-8" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>menu</title>
<link href="<c:url value="/resources/style/csstab.css"/>" type="text/css" rel="stylesheet">
</head>

<body>
<table width="270" height="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="270" height="400" valign="top" background="<c:url value="/resources/images/14.png"/>">
		<table width="100%" border="0" align="center" cellspacing="0" cellpadding="0">
			<tr>
				<td align="center">
					<div id="tabmenu">
						<ul>
						<c:forEach var="menuItem1" items="${success}" varStatus="itemStatus1">
							<c:if test="${menuItem1.func_Pid != -1 && menuItem1.func_Type == 0}">
								<li>
									<c:choose>
									<c:when test="${fn:length(menuItem1.func_URL) > 0}">
										<c:set value="" var="ctx"/>
										<c:set value="" var="ctxUrl"/>
										<c:set value="${fn:contains(menuItem1.func_URL, '?')}" var="qExist"/>
										<c:choose>
											<c:when test="${fn:startsWith(menuItem1.func_URL, '/')}">
												<c:set value="${fn:split(menuItem1.func_URL, '/')}" var="ctxItems"/>
												<c:set value="/${ctxItems[2]}" var="ctx"/>
												<c:set value="${fn:length(ctx)}" var="ctxBegin"/>
												<c:set value="${fn:length(menuItem1.func_URL)}" var="ctxEnd"/>
												<c:set value="/${fn:substring(menuItem1.func_URL, ctxBegin, ctxEnd)}" var="ctxUrl"/>
											</c:when>
											<c:otherwise>
												<c:set value="/${menuItem1.func_URL}" var="ctxUrl"/>
											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${qExist}">
												<c:set value="${ctxUrl}&uid=${sessionScope.user_id}" var="ctxUrl"/>
											</c:when>
											<c:otherwise>
												<c:set value="${ctxUrl}?uid=${sessionScope.user_id}" var="ctxUrl"/>
											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${fn:length(ctx) > 0}">
												<a id="current" href="<c:url context="${ctx}" value="${ctxUrl}"/>" target="mainFrame">
											</c:when>
											<c:otherwise>
												<a id="current" href="<c:url value="${ctxUrl}"/>" target="mainFrame">
											</c:otherwise>
										</c:choose>
									</c:when>
									<c:otherwise>
										<a id="current" href="#">
									</c:otherwise>
									</c:choose>
										<spring:message code="${fn:replace(menuItem1.func_Name,' ','')}"/>
									</a>
								</li>
								<c:forEach var="menuItem2" items="${success}" varStatus="itemStatus2">
									<c:if test="${menuItem2.func_Pid != -1 && menuItem2.func_Pid == menuItem1.id && menuItem2.func_Type == 1}">
										<li>
											<c:choose>
											<c:when test="${fn:length(menuItem2.func_URL) > 0}">
												<c:set value="" var="ctx"/>
												<c:set value="" var="ctxUrl"/>
												<c:set value="${fn:contains(menuItem2.func_URL, '?')}" var="qExist"/>
												<c:choose>
													<c:when test="${fn:startsWith(menuItem2.func_URL, '/')}">
														<c:set value="${fn:split(menuItem2.func_URL, '/')}" var="ctxItems"/>
														<c:set value="/${ctxItems[2]}" var="ctx"/>
														<c:set value="${fn:length(ctx)}" var="ctxBegin"/>
														<c:set value="${fn:length(menuItem2.func_URL)}" var="ctxEnd"/>
														<c:set value="/${fn:substring(menuItem2.func_URL, ctxBegin, ctxEnd)}" var="ctxUrl"/>
													</c:when>
													<c:otherwise>
														<c:set value="/${menuItem2.func_URL}" var="ctxUrl"/>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${qExist}">
														<c:set value="${ctxUrl}&uid=${sessionScope.user_id}" var="ctxUrl"/>
													</c:when>
													<c:otherwise>
														<c:set value="${ctxUrl}?uid=${sessionScope.user_id}" var="ctxUrl"/>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${fn:length(ctx) > 0}">
														<a href="<c:url context="${ctx}" value="${ctxUrl}"/>" target="mainFrame">
													</c:when>
													<c:otherwise>
														<a href="<c:url value="${ctxUrl}"/>" target="mainFrame">
													</c:otherwise>
												</c:choose>
											</c:when>
											<c:otherwise>
												<a href="#">
											</c:otherwise>
											</c:choose>
												&nbsp;&nbsp;<spring:message code="${fn:replace(menuItem2.func_Name,' ','')}"/>
											</a>
										</li>
									</c:if>
								</c:forEach>
							</c:if>
						</c:forEach>
						</ul>
					</div>
				</td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td height="400" valign="top" background="<c:url value="/resources/images/14.png"/>">&nbsp;</td>
	</tr>
</table>
</body>
</html>
<script type="text/javascript">
  var $=document.getElementsByTagName("a");
  for(var i=0;i<$.length;i++){
	  if($[i].id=="current"){
		$[i].onclick=function(){
		  var _$=this.parentNode; var __$=_$;
		    while(__$=__$.nextSibling){
			    if(__$.firstChild.id=="current") break;
				__$.style.display= (__$.style.display=="") ? "none":"";
			}
		}  
	  }  
  }
</script>
