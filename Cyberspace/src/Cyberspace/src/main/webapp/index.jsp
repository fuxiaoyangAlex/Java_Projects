<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/11/25
  Time: 17:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="text/javascript" src="Bootstrap/jquery/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="Bootstrap/js/bootstrap.min.js"></script>
    <link href="Bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
    <link rel="shortcut icon" href="images/icon02.png"/>
    <title>Cybersapce</title>
</head>
<body bgcolor="black">
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="row clearfix">
                <div class="col-md-6 column">
                    <ul class="nav nav-pills">
                        <li class="active">
                            <a href="user_login.jsp">登录</a>
                        </li>
                        <li>
                            <a href="#">简介</a>
                        </li>
                        <li class="disabled">
                            <a href="#">信息</a>
                        </li>
                        <li class="dropdown pull-right">
                            <a href="#" data-toggle="dropdown" class="dropdown-toggle">下拉<strong class="caret"></strong></a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="#">操作</a>
                                </li>
                                <li>
                                    <a href="#">设置栏目</a>
                                </li>
                                <li>
                                    <a href="#">更多设置</a>
                                </li>
                                <li class="divider">
                                </li>
                                <li>
                                    <a href="#">分割线</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
                <div class="col-md-6 column">
                    <ul class="breadcrumb">
                        <li>
                            <a href="#">Home</a>
                        </li>
                        <li>
                            <a href="#">Library</a>
                        </li>
                        <li class="active">
                            Data
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div>
        <%@ include file="testFiles/scroll.html"%>
    </div>

    <%--<div class="row clearfix">--%>
        <%--<div class="col-md-12 column">--%>
            <%--<div class="carousel slide" id="carousel-753985">--%>
                <%--<ol class="carousel-indicators">--%>
                    <%--<li data-slide-to="0" data-target="#carousel-753985">--%>
                    <%--</li>--%>
                    <%--<li data-slide-to="1" data-target="#carousel-753985">--%>
                    <%--</li>--%>
                    <%--<li data-slide-to="2" data-target="#carousel-753985" class="active">--%>
                    <%--</li>--%>
                <%--</ol>--%>
                <%--<div class="carousel-inner">--%>
                    <%--<div class="item">--%>
                        <%--<img alt="" src="images/scroll01%20(1).jpg" width="80%"/>--%>
                        <%--<div class="carousel-caption">--%>
                            <%--<h4>--%>
                                <%--First Thumbnail label--%>
                            <%--</h4>--%>
                            <%--<p>--%>
                                <%--Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta--%>
                                <%--gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.--%>
                            <%--</p>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="item">--%>
                        <%--<img alt="" src="images/scroll01%20(2).jpg " width="1920" height="1200"/>--%>
                        <%--<div class="carousel-caption">--%>
                            <%--<h4>--%>
                                <%--Second Thumbnail label--%>
                            <%--</h4>--%>
                            <%--<p>--%>
                                <%--Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta--%>
                                <%--gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.--%>
                            <%--</p>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="item active">--%>
                        <%--<img alt="" src="images/scroll01%20(3).jpg" height="520"/>--%>
                        <%--<div class="carousel-caption">--%>
                            <%--<h4>--%>
                                <%--Third Thumbnail label--%>
                            <%--</h4>--%>
                            <%--<p>--%>
                                <%--Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta--%>
                                <%--gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.--%>
                            <%--</p>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <%--<a class="left carousel-control" href="#carousel-753985" data-slide="prev"><span--%>
                        <%--class="glyphicon glyphicon-chevron-left"></span></a> <a class="right carousel-control"--%>
                                                                                <%--href="#carousel-753985"--%>
                                                                                <%--data-slide="next"><span--%>
                    <%--class="glyphicon glyphicon-chevron-right"></span></a>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
    <div class="row clearfix">
        <div class="col-md-6 column">
            <div class="media">
                <a href="#" class="pull-left"><img src="v3/default7.jpg" class="media-object" alt=''/></a>
                <div class="media-body">
                    <h4 class="media-heading">
                        Nested media heading
                    </h4> Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin
                    commodo. Cras purus odio, vestibulum in vulputate at, tempus viverra turpis.
                    <div class="media">
                        <a href="#" class="pull-left"><img src="v3/default8.jpg" class="media-object" alt=''/></a>
                        <div class="media-body">
                            <h4 class="media-heading">
                                Nested media heading
                            </h4> Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante
                            sollicitudin commodo. Cras purus odio, vestibulum in vulputate at, tempus viverra turpis.
                        </div>
                    </div>
                </div>
            </div>
            <div class="row clearfix">
                <div class="col-md-4 column">
                    <span class="label label-primary">标签</span>
                </div>
                <div class="col-md-4 column">
                    <span class="label label-default">标签</span>
                </div>
                <div class="col-md-4 column">
                    <span class="label label-success">标签</span>
                </div>
            </div>
            <div class="media">
                <a href="#" class="pull-left"><img src="v3/default7.jpg" class="media-object" alt=''/></a>
                <div class="media-body">
                    <h4 class="media-heading">
                        Nested media heading
                    </h4> Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin
                    commodo. Cras purus odio, vestibulum in vulputate at, tempus viverra turpis.
                    <div class="media">
                        <a href="#" class="pull-left"><img src="v3/default8.jpg" class="media-object" alt=''/></a>
                        <div class="media-body">
                            <h4 class="media-heading">
                                Nested media heading
                            </h4> Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante
                            sollicitudin commodo. Cras purus odio, vestibulum in vulputate at, tempus viverra turpis.
                        </div>
                    </div>
                </div>
            </div>
            <div class="media">
                <a href="#" class="pull-left"><img src="v3/default7.jpg" class="media-object" alt=''/></a>
                <div class="media-body">
                    <h4 class="media-heading">
                        Nested media heading
                    </h4> Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin
                    commodo. Cras purus odio, vestibulum in vulputate at, tempus viverra turpis.
                    <div class="media">
                        <a href="#" class="pull-left"><img src="v3/default8.jpg" class="media-object" alt=''/></a>
                        <div class="media-body">
                            <h4 class="media-heading">
                                Nested media heading
                            </h4> Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante
                            sollicitudin commodo. Cras purus odio, vestibulum in vulputate at, tempus viverra turpis.
                        </div>
                    </div>
                </div>
            </div>
            <div class="media">
                <a href="#" class="pull-left"><img src="v3/default7.jpg" class="media-object" alt=''/></a>
                <div class="media-body">
                    <h4 class="media-heading">
                        Nested media heading
                    </h4> Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin
                    commodo. Cras purus odio, vestibulum in vulputate at, tempus viverra turpis.
                    <div class="media">
                        <a href="#" class="pull-left"><img src="v3/default8.jpg" class="media-object" alt=''/></a>
                        <div class="media-body">
                            <h4 class="media-heading">
                                Nested media heading
                            </h4> Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante
                            sollicitudin commodo. Cras purus odio, vestibulum in vulputate at, tempus viverra turpis.
                        </div>
                    </div>
                </div>
            </div>
            <ul class="pagination">
                <li>
                    <a href="#">Prev</a>
                </li>
                <li>
                    <a href="#">1</a>
                </li>
                <li>
                    <a href="#">2</a>
                </li>
                <li>
                    <a href="#">3</a>
                </li>
                <li>
                    <a href="#">4</a>
                </li>
                <li>
                    <a href="#">5</a>
                </li>
                <li>
                    <a href="#">Next</a>
                </li>
            </ul>
        </div>
        <div class="col-md-6 column">
            <nav class="navbar navbar-default" role="navigation">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse"
                            data-target="#bs-example-navbar-collapse-1"><span
                            class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span
                            class="icon-bar"></span><span class="icon-bar"></span></button>
                    <a class="navbar-brand" href="#">Brand</a>
                </div>

                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li class="active">
                            <a href="#">Link</a>
                        </li>
                        <li>
                            <a href="#">Link</a>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown<strong
                                    class="caret"></strong></a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="#">Action</a>
                                </li>
                                <li>
                                    <a href="#">Another action</a>
                                </li>
                                <li>
                                    <a href="#">Something else here</a>
                                </li>
                                <li class="divider">
                                </li>
                                <li>
                                    <a href="#">Separated link</a>
                                </li>
                                <li class="divider">
                                </li>
                                <li>
                                    <a href="#">One more separated link</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                    <form class="navbar-form navbar-left" role="search">
                        <div class="form-group">
                            <input type="text" class="form-control"/>
                        </div>
                        <button type="submit" class="btn btn-default">Submit</button>
                    </form>
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <a href="#">Link</a>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown<strong
                                    class="caret"></strong></a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="#">Action</a>
                                </li>
                                <li>
                                    <a href="#">Another action</a>
                                </li>
                                <li>
                                    <a href="#">Something else here</a>
                                </li>
                                <li class="divider">
                                </li>
                                <li>
                                    <a href="#">Separated link</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>

            </nav>
            <div class="alert alert-dismissable alert-warning">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                <h4>
                    注意!
                </h4> <strong>Warning!</strong> Best check yo self, you're not looking too good. <a href="#"
                                                                                                    class="alert-link">alert
                link</a>
            </div>
            <dl>
                <dt>
                    Description lists
                </dt>
                <dd>
                    A description list is perfect for defining terms.
                </dd>
                <dt>
                    Euismod
                </dt>
                <dd>
                    Vestibulum id ligula porta felis euismod semper eget lacinia odio sem nec elit.
                </dd>
                <dd>
                    Donec id elit non mi porta gravida at eget metus.
                </dd>
                <dt>
                    Malesuada porta
                </dt>
                <dd>
                    Etiam porta sem malesuada magna mollis euismod.
                </dd>
                <dt>
                    Felis euismod semper eget lacinia
                </dt>
                <dd>
                    Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo
                    sit amet risus.
                </dd>
            </dl>
            <div class="panel-group" id="panel-307542">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <a class="panel-title collapsed" data-toggle="collapse" data-parent="#panel-307542"
                           href="#panel-element-994258">Collapsible Group Item #1</a>
                    </div>
                    <div id="panel-element-994258" class="panel-collapse collapse">
                        <div class="panel-body">
                            Anim pariatur cliche...
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <a class="panel-title collapsed" data-toggle="collapse" data-parent="#panel-307542"
                           href="#panel-element-617216">Collapsible Group Item #2</a>
                    </div>
                    <div id="panel-element-617216" class="panel-collapse collapse">
                        <div class="panel-body">
                            Anim pariatur cliche...
                        </div>
                    </div>
                </div>
            </div>
            <table class="table">
                <thead>
                <tr>
                    <th>
                        编号
                    </th>
                    <th>
                        产品
                    </th>
                    <th>
                        交付时间
                    </th>
                    <th>
                        状态
                    </th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>
                        1
                    </td>
                    <td>
                        TB - Monthly
                    </td>
                    <td>
                        01/04/2012
                    </td>
                    <td>
                        Default
                    </td>
                </tr>
                <tr class="success">
                    <td>
                        1
                    </td>
                    <td>
                        TB - Monthly
                    </td>
                    <td>
                        01/04/2012
                    </td>
                    <td>
                        Approved
                    </td>
                </tr>
                <tr class="error">
                    <td>
                        2
                    </td>
                    <td>
                        TB - Monthly
                    </td>
                    <td>
                        02/04/2012
                    </td>
                    <td>
                        Declined
                    </td>
                </tr>
                <tr class="warning">
                    <td>
                        3
                    </td>
                    <td>
                        TB - Monthly
                    </td>
                    <td>
                        03/04/2012
                    </td>
                    <td>
                        Pending
                    </td>
                </tr>
                <tr class="info">
                    <td>
                        4
                    </td>
                    <td>
                        TB - Monthly
                    </td>
                    <td>
                        04/04/2012
                    </td>
                    <td>
                        Call in to confirm
                    </td>
                </tr>
                </tbody>
            </table>
            <p>
                <em>Git</em> 是一个分布式的版本控制系统，最初由 <strong>Linus Torvalds</strong>
                编写，用作Linux内核代码的管理。在推出后，Git在其它项目中也取得了很大成功，尤其是在
                <small>Ruby</small>
                社区中。
            </p>
            <img alt="140x140" src="v3/default3.jpg"/>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <p>
                <em>Git</em> 是一个分布式的版本控制系统，最初由 <strong>Linus Torvalds</strong>
                编写，用作Linux内核代码的管理。在推出后，Git在其它项目中也取得了很大成功，尤其是在
                <small>Ruby</small>
                社区中。
            </p>
        </div>
    </div>
</div>
</body>
</html>
