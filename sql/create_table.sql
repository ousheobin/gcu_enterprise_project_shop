DROP TABLE IF EXISTS tb_article_type;
CREATE TABLE tb_article_type(
	f_code VARCHAR(255) PRIMARY KEY ,
	f_name VARCHAR(255) ,
	f_remark VARCHAR(255)
);

DROP TABLE IF EXISTS tb_article;
CREATE TABLE tb_article(
	f_id INT PRIMARY KEY AUTO_INCREMENT ,
	f_title VARCHAR(255) ,
	f_supplier VARCHAR(255) ,
	f_price DOUBLE ,
	f_discount DOUBLE ,
	f_locality VARCHAR(255) ,
	f_putaway_date DATETIME ,
	f_storage INT DEFAULT 0 ,
	f_image VARCHAR(255) ,
	f_type_code VARCHAR(255) ,
	f_create_date DATETIME ,
	f_disabled VARCHAR(255),
	f_description LONGTEXT,
	CONSTRAINT fk_aticle_type FOREIGN KEY (f_type_code) REFERENCES tb_article_type(f_code)
);



DROP TABLE IF EXISTS tb_user;
CREATE TABLE tb_user(
	f_id INT PRIMARY KEY AUTO_INCREMENT ,
	f_loginName VARCHAR(255) ,
	f_password VARCHAR(255) ,
	f_name VARCHAR(255) ,
	f_sex INT ,
	f_email VARCHAR(255) ,
	f_phone VARCHAR(255) ,
	f_address VARCHAR(255) ,
	f_role INT DEFAULT 1 ,
	f_create_date DATETIME ,
	f_disabled INT DEFAULT 0 ,
	f_active VARCHAR(255)
);

DROP TABLE IF EXISTS tb_order;
CREATE TABLE tb_order(
	f_id INT PRIMARY KEY AUTO_INCREMENT ,
	f_order_code VARCHAR(255) ,
	f_create_date DATETIME ,
	f_send_date DATETIME ,
	f_status VARCHAR(255) ,
	f_amount DOUBLE ,
	f_user_id INT ,
	CONSTRAINT fk_order_user FOREIGN KEY(f_user_id) REFERENCES tb_user(f_id)
);

DROP TABLE IF EXISTS tb_order_item;
CREATE TABLE tb_order_item(
	f_id INT PRIMARY KEY AUTO_INCREMENT,
	f_order_id INT,
	f_article_id INT,
	f_order_num INT,
	CONSTRAINT fk_order_item_article FOREIGN KEY(f_article_id) REFERENCES tb_article(f_id),
	CONSTRAINT fk_order_item_order FOREIGN KEY(f_order_id) REFERENCES tb_order(f_id)
);

DROP TABLE IF EXISTS tb_shop_car;
CREATE TABLE tb_shop_car (
	f_id INT PRIMARY KEY AUTO_INCREMENT,
	f_article_id INT,
	f_buy_num INT DEFAULT 1,
	f_user_id INT,
	CONSTRAINT fk_shop_car_article FOREIGN KEY(f_article_id) REFERENCES tb_article(f_id),
	CONSTRAINT fk_shop_car_user FOREIGN KEY(f_user_id) REFERENCES tb_user(f_id)
);