var dataTableParam = {};
$(function() {
	initDataTable();
	$("#search").click(function(){
		dataTableParam = $("#query").toJson();
		table.fnDraw(true);
	});
})
/* 用户-添加 */
function member_add(title, url, w, h) {
	layer_show(title, url, w, h);
}
/* 用户-修改*/
function member_edit(data) {
	layer_show("修改用户", Global.contextPath + "/oauthuser/getOAuthUserById.do?id=" + data, "", 510);
}
/* 用户-查看 */
function member_show(data) {
	layer_show("新增用户", "/security/UserController/viewUser.do?id=" + data, 400, 510);
}
/* 用户-停用 */
function member_stop(obj, id) {
	layer
			.confirm(
					'确认要停用吗？',
					function(index) {
						$(obj)
								.parents("tr")
								.find(".td-manage")
								.prepend(
										'<a style="text-decoration:none" onClick="member_start(this,id)" href="javascript:;" title="启用"><i class="Hui-iconfont">&#xe6e1;</i></a>');
						$(obj)
								.parents("tr")
								.find(".td-status")
								.html(
										'<span class="label label-defaunt radius">已停用</span>');
						$(obj).remove();
						layer.msg('已停用!', {
							icon : 5,
							time : 1000
						});
					});
}

/* 用户-启用 */
function member_start(obj, id) {
	layer
			.confirm(
					'确认要启用吗？',
					function(index) {
						$(obj)
								.parents("tr")
								.find(".td-manage")
								.prepend(
										'<a style="text-decoration:none" onClick="member_stop(this,id)" href="javascript:;" title="停用"><i class="Hui-iconfont">&#xe631;</i></a>');
						$(obj)
								.parents("tr")
								.find(".td-status")
								.html(
										'<span class="label label-success radius">已启用</span>');
						$(obj).remove();
						layer.msg('已启用!', {
							icon : 6,
							time : 1000
						});
					});
}
/* 密码-修改 */
function change_password(title, url, id, w, h) {
	layer_show(title, url, w, h);
}

/* 删除 */
function del(id) {
	layer.confirm('确认要删除吗？', function(index) {
		$.ajax({
			"type" : 'get',
			"url" : Global.contextPath + '/oauthuser/deleteOAuthUser.do',
			"dataType" : "json",
			"data" : {
				"ids" : id
			},
			"success" : function(data) {
				// console.log(data);
				if (data.code == '001') {
					table.fnDraw(false);// 刷新表格数据false为当前页数，true为刷新到第一页
					layer.msg('已删除!', {
						icon : 1,
						time : 1000
					});
				} else {
					layer.msg('操作失败!', {
						icon : 1,
						time : 1000
					});
				}
			}
		});

	});
}
/* 批量删除 */
function datasDelete() {
	var obj = $("input[name^='checkbox_']:checked");
	if (obj.length == 0) {
		layer.msg('请最少选择一条记录！', {
			icon : 1,
			time : 1000
		});
		return;
	}
	var values = [];
	for (var i = 0; i < obj.length; i++) {
		// console.log(obj[i]);
		values.push(obj[i].value);
	}
	var ids = values.join(",");
	$.ajax({
		"type" : 'get',
		"url" : Global.contextPath + '/oauthuser/deleteOAuthUser.do',
		"dataType" : "json",
		"data" : {
			"ids" : ids
		},
		"success" : function(data) {
			// console.log(data);
			if (data.code == '001') {
				table.fnDraw(false);// 刷新表格数据false为当前页数，true为刷新到第一页
				layer.msg('已删除!', {
					icon : 1,
					time : 1000
				});
			} else {
				layer.msg('操作失败!', {
					icon : 1,
					time : 1000
				});
			}
		}
	});
}
function initDataTable() {
	table = $('.table-sort').dataTable({
		"bPaginate" : true,// 分页按钮
		"bFilter" : true,// 搜索栏
		"bLengthChange" : true,// 每行显示记录数
		"iDisplayLength" : 10,// 每页显示行数
		"bProcessing" : true,
		"bSort" : false,// 排序
		"sPaginationType" : "full_numbers", // 分页，一共两种样式
		// 另一种为two_button //
		// 是datatables默认
		"bServerSide" : true,// 服务端传分页table参数
		"aoColumns" : [// 表格数据填充
				{
					"mDataProp" : "id",
					"sClass" : "text-c",
					"mRender" : function(data, type, full) {
						var html = '<input type="checkbox" value="'
								+ data
								+ '" title="'
								+ data
								+ '" id="checkbox_'
								+ data
								+ '" name="checkbox_'
								+ data
								+ '" />';
						return html;
					}

				}, {
					"mDataProp" : "username",
					"sClass" : "text-c",
				}, {
					"mDataProp" : "sex",
					"sClass" : "text-c",
					"mRender" : function(data, type, full) {
						if (data == "001") {
							return '男';
						} else if(data == "002") {
							return '女';
						} else {
							return '未知';
						}
					}
				}, {
					"mDataProp" : "phone",
					"sClass" : "text-c",
				}, {
					"mDataProp" : "remark",
					"sClass" : "text-c",
				}, {
					"mDataProp" : "status",
					"sClass" : "text-c",
					"mRender" : function(data, type, full) {
						if (data == "001") {
							return '<span class="label label-success radius">已启用</span>';
						} else {
							return '<span class="label label-fail radius">未启用</span>';
						}
					}
				}

		],
		"columnDefs" : [// 后加载填充自定义列内容
		{
			"targets" : [ 6 ],
			"data" : "id",
			"sClass" : "text-c",
			"mRender" : function(data, type, full) {
				var html = '';
				// 编辑用户-按钮
				html += '<a style="text-decoration: none" onClick="member_edit(\''
						+ data
						+ '\')" href="javascript:;" title="编辑">';
				html += '<i class="Hui-iconfont">&#xe6df;</i></a>';
				// 修改密码-按钮
				html += '<a style="text-decoration:none" class="ml-5" onClick="change_password(\''
						+ data 
						+ '\')" href="javascript:;" title="修改密码"><i class="Hui-iconfont">&#xe63f;</i></a>';
				return html;
			}
		}

		],
		/*sAjaxSource在这里没用到*/
		"sAjaxSource" : Global.contextPath
				+ '/DataTableController/getData.do',
		"fnServerData" : function(sSource, aoData, fnCallback) {
			$.dataTableService("com.kongbig.DataTableDao.getUserListForPage", aoData, dataTableParam, fnCallback);
		}
	});
}

jQuery.dataTableService = function(statement, aoData, param, fnCallback) {
	var params = {};
	if (param != null) {
		params = $.extend(params, param);
	}
	$.ajax({
		"type" : 'post',
		"url" : Global.contextPath
				+ '/DataTableController/getListForPage.do',
		"dataType" : "json",
		"data" : {
			aoData : JSON.stringify(aoData),
			param : JSON.stringify(params),
			statement : statement
		},
		"success" : function(resp) {
			fnCallback(resp);
		}
	});
}