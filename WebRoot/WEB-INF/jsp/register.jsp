
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html> 
<head>
<link href="resources/style/style_register.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--

-->
</style>

<script language="JavaScript" src="resources/js/validation.js"></script>
<script language="JavaScript" src="resources/js/isBlank.js"></script>
<script language="JavaScript" src="resources/js/vw_divwrite.js"></script>
<script language="JavaScript" src="resources/js/phone.js"></script>
<script language="JavaScript" src="resources/js/include.js"></script>

<script language="JavaScript">

  var beensubmitted = false;

  function save(){
  		
		_write('busInfoError', '<span class="copyspacer5"><br /></span>');		
		_write('usrInfoError', '<span class="copyspacer4"><br /></span>');
		_write('businessNameError', '<b><font face="Arial" size="1" color="#000000"><span class="fieldtag">Business/Practice Name</span></font></b><img src="resources/images/ast2.gif" width="9" height="13" alt="" border="0" />');
		_write('streetNumError', '<b><font face="Arial" size="1" color="#000000"><span class="fieldtag">Street Number</span></font></b>');
		_write('streetError', '<b><font face="Arial" size="1" color="#000000"><span class="fieldtag">Street Name</span></font></b><img src="resources/images/ast2.gif" width="9" height="13" alt="" border="0" />');
		<!--_write('cityError', '<b><font face="Arial" size="1" color="#000000"><span class="fieldtag">City</span></font></b><img src="resources/images/ast2.gif" width="9" height="13" alt="" border="0" />');-->
		_write('stateError', '<b><font face="Arial" size="1" color="#000000"><span class="fieldtag">State</span></font></b><img src="resources/images/ast2.gif" width="9" height="13" alt="" border="0" />');
		_write('zipCodeError', '<b><font face="Arial" size="1" color="#000000"><span class="fieldtag">Zip</span></font></b><img src="resources/images/ast2.gif" width="9" height="13" alt="" border="0" />');
		_write('busphoneError', '<b><font face="Arial" size="1" color="#000000"><span class="fieldtag">Business Phone</span></font></b><img src="resources/images/ast2.gif" width="9" height="13" alt="" border="0" />');
		_write('busextensionError', '<b><font face="Arial" size="1" color="#000000"><span class="fieldtag">Ext.</span></font></b>');
		_write('firstNameError','<b><font face="Arial" size="1" color="#000000"><span class="fieldtag">First Name</span></font></b><img src="resources/images/ast2.gif" width="9" height="13" alt="" border="0" />');
        _write('lastNameError', '<b><font face="Arial" size="1" color="#000000"><span class="fieldtag">Last Name</span></font></b><img src="resources/images/ast2.gif" width="9" height="13" alt="" border="0" />');
		_write('usrEmailError','<b><font face="Arial" size="1" color="#000000"><span class="fieldtag">E-mail Address</span></font></b><img src="resources/images/ast2.gif" width="9" height="13" alt="" border="0" />');
		_write('usrEmailCError','<b><font face="Arial" size="1" color="#000000"><span class="fieldtag">Confirm E-mail Address</span></font></b><img src="resources/images/ast2.gif" width="9" height="13" alt="" border="0" />');
		_write('usrphoneError', '<b><font face="Arial" size="1" color="#000000"><span class="fieldtag">Phone</span></font></b><img src="resources/images/ast2.gif" width="9" height="13" alt="" border="0" />');
		_write('usrextensionError', '<b><font face="Arial" size="1" color="#000000"><span class="fieldtag">Ext.</span></font></b>');
		_write('usrIdError', '<b><font face="Arial" size="1" color="#000000"><span class="fieldtag">Username</span><img src="resources/images/ast2.gif" width="9" height="13" alt="" border="0" /> (see note)</font></b>');
		_write('usrPswdError', '<b><font face="Arial" size="1" color="#000000"><span class="fieldtag">Password</span><img src="resources/images/ast2.gif" width="9" height="13" alt="" border="0" /> (see note)</font></b>');
        _write('usrPswdCError', '<b><font face="Arial" size="1" color="#000000"><span class="fieldtag">Confirm Password</span><img src="resources/images/ast2.gif" width="9" height="13" alt="" border="0" /> (see note)</font></b>');
	    _write('termAgreeError', '<FONT FACE="Arial" SIZE="2" COLOR="#000000">Yes. I have read, and agree to, the Online Order System <A HREF=\'http://www.conant.com\' target="_blank" alt="">Terms and Conditions</A>.</FONT>');
 	    _write('policyAgreeError', '<FONT FACE="Arial" SIZE="2" COLOR="#000000">Yes. I have read, and understand, the Online Order System <A HREF=\'http://www.conant.com\' target="_blank" alt="">Privacy Policy</A>.</FONT>');

		var error;
		var buserror = '<span class="copyspacer3"><br /></span><table cellspacing="0" cellpadding="2" border="0" bgcolor="#ffff99" width="600"><tr><td>';
		var usrerror = '<span class="copyspacer3"><br /></span><table cellspacing="0" cellpadding="2" border="0" bgcolor="#ffff99" width="600"><tr><td>';
		var buserrbool = false;
		var usrerrbool = false;
		
		var submit = true;
		var focus = false;
		

		if(!document.main_form.AgreeTnC.checked) {
			
			error='<table cellspacing="0" cellpadding="0" border="0" bgcolor="#ffff99"><tr><td><FONT FACE="Arial" SIZE="2" COLOR="#000000">Yes. I have read, and agree to, Online Order System <A HREF=\'http://www.conant.com\' target="_blank" alt="">Terms and Conditions</A>.</FONT></td></tr></table>';
	    _write('termAgreeError', error);
	    
			submit = false;
		} // end if

		if(!document.main_form.AgreePP.checked) {

 			error='<table cellspacing="0" cellpadding="0" border="0" bgcolor="#ffff99"><tr><td><FONT FACE="Arial" SIZE="2" COLOR="#000000">Yes. I have read, and understand, the Online Order System <A HREF=\'http://www.conant.com\' target="_blank" alt="">Privacy Policy</A>.</FONT></td></tr></table>';
		 	_write('policyAgreeError', error);
		 	submit = false;
		} // end if

	  var Name = document.main_form.businessName.value;  
	  
		if(isBlank(Name)){
			
			error='<table cellspacing="0" cellpadding="0" border="0" bgcolor="#ffff99"><tr><td><b><font face="Arial" size="1" color="#000000"><span class="fieldtag">Business/Practice Name</span></font></b><img src="resources/images/ast2.gif" width="9" height="13" alt="" border="0" /></td></tr></table>';
			buserror = buserror + '<font face="Arial" size="1" color="#333333">The <b>BUSINESS/PRACTICE NAME</b> is required.</font><br>';
			buserrbool = true;
			_write('businessNameError',error);
					
			submit = false;
			
			if(!focus){

					document.main_form.businessName.focus();
					focus = true;
			}
		}

    var StreetName = document.main_form.businessStreetName.value;
    //alert(StreetName + "===streetName");
    
		if(isBlank(StreetName)){
			error='<table cellspacing="0" cellpadding="0" border="0" bgcolor="#ffff99"><tr><td><b><font face="Arial" size="1" color="#000000"><span class="fieldtag">Street Name</span></font></b><img src="resources/images/ast2.gif" width="9" height="13" alt="" border="0" /></td></tr></table>';
			buserror = buserror + '<font face="Arial" size="1" color="#333333">The <b>STREET NAME</b> is required.</font><br>';
			buserrbool = true;
      _write('streetError', error);
			submit=false;
      if(!focus){
         document.main_form.businessStreetName.focus();
         focus = true;
			}
		}

		document.main_form.businessStreetNumber.value = stripCharsInBag(document.main_form.businessStreetNumber.value,' ');
		
		var StreetNum = document.main_form.businessStreetNumber.value;
		//alert(StreetNum + "===StreetNum");
		
		if(!isBlank(StreetNum) && !isNumeric(StreetNum)){
			error='<table cellspacing="0" cellpadding="0" border="0" bgcolor="#ffff99"><tr><td><b><font face="Arial" size="1" color="#000000"><span class="fieldtag">Street Number</span></font></b></td></tr></table>'
			buserror = buserror + '<font face="Arial" size="1" color="#333333">The STREET NUMBER must be numeric.</font><br>';
			buserrbool = true;
			_write('streetNumError', error);
			submit=false;
			if(!focus){
				document.main_form.businessStreetNumber.focus();
				focus = true;
			}
		}

		var i = document.main_form.businessState.selectedIndex;
		if(document.main_form.businessState.options[i].value == "00"){
			error='<table cellspacing="0" cellpadding="0" border="0" bgcolor="#ffff99"><tr><td><b><font face="Arial" size="1" color="#000000"><span class="fieldtag">State</span></font></b><img src="resources/images/ast2.gif" width="9" height="13" alt="" border="0" /></td></tr></table>';
			buserror = buserror + '<font face="Arial" size="1" color="#333333">The <b>STATE</b> is required.</font><br>';
			buserrbool = true;
			_write('stateError', error);
			submit=false;
			if(!focus){
				document.main_form.businessState.focus();
					focus = true;
			}
		}

		var theZip = document.main_form.businessZip.value;
		//alert(theZip + "===theZip");
		
		var founderr = false;
		if(isBlank(theZip)){
				buserror = buserror + '<font face="Arial" size="1" color="#333333">The ZIP CODE is required.</font><br>';
				buserrbool = true;
				founderr = true;
	 	}else{
				if(theZip.length == 10){
						var index=theZip.indexOf("-");
						if(index != 5){
								buserror = buserror + '<font face="Arial" size="1" color="#333333">The ZIP CODE format is 12345-0000</font><br>';
								buserrbool = true;
								founderr = true;
						}
						else {
							 var zipnums = theZip.substring(0,5)+ theZip.substring(6);
								if(!isNumeric(zipnums)){
										buserror = buserror + '<font face="Arial" size="1" color="#333333">The ZIP CODE must be numeric.</font><br>';
										buserrbool = true;
										founderr = true;
								}
						}
				}else{
						theZip = theZip.replace(/\-/g,"");
						if( theZip.length!=5&&theZip.length!=6&&theZip.length!=9){
								buserror = buserror + '<font face="Arial" size="1" color="#333333">The ZIP CODE must have either 5 or 6 or 9 numbers.</font><br>';
								buserrbool = true;
								founderr = true;
						}else{
								if(!isNumeric(theZip)){
										buserror = buserror + '<font face="Arial" size="1" color="#333333">The ZIP CODE must be numeric.</font><br>';
										buserrbool = true;
										founderr = true;
								}
						}

				}
		}
		if(founderr){
				error = '<table cellspacing="0" cellpadding="0" border="0" bgcolor="#ffff99"><tr><td><b><font face="Arial" size="1" color="#000000"><span class="fieldtag">Zip</span></font></b><img src="resources/images/ast2.gif" width="9" height="13" alt="" border="0" /></td></tr></table>';
				_write('zipCodeError', error);
				submit=false;
				if(!focus){
						document.main_form.businessZip.focus();
						focus = true;
				}
		}else{
				if(theZip.length == 5){
						theZip = theZip+"-0000";
				}
				if(theZip.length == 9){
						var temp = theZip.substr(0, 5) + "-" + theZip.substr(5);
						theZip = temp;
				}
				document.main_form.businessZip.value = theZip;
		}

		var Telephone = document.main_form.businessPhone.value;
		//alert(Telephone + "===Telephone");
		
		if(isBlank(Telephone)){
				error='<table cellspacing="0" cellpadding="0" border="0" bgcolor="#ffff99"><tr><td><b><font face="Arial" size="1" color="#000000"><span class="fieldtag">Business Phone</span></font></b><img src="resources/images/ast2.gif" width="9" height="13" alt="" border="0" /></td></tr></table>';
				buserror = buserror + '<font face="Arial" size="1" color="#333333">The BUSINESS PHONE is required.</font><br>';
				buserrbool = true;
			 _write('busphoneError', error);
				submit=false;
				if(!focus){
						document.main_form.businessPhone.focus();
						focus = true;
				}
		}

		var Extension = document.main_form.businessExt.value;
		//alert(Extension + "===Extension");
		
		if(!isBlank(Extension) && !isNumeric(Extension)){
				error='<table cellspacing="0" cellpadding="0" border="0" bgcolor="#ffff99"><tr><td><b><font face="Arial" size="1" color="#000000"><span class="fieldtag">Ext.</span></font></b></td></tr></table>';
				buserror = buserror + '<font face="Arial" size="1" color="#333333">The EXT must be numeric.</font><br />';
				buserrbool = true;
				_write('busextensionError', error);
				submit=false;
				if(!focus){
						document.main_form.businessExt.focus();
						focus = true;
				}
		}


		var FirstName = document.main_form.firstName.value;
		//alert(FirstName + "===FirstName");
		
		if(isBlank(FirstName)){
			error = '<table cellspacing="0" cellpadding="0" border="0" bgcolor="#ffff99"><tr><td><b><font face="Arial" size="1" color="#000000"><span class="fieldtag">First Name</span></font></b><img src="resources/images/ast2.gif" width="9" height="13" alt="" border="0" /></td></tr></table>';
			usrerror = usrerror + '<font face="Arial" size="1" color="#333333">The FIRST NAME  is required.</font><br>';
			usrerrbool = true;
			_write('firstNameError',error);
			submit=false;
			if(!focus){
				document.main_form.firstName.focus();
				focus = true;
			}
		}


		var LastName = document.main_form.lastName.value;
		//alert(LastName + "===LastName");
		
		if(isBlank(LastName)){
				error='<table cellspacing="0" cellpadding="0" border="0" bgcolor="#ffff99"><tr><td><b><font face="Arial" size="1" color="#000000"><span class="fieldtag">Last Name</span></font></b><img src="resources/images/ast2.gif" width="9" height="13" alt="" border="0" /></td></tr></table>';
				usrerror = usrerror + '<font face="Arial" size="1" color="#333333">The LAST NAME  is required.</font><br />';
				usrerrbool = true;
				_write('lastNameError', error);
				submit=false;
				if(!focus){
						document.main_form.lastName.focus();
						focus = true;
				}
		}

		var Telephone2 = document.main_form.mobile.value;
		//alert(Telephone2 + "===Telephone2");
		
		if(isBlank(Telephone2)){
			error='<table cellspacing="0" cellpadding="0" border="0" bgcolor="#ffff99"><tr><td><b><font face="Arial" size="1" color="#000000"><span class="fieldtag">Phone</span></font></b><img src="resources/images/ast2.gif" width="9" height="13" alt="" border="0" /></td></tr></table>';
			usrerror = usrerror + '<font face="Arial" size="1" color="#333333">The Contact PHONE is required.</font><br>';
			usrerrbool = true;
			_write('usrphoneError', error);
			submit=false;
			if(!focus){
				document.main_form.mobile.focus();
				focus = true;
			}
		}

		var Extension2 = document.main_form.home_tel.value;
		//alert(Extension2 + "===Extension2");
		
		if(!isBlank(Extension2) && !isNumeric(Extension2)){
			error='<table cellspacing="0" cellpadding="0" border="0" bgcolor="#ffff99"><tr><td><b><font face="Arial" size="1" color="#000000"><span class="fieldtag">Ext.</span></font></b></td></tr></table>';
			usrerror = usrerror + '<font face="Arial" size="1" color="#333333">The Contact EXT must be numeric.</font><br />';
			usrerrbool = true;
			_write('usrextensionError', error);
			submit=false;
			if(!focus){
				document.main_form.home_tel.focus();
				focus = true;
			}
		}


		var Email = document.main_form.email.value;
		//alert(Email + "===Email");
		
		founderr = false;
		if(isBlank(Email)){
			usrerror = usrerror + '<font face="Arial" size="1" color="#333333">The Contact EMAIL is required.</font><br />';
			usrerrbool = true;
			founderr = true;
		}else{
			if(!isEmail(Email)){
				usrerror = usrerror + '<font face="Arial" size="1" color="#333333">A valid Contact EMAIL is required.</font><br />';
				usrerrbool = true;
				founderr = true;
			}
		}

		if(founderr){
			error='<table cellspacing="0" cellpadding="0" border="0" bgcolor="#ffff99"><tr><td><b><font face="Arial" size="1" color="#000000"><span class="fieldtag">E-mail Address</span></font></b><img src="resources/images/ast2.gif" width="9" height="13" alt="" border="0" /></td></tr></table>';
			_write('usrEmailError', error);
			submit=false;
			if(!focus){
				document.main_form.email.focus();
				focus = true;
			}
		}

		var EmailC = document.main_form.emailConfirm.value;
		//alert(EmailC + "===EmailC");
		
		founderr = false;
		if(isBlank(EmailC)){
			usrerror = usrerror + '<font face="Arial" size="1" color="#333333">The CONFIRM EMAIL ADDRESS is required.</font><br />';
			usrerrbool = true;
			founderr = true;
		}else{
			if(!isEmail(EmailC)){
				usrerror = usrerror + '<font face="Arial" size="1" color="#333333">A valid CONFIRM EMAIL ADDRESS is required.</font><br />';
				usrerrbool = true;
				founderr = true;
			}
			else {
				if(EmailC != Email) {
				usrerror = usrerror + '<font face="Arial" size="1" color="#333333">The CONFIRM EMAIL ADDRESS must be identical to the entered EMAIL ADDRESS</font><br />';
				usrerrbool = true;
				founderr = true;
				}
			}
		}

		if(founderr){
			error='<table cellspacing="0" cellpadding="0" border="0" bgcolor="#ffff99"><tr><td><b><font face="Arial" size="1" color="#000000"><span class="fieldtag">Confirm E-mail Address</span></font></b><img src="resources/images/ast2.gif" width="9" height="13" alt="" border="0" /></td></tr></table>';
			_write('usrEmailCError', error);
			submit=false;
			if(!focus){
				document.main_form.emailConfirm.focus();
				focus = true;
			}
		}

		var username = document.main_form.user_tag.value;
		//alert(username + "===username");
		
		founderr = false;
		if(isBlank(username)){
			usrerror = usrerror + '<font face="Arial" size="1" color="#333333">The USERNAME is required.</font><br />';
			usrerrbool = true;
			founderr = true;
		}else{
			if((username.length <4)||(username.length >20)) {
				usrerror = usrerror + '<font face="Arial" size="1" color="#333333">The USERNAME must be between 4 and 20 characters in length.</font><br />';
				usrerrbool = true;
				founderr = true;
			}else{
				if(!isContainSpChar(username)) {
						 usrerror = usrerror + '<font face="Arial" size="1" color="#333333">The USERNAME can not contain any special characters other than .,-,and _.</font><br />';
						 usrerrbool = true;
						 founderr = true;
				}
				else {
						 if(!isStartAlphaNumeric(username)) {
						usrerror = usrerror + '<font face="Arial" size="1" color="#333333">The USERNAME must start with an alphanumeric character.</font><br />';
						usrerrbool = true;
						founderr = true;
						 }
				}
			}
		}

		if(founderr){
			error='<table cellspacing="0" cellpadding="0" border="0" bgcolor="#ffff99"><tr><td><b><font face="Arial" size="1" color="#000000"><span class="fieldtag">Username</span><img src="resources/images/ast2.gif" width="9" height="13" alt="" border="0" /> (see note)</font></b></td></tr></table>';
			_write('usrIdError', error);
			submit=false;
			if(!focus){
				document.main_form.user_tag.focus();
				focus = true;
			}
		}

		var pswd = document.main_form.user_passwd.value;
		//alert(pswd + "===pswd");
		
		founderr = false;
		if(isBlank(pswd)){
			usrerror = usrerror + '<font face="Arial" size="1" color="#333333">The PASSWORD is required.</font><br />';
			usrerrbool = true;
			founderr = true;
		}else{
			if((pswd.length <6)||(pswd.length >36)){
				usrerror = usrerror + '<font face="Arial" size="1" color="#333333">The PASSWORD must be between 6 and 36 characters in length.</font><br />';
				usrerrbool = true;
				founderr = true;
			}else{
				if(!isAlphaNumeric(pswd)){
					usrerror = usrerror + '<font face="Arial" size="1" color="#333333">The PASSWORD can not contain any special characters.</font><br />';
					usrerrbool = true;
					founderr = true;
				}
			}
			if(pswd == username) {
					usrerror = usrerror + '<font face="Arial" size="1" color="#333333">The PASSWORD cannot be same as the USERNAME.</font><br />';
					usrerrbool = true;
					founderr = true;
					}
		}

		if(founderr){
			error='<table cellspacing="0" cellpadding="0" border="0" bgcolor="#ffff99"><tr><td><b><font face="Arial" size="1" color="#000000"><span class="fieldtag">Password</span></font></b><img src="resources/images/ast2.gif" width="9" height="13" alt="" border="0" /></td></tr></table>';
			_write('usrPswdError', error);
			submit=false;
			if(!focus){
				document.main_form.user_passwd.focus();
				focus = true;
			}
		}

		var pswdc = document.main_form.user_passwdConfirm.value;
		//alert(pswdc + "===pswdc");
		
		founderr = false;
		if(isBlank(pswdc)){
			usrerror = usrerror + '<font face="Arial" size="1" color="#333333">The CONFIRM PASSWORD is required.</font><br />';
			usrerrbool = true;
			founderr = true;
		}else{
			if(pswd != pswdc){
				usrerror = usrerror + '<font face="Arial" size="1" color="#333333">The CONFIRM PASSWORD must be identical to the entered PASSWORD</font><br />';
				usrerrbool = true;
				founderr = true;
			}
		}

		if(founderr){
			error='<table cellspacing="0" cellpadding="0" border="0" bgcolor="#ffff99"><tr><td><b><font face="Arial" size="1" color="#000000"><span class="fieldtag">Confirm Password</span></font></b><img src="resources/images/ast2.gif" width="9" height="13" alt="" border="0" /></td></tr></table>';
			_write('usrPswdCError', error);
			submit=false;
			if(!focus){
				document.main_form.user_passwdConfirm.focus();
				focus = true;
			}
		}

		if(buserrbool){
			buserror = buserror + '</td></tr></table><span class="copyspacer3"><br /></span>';
			_write('busInfoError',buserror);
		}

		if(usrerrbool){
			usrerror = usrerror + '</td></tr></table><span class="copyspacer3"><br /></span>';
			_write('usrInfoError',usrerror);
		}

		// check phone number format on submit as in case of some co brnads, data will be pre-populated with no formatting
		//if( document.main_form.businessPhone.value != null  ){
			//var aObj = document.main_form.businessPhone;
			//formatPhone(aObj);
		//}
		//if( document.main_form.mobile.value != null  ){
			//var aObj = document.main_form.mobile;
			//formatPhone(aObj);
		//}
		

		if(beensubmitted){
			submit = false;
		}

		if(submit){
			beensubmitted = true;
		}

			return submit;

	}
	


