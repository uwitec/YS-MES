(function(factory) {
	if (typeof define === "function" && define.amd) {
		define([ "jquery", "../jquery.validate" ], factory);
	} else {
		factory(jQuery);
	}
}(function($) {

	/*
	 * Translated default messages for the jQuery validation plugin. Locale: ZH
	 * (Chinese, 中文 (Zhōngwén), 汉语, 漢語)
	 */
	$.extend($.validator.messages, {
		required : "必须",
		remote : "请修正此栏位",
		email : "电子邮件",
		url : "网址",
		date : "日期",
		dateISO : "请输入有效的日期 (YYYY-MM-DD)",
		number : "数字",
		digits : "数字",
		creditcard : "请输入有效的信用卡号码",
		equalTo : "你的输入不相同",
		extension : "请输入有效的后缀",
		maxlength : $.validator.format("最多 {0}个字符"),
		minlength : $.validator.format("最少 {0}个字符"),
		rangelength : $.validator.format("请输入长度为 {0} 至 {1} 之間的字串"),
		range : $.validator.format("请输入 {0} 至 {1} 之间的数值"),
		max : $.validator.format("请输入不大于 {0} 的数值"),
		min : $.validator.format("请输入不小于 {0} 的数值")
	});

}));