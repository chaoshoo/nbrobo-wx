/**
 * 工具js
 */

var dayTime = 24 * 60 * 60 * 1000;

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
			return parseInt(flag)+"岁";
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
 * 格式化时间
 * @param time  1992-03-33
 * @returns {String}  毫秒值为空时，返回--
 */
function formateTime2(time){
	if(isEmpty(time)){
		return "--";
	}else{
		return time;
	}
}

/**
 * 格式化时间
 * @param time  毫秒值
 * @returns {String}  毫秒值为空时，返回--
 */
function formateTime(time){
	if(isEmpty(time)){
		return "--";
	}else{
		return formatterDateTime2(new Date(time),true);
	}
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

/**
 ** 加法函数，用来得到精确的加法结果
 ** 说明：javascript的加法结果会有误差，在两个浮点数相加的时候会比较明显。这个函数返回较为精确的加法结果。
 ** 调用：accAdd(arg1,arg2)
 ** 返回值：arg1加上arg2的精确结果
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
