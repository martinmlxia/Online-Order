<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.conant.ums.form.F120_PurvMgtForm,com.conant.ums.util.tree.*,java.util.List"%>
<%@ page import="com.conant.ums.util.ComGlobal"%>
<%
    out.println("<div id='waiting' style='position:absolute;top:50;left:50;font-size:12pt;color:steelblue' align='center'>��������ϵͳ������ҵ��ʵ����,���Ե�...</div>");
    out.flush();
%>
<html>
<head >
<title>��Դ��</title>
<link href="../user/css/master.css" rel="stylesheet" type="text/css">
<link href="../user/css/pub.js" rel="stylesheet" type="text/css">
</head>
<script language="javascript" type="">
    function selectrole(){
        var roleselect = document.forms[0].roleselect.value;
        parent.parent.Frame1.document.all.group1.src = "<%=request.getContextPath()%>/user/F120_PurvMgtAction.do?forward=SelectRecord&op=selectchangerole&roleselect=" + roleselect;
    }
   
    //����û�����Ĳ�ѯ����
    function checkForm()
	{
        if(document.forms[0].roleselect.value == "#"){
          alert("��ѡ���ɫ��");
          document.forms[0].roleselect.focus();
          return;
        }
	//ʹList�е���Ŀȫ��ѡ��
	var size1=document.forms[0].operName.options.length;
	for(var i=0;i<size1;i++){
	  document.forms[0].operName.options[i].selected=true;
        }
	document.forms[0].op.value = "insert";
        document.forms[0].forward.value = "SelectRecord";
        document.forms[0].submit();
	}
</script>

<body topmargin="1" bottomMargin="0" leftMargin="1" class="navtree">
<html:form action="/user/F120_PurvMgtAction.do" method="post" >
    <html:hidden property="op"/>
    <html:hidden property="forward"/>
