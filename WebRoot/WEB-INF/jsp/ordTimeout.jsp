
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<HTML>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link rel="shortcut icon" href="conant.ico"/>
<TITLE><spring:message code="timeout.title"/></TITLE>
</HEAD>
<LINK href="<c:url value="/resources/style/style.css"/>" type="text/css" rel="stylesheet">
<STYLE type="text/css">
.style2
{
	FONT-SIZE: 14px;
	COLOR: #ff0000
}
</STYLE>

<SCRIPT>

</SCRIPT>
<BODY>
<TABLE cellSpacing=0 cellPadding=0 width="95%" align=center border=0>
	<TBODY>
		<TR>
			<TD height=60>&nbsp;</TD>
		</TR>
	</TBODY>
</TABLE>
<TABLE cellSpacing=0 cellPadding=0 width=534 align=center border=0>
	<TBODY>
		<TR>
			<TD colSpan=3>
				<IMG height=42 src="<c:url value="/resources/images/error_r1_c1.gif"/>"	width=534 border=0>
			</TD>
			<TD><IMG height=42 src="" width=1 border=0></TD>
		</TR>
		<TR>
			<TD rowSpan=3>
				<IMG height=239 src="<c:url value="/resources/images/error_r2_c1.gif"/>" width=43 border=0>
			</TD>
			<TD class="htd" align="center" width=479 bgColor=#f7f7f7 height=228>
			<TABLE cellSpacing=6 cellPadding=6 width="85%" border=0>
				<TBODY>
					<TR>
						<TD>
						<DIV class="style2" align=center>
							<spring:message code="timeout.tip"/>
						</DIV>
						</TD>
					</TR>
					<TR>
						<TD align="center">
							<A class=back href="<c:url value="index.html"/>" target="_top"><spring:message code="operation.back"/></A>
						</TD>
					</TR>
				</TBODY>
			</TABLE>
			</TD>
			<TD rowSpan=3>
				<IMG height=239 src="<c:url value="/resources/images/error_r2_c3.gif"/>" width=12 border=0>
			</TD>
			<TD><IMG height=228 src="" width=1 border=0></TD>
		</TR>
		<TR>
			<TD>
				<IMG height=11 src="<c:url value="/resources/images/error_r3_c2.gif"/>" width=479 border=0>
			</TD>
		</TR>
	</TBODY>
</TABLE>
</BODY>
</HTML>
