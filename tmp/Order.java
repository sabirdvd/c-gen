package com.yunyi.order.dto;

public class Order implements Serializable {

	private Long orderId;//订单ID

	private Byte isParent;//1、是父单。0、不是父单

	private Long orderParentId;//父订单ID

	private String orderNo;//订单编号

	private Integer orderType;//0、正常订单。1、内部下单。2、测试订单。

	private Integer orderStatusCode;//订单状态码

	private Integer channelId;//

	private String channelName;//渠道名称

	private String channelOrderNo;//渠道侧订单编号

	private Integer supplierId;//该订单被分配给了哪个供应商

	private String supplierOrderNo;//供应商订单编号

	private Byte submitState;//0、是预占库存

	private BigDecimal orderAmount;//订单价格

	private BigDecimal orderPayAmount;//订单应付价格

	private BigDecimal orderActulPayAmount;//订单实付价格

	private Timestamp orderTime;//下单时间

	private Timestamp assignTime;//该订单分配给供应商时间

	private Timestamp deliverTime;//发货时间

	private Timestamp payTime;//订单支付时间

	private Timestamp confirmTime;//订单确认时间

	private String deliverNoList;//物流单号列表(支持一个订单分开多个物流发货)

	private String deliverNameList;//物流公司列表(支持一个订单分开多个物流发货)

	private Byte isCloseCalc;//是否已结算

	private String orderRemark;//订单备注

	private Byte isDelate;//1、删除

	private Timestamp createTime;//创建时间

	private String creator;//创建人

	private Timestamp lastUpdateTime;//

	private String lastUpdator;//

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Byte getIsParent() {
		return isParent;
	}

	public void setIsParent(Byte isParent) {
		this.isParent = isParent;
	}

	public Long getOrderParentId() {
		return orderParentId;
	}

	public void setOrderParentId(Long orderParentId) {
		this.orderParentId = orderParentId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public Integer getOrderStatusCode() {
		return orderStatusCode;
	}

	public void setOrderStatusCode(Integer orderStatusCode) {
		this.orderStatusCode = orderStatusCode;
	}

	public Integer getChannelId() {
		return channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getChannelOrderNo() {
		return channelOrderNo;
	}

	public void setChannelOrderNo(String channelOrderNo) {
		this.channelOrderNo = channelOrderNo;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierOrderNo() {
		return supplierOrderNo;
	}

	public void setSupplierOrderNo(String supplierOrderNo) {
		this.supplierOrderNo = supplierOrderNo;
	}

	public Byte getSubmitState() {
		return submitState;
	}

	public void setSubmitState(Byte submitState) {
		this.submitState = submitState;
	}

	public BigDecimal getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}

	public BigDecimal getOrderPayAmount() {
		return orderPayAmount;
	}

	public void setOrderPayAmount(BigDecimal orderPayAmount) {
		this.orderPayAmount = orderPayAmount;
	}

	public BigDecimal getOrderActulPayAmount() {
		return orderActulPayAmount;
	}

	public void setOrderActulPayAmount(BigDecimal orderActulPayAmount) {
		this.orderActulPayAmount = orderActulPayAmount;
	}

	public Timestamp getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}

	public Timestamp getAssignTime() {
		return assignTime;
	}

	public void setAssignTime(Timestamp assignTime) {
		this.assignTime = assignTime;
	}

	public Timestamp getDeliverTime() {
		return deliverTime;
	}

	public void setDeliverTime(Timestamp deliverTime) {
		this.deliverTime = deliverTime;
	}

	public Timestamp getPayTime() {
		return payTime;
	}

	public void setPayTime(Timestamp payTime) {
		this.payTime = payTime;
	}

	public Timestamp getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(Timestamp confirmTime) {
		this.confirmTime = confirmTime;
	}

	public String getDeliverNoList() {
		return deliverNoList;
	}

	public void setDeliverNoList(String deliverNoList) {
		this.deliverNoList = deliverNoList;
	}

	public String getDeliverNameList() {
		return deliverNameList;
	}

	public void setDeliverNameList(String deliverNameList) {
		this.deliverNameList = deliverNameList;
	}

	public Byte getIsCloseCalc() {
		return isCloseCalc;
	}

	public void setIsCloseCalc(Byte isCloseCalc) {
		this.isCloseCalc = isCloseCalc;
	}

	public String getOrderRemark() {
		return orderRemark;
	}

	public void setOrderRemark(String orderRemark) {
		this.orderRemark = orderRemark;
	}

	public Byte getIsDelate() {
		return isDelate;
	}

	public void setIsDelate(Byte isDelate) {
		this.isDelate = isDelate;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Timestamp getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Timestamp lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getLastUpdator() {
		return lastUpdator;
	}

	public void setLastUpdator(String lastUpdator) {
		this.lastUpdator = lastUpdator;
	}

}