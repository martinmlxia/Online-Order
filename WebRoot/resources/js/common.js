
/*
 * fnGetPageSize()
 * Returns array with page width, height and window width, height
 * Core code from - quirksmode.org
 * Edit for Firefox by pHaez
 */
function fnGetPageSize()
{	
	var xScroll, yScroll;
	
	if(window.innerHeight && window.scrollMaxY) 
	{
		xScroll = document.body.scrollWidth;
		yScroll = window.innerHeight + window.scrollMaxY;
	} 
	else if(document.body.scrollHeight > document.body.offsetHeight)
	{ // all but Explorer Mac
		xScroll = document.body.scrollWidth;
		yScroll = document.body.scrollHeight;
	}
	else 
	{ // Explorer Mac...would also work in Explorer 6 Strict, Mozilla and Safari
		xScroll = document.body.offsetWidth;
		yScroll = document.body.offsetHeight;
	}
	
	var windowWidth, windowHeight;
	if(self.innerHeight) 
	{	// all except Explorer
		windowWidth = self.innerWidth;
		windowHeight = self.innerHeight;
	} 
	else if(document.documentElement && document.documentElement.clientHeight) 
	{ // Explorer 6 Strict Mode
		windowWidth = document.documentElement.clientWidth;
		windowHeight = document.documentElement.clientHeight;
	} 
	else if(document.body) 
	{ // other Explorers
		windowWidth = document.body.clientWidth;
		windowHeight = document.body.clientHeight;
	}	
	
	// for small pages with total height less then height of the viewport
	if(yScroll < windowHeight)
	{
		pageHeight = windowHeight;
	} 
	else 
	{ 
		pageHeight = yScroll;
	}

	// for small pages with total width less then width of the viewport
	if(xScroll < windowWidth)
	{	
		pageWidth = windowWidth;
	} 
	else 
	{
		pageWidth = xScroll;
	}
	arrayPageSize = new Array(pageWidth,pageHeight,windowWidth,windowHeight);
	return arrayPageSize;
}

/*
 * 说明：用 Javascript 获取指定页面元素的位置
 * 整理：http://www.codebit.cn
 * 来源：YUI DOM
 * 源: getElementPos(elementId)
 */
function fnGetElementPos(el)
{
	var ua = navigator.userAgent.toLowerCase();
	var isOpera = (ua.indexOf('opera') != -1);
	var isIE = (ua.indexOf('msie') != -1 && !isOpera); // not opera spoof

	if(el.parentNode === null || el.style.display == 'none')
	{
		return null;
	}
	var parent = null;
	var pos = [];
	var box;
	//IE
	if(el.getBoundingClientRect)
	{
		box = el.getBoundingClientRect();
		var scrollTop = Math.max(document.documentElement.scrollTop, document.body.scrollTop);
		var scrollLeft = Math.max(document.documentElement.scrollLeft, document.body.scrollLeft);
		pos = [box.left + scrollLeft, box.top + scrollTop];
		return pos;
	}
	//gecko
	else if(document.getBoxObjectFor)
	{
		box = document.getBoxObjectFor(el);
		var borderLeft = (el.style.borderLeftWidth) ? parseInt(el.style.borderLeftWidth) : 0;
		var borderTop = (el.style.borderTopWidth) ? parseInt(el.style.borderTopWidth) : 0;
		pos = [box.x - borderLeft, box.y - borderTop];
	}
	//safari & opera
	else
	{
		pos = [el.offsetLeft, el.offsetTop];
		parent = el.offsetParent;
		if(parent != el)
		{
			while(parent)
			{
				pos[0] += parent.offsetLeft;
				pos[1] += parent.offsetTop;
				parent = parent.offsetParent;
			}
		}
		if(ua.indexOf('opera') != -1 || (ua.indexOf('safari') != -1 && el.style.position == 'absolute'))
		{
			pos[0] -= document.body.offsetLeft;
			pos[1] -= document.body.offsetTop;
		}
	}
	if(el.parentNode) 
	{ 
		parent = el.parentNode;
	}
	else
	{
		parent = null;
	}
	while(parent && parent.tagName != 'BODY' && parent.tagName != 'HTML')
	{ 
		//account for any scrolled ancestors
		pos[0] -= parent.scrollLeft;
		pos[1] -= parent.scrollTop;
		if(parent.parentNode)
		{
			parent = parent.parentNode;
		}
		else
		{
			parent = null;
		}
	}

	return pos;
}
