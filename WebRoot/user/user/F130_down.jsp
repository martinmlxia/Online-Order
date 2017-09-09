<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.conant.ums.form.*" %>
<html>
<head>
  <title>用户基本信息</title>
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
            alert("请选择角色！");
            role_id.focus();
        }else if(checkrole()){
            alert("该角色已存在，请重新选择角色！");
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
            alert("角色不存在！");
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
p[0] = '请选择';
p[1] = "US";
p[2] = "中国";
p[3] = "Brazil";


var c =new Array();
c[0] =new Array("请选择");
c[1] =new Array("AK","AL","AR","AZ","CA","CO","CT","DC","DE","FL","GA","GU","HI","IA","ID","IL","IN","KS","KY","LA","MA","MD","ME","MI","MN","MO","MS","MT","NC","ND","NE","NH","NJ","NM","NV","NY","OH","OK","OR","PA","PR","RI","SC","SD","TN","TX","UT","VA","VI","VT","WA","WI","WV","WY");
c[2] =new Array("黑龙江","吉林","辽宁","河北","河南","山东","江苏","山西","陕西","甘肃","四川","青海","湖南","湖北","江西","安徽","浙江","福建","广东","广西","贵州","云南","海南","内蒙古","新疆","宁夏","西藏","北京","天津","上海","重庆","香港","澳门");
c[3] =new Array("Acre","Alagoas","Amazonas","Amapá","Bahia","Ceará","Espírito Santo","Goiás","Maranháo","Mato Grosso","Mato Grosso do Sul","Minas Gerais","Pará","Paraíba","Paraná","Pernambuco","Piauí","Rio Grande do Norte","Rio Grande do Sul","Rio de Janeiro","Rodánia","Roraima","Santa Catarina","Sáo Paulo","Sergipe","Tocantins");


/*
* 将省份添加到slect中去
*/

function set_pro_select1(so,si) {
///alert(123);
    for (var i = 0, n = p.length; i < n; i++ ) {
        var opt = document.createElement('option');
        opt.text = p[i];
        opt.value = i;
        if(i==si) opt.selected=true;
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
          <td align="left" width="12%"  height="25">登陆帐号：</td>
          <td align="left" width="35%"><html:text maxlength="15" size="30" styleClass="TextBox" property="user_tag" readonly="true" onkeyup="value=value.replace(/[\W]/g,'')"></html:text><font color="red">&nbsp;*</font></td>
          <td align="left" width="12%" height="25">邮件地址：</td>
          <td align="left" width="35%"><html:text maxlength="25" size="30" styleClass="TextBox" property="email"></html:text></td>
        </tr>
        <tr valign="absmiddle" class="Grid1">
          <td align="left" height="25">密  码：</td>
          <td align="left"><html:password maxlength="15" size="30" styleClass="TextBox" property="user_passwd" onkeyup="value=value.replace(/[^\a-zA-Z0-9]/g,'')"></html:password><font color="red">&nbsp;*</font></td>
          <td align="left" height="25">用户地址：</td>
          <td align="left"><html:text maxlength="150" size="30" styleClass="TextBox" property="address"></html:text></td>
        </tr>
        <tr valign="absmiddle" class="Grid1">
          <td align="left" height="25">再次输入密码：</td>
          <td align="left"><html:password maxlength="15" size="30" styleClass="TextBox" property="user_passwd_t" onkeyup="value=value.replace(/[^\a-zA-Z0-9]/g,'')"></html:password><font color="red">&nbsp;*</font></td>
          <td align="left" height="25">固定电话：</td>
          <td align="left"><html:text maxlength="50" size="30" styleClass="TextBox" property="home_tel"></html:text></td>
        </tr>
        <tr valign="absmiddle" class="Grid1">
          <td align="left" height="25">用户姓名：</td>
          <td align="left"><html:text maxlength="50" size="30" styleClass="TextBox" property="user_name" ></html:text><font color="red">&nbsp;*</font></td>
          <td align="left" height="25">移动电话：</td>
          <td align="left"><html:text maxlength="50" size="30" styleClass="TextBox" property="mobile"></html:text></td>
        </tr>
        
        <tr valign="absmiddle" class="Grid1">
          <td align="left" height="25">真实姓名：</td>
          <td align="left"><html:text maxlength="50" size="30" styleClass="TextBox" property="lastName" ></html:text></td>
          <td align="left" height="25">公司电话：</td>
          <td align="left"><html:text maxlength="50" size="30" styleClass="TextBox" property="businessPhone"></html:text></td>
        </tr>
         <tr valign="absmiddle" class="Grid1">
          <td align="left" height="25">公司名称：</td>
          <td align="left"><html:text maxlength="100" size="30" styleClass="TextBox" property="businessName" ></html:text></td>
          <td align="left" height="25">公司传真：</td>
          <td align="left"><html:text maxlength="50" size="30" styleClass="TextBox" property="businessFax"></html:text></td>
        </tr>
          <tr valign="absmiddle" class="Grid1">
          <td align="left" height="25">街道名称：</td>
          <td align="left"><html:text maxlength="100" size="30" styleClass="TextBox" property="businessStreetName" ></html:text></td>
          <td align="left" height="25">街道号码：</td>
          <td align="left"><html:text maxlength="100" size="30" styleClass="TextBox" property="businessStreetNumber"></html:text></td>
        </tr>
         <tr valign="absmiddle" class="Grid1">
          <td align="left" height="25">区：</td>
          <td align="left"><html:text maxlength="100" size="30" styleClass="TextBox" property="businessSuit" ></html:text></td>
          <td align="left" height="25">城市：</td>
          <td align="left"><html:text maxlength="50" size="30" styleClass="TextBox" property="businessCity"></html:text></td>
        </tr>
        <tr valign="absmiddle" class="Grid1">
          <td align="left" height="25">省：</td>
          <td align="left"><select style="width:140px" class="SelectButton" size="1" id="businessState" name="businessState"></select></td>
          <td align="left" height="25">国家：</td>
          <td align="left"><select style="width:140px" class="SelectButton" size="1" id="businessCountry" name="businessCountry" onChange="change_province(this.value);"></select></td>
        </tr>
      </table>
    </tr>
    <tr align="left" class="Grid1">
      <table width="64%" border="0" cellpadding="0" cellspacing="0" align="left" class="MainTable" >
        <tr align="left" class="Grid1">
          <td align="left" width="23%" height="25">所属部门：</td>
          <td align="left" width="30%">
                <html:select property="deptid" style="width:140px" styleClass="SelectButton" onchange="selectrole()" >
                    <html:option value="">没有所属部门</html:option>
                    <html:optionsCollection name="user_F130_UserMgtForm" property="deptOptions" value="dept_id" label="dept_name"/>
                </html:select><font color="red">&nbsp;*</font>
          </td>
          <td align="left" width="15%">&nbsp;可选角色：</td>
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
            <td align="left" height="25">所属角色：</td>
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
          	<td align="center" colspan="4"><font color="#990000">修改提示：修改用户信息时，如果不输入密码，则表示保持原密码不变！</font></td>
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
