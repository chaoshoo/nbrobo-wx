/**
 * tooljs
 */

var dayTime = 24 * 60 * 60 * 1000;
//图表http get接口
var chart_http='http://sys.nbrobo.com/vipInspectData/chartall';

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
			var age = parseInt(flag);
			if(age>100) {
				return "";
			}
			return age+"year";
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

function istel(mobile){
	if (mobile != "") {
		 var myregmb =/^1[3|4|5|7|8][0-9]\d{8}$/;
		 if(myregmb.test(mobile)){		 
			 return true;
		 }
	}
	return false;
}