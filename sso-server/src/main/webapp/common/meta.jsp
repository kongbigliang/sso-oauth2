<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 自定义标签库 -->
<%-- <%@ taglib prefix="sh" uri="http://com.kongbig/html/permission" %> --%>

<%
	String contextPath = request.getContextPath();
	request.setAttribute("contextPath", contextPath);
%>
<!--[if lt IE 9]>
<script type="text/javascript" src="${contextPath }/static/lib/html5.js"></script>
<script type="text/javascript" src="${contextPath }/static/lib/respond.min.js"></script>
<script type="text/javascript" src="${contextPath }/static/lib/PIE_IE678.js"></script>
<![endif]-->
<LINK rel="Bookmark" href="${contextPath}/common/favicon.ico">
<LINK rel="Shortcut Icon" href="${contextPath}/common/favicon.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="${contextPath }/static/lib/html5.js"></script>
<script type="text/javascript" src="${contextPath }/static/lib/respond.min.js"></script>
<script type="text/javascript" src="${contextPath }/static/lib/PIE_IE678.js"></script>
<![endif]-->
<link href="${contextPath }/static/css/H-ui.min.css" rel="stylesheet"
	type="text/css" />
<link href="${contextPath }/static/css/H-ui.admin.css" rel="stylesheet"
	type="text/css" />
<link href="${contextPath }/static/lib/Hui-iconfont/1.0.7/iconfont.css"
	rel="stylesheet" type="text/css" />
<link href="${contextPath }/static/skin/default/skin.css"
	rel="stylesheet" type="text/css" id="skin" />
<link href="${contextPath }/static/css/style.css" rel="stylesheet"
	type="text/css" />
<link href="${contextPath }/static/lib/icheck/icheck.css"
	rel="stylesheet" type="text/css" />

<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<script type="text/javascript"
	src="${contextPath }/static/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript"
	src="${contextPath }/static/lib/layer/layer.js"></script>
<script type="text/javascript" src="${contextPath }/static/js/H-ui.js"></script>
<script type="text/javascript"
	src="${contextPath }/static/js/H-ui.admin.js"></script>
<script type="text/javascript"
	src="${contextPath }/static/lib/icheck/jquery.icheck.min.js"></script>
<script type="text/javascript"
	src="${contextPath }/static/lib/Validform/5.3.2/Validform.min.js"></script>
<script type="text/javascript"
	src="${contextPath }/static/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript"
	src="${contextPath }/static/lib/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="${contextPath }/static/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript">
	var Global = {
		contextPath : '${contextPath }'
	};
</script>
<script type="text/javascript" src="${contextPath }/common/js/common.js"></script>
