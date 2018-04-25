<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + 
					  request.getServerPort() + path + "/";
%>

<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="resources/js/themes/default/easyui.css" />
<link href="resources/dist/remodal-default-theme.css" type="text/css" rel="stylesheet">
<link href="resources/css/index.css" type="text/css" rel="stylesheet">
<link href="<%=basePath%>/resources/css/order_list.css" type="text/css" rel="stylesheet">
<!-- <link rel="shortcut icon" href="#" /> -->
<!-- <link href="resources/bootstrap-4.0.0-alpha.6-dist/css/bootstrap.min.css" type="text/css" rel="stylesheet"> -->
<script type="text/javascript" src="resources/js/jquery-3.0.0.min.js"></script>
<script type="text/javascript" src="resources/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="resources/js/jquery.datagrid.js"></script>
<script type="text/javascript" src="resources/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path%>/resources/My97DatePicker/WdatePicker.js"></script>
<!-- ajaxfileupload 引入 -->
<script type="text/javascript" src="resources/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="order/order_list.js"></script>

</head>

<body>
	<div id="right" style="height:100%;width:100%;background-color:#F8F4EB;float:right;">
		<div style="margin:20px;height:92%;background:#F4F4F4;">
			<div class="search">
				<div class="item">
					<label for="orderNo">订单编号:</label>
					<input id="orderNo" name="orderNo" type="text">
				</div>
				<div class="item">
					<label for="startDate">订单日期:</label>
					<input id="startDate" type="text" class="inputdate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')}'})">
					<label for="endDate">-</label>
					<input id="endDate" type="text" class="inputdate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}'})">
				</div>
				<div class="item">
					<label for="documentName">单据名称:</label>
					<input id="documentName" name="documentName" type="text">
				</div>
				<div class="item">
					<label for="customerFullName">客户全称:</label>
					<input id="customerFullName" name="customerFullName" type="text">
				</div>
				<div class="item">
					<label for="projectName">工程名称:</label>
					<input id="projectName" name="projectName" type="text">
				</div>
				<div class="item">
					<label for="billCompanyName">开票单位:</label>
					<input id="billCompanyName" name="billCompanyName" type="text">
				</div>
				<div class="item">
					<label for="specialPriceApplication">特价申请:</label>
					<input id="specialPriceApplication" name="specialPriceApplication" type="text">
				</div>
				<div class="item">
					<label for="productCategory">品号类别名称:</label>
					<input id="productCategory" name="productCategory" type="text">
				</div>
				<div class="item">
					<label for="inquiryNo">询价单号:</label>
					<input id="inquiryNo" name="inquiryNo" type="text">
				</div>
				<div class="item">
					<label for="contractNo">合同编号:</label>
					<input id="contractNo" name="contractNo" type="text">
				</div>
				<div class="divBtn">
					<span id="addFilter">添加过滤</span>
					<button type="button" id="searchBtn" class="setBtn">查询</button>
					<button type="button" id="resetBtn" class="setBtn">重置</button>
				</div>
			</div>
			
			<div style="margin-top:20px;">
				<button id="startBilling" class="bestBtn" style="margin-right:20px;float:right;">开票</button>
				<button id="startGoods" class="bestBtn" style="margin-right:20px;float:right;">发货</button>
				<button id="paymentMethodBtn" class="bestBtn" style="margin-right:20px;float:right;">付款方式</button>
				<button id="importExcel" class="bestBtn" style="margin-right:20px;float:right;">导入</button>
				<!-- <div class="payOption" style="float: right;width: 180px;display: inline-block;font-size: 14;font-family: "Microsoft YaHei";">付款方式：
				<select id="changePayMethod" size="1" class="sel">
           <option value="1"></option>
           <option value="2">票到付款</option>
           <option value="3">货到付款</option>
       </select></div> -->
			</div>
			<div style="height:70%;margin:10px 20px 20px;background:white;border-radius:10px;">
				<table id="orderTable"></table>
			</div>
		</div>
	</div>
	
	<!-- 发货弹框 -->
	<div class="remodal fade" id="goodsModal" role="dialog">
	    <div class="modalContent">
            <div class="modalHeader">
                <label>发货信息</label>
			    <button data-remodal-action="close" class="remodal-close cancelGoodsBtn" aria-label="Close"></button>
            </div>
            <div class="modalBody">
            	<form id="addGoodsForm" method="post" style="margin-left:8px;">
					<!-- <div class="modal-item">
						<label style="letter-spacing:0.35em;">订单号：</label><span id="orderSpan"  style="width:130px;" class="hiddenLongInfo"></span>
					</div> -->
					<div class="modal-item">
						<label>发货数量：</label>
						<input id="deliveryNum" name="deliveryNum" type="text" onkeyup="this.value=this.value.replace(/\D/g,'')"
   						 onafterpaste="this.value=this.value.replace(/\D/g,'')">
					</div>
					<div class="modal-item">
						<label>发货日期：</label>
						<input id="deliveryDate" type="text" onFocus="WdatePicker({lang:'zh-cn'})" >
					</div>
					<!-- onFocus="WdatePicker({minDate:new Date()})"
					onFocus="WdatePicker({minDate:'#F{$dp.$D(\'deliveryDate\')}'})" -->
					<div class="modal-item">
						<label>到货日期：</label>
						<input id="arrivalDate" type="text" onFocus="WdatePicker({lang:'zh-cn'})">
					</div>
				</form>
            </div>
            <div class="commonBtn">
				<button data-remodal-action="confirm" class="remodal-confirm" id="saveDeliveryBtn">确定</button>
				<button data-remodal-action="cancel" class="remodal-cancel cancelGoodsBtn">取消</button>
            </div>
	    </div>
	</div>
	
	<!-- 支付方式弹框 -->
	<div class="remodal fade" id="payMethodModal" role="dialog">
	    <div class="modalContent">
            <div class="modalHeader">
                <label>付款方式</label>
			    <button data-remodal-action="close" class="remodal-close cancelPayMethodBtn" aria-label="Close"></button>
            </div>
            <div class="modalBody">
            <label><input id="billingInput" name="moneyAction" type="radio" value="1" checked>款到发货 </label> 
	            	<label><input id="goodsInput" name="moneyAction" type="radio" value="2">票到付款 </label> 
				<form id="sdffsf" method="post" style="margin-left:8px;">
	            <div id="goodsMethod" class="content" style="margin-left:8px;">
		            <div class="modal-item">
		            	<label class="payInput">预付率(%)：</label>
		            	<input id="prepaymentRate" name="prepaymentRate" type="text">
		            </div>
		            <div class="modal-item">
		            	<label class="payInput">预付时间：</label>
		            	<input id="prepaidTime" name="prepaidTime" type="text" onFocus="WdatePicker({lang:'zh-cn'})" >
		            </div>
		            <div class="modal-item">
		            	<label class="payInput">应到天数：</label>
		            	<input id="payMentDay" type="text">
		            </div>
		            <div class="modal-item">
		            	<label class="payInput">应收付款率(%)：</label>
	            		<input id="arrivalAfterRate" name="arrivalAfterRate" type="text">
		            </div>
		            <div class="modal-item">
		            	<label class="payInput">质保率(%)：</label>
		            	<input id="warrantyRate" name="warrantyRate" type="text">
		            </div>
		            <div class="modal-item">
		            	<label class="payInput">质保期限(年)：</label>
		            	<input id="warrantyPeriod" name="warrantyPeriod" type="text">
		            </div>
	            </div>
	            </form>
            </div>
            <div class="commonBtn">
				<button data-remodal-action="confirm" class="remodal-confirm" id="savePayMethodBtn">确定</button>
				<button data-remodal-action="cancel" class="remodal-cancel cancelPayMethodBtn">取消</button>
            </div>
	    </div>
	</div>
 	<!-- <div class="modal fade" style="display:none;" id="payMethodModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content" style="border-radius:6px;height:300px;overflow-x: hidden; overflow-y: hidden;">
	            <div class="modal-header">
	            	<span class="modal-title" id="myModalLabel" style="font-weight:bold;margin-left:5px;">付款方式</span>
	                <span type="button" id="closePayModal" style="float:right;margin-right:10px;">X</span>
	            </div>
	            
	            <div class="modal-body">
	            	<label><input id="billingInput" name="moneyAction" type="radio" value="1" checked>款到发货 </label> 
	            	<label><input id="goodsInput" name="moneyAction" type="radio" value="2">货到付款 </label> 
	            </div>
	            
	            <ul class="payUl_tab" style="margin-left: -30px;">
	            	<li  class="active">票到付款</li>
	            	<li>货到付款</li>
	            </ul>
	            <div id="billMethod" class="content show" style="margin-left:8px;">
		            <div class="modal-item">
		            	<label class="payInput"><span>预付</span><input id="prepaymentRate" name="prepaymentRate" type="text"><span>%</span></label>
			            <label class="payInput"><span>预付时间</span><input id="prepaidTime" name="prepaidTime" type="text" onFocus="WdatePicker({lang:'zh-cn'})" ></label>
		            </div>
		            <div class="modal-item">
		            	<label class="payInput"><span>票到</span><input id="payMentDay" name="payMentDay" type="text"><span>天，付</span>
		            		<input id="arrivalAfterRate" name="arrivalAfterRate" type="text"><span>%</span>
		            	</label>
		            </div>
		            <div class="modal-item">
		            	<label class="payInput"><span>质保金</span><input id="warrantyRate" name="warrantyRate" type="text"><span>%</span></label>
		            	<label class="payInput"><span>质保期限</span><input id="warrantyPeriod" name="warrantyPeriod" type="text"><span>年</span></label>
		            </div>
	            </div>
	            <form id="sdffsf" method="post" style="margin-left:8px;">
	            <div id="goodsMethod" class="content" style="margin-left:8px;">
		            <div class="modal-item">
		            	<label class="payInput"><span>预付率：</span><input id="prepaymentRate" name="prepaymentRate" type="text"><span>%</span></label>
			            <label class="payInput"><span>预付时间</span><input id="prepaidTime" name="prepaidTime" type="text" onFocus="WdatePicker({lang:'zh-cn'})" ></label>
		            </div>
		            <div class="modal-item">
		            	<label class="payInput"><span>货到天数：</span><input id="payMentDay" name="payMentDay" type="text">
		            		<span>付到付款率：</span>
		            		<input id="arrivalAfterRate" name="arrivalAfterRate" type="text"><span>%</span>
		            	</label>
		            </div>
		            <div class="modal-item">
		            	<label class="payInput"><span>质保率：</span><input id="warrantyRate" name="warrantyRate" type="text"><span>%</span></label>
		            	<label class="payInput"><span>质保期限(年)</span><input id="warrantyPeriod" name="warrantyPeriod" type="text"></label>
		            </div>
	            </div>
	            </form>
	            <div class="modal-footer" style="text-align:center;margin-top:200px;">
	            	<button type="button" id="savePayMethodBtn" style="margin-right:10px;">保存</button>
					<button type="button" id="cancelPayMethodBtn">取消</button>
	            </div>
	        </div>
	    </div>
	</div> -->
	
	
		<!-- 开票弹框 -->
	<div class="remodal fade" id="billingModal" role="dialog">
	    <div class="modalContent">
            <div class="modalHeader">
	            <label>开票信息</label>
			    <button data-remodal-action="close" class="remodal-close closeBillingModal" aria-label="Close"></button>
            </div>
            <div class="modalBody">
            	<form id="addGoodsForm" method="post" style="margin-left:8px;">
					<!-- <div class="modal-item">
						<label style="letter-spacing:0.35em;">订单号：</label><span id="orderSpan"  style="width:130px;" class="hiddenLongInfo"></span>
					</div> -->
					<div class="modal-item">
						<label class="two_word">数量</label><label class="label_size">：</label>
						<input id="billingNum" onchange="getBillMoneyValue()" type="text" onkeyup="this.value=this.value.replace(/\D/g,'')"
   						 onafterpaste="this.value=this.value.replace(/\D/g,'')">
					</div>
					<div class="modal-item">
						<label>开票日期：</label>
						<input id="billDate" type="text" onFocus="WdatePicker({lang:'zh-cn'})" >
					</div>
					<!-- onFocus="WdatePicker({minDate:new Date()})"
					onFocus="WdatePicker({minDate:'#F{$dp.$D(\'deliveryDate\')}'})" -->
					<div class="modal-item">
						<label class="two_word">金额</label><label class="label_size">：</label>
						<input id="billMoney" type="text" readonly>
					</div>
					<div class="modal-item">
						<label>开票单位：</label>
						<input id="modalbillCompanyName" type="text">
					</div>
					<div class="modal-item">
						<label>开票类型：</label>
						<input id="invoiceType" type="text">
					</div>
					<div class="modal-item">
						<label>发票号码：</label>
						<input id="invoiceNum" type="text">
					</div>
					<div class="modal-item">
						<label>运送方式：</label>
						<input id="deliveryMethod" type="text">
					</div>
					<div class="modal-item">
						<label>邮寄地址：</label>
						<input id="billingToAddress" type="text">
					</div>
					<div class="modal-item">
						<label class="three_word">联系人</label><label class="label_size_three">：</label>
						<input id="contacts" type="text">
					</div>
					<div class="modal-item">
						<label class="two_word">电话</label><label class="label_size">：</label>
						<input id="telephone" type="text">
					</div>
					<div class="modal-item">
						<label class="three_word">快递号</label><label class="label_size_three">：</label>
						<input id="expressNo" type="text">
					</div>
					<div class="modal-item">
						<label>邮寄时间：</label>
						<input id="mailDate" type="text" onFocus="WdatePicker({lang:'zh-cn'})">
					</div>
				</form>
            </div>
            <div class="commonBtn">
	            <button data-remodal-action="confirm" class="remodal-confirm" id="saveBillingInfoBtn">保存</button>
				<button data-remodal-action="cancel" class="remodal-cancel closeBillingModal">取消</button>
            </div>
	    </div>
	</div>
	
	<div class="remodal fade" id="filterInfoModal" role="dialog">
		<div class="modalContent">
			<div class="modalHeader">
				<label>请添加过滤条件</label>
			    <button data-remodal-action="close" class="remodal-close closeFilterModal" aria-label="Close"></button>
			</div>
			<div class="modalBody addFilterBox">
				<label><input name="chkItem" type="checkbox" value="salesman" />业务员</label>
		        <label><input name="chkItem" type="checkbox" value="region" />地区</label>
		        <label><input name="chkItem" type="checkbox" value="customerNum" />客户单号</label>
		        <label><input name="chkItem" type="checkbox" value="customerNo" />客户编号</label>
		        <label><input name="chkItem" type="checkbox" value="orderAttribute" />订单属性</label>
		        <label><input name="chkItem" type="checkbox" value="productName" />品名</label>
		        <label><input name="chkItem" type="checkbox" value="model" />规格</label>
		        <label><input name="chkItem" type="checkbox" value="amount" />订单数量</label>
		        <label><input name="chkItem" type="checkbox" value="unit" />单价</label>
		        <label><input name="chkItem" type="checkbox" value="totalPrice" />金额</label>
		        <label><input name="chkItem" type="checkbox" value="deliveryAddress1" />送货地址(一)</label>
		        <label><input name="chkItem" type="checkbox" value="deliveryAddress2" />送货地址(二)</label>
		        <label><input name="chkItem" type="checkbox" value="originalOrder" />原始订单</label>
		        <label><input name="chkItem" type="checkbox" value="referProgramNo" />参考方案号</label>
		        <label><input name="chkItem" type="checkbox" value="referOrderNo" />参考订单号</label>
			</div>
			<br>
			<div class="commonBtn">
				<button data-remodal-action="confirm" class="remodal-confirm" id="confirmFilter">确定</button>
				<button data-remodal-action="cancel" class="remodal-cancel closeFilterModal">取消</button>
			</div>
		</div>
	</div>
	
	<!-- 遮罩层 -->
	<div class="dialog_content"></div>
	
	<div id="importBoard" style="width:600px;height:400px;display:none;">
		<%-- <form enctype="multipart/form-data" id="file_form"  action="<%=request.getContextPath()%>/order/upload.do" method="post" class="form-horizontal">    
    		<button class="btn btn-success btn-xs" id="uploadEventBtn" style="height:26px;"  type="button" >选择文件</button>  
    		<input type="file" name="file"  style="width:0px;height:0px;" id="uploadEventFile">  
    		<input id="uploadEventPath"  disabled="disabled"  type="text" placeholder="请选择excel表" style="border: 1px solid #e6e6e6; height: 26px;width: 200px;" >                                           
		</form>  
		<!-- <button type="button" class="btn btn-success btn-sm"  onclick="uploadBtn()" >上传</button> -->  
		<input type="submit" value="文件上传" id='upFile-btn'> --%>
		<input id="fileupload" type="file" name ="upfile" />
        <a href="javascript:void(0)" id="fileuploadBTN">上传</a>
	</div>
	
</body>
</html>