var p =new Array();
p[0] = "<spring:message code='register.select'/>";
p[1] = "US";
p[2] = "<spring:message code='register.country'/>";
p[3] = "Brazil";


var c =new Array();
c[0] =new Array("<spring:message code='register.select'/>");
c[1] =new Array("AK","AL","AR","AZ","CA","CO","CT","DC","DE","FL","GA","GU","HI","IA","ID","IL","IN","KS","KY","LA","MA","MD","ME","MI","MN","MO","MS","MT","NC","ND","NE","NH","NJ","NM","NV","NY","OH","OK","OR","PA","PR","RI","SC","SD","TN","TX","UT","VA","VI","VT","WA","WI","WV","WY");
c[2] =new Array("黑龙江","吉林","辽宁","河北","河南","山东","江苏","山西","陕西","甘肃","四川","青海","湖南","湖北","江西","安徽","浙江","福建","广东","广西","贵州","云南","海南","内蒙古","新疆","宁夏","西藏","北京","天津","上海","重庆","香港","澳门");
c[3] =new Array("Acre","Alagoas","Amazonas","Amapá","Bahia","Ceará","Espírito Santo","Goiás","Maranháo","Mato Grosso","Mato Grosso do Sul","Minas Gerais","Pará","Paraíba","Paraná","Pernambuco","Piauí","Rio Grande do Norte","Rio Grande do Sul","Rio de Janeiro","Rodánia","Roraima","Santa Catarina","Sáo Paulo","Sergipe","Tocantins");


