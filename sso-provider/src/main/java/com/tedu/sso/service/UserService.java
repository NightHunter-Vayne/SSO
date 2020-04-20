package com.tedu.sso.service;

import com.tedu.sso.pojo.User;
import com.tedu.sso.result.SysResult;

public interface UserService {
	//1、根据type=1：username,2:phone,3:email
	public SysResult check(String param, Integer type);
	
	//2、添加用户信息
	public void InsertUser(User user);
	
	//3、用户登录
	public String Login(String username, String password);
	
	//4、从 Redis 中获取ticket
	public boolean Query(String ticket);
}
