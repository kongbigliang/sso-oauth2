<%@page import="com.kongbig.security.SecurityContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/common/meta.jsp"%>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<meta name="keywords" content="">
<meta name="description" content="">

<!-- 自定义js文件 -->

<title>H-ui.admin v2.3</title>
</head>
<body>
	<header class="Hui-header cl">
		<a class="Hui-logo l" title="sso-server" href="/">SSO服务器管理</a>
		<a class="Hui-logo-m l" href="/" title="sso-server">SSO</a> <span
			class="Hui-subtitle l">V1.0</span>
		<nav class="mainnav cl" id="Hui-nav">
			<ul>
				<li class="dropDown dropDown_click">
					<a href="javascript:;" aria-expanded="true" aria-haspopup="true" data-toggle="dropdown" class="dropDown_A">
						<i class="Hui-iconfont">&#xe600;</i> 新增 
						<i class="Hui-iconfont">&#xe6d5;</i>
					</a>
					<ul class="dropDown-menu radius box-shadow">
						<li>
							<a href="javascript:;" onclick="article_add('添加资讯','article-add.html')">
								<i class="Hui-iconfont">&#xe616;</i> 资讯
							</a>
						</li>
						<li>
							<a href="javascript:;" onclick="picture_add('添加资讯','picture-add.html')">
								<i class="Hui-iconfont">&#xe613;</i> 图片
							</a>
						</li>
						<li>
							<a href="javascript:;" onclick="product_add('添加资讯','product-add.html')">
								<i	class="Hui-iconfont">&#xe620;</i> 产品
							</a>
						</li>
						<li><a href="javascript:;" onclick="member_add('添加用户','member-add.html','','510')">
								<i class="Hui-iconfont">&#xe60d;</i> 用户
							</a>
						</li>
					</ul>
				</li>
			</ul>
		</nav>
		<ul class="Hui-userbar">
			<li><%=SecurityContext.getCurrentOAuthUser().getUsername()%></li>
			<li class="dropDown dropDown_hover">
				<a href="#" class="dropDown_A"><%=SecurityContext.getCurrentOAuthUser().getRemark()%>
					<i class="Hui-iconfont">&#xe6d5;</i>
				</a>
				<ul class="dropDown-menu radius box-shadow">
					<li><a href="#">个人信息</a></li>
					<li><a href="#">切换账户</a></li>
					<li><a href="#">退出</a></li>
				</ul></li>
			<li id="Hui-msg">
				<a href="#" title="消息">
					<span class="badge badge-danger">1</span>
					<i class="Hui-iconfont" style="font-size: 18px">&#xe68a;</i>
				</a>
			</li>
			<li id="Hui-skin" class="dropDown right dropDown_hover">
				<a href="javascript:;" title="换肤">
					<i class="Hui-iconfont" style="font-size: 18px">&#xe62a;</i>
				</a>
				<ul class="dropDown-menu radius box-shadow">
					<li><a href="javascript:;" data-val="default" title="默认（黑色）">默认（黑色）</a></li>
					<li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>
					<li><a href="javascript:;" data-val="green" title="绿色">绿色</a></li>
					<li><a href="javascript:;" data-val="red" title="红色">红色</a></li>
					<li><a href="javascript:;" data-val="yellow" title="黄色">黄色</a></li>
					<li><a href="javascript:;" data-val="orange" title="绿色">橙色</a></li>
				</ul>
			</li>
		</ul>
		<a href="javascript:;" class="Hui-nav-toggle Hui-iconfont" aria-hidden="false">&#xe667;</a>
	</header>
	<aside class="Hui-aside" id="myMenu">
	
		<input runat="server" id="divScrollValue" type="hidden" value="" />
		<div class="menu_dropdown bk_2">
		
			<!-- 用户管理开始 -->
			<dl id="menu-user">
				<dt>
					<i class="Hui-iconfont">&#xe60d;</i>用户管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
				</dt>
				<dd>
					<ul>
						<li><a _href="${contextPath }/module/user/user-list.jsp" data-title="用户列表" href="javascript:;">用户列表</a></li>
					</ul>
				</dd>
			</dl>
			<!-- 用户管理结束 -->
			
			<!-- 第三方应用管理开始 -->
			<dl id="menu-app">
				<dt>
					<i class="Hui-iconfont">&#xe616;</i>第三方应用管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
				</dt>
				<dd>
					<ul>
						<li><a _href="${contextPath }/module/client/client-list.jsp" data-title="第三方应用列表" href="javascript:;">第三方应用列表</a></li>
					</ul>
				</dd>
			</dl>
			<!-- 第三方应用管理结束 -->
			
			<!-- 角色管理开始 -->
			<%-- <dl id="menu-role">
				<dt><i class="Hui-iconfont">&#xe62d;</i> 角色管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
				<dd>
					<ul>
						<li><a _href="${contextPath }/module/role/role-list.jsp" data-title="角色列表" href="javascript:void(0)">角色列表</a></li>
						<li><a _href="${contextPath }/module/role/admin-list.jsp" data-title="管理员列表" href="javascript:void(0)">管理员列表</a></li>
					</ul>
				</dd>
			</dl> --%>
			<!-- 角色管理结束 -->
			<!-- 功能管理开始 -->
			<!-- <dl id="menu-function">
				<dt><i class="Hui-iconfont">&#xe615;</i> 功能管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
				<dd>
					<ul>
						<li><a _href="/security/module/function/function-list.jsp" data-title="功能列表" href="javascript:void(0)">功能列表</a></li>
					</ul>
				</dd>
			</dl> -->
			<!-- 功能管理结束 -->
			<!-- 权限管理开始 -->
			<!-- <dl id="menu-permission">
				<dt><i class="Hui-iconfont">&#xe62e;</i> 权限管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
				<dd>
					<ul>
						<li><a _href="/security/module/permission/permission-role-list.jsp" data-title="分配权限" href="javascript:void(0)">分配权限</a></li>
					</ul>
				</dd>
			</dl> -->
			<!-- 权限管理结束 -->
			<!-- 菜单管理开始 -->
			<!-- <dl id="menu-menu">
				<dt><i class="Hui-iconfont">&#xe623;</i> 菜单管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
				<dd>
					<ul>
						<li><a _href="/security/module/menu/menu-role-list.jsp" data-title="分配菜单" href="javascript:void(0)">分配菜单</a></li>
						<li><a _href="/security/module/menu/menu-list.jsp" data-title="菜单列表" href="javascript:void(0)">菜单列表</a></li>
					</ul>
				</dd>
			</dl> -->
			<!-- 菜单管理结束 -->
			<!-- 字典管理开始 -->
			<!-- <dl id="menu-dic">
				<dt><i class="Hui-iconfont">&#xe626;</i> 字典管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
				<dd>
					<ul>
						<li><a _href="/security/module/dic/dic-list.jsp" data-title="数据字典" href="javascript:void(0)">数据字典</a></li>
					</ul>
				</dd>
			</dl> -->
			<!-- 字典管理结束 -->
			<!-- 日志管理开始 -->
			<!-- <dl id="menu-log">
				<dt><i class="Hui-iconfont">&#xe616;</i> 日志管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
				<dd>
					<ul>
						<li><a _href="/security/module/log/log-list.jsp" data-title="系统日志" href="javascript:void(0)">系统日志</a></li>
					</ul>
				</dd>
			</dl> -->
			<!-- 日志管理结束 -->
			
		</div>
		
	</aside>
	<div class="dislpayArrow">
		<a class="pngfix" href="javascript:void(0);"
			onClick="displaynavbar(this)"></a>
	</div>
	<section class="Hui-article-box">
		<div id="Hui-tabNav" class="Hui-tabNav">
			<div class="Hui-tabNav-wp">
				<ul id="min_title_list" class="acrossTab cl">
					<li class="active"><span title="我的桌面" data-href="welcome.html">我的桌面</span><em></em></li>
				</ul>
			</div>
			<div class="Hui-tabNav-more btn-group">
				<a id="js-tabNav-prev" class="btn radius btn-default size-S"
					href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a
					id="js-tabNav-next" class="btn radius btn-default size-S"
					href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a>
			</div>
		</div>
		<div id="iframe_box" class="Hui-article">
			<div class="show_iframe">
				<div style="display: none" class="loading"></div>
				<%-- ${contextPath }/module/user/user-list.jsp --%>
				<iframe scrolling="yes" frameborder="0" src="${contextPath }/module/user/user-list.jsp"></iframe>
			</div>
		</div>
	</section>
	
	<script type="text/javascript">
	
	</script>
</body>
</html>