package cn.shaobin.entity;

import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias(value = "OrderEntity")
public class OrderEntity implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String orderCode;
	private java.util.Date createDate;
	private java.util.Date sendDate;
	private String status;
	private double amount;
	private int userId;

	private List<OrderItemEntity> items;// 封装订单明细数据

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public java.util.Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

	public java.util.Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(java.util.Date sendDate) {
		this.sendDate = sendDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public List<OrderItemEntity> getItems() {
		return items;
	}

	public void setItems(List<OrderItemEntity> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "OrderEntity [id=" + id + ", orderCode=" + orderCode + ", createDate=" + createDate + ", sendDate="
				+ sendDate + ", status=" + status + ", amount=" + amount + ", userId=" + userId + ", items=" + items
				+ "]";
	}

}