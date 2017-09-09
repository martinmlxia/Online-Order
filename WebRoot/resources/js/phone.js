/**function formatPhone(phoneValue) {
		var formattedValue = "";
		var currentChar = "";

		for(i = 0; i < phoneValue.length; i++) {
			currentChar = phoneValue.charAt(i);

			if (!isNaN(currentChar) && phoneValue.charAt(i) != " ") {
				formattedValue = formattedValue + phoneValue.charAt(i);
			} // end if
		} // end for
		
		if (formattedValue.length == 7) {
			return formattedValue.substring(0, 3) + '-' + formattedValue.substring(3, 7);
		} // end if
		else if (formattedValue.length == 10) {
			return formattedValue.substring(0, 3) + '-' + formattedValue.substring(3, 6) + '-' + formattedValue.substring(6, 10);
		}// end else if
		else
			return formattedValue;
}
**/

function formatPhone( aObj){
	var aValue= aObj.value;
	var sBag="()_- .*,"
	if ( aValue==""){
		return;
	}else{
		var sVal = stripCharsInBag ( aValue, sBag);		
		if( !isNumeric(sVal)){
		    alert("Not a  number")
		    aObj.focus();
			return;
		}else if(sVal.length != 10){
			  alert("Not a valid number")
			  aObj.focus();
			return;
		}else{
			aObj.value = "("+sVal.substring(0,3)+") "+ sVal.substring(3,6)+"-"+sVal.substring(6);
		}
	}		
}

function formatPhoneUK(aObj){
	var aValue= aObj.value;
	var sBag="()_- .*,"
	if ( aValue==""){
		return;
	}else{
		var sVal = stripCharsInBag ( aValue, sBag);		
		if( !isNumeric(sVal)){
		    alert("Not a UK number")
		    aObj.focus();
			return;
		}else if(sVal.length != 11){
			  alert("Not a valid UK number")
			  aObj.focus();
			return;
		}else{
			aObj.value = "("+sVal.substring(0,3)+") "+ sVal.substring(3,6)+"-"+sVal.substring(6);
		}
	}		
}


