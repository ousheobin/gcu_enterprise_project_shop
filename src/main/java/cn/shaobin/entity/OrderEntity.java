package cn.shaobin.entity;

import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias(value="OrderEntity")
public class OrderEntity implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String orderCode;
	private java.util.Date createDate;
	private java.util.Date sendDate;
	private String status;
	private double amount;
	private int userId;
	
	private List<OrderItemEntity> items;//封装订单明细数据

	public List<OrderItemEntity> getItems() {
		return items;
	}
	public void setItems(List<OrderItemEntity> items) {
		this.items = items;
	}
	/** setter and getter method */
	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return this.id;
	}
	public void setOrderCode(String orderCode){
		this.orderCode = orderCode;
	}
	public String getOrderCode(){
		return this.orderCode;
	}
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	public java.util.Date getCreateDate(){
		return this.createDate;
	}
	public void setSendDate(java.util.Date sendDate){
		this.sendDate = sendDate;
	}
	public java.util.Date getSendDate(){
		return this.sendDate;
	}
	public void setStatus(String status){
		this.status = status;
	}
	public String getStatus(){
		return this.status;
	}
	public void setAmount(double amount){
		this.amount = amount;
	}
	public double getAmount(){
		return this.amount;
	}
	public void setUserId(int userId){
		this.userId = userId;
	}
	public int getUserId(){
		return this.userId;
	}
	
	@Override
	public String toString() {
		return "Order [id=" + id + ", orderCode=" + orderCode + ", createDate=" + createDate + ", sendDate=" + sendDate
				+ ", status=" + status + ", amount=" + amount + ", userId=" + userId + ", items=" + items + "]";
	}
	
	

}