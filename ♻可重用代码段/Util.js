
$(function(){

    // alert("获取系统所有用户的个数");
    // 获取系统所有用户的个数
    $.ajax({
        url:  "http://localhost:8080/CloudDemo/user/getCountUsersNum.do",
        type: "GET",
        contentType:"application/json",
        dataType: "json",
        success:function (data) {
            console.log("-----获取系统所有用户的个数---------> " + JSON.stringify(data));
            $("#cnum").text(data.cnum);
        }
    });
});



/**
 * 获取服务器IP及端口
 * 例如：http://localhost:8080
 *
 * @returns
 */
function getLocalhostPath(){
    var curWwwPath = window.document.location.href;
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    var localhostPath = curWwwPath.substring(0, pos);
    return localhostPath;
}

/**
 * 获取当前应用名称
 *
 * @returns {string}
 */
function getDisplayName(){
    var pathName = window.document.location.pathname;
    var pos = pathName.indexOf('/', 1);
    var displayName = pathName.substring(0, pos+1);
    return displayName;
}

/**
 * 获取当前应用基础请求路径
 * 例如：http://localhost:8080/YunZhi
 *
 * @returns {string}
 */
function getBaseUrl(){
    return getLocalhostPath() + getDisplayName();
}

/**
 *  导出用户表Excel
 *  
 *
 <form id="form" method="post" action=""></form>
 */
function getUserInfoExcel() {
    console.log("we are the world");
    $("#form").attr("action", getBaseUrl() + "sys/exportUsers");
    $("#form").submit();
}

