Object.extend = function(destination, source) {
	for ( var property in source) {
		destination[property] = source[property];
	}
	return destination;
};

function $() {
	if (arguments.length == 0) {
		return;
	}
	var elements = [];
	for ( var i = 0; i < arguments.length; i++) {
		var element = arguments[i];
		if (typeof element != "string") {
			return;
		}
		element = document.getElementById(element);
		if (arguments.length == 1) {
			return element;
		}
		elements.push(element);
	}
	return elements;
}

String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, "");
};

String.prototype.replaceAll = function(searchValue, replaceValue) {
	if (RegExp.prototype.isPrototypeOf(searchValue)) {
		if (!searchValue.global) {
			searchValue = new RegExp(searchValue.source, "g");
		}
		return this.replace(searchValue, replaceValue);
	}
	searchValue = searchValue.toString();
	replaceValue = replaceValue.toString();
	var stringBuffer = new StringBuffer();
	stringBuffer.append(this);
	var position = 0;
	while (true) {
		var index = stringBuffer.toString().indexOf(searchValue, position);
		if (index == -1) {
			break;
		}
		stringBuffer.replace(index, index + searchValue.length, replaceValue);
		position = index + replaceValue.length;
	}
	return stringBuffer.toString();
};

String.prototype.shift = function(map) {
	var str = this;
	var entries = map.getEntries();
	for ( var i = 0; i < entries.length; i++) {
		str = str.replaceAll(entries[i].key, entries[i].value);
	}
	return str;
};

if (typeof Node == "undefined") {
	var Node = {
		ELEMENT_NODE : 1,
		ATTRIBUTE_NODE : 2,
		TEXT_NODE : 3,
		CDATA_SECTION_NODE : 4,
		ENTITY_REFERENCE_NODE : 5,
		ENTITY_NODE : 6,
		PROCESSING_INSTRUCTION_NODE : 7,
		COMMENT_NODE : 8,
		DOCUMENT_NODE : 9,
		DOCUMENT_TYPE_NODE : 10,
		DOCUMENT_FRAGMENT_NODE : 11,
		NOTATION_NODE : 12
	};
}

var NodeUtil = {
	find : function(root, filter) {
		var nodes = [];
		var childs = root.childNodes;
		for ( var i = 0; i < childs.length; i++) {
			var child = childs.item(i);
			if (filter(child)) {
				nodes.push(child);
			}
			Array.prototype.push.apply(nodes, this.find(child, filter));
		}
		return nodes;
	}
};

var URLUtil = {
	join : function(map) {
		var entries = map.getEntries();
		var stringBuffer = new StringBuffer();
		for ( var i = 0; i < entries.length; i++) {
			stringBuffer.append(encodeURIComponent(entries[i].key));
			stringBuffer.append("=");
			stringBuffer.append(encodeURIComponent(entries[i].value));
			if (i != entries.length - 1) {
				stringBuffer.append("&");
			}
		}
		return stringBuffer.toString();
	}
};

var StyleUtil = {
	split : function(text) {
		text = text.trim();
		if (text.charAt(text.length - 1) == ";") {
			text = text.substring(0, text.length - 1).trim();
		}
		var array = [];
		var properties = text.split(/;/);
		for ( var index in properties) {
			var nameAndValue = properties[index].split(/:/);
			array.push( {
				name : nameAndValue[0].trim(),
				value : nameAndValue[1].trim()
			});
		}
		return array;
	},
	remove : function(text) {
		var args = (arguments.length > 1 ? Array.prototype.slice.call(arguments).slice(
				1).join(":") : "");
		var properties = this.split(text);
		var stringBuffer = new StringBuffer();
		for ( var index in properties) {
			var property = properties[index];
			if (args.indexOf(property.name) == -1) {
				stringBuffer.append(property.name);
				stringBuffer.append(":");
				stringBuffer.append(property.value);
				stringBuffer.append(";");
			}
		}
		return stringBuffer.toString();
	},
	parse : function(text) {
		var properties = this.split(text);
		var stringBuffer = new StringBuffer();
		for ( var index in properties) {
			var property = properties[index];
			while (true) {
				var i = property.name.indexOf("-");
				if (i == -1) {
					break;
				}
				property.name = property.name.substring(0, i)
						+ property.name.substring(i + 1, i + 2).toUpperCase()
						+ property.name.substring(i + 2);
			}
			stringBuffer.append(property.name);
			stringBuffer.append(":");
			stringBuffer.append("\"");
			stringBuffer.append(property.value);
			stringBuffer.append("\"");
			if (index != properties.length - 1) {
				stringBuffer.append(",");
			}
		}
		return new EvalObject("{" + stringBuffer.toString() + "}").eval();
	}
};

