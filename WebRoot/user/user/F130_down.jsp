<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.conant.ums.form.*" %>
<html>
<head>
  <title>�û�������Ϣ</title>
</head>
<link href="<%=request.getContextPath()%>/user/css/master.css" rel="stylesheet" type="text/css">
<script>
    <%
	String RUflag =request.getParameter("flag");
	if(RUflag!=null && RUflag.equals("1")){
    %>
        parent.parent.Frame1.selectInfo();
    <%
	}
	   if(RUflag!=null && RUflag.equals("2")){
    %>
        parent.parent.Frame1.location.reload();
    <%}%>
    	parent.document.forms[0].save.disabled = false;
</script>
<script language="javascript">
    function selectrole(){
      if(document.forms[0].user_tag.readOnly == false){
        parent.parent.Frame2.document.all.group1.src = "<%=request.getContextPath()%>/user/F130_UserMgtAction.do?forward=Insertdown&op=selectchangerole" + getPrm();
      }else{
        parent.parent.Frame2.document.all.group1.src = "<%=request.getContextPath()%>/user/F130_UserMgtAction.do?forward=Qur_down&op=selectchangerole" + getPrm();
      }
    }

    function getPrm(){
        var prm = "&user_id=" + document.forms[0].user_id.value
                + "&user_tag=" + document.forms[0].user_tag.value
                + "&user_passwd=" + document.forms[0].user_passwd.value
                + "&user_passwd_t=" + document.forms[0].user_passwd_t.value
                + "&user_name=" + document.forms[0].user_name.value
                + "&deptid=" +document.forms[0].deptid.value
                + "&email=" + document.forms[0].email.value
                + "&address=" + document.forms[0].address.value
                + "&home_tel=" + document.forms[0].home_tel.value
                + "&mobile=" + document.forms[0].mobile.value
                + "&lastName=" + document.forms[0].lastName.value
                + "&businessPhone=" + document.forms[0].businessPhone.value
                + "&businessName=" + document.forms[0].businessName.value
                + "&businessFax=" + document.forms[0].businessFax.value
                + "&businessStreetName=" + document.forms[0].businessStreetName.value
                + "&businessStreetNumber=" + document.forms[0].businessStreetNumber.value
                + "&businessSuit=" + document.forms[0].businessSuit.value
                + "&businessCity=" + document.forms[0].businessCity.value
                + "&businessState=" + document.forms[0].businessState.value
                + "&businessCountry=" + document.forms[0].businessCountry.value;
        return prm;
    }

    function addrole(){
        with ( document.forms[0]) {
        if(role_id.options[ role_id.selectedIndex ].innerText==" "){
            alert("��ѡ���ɫ��");
            role_id.focus();
        }else if(checkrole()){
            alert("�ý�ɫ�Ѵ��ڣ�������ѡ���ɫ��");
            role_id.focus();
        }else{
            var sValue = role_id.options[ role_id.selectedIndex ].value;
            var sText = role_id.options[ role_id.selectedIndex ].innerText;
            var oOption = document.createElement("option");
            userrole.options.add(oOption);
            oOption.innerText=sText;
            oOption.value=sValue;
        }
      }
    }

    function checkrole(){
        with ( document.forms[0]) {
    	    var sValue = role_id.options[ role_id.selectedIndex ].value;
    	    for(i=0;i<userrole.length;i++){
    	    if(userrole.options[i].value==sValue){
    	        return true;
            }
            }
            return false;
    	}
    }

    function delrole(){
        var flag=0;
    	with(document.forms[0]){
          if(userrole.length=="0"){
            alert("��ɫ�����ڣ�");
          }else{
            for(i=0;i<userrole.length-1;i++)
            if(userrole.options[i].selected || flag==1){
              userrole.options[i].text=userrole.options[i+1].innerText;
              userrole.options[i].value=userrole.options[i+1].value;
              flag=1;
            }
            userrole.length=userrole.length-1;
          }
        }
    }

var p =new Array();
p[0] = '��ѡ��';
p[1] = "US";
p[2] = "�й�";
p[3] = "Brazil";


var c =new Array();
c[0] =new Array("��ѡ��");
c[1] =new Array("AK","AL","AR","AZ","CA","CO","CT","DC","DE","FL","GA","GU","HI","IA","ID","IL","IN","KS","KY","LA","MA","MD","ME","MI","MN","MO","MS","MT","NC","ND","NE","NH","NJ","NM","NV","NY","OH","OK","OR","PA","PR","RI","SC","SD","TN","TX","UT","VA","VI","VT","WA","WI","WV","WY");
c[2] =new Array("������","����","����","�ӱ�","����","ɽ��","����","ɽ��","����","����","�Ĵ�","�ຣ","����","����","����","����","�㽭","����","�㶫","����","����","����","����","���ɹ�","�½�","����","����","����","���","�Ϻ�","����","���","����");
c[3] =new Array("Acre","Alagoas","Amazonas","Amap��","Bahia","Cear��","Esp��rito Santo","Goi��s","Maranh��o","Mato Grosso","Mato Grosso do Sul","Minas Gerais","Par��","Para��ba","Paran��","Pernambuco","Piau��","Rio Grande do Norte","Rio Grande do Sul","Rio de Janeiro","Rod��nia","Roraima","Santa Catarina","S��o Paulo","Sergipe","Tocantins");


