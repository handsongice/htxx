var Common = {
    /**
     * 格式化日期（不含时间）
     */
    formatterDate: function (date) {
        if (date == undefined) {
            return "";
        }
        /*json格式时间转js时间格式   */
        date = date.substring(1, date.length - 2);
        var obj = eval('(' + "{Date: new " + date + "}" + ')');
        var date = obj["Date"];
        if (date.getFullYear() < 1900) {
            return "";
        }
 
        var datetime = date.getFullYear()
                + "-"// "年"
                + ((date.getMonth() + 1) > 10 ? (date.getMonth() + 1) : "0"
                        + (date.getMonth() + 1))
                + "-"// "月"
                + (date.getDate() < 10 ? "0" + date.getDate() : date
                        .getDate());
        return datetime;
    },
    /**
     * 格式化日期（含时间"00:00:00"）
     */
    formatterDate2: function (date) {
        if (date == undefined) {
            return "";
        }
        /*json格式时间转js时间格式*/
        date = date.substr(1, date.length - 2);
        var obj = eval('(' + "{Date: new " + date + "}" + ')');
        var date = obj["Date"];
        if (date.getFullYear() < 1900) {
            return "";
        }
 
        /*把日期格式化*/
        var datetime = date.getFullYear()
                + "-"// "年"
                + ((date.getMonth() + 1) > 10 ? (date.getMonth() + 1) : "0"
                        + (date.getMonth() + 1))
                + "-"// "月"
                + (date.getDate() < 10 ? "0" + date.getDate() : date
                        .getDate()) + " " + "00:00:00";
        return datetime;
    },
    /**
     * 格式化去日期（含时间）
     */
    formatterDateTime: function (date) {
        if (date == undefined) {
            return "";
        }
        /*json格式时间转js时间格式*/
        date = date.substring(1, date.length - 2);
        var obj = eval('(' + "{Date: new " + date + "}" + ')');
        var date = obj["Date"];
        if (date.getFullYear() < 1900) {
            return "";
        }
 
        var datetime = date.getFullYear()
                + "-"// "年"
                + ((date.getMonth() + 1) > 10 ? (date.getMonth() + 1) : "0"
                        + (date.getMonth() + 1))
                + "-"// "月"
                + (date.getDate() < 10 ? "0" + date.getDate() : date
                        .getDate())
                + " "
                + (date.getHours() < 10 ? "0" + date.getHours() : date
                        .getHours())
                + ":"
                + (date.getMinutes() < 10 ? "0" + date.getMinutes() : date
                        .getMinutes())
                + ":"
                + (date.getSeconds() < 10 ? "0" + date.getSeconds() : date
                        .getSeconds());
        return datetime;
    }
};
//对Date的扩展，将 Date 转化为指定格式的String  
//月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，  
//年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)  
//例子：  
//(new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423  
//(new Date()).Format("yyyy-M-d h:m:s.S")   ==> 2006-7-2 8:9:4.18  
Date.prototype.fmt = function(fmt)  
{ //author: meizz  
var o = {  
"M+" : this.getMonth()+1,         //月份  
"d+" : this.getDate(),          //日  
"h+" : this.getHours(),          //小时  
"m+" : this.getMinutes(),         //分  
"s+" : this.getSeconds(),         //秒  
"q+" : Math.floor((this.getMonth()+3)/3), //季度  
"S" : this.getMilliseconds()       //毫秒  
};  
if(/(y+)/.test(fmt))  
fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));  
for(var k in o)  
if(new RegExp("("+ k +")").test(fmt))  
fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));  
return fmt;  
}
//计算N天后的时间
function GetDateStr(AddDayCount) {
    var dd = new Date();
    dd.setDate(dd.getDate()+AddDayCount);//获取AddDayCount天后的日期
    var y = dd.getFullYear();
    var m = (dd.getMonth()+1)<10?"0"+(dd.getMonth()+1):(dd.getMonth()+1);//获取当前月份的日期，不足10补0
    var d = dd.getDate()<10?"0"+dd.getDate():dd.getDate();//获取当前几号，不足10补0
    return y+"-"+m+"-"+d;
}