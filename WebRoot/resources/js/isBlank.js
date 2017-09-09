function isBlank(textString) {
	returnValue = true;

	for(i = 0; i < textString.length; i++) {
		if(textString.charAt(i) != " ") {
			returnValue = false;
			break;
		} // end if
	} 

	return returnValue;
	}