var PositionUtil = {
	Style : {
		VALUE : "position: ${type}; left: ${left}; top: ${top};",
		ABSOLUTE : "absolute",
		RELATIVE : "relative"
	},
	call : function(element, type, left, top) {
		var value = this.Style.VALUE;
		var map = new Map();
		if (arguments.length > 1) {
			map.put("${type}", type);
			if (arguments.length > 2) {
				map.put("${left}", left);
				if (arguments.length > 3) {
					map.put("${top}", top);
				} else {
					value = StyleUtil.remove(value, "top");
				}
			} else {
				value = StyleUtil.remove(value, "left", "top");
			}
		} else {
 			value = StyleUtil.remove(value, "position", "left", "top");
		}
		Object.extend(element.style, StyleUtil.parse(value.shift(map)));
	},
	absolutize : function(element, left, top) {
		this.call(element, this.Style.ABSOLUTE, left, top);
	},
	relative : function(element, left, top) {
		this.call(element, this.Style.RELATIVE, left, top);
	},
	getPosition : function(element) {
		var topElement = (arguments.length > 1 ? arguments[1] : document.documentElement);
		var position = {
			left : 0,
			top : 0
		};
		while (element != topElement) {
			position.left += element.offsetLeft;
			position.top += element.offsetTop;
			element = element.parentNode;
		}
		return position;
	}
};

var PropertyUtil = {
	getLength : function(obj) {
		var t = 0;
		for ( var index in obj) {
			t++;
		}
		return t;
	},
	equals : function(obj1, obj2) {
		if (this.getLength(obj1) == 0 || this.getLength(obj2) == 0) {
			return false;
		}
		for ( var index in obj1) {
			if (!Map.equals(obj1[index], obj2[index])) {
				return false;
			}
		}
		return true;
	}
};

var Validate = {
	isNull : function(obj) {
		return obj === null;
	},
	isUndefined : function(obj) {
		return obj === undefined;
	},
	isVoid : function(obj) {
		return this.isNull(obj) || this.isUndefined(obj);
	}
};

var MaskUtil = {
	parameters : function() {
		return new function() {
			this.target = null;
			this.className = null;
			this.cover = true;
		};
	}
};

MaskUtil.Style = {
	VALUE : "width: ${width}px; height: ${height}px; background-image: ${backgroundImage};",
	BACKGROUND_IMAGE : "url(.)"
};

function Mask(params) {
	this.params = Object.extend(MaskUtil.parameters(), params);
	this.div = document.createElement("div");
	var position = PositionUtil.getPosition(this.params.target);
	PositionUtil.absolutize(this.div, position.left, position.top);
	var value = MaskUtil.Style.VALUE;
	var map = new Map();
	map.put("${width}", this.params.target.offsetWidth);
	map.put("${height}", this.params.target.offsetHeight);
	map.put("${backgroundImage}", MaskUtil.Style.BACKGROUND_IMAGE);
	if (!this.params.cover) {
		value = StyleUtil.remove(value, "width", "height");
	}
	if (!Validate.isVoid(this.params.className)) {
		this.div.className = this.params.className;
		value = StyleUtil.remove(value, "background-image");
	}
	Object.extend(this.div.style, StyleUtil.parse(value.shift(map)));
}

Mask.prototype.enable = function() {
	document.body.appendChild(this.div);
};

Mask.prototype.disable = function() {
	document.body.removeChild(this.div);
};

function Timer(params) {
	this.id = null;
	this.time = 3;
	this.step = 1;
	this.run = null;
	this.finish = null;
	Object.extend(this, params);
	this.origin = this.time;
}

Timer.prototype.getTime = function() {
	return this.origin;
};

Timer.prototype.getCurrentTime = function() {
	return this.time;
};

Timer.prototype.interrupt = function() {
	clearInterval(this.id);
};

Timer.prototype.reset = function() {
	this.interrupt();
	this.time = this.origin;
};

Timer.prototype.start = function() {
	var timer = this;
	this.id = setInterval(function() {
		timer.time -= timer.step;
		if (timer.time < 0) {
			if (!Validate.isVoid(timer.finish)) {
				timer.finish();
			}
			clearInterval(timer.id);
			return;
		}
		if (!Validate.isVoid(timer.run)) {
			timer.run();
		}
	}, this.step * 1000);
};

