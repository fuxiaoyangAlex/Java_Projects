$(function () {
    // 根据连接通过关键字获取参数值
    function GetRequest2(key)
    {
        var url = location.search;
        var theRequest = new Object();
        if (url.indexOf("?") != -1)
        {
            var str = url.substr(1);
            strs = str.split("&");
            for (var i = 0; i < strs.length; i++)
            {
                theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
            }
        }
        var value = theRequest[key];
        return value;
    }

// 根据phone获取管理员的所有信息
    $(function(){
        var adminphone =  GetRequest2("phone");
        console.log("管理员手机号码：" + adminphone + "we are the world!");
        $.ajax({
            url:  "http://localhost:8080/CloudDemo/admin/getAdminInfoByPhone.action",
            type: "POST",
            data: JSON.stringify({"adminphone":adminphone}),
            contentType:"application/json",
            dataType: "json",
            success:function (data) {

               console.log(JSON.stringify(data.admininfo));
               sessionStorage.setItem("key",JSON.stringify(data.admininfo));
               var admindata = JSON.parse(sessionStorage.getItem("key"));
                console.log("alert key : " + admindata);

                console.log("管理员姓名：" + data.admininfo.name);
                $("#adminname").text(data.admininfo.name);
                console.log(JSON.stringify(data.admininfo));
                $("#admingender").text(data.admininfo.gender);
                console.log("管理员性别：" + data.admininfo.gender);
                $("#adminemail").text(data.admininfo.email);
                console.log("管理员email: " + data.admininfo.email);
                $("#adminqq").text(data.admininfo.qq);
                console.log("管理员qq：" + data.admininfo.qq);
                $("#adminadress").text(data.admininfo.address);
                console.log("管理员地址：" + data.admininfo.address);
                $("#admincreatetime").text(data.admininfo.createtime);
                console.log("管理员注册时间★：" + data.admininfo.createtime);
                if(data.admininfo.adminflag === 1){

                    console.log("管理员：" + "adminflag★: " + data.admininfo.adminflag + " 管理员权限判别：高级管理员");
                }else{
                    console.log("管理员：" + "adminflag★: " + data.admininfo.adminflag + " 管理员权限判别：普通管理员");
                    console.log("flagControl----------listAdmin" );
                    // console.log("flagControl : " + $("#flagControl") );
                    $("#flagControl").hide();
                    $("#listAdmin").hide();
                }

                console.log("id:admininfoname:: " + $("#admininfoname"));
            }
        });



    });

});



