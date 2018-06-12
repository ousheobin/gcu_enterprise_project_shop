package cn.shaobin.entity;

import org.apache.ibatis.type.Alias;

@Alias(value="ShopCarEntity")
public class ShopCarEntity implements java.io.Serializable {

	private static final long serialVersionUID = -5696573633770345711L;
	
	private Integer id;
	private Integer articleId;
	private Integer buyNum;
	private Integer userId;

	private ArticleEntity article;// 物品信息

	public ArticleEntity getArticle() {
		return article;
	}

	public void setArticle(ArticleEntity article) {
		this.article = article;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public Integer getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(Integer buyNum) {
		this.buyNum = buyNum;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "ShopCar [id=" + id + ", articleId=" + articleId + ", buyNum=" + buyNum + ", userId=" + userId
				+ ", article=" + article + "]";
	}

}