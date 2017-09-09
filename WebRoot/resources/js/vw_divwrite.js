var ns4 = (document.layers)? true:false;
var ns6 = (document.getElementById)? true:false;
var ie4 = (document.all)? true:false;
var ie5 = false;
var over = null;

// Microsoft Stupidity Check(tm).
if (ie4) {
	if (navigator.userAgent.indexOf('MSIE 5')>0) {
		ie5 = true;
	}
	if (ns6) {
		ns6 = false;
	}
}


function _write(ALayer, AString){
	if (ie4 || ie5) document.all[ALayer].innerHTML = AString
	 if (ns6) {
		over = document.getElementById(ALayer);
		range = document.createRange();
		range.setStartBefore(over);
		domfrag = range.createContextualFragment(AString);
		while (over.hasChildNodes()) {
			over.removeChild(over.lastChild);
		}
		over.appendChild(domfrag);
	}
	else if(ns4){
			var lyr = document.layers[ALayer].document
			lyr.open()
			lyr.write(AString)
		lyr.close()
	}
}


// Make an object visible
function showObj(obj) {
	  if (ns4) eval("document."+obj+".visibility ='show'");
        else if (ie4 || ie5) eval("document.all."+ obj + ".style").visibility = "visible"
	else if (ns6) document.getElementById(obj).style.visibility = "visible";
}

// Hides an object
function hideObj(obj) {
		if (ns4) eval("document."+obj+".visibility = 'hidden'");
        else if (ie4 || ie5)  eval("document.all."+ obj + ".style").visibility ="hidden";
        //document.all[obj].visibility = "hidden";
        //eval("document.all["+obj+"].visibility = 'hidden'");
	else if (ns6) document.getElementById(obj).style.visibility = "hidden";
}

//** This method change the color of style class

function setBGColor( ALayer, Acolor){
		var obj =""
		if (ie4 || ie5){
			obj = eval("document.all." + ALayer +".style")
			obj.backgroundColor = Acolor
		}
		if(ns6){
			obj = document.getElementById(ALayer);
			obj.style.backgroundColor = Acolor;
			//obj.style.backgroundColor = "/vwweb/images/header/none_y.gif";

		}else if( ns4){
			obj = eval("document." + ALayer )
			obj.bgColor = Acolor
		}

}

/****************************************************************
New function is added by  Vivek Namala
This function set Div tag properties dynamically
This function used for scrolling div tags
*****************************************************************/
function setStyleProps(ObjName,stdWidth, stdHeight){
	s=screen;
	srw=s.width;
	srh=s.height;
	width=stdWidth;
	height=stdHeight;
	if ( srh > 600){
		height = height + (srh-600) - 20;
	}

	if (ie4 || ie5){
		dObj = eval("document.all." + ObjName +".style")
		dObj.position = "absolute";
		dObj.width=stdWidth+"px";
		dObj.height = height+"px";
		dObj.overflow = "auto";
	}
	if(ns6){
		dObj = document.getElementById(ObjName);
		dObj.style.position = "absolute";
		dObj.style.width = stdWidth+"px";
		dObj.style.height = height+ "px";
		dObj.style.overflow = "auto";
	}else if( ns4){
		alert("This method not support Netscape 4.0");
	}
}


/**************************************************************
   							Error Log
****************************************************************/

var errorLog ="";
var tableWidth = 600;
var tableMain = "";
var secTable = "";
var tdWidth = "";
var spacer = "";
function formatTable (aWidth){
   tableWidth = aWidth;
}
function setTable( aTableWidth){
	if( Number(aTableWidth) < 100){
			tableMain ="100";
			secTable = "96";
			tdWidth = "90";
			spacer = "2"

	}
	if( Number(aTableWidth) > 100 && Number(aTableWidth) < 200){
			tableMain ="140";
			secTable = "130";
			tdWidth = "135";
			spacer = "1";
			//tableMain ="200";
			//secTable = "175";
			//tdWidth = "150";
			//spacer = "10"

	}
	if( Number(aTableWidth) > 200 && Number(aTableWidth) < 600){
		tableMain =aTableWidth;
		secTable = Number(aTableWidth)-30;
		tdWidth =  Number(aTableWidth)- 60;
		spacer = "10"
	}
	if(Number(aTableWidth) >= 600){
			tableMain = "600";
			secTable = "550";
			tdWidth = "500";
			spacer = "10"
	}
}

function startErrorLog(aLayer){
	setTable(tableWidth);
	_write(aLayer, errorLog);
	//setBGColor(aLayer, "#ffffff");
	errorLog += '<TABLE cellpadding ="1" cellspacing="1" border="0" width="'+tableMain+'">';
	errorLog += '<TR>';
	//errorLog +='<TD><TABLE cellpadding ="1" cellspacing="0" border="0" width="'+ secTable +'" >'
}

function appendError( aError){
	errorLog += '<TD  bgcolor="#ffff99" nowrap="nowrap" width="'+ tdWidth +'"><font face="Arial" size="1px" color="#333333">'+aError+'</font></TD></TR>';
}

function writeErrorLog(aLayer){
	errorLog += '</TABLE>'
	//errorLog += '</TD><TD width="'+spacer+'"></TD></TR></TABLE>';
	_write(aLayer, errorLog);
	errorLog="";
}
function resetErrorLog( aLayer){
	errorLog="";
	_write(aLayer, errorLog);
}

