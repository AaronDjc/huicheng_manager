package com.huicheng.pojo;

/**
 * 订单明细
 * @author Administrator
 * 2017-10-10
 */
public class OrderInfo {

	//订单编号(id)
	private String orderNo;
	
	//数量
	private Integer amount;
	
	//单位
	private String unit;
	
	//单价
	private Double unitPrice;
	
	//总金额
	private Double totalPrice;
	
	//单据名称
	private String documentName;
	
	//下单时间
	private String orderDate;
	
	//下单月份
	private Integer orderMonth;

	//客户单号
	private String customerNum;
	
	//客户编号
	private String customerNo;
		
	//地区
	private String region;
	
	//业务员
	private String salesman;
	
	//订单属性
	private String orderAttribute;

	//客户全称
	private String customerFullName;
	
	//工程名称
	private String projectName;
	
	//品号
	private String productNo;
	
	//剩余数量
	private Integer remainAmount;
	
	//是否能发货。0：不可以，1：可以
	private Integer isDelivery;
	
	//剩余开票数量
	private Integer remainInvoicesNum;

	//产品类别名称
	private String productCategory;
	
	//产品名称
	private String productName;
	
	//产品规格
	private String model;

	//送货地址1
	private String deliveryAddress1;
	
	//送货地址2
	private String deliveryAddress2;
	
	//特殊说明
	private String specialRemark;
	
	//插头数量
	private String plugNum;

	//原始订单
	private String originalOrder;
	
	//询价单号
	private String inquiryNo;
	
	//参考方案号
	private String referProgramNo;
	
	//参考订单号
	private String referOrderNo;
	
	//预交货日期
	private String predeliveryDate;
	
	//交期回复
	private String deliveryReply;
	
	//合同编号
	private String contractNo;
	
	//备注
	private String remark;

	//合同性质
	private String contractNature;
	
	//特价申请
	private String specialPriceApplication;
	
	//付款方式
	private String payMethod;
	
	//付款可修改標記
	private String payFlag;
	
	//预付日期
	private String prepaidDate;
	
	//预付金额
	private Double prepaidMoney;
	
	//预收回款
	private Double advanceTotalPayment;
	
	//截至时间
	private String lastTime;
	
	//应收金额
	private Double arrivalMoney;
	
	//应收回款
	private Double arrivalTotalPayment;
	
	//质保金额
	private Double warranty;
	
	//质保金回款
	private Double warGoldTotalPayment;
	
	//质保期限
	private String warrantyPeriod;
	
	//发货总金额
	private Double deliveryTotalPrice;
	
	//开票总金额
	private Double billingTotalPrice;
	
	//回款总金额
	private Double paymentTotalPrice;
	
	//工号
	private String jobNumber;
	
	//总余额
	private Double totalBalance;
	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getContractNature() {
		return contractNature;
	}

	public void setContractNature(String contractNature) {
		this.contractNature = contractNature;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getCustomerNum() {
		return customerNum;
	}

	public void setCustomerNum(String customerNum) {
		this.customerNum = customerNum;
	}

	public String getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getSalesman() {
		return salesman;
	}

	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}

	public String getCustomerFullName() {
		return customerFullName;
	}