/*
* ��ʡ����ӵ�slect��ȥ
*/

function set_pro_select1(so,si) {
///alert(123);
    for (var i = 0, n = p.length; i < n; i++ ) {
        var opt = document.createElement('option');
        opt.text = p[i];
        opt.value = i;
        if(i==si) opt.selected=true;
        // ��Щ�������֧�� options ���Ե� add ������
        // ��֧�� DOM �� appendChild ���������磺Konqueror��
        if (so.options.add) {
            so.options.add(opt);
        }
        else {
            so.appendChild(opt);
        }
    }
}

/*
*����Ӧ�ĳ�����ӵ��ڶ���select��ȥ
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
*�������select�е�����
*/
function clear_select(so) {
    for (var i = so.options.length - 1; i > -1; i--) {
        // ��Щ�������֧�� options ���Ե� remove ������
        // ��֧�� DOM �� removeChild ���������磺Konqueror��
        if (so.options.remove) {
            so.options.remove(i);
        }
        else {
            so.removeChild(so.options[i]);
        }
    }
}

/*
*�ı�ʡ��
*/
function change_province(pid) {
    set_city_select(pid);
}

/*
   ������ʼ��ʡ�ݵĲ˵�
*/
function init(){
var so=document.getElementById('businessCountry');
var _cc="${Business_Country}";
if(_cc==""){
  _cc="${param.businessCountry}";
}
if(_cc==""){
  _cc=0
}
set_pro_select1(so,_cc);
change_province(_cc);
var _cd="${Business_State}";
if(_cd==""){
   _cd="${param.businessState}";
}
var so = document.getElementById('businessState');
  for (var i = so.options.length - 1; i > -1; i--) {
    if(so.options[i].value==_cd){
      so.options[i].selected=true;
      return;
    }
 }
}

