package cn.shaobin.entity;

import org.apache.ibatis.type.Alias;

@Alias(value = "OrderItemEntity")
public class OrderItemEntity implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private int orderId;
	private int articleId;
	private int orderNum;
	private ArticleEntity article;// 封装物品信息

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public ArticleEntity getArticle() {
		return article;
	}

	public void setArticle(ArticleEntity article) {
		this.article = article;
	}

	@Override
	public String toString() {
		return "OrderItemEntity [id=" + id + ", orderId=" + orderId + ", articleId=" + articleId + ", orderNum="
				+ orderNum + ", article=" + article + "]";
	}

}