function EvalObject(object) {
	this.object = object;
}

EvalObject.prototype.eval = function() {
	var type = EvalUtil.getType(this.object);
	switch (type) {
		case EvalType.NULL:
		case EvalType.BOOLEAN:
		case EvalType.NUMBER:
		case EvalType.STRING:
		case EvalType.ARRAY:
			return eval(this.object);
		case EvalType.ENTITY:
			return eval("[" + this.object + "]")[0];
	}
};

var EvalType = {
	NULL : 0,
	BOOLEAN : 1,
	NUMBER : 2,
	STRING : 3,
	ARRAY : 4,
	ENTITY : 5
};

var EvalUtil = {
	getType : function(json) {
		if (this.isNull(json)) {
			return EvalType.NULL;
		}
		if (this.isBoolean(json)) {
			return EvalType.BOOLEAN;
		}
		if (this.isNumber(json)) {
			return EvalType.NUMBER;
		}
		if (this.isString(json)) {
			return EvalType.STRING;
		}
		if (this.isArray(json)) {
			return EvalType.ARRAY;
		}
		if (this.isEntity(json)) {
			return EvalType.ENTITY;
		}
	},
	isNull : function(json) {
		return json == "null";
	},
	isBoolean : function(json) {
		return json == "true" || json == "false";
	},
	isNumber : function(json) {
		var regexp = /^([0-9]|[1-9][0-9]+)(\.[0-9]*)?((e|E)(\+|\-)?([0-9]|[1-9][0-9]+))?$/;
		if (json.match(regexp)) {
			return true;
		}
		regexp = /^0[0-7]+$/;
		if (json.match(regexp)) {
			return true;
		}
		regexp = /^0(x|X)[0-9a-fA-F]+$/;
		if (json.match(regexp)) {
			return true;
		}
		return false;
	},
	isString : function(json) {
		if (json.charAt(0) == '"' && json.charAt(json.length - 1) == '"') {
			return true;
		}
		if (json.charAt(0) == "'" && json.charAt(json.length - 1) == "'") {
			return true;
		}
		return false;
	},
	isArray : function(json) {
		return json.charAt(0) == "[" && json.charAt(json.length - 1) == "]";
	},
	isEntity : function(json) {
		return json.charAt(0) == "{" && json.charAt(json.length - 1) == "}";
	}
};

function StringBuffer() {
	this.array = [];
}

StringBuffer.prototype.append = function(s) {
	for ( var i = 0; i < arguments.length; i++) {
		this.array.push(arguments[i] + "");
	}
};

StringBuffer.prototype.deleteChars = function(start, end) {
	this.replace(start, end, "");
};

StringBuffer.prototype.deleteCharAt = function(pos) {
	this.deleteChars(pos, pos + 1);
};

StringBuffer.prototype.insert = function(offset, s) {
	this.replace(offset, offset, s);
};

StringBuffer.prototype.length = function() {
	return this.toString().length;
};

StringBuffer.prototype.replace = function(start, end, s) {
	if (start > end) {
		var t = start;
		start = end;
		end = t;
	}
	var string = this.toString();
	this.array = [];
	this.append(string.substring(0, start));
	this.append(s);
	this.append(string.substring(end));
};

StringBuffer.prototype.reverse = function() {
	this.array = this.toString().split("").reverse();
};

StringBuffer.prototype.setLength = function(nl) {
	this.substring(0, nl);
};

StringBuffer.prototype.substring = function(start, end) {
	var string = this.toString();
	this.array = [];
	this.append(string.substring(start, end));
};

StringBuffer.prototype.toString = function() {
	return this.array.join("");
};

function Map() {
	this.entries = [];
	this.keys = [];
	this.values = [];
}

Map.Entry = function() {
	this.key = (arguments.length < 1 ? null : arguments[0]);
	this.value = (arguments.length < 2 ? null : arguments[1]);
};

Map.Entry.prototype.toString = function() {
	return this.key + " = " + this.value;
};

Map.equals = function(v1, v2) {
	v1 = String.prototype.isPrototypeOf(v1) ? v1.toString() : v1;
	v2 = String.prototype.isPrototypeOf(v2) ? v2.toString() : v2;
	return v1 === v2;
};

Map.get = function(array, e) {
	for ( var index in array) {
		if (Map.equals(array[index], e)) {
			return index;
		}
	}
	return -1;
};

