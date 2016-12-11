/**
 * tooljs
 */

var dayTime = 24 * 60 * 60 * 1000;

/**
 * Calculating age，birthdayformat：1992-06-06
 */
function formatBirthday(birthday){
	var str = birthday;
	var arr = str.split("-");
	var yesteday = new Date(arr[0],arr[1],arr[2]);
	var now = new Date();
	var len = now.getTime()-yesteday.getTime();
	if(!isEmpty(str)){
		var flag = len/(dayTime*365);
		if(!isNaN(flag)){
			return parseInt(flag)+"year";
		}else{
			return "";
		}
	}else{
		return "";
	}
}

/**
 * Time format conversion
 * @param date
 * @param flag true:Sometimes every minute，false：Not every minute
 * @returns {String}
 */
function formatterDateTime2(date, flag) {
	var datetime = date.getFullYear()
			+ "-"// "year"
			+ ((date.getMonth() + 1) > 10 ? (date.getMonth() + 1) :
					+ (date.getMonth() + 1)) + "-"// "month"
			+ (date.getDate() < 10 ? "0" + date.getDate() : date.getDate())
			+ " ";
	if (flag) {
		datetime += (date.getHours() < 10 ? "0" + date.getHours() : date
				.getHours());
		datetime += ":";
		datetime += (date.getMinutes() < 10 ? "0" + date.getMinutes() : date
				.getMinutes());
		datetime += ":";
		datetime += (date.getSeconds() < 10 ? "0" + date.getSeconds() : date
				.getSeconds());
	}

	return datetime;
}

/**
 * Time format conversion,Does not display seconds
 * @param date
 * @returns {String}
 */
function formatterDateTime(date) {
	var datetime = date.getFullYear()
			+ "year"// "year"
			+ ((date.getMonth() + 1) > 10 ? (date.getMonth() + 1) :
					+ (date.getMonth() + 1)) + "month"// "month"
			+ (date.getDate() < 10 ? "0" + date.getDate() : date.getDate())
			+ "day";
	
		datetime += (date.getHours() < 10 ? "0" + date.getHours() : date
				.getHours());
		datetime += ":";
		datetime += (date.getMinutes() < 10 ? "0" + date.getMinutes() : date
				.getMinutes());
	

	return datetime;
}

/**
 * Whether the judgment is empty，Is returntrue，No returnfalse
 * @param obj
 * @returns
 */
function isEmpty(obj){
	return (obj == null || obj == "null" || $.trim(obj) == "")?true:false;
}

/**
 * Formatting time
 * @param time  1992-03-33
 * @returns {String}  The millisecond value is empty.，Return--
 */
function formateTime2(time){
	if(isEmpty(time)){
		return "--";
	}else{
		return time;
	}
}

/**
 * Formatting time
 * @param time  Millisecond value
 * @returns {String}  The millisecond value is empty.，Return--
 */
function formateTime(time){
	if(isEmpty(time)){
		return "--";
	}else{
		return formatterDateTime2(new Date(time),true);
	}
}


/**
 * Format null string，If it is empty，Return""
 * @param str
 * @returns
 */
function formateStr(str){
	if(isEmpty(str)){
		return "";
	}else{
		return str;
	}
}



/**
 * To determine whether a positive integer
 */
function IsNum1(s)
{
    if(s!=null){
        var r,re;
        re = /\d*/i; //\dExpress number,*That matches the number of digits
        r = s.match(re);
        return (r==s)?true:false;
    }
    return false;
}

/**
 * To determine whether or not to figure
 */
function IsNum2(s)
{
    if (s!=null && s!="")
    {
        return !isNaN(s);
    }
    return false;
}

/**
 ** Additive function，Used to get accurate results.
 ** Explain：javascriptThe result of the addition will be error，It will be more obvious when the two floating point numbers are added together.。This function returns a more accurate addition result.。
 ** call：accAdd(arg1,arg2)
 ** Return value：arg1Addarg2Exact result
 **/
function accAdd(arg1, arg2) {
    var r1, r2, m, c;
    try {
        r1 = arg1.toString().split(".")[1].length;
    }
    catch (e) {
        r1 = 0;
    }
    try {
        r2 = arg2.toString().split(".")[1].length;
    }
    catch (e) {
        r2 = 0;
    }
    c = Math.abs(r1 - r2);
    m = Math.pow(10, Math.max(r1, r2));
    if (c > 0) {
        var cm = Math.pow(10, c);
        if (r1 > r2) {
            arg1 = Number(arg1.toString().replace(".", ""));
            arg2 = Number(arg2.toString().replace(".", "")) * cm;
        } else {
            arg1 = Number(arg1.toString().replace(".", "")) * cm;
            arg2 = Number(arg2.toString().replace(".", ""));
        }
    } else {
        arg1 = Number(arg1.toString().replace(".", ""));
        arg2 = Number(arg2.toString().replace(".", ""));
    }
    return (arg1 + arg2) / m;
}