	public void setCustomerFullName(String customerFullName) {
		this.customerFullName = customerFullName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getDeliveryAddress1() {
		return deliveryAddress1;
	}

	public void setDeliveryAddress1(String deliveryAddress1) {
		this.deliveryAddress1 = deliveryAddress1;
	}

	public String getDeliveryAddress2() {
		return deliveryAddress2;
	}

	public void setDeliveryAddress2(String deliveryAddress2) {
		this.deliveryAddress2 = deliveryAddress2;
	}

	public String getPlugNum() {
		return plugNum;
	}

	public void setPlugNum(String plugNum) {
		this.plugNum = plugNum;
	}

	public String getOriginalOrder() {
		return originalOrder;
	}

	public void setOriginalOrder(String originalOrder) {
		this.originalOrder = originalOrder;
	}

	public String getInquiryNo() {
		return inquiryNo;
	}

	public void setInquiryNo(String inquiryNo) {
		this.inquiryNo = inquiryNo;
	}

	public String getReferProgramNo() {
		return referProgramNo;
	}

	public void setReferProgramNo(String referProgramNo) {
		this.referProgramNo = referProgramNo;
	}

	public String getReferOrderNo() {
		return referOrderNo;
	}

	public void setReferOrderNo(String referOrderNo) {
		this.referOrderNo = referOrderNo;
	}

	public String getOrderAttribute() {
		return orderAttribute;
	}

	public void setOrderAttribute(String orderAttribute) {
		this.orderAttribute = orderAttribute;
	}

	public Integer getIsDelivery() {
		return isDelivery;
	}

	public void setIsDelivery(Integer isDelivery) {
		this.isDelivery = isDelivery;
	}

	public Integer getRemainAmount() {
		return remainAmount;
	}

	public void setRemainAmount(Integer remainAmount) {
		this.remainAmount = remainAmount;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public Integer getOrderMonth() {
		return orderMonth;
	}

	public void setOrderMonth(Integer orderMonth) {
		this.orderMonth = orderMonth;
	}
	
	public String getSpecialRemark() {
		return specialRemark;
	}

	public void setSpecialRemark(String specialRemark) {
		this.specialRemark = specialRemark;
	}

	public String getDeliveryReply() {
		return deliveryReply;
	}

	public void setDeliveryReply(String deliveryReply) {
		this.deliveryReply = deliveryReply;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getPredeliveryDate() {
		return predeliveryDate;
	}

	public void setPredeliveryDate(String predeliveryDate) {
		this.predeliveryDate = predeliveryDate;
	}

	public String getSpecialPriceApplication() {
		return specialPriceApplication;
	}

	public void setSpecialPriceApplication(String specialPriceApplication) {
		this.specialPriceApplication = specialPriceApplication;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public String getPayFlag() {
		return payFlag;
	}

	public void setPayFlag(String payFlag) {
		this.payFlag = payFlag;
	}

	public String getPrepaidDate() {
		return prepaidDate;
	}

	public void setPrepaidDate(String prepaidDate) {
		this.prepaidDate = prepaidDate;
	}

	public Double getPrepaidMoney() {
		return prepaidMoney;
	}

	public void setPrepaidMoney(Double prepaidMoney) {
		this.prepaidMoney = prepaidMoney;
	}

	public String getLastTime() {
		return lastTime;
	}

	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}

	public Double getArrivalMoney() {
		return arrivalMoney;
	}

	public void setArrivalMoney(Double arrivalMoney) {
		this.arrivalMoney = arrivalMoney;
	}

	public Double getWarranty() {
		return warranty;
	}

	public void setWarranty(Double warranty) {
		this.warranty = warranty;
	}

	public String getWarrantyPeriod() {
		return warrantyPeriod;
	}

	public void setWarrantyPeriod(String warrantyPeriod) {
		this.warrantyPeriod = warrantyPeriod;
	}

	public Integer getRemainInvoicesNum() {
		return remainInvoicesNum;
	}

	public void setRemainInvoicesNum(Integer remainInvoicesNum) {
		this.remainInvoicesNum = remainInvoicesNum;
	}

	public Double getDeliveryTotalPrice() {
		return deliveryTotalPrice;
	}

	public void setDeliveryTotalPrice(Double deliveryTotalPrice) {
		this.deliveryTotalPrice = deliveryTotalPrice;
	}

	public Double getBillingTotalPrice() {
		return billingTotalPrice;
	}

	public void setBillingTotalPrice(Double billingTotalPrice) {
		this.billingTotalPrice = billingTotalPrice;
	}

	public Double getPaymentTotalPrice() {
		return paymentTotalPrice;
	}

	public void setPaymentTotalPrice(Double paymentTotalPrice) {
		this.paymentTotalPrice = paymentTotalPrice;
	}

	public String getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	public Double getTotalBalance() {
		return totalBalance;
	}

	public void setTotalBalance(Double totalBalance) {
		this.totalBalance = totalBalance;
	}

	public Double getAdvanceTotalPayment() {
		return advanceTotalPayment;
	}

	public void setAdvanceTotalPayment(Double advanceTotalPayment) {
		this.advanceTotalPayment = advanceTotalPayment;
	}

	public Double getArrivalTotalPayment() {
		return arrivalTotalPayment;
	}

	public void setArrivalTotalPayment(Double arrivalTotalPayment) {
		this.arrivalTotalPayment = arrivalTotalPayment;
	}

	public Double getWarGoldTotalPayment() {
		return warGoldTotalPayment;
	}

	public void setWarGoldTotalPayment(Double warGoldTotalPayment) {
		this.warGoldTotalPayment = warGoldTotalPayment;
	}
	
}