/*
* 将省份添加到slect中去
*/

function set_pro_select1(so) {
///alert(123);
    for (var i = 0, n = p.length; i < n; i++ ) {
        var opt = document.createElement('option');
        opt.text = p[i];
        opt.value = i;
        // 有些浏览器不支持 options 属性的 add 方法，
        // 但支持 DOM 的 appendChild 方法（比如：Konqueror）
        if (so.options.add) {
            so.options.add(opt);
        }
        else {
            so.appendChild(opt);
        }
    }
}

/*
*将对应的城市添加到第二个select中去
*/
function set_city_select1(so,pid){
for(var i = 0,n = c[pid].length;i < n;i++){
   var opt=document.createElement('option');
   opt.text = c[pid][i];
   opt.value = i;
  
   if (so.options.add) {
            so.options.add(opt);
        }
        else {
            so.appendChild(opt);
        }
}
}

/*
*清除城市select中的内容
*/
function clear_select(so) {
    for (var i = so.options.length - 1; i > -1; i--) {
        // 有些浏览器不支持 options 属性的 remove 方法，
        // 但支持 DOM 的 removeChild 方法（比如：Konqueror）
        if (so.options.remove) {
            so.options.remove(i);
        }
        else {
            so.removeChild(so.options[i]);
        }
    }
}

