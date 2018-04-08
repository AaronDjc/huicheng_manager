/**
 * 自定义文本框验证
 */

// 定义全局变量，该值判断帐号是否可以进行注册    true:可以   false: 不可以
var accountFlag = false;
var ajaxValidateStatus = false;  // 服务器验证是否效验完成

$.extend($.fn.validatebox.defaults.rules, {
	// ajax验证, 例：   equalParamToServer['account/account_validate.do', 'account']
	equalParamToServer: {
		// value 文本框的值   param[0] ajax验证地址,    param[1] ajax验证提交到后台的参数名称
		validator: function(value, param) {
			$.ajax({
				type: "POST",
				url: param[0],
				data: param[1] + "=" + value,
				success: function(msg) {
					// 将 字符串转换为 对象
					if(msg.code < 0 ) {
						// 验证失败，将服务器返回的信息展示在表单页
						$.fn.validatebox.defaults.rules.equalParamToServer.message = msg.message;
						accountFlag = false;
					} else {
						$.fn.validatebox.defaults.rules.equalParamToServer.message = msg.message;
						accountFlag = true;
					}
					ajaxValidateStatus = true;
				}
			});
			console.info(accountFlag);
			return accountFlag;
		},
		message: ""
	},
	equalTo: {
		// 验证的方法，该规则赋予的文本域每200ms调用一次该验证方法，value：当前文本框内的值       param：传递过来的数组（例：validType: equalTo['#password']  param[0] = 'password'） 
		validator: function(value, param) {
			// 返回 false 代表验证失败
			return $(param[0]).val() == value;
		},
		// 验证失败时需要提示的信息
		message: "确认密码与密码不一致"
	},
	CHS : {
		validator : function(value, param) {
			return /^[\u0391-\uFFE5]+$/.test(value);
		},
		message : '请输入汉字'
	},
	english : {// 验证英语
		validator : function(value) {
			return /^[A-Za-z]+$/i.test(value);
		},
		message : '请输入英文'
	},
	ip : {// 验证IP地址
		validator : function(value) {
			return /\d+\.\d+\.\d+\.\d+/.test(value);
		},
		message : 'IP地址格式不正确'
	},
	ZIP : {
		validator : function(value, param) {
			return /^[0-9]\d{5}$/.test(value);
		},
		message : '邮政编码不存在'
	},
	QQ : {
		validator : function(value, param) {
			return /^[1-9]\d{4,10}$/.test(value);
		},
		message : 'QQ号码不正确'
	},
	mobile : {
		validator : function(value, param) {
			return /^(?:13\d|15\d|18\d)-?\d{5}(\d{3}|\*{3})$/
					.test(value);
		},
		message : '手机号码不正确'
	},
	tel : {
		validator : function(value, param) {
			return /^(\d{3}-|\d{4}-)?(\d{8}|\d{7})?(-\d{1,6})?$/
					.test(value);
		},
		message : '电话号码不正确'
	},
	mobileAndTel : {
		validator : function(value, param) {
			return /(^([0\+]\d{2,3})\d{3,4}\-\d{3,8}$)|(^([0\+]\d{2,3})\d{3,4}\d{3,8}$)|(^([0\+]\d{2,3}){0,1}13\d{9}$)|(^\d{3,4}\d{3,8}$)|(^\d{3,4}\-\d{3,8}$)/
					.test(value);
		},
		message : '请正确输入电话号码'
	},
	number : {
		validator : function(value, param) {
			return /^[0-9]+.?[0-9]*$/.test(value);
		},
		message : '请输入数字'
	},
	money : {
		validator : function(value, param) {
			return (/^(([1-9]\d*)|\d)(\.\d{1,2})?$/)
					.test(value);
		},
		message : '请输入正确的金额'

	},
	mone : {
		validator : function(value, param) {
			return (/^(([1-9]\d*)|\d)(\.\d{1,2})?$/)
					.test(value);
		},
		message : '请输入整数或小数'

	},
	integer : {
		validator : function(value, param) {
			return /^[+]?[1-9]\d*$/.test(value);
		},
		message : '请输入最小为1的整数'
	},
	integ : {
		validator : function(value, param) {
			return /^[+]?[0-9]\d*$/.test(value);
		},
		message : '请输入整数'
	},
	range : {
		validator : function(value, param) {
			if (/^[1-9]\d*$/.test(value)) {
				return value >= param[0] && value <= param[1]
			} else {
				return false;
			}
		},
		message : '输入的数字在{0}到{1}之间'
	},
	minLength : {
		validator : function(value, param) {
			return value.length >= param[0]
		},
		message : '至少输入{0}个字'
	},
	maxLength : {
		validator : function(value, param) {
			return value.length <= param[0]
		},
		message : '最多{0}个字'
	},
	// select即选择框的验证
	selectValid : {
		validator : function(value, param) {
			if (value == param[0]) {
				return false;
			} else {
				return true;
			}
		},
		message : '请选择'
	},
	idCode : {
		validator : function(value, param) {
			return /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/
					.test(value);
		},
		message : '请输入正确的身份证号'
	},
	loginName : {
		validator : function(value, param) {
			return /^[\u0391-\uFFE5\w]+$/.test(value);
		},
		message : '登录名称只允许汉字、英文字母、数字及下划线。'
	},
	englishOrNum : {// 只能输入英文和数字
		validator : function(value) {
			return /^[a-zA-Z0-9_ ]{1,}$/.test(value);
		},
		message : '请输入英文、数字、下划线或者空格'
	},
	xiaoshu : {
		validator : function(value) {
			return /^(([1-9]+)|([0-9]+\.[0-9]{1,2}))$/
					.test(value);
		},
		message : '最多保留两位小数！'
	},
	ddPrice : {
		validator : function(value, param) {
			if (/^[1-9]\d*$/.test(value)) {
				return value >= param[0] && value <= param[1];
			} else {
				return false;
			}
		},
		message : '请输入1到100之间正整数'
	},
	jretailUpperLimit : {
		validator : function(value, param) {
			if (/^[0-9]+([.]{1}[0-9]{1,2})?$/.test(value)) {
				return parseFloat(value) > parseFloat(param[0])
						&& parseFloat(value) <= parseFloat(param[1]);
			} else {
				return false;
			}
		},
		message : '请输入0到100之间的最多俩位小数的数字'
	},
	rateCheck : {
		validator : function(value, param) {
			if (/^[0-9]+([.]{1}[0-9]{1,2})?$/.test(value)) {
				return parseFloat(value) > parseFloat(param[0])
						&& parseFloat(value) <= parseFloat(param[1]);
			} else {
				return false;
			}
		},
		message : '请输入0到1000之间的最多俩位小数的数字'
	}
});