

  //----------------------------------------------------------------------

  /**
   * Check if the string is a numeric
   */
  function isFloat(string) {
      if (string.search(/^[0-9]+[\.|\,][0-9]*$/) != -1)
          return true;
      else
          return false;
  }

  //----------------------------------------------------------------------

  /**
   * Check if the string is a numeric
   */
  function isNumeric(string) {
      if (string.search(/^[0-9]*$/) != -1)
          return true;
      else
          return false;
  }

  /**
   * Check if the string is a lower case or numeric
   */
  
  function isLowerNumeric(string){
  	if(string.search(/^[a-z0-9]*$/) != -1)
		return true;
	else
		return false;
  }
  
  /**
   * Check if the string is a alpha or numeric
   */
  function isAlphaNumeric(string){
  if(string.search(/^[A-Za-z0-9]*$/) != -1)
  		return true;
  	else
  		return false;
  }

  /**
   * Check if the string is a alpha or numeric or email
   */
  function isAlphaNumericEmail(string){
  
  if(string.search(/^[A-Za-z0-9]*$/) != -1)
  		return true;
  	else
             if (string.search(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/) != -1)
                 return true;
             else
                 return false;
  }


  /**
   * Check if the start of the string is a alpha or numeric
   */
  function isStartAlphaNumeric(string){
  
  if(string.search(/^[A-Za-z0-9]/) != -1)
  		return true;
  	else
                return false;
  }

  /**
   * Check if the string is a alpha or numeric or permitted special characters
   */
  function isContainSpChar(string){
  
  if(string.search(/^[A-Za-z0-9\.\-\_]*$/) != -1)
  		return true;
  	else
                return false;
  }

  //----------------------------------------------------------------------

  /**
   * Check if the string is an email
   */
  function isEmail(string) {
      if (string.search(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/) != -1)
          return true;
      else
          return false;
  }

  //----------------------------------------------------------------------

  /**
   * Check if the string is a noun
   */
  function isProper(string) {
      if (string.search(/^[A-Za-z\']+( [A-Za-z\']+)*$/) != -1)
          return true;
      else
          return false;
  }

  //----------------------------------------------------------------------

  /**
   * Check if the string is a date
   */
function isDate(theDate)
{
    
    // Make sure the date parameter is supplied
    // This prevents a scripting error

   if (theDate == '')
    return false;

    var s;
    s = theDate.toString();

	var i;
	var c;
	var charinbag
	var digitCount= 0;
	
	//Make sure the date has correct number of digits
	// It must have 6, 7 or 8 to even be considered as valid
		
	for ( i=0; i<s.length; i++)
	{
		c = s.charAt(i)
		if ( c >= "0" && c <= "9")
			digitCount +=1;
	}
	
	charinbag= s.length-digitCount;
	if (charinbag>2)
	    return false;
	    		    
    if ( digitCount < 6 || digitCount > 8 )
        return false;
    
        
    var theMonth;
    var theDay;
    var theYear;

    var firstslash = 0;
    var secondslash = 0 ;
    
    // Pick apart the different pieces of the date
    // Make sure they are the right size.
    
    // Find the month and day separator
    for (i=0; i<s.length; i++ )
    {
    	var c = s.charAt(i);
    	if ( c == '/' || c == '-' )
    	{
    		firstslash = i;
    		i = s.length + 1;  
    	}
    }

    // Find the day and year separator

    for (i=firstslash + 1; i<s.length; i++)
    {
    	var c=s.charAt(i);
    	if ( c == "/" )
    	{
    		secondslash = i;
    		i=s.length + 1;  // exit the loop
    	}
    }

	// Parse the month, day and year according to position of the separators.
	
    theMonth = s.substring(0, firstslash);
    theDay = s.substring(firstslash + 1, secondslash);
    theYear =s.substring(secondslash+1,  s.length);

    // Month must be 1 or 2 characters, 
    // Day must be 1 or two characters, 
    // Year must be 4 characters
    
    if ( theMonth.length > 2 || theMonth.length < 1 || theDay.length > 2 || theDay.length < 1 || theYear.length != 4) 
    	return false;

    // Make sure the month and day make for a good date.

    if ( theMonth == 1 || theMonth == 3 || theMonth == 5 || theMonth==7 || theMonth==8 || theMonth==10 || theMonth == 12 )
    {
        if ( theDay < 1 || theDay > 31 )
            return false;
    }
    else if ( theMonth == 4 || theMonth == 6 || theMonth == 9 || theMonth == 11 ) 
    {
        if ( theDay < 1 || theDay > 30 )
            return false;
    }
    else if ( theMonth == 2 )
    {
        if ( theDay < 1 || theDay > 29 )
            return false;
            
        if  ( theDay == 29 )
		{
			// make sure the year is a leap year
			// It must be either divisible by 4 and not 100 or divisible by 400
			if ( !( ( theYear % 400 == 0 ) || ( theYear % 4 == 0 && theYear % 100 != 0)) )
			{
				return false;
			}
        }
    }
    else
        return false;

    // Prefill month and day with zeros
    if ( theMonth.length == 1 )
        theMonth = "0" + theMonth;

    if ( theDay.length == 1 )
        theDay = "0" + theDay;
        
    // Update the object's value to the properly formatted date
    
    return true;
}

//********************************************************
// Removes all characters which appear in string bag from string s.


function stripCharsInBag (s, bag)

{   var i;
    var returnString = "";

    // Search through string's characters one by one.
    // If character is not in bag, append to returnString.

    for (i = 0; i < s.length; i++)
    {   
        // Check that current character isn't whitespace.
        var c = s.charAt(i);
        if (bag.indexOf(c) == -1) returnString += c;
    }

    return returnString;
}



  //----------------------------------------------------------------------

  /**
   * Check if the string is Social Security Number
   */
  function isSSN(string) {
      if (string.search(/^[0-9][0-9][0-9]\-[0-9][0-9]\-[0-9][0-9][0-9][0-9]$/) != -1)
          return true;
        else
        return false;
  }

function isBlank(textString) 
{
  returnValue = true;
  for(i = 0; i < textString.length; i++) 
  {
	if(textString.charAt(i) != " ") 
	{
		returnValue = false;
		break;
	} // end if
  } 

 return returnValue;
}
