// Version 1.1 (22502)

var includejsx = "1.1";

// Global Variables



states = new Array("AK", "AL", "AR", "AZ", "CA", "CO", "CT", "DC","DE","FL","GA","GU", "HI", "IA", "ID", "IL", "IN", "KS","KY", "LA", "MA", "MD", "ME", "MI","MN", "MO", "MS", "MT", "NC", "ND", "NE", "NH", "NJ", "NM", "NV", "NY", "OH", "OK", "OR", "PA","PR", "RI","SC", "SD", "TN", "TX", "UT", "VA","VI","VT", "WA", "WI", "WV", "WY");




// Set Stylesheet by Browser function
var ns6x = false;
	var browser=navigator.appName;
	var version=navigator.appVersion;
	var ver1=version.substring(0,1);
	var ver2=version.lastIndexOf('MSIE');
	var ver3=version.substring(ver2+5,ver2+6);

function stylecheck() {

	if (browser == "Netscape" && ver1 >= 5){	
		ns6x = true;
	}

function ieVersion(){
	  if (ver1 >= 4) {
	   document.write("<LINK REL=StyleSheet HREF='/vwweb/styles/ie4.css' TYPE='text/css'>");
	  }
	  if (ver3 == 3) {
	   document.write("<LINK REL=StyleSheet HREF='/vwweb/styles/ie4.css' TYPE='text/css'>");
	  }
	  else{
	   document.write("<LINK REL=StyleSheet HREF='/vwweb/styles/ie4.css' TYPE='text/css'>");
	 }
 }

 function nsVersion() {
	  if (ver1 >= 5) {
	   document.write("<LINK REL=StyleSheet HREF='/vwweb/styles/ns5.css' TYPE='text/css'>");
	 }
	  if (ver1 == 4) {
	   document.write("<LINK REL=StyleSheet HREF='/vwweb/styles/ns4.css' TYPE='text/css'>");
	 }
	  else if (ver1 ==3) {
	   document.write("<LINK REL=StyleSheet HREF='/vwweb/styles/ns4.css' TYPE='text/css'>");
	 }
	 }

	 if (browser == "Netscape") {
	  nsVersion();
	 }
	 else if (browser == "Microsoft Internet Explorer") {
	  ieVersion();
	 }
	else{
	  document.write("<LINK REL=StyleSheet HREF='/vwweb/styles/ie4.css' TYPE='text/css'>");
	 }

 }

// Sidebar Rollover functions

    function SBDown(src) {		
        if(event.srcElement.tagName=='TD'){src.children.tags('A')[0].click();}
    }

    function SBOverTD(src,tdcolor,acolor) {
        src.style.cursor = 'hand';
        src.style.backgroundColor = tdcolor;
        if (ns6x != true){
        src.children.tags('A')[0].style.color = acolor;
        src.children.tags('A')[0].style.textDecoration = 'underline';
        }
    }

    function SBOutTD(src,tdcolor,acolor) {
        src.style.cursor = 'default';
        src.style.backgroundColor = tdcolor;
         if (ns6x != true){
        src.children.tags('A')[0].style.color = acolor;
        src.children.tags('A')[0].style.textDecoration = 'none';
        }
    }

    function SBOverA(src,acolor) {
        if (ns6x){
        src.style.color = acolor;}
    }

    function SBOutA(src,acolor) {
        if (ns6x){
        src.style.color = acolor;}
    }

// Open Help File function

    function xhelp(xdocname) {
        var xdocurl = "/vwweb/help/" + xdocname + ".html";
        helpdoc = window.open(xdocurl,"InstructionWindow","width=500,height=400,toolbar=no,menubar=no,scrollbars");
    }

// Sidebar Color Settings

    var sbtdc1 = "#dddddd";
    var sbtdc2 = "#cccccc";
    var sbtdc3 = "#AFC3D9";
    var sbtdc4 = "#9EB3C8";
    var sbac1 = "#333333";
    var sbac2 = "#333333";
    var sbns1 = "#333333";
    var sbns2 = "#333333";

// Footer Copyright Date

    var crdate = "2008";

// Clear Field Default Value On Focus

	function clearFIELDx(src,fvalue){
	    if(src.value==fvalue)
	    {src.value = ""}
	}

// split()
function split(AArray, AString,ASeparator) {
   sTemp ='';
   for (var i=0;i < AString.length; i++){
		chTemp = AString.charAt(i);
    if (chTemp == ASeparator){
			AArray[AArray.length] = sTemp;
			sTemp = '';
    }
		else
			sTemp += chTemp;
  }
	if (sTemp != '')
		AArray[AArray.length] = sTemp;

}


function setSelectObj(aObj, aVal){
	for( i=0; i< aObj.length; i++){
		if( aObj.options[i].value == aVal){
			aObj.options.selectedIndex = i;
			break;
		}
	}
}


    
function trim(str)

{
   var whitespace = new String(" \t\n\r");

   var s = new String(str);

   if (whitespace.indexOf(s.charAt(s.length-1)) != -1) {
      // We have a string with trailing blank(s)...

      var i = s.length - 1;       // Get length of string

      // Iterate from the far right of string until we
      // don't have any more whitespace...
      while (i >= 0 && whitespace.indexOf(s.charAt(i)) != -1)
         i--;


      // Get the substring from the front of the string to
      // where the last non-whitespace character is...
      s = s.substring(0, i+1);
   }

   return s;
}
