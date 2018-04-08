$(function() {
	//js异步上传
 	$("#fileuploadBTN").click(function(){
				 $.ajaxFileUpload({
			            url: 'loginUpfile.do', 
			            type: 'post',
			            secureuri: false, //一般设置为false
			            fileElementId: 'fileupload', // 上传文件的id、name属性名
			            dataType: 'text', //返回值类型，一般设置为json、application/json
// 			            elementIds: elementIds, //传递参数到服务器
			            success: function(data, status){
			            	var date = eval("("+data+")");
			                alert(date.msg);
			            },
			            error: function(data, status, e){ 
			                alert(e);
			            }
			        });
			})
			
			//flash  异步文件上传
			$('#upfile').uploadify({
				'swf'      : 'resources/uploadify/uploadify.swf',
				'uploader' : 'loginUpfile.do',
				'buttonText': '上传图片',
				'queueID':'upfilediv',
				'fileObjName':'upfile1',
                //显示的高度和宽度，默认 height 30；width 120
                //'height': 15,
                //'width': 80,
                //上传文件的类型  默认为所有文件    'All Files'  ;  '*.*'
                //在浏览窗口底部的文件类型下拉菜单中显示的文本
                'fileTypeDesc': 'jpg doc pdf',
                //允许上传的文件后缀
                'fileTypeExts': '*.gif; *.jpg; *.png; ',
                //发送给后台的其他参数通过formData指定
                //'formData': { 'someKey': 'someValue', 'someOtherKey': 1 },
                //上传文件页面中，你想要用来作为文件队列的元素的id, 默认为false  自动生成,  不带#
                //'queueID': 'fileQueue',
                //选择文件后自动上传
                'auto': true,
                //设置为true将允许多文件上传
                'multi': true,
                'method':'post',
                'onUploadSuccess':function(file, data, response){
                	var date = eval("("+data+")");
//                     alert(date.msg);
                },

			}); 
	//设置登录窗口
    $('#w').window({
//     title: '睿思源后台管理系统',
        width: 350,
        modal: true,
        shadow: true,
        closed: true,
        height: 220,
        resizable:false
    });
	// 打开登录窗口
	$('#w').window('open');

	$("#btnLogin").click(function() {
		if(!$("#loginFM").form("validate")) {
			alert(1);
			return;
		}
		$.post("loginOn.do",{
			account:$("#account").val(),
			password: $("#password").val(),
			token:$("#token").val()
		},function(result){
			if(result.code==1){
				window.location=result.href;
				$('#w').window('close');
			}
			if(result.code==0){
				$("#tokenimg").attr("src","loginToken.do");
				$.messager.show({
					title:"错误提示",
					msg:result.msg
				})
			}
		});
// 				window.location = 'loginOn.do?account=' + $("#account").val() + "&password=" + $("#password").val();
	});
	//重新加载验证码
	$("#tokenimg").click(function(){
		$("#tokenimg").attr("src","loginToken.do")
	})
	
});
//easy_ui 表单验证
//$(function(){
//	$('#account').validatebox({    
//	    required: true,    
//	    validType: 'length[5,10]'  ,
//	    missingMessage:"用户名不能为空",
//	    invalidMessage:"格式错误"
//	});
//	$('#password').validatebox({    
//	    required: true,    
//	    validType:'length[3,10]' ,
//	    missingMessage:"密码不能为空",
//	    invalidMessage:"格式错误"
//	    
//	});
////	$('#token').validatebox({    
////	    required: true, 
////	    length:4,
////	    missingMessage:"验证码不能为空",
////	    validType:'length[4,4]' ,
////	    invalidMessage:"格式错误"
////	});
//	
//})