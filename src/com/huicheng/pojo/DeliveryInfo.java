package com.huicheng.pojo;

/**
 * 发货信息
 * @author Administrator
 * 2017年10月21日
 */
public class DeliveryInfo {

	//发货编号（id）
	private String deliveryNo;
	
	//订单号
	private String orderNo;
	
	//预交货日期
	private String predeliveryDate;
	
	//交期回复
	private String replyDate;
	
	//发货日期
	private String deliveryDate;
	
	//发货数量
	private Integer deliveryNum;
	
	//发货金额
	private Double deliveryMoney;
	
	//到货日期
	private String arrivalDate;
	
	//签收单返回日期
	private String receiptDate;
	
	//换货、样品退回
	private String returnInfo;
	
	//换货、样品退回
	private String projectName;
	
	//单价
	private Double unitPrice;
	
	//客户全称
	private String customerFullName;
	
	//合同号
	private String contractNo;

	public String getCustomerFullName() {
			return customerFullName;
		}

		public void setCustomerFullName(String customerFullName) {
			this.customerFullName = customerFullName;
		}

	public Double getUnitPrice() {
			return unitPrice;
		}

		public void setUnitPrice(Double unitPrice) {
			this.unitPrice = unitPrice;
		}

	public String getDeliveryNo() {
		return deliveryNo;
	}

	public void setDeliveryNo(String deliveryNo) {
		this.deliveryNo = deliveryNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getPredeliveryDate() {
		return predeliveryDate;
	}

	public void setPredeliveryDate(String predeliveryDate) {
		this.predeliveryDate = predeliveryDate;
	}

	public String getReplyDate() {
		return replyDate;
	}

	public void setReplyDate(String replyDate) {
		this.replyDate = replyDate;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Integer getDeliveryNum() {
		return deliveryNum;
	}

	public void setDeliveryNum(Integer deliveryNum) {
		this.deliveryNum = deliveryNum;
	}

	public String getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public String getReceiptDate() {
		return receiptDate;
	}

	public void setReceiptDate(String receiptDate) {
		this.receiptDate = receiptDate;
	}

	public String getReturnInfo() {
		return returnInfo;
	}

	public void setReturnInfo(String returnInfo) {
		this.returnInfo = returnInfo;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public Double getDeliveryMoney() {
		return deliveryMoney;
	}

	public void setDeliveryMoney(Double deliveryMoney) {
		this.deliveryMoney = deliveryMoney;
	}

	
}
