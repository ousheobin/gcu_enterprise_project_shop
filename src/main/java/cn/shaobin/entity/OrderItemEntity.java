package cn.shaobin.entity;

import org.apache.ibatis.type.Alias;

@Alias(value="OrderItemEntity")
public class OrderItemEntity implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	private int orderId;
	private int articleId;
	private int orderNum;
	private ArticleEntity article;//封装物品信息

	public ArticleEntity getArticle() {
		return article;
	}
	public void setArticle(ArticleEntity article) {
		this.article = article;
	}
	/** setter and getter method */
	public void setOrderId(int orderId){
		this.orderId = orderId;
	}
	public int getOrderId(){
		return this.orderId;
	}
	public void setArticleId(int articleId){
		this.articleId = articleId;
	}
	public int getArticleId(){
		return this.articleId;
	}
	public void setOrderNum(int orderNum){
		this.orderNum = orderNum;
	}
	public int getOrderNum(){
		return this.orderNum;
	}
	@Override
	public String toString() {
		return "OrderItem [orderId=" + orderId + ", articleId=" + articleId + ", orderNum=" + orderNum + ", article="
				+ article + "]";
	}

}