<table width="100%" height="100%" border="0" cellSpacing="0" cellPadding="0" class="butt">
  <tr valign="absmiddle" class="Grid1" style="height:33px">
    <td align="center" class="orarowheader">&nbsp;��ɫ�б���
              <html:select property="roleselect" style="width:150px" styleClass="SelectButton" onchange="selectrole()">
                  <html:option value="#">&nbsp;</html:option>
                  <html:optionsCollection name="user_F120_PurvMgtForm" property="role_list" value="role_id" label="role_name"/>
              </html:select>
    </td>
    <td class="orarowheader" align="right" valign="bottom">
      <input type="button" name="save" value="��  ��" class="button" onclick="checkForm()"/>
    </td>
  </tr>

  <tr valign="bottom" style="height:450px">
    <td width="84%" align="left">
      <table width="100%" height="100%" border="1" cellSpacing="5" cellPadding="5" align="center">
      <script type="text/javascript" language="JavaScript" src="../user/js/alai_tree.js"></script>
      <script type="text/javascript" language="JavaScript" src="../user/js/alai_tree_win2k.js"></script>
      <script type="text/javascript" language="JavaScript" src="../user/js/alai_tree_check.js"></script>
      <script type="text/javascript" language="JavaScript" src="../user/js/aptree.js"></script>
      <script type="text/javascript" language="javascript" src="../user/js/pub.js"></script>
        <script type="text/javascript" language="JavaScript1.2">
        setShowExpanders(true);

        //ȫ��չ��
        setExpandDepth(-1);
        //���ÿ�Ƭ��ʽʱ���������ʽ
        setclickType(1);

        setKeepState(true);
        setShowHealth(false);
        setInTable(false);
        setTargetFrame("main");
        openFolder = "";
        closedFolder = "";
        plusIcon = "../user/images/lplus.gif";
        minusIcon = "../user/images/lminus.gif";
        blankIcon = "../user/images/blank20.gif";
        </script>
        <td id='mnuList' class="LeftFrame" background="../user/image/left_bg.gif">
        <div style="overflow:auto; height:100%" id="kun" >
        <table border="0" cellpadding="0" cellspacing="0">
        <%
            F120_PurvMgtForm piForm = (F120_PurvMgtForm)request.getAttribute( "user_F120_PurvMgtForm" );
            TreeNode trRoot = null;
            List operList = piForm.getOperList();
            TreeNode tmp = (TreeNode)operList.get(0);
            if ( tmp.getChildren().size() != 0 ) {
        %>

        <script type="text/javascript" language="JavaScript1.2">
              var companyGif = "";
              var rootGif = "";
              var funcGif = "";

              with ( document.forms[0]) {
              var tree1=new alai_tree_win2k(kun);
              var root=tree1.root;

              //root = addRoot(companyGif, 'ϵͳ������', '');
              //menu0 = addRoot(companyGif, '', '');
              <%
              List menu1List = tmp.getChildren();
              for(int i=0;i< menu1List.size();i++){
                TreeNode menu1 = (TreeNode)menu1List.get(i);
                %>
                var n1=tree1.addChkNode(root,'<%=menu1.getName()%>','<%=menu1.getId()%>','','','',<%=menu1.isSelected()%>);
                //  tmp = addItem(menu0, funcGif, '<%=menu1.getName()%>','');
                <%
                List menu2List = menu1.getChildren();
                for(int k=0;k<menu2List.size();k++){
                  TreeNode menu2 = (TreeNode)menu2List.get(k);
                  %>
                  var n2=tree1.addChkNode(n1,'<%=menu2.getName()%>','<%=menu2.getId()%>','','','',<%=menu2.isSelected()%>);
                  //   menu1 = addItem(tmp, funcGif, '<%=menu2.getName()%>','');
                  <%
                  List menu3List = menu2.getChildren();
                  for(int j=0;j<menu3List.size();j++){
                    TreeNode menu3 = (TreeNode)menu3List.get(j);
                    %>
                    var n3=tree1.addChkNode(n2,'<%=menu3.getName()%>','<%=menu3.getId()%>','','','',<%=menu3.isSelected()%>);
                    //  menu2 = addItem(menu1, funcGif, '<%=menu3.getName() %>', '');
                    <%
                    List menu4List = menu3.getChildren();
                    for(int l=0;l<menu4List.size();l++) {
                      TreeNode menu4 = (TreeNode)menu4List.get(l);
                      %>
                      var n4=tree1.addChkNode(n3,'<%=menu4.getName()%>','<%=menu4.getId()%>','','','',<%=menu4.isSelected()%>);
                      // menu3 = addItem(menu2, funcGif, "<%=menu4.getName() %>", "");
                      <%
                      List menu5List = menu4.getChildren();
                      for(int m=0;m<menu5List.size();m++) {
                        TreeNode menu5 = (TreeNode)menu5List.get(m);
                        %>
                        var n5=tree1.addChkNode(n4,'<%=menu5.getName()%>','<%=menu5.getId()%>','','','',<%=menu5.isSelected()%>);
                        //  menu4 = addItem(menu3, funcGif, "<%=menu5.getName() %>", "");
                        <%
                          List menu6List = menu5.getChildren();
                          for(int n=0;n<menu6List.size();n++) {
                            TreeNode menu6 = (TreeNode)menu6List.get(n);
                            %>
                            var n6=tree1.addChkNode(n5,'<%=menu6.getName()%>','<%=menu6.getId()%>','','','',<%=menu6.isSelected()%>);
                            //  menu4 = addItem(menu3, funcGif, "<%=menu6.getName() %>", "");
                            <%
                              List menu7List = menu6.getChildren();
                              for(int o=0;o<menu7List.size();o++) {
                                TreeNode menu7 = (TreeNode)menu7List.get(o);
                                %>
                                var n7=tree1.addChkNode(n6,'<%=menu7.getName()%>','<%=menu7.getId()%>','','','',<%=menu7.isSelected()%>);
                                //  menu4 = addItem(menu3, funcGif, "<%=menu7.getName() %>", "");
                                <%
                                  List menu8List = menu7.getChildren();
                                  for(int p=0;p<menu8List.size();p++) {
                                    TreeNode menu8 = (TreeNode)menu8List.get(p);
                                    %>
                                    var n8=tree1.addChkNode(n7,'<%=menu8.getName()%>','<%=menu8.getId()%>','','','',<%=menu8.isSelected()%>);
                                    //  menu4 = addItem(menu3, funcGif, "<%=menu8.getName() %>", "");
                                    <%
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                break;
              }

              %>

          var cc=true;
          tree1.oncheck=function(srcNode)
          {
            if(srcNode.checkBox.checked)  //����
            {
              //�����֦�ڵ㣬�����ӽڵ�
              if(srcNode.hasChild)
              {
              //ѡ�񸸽ڵ�
              if(srcNode.getKey()!=<%=ComGlobal.OPERATIONID%>){
                  if(srcNode.parent.getKey()!=<%=ComGlobal.OPERATIONID%>){
                    if(!srcNode.parent.checkBox.checked){srcNode.parent.checkBox.checked=true;}
                    if(srcNode.parent.parent.getKey()!=<%=ComGlobal.OPERATIONID%>){
                        if(!srcNode.parent.parent.checkBox.checked){srcNode.parent.parent.checkBox.checked=true;}
                        if(srcNode.parent.parent.parent.getKey()!=<%=ComGlobal.OPERATIONID%>){
                            if(!srcNode.parent.parent.parent.checkBox.checked){srcNode.parent.parent.parent.checkBox.checked=true;}
                            if(srcNode.parent.parent.parent.parent.getKey()!=<%=ComGlobal.OPERATIONID%>){
                                if(!srcNode.parent.parent.parent.parent.checkBox.checked){srcNode.parent.parent.parent.parent.checkBox.checked=true;}
                                if(srcNode.parent.parent.parent.parent.parent.getKey()!=<%=ComGlobal.OPERATIONID%>){
                                    if(!srcNode.parent.parent.parent.parent.parent.checkBox.checked){srcNode.parent.parent.parent.parent.parent.checkBox.checked=true;}
                                    if(srcNode.parent.parent.parent.parent.parent.parent.getKey()!=<%=ComGlobal.OPERATIONID%>){
                                        if(!srcNode.parent.parent.parent.parent.parent.parent.checkBox.checked){srcNode.parent.parent.parent.parent.parent.parent.checkBox.checked=true;}
                                    }
                                }
                            }
                        }
                    }
                }
                }else{
                     for(var i=0;i<srcNode.children.length;i++)
                     {
                         if(!srcNode.children[i].checkBox.checked)
                         {
                             srcNode.children[i].checkBox.checked=true;
                         }
                         if(srcNode.children[i].hasChild)
                         {
                              for(var j=0;j<srcNode.children[i].children.length;j++)
                              {
                                  if(!srcNode.children[i].children[j].checkBox.checked)
                                  {
                                      srcNode.children[i].children[j].checkBox.checked=true;
                                  }
                                      if(srcNode.children[i].children[j].hasChild)
                                      {
                                          for(var k=0;k<srcNode.children[i].children[j].children.length;k++)
                                          {
                                              if(!srcNode.children[i].children[j].children[k].checkBox.checked)
                                              {
                                                  srcNode.children[i].children[j].children[k].checkBox.checked=true;
                                              }
                                                  if(srcNode.children[i].children[j].children[k].hasChild)
                                                  {
                                                      for(var l=0;l<srcNode.children[i].children[j].children[k].children.length;l++)
                                                      {
                                                          if(!srcNode.children[i].children[j].children[k].children[l].checkBox.checked)
                                                          {
                                                              srcNode.children[i].children[j].children[k].children[l].checkBox.checked=true;
                                                          }
                                                              if(srcNode.children[i].children[j].children[k].children[l].hasChild)
                                                              {
                                                                  for(var m=0;m<srcNode.children[i].children[j].children[k].children[l].children.length;m++)
                                                                  {
                                                                      if(!srcNode.children[i].children[j].children[k].children[l].children[m].checkBox.checked)
                                                                      {
                                                                          srcNode.children[i].children[j].children[k].children[l].children[m].checkBox.checked=true;
                                                                      }
                                                                          if(srcNode.children[i].children[j].children[k].children[l].children[m].hasChild)
                                                                          {
                                                                              for(var n=0;n<srcNode.children[i].children[j].children[k].children[l].children[m].children.length;n++)
                                                                              {
                                                                                  if(!srcNode.children[i].children[j].children[k].children[l].children[m].children[n].checkBox.checked)
                                                                                  {
                                                                                      srcNode.children[i].children[j].children[k].children[l].children[m].children[n].checkBox.checked=true;
                                                                                  }
                                                                              }
                                                                          }
                                                                  }
                                                              }
                                                      }
                                                  }
                                          }
                                      }
                              }
                          }
                      }
                }
                var item=document.createElement("option");
                //item.text=item.value=srcNode.label.innerText;
                item.value=srcNode.getKey();
                item.text=srcNode.label.innerText;
                operName.add(item);
              }
              //�����Ҷ�ڵ㣬����
              if(!srcNode.hasChild)
              {
              //ѡ�񸸽ڵ�
                if(srcNode.parent.getKey()!=<%=ComGlobal.OPERATIONID%>){
                    if(!srcNode.parent.checkBox.checked){srcNode.parent.checkBox.checked=true;}
                    if(srcNode.parent.parent.getKey()!=<%=ComGlobal.OPERATIONID%>){
                        if(!srcNode.parent.parent.checkBox.checked){srcNode.parent.parent.checkBox.checked=true;}
                        if(srcNode.parent.parent.parent.getKey()!=<%=ComGlobal.OPERATIONID%>){
                            if(!srcNode.parent.parent.parent.checkBox.checked){srcNode.parent.parent.parent.checkBox.checked=true;}
                            if(srcNode.parent.parent.parent.parent.getKey()!=<%=ComGlobal.OPERATIONID%>){
                                if(!srcNode.parent.parent.parent.parent.checkBox.checked){srcNode.parent.parent.parent.parent.checkBox.checked=true;}
                                if(srcNode.parent.parent.parent.parent.parent.getKey()!=<%=ComGlobal.OPERATIONID%>){
                                    if(!srcNode.parent.parent.parent.parent.parent.checkBox.checked){srcNode.parent.parent.parent.parent.parent.checkBox.checked=true;}
                                    if(srcNode.parent.parent.parent.parent.parent.parent.getKey()!=<%=ComGlobal.OPERATIONID%>){
                                        if(!srcNode.parent.parent.parent.parent.parent.parent.checkBox.checked){srcNode.parent.parent.parent.parent.parent.parent.checkBox.checked=true;}
                                    }
                                }
                            }
                        }
                    }
                }
                var item=document.createElement("option");
                //item.text=item.value=srcNode.label.innerText;
                item.value=srcNode.getKey();
                item.text=srcNode.label.innerText;
                operName.add(item);
                //logQueryActionForm.operName.add(item)
              }
            }else    //ȡ��
            {
              //Ҷ�ڵ�
              if(!srcNode.hasChild)
              {
                //������ǰ�ڵ�
                for(var i=0;i<operName.options.length;i++)
                {
                //if(srcNode.label.innerText==operName.options[i].value)operName.options[i]=null;
                  if(operName.options[i].value==srcNode.getKey()) operName.options[i]=null;
                }
                //ȡ��ѡ�񸸽ڵ�
//                if(srcNode.parent!=root){
//                  if(srcNode.parent.checkBox.checked)
//                  {
//                    cc=false;
//                    srcNode.parent.checkBox.checked=false;
//                  }
//                }
              }
              //���ڵ�
              else
              {
                for(var i=0;i<operName.options.length;i++)
                {
                  if(operName.options[i].value==srcNode.getKey()) operName.options[i]=null;
                }
                if(cc)
                {
                  for(var i=0;i<srcNode.children.length;i++)
                  {
                    srcNode.children[i].checkBox.checked=false;
                  }
                }
                else
                {
                  cc=true;
                }
              }
            }
          }
        }
        nowait();
            </script>
            <script type="text/javascript" language="JavaScript1.2">
                initialize()
            </script>
    <%
        } else {
    %>
            <tr>
                <td><div align="center" ><Strong>���κ�ϵͳ����</Strong></div></td>
            </tr>
       <script type="">
         nowait();
       </script>
    <%
        }
    %>
    </table>
    </div>
    </td>
    </table>
    </td>

    <td align="center" valign="absmiddle">
      <div style="visibility: hidden">
      <table border="0" cellpadding="0" cellspacing="0" align="center">
        <tr class="Grid1">
          <td>&nbsp;</td>
          <td>��ѡ����ϵͳ����:</td>
          <td>&nbsp;</td>
        </tr>
        <tr class="Grid1">
          <td align="center"><img alt="" src="../user/images/allrt.gif">&nbsp;&nbsp;</td>
          <td colspan="3">
            <html:select property="operName" size="1" multiple="true" styleClass="SelectButton" style="width:1px" >
               <html:optionsCollection name="user_F120_PurvMgtForm" property="oper_list" value="oper_id" label="oper_name"/>
            </html:select></td>
          <td>&nbsp;</td>
        </tr>
     </table>
     </div>
     <table border="0" cellpadding="0" cellspacing="0" align="left">
        <tr class="Grid1">
          <td colspan="3" align="left">&nbsp;Ȩ������˵����</td>
        </tr>
        <tr class="Grid1">
          <td colspan="3" align="left">&nbsp;1.������ѡ��һ����ɫ</td>
        </tr>
        <tr class="Grid1">
          <td colspan="3" align="left">&nbsp;2.������ϵͳ��������</td>
        </tr>
        <tr class="Grid1">
          <td colspan="3" align="left">&nbsp;&nbsp;&nbsp;��ѡ��</td>
        </tr>
      </table>
    </td>
    </tr>
</table>
</html:form>
</body>
</html>