Map.remove = function(array, i) {
	var dest = array.slice(0, i);
	dest = dest.concat(array.slice(i + 1, array.length));
	return dest;
};

Map.prototype.clear = function() {
	this.entries = [];
	this.keys = [];
	this.values = [];
};

Map.prototype.containsKey = function(key) {
	return Map.get(this.keys, key) != -1 ? true : false;
};

Map.prototype.containsValue = function(value) {
	return Map.get(this.values, value) != -1 ? true : false;
};

Map.prototype.get = function(key) {
	var index = Map.get(this.keys, key);
	if (index != -1) {
		return this.values[index];
	}
};

Map.prototype.getEntries = function() {
	return this.entries;
};

Map.prototype.getKeys = function() {
	return this.keys;
};

Map.prototype.getValues = function() {
	return this.values;
};

Map.prototype.isEmpty = function() {
	return this.entries.length == 0 ? true : false;
};

Map.prototype.put = function(key, value) {
	var index = Map.get(this.keys, key);
	var i = (index == -1 ? this.entries.length : index);
	var entry = new Map.Entry(key, value);
	this.entries[i] = entry;
	this.keys[i] = key;
	this.values[i] = value;
};

Map.prototype.remove = function(key) {
	var index = Map.get(this.keys, key);
	if (index != -1) {
		this.entries = Map.remove(this.entries, index);
		this.keys = Map.remove(this.keys, index);
		this.values = Map.remove(this.values, index);
	}
};

Map.prototype.size = function() {
	return this.entries.length;
};

Map.prototype.toString = function() {
	var stringBuffer = new StringBuffer();
	stringBuffer.append("[");
	for ( var index in this.entries) {
		stringBuffer.append(this.entries[index]);
		stringBuffer.append(", ");
	}
	if (this.entries.length > 0) {
		stringBuffer.substring(0, stringBuffer.length() - 2);
	}
	stringBuffer.append("]");
	return stringBuffer.toString();
};

function XMLHttpRequestWrapper() {
	this.readyState = null;
	this.responseText = null;
	this.responseXML = null;
	this.status = null;
	this.statusText = null;
}

XMLHttpRequestWrapper.prototype.populate = function(transport) {
	for ( var index in this) {
		if (index != "populate") {
			this[index] = transport[index];
		}
	}
};

var StringWrapperMap = new Map();

function StringWrapper(s) {
	s = (arguments.length < 1 ? "" : new String(s).toString());
	StringWrapperMap.put(this, s);
	this.length = s.length;
}

StringWrapper.prototype.evalJSON = function() {
	return new EvalObject(this.toString()).eval();
};

StringWrapper.prototype.charAt = function(pos) {
	return this.toString().charAt(pos);
};

StringWrapper.prototype.charCodeAt = function(pos) {
	return this.toString().charCodeAt(pos);
};

StringWrapper.prototype.concat = function(value) {
	return this.toString().concat(value);
};

StringWrapper.prototype.indexOf = function(searchString, position) {
	return this.toString().indexOf(searchString, position);
};

StringWrapper.prototype.lastIndexOf = function(searchString, position) {
	return this.toString().lastIndexOf(searchString, position);
};

StringWrapper.prototype.localeCompare = function(otherString) {
	return this.toString().localeCompare(otherString);
};

StringWrapper.prototype.match = function(regexp) {
	return this.toString().match(regexp);
};

StringWrapper.prototype.replace = function(searchValue, replaceValue) {
	return this.toString().replace(searchValue, replaceValue);
};

StringWrapper.prototype.search = function(regexp) {
	return this.toString().search(regexp);
};

StringWrapper.prototype.slice = function(start, end) {
	return this.toString().slice(start, end);
};

StringWrapper.prototype.split = function(separator, limit) {
	return this.toString().split(separator, limit);
};

StringWrapper.prototype.substring = function(start, end) {
	return this.toString().substring(start, end);
};

StringWrapper.prototype.toLocaleLowerCase = function() {
	return this.toString().toLocaleLowerCase();
};

StringWrapper.prototype.toLocaleUpperCase = function() {
	return this.toString().toLocaleUpperCase();
};

StringWrapper.prototype.toLowerCase = function() {
	return this.toString().toLowerCase();
};

StringWrapper.prototype.toString = function() {
	return StringWrapperMap.get(this);
};

StringWrapper.prototype.toLocaleString = function() {
	return this.toString().toLocaleString();
};

StringWrapper.prototype.valueOf = function() {
	return this.toString();
};

