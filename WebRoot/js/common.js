/**
 * 工具js
 */

var dayTime = 24 * 60 * 60 * 1000;
//图表http get接口
var chart_http='http://sys.nbrobo.com/vipInspectData/chartall';

/**
 * 计算年龄，birthday格式：1992-06-06
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
			return age+"岁";
		}else{
			return "";
		}
	}else{
		return "";
	}
}

/**
 * 时间格式转换
 * @param date
 * @param flag true:有时分秒，false：没有时分秒
 * @returns {String}
 */
function formatterDateTime2(date, flag) {
	var datetime = date.getFullYear()
			+ "-"// "年"
			+ ((date.getMonth() + 1) > 10 ? (date.getMonth() + 1) :
					+ (date.getMonth() + 1)) + "-"// "月"
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
 * 时间格式转换,不显示秒
 * @param date
 * @returns {String}
 */
function formatterDateTime(date) {
	var datetime = date.getFullYear()
			+ "年"// "年"
			+ ((date.getMonth() + 1) > 10 ? (date.getMonth() + 1) :
					+ (date.getMonth() + 1)) + "月"// "月"
			+ (date.getDate() < 10 ? "0" + date.getDate() : date.getDate())
			+ "日";
	
		datetime += (date.getHours() < 10 ? "0" + date.getHours() : date
				.getHours());
		datetime += ":";
		datetime += (date.getMinutes() < 10 ? "0" + date.getMinutes() : date
				.getMinutes());
	

	return datetime;
}

/**
 * 判断是否为空，是返回true，否返回false
 * @param obj
 * @returns
 */
function isEmpty(obj){
	return (obj == null || obj == "null" || $.trim(obj) == "")?true:false;
}


/**
 * 格式化空字符串，如果为空，返回""
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
 * 判断是否是正整数
 */
function IsNum1(s)
{
    if(s!=null){
        var r,re;
        re = /\d*/i; //\d表示数字,*表示匹配多个数字
        r = s.match(re);
        return (r==s)?true:false;
    }
    return false;
}

/**
 * 判断是否为数字
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