/*
*改变省份
*/
function change_province(pid) {
    set_city_select(pid);
}

/*
   用来初始化省份的菜单
*/
function init(){
var so=document.getElementById('businessCountry');
set_pro_select1(so);
change_province(0);
}

function set_city_select(pid){
var so = document.getElementById('businessState');
clear_select(so);
set_city_select1(so,pid);
}


</script>

</head>
<BODY bgcolor="eeeeee" onLoad="init();">

<table border="0" cellpadding="0" cellspacing="0" width="783px" align="center">
	<tr>
		<td colspan="2" valign="top" align="center">
		<table cellspacing="0" cellpadding="0" border="0" width="630">
			<tr>
				<td width="630" valign="top" align="center">
					<form name="main_form" action="<%=request.getContextPath()%>/register.ord" method="post" onSubmit="return save()">
				<!-- Begin Page Title Section -->
				<table cellspacing="0" cellpadding="0" border="0" width="800">
					<tr>
						<td align="center">
						<table cellspacing="0" cellpadding="2" border="0" width="800" >
							<tr>
								<td>
								<table cellspacing="0" cellpadding="2" border="0" width="800" height="80" id="log" >
									<tr>
										<td width="50%">
										<div style="padding-left: 2px;" align="center">
											<span class="STYLE2">
												<font size="6" color="White"><b><spring:message code="register.title"/></b></font>
											</span> 
											  <font size="6" color="White"><b>
											  	<span class="pagetitle2 STYLE2"><spring:message code="register.menu"/></span>
											  </b></font>
											</div>
											</
										</td>
									</tr>
								</table>
								</td>
							</tr>
						</table>
						<span class="copyspacer4"><br/>
						</span>
						</td>
					</tr>
				</table>
				<!-- BEGIN Main Body  -->
				<div class="b">
				<table cellspacing="0" cellpadding="2" border="0" width="800" bgcolor="#72bd96">
					<tr>
						<td width="500" align="left">
							<img src="<c:url value="/resources/images/trans_filler.gif"/>" width="5" height="5" alt="" border="0" />
								 <B><FONT FACE="<spring:message code="register.face"/>" SIZE="2" COLOR="#ffffff">
								 	<span class="titlebartext"><spring:message code="userInfo.businessInfo"/></span>
								 	</FONT></B>
						</td>
						<td align="right" width="280"></td>
					</tr>
				</table>
                  
				<table cellspacing="0" cellpadding="3" border="0" width="800" id="cantent" >
					<tr>
						<td>
						
						<table cellspacing="0" cellpadding="0" border="0" >
							<tr>
								<td>
								<table cellspacing="2" cellpadding="0" border="0" width="794">
									<tr align="left">
										<td width="500">
										<div id="businessNameError">
											<font face="Arial" size="1" color="#000033">
												<span class="fieldtag"><spring:message code="userInfo.businessName"/></span>
											</font> 
											<img src="<c:url value="/resources/images/ast2.gif"/>" width="9" height="13" alt="" border="0" />
										</div>
										</td>
										<td>
											
										</td>
									</tr>
									<tr align="left">
										<td>
											<INPUT type="text" name="businessName" value="" size="62" onFocus="this.select()" maxlength="50" tabindex="1" />
										</td>
										<td>
											
										</td>
									</tr>
								</table>
								<table cellspacing="2" cellpadding="0" border="0"  width="794">
									<tr align="left">
										<td width="262">
										<div id="streetNumError">
											<font face="Arial" size="1" color="#000033">
												<span class="fieldtag"><spring:message code="userInfo.businessStreetNumber"/></span>
											</font>
										</div>
										</td>
										<td width="262">
										<div id="streetError">
											<font face="Arial" size="1" color="#000033">
												<span class="fieldtag"><spring:message code="userInfo.businessStreetName"/></span>
											</font>
											<img src="<c:url value="/resources/images/ast2.gif"/>" width="9" height="13" alt="" border="0" />
										</div>
										</td>
										<td width="262">
											<font face="Arial" size="1" color="#000033">
												<span class="fieldtag"><spring:message code="userInfo.businessSuit"/></span>
											</font>
										</td>
									</tr>
									<tr align="left">
										<td><input type="text" name="businessStreetNumber" value="" size="10" maxlength="6" tabindex="3" onFocus="this.select()" /></td>
										<td><input type="text" name="businessStreetName" value="" maxlength="30" onFocus="this.select()" size="28" tabindex="4" /></td>
										<td><input type="text" name="businessSuit" value="" size="33" maxlength="50" tabindex="5" /></td>
									</tr>
								</table>

								<table cellspacing="2" cellpadding="0" border="0"  width="794">
									<tr align="left">
										<td width="262">
										<div id="cityError">
											<font face="Arial" size="1" color="#000033"><span class="fieldtag"><spring:message code="userInfo.businessCity"/></span></font>
											<!--<img src="<c:url value="/resources/images/ast2.gif"/>" width="9" height="13" alt="" border="0" />-->
										</div>
										</td>
										<td width="113">
										<div id="stateError">
											<font face="Arial" size="1" color="#000033"><span class="fieldtag"><spring:message code="userInfo.businessState"/></span></font>
											<img src="<c:url value="/resources/images/ast2.gif"/>" width="9" height="13" alt="" border="0" />
										</div>
										</td>
										<td width="147">
										<div id="countryError">
											<font face="Arial" size="1" color="#000033"><span class="fieldtag"><spring:message code="userInfo.businessCountry"/></span></font>
										<img src="<c:url value="/resources/images/ast2.gif"/>" width="9" height="13" alt="" border="0" />
										</div>
										</td>
										<td width="262">
										<div id="zipCodeError">
											<font face="Arial" size="1" color="#000033">
												<span class="fieldtag"><spring:message code="userInfo.businessZip"/>(12345-0000)</span>
											</font>
										<img src="<c:url value="/resources/images/ast2.gif"/>" width="9" height="13" alt="" border="0" />
										</div>
										</td>
									</tr>
									<tr align="left">
										<td><input type="text" name="businessCity" value="" size="23" onFocus="this.select()" maxlength="30" tabindex="6" /></td>
										<td><select tabindex="7" size="1" id="businessState" name="businessState">
										</select></td>
										<td><select tabindex="7" size="1" id="businessCountry" name="businessCountry" onChange="change_province(this.value);">
										</select></td>
										<td><input type="text" name="businessZip" value="" size="10" maxlength="10" tabindex="8" onFocus="this.select()" /></td>
									</tr>
								</table>
								<table cellspacing="2" cellpadding="0" border="0"  width="794">
									<tr align="left">
										<td width="262">
										<div id="busphoneError">
											<font face="Arial" size="1" color="#000033">
												<span class="fieldtag"><spring:message code="userInfo.businessPhone"/></span></font> 
													<img src="<c:url value="/resources/images/ast2.gif"/>" width="9" height="13" alt="" border="0" />
										</div>
										</td>
										<td width="262">
										<div id="busextensionError">
											<font face="Arial" size="1" color="#000033">
												<span class="fieldtag"><spring:message code="userInfo.businessExt"/></span>
											</font>
										</div>
										</td>
										<td width="262">
											<font face="Arial" size="1" color="#000033">
											<span class="fieldtag"><spring:message code="userInfo.businessFax"/></span>
											</font>
										</td>
									</tr>
									<tr align="left">
										<td>
											<input type="text" name="businessPhone" value="" size="30" tabindex="11" maxlength="20"/>
										</td>
										<td>
											<input type="text" name="businessExt" value="" size="11" tabindex="11" maxlength="6" />
										</td>
										<td>
											<input type="text" name="businessFax" value="" size="30" maxlength="20" tabindex="12" />
										</td>
									</tr>
								</table>
								</td>
							</tr>
						</table>
						
						</td>
					</tr>
				</table>
				</div>
			
				<div id="busInfoError">
					<span class="copyspacer5"><br/>
				  </span>
				</div>
				<div class="b">
				<table cellspacing="0" cellpadding="2" border="0" width="800" bgcolor="#72bd96">
					<tr>
						<td align="left">
							<img src="<c:url value="/resources/images/trans_filler.gif"/>" width="5" height="5" alt="" border="0" /> 
								<B><FONT FACE="Arial" SIZE="2" COLOR="#ffffff">
								<span class="titlebartext"><spring:message code="register.primaryContact"/></span>
								</FONT></B>
						</td>
					</tr>
				</table>
				
				<table cellspacing="0" cellpadding="3" border="0" width="800" id="cantent">
					<tr>
						<td>
						<table cellspacing="0" cellpadding="0" border="0" >
							<tr>
								<td>
								<table cellspacing="2" cellpadding="0" border="0"  width="794">
									<tr>
										<td align="left">
										<div align="justify">
											<font face="Arial" size="1" color="#000033"><spring:message code="register.justify"/></font>
										 </div>
										</td>
									</tr>
								</table>
								<table cellspacing="2" cellpadding="0" border="0"  width="794">
									<tr align="left">
										<td width="262">
										<div id="firstNameError">
											<font face="Arial" size="1" color="#000033">
												<span class="fieldtag"><spring:message code="userInfo.user_name"/></span>
											</font> 
											<img src="<c:url value="/resources/images/ast2.gif"/>" width="9" height="13" alt="" border="0" />
										</div>
										</td>
										<td width="269">
										<div id="lastNameError"><font face="Arial" size="1" color="#000033">
											<span class="fieldtag"><spring:message code="userInfo.lastName"/></span></font>
												<img src="<c:url value="/resources/images/ast2.gif"/>" width="9" height="13" alt="" border="0" />
										</div>
										</td>
										<td width="161">
										<div id="usrphoneError"><font face="Arial" size="1" color="#000033">
											<span class="fieldtag"><spring:message code="userInfo.mobile"/></span></font>
										<img src="<c:url value="/resources/images/ast2.gif"/>" width="9" height="13" alt="" border="0" />
										</div>
										</td>
										<td colspan="2">
										<div id="usrextensionError"><font face="Arial" size="1" color="#000033">
											<span class="fieldtag"><spring:message code="userInfo.home_tel"/></span></font>
										</div>
										</td>
									</tr>
									<tr align="left">
										<td>
											<input type="text" name="firstName" value="" size="18" onFocus="this.select()" style="width: 154px" maxlength="20" tabindex="17" />
										</td>
										<td>
											<input type="text" name="lastName" value="" size="23" onFocus="this.select()" style="width: 154px" maxlength="20" tabindex="18" />
										</td>
										<td>
											<input type="text" name="mobile" value="" size="20" onFocus="this.select()" style="width: 140px" maxlength="20" tabindex="19" />
										</td>
										<td width="80">
											<input type="text" name="home_tel" value="" size="10" onFocus="this.select()" style="width: 80px" maxlength="6" tabindex="20" />
										</td>
										<td width="10">&nbsp;</td>
									</tr>
								</table>
								</td>
							</tr>
						</table>
						<table cellspacing="0" cellpadding="0" border="0" >
							<tr>
								<td>
								<table cellspacing="2" cellpadding="0" border="0"  width="794">
									<tr align="left">
										<td width="262">
										<div id="usrEmailError"><font face="Arial" size="1" color="#000033">
											<span class="fieldtag"><spring:message code="userInfo.email"/></span></font> 
												<img src="<c:url value="/resources/images/ast2.gif"/>" width="9" height="13" alt="" border="0" />
										</div>
										</td>
										<td width="526">
										<div id="usrEmailCError"><font face="Arial" size="1" color="#000033">
											<span class="fieldtag"><spring:message code="userInfo.Cemail"/></span>
											</font>
											<img src="<c:url value="/resources/images/ast2.gif"/>" width="9" height="13" alt="" border="0" />
										</div>
										</td>
									</tr>
									<tr align="left">
										<td>
											<input type="text" name="email" value="" size="30" style="width: 250px" onFocus="this.select()" tabindex="21" />
										</td>
										<td>
											<input type="text" name="emailConfirm" value="" size="30" onFocus="this.select()" style="width: 250px" tabindex="22" />
										</td>
									</tr>
								</table>
								</td>
							</tr>
						</table>
						<table cellspacing="0" cellpadding="0" border="0" >
							<tr>
								<td>
								<table cellspacing="0" cellpadding="2" border="0"  width="794">
									<tr align="left">
										<td width="262">
										<div id="usrIdError"><font face="Arial" size="1" color="#000033">
											<span class="fieldtag"><spring:message code="userInfo.user_tag"/></span>
											<img src="<c:url value="/resources/images/ast2.gif"/>" width="9" height="13" alt="" border="0" />
												 (<spring:message code="register.note"/>)</font>
										</div>
										</td>
										<td width="262">
										<div id="usrPswdError"><font face="Arial" size="1" color="#000033">
											<span class="fieldtag"><spring:message code="userInfo.password"/></span>
											<img src="<c:url value="/resources/images/ast2.gif"/>" width="9" height="13" alt="" border="0" />
												 (<spring:message code="register.note"/>)</font>
										</div>
										</td>
										<td width="258">
										<div id="usrPswdCError"><font face="Arial" size="1" color="#000033">
											<span class="fieldtag"><spring:message code="userInfo.Cpassword"/></span></font>
												<img src="<c:url value="/resources/images/ast2.gif"/>" width="9" height="13" alt="" border="0" />
											</b>
										</div>
										</td>
									</tr>
									<tr align="left">
										<td>
											<input type="text" name="user_tag" size="30" onFocus="this.select()" style="width: 180px" value="" tabindex="23">
										</td>
										<td>
											<input type="password" name="user_passwd" value="" size="25" onFocus="this.select()" style="width: 175px" tabindex="24" />
										</td>
										<td>
											<input type="password" name="user_passwdConfirm" value="" size="25" onFocus="this.select()" style="width: 175px" tabindex="25" />
										</td>
									</tr>
								</table>
								</td>
							</tr>
							<tr>
								<td rowspan="2">
								<table cellspacing="2" cellpadding="2" border="0">
									<tr align="left">
										<td valign="bottom">
											<font face="Arial" size="1" color="#000033"><spring:message code="register.UPrule"/></font>
										</td>
									</tr>
								</table>
								</td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
                 </div>
				<div id="usrInfoError"></div>
				<p></p>
				<table border="0" align="center">
					<tr align="center">
						<td>
						<p>
							<input type="checkbox" name="AgreeTnC" value="Y" tabindex="26" />
						</p>
						</td>
						<td>
						<div id="termAgreeError">
						<p><FONT FACE="Arial" SIZE="2" COLOR="#000033">
							 <spring:message code="register.termAgree"/> 
							 <A HREF='http://www.conantoptics.com' target="_blank" alt=""> <spring:message code="register.termConditions"/> </A> 
							 </font>
							 <FONT FACE="Arial" SIZE="2" COLOR="#000033">.</font></p>
						</div>
						</td>
					</tr>
					<tr align="left">
						<td>
							<input type="checkbox" name="AgreePP" value="Y" tabindex="27" />
						</td>
						<td>
						<div id="policyAgreeError">
							<FONT FACE="Arial" SIZE="2" COLOR="#000033">
								<spring:message code="register.policyAgree"/> 
								<A HREF='http://www.conantoptics.com' target="_blank" alt=""><spring:message code="register.privacyPolicy"/></A>.
							</font>
						</div>
						</td>
					</tr>

				</table>
				<p></p>
		
				<span class="copyspacer5"><br/>
				</span>
				<table cellspacing="0" cellpadding="0" border="0" width="800">
					<tr>
						<td align="center">
						<table cellspacing="0" cellpadding="1" border="0" width="800" id="cantent">
							<tr>
								<td>
								<div class="b">
								<table cellspacing="0" cellpadding="4" border="0" width="800">
									<tr>
										<td width="250" align="left">
										<div style="padding-left: 3px;">
											<input type="button" width="75" height="22" name="Submit" value="<spring:message code="register.return"/>" onclick="JavaScript:history.go(-1)">
										</div>
										</td>
										<td align="center" width="250">&nbsp;</td>
										<td align="right" valign="middle" width="250">
										<input type="submit" name="Submit" value="<spring:message code="register.submit"/>">
										</td>
									</tr>
								</table>
								</div>
								</td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				</form>
				<span class="copyspacer5"><br />
				</span> 
				<!-- END Main Body -->

				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</body>
</html>

