package com.huicheng.pojo;

/**
 * 回款信息
 * @author Administrator
 * 2017年10月28日
 */
public class PaymentInfo {

	//回款编号
	private String paymentNo;
	
	//订单编号
	private String orderNo;
	
	//预收回款日期
	private String advancePaymentDate;
	
	//预收回款
	private Double advancePayment;
	
	//预收回款比例
	private Double advancePaymentRate;
		
	//应收回款日期
	private String arrivalPaymentDate;
	
	//应收回款
	private Double arrivalPayment;
	
	//应收回款比例
	private Double arrivalPaymentRate;
	
	//质保金回款日期
	private String warGoldPaymentDate;
	
	//质保金回款
	private Double warGoldPayment;
	
	//质保金回款比例
	private Double warGoldPaymentRate;
	
	
	/*//余额校对
	private Double balanceProofread;
	
	//准确控制（校对结果）
	private Double proofResult;
	
	//特价申请
	private String specialApplication;
	
	//是否有付款方式
	private String whetherPay;*/

	public String getPaymentNo() {
		return paymentNo;
	}

	public void setPaymentNo(String paymentNo) {
		this.paymentNo = paymentNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getAdvancePaymentDate() {
		return advancePaymentDate;
	}

	public void setAdvancePaymentDate(String advancePaymentDate) {
		this.advancePaymentDate = advancePaymentDate;
	}

	public Double getAdvancePayment() {
		return advancePayment;
	}

	public void setAdvancePayment(Double advancePayment) {
		this.advancePayment = advancePayment;
	}

	public Double getAdvancePaymentRate() {
		return advancePaymentRate;
	}

	public void setAdvancePaymentRate(Double advancePaymentRate) {
		this.advancePaymentRate = advancePaymentRate;
	}

	public String getArrivalPaymentDate() {
		return arrivalPaymentDate;
	}

	public void setArrivalPaymentDate(String arrivalPaymentDate) {
		this.arrivalPaymentDate = arrivalPaymentDate;
	}

	public Double getArrivalPayment() {
		return arrivalPayment;
	}

	public void setArrivalPayment(Double arrivalPayment) {
		this.arrivalPayment = arrivalPayment;
	}

	public Double getArrivalPaymentRate() {
		return arrivalPaymentRate;
	}

	public void setArrivalPaymentRate(Double arrivalPaymentRate) {
		this.arrivalPaymentRate = arrivalPaymentRate;
	}

	public String getWarGoldPaymentDate() {
		return warGoldPaymentDate;
	}

	public void setWarGoldPaymentDate(String warGoldPaymentDate) {
		this.warGoldPaymentDate = warGoldPaymentDate;
	}

	public Double getWarGoldPayment() {
		return warGoldPayment;
	}

	public void setWarGoldPayment(Double warGoldPayment) {
		this.warGoldPayment = warGoldPayment;
	}

	public Double getWarGoldPaymentRate() {
		return warGoldPaymentRate;
	}

	public void setWarGoldPaymentRate(Double warGoldPaymentRate) {
		this.warGoldPaymentRate = warGoldPaymentRate;
	}
	
}
