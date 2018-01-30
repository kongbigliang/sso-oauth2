/**
 * 
 */
$(document).ready(function(){
	$.ajax({
		url : "/security/CommonController/loadLeftMenu.do",
		type : "post",
		dataType : "json",
		data : {
			
		},
		success : function(data) {
			console.debug(data[0].parentId);
			/*<aside class="Hui-aside">*/
			var add = '<div class="menu_dropdown">';
			for(var i = 0; i < data.length; i++){
				if(data[i].parentId == '0'){ //type可能是关键字,这里不能用来做判断.
					var html = '';
					html += '<dl><dt><i class="Hui-iconfont">&#' + data[i].icon + '</i>'+ data[i].name + '<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>';
					html += '</dt><dd><ul>';
					for(var j = 0; j < data.length; j++){
						if (data[j].parentId != '0' && data[j].parentId == data[i].id) {
							html += '<li><a _href='+ data[j].context + data[j].url +' data-title=' + data[j].name +' href="javascript:;">' + data[j].name + '</a></li>';
						}
					}
					html += '</ul></dd></dl>';
					add += html;
				}
			}
			add += '</div>';
			/*</aside>*/
			console.debug(add);
			$('#myMenu').append(add);
			/*左侧菜单*/
			$.Huifold(".menu_dropdown dl dt",".menu_dropdown dl dd","fast",1,"click");
		},
		error : function() {
			layer.alert('加载菜单失败', {
				icon : 2,
				shade : 0.5,
				time : 3000
			});
		}
	});
	
	
	
});