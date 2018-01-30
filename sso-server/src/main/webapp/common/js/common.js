jQuery.fn.toJson = function(beforeExtend, afterExtend, params) {
	var me = jQuery(this);
	beforeExtend = beforeExtend || {};
	afterExtend = afterExtend || {};
	params = params || {};
	var a = {};

	// text hidden password
	me.find("input[type=text],input[type=hidden],input[type=password]").each(
			function() {
				_add(this.name || this.id, this.value);
			});
	me.find("textarea").each(function() {
		_add(this.name || this.id, this.value);
	});

	// radio
	me.find("input[type=radio]").filter(":checked").each(function() {
		_add(this.name || this.id, this.value);
	});

	// checkbox
	var temp_cb = "";
	me.find("input[type=checkbox]").filter(":checked").each(function() {
		if (temp_cb.indexOf(this.name) == -1) {
			temp_cb += (this.name) + ",";
		}
	});
	jQuery(temp_cb.split(",")).each(function() {
		var tempValue = [];
		jQuery("input[name='" + this + "']:checked").each(function(i) {
			tempValue.push(this.value);
		});
		_add(this, tempValue.join(","));
	});

	// select
	me.find('select').each(function() {
		var multi = $(this).attr('multiple');
		var val = [];
		jQuery(this).find('option:selected').each(function() {
			if (this.value)
				val.push(this.value);
		});

		if (val.length == 0) {
			val.push(this.value || "");
		}

		if (multi && params.mulSelectSplit) {
			_add(this.name || this.id, "'" + val.join("','") + "'");
		} else {
			_add(this.name || this.id, val.join(','));
		}
	});

	return $.extend(beforeExtend, a, afterExtend);

	function _add(key, value) {
		if (key == "__ValidatorRules")
			return;

		if (!key || !jQuery.trim(key))
			return;

		value = value || '';
		a[key] = value;
	}
}
// datable数据请求服务
jQuery.dataTableService = function(service, aoData, param, fnCallback) {
	var params = {};
	if (param != null) {
		params = $.extend(params, param);
	}
	$.ajax({
		"type" : 'post',
		"url" : Global.contextPath
				+ '/DataTableController/getTableListForPage.do',
		"dataType" : "json",
		"data" : {
			aoData : JSON.stringify(aoData),
			param : JSON.stringify(params),
			service : service
		},
		"success" : function(resp) {
			fnCallback(resp);
		}
	});
}

// 判断字段是否为空
jQuery.isEmpty = function(value) {
	if (value == null || value == '' || value == undefined) {
		return true;
	} else {
		return false;
	}
}