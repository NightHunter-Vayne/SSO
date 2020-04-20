package com.tedu.sso.pojo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

//基于MyBatis注解完成CRUD，这里引入MyBatisPlus，它能实现单表的CRUD操作不需要SQL语句
@TableName("tb_user")//数据库表和类的映射
public class User extends BasePojo implements Serializable{
	private static final long serialVersionUID = -748389533242503940L;
	//主键 自增主键
	@TableId(type=IdType.AUTO)
	private Long id;
	private String userName;
	private String password;
	private String phone;
	private String email;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