var Try = {
	these : function() {
		var returnValue;
		for ( var i = 0; i < arguments.length; i++) {
			var lambda = arguments[i];
			try {
				returnValue = lambda();
				break;
			} catch (e) {
			}
		}
		return returnValue;
	}
};

var Ajax = {
	getTransport : function() {
		return Try.these(function() {
			return new ActiveXObject("Msxml2.XMLHTTP");
		}, function() {
			return new ActiveXObject("Microsoft.XMLHTTP");
		}, function() {
			return new XMLHttpRequest();
		}) || false;
	},
	setting : function() {
		return new function() {
			this.method = null;
			this.asynchronous = true;
			this.onSuccess = null;
		};
	}
};

Ajax.Request = function() {
	var url = arguments[0];
	var i = url.indexOf("?");
	var setting = Object.extend(Ajax.setting(), arguments[1]);
	var transport = Ajax.getTransport();
	transport.open(setting.method, (setting.method.toLowerCase() == "post"
			&& i != -1 ? url.substring(0, i) : url), setting.asynchronous);
	transport.onreadystatechange = function() {
		if (transport.readyState == 4) {
			if (transport.status == 200) {
				if (setting.onSuccess != undefined && setting.onSuccess != null) {
					var transportWrapper = new XMLHttpRequestWrapper();
					transportWrapper.populate(transport);
					var responseTextWrapper = new StringWrapper(
							transportWrapper.responseText);
					transportWrapper.responseText = responseTextWrapper;
					setting.onSuccess(transportWrapper);
					StringWrapperMap.remove(responseTextWrapper);
				}
			}
		}
	};
	if (setting.method.toLowerCase() == "post") {
		transport.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");
		transport.send(i != -1 ? url.substring(i + 1) : null);
		return;
	}
	transport.send(null);
};
var getRadioValue=function(name){
   var _radio=document.getElementsByName(name);
      for(var i=0;i<_radio.length;i++){
	     if(_radio[i].checked) return _radio[i].value;
	  }
	  return "";
}
var getCheckBoxValue=function (name){
   var _checkbox=document.getElementsByName(name);
   var str="";
   for(var i=0;i<_checkbox.length;i++){
     if(_checkbox[i].checked) str+=","+_checkbox[i].value;
   }
   if(str!="") str=str.substring(1);
   return str;
}
var getDetail=function(){
  var $=document.getElementsByTagName("form")[0];
  return {
      _framestyle : $.framestyle.value.trim(),
      _material : getRadioValue("material"),
      _material2 : getRadioValue("material2"),
      _lenstype : getRadioValue("lenstype"),
      _treat : getRadioValue("treat"),
      _uv : getRadioValue("uv"),
      _tinttype : getRadioValue("tinttype"),
      _emergent : getCheckBoxValue("emergent"),
      _rsphere : $.rsphere.value.trim(),
      _rcylinder : $.rcylinder.value.trim(),
      _radd : $.radd.value.trim(),
      _lsphere : $.lsphere.value.trim(),
      _lcylinder : $.lcylinder.value.trim(),
      _ladd : $.ladd.value.trim()
   };
}
var result=null;
var setPrice = function(evt){
  evt = evt ? evt : (window.event ? window.event : null);
  var elem = evt.srcElement ? evt.srcElement : evt.target;  
  ChangeParam(elem);
  var lensDetail = getDetail();
  var price=0,factor=0,detail="";
  if((lensDetail._rsphere!="" && lensDetail._rsphere!="0") ||
     (lensDetail._rcylinder!="" && lensDetail._rcylinder!="0") ||
     (lensDetail._radd!="" && lensDetail._radd!="0")){
     factor+=0.5;
  }
  if((lensDetail._lsphere!="" && lensDetail._lsphere!="0") ||
     (lensDetail._lcylinder!="" && lensDetail._lcylinder!="0") ||
     (lensDetail._ladd!="" && lensDetail._ladd!="0")){
     factor+=0.5;
  }
  if(factor!=0){
     detail+=lensDetail._material;
     detail+=","+lensDetail._material2;
     detail+=","+lensDetail._lenstype;
     if(lensDetail._treat=="基片"){
        detail+=",UNC";
     }else if(lensDetail._treat=="加硬"){
        detail+=",HC";
     }else if(lensDetail._treat=="可染加硬"){
        detail+=",THC";
     }else if(lensDetail._treat=="加硬镀膜"){
       detail+=",HMC";
     }else if(lensDetail._treat=="超加硬镀膜"){
       detail+=",Super HMC";
     }else{
        detail+=","+lensDetail._treat;
     }
     price+=getModelPrice(detail);
     if(lensDetail._uv=="uv") price+=result.userGroup.uv;
     if(lensDetail._tinttype!="None") price+=result.userGroup.coloration;
     if(result.userGroup.checkframe==1) {
        price+=getFramePrice(lensDetail._framestyle);
        document.getElementById("_tframe").value = getFrameType(lensDetail._framestyle);
     }
     price=price*factor;
     if(lensDetail._emergent=="on") price+=result.userGroup.urgent;
  }
  document.getElementById("_price").value = price; 
}
function ChangeParam(param){
   if(param.name=="material"){
     if(param.value=="None"){
        hiddenRadio("material2");
        hiddenRadio("lenstype");
     }else{
        changeMaterial(param);
        var j=0;
         var $radios=document.getElementsByName("material2"); 
		  for(var i=0;i<$radios.length;i++){
		    if($radios[i].checked){
		      changeLensType($radios[i]);
		      j++;
		      break;
		    }
		  }
		  if(j==0) hiddenRadio("lenstype");
     }
   }
   if(param.name=="material2"){
      changeLensType(param);
   }
}
function hiddenRadio(paramName){
  var $radios=document.getElementsByName(paramName); 
  for(var i=0;i<$radios.length;i++){
    $radios[i].checked=false;
    $radios[i].parentNode.disabled=true;
  }
}
function changeLensType(param){
   var $m1=getRadioValue("material");
   var $dd=new Array();
   for(var i=0;i<$d.length;i++){
    if($d[i].m1==$m1&&$d[i].m2==param.value){
       $dd.push($d[i]);
    } 
   }
   var $radios=document.getElementsByName("lenstype"); 
   var k;
   for(var i=0;i<$radios.length;i++){
    k=0;
    for(var j=0;j<$dd.length;j++){
      if($dd[j].t==$radios[i].value||$dd[j].t==""){
        k++;
        break;
      }
    }    
     if(k==1){
         $radios[i].parentNode.disabled=false;
      }else{
         $radios[i].checked=false;
         $radios[i].parentNode.disabled=true;
      }
   }
}
function changeMaterial(param){
  var $dd=new Array();
  for(var i=0;i<$d.length;i++){
    if($d[i].m1==param.value){
      $dd.push($d[i]);
    }
  }
  var $radios=document.getElementsByName("material2"); 
  var k;
  for(var i=0;i<$radios.length;i++){
     k=0;
    for(var j=0;j<$dd.length;j++){
      if($dd[j].m2==$radios[i].value){
        k++;
        break;
      }
    }   
    if(k==1){
      $radios[i].parentNode.disabled=false;
    }else{
      $radios[i].checked=false;
      $radios[i].parentNode.disabled=true;
    }
  }
}
var getFramePrice = function(fstyle){
  var _framePrice=result.framePrice;
  for(var i=0;i<_framePrice.length;i++){
    if(_framePrice[i].key==fstyle) return _framePrice[i].value;
  }
  return result.userGroup.fullframe;
}
var getModelPrice = function(model){
   var _UserPrice=result.userPrice;
   for(var i=0;i<_UserPrice.length;i++){
     if(_UserPrice[i].model==model) return _UserPrice[i].price;
   }
   return 0;
}
var getFrameType = function(name){
   var _UserFrame=result.userFrame;
   for(var i=0;i<_UserFrame.length;i++){
      if(_UserFrame[i].framename==name) return _UserFrame[i].frametype;
   }
   return "";
}
var batchAddEvent = function(name,callback){
   var $=document.getElementsByName(name);
   for(var i=0;i<$.length;i++){
     $[i].onclick = callback;
   }
}
var addEvent = function(){
  var $=document.getElementsByTagName("form")[0];
  $.framestyle.onchange = setPrice;
  batchAddEvent("material",setPrice);
  batchAddEvent("material2",setPrice);
  batchAddEvent("lenstype",setPrice);
  batchAddEvent("treat",setPrice);
  batchAddEvent("uv",setPrice);
  batchAddEvent("tinttype",setPrice);
  batchAddEvent("emergent",setPrice);
  $.rsphere.onchange = setPrice;
  $.rcylinder.onchange = setPrice;
  $.radd.onchange = setPrice;
  $.lsphere.onchange = setPrice;
  $.lcylinder.onchange = setPrice;
  $.ladd.onchange = setPrice;
}
