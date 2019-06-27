$(function () {

        // 根据连接通过关键字获取参数值
        var admindata = JSON.parse(sessionStorage.getItem("key"));
        console.log("site: ---- ooo" + admindata);
        console.log("site: ---- ooo" + admindata.name);
        console.log("site: ---- ooo" + admindata.address);

        // console.log("管理员姓名💗" + data.admininfo.name);
        $("#admininfoname").attr("value", admindata.name);
        $("#admininfogender").attr("value", admindata.gender);
        $("#admininfoaddress").attr("value", admindata.address);
        $("#admininfophone").attr("value", admindata.phone);
        $("#admininfoemail").attr("value", admindata.email);
        $("#admininfoqq").attr("value", admindata.qq);
        if(1 === admindata.adminflag){
            $("#admininfoflag").text("高级管理员");
        }else{
            $("#admininfoflag").text("普通管理员");
        }

        $("#admininfocreatetime").text(admindata.createtime);



});