function set_city_select(pid){
var so = document.getElementById('businessState');
clear_select(so);
set_city_select1(so,pid);
}
</script>
<body>
<html:form method="post" action="/user/F130_UserMgtAction.do" >
    <html:hidden property="user_id"/>
    <html:hidden property="op"/>
    <html:hidden property="forward"/>
  <table width="100%" border="0" cellpadding="0" cellspacing="0" align="center" class="MainTable" >
    <tr valign="top" class="Grid1">
      <table width="100%" border="0" cellpadding="0" cellspacing="0" align="center" class="MainTable" >
        <tr valign="absmiddle" class="Grid1">
          <td align="left" width="12%"  height="25">��½�ʺţ�</td>
          <td align="left" width="35%"><html:text maxlength="15" size="30" styleClass="TextBox" property="user_tag" readonly="true" onkeyup="value=value.replace(/[\W]/g,'')"></html:text><font color="red">&nbsp;*</font></td>
          <td align="left" width="12%" height="25">�ʼ���ַ��</td>
          <td align="left" width="35%"><html:text maxlength="25" size="30" styleClass="TextBox" property="email"></html:text></td>
        </tr>
        <tr valign="absmiddle" class="Grid1">
          <td align="left" height="25">��  �룺</td>
          <td align="left"><html:password maxlength="15" size="30" styleClass="TextBox" property="user_passwd" onkeyup="value=value.replace(/[^\a-zA-Z0-9]/g,'')"></html:password><font color="red">&nbsp;*</font></td>
          <td align="left" height="25">�û���ַ��</td>
          <td align="left"><html:text maxlength="150" size="30" styleClass="TextBox" property="address"></html:text></td>
        </tr>
        <tr valign="absmiddle" class="Grid1">
          <td align="left" height="25">�ٴ��������룺</td>
          <td align="left"><html:password maxlength="15" size="30" styleClass="TextBox" property="user_passwd_t" onkeyup="value=value.replace(/[^\a-zA-Z0-9]/g,'')"></html:password><font color="red">&nbsp;*</font></td>
          <td align="left" height="25">�̶��绰��</td>
          <td align="left"><html:text maxlength="50" size="30" styleClass="TextBox" property="home_tel"></html:text></td>
        </tr>
        <tr valign="absmiddle" class="Grid1">
          <td align="left" height="25">�û�������</td>
          <td align="left"><html:text maxlength="50" size="30" styleClass="TextBox" property="user_name" ></html:text><font color="red">&nbsp;*</font></td>
          <td align="left" height="25">�ƶ��绰��</td>
          <td align="left"><html:text maxlength="50" size="30" styleClass="TextBox" property="mobile"></html:text></td>
        </tr>
        
        <tr valign="absmiddle" class="Grid1">
          <td align="left" height="25">��ʵ������</td>
          <td align="left"><html:text maxlength="50" size="30" styleClass="TextBox" property="lastName" ></html:text></td>
          <td align="left" height="25">��˾�绰��</td>
          <td align="left"><html:text maxlength="50" size="30" styleClass="TextBox" property="businessPhone"></html:text></td>
        </tr>
         <tr valign="absmiddle" class="Grid1">
          <td align="left" height="25">��˾���ƣ�</td>
          <td align="left"><html:text maxlength="100" size="30" styleClass="TextBox" property="businessName" ></html:text></td>
          <td align="left" height="25">��˾���棺</td>
          <td align="left"><html:text maxlength="50" size="30" styleClass="TextBox" property="businessFax"></html:text></td>
        </tr>
          <tr valign="absmiddle" class="Grid1">
          <td align="left" height="25">�ֵ����ƣ�</td>
          <td align="left"><html:text maxlength="100" size="30" styleClass="TextBox" property="businessStreetName" ></html:text></td>
          <td align="left" height="25">�ֵ����룺</td>
          <td align="left"><html:text maxlength="100" size="30" styleClass="TextBox" property="businessStreetNumber"></html:text></td>
        </tr>
         <tr valign="absmiddle" class="Grid1">
          <td align="left" height="25">����</td>
          <td align="left"><html:text maxlength="100" size="30" styleClass="TextBox" property="businessSuit" ></html:text></td>
          <td align="left" height="25">���У�</td>
          <td align="left"><html:text maxlength="50" size="30" styleClass="TextBox" property="businessCity"></html:text></td>
        </tr>
        <tr valign="absmiddle" class="Grid1">
          <td align="left" height="25">ʡ��</td>
          <td align="left"><select style="width:140px" class="SelectButton" size="1" id="businessState" name="businessState"></select></td>
          <td align="left" height="25">���ң�</td>
          <td align="left"><select style="width:140px" class="SelectButton" size="1" id="businessCountry" name="businessCountry" onChange="change_province(this.value);"></select></td>
        </tr>
      </table>
    </tr>
    <tr align="left" class="Grid1">
      <table width="64%" border="0" cellpadding="0" cellspacing="0" align="left" class="MainTable" >
        <tr align="left" class="Grid1">
          <td align="left" width="23%" height="25">�������ţ�</td>
          <td align="left" width="30%">
                <html:select property="deptid" style="width:140px" styleClass="SelectButton" onchange="selectrole()" >
                    <html:option value="">û����������</html:option>
                    <html:optionsCollection name="user_F130_UserMgtForm" property="deptOptions" value="dept_id" label="dept_name"/>
                </html:select><font color="red">&nbsp;*</font>
          </td>
          <td align="left" width="15%">&nbsp;��ѡ��ɫ��</td>
          <td align="left" width="25%">
                <html:select property="role_id" style="width:140px" styleClass="SelectButton" >
                    <html:option value="">&nbsp;</html:option>
                    <html:optionsCollection name="user_F130_UserMgtForm" property="deptRoleGroup" value="role_id" label="role_name"/>
                </html:select>
          </td>
          <td align="left" width="8%">&nbsp;</td>
          </tr>
          <tr>
           <td align="left">&nbsp;</td>
          </tr>
          <tr align="left" class="Grid1">
            <td align="left" height="25">������ɫ��</td>
            <td align="left" colspan="3">
                 <html:select property="userrole" style="width:380px" styleClass="SelectButton" size="5" multiple="true">
                   <html:optionsCollection name="user_F130_UserMgtForm" property="userRoleGroup" value="user_role_id" label="user_role_name"/>
                 </html:select>
          </td>
          <td align="left" valign="top">
          &nbsp;<img src="<%=request.getContextPath()%>/user/images/Insert_icon.gif" onclick="addrole()" alt="" style="cursor:hand"><br><br>
          &nbsp;<img src="<%=request.getContextPath()%>/user/images/Delete_icon.gif" onclick="delrole()" alt="" style="cursor:hand">
          </td>
          </tr>
          <tr><td>&nbsp;</td></tr>
          <tr align="left" class="Grid1">
          	<td align="center" colspan="4"><font color="#990000">�޸���ʾ���޸��û���Ϣʱ��������������룬���ʾ����ԭ���벻�䣡</font></td>
          </tr>
        </table>
    </tr>
  </table>
</html:form>
</body>
<script>
    <%
        String tagFlag =request.getParameter("tagFlag");
        if(tagFlag!=null && tagFlag.equals("1")){
    %>
        document.forms[0].user_tag.readOnly = false;
	<%}%>
	init();
</script>
</html>
