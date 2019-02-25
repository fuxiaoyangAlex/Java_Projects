<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.spark.service.LogService" %>
<%@ page import="com.spark.domain.T_ochart" %>
<%@ page import="java.util.List" %>
<%@ page import="com.spark.domain.T_rchart" %>
<!--<!DOCTYPE html>-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="css/echarts.min.js"></script>
<html>
<head>
    <meta charset="utf-8">
    <script type="text/javascript" src="css/echarts.min.js"></script>
</head>

<body>
<div id="main" style="width: 1200px;height:400px;"></div>
<%
    LogService ls = new LogService();
    List<T_ochart> allOchart = ls.getAllOchart();//获取所有的操作视图的数据
    List<T_rchart> allRchart = ls.getAllRchart();//获取所有的注册视图的数据
    pageContext.setAttribute("allOchart",allOchart);
    pageContext.setAttribute("allRchart",allRchart);
    System.out.println("allOchart: " + allOchart);
    System.out.println("allRchart: " + allRchart);
    T_ochart r = allOchart.get(0); //用户活跃度
    T_rchart r2 = allRchart.get(0); //站点访问量

    //存入域对象之中
%>
<script type="text/javascript">
    var dom = document.getElementById("main");
    var myChart = echarts.init(dom);
    var app = {};
    option = null;
    option = {
        title : {
            text: "站点统计（日均）",
            subtext: '单位（h）'
        },
        tooltip : {
            trigger: 'axis'
        },
        legend: {
            data:['站点访问量','用户活跃度']
        },
        toolbox: {
            show : true,
            feature : {
                dataView : {show: true, readOnly: false},
                magicType : {show: true, type: ['line', 'bar']},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
        xAxis : [
            {
                type : 'category',
                data : ['00:00-02:00','02:00-04:00','04:00-06:00','6:00-8:00','8:00-10:00','10:00-12:00','12:00-14:00','14:00-16:00','16:00-18:00','18:00-20:00','20:00-22:00','22:00-24:00']
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                //rchart==========================================================================================
                name:'站点访问量',
                type:'bar',
                data:[
                    <%=r2.getTr1()%>,<%=r2.getTr2()%>,<%=r2.getTr3()%>,<%=r2.getTr4()%>,
                    <%=r2.getTr5()%>,<%=r2.getTr6()%>,<%=r2.getTr7()%>,<%=r2.getTr8()%>,
                    <%=r2.getTr9()%>,<%=r2.getTr10()%>,<%=r2.getTr11()%>,<%=r2.getTr12()%>
                    //2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3

                ],
                markPoint : {
                    data : [
                        {type : 'max', name: '最大值'},
                        {type : 'min', name: '最小值'}
                    ]
                },
                markLine : {
                    data : [
                        {type : 'average', name: '平均值'}
                    ]
                }
            },
            {
                //ochart==========================================================================================
                name:'用户活跃度',
                type:'bar',
                data:[
                    <%=r.getTo1()%>,<%=r.getTo2()%>,<%=r.getTo3()%>,<%=r.getTo4()%>,<%=r.getTo5()%>,<%=r.getTo6()%>,
                    <%=r.getTo7()%>,<%=r.getTo8()%>,<%=r.getTo9()%>,<%=r.getTo10()%>,<%=r.getTo11()%>,<%=r.getTo12()%>
                ],
                markPoint : {
                    data : [
                        {name : '日均最高', value : 182.2, xAxis: 7, yAxis: 183},
                        {name : '日均最低', value : 2.3, xAxis: 11, yAxis: 3}
                    ]
                },
                markLine : {
                    data : [
                        {type : 'average', name : '平均值'}
                    ]
                }
            }
        ]
    };
    ;
    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }
</script>
</body>
</html>